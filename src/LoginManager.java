
//--------------------------------------------------
//Assignment 2B
//©Ozwin Neel Lobo
//Written by: Ozwin Neel Lobo - 40228757
//--------------------------------------------------
import java.util.Scanner;

/*
 * The LoginManager class handles the authentication of the user, it supports 2 different levels of authentication i.e LOW , HIGH
 */
public class LoginManager {
	private static final String ADMIN_PASSWORD = "password";
	private static final byte DEFAULT_MAX_TRIES = 3;
	private static final byte MAX_TRIES_HIGH_LEVEL = 12;
	private static byte attemptsCounterLowLevel = 0, attemptsCounterHighLevel = 0, currentAttempCounterHighLevel = 0;

	enum Level {
		LOW, HIGH
	}

	public LoginManager() {

	}

	/*
	 * isAutorized:Based on the request type, authenticates the request and performs
	 * retries based on pre-determined criteria's
	 * 
	 * @param level : Which type of authentication is requested -> LOW/HIGH
	 * 
	 * @param scanner : Instance of scanner class , (we can use extend scanner as
	 * singleton class per application and inject the same instance wherever
	 * required)
	 * 
	 * @return boolean: if the request was authenticated or not
	 */
	public static boolean isAutorized(Level level, Scanner scanner) {
		String password = fetchPassword("Please enter your password", scanner);
		if (!password.equals(LoginManager.ADMIN_PASSWORD)) {
			switch (level) {
			case LOW:
				attemptsCounterLowLevel++;
				while (attemptsCounterLowLevel < DEFAULT_MAX_TRIES) {
					password = fetchPassword("That's wrong password, please try again!", scanner);
					if (LoginManager.ADMIN_PASSWORD.equals(password)) {
						attemptsCounterLowLevel = 0;
						currentAttempCounterHighLevel = 0;
						attemptsCounterHighLevel = 0;
						return true;
					}
					attemptsCounterLowLevel++;
				}
				attemptsCounterLowLevel = 0;
				return false;
			case HIGH:
				currentAttempCounterHighLevel++;
				attemptsCounterHighLevel++;
				while (currentAttempCounterHighLevel < DEFAULT_MAX_TRIES) {
					password = fetchPassword("That's wrong password, please try again!", scanner);
					if (LoginManager.ADMIN_PASSWORD.equals(password)) {
						attemptsCounterLowLevel = 0;
						currentAttempCounterHighLevel = 0;
						attemptsCounterHighLevel = 0;
						return true;
					}
					currentAttempCounterHighLevel++;
					attemptsCounterHighLevel++;
				}
				currentAttempCounterHighLevel = 0;
				if (attemptsCounterHighLevel >= MAX_TRIES_HIGH_LEVEL) {
					System.out.println("Program detected suspicious activities and will terminate immediately!");
					System.exit(0);
				}
				return false;
			}
		}
		attemptsCounterLowLevel = 0;
		attemptsCounterHighLevel = 0;
		currentAttempCounterHighLevel = 0;
		return true;
	}

	/*
	 * Utility function to help take input from the user
	 * 
	 * @param message : Display message for user
	 * 
	 * @param scanner : Instance of scanner class , (we can use extend scanner as
	 * singleton class per application and inject the same instance wherever
	 * required)
	 * 
	 * @return String: password string entered by user
	 */
	private static String fetchPassword(String message, Scanner scanner) {
		System.out.println(message);
		String userPassword = scanner.nextLine();
		return userPassword;

	}
	/*
	 * Determines if the number of attempts for a request type is exceeded or not
	 * 
	 * @param level : Which type of authentication is requested -> LOW/HIGH
	 * 
	 * @return boolean: if the number of attempts for particular request are
	 * exceeded or not.
	 */

	public static boolean isMaxAttemptsReached(Level level) {
		switch (level) {
		case LOW:
			if (attemptsCounterLowLevel >= DEFAULT_MAX_TRIES) {
				return true;
			}
			break;
		case HIGH:
			if (attemptsCounterHighLevel >= MAX_TRIES_HIGH_LEVEL) {
				return true;
			}
			break;
		}
		return false;
	}

}

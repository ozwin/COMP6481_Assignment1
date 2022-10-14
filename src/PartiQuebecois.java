//--------------------------------------------------
//Assignment 2B
//©Ozwin Neel Lobo
//Written by: Ozwin Neel Lobo - 40228757
//--------------------------------------------------
import java.util.Arrays;
import java.util.Scanner;
/*
 * The PartiQuebecois class handles the functionality of the tool.It provides the functionality to manipulate the voters data set.
 */
public class PartiQuebecois {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Parti Quebecois!");
		System.out.println("Please enter the maximum number of voters in the neighborhood.");
		/*
		 * numberOfVoters: Maximum no of voters that can be stored,requested by the application user
		 */
		int numberOfVoters = Integer.parseInt(scanner.nextLine().trim());
		/*
		 * numberOfVoters: Array of voters that stores the details of voters , entered by user
		 */
		Voter[] voterBase = new Voter[numberOfVoters];
		boolean allowOperations = true;
		while (allowOperations) {
			try {
				System.out.println(
						"What do you want to do?\n 1.\t Enter new voters(password required).\n 2.\t Change inofrmation of a voter\n 3.\t Display all voters by specific voterPcode.\n 4.\t Display all voters under certain age.\n 5.\t Quit\n Please enter your choice >");
				int userOption = Integer.parseInt(scanner.nextLine().trim());
				switch (userOption) {
				case 1:
					/*
					 * case 1: This operation allows user to add new voters to the system.
					 */					
					if (LoginManager.isAutorized(LoginManager.Level.HIGH, scanner)) {
						int availableSlots = (int) (numberOfVoters - Voter.findNumberOfCreatedVoters());
						System.out.println("Please enter the number of voters you want to enter");
						int voters = Integer.parseInt(scanner.nextLine().trim());
						if (voters > availableSlots) {
							System.out.println(String.format("Sorry only %d slots are available!", availableSlots));
							if (availableSlots > 0) {
								System.out.println(String.format("please enter details for %d voters", availableSlots));
							}
							voters = availableSlots;
						}
						System.out.println("Enter voter details");
						for (int i = 0; i < voters; i++) {
							Voter voter = new Voter();
							System.out.println("Name  :");
							voter.setVoterName(scanner.nextLine().trim());
							System.out.println("Email :");
							voter.setVoterEmail(scanner.nextLine().trim());
							System.out.println("Age   :");
							voter.setVoterAge(Byte.parseByte(scanner.nextLine().trim()));
							System.out.println("Pcode :");
							voter.setVoterPcode(scanner.nextLine().trim().toCharArray());
							voterBase[(int) Voter.findNumberOfCreatedVoters() - 1] = voter;
						}
					}
					break;
				case 2:
					boolean retry = true;
					if (LoginManager.isAutorized(LoginManager.Level.LOW, scanner)) {
						while (retry) {
							System.out.println("Please enter the voterID");
							long voterID = Long.parseLong(scanner.nextLine().trim());
							Voter currentVoter = null;
							boolean invalidID = true;
							for (int i = 0; i < Voter.findNumberOfCreatedVoters(); i++) {
								if (voterBase[i].getVoterID() == voterID) {
									System.out.println(String.format(" Voter :# %d",i+1));
									System.out.println(voterBase[i]);
									currentVoter = voterBase[i];
									invalidID = false;
									break;
								}
							}
							if (invalidID) {
								System.out.println(
										"That's a invalid voter ID , Please Enter\n 1.To re-enter voter ID\n 2.Go back to main menu");
								byte operation = Byte.parseByte(scanner.nextLine().trim());
								switch (operation) {
								case 1:
									break;
								case 2:
								default:
									retry = false;
									break;
								}
							} else {
								boolean allowUpdates = true;
								while (allowUpdates) {
									try {
										System.out.println("What information would you like to change?");
										System.out.println(
												" 1.Name \n 2.Age\n 3.Email\n 4.Pcode\n 5.Quit \n Enter your choice >");
										byte updateChoice = Byte.parseByte(scanner.nextLine().trim());
										switch (updateChoice) {
										case 1:
											System.out.println("Name  :");
											currentVoter.setVoterName(scanner.nextLine().trim());
											break;
										case 2:
											System.out.println("Age   :");
											currentVoter.setVoterAge(Byte.parseByte(scanner.nextLine().trim()));
											break;
										case 3:
											System.out.println("Email :");
											currentVoter.setVoterEmail(scanner.nextLine().trim());
											break;
										case 4:
											System.out.println("Pcode :");
											currentVoter.setVoterPcode(scanner.nextLine().trim().toCharArray());
											break;
										case 5:
											allowUpdates = false;
											retry = false;
											break;
										default:
											break;
										}
									} catch (NumberFormatException ex) {
										System.out.println("Sorry! that was a wrong option, Please try again!");
									} catch (Exception ex) {
										System.out.println(
												"Sorry! an error encountered while processing your last request, Please try again!");
									}
								}
							}
						}
					}
					break;
				case 3:
					System.out.println("Please enter pcode:");
					char[] pcode = scanner.nextLine().trim().toCharArray();
					for (int i = 0; i < Voter.findNumberOfCreatedVoters(); i++) {
						if (Arrays.equals(voterBase[i].getVoterPcode(), pcode)) {
							System.out.println(voterBase[i]);
						}
					}
					break;
				case 4:
					System.out.println("Please enter age:");
					byte age = Byte.parseByte(scanner.nextLine().trim());
					for (int i = 0; i < Voter.findNumberOfCreatedVoters(); i++) {
						if (voterBase[i].getVoterAge() < age) {
							System.out.println(voterBase[i]);
						}
					}
					break;
				case 5:
					allowOperations = false;
					System.out.println("Exiting the application!");
					break;
				default:
					break;
				}
			} catch (NumberFormatException ex) {
				System.out.println("Sorry! that was a wrong option, Please try again!");
			} catch (Exception ex) {
				System.out.println(String.format(
						"Sorry! an error encountered while processing your last request, Please try again! Error Details:%s",
						ex.toString()));
			}
		}
		scanner.close();
	}
}

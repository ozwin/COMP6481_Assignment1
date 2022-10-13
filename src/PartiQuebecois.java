import java.util.Scanner;

public class PartiQuebecois {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		System.out.println("Welcome to Parti Quebecois!");
		System.out.println("Please enter the number of voters.");
		int numberOfVoters=Integer.parseInt(scanner.nextLine().trim());
		Voter[] voterBase=new Voter[numberOfVoters];
		scanner.close();
	}

}

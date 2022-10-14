
public class Part2A {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Voter voter=new Voter();
		Voter voter2=new Voter("ozwin", (byte)10, "test", new char[5]);
		System.out.println(Voter.findNumberOfCreatedVoters());
		System.out.println(voter);
		System.out.println(voter2);

	}

}

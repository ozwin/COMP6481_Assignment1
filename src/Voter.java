//--------------------------------------------------
//Assignment 2A
//©Ozwin Neel Lobo
//Written by: Ozwin Neel Lobo - 40228757
//--------------------------------------------------
import java.util.Arrays;

public class Voter {
	private long voterID;
	private String voterName;
	private byte voterAge;
	private String voterEmail;
	private char[] voterPcode;
	static long counter = 0;

	public Voter() {
		counter++;
		this.voterID = counter;
		this.voterName = "";
		this.voterAge = 0;
		this.voterEmail = "";
		this.voterPcode = new char[6];
		Arrays.fill(voterPcode, Character.MIN_VALUE);
	}

	public Voter(String voterName, byte voterAge, String voterEmail, char[] voterPcode) {
		this.voterName = voterName;
		this.voterAge = voterAge;
		this.voterEmail = voterEmail;
		this.voterPcode = voterPcode;
		counter++;
		this.voterID = counter;
	}
	public long getVoterID() {
		return this.voterID;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public String getVoterName() {
		return this.voterName;
	}

	public void setVoterAge(byte voterAge) {
		this.voterAge = voterAge;
	}

	public byte getVoterAge() {
		return this.voterAge;
	}

	public void setVoterEmail(String voterEmail) {
		this.voterEmail = voterEmail;
	}

	public String getVoterEmail() {
		return this.voterEmail;
	}

	public void setVoterPcode(char[] voterPcode) {
		this.voterPcode = voterPcode;
	}

	public char[] getVoterPcode() {
		return this.voterPcode;
	}

	public static long findNumberOfCreatedVoters() {
		return counter;
	}

	public String toString() {
		return String.format(" Id:%d \n Name: %s \n Age: %d \n Email:%s \n Pcode:%s\n", voterID, voterName, voterAge,
				voterEmail, new String(voterPcode));
	}
	public boolean equals(Voter voter) {
		if(this.voterID==voter.getVoterID() && Arrays.equals(this.voterPcode, voter.getVoterPcode()))
				return true;
		return false;
	}
}
package factory_classes;

public class Candidate {
	
	private String name;
	private int numberOfMonthsToCommit;
	private DateCreator latestJoiningDate = new DateCreator();;
	private String technologiesKnown;
	private String phoneContact;
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfMonthsToCommit() {
		return numberOfMonthsToCommit;
	}
	public void setNumberOfMonthsToCommit(int numberOfMonthsToCommit) {
		this.numberOfMonthsToCommit = numberOfMonthsToCommit;
	}
	public DateCreator getLatestJoiningDate() {
		return latestJoiningDate;
	}
	public void setLatestJoiningDate(int dd, int mm, int yy) {
		latestJoiningDate.setDd(dd);
		latestJoiningDate.setMm(mm);
		latestJoiningDate.setYy(yy);
	}
	public String getTechnologiesKnown() {
		return technologiesKnown;
	}
	public void setTechnologiesKnown(String technologiesKnown) {
		this.technologiesKnown = technologiesKnown;
	}
	public String getPhoneContact() {
		return phoneContact;
	}
	public void setPhoneContact(String phoneContact) {
		this.phoneContact = phoneContact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
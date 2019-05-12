package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

public class OffPersonRepo extends OffPerson {

	public OffPersonRepo(int _id, int _personId, String _startDate, String _endDate, String _desc, int _userId,
			Boolean _status, String _personName, String _userName, int _howManyDays) {
		super(_id, _personId, _startDate, _endDate, _desc, _userId, _status);

		PersonName = _personName;
		UserName = _userName;
		HowManyDays = _howManyDays;

	}

	private String PersonName;
	private String UserName;
	private int HowManyDays;

	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String personName) {
		PersonName = personName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getHowManyDays() {
		return HowManyDays;
	}

	public void setHowManyDays(int howManyDays) {
		HowManyDays = howManyDays;
	}
}

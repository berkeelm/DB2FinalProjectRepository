package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

public class OffPerson {
	private int Id;
	private int PersonId;
	private String StartDate;
	private String EndDate;
	private String Description;
	private int UserId;
	private Boolean Status;

	public OffPerson(int _id, int _personId, String _startDate, String _endDate, String _desc, int _userId,
			Boolean _status) {
		Id = _id;
		PersonId = _personId;
		StartDate = _startDate;
		EndDate = _endDate;
		Description = _desc;
		UserId = _userId;
		Status = _status;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getPersonId() {
		return PersonId;
	}

	public void setPersonId(int personId) {
		PersonId = personId;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}
}

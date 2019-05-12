package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

public class PersonRepo extends Person {

	public PersonRepo(int _id, String _name, String _surname, String _dateOfBirth, String _socialSecurityNumber,
			Boolean _status, int _departmentId, int _addedUserId, String _departmentName, String _addedUserName) {
		super(_id, _name, _surname, _dateOfBirth, _socialSecurityNumber, _status, _departmentId, _addedUserId);
		setDepartmentName(_departmentName);
		setAddedUserName(_addedUserName);
	}

	private String DepartmentName;
	private String AddedUserName;

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getAddedUserName() {
		return AddedUserName;
	}

	public void setAddedUserName(String addedUserName) {
		AddedUserName = addedUserName;
	}
}
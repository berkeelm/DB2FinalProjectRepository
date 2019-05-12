package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

public class Person {
	private int Id;
	private String Name;
	private String Surname;
	private String DateOfBirth;
	private String SocialSecurityNumber;
	private Boolean Status;
	private int DepartmentId;
	private int AddedUserId;

	public Person(int _id, String _name, String _surname, String _dateOfBirth, String _socialSecurityNumber,
			Boolean _status, int _departmentId, int _addedUserId) {
		this.Id = _id;
		this.Name = _name;
		this.Surname = _surname;
		this.DateOfBirth = _dateOfBirth;
		this.Status = _status;
		this.DepartmentId = _departmentId;
		this.AddedUserId = _addedUserId;
		this.SocialSecurityNumber = _socialSecurityNumber;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getSocialSecurityNumber() {
		return SocialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		SocialSecurityNumber = socialSecurityNumber;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public int getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
	}

	public int getAddedUserId() {
		return AddedUserId;
	}

	public void setAddedUserId(int addedUserId) {
		AddedUserId = addedUserId;
	}

}
package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

public class Person {
	private int Id;
	private String Name;
	private String Surname;
	private String DateOfBirth;
	private String SocialSecurtyNumber;
	private String ProfilePhoto;
	private Boolean Status;
	private String DepartmentName;

	public Person(int _id, String _name, String _surname, String _dateOfBirth, String _socialSecurityNumber,
			String _profilePhoto, Boolean _status, String _departmentName) {
		this.Id = _id;
		this.Name = _name;
		this.Surname = _surname;
		this.DateOfBirth = _dateOfBirth;
		this.SocialSecurtyNumber = _socialSecurityNumber;
		this.ProfilePhoto = _profilePhoto;
		this.Status = _status;
		this.DepartmentName = _departmentName;
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

	public String getSocialSecurtyNumber() {
		return SocialSecurtyNumber;
	}

	public void setSocialSecurtyNumber(String socialSecurtyNumber) {
		SocialSecurtyNumber = socialSecurtyNumber;
	}

	public String getProfilePhoto() {
		return ProfilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		ProfilePhoto = profilePhoto;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
}
package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

public class User {
	private int Id;
	private String Username;
	private String Password;
	private Boolean IsAdmin;
	private Boolean Status;

	public User(int _id, String _username, String _password, Boolean _isAdmin, Boolean _status) {
		this.Id = _id;
		this.Username = _username;
		this.Password = _password;
		this.IsAdmin = _isAdmin;
		this.Status = _status;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Boolean getIsAdmin() {
		return IsAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		IsAdmin = isAdmin;
	}

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}
}

package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

public class Department {
	private int Id;
	private String Name;
	private Boolean Status;

	public Department(int _id, String _name, Boolean _status) {
		this.Id = _id;
		this.Name = _name;
		this.Status = _status;
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

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean status) {
		Status = status;
	}
}
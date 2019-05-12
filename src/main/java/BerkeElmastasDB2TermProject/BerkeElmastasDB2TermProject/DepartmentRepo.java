package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

public class DepartmentRepo extends Department
{
	public DepartmentRepo(int _id, String _name, Boolean _status, int _userCount) {
		super(_id, _name, _status);
		setUserCount(_userCount);
	}
	
	private int UserCount;

	public int getUserCount() {
		return UserCount;
	}

	public void setUserCount(int userCount) {
		UserCount = userCount;
	}
}
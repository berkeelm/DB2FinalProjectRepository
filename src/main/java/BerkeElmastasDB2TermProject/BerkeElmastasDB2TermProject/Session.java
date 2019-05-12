package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

public class Session {
	private static User _loggedUser = null;

	public static User get_loggedUser() {
		return _loggedUser;
	}

	public static void set_loggedUser(User _loggedUser) {
		Session._loggedUser = _loggedUser;
	}
}

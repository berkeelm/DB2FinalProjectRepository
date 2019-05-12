package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.bind.DataBindingException;

public class DBO {
	private static DBO instance = null;
	private static Connection con = null;

	private DBO() {
		try {
			con = DriverManager.getConnection(
					"jdbc:sqlserver://MyJavaProjectDB2019.mssql.somee.com;databaseName=MyJavaProjectDB2019;portNumber=1433",
					"berkeelm_SQLLogin_1", "jnmhvreruc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ResultSet RunQuery(String _query) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(_query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static DBO GetInstance() {
		if (instance == null)
			instance = new DBO();

		return instance;
	}

	public Boolean SaveOffPerson(int _id, String _personName, String _startDate, int _howManyDays, String _desc) {
		try {
			ResultSet resultSet = RunQuery("EXEC USP_SavePersonOff @Id = " + _id + ", @PersonNameSurname = N'"
					+ _personName + "', @StartDate = '" + _startDate + "', @HowManyDays = " + _howManyDays
					+ ", @Desc = N'" + _desc + "', @UserId = " + Session.get_loggedUser().getId());
			resultSet.next();
			String processResult = resultSet.getString("ProcessResult");
			resultSet.close();
			return processResult.equals("true");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public OffPersonRepo GetPersonOffById(int _id) {
		try {
			OffPersonRepo returnObj = null;
			String query = "EXEC USP_GetPersonOffById @Id = " + _id;
			ResultSet resultSet = RunQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				int personId = resultSet.getInt("Id");
				String startDate = resultSet.getString("StartDate");
				String endDate = resultSet.getString("EndDate");
				String desc = resultSet.getString("Description");
				int userId = resultSet.getInt("UserId");
				Boolean status = resultSet.getBoolean("Status");
				String personName = resultSet.getString("PersonName");
				String userName = resultSet.getString("UserName");
				int howManyDays = resultSet.getInt("HowManyDays");

				returnObj = new OffPersonRepo(id, personId, startDate, endDate, desc, userId, status, personName,
						userName, howManyDays);
			}
			return returnObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User GetUserById(int _id) {
		try {
			User returnObj = null;
			String query = "EXEC USP_GetUserById @Id = " + _id;
			ResultSet resultSet = RunQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String username = resultSet.getString("Username");
				String password = resultSet.getString("Password");
				Boolean isAdmin = resultSet.getBoolean("IsAdmin");
				Boolean status = resultSet.getBoolean("Status");

				returnObj = new User(id, username, password, isAdmin, status);
			}
			return returnObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<User> GetAllUsers() {
		try {
			ArrayList<User> returnList = new ArrayList<User>();
			String query = "EXEC USP_GetAllUsers @UserId = " + Session.get_loggedUser().getId();
			ResultSet resultSet = RunQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String username = resultSet.getString("Username");
				String password = resultSet.getString("Password");
				Boolean isAdmin = resultSet.getBoolean("IsAdmin");
				Boolean status = resultSet.getBoolean("Status");

				returnList.add(new User(id, username, password, isAdmin, status));
			}
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<OffPersonRepo> GetAllPersonOffsBetweenTwoDate(String _startDate, String _endDate) {
		try {
			ArrayList<OffPersonRepo> returnList = new ArrayList<OffPersonRepo>();
			String query = "EXEC USP_GetAllPersonOffsBetweenTwoDate @StartDate = '" + _startDate + "', @EndDate = '"
					+ _endDate + "'";
			ResultSet resultSet = RunQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				int personId = resultSet.getInt("PersonId");
				String startDate = resultSet.getString("StartDate");
				String endDate = resultSet.getString("EndDate");
				String desc = resultSet.getString("Description");
				int userId = resultSet.getInt("UserId");
				Boolean status = resultSet.getBoolean("Status");
				String personName = resultSet.getString("PersonName");
				String userName = resultSet.getString("UserName");
				int howManyDays = resultSet.getInt("HowManyDays");

				returnList.add(new OffPersonRepo(id, personId, startDate, endDate, desc, userId, status, personName,
						userName, howManyDays));
			}
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<OffPersonRepo> GetAllOffPersons() {
		try {
			ArrayList<OffPersonRepo> returnList = new ArrayList<OffPersonRepo>();
			String query = "EXEC USP_GetAllPersonOffs";
			ResultSet resultSet = RunQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				int personId = resultSet.getInt("PersonId");
				String startDate = resultSet.getString("StartDate");
				String endDate = resultSet.getString("EndDate");
				String desc = resultSet.getString("Description");
				int userId = resultSet.getInt("UserId");
				Boolean status = resultSet.getBoolean("Status");
				String personName = resultSet.getString("PersonName");
				String userName = resultSet.getString("UserName");
				int howManyDays = resultSet.getInt("HowManyDays");

				returnList.add(new OffPersonRepo(id, personId, startDate, endDate, desc, userId, status, personName,
						userName, howManyDays));
			}
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User LoginControl(String _username, String _password) {
		try {
			User returnObj = null;
			String query = "EXEC USP_LoginControl @Username = '" + _username + "', @Password = '" + _password + "'";
			ResultSet resultSet = RunQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String username = resultSet.getString("Username");
				String password = resultSet.getString("Password");
				Boolean isAdmin = resultSet.getBoolean("IsAdmin");
				Boolean status = resultSet.getBoolean("Status");

				returnObj = new User(id, username, password, isAdmin, status);
			}
			return returnObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<PersonRepo> GetAllPerson() {
		try {
			ArrayList<PersonRepo> returnList = new ArrayList<PersonRepo>();
			String query = "EXEC USP_GetAllPersons";
			ResultSet resultSet = RunQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String name = resultSet.getString("Name");
				String surname = resultSet.getString("Surname");
				String dateOfBirth = resultSet.getString("DateOfBirth");
				String socialSecurtyNumber = resultSet.getString("SocialSecurityNumber");
				Boolean status = resultSet.getBoolean("Status");
				int addedUserId = resultSet.getInt("AddedUserId");
				String addedUserName = resultSet.getString("AddedUserName");
				int departmentId = resultSet.getInt("DepartmentId");
				String departmentName = resultSet.getString("DepartmentName");

				returnList.add(new PersonRepo(id, name, surname, dateOfBirth, socialSecurtyNumber, status, departmentId,
						addedUserId, departmentName, addedUserName));
			}
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PersonRepo GetPersonById(int _id) {
		try {
			ArrayList<PersonRepo> returnList = new ArrayList<PersonRepo>();
			ResultSet resultSet = RunQuery("EXEC USP_GetPersonById @_userId = " + _id);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String name = resultSet.getString("Name");
				String surname = resultSet.getString("Surname");
				String dateOfBirth = resultSet.getString("DateOfBirth");
				String socialSecurtyNumber = resultSet.getString("SocialSecurityNumber");
				Boolean status = resultSet.getBoolean("Status");
				int addedUserId = resultSet.getInt("AddedUserId");
				String addedUserName = resultSet.getString("AddedUserName");
				int departmentId = resultSet.getInt("DepartmentId");
				String departmentName = resultSet.getString("Status");

				returnList.add(new PersonRepo(id, name, surname, dateOfBirth, socialSecurtyNumber, status, departmentId,
						addedUserId, departmentName, addedUserName));
			}

			return returnList.size() == 0 ? null : returnList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<DepartmentRepo> GetAllDepartments() {
		try {
			ArrayList<DepartmentRepo> returnList = new ArrayList<DepartmentRepo>();
			ResultSet resultSet = RunQuery("EXEC USP_GetAllDepartments");
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String name = resultSet.getString("Name");
				Boolean status = resultSet.getBoolean("Status");
				int userCount = resultSet.getInt("UserCount");

				returnList.add(new DepartmentRepo(id, name, status, userCount));
			}
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DepartmentRepo GetDepartmentById(int _id) {
		try {
			DepartmentRepo returnObj = null;
			ResultSet resultSet = RunQuery("EXEC USP_GetDepartmentById @Id=" + _id);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String name = resultSet.getString("Name");
				Boolean status = resultSet.getBoolean("Status");
				int userCount = 0;

				returnObj = new DepartmentRepo(id, name, status, userCount);
			}
			return returnObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean DeletePersonOff(int _id) {
		try {
			String query = "EXEC USP_DeletePersonOff @Id = " + _id;
			ResultSet resultSet = RunQuery(query);
			resultSet.next();
			String processResult = resultSet.getString("ProcessResult");
			resultSet.close();
			return processResult.equals("true");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Boolean DeleteUser(int _userId) {
		try {
			String query = "EXEC USP_DeleteUser @Id = " + _userId;
			ResultSet resultSet = RunQuery(query);
			resultSet.next();
			String processResult = resultSet.getString("ProcessResult");
			resultSet.close();
			return processResult.equals("true");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean DeleteDepartment(int _departmentId) {
		try {
			String query = "EXEC USP_DeleteDepartment @Id = " + _departmentId;
			ResultSet resultSet = RunQuery(query);
			resultSet.next();
			String processResult = resultSet.getString("ProcessResult");
			resultSet.close();
			return processResult.equals("true");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean SaveDepartment(int _departmentId, String _departmentName) {
		try {
			ResultSet resultSet = RunQuery(
					"EXEC USP_SaveDepartment @Id=" + _departmentId + ", @DepartmentName=N'" + _departmentName + "'");
			resultSet.next();
			String processResult = resultSet.getString("ProcessResult");
			resultSet.close();
			return processResult.equals("true");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean SaveUser(int _id, String _username, String _password, Boolean _isAdmin) {
		try {
			String query = "EXEC USP_SaveUser @Id = " + _id + ", @Username = '" + _username + "', @Password = '"
					+ _password + "', @IsAdmin = " + (_isAdmin ? "1" : "0");
			ResultSet resultSet = RunQuery(query);
			resultSet.next();
			String processResult = resultSet.getString("ProcessResult");
			resultSet.close();
			return processResult.equals("true");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean SavePerson(int _personId, String _name, String _surname, String _departmentName, String _dateOfBirth,
			String _ssn) {
		try {
			String query = "EXEC USP_SavePerson @Id = " + _personId + ", @Name = N'" + _name + "', @Surname = N'"
					+ _surname + "', @DepartmentName = N'" + _departmentName + "', @DateOfBirth = '" + _dateOfBirth
					+ "', @SocialSecurityNumber = '" + _ssn + "', @AddedUserId = " + Session.get_loggedUser().getId();
			ResultSet resultSet = RunQuery(query);
			resultSet.next();
			String processResult = resultSet.getString("ProcessResult");
			resultSet.close();
			return processResult.equals("true");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Boolean DeletePerson(int _personId) {
		try {
			String query = "EXEC USP_DeletePerson @Id = " + _personId;
			ResultSet resultSet = RunQuery(query);
			resultSet.next();
			String processResult = resultSet.getString("ProcessResult");
			resultSet.close();
			return processResult.equals("true");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}

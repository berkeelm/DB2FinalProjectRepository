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
					"jdbc:sqlserver://188.121.44.217;databaseName=MyJavaProjectDB;portNumber=1433", "berkeelm",
					"berkex123");
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

	public Boolean LoginControl(String _username, String _password) {
		try {
			String loginQuery = "SELECT COUNT(*) AS UserCount FROM [User] WHERE Username = '" + _username
					+ "' AND Password = '" + _password + "' AND Status = 1";
			ResultSet resultSet = RunQuery(loginQuery);
			resultSet.next();
			int count = resultSet.getInt("UserCount");
			resultSet.close();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Person> GetAllPerson() {
		try {
			ArrayList<Person> returnList = new ArrayList<Person>();
			String query = "SELECT P.Id AS Id, P.Name, P.Surname, P.DateOfBirth, P.SocialSecurityNumber, P.ProfilePhoto, P.Status, D.Name AS DepartmentName FROM Person AS P INNER JOIN Department AS D ON D.Id = P.DepartmentId WHERE P.Status = 1";
			ResultSet resultSet = RunQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String name = resultSet.getString("Name");
				String surname = resultSet.getString("Surname");
				String dateOfBirth = resultSet.getString("DateOfBirth");
				String socialSecurtyNumber = resultSet.getString("SocialSecurityNumber");
				String profilePhoto = resultSet.getString("ProfilePhoto");
				String departmentName = resultSet.getString("DepartmentName");
				Boolean status = resultSet.getBoolean("Status");

				returnList.add(new Person(id, name, surname, dateOfBirth, socialSecurtyNumber, profilePhoto, status,
						departmentName));
			}
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Person GetPersonById(int _id) {
		try {
			ArrayList<Person> returnList = new ArrayList<Person>();
			String query = "SELECT P.Id AS Id, P.Name, P.Surname, P.DateOfBirth, P.SocialSecurityNumber, P.ProfilePhoto, P.Status, D.Name AS DepartmentName FROM Person AS P INNER JOIN Department AS D ON D.Id = P.DepartmentId WHERE P.Status = 1 AND P.Id = "
					+ _id;
			ResultSet resultSet = RunQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String name = resultSet.getString("Name");
				String surname = resultSet.getString("Surname");
				String dateOfBirth = resultSet.getString("DateOfBirth");
				String socialSecurtyNumber = resultSet.getString("SocialSecurityNumber");
				String profilePhoto = resultSet.getString("ProfilePhoto");
				String departmentName = resultSet.getString("DepartmentName");
				Boolean status = resultSet.getBoolean("Status");

				returnList.add(new Person(id, name, surname, dateOfBirth, socialSecurtyNumber, profilePhoto, status,
						departmentName));
			}

			return returnList.size() == 0 ? null : returnList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Department> GetAllDepartments() {
		try {
			ArrayList<Department> returnList = new ArrayList<Department>();
			String query = "SELECT * FROM Department WHERE Status = 1";
			ResultSet resultSet = RunQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String name = resultSet.getString("Name");
				Boolean status = resultSet.getBoolean("Status");

				returnList.add(new Department(id, name, status));
			}
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean SavePerson(int _personId, String _name, String _surname, String _departmentName, String _dateOfBirth,
			String _ssn) {
		try {
			String query = "EXEC SavePerson @Id = " + _personId + ", @Name = '" + _name + "', @Surname = '" + _surname
					+ "', @DepartmentName = '" + _departmentName + "', @DateOfBirth = '" + _dateOfBirth
					+ "', @SocialSecurityNumber = '" + _ssn + "'";
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
			String query = "EXEC DeletePerson @Id = " + _personId;
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

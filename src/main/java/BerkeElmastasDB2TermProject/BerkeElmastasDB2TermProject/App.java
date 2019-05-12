package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.*;

import javax.swing.JOptionPane;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			JOptionPane.showMessageDialog(null, "", "", 0);

			System.out.println("started");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:sqlserver://188.121.44.217;databaseName=MyJavaProjectDB;portNumber=1433", "berkeelm",
					"berkex123");// repalce your databse name and user name
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from [User]");// replace your table name
			while (rs.next()) {
				String s1 = rs.getString(1);
				String s2 = rs.getString(2);
				System.out.println("UserID:" + s1 + "Password:" + s2);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//188.121.44.217
//MyJavaProjectDB
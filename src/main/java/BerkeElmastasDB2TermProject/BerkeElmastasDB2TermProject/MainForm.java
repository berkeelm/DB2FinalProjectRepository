package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class MainForm {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 309, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("New label");
		lblUsername.setBounds(10, 11, 97, 14);
		frame.getContentPane().add(lblUsername);

		lblUsername.setText("Hello, " + Session.get_loggedUser().getUsername());

		JButton btnDepartment = new JButton("Manage Departments");
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DepartmentForm();
			}
		});
		btnDepartment.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDepartment.setBounds(10, 38, 273, 42);
		frame.getContentPane().add(btnDepartment);

		JButton btnManageOffPersons = new JButton("Manage Off Persons");
		btnManageOffPersons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OffPersonForm();
			}
		});
		btnManageOffPersons.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnManageOffPersons.setBounds(10, 197, 273, 42);
		frame.getContentPane().add(btnManageOffPersons);

		JButton btnSearchPersonOff = new JButton("Search Person Off Days");
		btnSearchPersonOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OffPersonInfoForm();
			}
		});
		btnSearchPersonOff.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearchPersonOff.setBounds(10, 250, 273, 42);
		frame.getContentPane().add(btnSearchPersonOff);

		JButton btnManagePersons = new JButton("Manage Persons");
		btnManagePersons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PersonForm();
			}
		});
		btnManagePersons.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnManagePersons.setBounds(10, 91, 273, 42);
		frame.getContentPane().add(btnManagePersons);

		JButton btnManageUsers = new JButton("Manage Users");
		btnManageUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserForm();
			}
		});
		btnManageUsers.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnManageUsers.setBounds(10, 144, 273, 42);
		frame.getContentPane().add(btnManageUsers);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginForm();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogout.setBounds(10, 356, 273, 42);
		frame.getContentPane().add(btnLogout);

		JButton btnWhoIsOff = new JButton("Who Is Off Today?");
		btnWhoIsOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String today = new SimpleDateFormat("yyyy").format(new Date()) + "-"
						+ new SimpleDateFormat("MM").format(new Date()) + "-"
						+ new SimpleDateFormat("dd").format(new Date());

				String msg = "";
				ArrayList<OffPersonRepo> list = DBO.GetInstance().GetAllPersonOffsBetweenTwoDate(today, today);

				for (OffPersonRepo offPersonRepo : list) {
					msg += offPersonRepo.getPersonName() + ": " + offPersonRepo.getStartDate() + " - "
							+ offPersonRepo.getEndDate() + " (" + offPersonRepo.getHowManyDays() + " Days)\n\n";
				}

				JOptionPane.showMessageDialog(null, msg.equals("") ? "No records found." : msg);
			}
		});
		btnWhoIsOff.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnWhoIsOff.setBounds(10, 303, 273, 42);
		frame.getContentPane().add(btnWhoIsOff);

		if (!Session.get_loggedUser().getIsAdmin()) {
			btnManageOffPersons.setEnabled(false);
			btnManagePersons.setEnabled(false);
			btnManageUsers.setEnabled(false);
			btnDepartment.setEnabled(false);
		}

		java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}
}

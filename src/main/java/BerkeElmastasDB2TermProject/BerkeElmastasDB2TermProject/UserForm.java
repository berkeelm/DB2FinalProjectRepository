package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class UserForm {

	private JFrame frame;
	private JTextField tbUsername;
	private JTextField tbUserId;
	private JList list_1;
	private JList list_2;
	private JPasswordField tbPassword;
	private JComboBox cbUserType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserForm window = new UserForm();
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
	public UserForm() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private String equalSpace(Object obj, int lengthShoudBe) {
		String str = obj.toString().trim();
		while (str.length() < lengthShoudBe)
			str += " ";

		return str;
	}

	private String getSpace() {
		return "     ";
	}

	private void fillUserList() {
		ArrayList<User> userList = DBO.GetInstance().GetAllUsers();

		String[] strArr = new String[userList.size()];
		int index = 0;
		for (User user : userList) {
			strArr[index] = equalSpace(user.getId(), 5) + getSpace() + equalSpace(user.getUsername(), 20) + getSpace()
					+ equalSpace(user.getIsAdmin() == true ? "Admin" : "User", 10);
			index++;
		}

		list_2.setListData(strArr);
		strArr = new String[1];
		strArr[0] = equalSpace("Id", 5) + getSpace() + equalSpace("Username", 20) + getSpace()
				+ equalSpace("User Type", 10);
		list_1.setListData(strArr);
	}

	private void clearForm() {
		tbUsername.setText("");
		tbPassword.setText("");
		tbUserId.setText("0");
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1333, 563);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		list_2 = new JList();
		list_2.setBounds(10, 32, 899, 429);
		list_2.setFont(new Font("monospaced", Font.PLAIN, 13));
		frame.getContentPane().add(list_2);

		list_1 = new JList();
		list_1.setEnabled(false);
		list_1.setBounds(10, 11, 899, 24);
		list_1.setFont(new Font("monospaced", Font.PLAIN, 13));
		frame.getContentPane().add(list_1);

		fillUserList();

		final JPanel panel = new JPanel();
		panel.setBounds(919, 11, 392, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);

		JLabel lblName = new JLabel("Username");
		lblName.setBounds(10, 11, 188, 14);
		panel.add(lblName);

		tbUsername = new JTextField();
		tbUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbUsername.setBounds(10, 28, 360, 31);
		panel.add(tbUsername);
		tbUsername.setColumns(10);

		tbUserId = new JTextField();
		tbUserId.setText("0");
		tbUserId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbUserId.setColumns(10);
		tbUserId.setBounds(10, 198, 102, 31);
		tbUserId.setVisible(false);
		panel.add(tbUserId);

		JButton btnSaveUser = new JButton("Save User");
		btnSaveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int userId = Integer.parseInt(tbUserId.getText());
				String username = tbUsername.getText();
				String password = tbPassword.getText();
				Boolean isAdmin = cbUserType.getSelectedItem().toString() == "Admin";

				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill all the blanks.", "Error!", 0);
				} else {
					Boolean saveResult = DBO.GetInstance().SaveUser(userId, username, password, isAdmin);

					if (saveResult) {
						JOptionPane.showMessageDialog(null, "User Saved!", "Success!", 0);
						fillUserList();
						clearForm();
						panel.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
					}
				}
			}
		});
		btnSaveUser.setBounds(223, 198, 147, 48);
		panel.add(btnSaveUser);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 73, 188, 14);
		panel.add(lblPassword);

		tbPassword = new JPasswordField();
		tbPassword.setBounds(10, 90, 360, 31);
		panel.add(tbPassword);

		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setBounds(10, 132, 212, 14);
		panel.add(lblUserType);

		cbUserType = new JComboBox();
		cbUserType.setBounds(10, 150, 360, 31);
		panel.add(cbUserType);

		JButton btnCreateNewUser = new JButton("Create New User");
		btnCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearForm();
				panel.setVisible(true);
			}
		});
		btnCreateNewUser.setBounds(682, 472, 227, 31);
		frame.getContentPane().add(btnCreateNewUser);

		JButton btnUpdateSelectedUser = new JButton("Update Selected User");
		btnUpdateSelectedUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list_2.getSelectedIndex();

				if (selectedIndex == -1)
					JOptionPane.showMessageDialog(null, "Select an item first!", "Error!", 0);
				else {
					int userId = Integer.parseInt(list_2.getSelectedValue().toString().split(" ")[0]);
					User user = DBO.GetInstance().GetUserById(userId);

					if (user == null) {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
						return;
					}
					clearForm();
					tbUserId.setText(String.valueOf(user.getId()));
					tbUsername.setText(user.getUsername());
					tbPassword.setText(user.getPassword());
					cbUserType.setSelectedItem(user.getIsAdmin() ? "Admin" : "User");

					panel.setVisible(true);
				}
			}
		});
		btnUpdateSelectedUser.setBounds(445, 472, 227, 31);
		frame.getContentPane().add(btnUpdateSelectedUser);

		JButton btnDeleteSelectedUser = new JButton("Delete Selected User");
		btnDeleteSelectedUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list_2.getSelectedIndex();

				if (selectedIndex == -1)
					JOptionPane.showMessageDialog(null, "Select an item first!", "Error!", 0);
				else {
					int userId = Integer.parseInt(list_2.getSelectedValue().toString().split(" ")[0]);

					Boolean processResult = DBO.GetInstance().DeleteUser(userId);

					if (processResult) {
						JOptionPane.showMessageDialog(null, "User Deleted!", "Success!", 0);
						fillUserList();
						clearForm();
						panel.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
					}
				}
			}
		});
		btnDeleteSelectedUser.setBounds(208, 472, 227, 31);
		frame.getContentPane().add(btnDeleteSelectedUser);

		cbUserType.addItem("User");
		cbUserType.addItem("Admin");

		java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}
}

package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class LoginForm {

	private JFrame frame;
	private JTextField tbUsername;
	private JPasswordField tbPassword;
	private JLabel lblPassword;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
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
	public LoginForm() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 312, 261);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		tbUsername = new JTextField();
		tbUsername.setBounds(110, 100, 166, 20);
		frame.getContentPane().add(tbUsername);
		tbUsername.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(10, 103, 75, 14);
		frame.getContentPane().add(lblNewLabel);

		tbPassword = new JPasswordField();
		tbPassword.setColumns(10);
		tbPassword.setBounds(110, 131, 166, 20);
		frame.getContentPane().add(tbPassword);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 134, 75, 14);
		frame.getContentPane().add(lblPassword);

		btnLogin = new JButton("Login!");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = tbUsername.getText();
				String password = tbPassword.getText();

				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill all the blanks.", "Error!", 0);
				} else {
					User result = DBO.GetInstance().LoginControl(username, password);

					if (result == null)
						JOptionPane.showMessageDialog(null, "Username or password is wrong.", "Error!", 0);
					else {
						Session.set_loggedUser(result);
						frame.setVisible(false);
						new MainForm();
					}
				}
			}
		});
		btnLogin.setBounds(171, 162, 105, 39);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Berke\\Desktop\\javaprojectlogo.png"));
		lblNewLabel_1.setBounds(63, 22, 169, 61);
		frame.getContentPane().add(lblNewLabel_1);

		java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}
}

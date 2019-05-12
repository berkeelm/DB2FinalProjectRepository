package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DepartmentForm {

	private JFrame frame;
	private JTextField tbDepartmentName;
	private JTextField tbDepartmentId;
	private JList list_1;
	private JList list_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentForm window = new DepartmentForm();
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
	public DepartmentForm() {
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

	private void fillDepartmentList() {
		ArrayList<DepartmentRepo> departmentList = DBO.GetInstance().GetAllDepartments();

		String[] strArr = new String[departmentList.size()];
		int index = 0;
		for (DepartmentRepo department : departmentList) {
			strArr[index] = equalSpace(department.getId(), 5) + getSpace() + equalSpace(department.getName(), 35)
					+ getSpace() + equalSpace(department.getUserCount(), 5);
			index++;
		}

		list_2.setListData(strArr);
		strArr = new String[1];
		strArr[0] = equalSpace("Id", 5) + getSpace() + equalSpace("Department Name", 35) + getSpace()
				+ equalSpace("Person Count", 12);
		list_1.setListData(strArr);
	}

	private void clearForm() {
		tbDepartmentName.setText("");
		tbDepartmentId.setText("0");
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

		fillDepartmentList();

		final JPanel panel = new JPanel();
		panel.setBounds(919, 11, 392, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);

		JLabel lblName = new JLabel("Department Name");
		lblName.setBounds(10, 11, 188, 14);
		panel.add(lblName);

		tbDepartmentName = new JTextField();
		tbDepartmentName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbDepartmentName.setBounds(10, 28, 360, 31);
		panel.add(tbDepartmentName);
		tbDepartmentName.setColumns(10);

		tbDepartmentId = new JTextField();
		tbDepartmentId.setText("0");
		tbDepartmentId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbDepartmentId.setColumns(10);
		tbDepartmentId.setBounds(10, 70, 102, 31);
		tbDepartmentId.setVisible(false);
		panel.add(tbDepartmentId);

		JButton btnSaveDepartment = new JButton("Save Department");
		btnSaveDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int departmentId = Integer.parseInt(tbDepartmentId.getText());
				String departmentName = tbDepartmentName.getText();

				if (departmentName.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill all the blanks.", "Error!", 0);
				} else {
					Boolean saveResult = DBO.GetInstance().SaveDepartment(departmentId, departmentName);

					if (saveResult) {
						JOptionPane.showMessageDialog(null, "Department Saved!", "Success!", 0);
						fillDepartmentList();
						clearForm();
						panel.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
					}
				}
			}
		});
		btnSaveDepartment.setBounds(223, 70, 147, 48);
		panel.add(btnSaveDepartment);

		JButton btnCreateNewDepartment = new JButton("Create New Department");
		btnCreateNewDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearForm();
				panel.setVisible(true);
			}
		});
		btnCreateNewDepartment.setBounds(682, 472, 227, 31);
		frame.getContentPane().add(btnCreateNewDepartment);

		JButton btnUpdateSelectedDepartment = new JButton("Update Selected Department");
		btnUpdateSelectedDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list_2.getSelectedIndex();

				if (selectedIndex == -1)
					JOptionPane.showMessageDialog(null, "Select a department first!", "Error!", 0);
				else {
					int departmentId = Integer.parseInt(list_2.getSelectedValue().toString().split(" ")[0]);
					DepartmentRepo department = DBO.GetInstance().GetDepartmentById(departmentId);

					if (department == null) {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
						return;
					}
					clearForm();
					tbDepartmentId.setText(String.valueOf(department.getId()));
					tbDepartmentName.setText(department.getName());

					panel.setVisible(true);
				}
			}
		});
		btnUpdateSelectedDepartment.setBounds(445, 472, 227, 31);
		frame.getContentPane().add(btnUpdateSelectedDepartment);

		JButton btnDeleteSelectedDepartment = new JButton("Delete Selected Department");
		btnDeleteSelectedDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list_2.getSelectedIndex();

				if (selectedIndex == -1)
					JOptionPane.showMessageDialog(null, "Select a department first!", "Error!", 0);
				else {
					int departmentId = Integer.parseInt(list_2.getSelectedValue().toString().split(" ")[0]);

					Boolean processResult = DBO.GetInstance().DeleteDepartment(departmentId);

					if (processResult) {
						JOptionPane.showMessageDialog(null, "Department Deleted!", "Success!", 0);
						fillDepartmentList();
						clearForm();
						panel.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
					}
				}
			}
		});
		btnDeleteSelectedDepartment.setBounds(208, 472, 227, 31);
		frame.getContentPane().add(btnDeleteSelectedDepartment);
		
		java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}
}

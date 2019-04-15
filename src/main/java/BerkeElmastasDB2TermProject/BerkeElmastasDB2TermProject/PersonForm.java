package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.bouncycastle.crypto.macs.CBCBlockCipherMac;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonForm {

	private JFrame frame;
	private JTextField tbName;
	private JTextField tbSurname;
	private JTextField tbSsn;
	private JTextField tbPersonId;
	private JList list;
	private JList list_1;
	private JComboBox cbDepartmentName = null;
	private JComboBox cbDay = null;
	private JComboBox cbMonth = null;
	private JComboBox cbYear = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonForm window = new PersonForm();
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
	public PersonForm() {
		initialize();
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

	private void fillPersonList() {
		ArrayList<Person> personList = DBO.GetInstance().GetAllPerson();

		String[] strArr = new String[personList.size()];
		int index = 0;
		for (Person person : personList) {
			strArr[index] = equalSpace(person.getId(), 5) + getSpace() + equalSpace(person.getName(), 10) + getSpace()
					+ equalSpace(person.getSurname(), 10) + getSpace() + equalSpace(person.getDateOfBirth(), 10)
					+ getSpace() + equalSpace(person.getSocialSecurtyNumber(), 11) + getSpace()
					+ equalSpace(person.getDepartmentName(), 20);
			index++;
		}

		list.setListData(strArr);

		String[] headerArr = new String[1];
		headerArr[0] = equalSpace("Id", 5) + getSpace() + equalSpace("Name", 10) + getSpace()
				+ equalSpace("Surname", 10) + getSpace() + equalSpace("Birth Date", 10) + getSpace()
				+ equalSpace("SSN", 11) + getSpace() + equalSpace("Department Name", 20);
		list_1.setListData(headerArr);
	}

	private void clearForm() {
		tbName.setText("");
		tbSurname.setText("");
		cbDepartmentName.setSelectedIndex(0);
		cbDay.setSelectedIndex(0);
		cbMonth.setSelectedIndex(0);
		cbYear.setSelectedIndex(0);
		tbSsn.setText("");
		tbPersonId.setText("0");
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1243, 563);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		list = new JList();
		list.setBounds(10, 32, 793, 429);
		list.setFont(new Font("monospaced", Font.PLAIN, 13));
		frame.getContentPane().add(list);

		list_1 = new JList();
		list_1.setEnabled(false);
		list_1.setBounds(10, 11, 793, 24);
		list_1.setFont(new Font("monospaced", Font.PLAIN, 13));
		frame.getContentPane().add(list_1);

		fillPersonList();

		final JPanel panel = new JPanel();
		panel.setBounds(825, 11, 392, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 46, 14);
		panel.add(lblName);

		tbName = new JTextField();
		tbName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbName.setBounds(10, 28, 360, 31);
		panel.add(tbName);
		tbName.setColumns(10);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(10, 70, 113, 14);
		panel.add(lblSurname);

		tbSurname = new JTextField();
		tbSurname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbSurname.setColumns(10);
		tbSurname.setBounds(10, 87, 360, 31);
		panel.add(tbSurname);

		cbDepartmentName = new JComboBox();
		cbDepartmentName.setBounds(10, 145, 360, 31);
		panel.add(cbDepartmentName);

		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(10, 127, 212, 14);
		panel.add(lblDepartment);

		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setBounds(10, 186, 212, 14);
		panel.add(lblDateOfBirth);

		JLabel lblDay = new JLabel("Day");
		lblDay.setBounds(10, 207, 29, 14);
		panel.add(lblDay);

		cbDay = new JComboBox();
		cbDay.setBounds(36, 204, 76, 20);
		panel.add(cbDay);

		cbMonth = new JComboBox();
		cbMonth.setBounds(165, 204, 76, 20);
		panel.add(cbMonth);

		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(122, 207, 46, 14);
		panel.add(lblMonth);

		JLabel label = new JLabel("Month");
		label.setBounds(251, 207, 46, 14);
		panel.add(label);

		cbYear = new JComboBox();
		cbYear.setBounds(294, 204, 76, 20);
		panel.add(cbYear);

		tbSsn = new JTextField();
		tbSsn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbSsn.setColumns(10);
		tbSsn.setBounds(10, 253, 360, 31);
		panel.add(tbSsn);

		final JLabel lbl = new JLabel("Social Security Number");
		lbl.setBounds(10, 236, 188, 14);
		panel.add(lbl);

		tbPersonId = new JTextField();
		tbPersonId.setText("0");
		tbPersonId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbPersonId.setColumns(10);
		tbPersonId.setBounds(10, 295, 102, 31);
		tbPersonId.setVisible(false);
		panel.add(tbPersonId);

		JButton btnSavePerson = new JButton("Save Person");
		btnSavePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int personId = Integer.parseInt(tbPersonId.getText());
				String name = tbName.getText();
				String surname = tbSurname.getText();
				String departmentName = cbDepartmentName.getSelectedItem().toString();
				String dateOfBirth = cbYear.getSelectedItem().toString() + "-" + cbMonth.getSelectedItem().toString()
						+ "-" + cbDay.getSelectedItem().toString();
				String socialSecurityNumber = tbSsn.getText();

				if (name.equals("") || surname.equals("") || socialSecurityNumber.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill all the blanks.", "Error!", 0);
				} else if (socialSecurityNumber.length() != 11) {
					JOptionPane.showMessageDialog(null, "Social security number must be 11 characters.", "Error!", 0);
				} else {
					Boolean saveResult = DBO.GetInstance().SavePerson(personId, name, surname, departmentName,
							dateOfBirth, socialSecurityNumber);

					if (saveResult) {
						JOptionPane.showMessageDialog(null, "Person Saved!", "Success!", 0);
						fillPersonList();
						clearForm();
						panel.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
					}
				}
			}
		});
		btnSavePerson.setBounds(223, 295, 147, 48);
		panel.add(btnSavePerson);

		JButton btnCreateNewPerson = new JButton("Create New Person");
		btnCreateNewPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearForm();
				panel.setVisible(true);
			}
		});
		btnCreateNewPerson.setBounds(576, 472, 227, 31);
		frame.getContentPane().add(btnCreateNewPerson);

		JButton btnUpdateSelectedPerson = new JButton("Update Selected Person");
		btnUpdateSelectedPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();

				if (selectedIndex == -1)
					JOptionPane.showMessageDialog(null, "Select a person first!", "Error!", 0);
				else {
					int personId = Integer.parseInt(list.getSelectedValue().toString().split(" ")[0]);
					Person person = DBO.GetInstance().GetPersonById(personId);

					if (person == null) {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
						return;
					}
					clearForm();
					tbPersonId.setText(String.valueOf(person.getId()));
					tbSsn.setText(person.getSocialSecurtyNumber());
					tbName.setText(person.getName());
					tbSurname.setText(person.getSurname());
					cbDepartmentName.setSelectedItem(person.getDepartmentName());
					cbDay.setSelectedItem(Integer.parseInt(person.getDateOfBirth().split("-")[2]));
					cbMonth.setSelectedItem(Integer.parseInt(person.getDateOfBirth().split("-")[1]));
					cbYear.setSelectedItem(Integer.parseInt(person.getDateOfBirth().split("-")[0]));

					panel.setVisible(true);
				}
			}
		});
		btnUpdateSelectedPerson.setBounds(339, 472, 227, 31);
		frame.getContentPane().add(btnUpdateSelectedPerson);

		JButton btnDeleteSelectedPerson = new JButton("Delete Selected Person");
		btnDeleteSelectedPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();

				if (selectedIndex == -1)
					JOptionPane.showMessageDialog(null, "Select a person first!", "Error!", 0);
				else {
					int personId = Integer.parseInt(list.getSelectedValue().toString().split(" ")[0]);
					Person person = DBO.GetInstance().GetPersonById(personId);

					if (person == null) {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
						return;
					}

					Boolean processResult = DBO.GetInstance().DeletePerson(personId);

					if (processResult) {
						JOptionPane.showMessageDialog(null, "Person Deleted!", "Success!", 0);
						fillPersonList();
						clearForm();
						panel.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
					}
				}
			}
		});
		btnDeleteSelectedPerson.setBounds(102, 472, 227, 31);
		frame.getContentPane().add(btnDeleteSelectedPerson);

		for (int i = 1; i <= 31; i++) {
			cbDay.addItem(i);
		}

		for (int i = 1; i <= 12; i++) {
			cbMonth.addItem(i);
		}

		for (int i = 1900; i <= 2050; i++) {
			cbYear.addItem(i);
		}

		ArrayList<Department> departmens = DBO.GetInstance().GetAllDepartments();

		for (Department department : departmens) {
			cbDepartmentName.addItem(department.getName());
		}
	}
}

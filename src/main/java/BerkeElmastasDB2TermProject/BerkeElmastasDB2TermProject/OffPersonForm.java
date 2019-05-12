package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class OffPersonForm {

	private JFrame frame;
	private JTextField tbHowManyDays;
	private JTextField tbOffPersonId;
	private JList list_1;
	private JList list_2;
	private JTextField tbDescription;
	private JComboBox cbPersonName;
	private JComboBox cbDay;
	private JComboBox cbMonth;
	private JComboBox cbYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OffPersonForm window = new OffPersonForm();
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
	public OffPersonForm() {
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

	private void fillPersonOffList() {
		ArrayList<OffPersonRepo> offList = DBO.GetInstance().GetAllOffPersons();

		String[] strArr = new String[offList.size()];
		int index = 0;
		for (OffPersonRepo off : offList) {
			strArr[index] = equalSpace(off.getId(), 3) + getSpace() + equalSpace(off.getPersonName(), 18) + getSpace()
					+ equalSpace(off.getStartDate(), 10) + getSpace() + equalSpace(off.getEndDate(), 10) + getSpace()
					+ equalSpace(off.getHowManyDays(), 13) + getSpace() + equalSpace(off.getDescription(), 16)
					+ getSpace() + equalSpace(off.getUserName(), 12);
			index++;
		}

		list_2.setListData(strArr);
		strArr = new String[1];
		strArr[0] = equalSpace("Id", 3) + getSpace() + equalSpace("Person Name", 18) + getSpace()
				+ equalSpace("Start Date", 10) + getSpace() + equalSpace("End Date", 10) + getSpace()
				+ equalSpace("How Many Days", 13) + getSpace() + equalSpace("Description", 16) + getSpace()
				+ equalSpace("Done By", 12);
		list_1.setListData(strArr);
	}

	private void clearForm() {
		tbOffPersonId.setText("0");
		cbDay.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
		cbMonth.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
		cbYear.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
		tbHowManyDays.setText("");
		tbDescription.setText("");
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

		fillPersonOffList();

		final JPanel panel = new JPanel();
		panel.setBounds(919, 11, 392, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);

		JLabel lblName = new JLabel("How Many Days");
		lblName.setBounds(10, 138, 188, 14);
		panel.add(lblName);

		tbHowManyDays = new JTextField();
		tbHowManyDays.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbHowManyDays.setBounds(10, 155, 360, 31);
		panel.add(tbHowManyDays);
		tbHowManyDays.setColumns(10);

		tbOffPersonId = new JTextField();
		tbOffPersonId.setText("0");
		tbOffPersonId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbOffPersonId.setColumns(10);
		tbOffPersonId.setBounds(10, 256, 102, 31);
		tbOffPersonId.setVisible(false);
		panel.add(tbOffPersonId);

		JButton btnSaveItem = new JButton("Save Item");
		btnSaveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int offPersonId = Integer.parseInt(tbOffPersonId.getText());
				String personName = cbPersonName.getSelectedItem().toString();
				String startDate = cbYear.getSelectedItem().toString() + "-" + cbMonth.getSelectedItem().toString()
						+ "-" + cbDay.getSelectedItem().toString();
				int howManyDays = Integer.parseInt(tbHowManyDays.getText());
				String desc = tbDescription.getText();

				if (tbHowManyDays.getText().equals("") || desc.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill all the blanks.", "Error!", 0);
				} else {
					Boolean saveResult = DBO.GetInstance().SaveOffPerson(offPersonId, personName, startDate,
							howManyDays, desc);

					if (saveResult) {
						JOptionPane.showMessageDialog(null, "Item Saved!", "Success!", 0);
						fillPersonOffList();
						clearForm();
						panel.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
					}
				}
			}
		});
		btnSaveItem.setBounds(223, 256, 147, 48);
		panel.add(btnSaveItem);

		JLabel lblPerson = new JLabel("Person");
		lblPerson.setBounds(10, 11, 212, 14);
		panel.add(lblPerson);

		cbPersonName = new JComboBox();
		cbPersonName.setBounds(10, 29, 360, 31);
		panel.add(cbPersonName);

		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 197, 188, 14);
		panel.add(lblDescription);

		tbDescription = new JTextField();
		tbDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbDescription.setColumns(10);
		tbDescription.setBounds(10, 214, 360, 31);
		panel.add(tbDescription);

		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(10, 78, 212, 14);
		panel.add(lblStartDate);

		JLabel label_1 = new JLabel("Day");
		label_1.setBounds(10, 99, 29, 14);
		panel.add(label_1);

		cbDay = new JComboBox();
		cbDay.setBounds(36, 96, 76, 20);
		panel.add(cbDay);

		JLabel label_2 = new JLabel("Month");
		label_2.setBounds(122, 99, 46, 14);
		panel.add(label_2);

		cbMonth = new JComboBox();
		cbMonth.setBounds(165, 96, 76, 20);
		panel.add(cbMonth);

		JLabel label_3 = new JLabel("Year");
		label_3.setBounds(251, 99, 46, 14);
		panel.add(label_3);

		cbYear = new JComboBox();
		cbYear.setBounds(294, 96, 76, 20);
		panel.add(cbYear);

		JButton btnCreateNewItem = new JButton("Create New Item");
		btnCreateNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearForm();
				panel.setVisible(true);
			}
		});
		btnCreateNewItem.setBounds(682, 472, 227, 31);
		frame.getContentPane().add(btnCreateNewItem);

		JButton btnUpdateSelectedItem = new JButton("Update Selected Item");
		btnUpdateSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list_2.getSelectedIndex();

				if (selectedIndex == -1)
					JOptionPane.showMessageDialog(null, "Select an item first!", "Error!", 0);
				else {
					int personOffId = Integer.parseInt(list_2.getSelectedValue().toString().split(" ")[0]);
					OffPersonRepo offPerson = DBO.GetInstance().GetPersonOffById(personOffId);

					if (offPerson == null) {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
						return;
					}
					clearForm();

					tbOffPersonId.setText(String.valueOf(personOffId));
					cbPersonName.setSelectedItem(offPerson.getPersonName());
					cbDay.setSelectedItem(offPerson.getStartDate().split("-")[2]);
					cbMonth.setSelectedItem(offPerson.getStartDate().split("-")[1]);
					cbYear.setSelectedItem(offPerson.getStartDate().split("-")[0]);
					tbHowManyDays.setText(String.valueOf(offPerson.getHowManyDays()));
					tbDescription.setText(offPerson.getDescription());

					panel.setVisible(true);
				}
			}
		});
		btnUpdateSelectedItem.setBounds(445, 472, 227, 31);
		frame.getContentPane().add(btnUpdateSelectedItem);

		JButton btnDeleteSelectedItem = new JButton("Delete Selected Item");
		btnDeleteSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list_2.getSelectedIndex();

				if (selectedIndex == -1)
					JOptionPane.showMessageDialog(null, "Select an item first!", "Error!", 0);
				else {
					int personOffId = Integer.parseInt(list_2.getSelectedValue().toString().split(" ")[0]);

					Boolean processResult = DBO.GetInstance().DeletePersonOff(personOffId);

					if (processResult) {
						JOptionPane.showMessageDialog(null, "Item Deleted!", "Success!", 0);
						fillPersonOffList();
						clearForm();
						panel.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Oops, an error occured!", "Error!", 0);
					}
				}
			}
		});
		btnDeleteSelectedItem.setBounds(208, 472, 227, 31);
		frame.getContentPane().add(btnDeleteSelectedItem);

		ArrayList<PersonRepo> personList = DBO.GetInstance().GetAllPerson();

		for (PersonRepo person : personList) {
			cbPersonName.addItem(person.getName() + " " + person.getSurname());
		}

		for (int i = 1; i <= 31; i++) {
			cbDay.addItem(String.valueOf(i).length() == 1 ? ("0" + String.valueOf(i)) : String.valueOf(i));
		}

		for (int i = 1; i <= 12; i++) {
			cbMonth.addItem(String.valueOf(i).length() == 1 ? ("0" + String.valueOf(i)) : String.valueOf(i));
		}

		for (int i = 1900; i <= 2050; i++) {
			cbYear.addItem(String.valueOf(i));
		}

		cbDay.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
		cbMonth.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
		cbYear.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));
		
		java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}
}

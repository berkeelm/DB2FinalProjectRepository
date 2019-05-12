package BerkeElmastasDB2TermProject.BerkeElmastasDB2TermProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;

public class OffPersonInfoForm {

	private JFrame frame;
	private JComboBox cbNameSurname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OffPersonInfoForm window = new OffPersonInfoForm();
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
	public OffPersonInfoForm() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 762, 292);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		cbNameSurname = new JComboBox();
		cbNameSurname.setBounds(26, 92, 252, 31);
		frame.getContentPane().add(cbNameSurname);

		JLabel lblSearchPersonsOff = new JLabel("Search persons' off days");
		lblSearchPersonsOff.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSearchPersonsOff.setBounds(26, 29, 226, 31);
		frame.getContentPane().add(lblSearchPersonsOff);

		JLabel cbPersonName = new JLabel("Person Name");
		cbPersonName.setBounds(26, 71, 109, 14);
		frame.getContentPane().add(cbPersonName);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "";
				ArrayList<OffPersonRepo> list = DBO.GetInstance().GetAllOffPersons();

				for (OffPersonRepo offPersonRepo : list) {
					if (offPersonRepo.getPersonName().equals(cbNameSurname.getSelectedItem().toString())) {
						msg += offPersonRepo.getStartDate() + " - " + offPersonRepo.getEndDate() + " ("
								+ offPersonRepo.getHowManyDays() + " Days)\n\n";
					}
				}

				JOptionPane.showMessageDialog(null, msg.equals("") ? "No records found." : msg);
			}
		});
		btnNewButton.setBounds(181, 134, 97, 31);
		frame.getContentPane().add(btnNewButton);

		JLabel lblOffPersonsBetween = new JLabel("Off persons between two dates");
		lblOffPersonsBetween.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblOffPersonsBetween.setBounds(357, 29, 349, 31);
		frame.getContentPane().add(lblOffPersonsBetween);

		JLabel label = new JLabel("Start Date");
		label.setBounds(357, 71, 212, 14);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("Day");
		label_1.setBounds(357, 92, 29, 14);
		frame.getContentPane().add(label_1);

		final JComboBox cbDay1 = new JComboBox();
		cbDay1.setBounds(383, 89, 76, 20);
		frame.getContentPane().add(cbDay1);

		JLabel label_2 = new JLabel("Month");
		label_2.setBounds(469, 92, 46, 14);
		frame.getContentPane().add(label_2);

		final JComboBox cbMonth1 = new JComboBox();
		cbMonth1.setBounds(512, 89, 76, 20);
		frame.getContentPane().add(cbMonth1);

		JLabel label_3 = new JLabel("Year");
		label_3.setBounds(598, 92, 46, 14);
		frame.getContentPane().add(label_3);

		final JComboBox cbYear1 = new JComboBox();
		cbYear1.setBounds(641, 89, 76, 20);
		frame.getContentPane().add(cbYear1);

		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(357, 127, 212, 14);
		frame.getContentPane().add(lblEndDate);

		JLabel label_5 = new JLabel("Day");
		label_5.setBounds(357, 148, 29, 14);
		frame.getContentPane().add(label_5);

		final JComboBox cbDay2 = new JComboBox();
		cbDay2.setBounds(383, 145, 76, 20);
		frame.getContentPane().add(cbDay2);

		JLabel label_6 = new JLabel("Month");
		label_6.setBounds(469, 148, 46, 14);
		frame.getContentPane().add(label_6);

		final JComboBox cbMonth2 = new JComboBox();
		cbMonth2.setBounds(512, 145, 76, 20);
		frame.getContentPane().add(cbMonth2);

		JLabel label_7 = new JLabel("Year");
		label_7.setBounds(598, 148, 46, 14);
		frame.getContentPane().add(label_7);

		final JComboBox cbYear2 = new JComboBox();
		cbYear2.setBounds(641, 145, 76, 20);
		frame.getContentPane().add(cbYear2);

		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String startDate = cbYear1.getSelectedItem().toString() + "-" + cbMonth1.getSelectedItem().toString()
						+ "-" + cbDay1.getSelectedItem().toString();

				String endDate = cbYear2.getSelectedItem().toString() + "-" + cbMonth2.getSelectedItem().toString()
						+ "-" + cbDay2.getSelectedItem().toString();

				String msg = "";
				ArrayList<OffPersonRepo> list = DBO.GetInstance().GetAllPersonOffsBetweenTwoDate(startDate, endDate);

				for (OffPersonRepo offPersonRepo : list) {
					msg += offPersonRepo.getPersonName() + ": " + offPersonRepo.getStartDate() + " - "
							+ offPersonRepo.getEndDate() + " (" + offPersonRepo.getHowManyDays() + " Days)\n\n";
				}

				JOptionPane.showMessageDialog(null, msg.equals("") ? "No records found." : msg);
			}
		});
		button.setBounds(618, 188, 97, 31);
		frame.getContentPane().add(button);

		ArrayList<PersonRepo> persons = DBO.GetInstance().GetAllPerson();

		for (PersonRepo personRepo : persons) {
			cbNameSurname.addItem(personRepo.getName() + " " + personRepo.getSurname());
		}

		for (int i = 1; i <= 31; i++) {
			cbDay1.addItem(String.valueOf(i).length() == 1 ? ("0" + String.valueOf(i)) : String.valueOf(i));
			cbDay2.addItem(String.valueOf(i).length() == 1 ? ("0" + String.valueOf(i)) : String.valueOf(i));
		}

		for (int i = 1; i <= 12; i++) {
			cbMonth1.addItem(String.valueOf(i).length() == 1 ? ("0" + String.valueOf(i)) : String.valueOf(i));
			cbMonth2.addItem(String.valueOf(i).length() == 1 ? ("0" + String.valueOf(i)) : String.valueOf(i));
		}

		for (int i = 1900; i <= 2050; i++) {
			cbYear1.addItem(String.valueOf(i));
			cbYear2.addItem(String.valueOf(i));
		}

		cbDay1.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
		cbMonth1.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
		cbYear1.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));

		cbDay2.setSelectedItem(new SimpleDateFormat("dd").format(new Date()));
		cbMonth2.setSelectedItem(new SimpleDateFormat("MM").format(new Date()));
		cbYear2.setSelectedItem(new SimpleDateFormat("yyyy").format(new Date()));

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(311, 29, 2, 190);
		frame.getContentPane().add(panel);

		java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}
}

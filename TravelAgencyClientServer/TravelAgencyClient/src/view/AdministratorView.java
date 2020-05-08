package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdministratorView extends JDialog {

	private JButton addEmployee;
	private JButton readEmployees;
	private JButton updateEmployee;
	private JButton deleteEmployee;
	private JButton saveButton;
	private JButton deleteButton;
	private JButton logOutButton;
	private JTextField nameText;
	private JTextField ageText;
	private JTextField userNameText;
	private JTextField passwordText;
	private JTextField phoneText;
	private JComboBox<String> employees;
	private JList<String> employeesList;
	private JPanel adminContentPanel;
	private int operationID;

	public AdministratorView(String name) {

		this.operationID = 0;
		addEmployee = new JButton("Add new employee");
		readEmployees = new JButton("View the list of employees");
		updateEmployee = new JButton("Update employee");
		deleteEmployee = new JButton("Delete employee");
		saveButton = new JButton("Save");
		deleteButton = new JButton("Delete");
		logOutButton = new JButton("Log out");

		nameText = new JTextField(15);
		ageText = new JTextField(15);
		userNameText = new JTextField(15);
		passwordText = new JTextField(15);
		phoneText = new JTextField(15);
		adminContentPanel = new JPanel();

		employees = new JComboBox<String>();
		employeesList = new JList<String>();

		addAdminPanel(name);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(600, 600));
		this.setTitle("Administrator");
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void addAdminPanel(String name) {

		JLabel nameLabel = new JLabel("Welcome " + name + " !");
		nameLabel.setFont(new Font("Arial", Font.ITALIC, 16));

		JPanel p1 = new JPanel();
		JPanel p11 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel adminPanel = new JPanel();
		p1.add(addEmployee);
		p1.add(readEmployees);
		p11.add(updateEmployee);
		p11.add(deleteEmployee);
		p2.add(nameLabel);
		p2.add(logOutButton);
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.add(p2);
		p3.add(p1);
		p3.add(p11);
		adminPanel.removeAll();
		adminPanel.setLayout(new BorderLayout());
		adminPanel.add(p3, BorderLayout.PAGE_START);
		adminPanel.add(adminContentPanel, BorderLayout.CENTER);
		this.setContentPane(adminPanel);
		this.revalidate();
	}

	public void addAddEmployeePanel() {

		this.operationID = 1;
		JLabel nameLabel = new JLabel("Name: ");
		JLabel ageLabel = new JLabel("Age: ");
		JLabel userNameLabel = new JLabel("Username: ");
		JLabel passwordLabel = new JLabel("Password: ");
		JLabel phoneLabel = new JLabel("Phone number: ");

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel addPanel = new JPanel();
		p1.add(nameLabel);
		p1.add(nameText);
		p2.add(ageLabel);
		p2.add(ageText);
		p3.add(userNameLabel);
		p3.add(userNameText);
		p4.add(passwordLabel);
		p4.add(passwordText);
		p7.add(phoneLabel);
		p7.add(phoneText);
		p8.add(saveButton);
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.PAGE_AXIS));
		addPanel.add(Box.createVerticalStrut(20));
		addPanel.add(p1);
		addPanel.add(p2);
		addPanel.add(p3);
		addPanel.add(p4);
		addPanel.add(p7);
		addPanel.add(p8);
		setAdministratorPanelContentPane(addPanel);
	}

	public void addUpdateEmployeePanel(List<String> values) {

		this.operationID = 2;
		JLabel select = new JLabel("Select an employee!");
		JLabel nameLabel = new JLabel("Name: ");
		JLabel ageLabel = new JLabel("Age: ");
		JLabel userNameLabel = new JLabel("Username: ");
		JLabel passwordLabel = new JLabel("Password: ");
		JLabel phoneLabel = new JLabel("Phone number: ");
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(values);
		employees.setModel(model);

		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel updatePanel = new JPanel();
		p0.add(select);
		p0.add(employees);
		p1.add(nameLabel);
		p1.add(nameText);
		p2.add(ageLabel);
		p2.add(ageText);
		p3.add(userNameLabel);
		p3.add(userNameText);
		p4.add(passwordLabel);
		p4.add(passwordText);
		p7.add(phoneLabel);
		p7.add(phoneText);
		p8.add(saveButton);
		updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.PAGE_AXIS));
		updatePanel.add(p0);
		updatePanel.add(p1);
		updatePanel.add(p2);
		updatePanel.add(p3);
		updatePanel.add(p4);
		updatePanel.add(p7);
		updatePanel.add(p8);
		setAdministratorPanelContentPane(updatePanel);
	}

	public void addReadEmployeePanel(String text) {
		JTextArea employeeTextArea = new JTextArea(17, 30);
		employeeTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(employeeTextArea);
		JLabel list = new JLabel("List of the employees:");

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel readPanel = new JPanel();
		p1.add(list);
		p2.add(scroll);
		readPanel.setLayout(new BoxLayout(readPanel, BoxLayout.PAGE_AXIS));
		readPanel.add(Box.createVerticalStrut(20));
		readPanel.add(p1);
		readPanel.add(p2);
		employeeTextArea.setText(text);
		setAdministratorPanelContentPane(readPanel);
	}

	public void addDeleteEmployeePanel(List<String> names) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addAll(names);
		employeesList.setModel(model);
		JScrollPane scroll = new JScrollPane(employeesList);
		JLabel list = new JLabel("List of the employees:");

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel deletePanel = new JPanel();
		p1.add(list);
		p2.add(scroll);
		p3.add(deleteButton);
		deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.PAGE_AXIS));
		deletePanel.add(Box.createVerticalStrut(20));
		deletePanel.add(p1);
		deletePanel.add(p2);
		deletePanel.add(p3);
		setAdministratorPanelContentPane(deletePanel);
	}

	private void setAdministratorPanelContentPane(JPanel p) {
		adminContentPanel.removeAll();
		adminContentPanel.add(p);
		adminContentPanel.revalidate();
		adminContentPanel.repaint();
	}

	public void addAddEmployeeButtonListener(ActionListener listener) {
		addEmployee.addActionListener(listener);
	}

	public void addReadEmployeeButtonListener(ActionListener listener) {
		readEmployees.addActionListener(listener);
	}

	public void addUpdateEmployeeButtonListener(ActionListener listener) {
		updateEmployee.addActionListener(listener);
	}

	public void addDeleteEmployeeButtonListener(ActionListener listener) {
		deleteEmployee.addActionListener(listener);
	}

	public void addEmployeeBoxListener(ActionListener listener) {
		employees.addActionListener(listener);
	}

	public void addSaveButtonListener(ActionListener listener) {
		saveButton.addActionListener(listener);
	}

	public void addDeleteButtonListener(ActionListener listener) {
		deleteButton.addActionListener(listener);
	}

	public void addLogOutButtonListener(ActionListener listener) {
		logOutButton.addActionListener(listener);
	}

	public void showLogInErrorMessage() {
		JOptionPane.showMessageDialog(null,
				"Error!\nInvalid username or password!\nTry again with correct credentials!", "Wrong credentials",
				JOptionPane.ERROR_MESSAGE);
	}

	public String getNameText() {
		return nameText.getText();
	}

	public int getAgeText() {
		int val = 0;
		try {
			val = Integer.parseInt(ageText.getText());
			return val;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!\nInvalid input for age!\nPlease enter a correct value!",
					"Wrong age input", JOptionPane.ERROR_MESSAGE);
		}
		return val;
	}

	public String getUsernameText() {
		return userNameText.getText();
	}

	public String getPasswordText() {
		return passwordText.getText();
	}

	public String getPhoneText() {
		return phoneText.getText();
	}

	public void setNameText(String text) {
		nameText.setText(text);
	}

	public void setAgeText(String text) {
		ageText.setText(text);
	}

	public void setUsernameText(String text) {
		userNameText.setText(text);
	}

	public void setPasswordText(String text) {
		passwordText.setText(text);
	}

	public void setPhoneText(String text) {
		phoneText.setText(text);
	}

	public String getComboBoxValue() {
		return String.valueOf(employees.getSelectedItem());
	}

	public void clearTextBoxes() {
		nameText.setText("");
		ageText.setText("");
		userNameText.setText("");
		passwordText.setText("");
		phoneText.setText("");
	}

	public boolean isValidInput() {
		return (!(nameText.getText().equals("") || ageText.getText().equals("") || userNameText.getText().equals("")
				|| passwordText.getText().equals("") || phoneText.getText().equals("")));
	}

	public void showInvalidInputMessage() {
		JOptionPane.showMessageDialog(null, "Warning!\nPlease fill each textbox!", "Wrong input",
				JOptionPane.WARNING_MESSAGE);
	}

	public void showAddedSuccesfullyMessage() {
		JOptionPane.showMessageDialog(null, "Employee added successfully!", "Employee added",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void showUpdatedSuccesfullyMessage() {
		JOptionPane.showMessageDialog(null, "Employee updated successfully!", "Employee updated",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void showInsertErrorMessage() {
		JOptionPane.showMessageDialog(null, "Error! Employee already exists! ", "Duplicate employee",
				JOptionPane.ERROR_MESSAGE);
	}

	public void showUpdateErrorMessage() {
		JOptionPane.showMessageDialog(null, "Error! Each username need to be unique! ", "Wrong username!",
				JOptionPane.ERROR_MESSAGE);
	}

	public int getID() {
		return this.operationID;
	}

	public String getSelectedEmployeeName() {
		return (String) employeesList.getSelectedValue();
	}

	public void updateDeleteEmployeeList(List<String> names) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addAll(names);
		employeesList.setModel(model);
	}

	public void updateUpdateEmployeeList(List<String> names) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(names);
		employees.setModel(model);
	}

	public void closeWindow() {
		this.dispose();
	}

}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import model.ContUtilizator;
import model.Utilizator;
import view.AdministratorView;

public class AdministratorController {

	private AdministratorView administratorView;
	private List<Utilizator> utilizatori;

	public AdministratorController(List<Utilizator> utilizatori, String name) {
		administratorView = new AdministratorView(name);
		this.utilizatori = utilizatori;
		addActionListeners();
	}

	private void addActionListeners() {
		administratorView.addAddEmployeeButtonListener(new AddEmployeeButtonListener());
		administratorView.addReadEmployeeButtonListener(new ReadEmployeeButtonListener());
		administratorView.addUpdateEmployeeButtonListener(new UpdateEmployeeButtonListener());
		administratorView.addDeleteEmployeeButtonListener(new DeleteEmployeeButtonListener());
		administratorView.addSaveButtonListener(new SaveButtonListener());
		administratorView.addDeleteButtonListener(new DeleteButtonListener());
		administratorView.addLogOutButtonListener(new LogoutButtonListener());
	}

	private List<String> getEmployeesName() {

		return utilizatori.stream().filter(u -> u.getTip().equals("Employee")).map(u -> u.getNume())
				.collect(Collectors.toList());
	}

	private Utilizator getUserByName(String name) {

		return utilizatori.stream().filter(u -> u.getNume().equals(name)).collect(Collectors.toList()).get(0);
	}

	private String getEmployees() {
		String employees = "";
		for (Utilizator utilizator : utilizatori) {
			if (utilizator.getTip().equals("Employee")) {
				employees += utilizator + "\n\n";
			}
		}
		return employees;
	}

	class AddEmployeeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.addAddEmployeePanel();
			administratorView.clearTextBoxes();
		}

	}

	class ReadEmployeeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.addReadEmployeePanel(getEmployees());
			administratorView.clearTextBoxes();
		}

	}

	class UpdateEmployeeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.addUpdateEmployeePanel(getEmployeesName());
			administratorView.addEmployeeBoxListener(new EmployeeBoxListener());
			administratorView.clearTextBoxes();
		}

	}

	class DeleteEmployeeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.addDeleteEmployeePanel(getEmployeesName());
			administratorView.clearTextBoxes();
		}

	}

	class EmployeeBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Utilizator utilizator = getUserByName(administratorView.getComboBoxValue());
			administratorView.setNameText(utilizator.getNume());
			administratorView.setAgeText(String.valueOf(utilizator.getVarsta()));
			administratorView.setUsernameText(utilizator.getContUtilizator().getUserName());
			administratorView.setPasswordText(utilizator.getContUtilizator().getParola());
			administratorView.setPhoneText(utilizator.getNrTel());
		}

	}

	class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int age = administratorView.getAgeText();
			if (administratorView.isValidInput() && age != 0) {
				String name = administratorView.getNameText();
				String user = administratorView.getUsernameText();
				String password = administratorView.getPasswordText();
				String phone = administratorView.getPhoneText();
				if (administratorView.getID() == 1) {
					Utilizator utilizator = new Utilizator(name, age, "New Address", "email@email2.com", phone,
							"Employee", new ContUtilizator(user, password));
					utilizatori.add(utilizator);
					administratorView.showAddedSuccesfullyMessage();
				} else {
					if (administratorView.getID() == 2) {
						Utilizator utilizator = getUserByName(administratorView.getComboBoxValue());
						utilizator.setNume(name);
						utilizator.Setvarsta(age);
						utilizator.setNrTel(phone);
						utilizator.getContUtilizator().setUserName(user);
						utilizator.getContUtilizator().setParola(password);
						administratorView.showUpdatedSuccesfullyMessage();
					}
				}
			} else {
				if (age != 0) {
					administratorView.showInvalidInputMessage();
				}
			}
		}

	}

	class DeleteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Utilizator user = getUserByName(administratorView.getSelectedEmployeeName());
			utilizatori.remove(user);
			administratorView.updateDeleteEmployeeList(getEmployeesName());
		}

	}

	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.closeWindow();
		}

	}
}

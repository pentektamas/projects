package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import model.Avion;
import model.ContUtilizator;
import model.Utilizator;
import view.AdministratorView;

public class AdministratorController implements Autentificare {

	private AdministratorView administratorView;
	private List<Avion> avioane;
	private List<Utilizator> utilizatori;

	public AdministratorController(List<Avion> avioane, List<Utilizator> utilizatori) {
		administratorView = new AdministratorView();
		this.avioane = avioane;
		this.utilizatori = utilizatori;
		addActionListeners();
	}

	private void addActionListeners() {
		administratorView.addLogInButtonListener(new LogInButtonListener());
		administratorView.addContinueButtonListener(new ContinueButtonListener());
		administratorView.addAddEmployeeButtonListener(new AddEmployeeButtonListener());
		administratorView.addReadEmployeeButtonListener(new ReadEmployeeButtonListener());
		administratorView.addUpdateEmployeeButtonListener(new UpdateEmployeeButtonListener());
		administratorView.addDeleteEmployeeButtonListener(new DeleteEmployeeButtonListener());
		administratorView.addSaveButtonListener(new SaveButtonListener());
		administratorView.addBackButtonListener(new BackButtonListener());
		administratorView.addDeleteButtonListener(new DeleteButtonListener());
		administratorView.addLogOutButtonListener(new LogoutButtonListener());
	}

	private Utilizator getUserWithCredentials(String userName, String password) {
		Utilizator utilizator = null;
		try {
			utilizator = utilizatori.stream().filter(u -> u.verifyCont(userName, password, "Administrator"))
					.collect(Collectors.toList()).get(0);
			return utilizator;
		} catch (Exception e) {
			return null;
		}
	}

	private String getUsersName(String userName, String password) {

		Utilizator utilizator = utilizatori.stream().filter(u -> u.verifyCont(userName, password, "Administrator"))
				.collect(Collectors.toList()).get(0);

		return ((utilizator != null) ? utilizator.getNume() : "");
	}

	public boolean logIn() {
		String userName = administratorView.getUserName();
		String password = administratorView.getPassword();
		Utilizator utilizator = getUserWithCredentials(userName, password);
		if (utilizator != null)
			return true;
		else
			return false;
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

	class LogInButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (logIn()) {
				administratorView
						.addAdminPanel(getUsersName(administratorView.getUserName(), administratorView.getPassword()));
			} else
				administratorView.showLogInErrorMessage();
			administratorView.clearCredentials();
		}

	}

	class AddEmployeeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.addAddEmployeePanel();
		}

	}

	class ReadEmployeeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.addReadEmployeePanel(getEmployees());
		}

	}

	class UpdateEmployeeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.addUpdateEmployeePanel(getEmployeesName());
			administratorView.addEmployeeBoxListener(new EmployeeBoxListener());
		}

	}

	class DeleteEmployeeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.addDeleteEmployeePanel(getEmployeesName());
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

	class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.backAction();
			administratorView.clearTextBoxes();
		}

	}

	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorView.setLogoutPanel();
		}

	}

	class ContinueButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new CalatorController(avioane);
		}

	}
}

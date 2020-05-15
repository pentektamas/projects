package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Locale;

import contract.IContUtilizatorDAC;
import contract.IPersoanaDAC;
import contract.IUtilizatorDAC;
import entity.ContUtilizator;
import entity.Utilizator;
import model.RBundle;
import view.AdministratorView;

public class AdministratorController {

	private AdministratorView administratorView;
	private IContUtilizatorDAC stubContUtilizator;
	private IPersoanaDAC stubPersoana;
	private IUtilizatorDAC stubUtilizator;
	private RBundle resourceBundle;

	public AdministratorController(IUtilizatorDAC stubUtilizator, IContUtilizatorDAC stubContUtilizator,
			IPersoanaDAC stubPersoana, String name, RBundle bundle) {
		this.resourceBundle = bundle;
		administratorView = new AdministratorView(name, resourceBundle);
		resourceBundle.attach(administratorView);
		this.stubUtilizator = stubUtilizator;
		this.stubPersoana = stubPersoana;
		this.stubContUtilizator = stubContUtilizator;
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
		administratorView.addLanguageListener(new LanguageListener());
	}

	private List<String> getEmployeesName() {
		List<String> names = null;
		try {
			names = stubUtilizator.getEmployeesNames();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return names;
	}

	private Utilizator getUserByName(String name) {
		Utilizator utilizator = null;
		try {
			utilizator = stubUtilizator.getUserByName(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return utilizator;
	}

	private String getEmployees() {
		String employees = "";
		try {
			employees = stubUtilizator.getEmployees();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return employees;
	}

	private boolean addNewUser(Utilizator utilizator) {
		boolean rez = true;

		try {
			boolean duplicatePerson = stubPersoana.isDuplicatePersonInput(utilizator);
			boolean duplicateAccount = stubContUtilizator
					.isDuplicateAccountInput(utilizator.getContUtilizator().getUserName());
			boolean duplicateUser = stubUtilizator.isDuplicateUserInput(stubPersoana.getlastPersID() + 1,
					stubContUtilizator.getLastContID() + 1);

			if (!duplicatePerson && !duplicateAccount && !duplicateUser) {
				stubPersoana.addNewPerson(utilizator);
				stubContUtilizator.addNewAccount(utilizator.getContUtilizator().getUserName(),
						utilizator.getContUtilizator().getParola());
				stubUtilizator.addNewUser(stubPersoana.getlastPersID(), stubContUtilizator.getLastContID());
			} else
				rez = false;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return rez;
	}

	private boolean updateUser(int persID, String name, int age, String phone, int contID, String user, String parola) {
		boolean rez = true;
		try {
			if (!stubContUtilizator.isDuplicateUsername(contID, user)) {
				stubPersoana.updatePerson(persID, name, age, phone);
				stubContUtilizator.updateAccount(contID, user, parola);
			} else
				rez = false;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return rez;
	}

	private void deleteUser(Utilizator user) {
		try {
			stubUtilizator.deleteUser(user.getPersID());
			stubPersoana.deletePerson(user.getPersID());
			stubContUtilizator.deleteAccount(user.getContUtilizator().getContID());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
					Utilizator utilizator = new Utilizator(0, name, age, "New Address", "email@email2.com", phone,
							"Employee", new ContUtilizator(0, user, password));
					boolean isAdded = addNewUser(utilizator);
					if (isAdded) {
						administratorView.showAddedSuccesfullyMessage();
						administratorView.clearTextBoxes();
					} else
						administratorView.showInsertErrorMessage();
				} else {
					if (administratorView.getID() == 2) {
						Utilizator utilizator = getUserByName(administratorView.getComboBoxValue());
						boolean isUpdated = updateUser(utilizator.getPersID(), name, age, phone,
								utilizator.getContUtilizator().getContID(), user, password);
						if (isUpdated) {
							administratorView.clearTextBoxes();
							administratorView.updateUpdateEmployeeList(getEmployeesName());
							administratorView.showUpdatedSuccesfullyMessage();
						} else
							administratorView.showUpdateErrorMessage();
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
			deleteUser(user);
			administratorView.updateDeleteEmployeeList(getEmployeesName());
		}
	}

	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			resourceBundle.detach(administratorView);
			administratorView.closeWindow();
		}
	}

	class LanguageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (administratorView.isEnglishSelected())
				resourceBundle.changeLanguage(new Locale("en", "US"));
			if (administratorView.isRomanianSelected())
				resourceBundle.changeLanguage(new Locale("ro", "RO"));
			if (administratorView.isSpanishSelected())
				resourceBundle.changeLanguage(new Locale("es", "ES"));
		}

	}

}

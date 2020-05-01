package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import model.Avion;
import model.Bilet;
import model.Calator;
import model.Utilizator;
import view.LogInPanel;

public class LogInController {

	private LogInPanel panel;
	private List<Utilizator> utilizatori;
	private List<Avion> avioane;
	private List<Bilet> bilete;
	private List<Calator> calatori;
	private int type;

	public LogInController(List<Utilizator> utilizatori, List<Avion> avioane, List<Bilet> bilete,
			List<Calator> calatori, int type) {

		panel = new LogInPanel();
		this.utilizatori = utilizatori;
		this.avioane = avioane;
		this.bilete = bilete;
		this.calatori = calatori;
		this.type = type;
		addActionListeners();
	}

	private void addActionListeners() {
		panel.addLogInButtonListener(new LogInButtonListener());
		panel.addContinueButtonListener(new ContinueButtonListener());
	}

	private Utilizator getUserWithCredentials(String userName, String password, String userType) {
		Utilizator utilizator = null;
		try {
			utilizator = utilizatori.stream().filter(u -> u.verifyCont(userName, password, userType))
					.collect(Collectors.toList()).get(0);
			return utilizator;
		} catch (Exception e) {
			return null;
		}
	}

	private String getUsersName(String userName, String password, String userType) {

		Utilizator utilizator = utilizatori.stream().filter(u -> u.verifyCont(userName, password, userType))
				.collect(Collectors.toList()).get(0);

		return ((utilizator != null) ? utilizator.getNume() : " ");
	}

	private boolean logIn() {
		String userName = panel.getUserNameText();
		String password = panel.getPasswordText();
		String userType = "";
		if (this.type == 1) {
			userType = "Employee";
		} else {
			if (this.type == 2) {
				userType = "Administrator";
			}
		}
		Utilizator utilizator = getUserWithCredentials(userName, password, userType);
		if (utilizator != null)
			return true;
		else
			return false;
	}

	class LogInButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (logIn()) {
				panel.closeWindow();
				if (type == 1) {
					new AngajatController(avioane, bilete, calatori,
							getUsersName(panel.getUserNameText(), panel.getPasswordText(), "Employee"));
				} else {
					if (type == 2) {
						new AdministratorController(utilizatori,
								getUsersName(panel.getUserNameText(), panel.getPasswordText(), "Administrator"));
					}
				}
			} else
				panel.showLogInErrorMessage();
			panel.clearTextFields();
		}

	}

	class ContinueButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new CalatorController(avioane);
			panel.closeWindow();
		}

	}
}
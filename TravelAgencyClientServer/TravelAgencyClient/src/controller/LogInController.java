package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import contract.IAvionDAC;
import contract.IBiletDAC;
import contract.ICalatorDAC;
import contract.IContUtilizatorDAC;
import contract.IPersoanaDAC;
import contract.IUtilizatorDAC;
import view.LogInPanel;

public class LogInController {

	private LogInPanel panel;
	private int type;
	private IAvionDAC stubAvion;
	private IBiletDAC stubBilet;
	private ICalatorDAC stubCalator;
	private IContUtilizatorDAC stubContUtilizator;
	private IPersoanaDAC stubPersoana;
	private IUtilizatorDAC stubUtilizator;

	public LogInController(int type, IAvionDAC stubAvion, IBiletDAC stubBilet, ICalatorDAC stubCalator,
			IContUtilizatorDAC stubContUtilizator, IPersoanaDAC stubPersoana, IUtilizatorDAC stubUtilizator) {

		this.stubAvion = stubAvion;
		this.stubBilet = stubBilet;
		this.stubCalator = stubCalator;
		this.stubContUtilizator = stubContUtilizator;
		this.stubPersoana = stubPersoana;
		this.stubUtilizator = stubUtilizator;
		panel = new LogInPanel();
		this.type = type;
		addActionListeners();
	}

	private void addActionListeners() {
		panel.addLogInButtonListener(new LogInButtonListener());
		panel.addContinueButtonListener(new ContinueButtonListener());
	}

	private String getUserWithCredentials(String userName, String password, String userType) {
		String utilizator = "";
		try {
			utilizator = stubUtilizator.getUserWithCredentials(userName, password, userType);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return utilizator;
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
		String utilizator = getUserWithCredentials(userName, password, userType);
		if (utilizator.equals(""))
			return false;
		else
			return true;
	}

	class LogInButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (logIn()) {
				panel.closeWindow();
				if (type == 1) {
					new AngajatController(stubAvion, stubBilet, stubCalator,
							getUserWithCredentials(panel.getUserNameText(), panel.getPasswordText(), "Employee"));
				} else {
					if (type == 2) {
						new AdministratorController(stubUtilizator, stubContUtilizator, stubPersoana,
								getUserWithCredentials(panel.getUserNameText(), panel.getPasswordText(),
										"Administrator"));
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
			new CalatorController(stubAvion);
			panel.closeWindow();
		}

	}
}
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.Locale;

import contract.IAvionDAC;
import contract.IBiletDAC;
import contract.ICalatorDAC;
import contract.IContUtilizatorDAC;
import contract.IPersoanaDAC;
import contract.IUtilizatorDAC;
import model.RBundle;
import view.GeneralView;

public class GeneralController {

	private GeneralView generalView;
	private IAvionDAC stubAvion;
	private IBiletDAC stubBilet;
	private ICalatorDAC stubCalator;
	private IContUtilizatorDAC stubContUtilizator;
	private IPersoanaDAC stubPersoana;
	private IUtilizatorDAC stubUtilizator;
	private Socket socket;
	private RBundle resourceBundle;

	public GeneralController(Socket socket, IAvionDAC stub1, IBiletDAC stub2, ICalatorDAC stub3,
			IContUtilizatorDAC stub4, IPersoanaDAC stub5, IUtilizatorDAC stub6, RBundle bundle) {

		this.socket = socket;
		this.stubAvion = stub1;
		this.stubBilet = stub2;
		this.stubCalator = stub3;
		this.stubContUtilizator = stub4;
		this.stubPersoana = stub5;
		this.stubUtilizator = stub6;
		this.resourceBundle = bundle;
		generalView = new GeneralView(resourceBundle);
		resourceBundle.attach(generalView);
		addActionListeners();
	}

	private void addActionListeners() {
		generalView.addCalatorButtonListener(new CalatorButtonListener());
		generalView.addAngajatButtonListener(new AngajatButtonListener());
		generalView.addAdministratorButtonListener(new AdministratorButtonListener());
		generalView.addWindowExitListener(new WindowExitListener());
		generalView.addLanguageListener(new LanguageListener());
	}

	class CalatorButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new CalatorController(stubAvion, resourceBundle);
		}
	}

	class AngajatButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new LogInController(1, stubAvion, stubBilet, stubCalator, stubContUtilizator, stubPersoana, stubUtilizator,
					resourceBundle);
		}
	}

	class AdministratorButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new LogInController(2, stubAvion, stubBilet, stubCalator, stubContUtilizator, stubPersoana, stubUtilizator,
					resourceBundle);
		}
	}

	class LanguageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (generalView.isEnglishSelected())
				resourceBundle.changeLanguage(new Locale("en", "US"));
			if (generalView.isRomanianSelected())
				resourceBundle.changeLanguage(new Locale("ro", "RO"));
			if (generalView.isSpanishSelected())
				resourceBundle.changeLanguage(new Locale("es", "ES"));
		}

	}

	class WindowExitListener extends WindowAdapter {

		public void windowClosing(WindowEvent e) {
			try {
				resourceBundle.detach(generalView);
				socket.close();
			} catch (IOException e1) {
				System.out.println("Error while closing the client socket!");
				e1.printStackTrace();
			}
		}
	}
}

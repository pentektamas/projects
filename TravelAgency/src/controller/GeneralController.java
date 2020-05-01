package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import view.GeneralView;
import model.Avion;
import model.Bilet;
import model.Calator;
import model.ContUtilizator;
import model.FabricaPersistenta;
import model.PersistentaAvion;
import model.PersistentaBilet;
import model.PersistentaCalator;
import model.PersistentaContUtilizator;
import model.PersistentaUtilizator;
import model.Utilizator;

public class GeneralController {

	private GeneralView generalView;
	private List<Avion> avioane;
	private List<Bilet> bilete;
	private List<Calator> calatori;
	private List<ContUtilizator> conturi;
	private List<Utilizator> utilizatori;
	private PersistentaAvion persAvion;
	private PersistentaBilet persBilet;
	private PersistentaCalator persCalator;
	private PersistentaContUtilizator persContUtilizator;
	private PersistentaUtilizator persUtilizator;

	public GeneralController() {
		FabricaPersistenta fabrica = new FabricaPersistenta();
		persAvion = (PersistentaAvion) fabrica.obtinePersistenta("Avion");
		persBilet = (PersistentaBilet) fabrica.obtinePersistenta("Bilet");
		persCalator = (PersistentaCalator) fabrica.obtinePersistenta("Calator");
		persContUtilizator = (PersistentaContUtilizator) fabrica.obtinePersistenta("ContUtilizator");
		persUtilizator = (PersistentaUtilizator) fabrica.obtinePersistenta("Utilizator");
		loadInformation();

		generalView = new GeneralView();
		addActionListeners();
	}

	private void addActionListeners() {
		generalView.addCalatorButtonListener(new CalatorButtonListener());
		generalView.addAngajatButtonListener(new AngajatButtonListener());
		generalView.addAdministratorButtonListener(new AdministratorButtonListener());
		generalView.addWindowExitListener(new WindowExitListener());
	}

	private void loadInformation() {
		avioane = persAvion.incarcare();
		bilete = persBilet.incarcare();
		calatori = persCalator.incarcare();
		conturi = persContUtilizator.incarcare();
		utilizatori = persUtilizator.incarcare();
	}

	private void saveInformation() {
		persAvion.salvare(avioane);
		persBilet.salvare(bilete);
		persCalator.salvare(calatori);
		persContUtilizator.salvare(conturi);
		persUtilizator.salvare(utilizatori);
	}

	class CalatorButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new CalatorController(avioane);

		}

	}

	class AngajatButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new LogInController(utilizatori, avioane, bilete, calatori, 1);
		}

	}

	class AdministratorButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new LogInController(utilizatori, avioane, bilete, calatori, 2);
		}

	}

	class WindowExitListener extends WindowAdapter {

		public void windowClosing(WindowEvent e) {
			saveInformation();
		}
	}
}

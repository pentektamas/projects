package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import model.Avion;
import model.Bilet;
import model.Calator;
import model.ConstructorStatistica;
import model.FabricaRaport;
import model.Statistica;
import model.Utilizator;
import view.AngajatView;

public class AngajatController implements Autentificare {

	private AngajatView angajatView;
	private List<Avion> avioane;
	private List<Utilizator> utilizatori;
	private List<Bilet> bilete;
	private List<Calator> calatori;

	public AngajatController(List<Avion> avioane, List<Utilizator> utilizatori, List<Bilet> bilete,
			List<Calator> calatori) {
		angajatView = new AngajatView();
		this.avioane = avioane;
		this.utilizatori = utilizatori;
		this.bilete = bilete;
		this.calatori = calatori;
		addActionListeners();
	}

	private void addActionListeners() {
		angajatView.addLogInButtonListener(new LogInButtonListener());
		angajatView.addContinueButtonListener(new ContinueButtonListener());
		angajatView.addLogOutButtonListener(new LogoutButtonListener());
		angajatView.addVanzareButtonListener(new VanzareButtonListener());
		angajatView.addStatisticiButtonListener(new StatisticiButtonListener());
		angajatView.addRaportButtonListener(new RaportButtonListener());
		angajatView.addSaveButtonListener(new SaveButtonListener());
		angajatView.addBackButtonListener(new BackButtonListener());
		angajatView.addAddPlaneButtonListener(new AddPlaneButtonListener());
		angajatView.addReadPlaneButtonListener(new ReadPlaneButtonListener());
		angajatView.addUpdatePlaneButtonListener(new UpdatePlaneButtonListener());
		angajatView.addDeletePlaneButtonListener(new DeletePlaneButtonListener());
		angajatView.addAddTicketButtonListener(new AddTicketButtonListener());
		angajatView.addReadTicketButtonListener(new ReadTicketButtonListener());
		angajatView.addUpdateTicketButtonListener(new UpdateTicketButtonListener());
		angajatView.addDeleteTicketButtonListener(new DeleteTicketButtonListener());
		angajatView.addPlanesComboBoxListener(new PlanesComboBoxListener());
		angajatView.addDeleteBUttonListener(new DeleteButtonListener());
		angajatView.addTicketsComobButtonListener(new TicketComboButtonListener());
		angajatView.addSellButtonListener(new SellButtonListener());
		angajatView.addSaveReportButtonListener(new SaveRaportButtonListener());
		angajatView.addShowButtonListener(new ShowButtonListener());
	}

	private Utilizator getUserWithCredentials(String userName, String password) {
		Utilizator utilizator = null;
		try {
			utilizator = utilizatori.stream().filter(u -> u.verifyCont(userName, password, "Employee"))
					.collect(Collectors.toList()).get(0);
			return utilizator;
		} catch (Exception e) {
			return null;
		}
	}

	private String getUsersName(String userName, String password) {

		Utilizator utilizator = utilizatori.stream().filter(u -> u.verifyCont(userName, password, "Employee"))
				.collect(Collectors.toList()).get(0);

		return ((utilizator != null) ? utilizator.getNume() : "");
	}

	public boolean logIn() {
		String userName = angajatView.getUserName();
		String password = angajatView.getPassword();
		Utilizator utilizator = getUserWithCredentials(userName, password);
		if (utilizator != null)
			return true;
		else
			return false;
	}

	private String getPlanes() {
		String planes = "";
		for (Avion avion : avioane) {
			planes += avion + "\n\n";
		}
		return planes;
	}

	private String getTickets() {
		String tickets = "";
		for (Bilet bilet : bilete) {
			tickets += bilet + "\n\n";
		}
		return tickets;
	}

	private List<String> getPlaneNumbers() {
		return avioane.stream().map(a -> String.valueOf(a.getNrAvion())).collect(Collectors.toList());
	}

	private Avion getPlaneByNumber(String number) {
		int nr = Integer.parseInt(number);
		return avioane.stream().filter(a -> a.getNrAvion() == nr).collect(Collectors.toList()).get(0);
	}

	private List<String> getTicketNumbers() {
		return bilete.stream().map(b -> String.valueOf(b.getNrBilet())).collect(Collectors.toList());
	}

	private Bilet getTicketByNumber(String number) {
		int nr = Integer.parseInt(number);
		return bilete.stream().filter(b -> b.getNrBilet() == nr).collect(Collectors.toList()).get(0);
	}

	private List<String> getSellTickets() {

		return bilete.stream().filter(b -> b.isVandut() == false).map(b -> String.valueOf(b.getNrBilet()))
				.collect(Collectors.toList());
	}

	private List<String> getSellTravellers() {

		return calatori.stream().map(c -> c.getNume()).collect(Collectors.toList());
	}

	private Calator getTravellerByName(String name) {

		return calatori.stream().filter(c -> c.getNume().equals(name)).collect(Collectors.toList()).get(0);
	}

	private boolean CheckDuplicatePlaneID(int ID) {
		for (Avion avion : avioane) {
			if (avion.getNrAvion() == ID)
				return true;
		}
		return false;
	}

	private boolean CheckDuplicateTicketID(int ID) {
		for (Bilet bilet : bilete) {
			if (bilet.getNrBilet() == ID)
				return true;
		}
		return false;
	}

	private void changeAvailableSeatsNumber(Bilet bilet) {
		for (Avion avion : avioane) {
			if (avion.getPlecare().equals(bilet.getPlecare()) && avion.getSosire().equals(bilet.getSosire())
					&& avion.getPretBilet() == bilet.getPret())
				avion.setNrLocuri(avion.getNrLocuri() - 1);
		}
	}

	class LogInButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (logIn()) {
				angajatView.addEmployeePanel(getUsersName(angajatView.getUserName(), angajatView.getPassword()));
			} else
				angajatView.showLogInErrorMessage();
			angajatView.clearCredentials();
		}

	}

	class AddPlaneButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addAddPlanePanel();
		}

	}

	class ReadPlaneButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addReadPlanesPanel(getPlanes());
		}

	}

	class UpdatePlaneButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addUpdatePlanePanel(getPlaneNumbers());
		}

	}

	class PlanesComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Avion avion = getPlaneByNumber(angajatView.getComboBoxValue());
			angajatView.setnrPlane(String.valueOf(avion.getNrAvion()));
			angajatView.setnrSeats(String.valueOf(avion.getNrLocuri()));
			angajatView.setDuration(String.valueOf(avion.getDurataZborului()));
			angajatView.setTicket(String.valueOf(avion.getPretBilet()));
			angajatView.setDeparture(avion.getPlecare());
			angajatView.setArrival(avion.getSosire());
			angajatView.setAirline(avion.getLiniaAeriana());
		}

	}

	class DeletePlaneButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addDeletePlanePanel(getPlaneNumbers());
		}

	}

	class AddTicketButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addAddTicketPanel();
		}

	}

	class ReadTicketButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addReadTicketsPanel(getTickets());
		}

	}

	class UpdateTicketButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addUpdateTicketPanel(getTicketNumbers());
		}

	}

	class TicketComboButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Bilet bilet = getTicketByNumber(angajatView.getTicketComboBoxValue());
			angajatView.setnrTicket(String.valueOf(bilet.getNrBilet()));
			angajatView.setDepartureTicket(bilet.getPlecare());
			angajatView.setArrivalTicket(bilet.getSosire());
			angajatView.setDate(bilet.getData());
			angajatView.setPrice(String.valueOf(bilet.getPret()));
		}

	}

	class DeleteTicketButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addDeleteTicketPanel(getTicketNumbers());
		}

	}

	class VanzareButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addSellTicketPanel(getSellTickets(), getSellTravellers());
		}

	}

	class StatisticiButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addStatisticsPanel();
		}

	}

	class RaportButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addRaportPanel();
		}

	}

	class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.setLogoutPanel();
		}

	}

	class SellButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String ticket = angajatView.getSellTicket();
			String traveller = angajatView.getSellTraveller();
			if (ticket == null || traveller == null) {
				angajatView.showPleaseSelectMessage();
				return;
			}
			Bilet bilet = getTicketByNumber(ticket);
			Calator calator = getTravellerByName(traveller);
			bilet.setVandut(true);
			calator.addBilet(bilet);
			changeAvailableSeatsNumber(bilet);
			angajatView.showSoldSuccesfullyMessage();
			angajatView.updateSellTicketList(getSellTickets());

		}

	}

	class DeleteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int ID = angajatView.getID();
			if (ID == 5) {
				String number = angajatView.getSelectedAirplaneNumber();
				if (number == null) {
					angajatView.showPleaseSelectMessage();
					return;
				}
				Avion avion = getPlaneByNumber(number);
				avioane.remove(avion);
				angajatView.updateDeletePlaneList(getPlaneNumbers());
			} else {
				if (ID == 6) {
					String number = angajatView.getSelectedTicketNumber();
					if (number == null) {
						angajatView.showPleaseSelectMessage();
						return;
					}
					Bilet bilet = getTicketByNumber(number);
					bilete.remove(bilet);
					angajatView.updateDeleteTicketList(getTicketNumbers());
				}
			}
		}

	}

	class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int ID = angajatView.getID();
			int nrPlane = angajatView.getnrPlane();
			int nrSeats = angajatView.getnrSeats();
			int duration = angajatView.getDuration();
			int ticketPrice = angajatView.getTicket();
			String departure = angajatView.getDeparture();
			String arrival = angajatView.getArrival();
			String airline = angajatView.getAirline();

			int nrTicket = angajatView.getnrTicket();
			int price = angajatView.getPrice();

			String departureTicket = angajatView.getDepartureTicket();
			String arrivalTicket = angajatView.getArrivalTicket();
			String date = angajatView.getDate();
			if (ID < 3 && angajatView.isValidPlaneInput() && nrPlane != 0 && nrSeats != 0 && duration != 0
					&& ticketPrice != 0) {
				if (ID == 1) {
					if (CheckDuplicatePlaneID(nrPlane)) {
						angajatView.showDuplicateMessage("plane");
						return;
					}
					Avion avion = new Avion(nrPlane, nrSeats, "Charter", airline, duration, departure, arrival,
							ticketPrice);
					avioane.add(avion);
					angajatView.showAddedSuccesfullyMessage("Airplane");
					angajatView.clearBoxes();
				} else {
					if (ID == 2) {
						Avion avion = getPlaneByNumber(angajatView.getComboBoxValue());
						avion.setNrAvion(nrPlane);
						avion.setNrLocuri(nrSeats);
						avion.setDurataZborului(duration);
						avion.setLiniaAeriana(airline);
						avion.setPlecare(departure);
						avion.setSosire(arrival);
						avion.setPretBilet(ticketPrice);
						angajatView.showUpdatedSuccesfullyMessage("Airplane");
						angajatView.clearBoxes();
					}
				}
			} else {
				if (ID >= 3 & angajatView.isValidTicketInput() && nrTicket != 0 && price != 0) {
					if (ID == 3) {
						if (CheckDuplicateTicketID(nrTicket)) {
							angajatView.showDuplicateMessage("ticket");
							return;
						}
						Bilet bilet = new Bilet(nrTicket, departureTicket, arrivalTicket, date, price);
						bilete.add(bilet);
						angajatView.showAddedSuccesfullyMessage("Ticket");
						angajatView.clearBoxes();
					} else {
						if (ID == 4) {
							Bilet bilet = getTicketByNumber(angajatView.getTicketComboBoxValue());
							bilet.setNrBilet(nrTicket);
							bilet.setData(date);
							bilet.setPlecare(departureTicket);
							bilet.setSosire(arrivalTicket);
							bilet.setPret(price);
							angajatView.showUpdatedSuccesfullyMessage("Ticket");
							angajatView.clearBoxes();
						}
					}
				} else
					angajatView.showInvalidInputMessage();
			}
		}

	}

	class SaveRaportButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String option = angajatView.getSaveReportOption();
			String fileName = angajatView.getFileName();
			FabricaRaport fabrica = new FabricaRaport();
			if (option == null) {
				angajatView.showPleaseSelectMessage();
				return;
			}
			if (fileName.equals("")) {
				angajatView.showPleaseEnter();
				return;
			}
			if (option.equals("CSV format")) {
				fabrica.obtineRaport(avioane, "CSV").salvareRaport(fileName);
			} else {
				if (option.equals("JSON format")) {
					fabrica.obtineRaport(avioane, "JSON").salvareRaport(fileName);

				} else {
					if (option.equals("XML format")) {
						fabrica.obtineRaport(avioane, "XML").salvareRaport(fileName);
					}
				}
			}
			angajatView.showSavedMessage();
		}

	}

	class ShowButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ConstructorStatistica constStat = new ConstructorStatistica();
			Statistica statistics = constStat.creareStatistica(bilete);

			String option = angajatView.getSelectedShowOption();
			if (option == null) {
				angajatView.showPleaseSelectMessage();
				return;
			}
			if (option.equals("Departure")) {
				angajatView.getChart(statistics.getStatisticaDupaPlecare(), "Departure");
			} else {
				if (option.equals("Arrival")) {
					angajatView.getChart(statistics.getStatisticaDupaSosire(), "Arrival");
				} else {
					if (option.equals("Price")) {
						angajatView.getChart(statistics.getStatisticaDupaPret(), "Price");
					}
				}
			}

		}

	}

	class BackButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.backAction();
			angajatView.clearBoxes();
		}

	}

	class ContinueButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new CalatorController(avioane);
		}

	}
}

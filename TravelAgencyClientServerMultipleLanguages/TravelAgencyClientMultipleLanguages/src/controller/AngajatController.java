package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import contract.IAvionDAC;
import contract.IBiletDAC;
import contract.ICalatorDAC;
import entity.Avion;
import entity.Bilet;
import entity.Calator;
import model.ConstructorStatistica;
import model.FabricaRaport;
import model.RBundle;
import model.Statistica;
import view.AngajatView;

public class AngajatController {

	private AngajatView angajatView;
	private IAvionDAC stubAvion;
	private IBiletDAC stubBilet;
	private ICalatorDAC stubCalator;
	private RBundle resourceBundle;

	public AngajatController(IAvionDAC stubAvion, IBiletDAC stubBilet, ICalatorDAC stubCalator, String usersName,
			RBundle bundle) {

		this.resourceBundle = bundle;
		angajatView = new AngajatView(usersName, resourceBundle);
		resourceBundle.attach(angajatView);
		this.stubAvion = stubAvion;
		this.stubBilet = stubBilet;
		this.stubCalator = stubCalator;
		addActionListeners();
	}

	private void addActionListeners() {
		angajatView.addLogOutButtonListener(new LogoutButtonListener());
		angajatView.addVanzareButtonListener(new VanzareButtonListener());
		angajatView.addStatisticiButtonListener(new StatisticiButtonListener());
		angajatView.addRaportButtonListener(new RaportButtonListener());
		angajatView.addSaveButtonListener(new SaveButtonListener());
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
		angajatView.addLanguageListener(new LanguageListener());
	}

	private String getAllPlanes() {
		String planes = "";
		try {
			planes = stubAvion.getAllPlanes().stream().map(s -> s.toString()).collect(Collectors.joining("\n\n"))
					.toString();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return planes;
	}

	private List<Avion> getPlanesList() {
		List<Avion> avioane = null;
		try {
			avioane = stubAvion.getAllPlanes();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return avioane;
	}

	private String getAllTickets() {
		String tickets = "";
		try {
			tickets = stubBilet.getAllTickets().stream().map(s -> s.toString()).collect(Collectors.joining("\n\n"))
					.toString();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	private List<Bilet> getTicketsList() {
		List<Bilet> tickets = null;
		try {
			tickets = stubBilet.getAllTickets();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	private List<String> getPlaneNumbers() {
		List<String> planeNumbers = null;
		try {
			planeNumbers = stubAvion.getPlaneNumbers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return planeNumbers;
	}

	private Avion getPlaneByNumber(String number) {
		int nr = Integer.parseInt(number);
		Avion avion = null;
		try {
			avion = stubAvion.getPlaneByNumber(nr);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return avion;
	}

	private List<String> getTicketNumbers() {
		List<String> ticketNumbers = null;
		try {
			ticketNumbers = stubBilet.getTicketNumbers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ticketNumbers;
	}

	private Bilet getTicketByNumber(String number) {
		int nr = Integer.parseInt(number);
		Bilet ticket = null;
		try {
			ticket = stubBilet.getTicketByNumber(nr);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	private List<String> getSellTickets() {
		List<String> ticketNumbers = null;
		try {
			ticketNumbers = stubBilet.getSellTickets();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ticketNumbers;
	}

	private List<String> getSellTravellers() {
		List<String> names = null;
		try {
			names = stubCalator.getSellTravellers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return names;
	}

	private Calator getTravellerByName(String name) {
		Calator calator = null;
		try {
			calator = stubCalator.getTravellerByName(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return calator;
	}

	private void changeAvailableSeatsNumber(Bilet bilet) {
		try {
			stubAvion.changeAvailableSeatsNumber(bilet);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void updateSoldTicket(Bilet bilet) {
		try {
			stubBilet.updateSoldTicket(bilet.getNrBilet());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private boolean addNewPlane(Avion avion) {
		boolean rez = true;
		try {
			boolean duplicatePlanes = stubAvion.isDuplicatePlaneInput(avion);
			if (!duplicatePlanes) {
				stubAvion.addNewPlane(avion);
			} else
				rez = false;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return rez;
	}

	private void updatePlane(int nrAvion, Avion avion) {
		try {
			stubAvion.updatePlane(nrAvion, avion);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void deletePlane(int planeID) {
		try {
			stubAvion.deletePlane(planeID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private boolean addNewTicket(Bilet bilet) {
		boolean rez = true;
		try {
			boolean duplicateTickets = stubBilet.isDuplicateTicketInput(bilet);
			if (!duplicateTickets) {
				stubBilet.addNewTicket(bilet);
			} else
				rez = false;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return rez;
	}

	private void updateTicket(int nrBilet, Bilet bilet) {
		try {
			stubBilet.updateTicket(nrBilet, bilet);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void deleteTicket(int ticketID) {
		try {
			stubBilet.deleteTicket(ticketID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void addSoldTicketToTraveller(int persID, int nrBilet) {
		try {
			stubCalator.addSoldTicket(persID, nrBilet);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	class AddPlaneButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addAddPlanePanel();
			angajatView.clearBoxes();
		}

	}

	class ReadPlaneButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addReadPlanesPanel(getAllPlanes());
			angajatView.clearBoxes();
		}

	}

	class UpdatePlaneButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addUpdatePlanePanel(getPlaneNumbers());
			angajatView.clearBoxes();
		}

	}

	class PlanesComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Avion avion = getPlaneByNumber(angajatView.getComboBoxValue());
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
			angajatView.clearBoxes();
		}

	}

	class AddTicketButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addAddTicketPanel();
			angajatView.clearBoxes();
		}

	}

	class ReadTicketButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addReadTicketsPanel(getAllTickets());
			angajatView.clearBoxes();
		}

	}

	class UpdateTicketButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			angajatView.addUpdateTicketPanel(getTicketNumbers());
			angajatView.clearBoxes();
		}

	}

	class TicketComboButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Bilet bilet = getTicketByNumber(angajatView.getTicketComboBoxValue());
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
			angajatView.clearBoxes();
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
			resourceBundle.detach(angajatView);
			angajatView.closeWindow();
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
			updateSoldTicket(bilet);
			addSoldTicketToTraveller(calator.getPersID(), bilet.getNrBilet());
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
				deletePlane(avion.getNrAvion());
				angajatView.updateDeletePlaneList(getPlaneNumbers());
			} else {
				if (ID == 6) {
					String number = angajatView.getSelectedTicketNumber();
					if (number == null) {
						angajatView.showPleaseSelectMessage();
						return;
					}
					Bilet bilet = getTicketByNumber(number);
					deleteTicket(bilet.getNrBilet());
					angajatView.updateDeleteTicketList(getTicketNumbers());
				}
			}
		}

	}

	class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int ID = angajatView.getID();
			int nrSeats = angajatView.getnrSeats();
			int duration = angajatView.getDuration();
			int ticketPrice = angajatView.getTicket();
			String departure = angajatView.getDeparture();
			String arrival = angajatView.getArrival();
			String airline = angajatView.getAirline();
			int price = angajatView.getPrice();
			String departureTicket = angajatView.getDepartureTicket();
			String arrivalTicket = angajatView.getArrivalTicket();
			String date = angajatView.getDate();

			if (ID < 3 && angajatView.isValidPlaneInput() && nrSeats != 0 && duration != 0 && ticketPrice != 0) {
				if (ID == 1) {
					Avion avion = new Avion(0, nrSeats, "Charter", airline, duration, departure, arrival, ticketPrice);
					boolean isAdded = addNewPlane(avion);
					if (isAdded) {
						angajatView.showAddedSuccesfullyMessage("Airplane");
						angajatView.clearBoxes();
					} else {
						angajatView.showDuplicateMessage("plane");
						return;
					}
				} else {
					if (ID == 2) {
						Avion plane = getPlaneByNumber(angajatView.getComboBoxValue());
						Avion updatedPlane = new Avion(0, nrSeats, plane.getTip(), airline, duration, departure,
								arrival, ticketPrice);
						updatePlane(plane.getNrAvion(), updatedPlane);
						angajatView.showUpdatedSuccesfullyMessage("Airplane");
						angajatView.clearBoxes();
						angajatView.updateUpdatePlaneList(getPlaneNumbers());
					}
				}
			} else {
				if (ID >= 3 & angajatView.isValidTicketInput() && price != 0) {
					if (ID == 3) {
						Bilet bilet = new Bilet(0, departureTicket, arrivalTicket, date, price, false);
						boolean isAdded = addNewTicket(bilet);
						if (isAdded) {
							angajatView.showAddedSuccesfullyMessage("Ticket");
							angajatView.clearBoxes();
						} else {
							angajatView.showDuplicateMessage("ticket");
							return;
						}
					} else {
						if (ID == 4) {
							Bilet bilet = getTicketByNumber(angajatView.getTicketComboBoxValue());
							Bilet updatedTicket = new Bilet(0, departureTicket, arrivalTicket, date, price,
									bilet.isVandut());
							updateTicket(bilet.getNrBilet(), updatedTicket);
							angajatView.showUpdatedSuccesfullyMessage("Ticket");
							angajatView.clearBoxes();
							angajatView.updateUpdateTicketList(getTicketNumbers());
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
				fabrica.obtineRaport(getPlanesList(), "CSV").salvareRaport(fileName);
			} else {
				if (option.equals("JSON format")) {
					fabrica.obtineRaport(getPlanesList(), "JSON").salvareRaport(fileName);

				} else {
					if (option.equals("XML format")) {
						fabrica.obtineRaport(getPlanesList(), "XML").salvareRaport(fileName);
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
			Statistica statistics = constStat.creareStatistica(getTicketsList());
			int option = angajatView.getSelectedShowOption();
			if (option == -1) {
				angajatView.showPleaseSelectMessage();
				return;
			}
			if (option == 0) {
				angajatView.getChart(statistics.getStatisticaDupaPlecare(), "Departure");
			} else {
				if (option == 1) {
					angajatView.getChart(statistics.getStatisticaDupaSosire(), "Arrival");
				} else {
					if (option == 2) {
						angajatView.getChart(statistics.getStatisticaDupaPret(), "Price");
					}
				}
			}
		}
	}

	class LanguageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (angajatView.isEnglishSelected())
				resourceBundle.changeLanguage(new Locale("en", "US"));
			if (angajatView.isRomanianSelected())
				resourceBundle.changeLanguage(new Locale("ro", "RO"));
			if (angajatView.isSpanishSelected())
				resourceBundle.changeLanguage(new Locale("es", "ES"));
		}

	}
}

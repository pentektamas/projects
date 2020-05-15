package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Locale;

import contract.IAvionDAC;
import model.RBundle;
import view.CalatorView;

public class CalatorController {

	private CalatorView calatorView;
	private IAvionDAC stub;
	private RBundle resourceBundle;

	public CalatorController(IAvionDAC stub, RBundle bundle) {

		this.stub = stub;
		this.resourceBundle = bundle;
		calatorView = new CalatorView(resourceBundle);
		resourceBundle.attach(calatorView);
		addActionListeners();

	}

	private void addActionListeners() {
		calatorView.addNumberSearchListener(new NumberSearchListener());
		calatorView.addButtonListener(new SearchButtonListener());
		calatorView.addDepartureButtonListener(new DepartureButtonListener());
		calatorView.addArrivalButtonListener(new ArrivalButtonListener());
		calatorView.addDurationButtonListener(new DurationButtonListener());
		calatorView.addRouteButtonListener(new RouteButtonListener());
		calatorView.addDepartureBoxListener(new DepartureBoxListener());
		calatorView.addArrivalBoxListener(new ArrivalBoxListener());
		calatorView.addDurationBoxListener(new DurationBoxListener());
		calatorView.addRoute1BoxListener(new Route1BoxListener());
		calatorView.addRoute2BoxListener(new Route2BoxListener());
		calatorView.addLanguageListener(new LanguageListener());
	}

	public String getPlaneByFlightNumber(int nr) {
		String plane = "";
		try {
			if (stub.getPlaneByNumber(nr) != null)
				plane = stub.getPlaneByNumber(nr).toString();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return plane;
	}

	public List<String> getDepartureList() {

		List<String> departures = null;
		try {
			departures = stub.getDepartures();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return departures;
	}

	public List<String> getArrivalList() {
		List<String> arrivals = null;
		try {
			arrivals = stub.getArrivals();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return arrivals;
	}

	public List<String> getDurationList() {
		List<String> durations = null;
		try {
			durations = stub.getDurations();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return durations;
	}

	public String getDeparturePlanes(String departure) {
		String departurePlanes = "";
		try {
			departurePlanes = stub.getPlanesByDeparture(departure);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return departurePlanes;
	}

	public String getArrivalPlanes(String arrival) {
		String arrivalPlanes = "";
		try {
			arrivalPlanes = stub.getPlanesByArrival(arrival);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return arrivalPlanes;
	}

	public String getDurationPlanes(String duration) {

		String durationPlanes = "";
		try {
			durationPlanes = stub.getPlanesByDuration(duration);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return durationPlanes;
	}

	public String getRoutePlanes(String departure, String arrival) {
		String routePlanes = "";
		try {
			routePlanes = stub.getPlanesByRoute(departure, arrival);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return routePlanes;
	}

	class NumberSearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int nr = calatorView.getFligthNumber();
			if (nr != -1) {
				if (!getPlaneByFlightNumber(nr).equals("")) {
					calatorView.printPlane(getPlaneByFlightNumber(nr));
				} else
					calatorView.showPlaneNotFound();
			}
			calatorView.clearText();
		}
	}

	class SearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			calatorView.addSearchByNumberDialog();

		}

	}

	class DepartureButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			calatorView.addSearchByDeparture(getDepartureList());

		}

	}

	class ArrivalButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			calatorView.addSearchByArrival(getArrivalList());
		}

	}

	class DurationButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			calatorView.addSearchByDuration(getDurationList());
		}

	}

	class RouteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			calatorView.addSearchByRoute(getDepartureList(), getArrivalList());
		}

	}

	class DepartureBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String departure = calatorView.getDepartureBoxText();
			calatorView.printDeparturePlanes(getDeparturePlanes(departure));

		}

	}

	class ArrivalBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String arrival = calatorView.getArrivalBoxText();
			calatorView.printArrivalPlanes(getArrivalPlanes(arrival));
		}

	}

	class DurationBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String duration = calatorView.getDurationBoxText();
			calatorView.printDurationPlanes(getDurationPlanes(duration));
		}

	}

	class Route1BoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String departure = calatorView.getRoute1BoxText();
			String arrival = calatorView.getRoute2BoxText();
			calatorView.printRoutePlanes(getRoutePlanes(departure, arrival));
		}

	}

	class Route2BoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String departure = calatorView.getRoute1BoxText();
			String arrival = calatorView.getRoute2BoxText();
			calatorView.printRoutePlanes(getRoutePlanes(departure, arrival));
		}

	}

	class LanguageListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (calatorView.isEnglishSelected())
				resourceBundle.changeLanguage(new Locale("en", "US"));
			if (calatorView.isRomanianSelected())
				resourceBundle.changeLanguage(new Locale("ro", "RO"));
			if (calatorView.isSpanishSelected())
				resourceBundle.changeLanguage(new Locale("es", "ES"));
		}

	}
}

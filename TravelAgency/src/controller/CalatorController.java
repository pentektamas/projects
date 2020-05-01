package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import view.CalatorView;
import model.Avion;

public class CalatorController {

	private CalatorView calatorView;
	private List<Avion> avioane;

	public CalatorController(List<Avion> avioane) {
		calatorView = new CalatorView();
		this.avioane = avioane;
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
	}

	public String getPlaneByFlightNumber(int nr) {
		try {
			Avion avion = (avioane.stream().filter(a -> a.getNrAvion() == nr).collect(Collectors.toList())).get(0);
			return avion.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public List<String> getDepartureList() {
		return avioane.stream().map(a -> a.getPlecare()).distinct().collect(Collectors.toList());
	}

	public List<String> getArrivalList() {
		return avioane.stream().map(a -> a.getSosire()).distinct().collect(Collectors.toList());
	}

	public List<String> getDurationList() {
		return avioane.stream().map(a -> String.valueOf(a.getDurataZborului())).distinct().collect(Collectors.toList());
	}

	public String getDeparturePlanes(String departure) {
		String departurePlanes = "";
		for (Avion avion : avioane) {
			if (avion.getPlecare().equals(departure))
				departurePlanes += avion + "\n\n";
		}
		return departurePlanes;
	}

	public String getArrivalPlanes(String arrival) {
		String arrivalPlanes = "";
		for (Avion avion : avioane) {
			if (avion.getSosire().equals(arrival))
				arrivalPlanes += avion + "\n\n";
		}
		return arrivalPlanes;
	}

	public String getDurationPlanes(String duration) {
		int dur;
		if (duration == null)
			dur = 0;
		else
			dur = Integer.parseInt(duration);
		String durationPlanes = "";
		for (Avion avion : avioane) {
			if (avion.getDurataZborului() == dur)
				durationPlanes += avion + "\n\n";
		}
		return durationPlanes;
	}

	public String getRoutePlanes(String departure, String arrival) {
		String routePlanes = "";
		for (Avion avion : avioane) {
			if (avion.getPlecare().equals(departure) && avion.getSosire().equals(arrival))
				routePlanes += avion + "\n\n";
		}
		return routePlanes;
	}

	class NumberSearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int nr = calatorView.getFligthNumber();
			if (nr != -1) {
				if (getPlaneByFlightNumber(nr) != null)
					calatorView.printPlane(getPlaneByFlightNumber(nr));
				else
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
			String arrival = calatorView.getRoute2BoxText();
			String departure = calatorView.getRoute1BoxText();
			calatorView.printRoutePlanes(getRoutePlanes(departure, arrival));
		}

	}
}

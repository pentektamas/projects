package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.Observer;
import model.RBundle;

public class AngajatView extends JDialog implements Observer {

	private JButton vanzareBiletButton;
	private JButton statisticiButton;
	private JButton salvareRaportButton;
	private JButton logOutButton;
	private JButton saveButton;
	private JButton deleteButton;
	private JButton sellButton;
	private JButton saveRaportButton;
	private JButton showButton;
	private JPanel startPanel;
	private int operationID;

	private JRadioButton addPlane;
	private JRadioButton readPlane;
	private JRadioButton updatePlane;
	private JRadioButton deletePlane;

	private JRadioButton addTicket;
	private JRadioButton readTicket;
	private JRadioButton updateTicket;
	private JRadioButton deleteTicket;

	private JTextField nrSeatsText;
	private JTextField airlineText;
	private JTextField durationText;
	private JTextField departureText;
	private JTextField arrivalText;
	private JTextField ticketText;

	private JTextField departureTicketText;
	private JTextField arrivalTicketText;
	private JTextField dateText;
	private JTextField priceText;

	private JComboBox<String> airplanesCombo;
	private JComboBox<String> ticketCombo;
	private JList<String> airplanesList;
	private JList<String> ticketsList;

	private JComboBox<String> sellTicketBox;
	private JComboBox<String> sellTravellerBox;
	private JComboBox<String> raportBox;
	private JComboBox<String> statisticsBox;
	private JTextField fileName;
	private JPanel employeeContentPanel;

	private RBundle resourceBundle;
	private JMenu menu;
	private JMenuItem english;
	private JMenuItem romanian;
	private JMenuItem spanish;

	private JLabel nameLabel;
	private JLabel nrSeatsLabel;
	private JLabel airlineLabel;
	private JLabel durationLabel;
	private JLabel departureLabel;
	private JLabel arrivalLabel;
	private JLabel ticketLabel;
	private JLabel selectPlane;
	private JLabel listOfPlanes;
	private JLabel listOfTickets;
	private JLabel selectTicket;
	private JLabel dateLabel;
	private JLabel priceLabel;
	private JLabel selectSellTicket;
	private JLabel selectTraveller;
	private JLabel selectRaport;
	private JLabel selectRaporName;
	private JLabel selectStatistic;
	private String employeeName;

	public AngajatView(String name, RBundle bundle) {
		this.employeeName = name;
		this.resourceBundle = bundle;
		JMenuBar menuBar = new JMenuBar();
		menu = new JMenu(resourceBundle.getString("language"));
		english = new JMenuItem(resourceBundle.getString("english"));
		romanian = new JMenuItem(resourceBundle.getString("romanian"));
		spanish = new JMenuItem(resourceBundle.getString("spanish"));
		menu.add(english);
		menu.add(romanian);
		menu.add(spanish);
		menuBar.add(menu);

		operationID = 0;
		vanzareBiletButton = new JButton(resourceBundle.getString("vanzareBiletButton"));
		statisticiButton = new JButton(resourceBundle.getString("statisticiButton"));
		salvareRaportButton = new JButton(resourceBundle.getString("salvareRaportButton"));
		saveButton = new JButton(resourceBundle.getString("saveButton"));
		logOutButton = new JButton(resourceBundle.getString("logOutButton"));
		deleteButton = new JButton(resourceBundle.getString("deleteButton"));
		sellButton = new JButton(resourceBundle.getString("sellButton"));
		saveRaportButton = new JButton(resourceBundle.getString("saveRaportButton"));
		showButton = new JButton(resourceBundle.getString("showButton"));
		startPanel = new JPanel();

		addPlane = new JRadioButton(resourceBundle.getString("addPlane"));
		readPlane = new JRadioButton(resourceBundle.getString("readPlane"));
		updatePlane = new JRadioButton(resourceBundle.getString("updatePlane"));
		deletePlane = new JRadioButton(resourceBundle.getString("deletePlane"));

		addTicket = new JRadioButton(resourceBundle.getString("addTicket"));
		readTicket = new JRadioButton(resourceBundle.getString("readTicket"));
		updateTicket = new JRadioButton(resourceBundle.getString("updateTicket"));
		deleteTicket = new JRadioButton(resourceBundle.getString("deleteTicket"));

		nrSeatsLabel = new JLabel(resourceBundle.getString("nrSeatsLabel"));
		airlineLabel = new JLabel(resourceBundle.getString("airlineLabel"));
		durationLabel = new JLabel(resourceBundle.getString("durationLabel"));
		departureLabel = new JLabel(resourceBundle.getString("departureLabel"));
		arrivalLabel = new JLabel(resourceBundle.getString("arrivalLabel"));
		ticketLabel = new JLabel(resourceBundle.getString("ticketLabel"));
		listOfPlanes = new JLabel(resourceBundle.getString("listOfPlanes"));
		listOfTickets = new JLabel(resourceBundle.getString("listOfTickets"));
		selectTicket = new JLabel(resourceBundle.getString("selectTicket"));
		selectPlane = new JLabel(resourceBundle.getString("selectPlane"));
		dateLabel = new JLabel(resourceBundle.getString("dateLabel"));
		priceLabel = new JLabel(resourceBundle.getString("priceLabel"));
		nameLabel = new JLabel(resourceBundle.getString("usersNameLabel", employeeName));
		selectSellTicket = new JLabel(resourceBundle.getString("selectSellTicket"));
		selectTraveller = new JLabel(resourceBundle.getString("selectTraveller"));
		selectRaport = new JLabel(resourceBundle.getString("selectRaport"));
		selectRaporName = new JLabel(resourceBundle.getString("selectRaporName"));
		selectStatistic = new JLabel(resourceBundle.getString("selectStatistic"));

		nrSeatsText = new JTextField(15);
		airlineText = new JTextField(15);
		durationText = new JTextField(15);
		departureText = new JTextField(15);
		arrivalText = new JTextField(15);
		ticketText = new JTextField(15);

		departureTicketText = new JTextField(15);
		arrivalTicketText = new JTextField(15);
		dateText = new JTextField(15);
		priceText = new JTextField(15);

		airplanesCombo = new JComboBox<String>();
		airplanesList = new JList<String>();
		ticketCombo = new JComboBox<String>();
		ticketsList = new JList<String>();

		sellTicketBox = new JComboBox<String>();
		sellTravellerBox = new JComboBox<String>();
		raportBox = new JComboBox<String>();
		fileName = new JTextField(15);
		statisticsBox = new JComboBox<String>();

		employeeContentPanel = new JPanel();

		addEmployeePanel();
		this.setVisible(true);
		this.setJMenuBar(menuBar);
		this.setPreferredSize(new Dimension(600, 600));
		this.setTitle(resourceBundle.getString("employeeTitle"));
		this.pack();
		this.setLocationRelativeTo(null);
	}

	@Override
	public void update() {
		menu.setText(resourceBundle.getString("language"));
		english.setText(resourceBundle.getString("english"));
		romanian.setText(resourceBundle.getString("romanian"));
		spanish.setText(resourceBundle.getString("spanish"));
		vanzareBiletButton.setText(resourceBundle.getString("vanzareBiletButton"));
		statisticiButton.setText(resourceBundle.getString("statisticiButton"));
		salvareRaportButton.setText(resourceBundle.getString("salvareRaportButton"));
		saveButton.setText(resourceBundle.getString("saveButton"));
		logOutButton.setText(resourceBundle.getString("logOutButton"));
		deleteButton.setText(resourceBundle.getString("deleteButton"));
		sellButton.setText(resourceBundle.getString("sellButton"));
		saveRaportButton.setText(resourceBundle.getString("saveRaportButton"));
		showButton.setText(resourceBundle.getString("showButton"));
		addPlane.setText(resourceBundle.getString("addPlane"));
		readPlane.setText(resourceBundle.getString("readPlane"));
		updatePlane.setText(resourceBundle.getString("updatePlane"));
		deletePlane.setText(resourceBundle.getString("deletePlane"));
		addTicket.setText(resourceBundle.getString("addTicket"));
		readTicket.setText(resourceBundle.getString("readTicket"));
		updateTicket.setText(resourceBundle.getString("updateTicket"));
		deleteTicket.setText(resourceBundle.getString("deleteTicket"));
		nrSeatsLabel.setText(resourceBundle.getString("nrSeatsLabel"));
		airlineLabel.setText(resourceBundle.getString("airlineLabel"));
		durationLabel.setText(resourceBundle.getString("durationLabel"));
		departureLabel.setText(resourceBundle.getString("departureLabel"));
		arrivalLabel.setText(resourceBundle.getString("arrivalLabel"));
		ticketLabel.setText(resourceBundle.getString("ticketLabel"));
		listOfPlanes.setText(resourceBundle.getString("listOfPlanes"));
		listOfTickets.setText(resourceBundle.getString("listOfTickets"));
		selectTicket.setText(resourceBundle.getString("selectTicket"));
		selectPlane.setText(resourceBundle.getString("selectPlane"));
		dateLabel.setText(resourceBundle.getString("dateLabel"));
		priceLabel.setText(resourceBundle.getString("priceLabel"));
		nameLabel.setText(resourceBundle.getString("usersNameLabel", employeeName));
		selectSellTicket.setText(resourceBundle.getString("selectSellTicket"));
		selectTraveller.setText(resourceBundle.getString("selectTraveller"));
		selectRaport.setText(resourceBundle.getString("selectRaport"));
		selectRaporName.setText(resourceBundle.getString("selectRaporName"));
		selectStatistic.setText(resourceBundle.getString("selectStatistic"));
		this.setTitle(resourceBundle.getString("employeeTitle"));
	}

	public void addEmployeePanel() {
		nameLabel.setFont(new Font("Arial", Font.ITALIC, 16));
		ButtonGroup options = new ButtonGroup();
		options.add(addPlane);
		options.add(readPlane);
		options.add(updatePlane);
		options.add(deletePlane);

		options.add(addTicket);
		options.add(readTicket);
		options.add(updateTicket);
		options.add(deleteTicket);

		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel employeePanel = new JPanel();
		p0.add(nameLabel);
		p0.add(logOutButton);
		p1.add(addPlane);
		p1.add(readPlane);
		p1.add(updatePlane);
		p1.add(deletePlane);
		p2.add(addTicket);
		p2.add(readTicket);
		p2.add(updateTicket);
		p2.add(deleteTicket);
		p3.add(vanzareBiletButton);
		p3.add(statisticiButton);
		p3.add(salvareRaportButton);
		startPanel.removeAll();
		startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));
		startPanel.add(p0);
		startPanel.add(p1);
		startPanel.add(p2);
		startPanel.add(p3);

		employeePanel.setLayout(new BorderLayout());
		employeePanel.add(startPanel, BorderLayout.PAGE_START);
		employeePanel.add(employeeContentPanel, BorderLayout.CENTER);

		this.setContentPane(employeePanel);
		this.revalidate();
	}

	public void addAddPlanePanel() {

		this.operationID = 1;
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel p9 = new JPanel();
		JPanel p10 = new JPanel();
		JPanel addPanel = new JPanel();
		p2.add(nrSeatsLabel);
		p2.add(nrSeatsText);
		p3.add(airlineLabel);
		p3.add(airlineText);
		p4.add(durationLabel);
		p4.add(durationText);
		p7.add(departureLabel);
		p7.add(departureText);
		p8.add(arrivalLabel);
		p8.add(arrivalText);
		p9.add(ticketLabel);
		p9.add(ticketText);
		p10.add(saveButton);
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.PAGE_AXIS));
		addPanel.add(Box.createVerticalStrut(20));
		addPanel.add(p2);
		addPanel.add(p3);
		addPanel.add(p4);
		addPanel.add(p7);
		addPanel.add(p8);
		addPanel.add(p9);
		addPanel.add(p10);
		setEmployeePanelContentPane(addPanel);
	}

	public void addUpdatePlanePanel(List<String> airplanes) {

		this.operationID = 2;
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(airplanes);
		airplanesCombo.setModel(model);

		JPanel p0 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel p9 = new JPanel();
		JPanel p10 = new JPanel();
		JPanel updatePanel = new JPanel();
		p0.add(selectPlane);
		p0.add(airplanesCombo);
		p2.add(nrSeatsLabel);
		p2.add(nrSeatsText);
		p3.add(airlineLabel);
		p3.add(airlineText);
		p4.add(durationLabel);
		p4.add(durationText);
		p7.add(departureLabel);
		p7.add(departureText);
		p8.add(arrivalLabel);
		p8.add(arrivalText);
		p9.add(ticketLabel);
		p9.add(ticketText);
		p10.add(saveButton);
		updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.PAGE_AXIS));
		updatePanel.add(Box.createVerticalStrut(20));
		updatePanel.add(p0);
		updatePanel.add(p2);
		updatePanel.add(p3);
		updatePanel.add(p4);
		updatePanel.add(p7);
		updatePanel.add(p8);
		updatePanel.add(p9);
		updatePanel.add(p10);
		setEmployeePanelContentPane(updatePanel);
	}

	public void addReadPlanesPanel(String text) {
		JTextArea planesText = new JTextArea(17, 30);
		planesText.setEditable(false);
		JScrollPane scroll = new JScrollPane(planesText);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel readPanel = new JPanel();
		p1.add(listOfPlanes);
		p2.add(scroll);
		readPanel.setLayout(new BoxLayout(readPanel, BoxLayout.PAGE_AXIS));
		readPanel.add(Box.createVerticalStrut(20));
		readPanel.add(p1);
		readPanel.add(p2);
		planesText.setText(text);
		setEmployeePanelContentPane(readPanel);
	}

	public void addDeletePlanePanel(List<String> names) {

		this.operationID = 5;
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addAll(names);
		airplanesList.setModel(model);
		JScrollPane scroll = new JScrollPane(airplanesList);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel deletePanel = new JPanel();
		p1.add(listOfPlanes);
		p2.add(scroll);
		p3.add(deleteButton);
		deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.PAGE_AXIS));
		deletePanel.add(Box.createVerticalStrut(20));
		deletePanel.add(p1);
		deletePanel.add(p2);
		deletePanel.add(p3);
		setEmployeePanelContentPane(deletePanel);
	}

	public void addAddTicketPanel() {

		this.operationID = 3;
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel addPanel = new JPanel();
		p2.add(departureLabel);
		p2.add(departureTicketText);
		p3.add(arrivalLabel);
		p3.add(arrivalTicketText);
		p4.add(dateLabel);
		p4.add(dateText);
		p5.add(priceLabel);
		p5.add(priceText);
		p6.add(saveButton);
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.PAGE_AXIS));
		addPanel.add(Box.createVerticalStrut(20));
		addPanel.add(p2);
		addPanel.add(p3);
		addPanel.add(p4);
		addPanel.add(p5);
		addPanel.add(p6);
		setEmployeePanelContentPane(addPanel);
	}

	public void addUpdateTicketPanel(List<String> tickets) {

		this.operationID = 4;
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(tickets);
		ticketCombo.setModel(model);

		JPanel p0 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel updatePanel = new JPanel();
		p0.add(selectTicket);
		p0.add(ticketCombo);
		p2.add(departureLabel);
		p2.add(departureTicketText);
		p3.add(arrivalLabel);
		p3.add(arrivalTicketText);
		p4.add(dateLabel);
		p4.add(dateText);
		p5.add(priceLabel);
		p5.add(priceText);
		p6.add(saveButton);
		updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.PAGE_AXIS));
		updatePanel.add(Box.createVerticalStrut(20));
		updatePanel.add(p0);
		updatePanel.add(p2);
		updatePanel.add(p3);
		updatePanel.add(p4);
		updatePanel.add(p5);
		updatePanel.add(p6);
		setEmployeePanelContentPane(updatePanel);
	}

	public void addReadTicketsPanel(String text) {
		JTextArea planesText = new JTextArea(17, 30);
		planesText.setEditable(false);
		JScrollPane scroll = new JScrollPane(planesText);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel readPanel = new JPanel();
		p1.add(listOfTickets);
		p2.add(scroll);
		readPanel.setLayout(new BoxLayout(readPanel, BoxLayout.PAGE_AXIS));
		readPanel.add(Box.createVerticalStrut(20));
		readPanel.add(p1);
		readPanel.add(p2);
		planesText.setText(text);
		setEmployeePanelContentPane(readPanel);
	}

	public void addDeleteTicketPanel(List<String> names) {

		this.operationID = 6;
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addAll(names);
		ticketsList.setModel(model);
		JScrollPane scroll = new JScrollPane(ticketsList);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel deletePanel = new JPanel();
		p1.add(listOfTickets);
		p2.add(scroll);
		p3.add(deleteButton);
		deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.PAGE_AXIS));
		deletePanel.add(Box.createVerticalStrut(20));
		deletePanel.add(p1);
		deletePanel.add(p2);
		deletePanel.add(p3);
		setEmployeePanelContentPane(deletePanel);
	}

	public void addSellTicketPanel(List<String> tickets, List<String> travellers) {

		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>();
		model1.addAll(tickets);
		model2.addAll(travellers);
		sellTicketBox.setModel(model1);
		sellTravellerBox.setModel(model2);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel sellPanel = new JPanel();
		p1.add(selectTicket);
		p1.add(sellTicketBox);
		p2.add(selectTraveller);
		p2.add(sellTravellerBox);
		p3.add(sellButton);
		sellPanel.setLayout(new BoxLayout(sellPanel, BoxLayout.PAGE_AXIS));
		sellPanel.add(Box.createVerticalStrut(20));
		sellPanel.add(p1);
		sellPanel.add(p2);
		sellPanel.add(p3);
		setEmployeePanelContentPane(sellPanel);
	}

	public void addRaportPanel() {

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(Arrays.asList("CSV format", "XML format", "JSON format"));
		raportBox.setModel(model);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel raportPanel = new JPanel();
		p1.add(selectRaport);
		p1.add(raportBox);
		p2.add(selectRaporName);
		p2.add(fileName);
		p3.add(saveRaportButton);
		raportPanel.setLayout(new BoxLayout(raportPanel, BoxLayout.PAGE_AXIS));
		raportPanel.add(Box.createVerticalStrut(20));
		raportPanel.add(p1);
		raportPanel.add(p2);
		raportPanel.add(p3);
		setEmployeePanelContentPane(raportPanel);
	}

	public void addStatisticsPanel() {

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(Arrays.asList(resourceBundle.getString("Departure"), resourceBundle.getString("Arrival"),
				resourceBundle.getString("Price")));
		statisticsBox.setModel(model);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel statisticsPanel = new JPanel();
		p1.add(selectStatistic);
		p1.add(statisticsBox);
		p2.add(showButton);
		statisticsPanel.setLayout(new BoxLayout(statisticsPanel, BoxLayout.PAGE_AXIS));
		statisticsPanel.add(Box.createVerticalStrut(20));
		statisticsPanel.add(p1);
		statisticsPanel.add(p2);
		setEmployeePanelContentPane(statisticsPanel);
	}

	public void getChart(Map<String, Long> values, String type) {

		JDialog dialog = new JDialog();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (String name : values.keySet()) {
			String key = name.toString();
			Long value = values.get(name);
			dataset.addValue(value, type, key);
		}

		JFreeChart barChart = ChartFactory.createBarChart(
				resourceBundle.getString("statisticsBy", resourceBundle.getString(type)), "",
				resourceBundle.getString("criteriaStatistics"), dataset, PlotOrientation.VERTICAL, false, true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setBackground(Color.BLACK);
		dialog.add(chartPanel);
		dialog.pack();
		dialog.setTitle(resourceBundle.getString("chartType", resourceBundle.getString(type)));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	private void setEmployeePanelContentPane(JPanel p) {
		employeeContentPanel.removeAll();
		employeeContentPanel.add(p);
		employeeContentPanel.revalidate();
		employeeContentPanel.repaint();
	}

	public void addLogOutButtonListener(ActionListener listener) {
		logOutButton.addActionListener(listener);
	}

	public void addAddPlaneButtonListener(ActionListener listener) {
		addPlane.addActionListener(listener);
	}

	public void addReadPlaneButtonListener(ActionListener listener) {
		readPlane.addActionListener(listener);
	}

	public void addUpdatePlaneButtonListener(ActionListener listener) {
		updatePlane.addActionListener(listener);
	}

	public void addDeletePlaneButtonListener(ActionListener listener) {
		deletePlane.addActionListener(listener);
	}

	public void addAddTicketButtonListener(ActionListener listener) {
		addTicket.addActionListener(listener);
	}

	public void addReadTicketButtonListener(ActionListener listener) {
		readTicket.addActionListener(listener);
	}

	public void addUpdateTicketButtonListener(ActionListener listener) {
		updateTicket.addActionListener(listener);
	}

	public void addDeleteTicketButtonListener(ActionListener listener) {
		deleteTicket.addActionListener(listener);
	}

	public void addVanzareButtonListener(ActionListener listener) {
		vanzareBiletButton.addActionListener(listener);
	}

	public void addStatisticiButtonListener(ActionListener listener) {
		statisticiButton.addActionListener(listener);
	}

	public void addRaportButtonListener(ActionListener listener) {
		salvareRaportButton.addActionListener(listener);
	}

	public void addSaveButtonListener(ActionListener listener) {
		saveButton.addActionListener(listener);
	}

	public void addPlanesComboBoxListener(ActionListener listener) {
		airplanesCombo.addActionListener(listener);
	}

	public void addDeleteBUttonListener(ActionListener listener) {
		deleteButton.addActionListener(listener);
	}

	public void addTicketsComobButtonListener(ActionListener listener) {
		ticketCombo.addActionListener(listener);
	}

	public void addSellButtonListener(ActionListener listener) {
		sellButton.addActionListener(listener);
	}

	public void addSaveReportButtonListener(ActionListener listener) {
		saveRaportButton.addActionListener(listener);
	}

	public void addShowButtonListener(ActionListener listener) {
		showButton.addActionListener(listener);
	}

	public void addLanguageListener(ActionListener listener) {
		english.addActionListener(listener);
		romanian.addActionListener(listener);
		spanish.addActionListener(listener);
	}

	public void clearBoxes() {
		nrSeatsText.setText("");
		ticketText.setText("");
		departureText.setText("");
		arrivalText.setText("");
		airlineText.setText("");
		durationText.setText("");
		departureTicketText.setText("");
		arrivalTicketText.setText("");
		dateText.setText("");
		priceText.setText("");
	}

	public int getnrSeats() {
		int val = 0;
		try {
			val = Integer.parseInt(nrSeatsText.getText());
		} catch (Exception e) {
		}
		return val;
	}

	public String getDeparture() {
		return departureText.getText();
	}

	public String getArrival() {
		return arrivalText.getText();
	}

	public int getDuration() {
		int val = 0;
		try {
			val = Integer.parseInt(durationText.getText());
		} catch (Exception e) {
		}
		return val;

	}

	public int getTicket() {
		int val = 0;
		try {
			val = Integer.parseInt(ticketText.getText());
		} catch (Exception e) {
		}
		return val;

	}

	public String getAirline() {
		return airlineText.getText();
	}

	public void setnrSeats(String text) {
		nrSeatsText.setText(text);
	}

	public void setDeparture(String text) {
		departureText.setText(text);
	}

	public void setArrival(String text) {
		arrivalText.setText(text);
	}

	public void setDuration(String text) {
		durationText.setText(text);
	}

	public void setTicket(String text) {
		ticketText.setText(text);
	}

	public void setAirline(String text) {
		airlineText.setText(text);
	}

	public int getPrice() {
		int val = 0;
		try {
			val = Integer.parseInt(priceText.getText());
		} catch (Exception e) {
		}
		return val;
	}

	public String getDepartureTicket() {
		return departureTicketText.getText();
	}

	public String getArrivalTicket() {
		return arrivalTicketText.getText();
	}

	public String getDate() {
		return dateText.getText();
	}

	public void setDepartureTicket(String text) {
		departureTicketText.setText(text);
	}

	public void setArrivalTicket(String text) {
		arrivalTicketText.setText(text);
	}

	public void setDate(String text) {
		dateText.setText(text);
	}

	public void setPrice(String text) {
		priceText.setText(text);
	}

	public boolean isValidPlaneInput() {
		return (!(nrSeatsText.getText().equals("") || departureText.getText().equals("")
				|| arrivalText.getText().equals("") || durationText.getText().equals("")
				|| ticketText.getText().equals("") || airlineText.getText().equals("")));
	}

	public boolean isValidTicketInput() {
		return (!(departureTicketText.getText().equals("") || arrivalTicketText.getText().equals("")
				|| priceText.getText().equals("") || dateText.getText().equals("")));
	}

	public int getID() {
		return this.operationID;
	}

	public void showInvalidInputMessage() {
		JOptionPane.showMessageDialog(null, resourceBundle.getString("invalidInputMessage"),
				resourceBundle.getString("wrongInput"), JOptionPane.WARNING_MESSAGE);
	}

	public void showAddedSuccesfullyMessage(String type) {
		JOptionPane.showMessageDialog(null,
				resourceBundle.getString("addedSuccessfullyMessage", resourceBundle.getString(type)),
				resourceBundle.getString("typeAdded", resourceBundle.getString(type)), JOptionPane.INFORMATION_MESSAGE);
	}

	public void showUpdatedSuccesfullyMessage(String type) {
		JOptionPane.showMessageDialog(null,
				resourceBundle.getString("updatedSuccessfullyMessage", resourceBundle.getString(type)),
				resourceBundle.getString("typeUpdated", resourceBundle.getString(type)),
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void showSoldSuccesfullyMessage() {
		JOptionPane.showMessageDialog(null, resourceBundle.getString("soldSuccesfullyMessag"),
				resourceBundle.getString("ticketSold"), JOptionPane.INFORMATION_MESSAGE);
	}

	public void showSavedMessage() {
		JOptionPane.showMessageDialog(null, resourceBundle.getString("savedMessage"),
				resourceBundle.getString("reportSaved"), JOptionPane.INFORMATION_MESSAGE);
	}

	public void showDuplicateMessage(String type) {
		JOptionPane.showMessageDialog(null,
				resourceBundle.getString("duplicateMessage", resourceBundle.getString(type)),
				resourceBundle.getString("duplicateInput", resourceBundle.getString(type)), JOptionPane.ERROR_MESSAGE);
	}

	public void showPleaseSelectMessage() {
		JOptionPane.showMessageDialog(null, resourceBundle.getString("pleaseSelectMessage"),
				resourceBundle.getString("selectAValue"), JOptionPane.WARNING_MESSAGE);
	}

	public void showPleaseEnter() {
		JOptionPane.showMessageDialog(null, resourceBundle.getString("pleaseEnter"),
				resourceBundle.getString("enterAValue"), JOptionPane.WARNING_MESSAGE);
	}

	public String getComboBoxValue() {
		return (String) airplanesCombo.getSelectedItem();
	}

	public String getSelectedAirplaneNumber() {
		return (String) airplanesList.getSelectedValue();
	}

	public String getSelectedTicketNumber() {
		return (String) ticketsList.getSelectedValue();
	}

	public void updateUpdatePlaneList(List<String> planes) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(planes);
		airplanesCombo.setModel(model);
	}

	public void updateUpdateTicketList(List<String> tickets) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(tickets);
		ticketCombo.setModel(model);
	}

	public void updateDeletePlaneList(List<String> planes) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addAll(planes);
		airplanesList.setModel(model);
	}

	public void updateDeleteTicketList(List<String> tickets) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addAll(tickets);
		ticketsList.setModel(model);
	}

	public String getTicketComboBoxValue() {
		return (String) ticketCombo.getSelectedItem();
	}

	public String getSellTicket() {
		return (String) sellTicketBox.getSelectedItem();
	}

	public String getSellTraveller() {
		return (String) sellTravellerBox.getSelectedItem();
	}

	public void updateSellTicketList(List<String> tickets) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(tickets);
		sellTicketBox.setModel(model);
		sellTravellerBox.setSelectedIndex(-1);
	}

	public String getSaveReportOption() {
		return (String) raportBox.getSelectedItem();
	}

	public String getFileName() {
		return fileName.getText();
	}

	public int getSelectedShowOption() {
		return statisticsBox.getSelectedIndex();
	}

	public void closeWindow() {
		this.dispose();
	}

	public boolean isEnglishSelected() {
		return english.isArmed();
	}

	public boolean isRomanianSelected() {
		return romanian.isArmed();
	}

	public boolean isSpanishSelected() {
		return spanish.isArmed();
	}
}

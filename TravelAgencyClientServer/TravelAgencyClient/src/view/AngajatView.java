package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class AngajatView extends JDialog {

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

//	private JTextField nrPlaneText;
	private JTextField nrSeatsText;
	private JTextField airlineText;
	private JTextField durationText;
	private JTextField departureText;
	private JTextField arrivalText;
	private JTextField ticketText;

//	private JTextField nrTicketText;
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

	public AngajatView(String name) {

		operationID = 0;
		vanzareBiletButton = new JButton("Sell a ticket");
		statisticiButton = new JButton("Create statistics");
		salvareRaportButton = new JButton("Save reports");
		saveButton = new JButton("Save");
		logOutButton = new JButton("Log out");
		deleteButton = new JButton("Delete");
		sellButton = new JButton("Sell");
		saveRaportButton = new JButton("Save Report");
		showButton = new JButton("Show");
		startPanel = new JPanel();

		addPlane = new JRadioButton("Add plane");
		readPlane = new JRadioButton("View planes");
		updatePlane = new JRadioButton("Update plane");
		deletePlane = new JRadioButton("Delete plane");

		addTicket = new JRadioButton("Add ticket");
		readTicket = new JRadioButton("View tickets");
		updateTicket = new JRadioButton("Update ticket");
		deleteTicket = new JRadioButton("Delete ticket");

//		nrPlaneText = new JTextField(15);
		nrSeatsText = new JTextField(15);
		airlineText = new JTextField(15);
		durationText = new JTextField(15);
		departureText = new JTextField(15);
		arrivalText = new JTextField(15);
		ticketText = new JTextField(15);

//		nrTicketText = new JTextField(15);
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

		addEmployeePanel(name);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(600, 600));
		this.setTitle("Employee");
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void addEmployeePanel(String name) {

		JLabel nameLabel = new JLabel("Welcome " + name + " !");
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
//		JLabel nrPlaneLabel = new JLabel("Nr. plane: ");
		JLabel nrSeatsLabel = new JLabel("Nr. seats: ");
		JLabel airlineLabel = new JLabel("Airline: ");
		JLabel durationLabel = new JLabel("Duration: ");
		JLabel departureLabel = new JLabel("Departure: ");
		JLabel arrivalLabel = new JLabel("Arrival: ");
		JLabel ticketLabel = new JLabel("Ticket price: ");

//		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel p9 = new JPanel();
		JPanel p10 = new JPanel();
		JPanel addPanel = new JPanel();
//		p1.add(nrPlaneLabel);
//		p1.add(nrPlaneText);
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
//		addPanel.add(p1);
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
		JLabel select = new JLabel("Select an airplane number");
//		JLabel nrPlaneLabel = new JLabel("Nr. plane: ");
		JLabel nrSeatsLabel = new JLabel("Nr. seats: ");
		JLabel airlineLabel = new JLabel("Airline: ");
		JLabel durationLabel = new JLabel("Duration: ");
		JLabel departureLabel = new JLabel("Departure: ");
		JLabel arrivalLabel = new JLabel("Arrival: ");
		JLabel ticketLabel = new JLabel("Ticket price: ");

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(airplanes);
		airplanesCombo.setModel(model);

		JPanel p0 = new JPanel();
//		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel p9 = new JPanel();
		JPanel p10 = new JPanel();
		JPanel updatePanel = new JPanel();
		p0.add(select);
		p0.add(airplanesCombo);
//		p1.add(nrPlaneLabel);
//		p1.add(nrPlaneText);
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
//		updatePanel.add(p1);
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
		JLabel list = new JLabel("List of the planes:");

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel readPanel = new JPanel();
		p1.add(list);
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
		JLabel list = new JLabel("List of the airplanes:");

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel deletePanel = new JPanel();
		p1.add(list);
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
//		JLabel nrTicketLabel = new JLabel("Nr. ticket: ");
		JLabel departureLabel = new JLabel("Departure: ");
		JLabel arrivalLabel = new JLabel("Arrival: ");
		JLabel dateLabel = new JLabel("Date: ");
		JLabel priceLabel = new JLabel("Price: ");

//		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel addPanel = new JPanel();
//		p1.add(nrTicketLabel);
//		p1.add(nrTicketText);
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
//		addPanel.add(p1);
		addPanel.add(p2);
		addPanel.add(p3);
		addPanel.add(p4);
		addPanel.add(p5);
		addPanel.add(p6);
		setEmployeePanelContentPane(addPanel);
	}

	public void addUpdateTicketPanel(List<String> tickets) {

		this.operationID = 4;
		JLabel select = new JLabel("Select a ticket number");
//		JLabel nrTicketLabel = new JLabel("Nr. ticket: ");
		JLabel departureLabel = new JLabel("Departure: ");
		JLabel arrivalLabel = new JLabel("Arrival: ");
		JLabel dateLabel = new JLabel("Date: ");
		JLabel priceLabel = new JLabel("Price: ");

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(tickets);
		ticketCombo.setModel(model);

		JPanel p0 = new JPanel();
//		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel updatePanel = new JPanel();
		p0.add(select);
		p0.add(ticketCombo);
//		p1.add(nrTicketLabel);
//		p1.add(nrTicketText);
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
//		updatePanel.add(p1);
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
		JLabel list = new JLabel("List of the tickets:");

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel readPanel = new JPanel();
		p1.add(list);
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
		JLabel list = new JLabel("List of the tickets:");

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel deletePanel = new JPanel();
		p1.add(list);
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

		JLabel ticket = new JLabel("Select a ticket: ");
		JLabel traveller = new JLabel("Select a traveller: ");
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
		p1.add(ticket);
		p1.add(sellTicketBox);
		p2.add(traveller);
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

		JLabel select = new JLabel("Select a format to save the report: ");
		JLabel name = new JLabel("Name of the file: ");
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(Arrays.asList("CSV format", "XML format", "JSON format"));
		raportBox.setModel(model);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel raportPanel = new JPanel();
		p1.add(select);
		p1.add(raportBox);
		p2.add(name);
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

		JLabel select = new JLabel("Select a criteria for statistic charts: ");
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(Arrays.asList("Departure", "Arrival", "Price"));
		statisticsBox.setModel(model);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel statisticsPanel = new JPanel();
		p1.add(select);
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

		JFreeChart barChart = ChartFactory.createBarChart("Statistics by " + type, "", "Number of sold tickets",
				dataset, PlotOrientation.VERTICAL, false, true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setBackground(Color.BLACK);
		dialog.add(chartPanel);
		dialog.pack();
		dialog.setTitle(type + " chart");
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

	public void clearBoxes() {
//		nrPlaneText.setText("");
		nrSeatsText.setText("");
		ticketText.setText("");
		departureText.setText("");
		arrivalText.setText("");
		airlineText.setText("");
		durationText.setText("");
//		nrTicketText.setText("");
		departureTicketText.setText("");
		arrivalTicketText.setText("");
		dateText.setText("");
		priceText.setText("");
	}

	/*
	 * public int getnrPlane() { int val = 0; try { val =
	 * Integer.parseInt(nrPlaneText.getText()); } catch (Exception e) { } return
	 * val;
	 * 
	 * }
	 */

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

	/*
	 * public void setnrPlane(String text) { nrPlaneText.setText(text); }
	 */

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

	/*
	 * public int getnrTicket() { int val = 0; try { val =
	 * Integer.parseInt(nrTicketText.getText()); } catch (Exception e) { } return
	 * val; }
	 */

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

	/*
	 * public void setnrTicket(String text) { nrTicketText.setText(text); }
	 */

	public void setPrice(String text) {
		priceText.setText(text);
	}

	public boolean isValidPlaneInput() {
		return (!(nrSeatsText.getText().equals("") // || nrPlaneText.getText().equals("")
				|| departureText.getText().equals("") || arrivalText.getText().equals("")
				|| durationText.getText().equals("") || ticketText.getText().equals("")
				|| airlineText.getText().equals("")));
	}

	public boolean isValidTicketInput() {
		return (!(/* nrTicketText.getText().equals("") || */ departureTicketText.getText().equals("")
				|| arrivalTicketText.getText().equals("") || priceText.getText().equals("")
				|| dateText.getText().equals("")));
	}

	public int getID() {
		return this.operationID;
	}

	public void showInvalidInputMessage() {
		JOptionPane.showMessageDialog(null, "Warning!\nPlease fill each textbox witch correct value!", "Wrong input",
				JOptionPane.WARNING_MESSAGE);
	}

	public void showAddedSuccesfullyMessage(String type) {
		JOptionPane.showMessageDialog(null, type + " added successfully!", type + " added",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void showUpdatedSuccesfullyMessage(String type) {
		JOptionPane.showMessageDialog(null, type + " updated successfully!", type + " updated",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void showSoldSuccesfullyMessage() {
		JOptionPane.showMessageDialog(null, "Ticket sold successfully!", "Ticket sold",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void showSavedMessage() {
		JOptionPane.showMessageDialog(null, "Report saved successfully!", "Report saved",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void showDuplicateMessage(String type) {
		JOptionPane.showMessageDialog(null, "Error!\nDuplicate " + type + " number!\nEnter a unique plane number!",
				"Duplicate " + type + " number", JOptionPane.ERROR_MESSAGE);
	}

	public void showPleaseSelectMessage() {
		JOptionPane.showMessageDialog(null, "Warning!\nPlease select a value from list!", "Select a value ",
				JOptionPane.WARNING_MESSAGE);
	}

	public void showPleaseEnter() {
		JOptionPane.showMessageDialog(null, "Warning!\nPlease enter a file name!", "Enter a value ",
				JOptionPane.WARNING_MESSAGE);
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

	public String getSelectedShowOption() {
		return (String) statisticsBox.getSelectedItem();
	}

	public void closeWindow() {
		this.dispose();
	}
}

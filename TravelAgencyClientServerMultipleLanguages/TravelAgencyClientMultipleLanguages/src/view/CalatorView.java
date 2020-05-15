package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import model.Observer;
import model.RBundle;

public class CalatorView extends JDialog implements Observer {

	private JLabel viewLabel;
	private ButtonGroup buttonGroup;
	private JRadioButton departureButton;
	private JRadioButton arrivalButton;
	private JRadioButton durationButton;
	private JRadioButton routeButton;
	private JLabel searchLabel;
	private JButton searchButton;
	private JTextArea numberTextArea;
	private JTextField number;
	private JPanel calatorPanel;
	private JPanel functionalityPanel;
	private JComboBox<String> departureBox;
	private JComboBox<String> arrivalBox;
	private JComboBox<String> durationBox;
	private JComboBox<String> route1Box;
	private JComboBox<String> route2Box;
	private JTextArea departureTextArea;
	private JTextArea arrivalTextArea;
	private JTextArea durationTextArea;
	private JTextArea routeTextArea;
	private RBundle resourceBundle;
	private JMenu menu;
	private JMenuItem english;
	private JMenuItem romanian;
	private JMenuItem spanish;
	private JLabel enterFlightNumber;
	private JLabel selectDeparture;
	private JLabel selectArrival;
	private JLabel selectDuration;
	private JLabel selectRouteDeparture;
	private JLabel selectRouteArrival;

	public CalatorView(RBundle bundle) {
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

		viewLabel = new JLabel(resourceBundle.getString("view"));
		departureButton = new JRadioButton(resourceBundle.getString("departure"));
		arrivalButton = new JRadioButton(resourceBundle.getString("arrival"));
		durationButton = new JRadioButton(resourceBundle.getString("duration"));
		routeButton = new JRadioButton(resourceBundle.getString("route"));
		buttonGroup = new ButtonGroup();
		searchLabel = new JLabel(resourceBundle.getString("searchLabel"));
		searchButton = new JButton(resourceBundle.getString("searchButton"));
		enterFlightNumber = new JLabel(resourceBundle.getString("enterFlightNumber"));
		selectDeparture = new JLabel(resourceBundle.getString("selectDeparture"));
		selectArrival = new JLabel(resourceBundle.getString("selectArrival"));
		selectDuration = new JLabel(resourceBundle.getString("selectDuration"));
		selectRouteDeparture = new JLabel(resourceBundle.getString("selectDeparture"));
		selectRouteArrival = new JLabel(resourceBundle.getString("selectArrival"));

		number = new JTextField(15);
		buttonGroup.add(departureButton);
		buttonGroup.add(arrivalButton);
		buttonGroup.add(durationButton);
		buttonGroup.add(routeButton);

		calatorPanel = new JPanel();
		functionalityPanel = new JPanel();

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.add(p1);
		p3.add(p2);
		p1.add(viewLabel);
		p1.add(departureButton);
		p1.add(arrivalButton);
		p1.add(durationButton);
		p1.add(routeButton);
		p2.add(searchLabel);
		p2.add(searchButton);
		calatorPanel.setLayout(new BorderLayout());
		calatorPanel.add(p3, BorderLayout.PAGE_START);
		calatorPanel.add(functionalityPanel);

		departureBox = new JComboBox<String>();
		arrivalBox = new JComboBox<String>();
		durationBox = new JComboBox<String>();
		route1Box = new JComboBox<String>();
		route2Box = new JComboBox<String>();

		this.setContentPane(calatorPanel);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(600, 600));
		this.setTitle(resourceBundle.getString("calatorTitle"));
		this.pack();
		this.setLocationRelativeTo(null);
	}

	@Override
	public void update() {
		menu.setText(resourceBundle.getString("language"));
		english.setText(resourceBundle.getString("english"));
		romanian.setText(resourceBundle.getString("romanian"));
		spanish.setText(resourceBundle.getString("spanish"));
		viewLabel.setText(resourceBundle.getString("view"));
		departureButton.setText(resourceBundle.getString("departure"));
		arrivalButton.setText(resourceBundle.getString("arrival"));
		durationButton.setText(resourceBundle.getString("duration"));
		routeButton.setText(resourceBundle.getString("route"));
		searchLabel.setText(resourceBundle.getString("searchLabel"));
		searchButton.setText(resourceBundle.getString("searchButton"));
		enterFlightNumber.setText(resourceBundle.getString("enterFlightNumber"));
		selectDeparture.setText(resourceBundle.getString("selectDeparture"));
		selectArrival.setText(resourceBundle.getString("selectArrival"));
		selectDuration.setText(resourceBundle.getString("selectDuration"));
		selectRouteDeparture.setText(resourceBundle.getString("selectDeparture"));
		selectRouteArrival.setText(resourceBundle.getString("selectArrival"));
		this.setTitle(resourceBundle.getString("calatorTitle"));
	}

	public void addSearchByNumberDialog() {
		numberTextArea = new JTextArea(22, 30);
		numberTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(numberTextArea);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.add(enterFlightNumber);
		p1.add(Box.createVerticalStrut(20));
		p1.add(number);
		p2.add(scroll);
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.add(p1);
		p3.add(p2);

		setFunctionalityPanelContentPane(p3);
	}

	public void addSearchByDeparture(List<String> values) {
		departureTextArea = new JTextArea(22, 30);
		departureTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(departureTextArea);
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(values);
		departureBox.setModel(model);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.add(selectDeparture);
		p1.add(Box.createVerticalStrut(20));
		p1.add(departureBox);
		p2.add(scroll);
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.add(p1);
		p3.add(p2);

		setFunctionalityPanelContentPane(p3);
	}

	public void addSearchByArrival(List<String> values) {
		arrivalTextArea = new JTextArea(22, 30);
		arrivalTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(arrivalTextArea);
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(values);
		arrivalBox.setModel(model);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.add(selectArrival);
		p1.add(Box.createVerticalStrut(20));
		p1.add(arrivalBox);
		p2.add(scroll);
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.add(p1);
		p3.add(p2);

		setFunctionalityPanelContentPane(p3);
	}

	public void addSearchByDuration(List<String> values) {
		durationTextArea = new JTextArea(22, 30);
		durationTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(durationTextArea);
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(values);
		durationBox.setModel(model);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();

		p1.add(selectDuration);
		p1.add(Box.createVerticalStrut(20));
		p1.add(durationBox);
		p2.add(scroll);
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.add(p1);
		p3.add(p2);

		setFunctionalityPanelContentPane(p3);
	}

	public void addSearchByRoute(List<String> departure, List<String> arrival) {
		routeTextArea = new JTextArea(20, 30);
		routeTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(routeTextArea);
		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<String>();
		model1.addAll(departure);
		route1Box.setModel(model1);
		DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>();
		model2.addAll(arrival);
		route2Box.setModel(model2);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p5 = new JPanel();
		p1.add(selectRouteDeparture);
		p1.add(route1Box);
		p5.add(selectRouteArrival);
		p5.add(route2Box);
		p2.add(scroll);
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.add(p1);
		p3.add(p5);
		p3.add(p2);
		setFunctionalityPanelContentPane(p3);
	}

	private void setFunctionalityPanelContentPane(JPanel p) {
		functionalityPanel.removeAll();
		functionalityPanel.add(p);
		functionalityPanel.revalidate();
		functionalityPanel.repaint();
	}

	public void addNumberSearchListener(ActionListener listener) {
		number.addActionListener(listener);
	}

	public void addButtonListener(ActionListener listener) {
		searchButton.addActionListener(listener);
	}

	public void addDepartureButtonListener(ActionListener listener) {
		departureButton.addActionListener(listener);
	}

	public void addDepartureBoxListener(ActionListener listener) {
		departureBox.addActionListener(listener);
	}

	public void addArrivalButtonListener(ActionListener listener) {
		arrivalButton.addActionListener(listener);
	}

	public void addArrivalBoxListener(ActionListener listener) {
		arrivalBox.addActionListener(listener);
	}

	public void addDurationButtonListener(ActionListener listener) {
		durationButton.addActionListener(listener);
	}

	public void addDurationBoxListener(ActionListener listener) {
		durationBox.addActionListener(listener);
	}

	public void addRouteButtonListener(ActionListener listener) {
		routeButton.addActionListener(listener);
	}

	public void addRoute1BoxListener(ActionListener listener) {
		route1Box.addActionListener(listener);
	}

	public void addRoute2BoxListener(ActionListener listener) {
		route2Box.addActionListener(listener);
	}

	public void addLanguageListener(ActionListener listener) {
		english.addActionListener(listener);
		romanian.addActionListener(listener);
		spanish.addActionListener(listener);
	}

	public int getFligthNumber() {
		int nr = -1;
		try {
			nr = Integer.parseInt(number.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, resourceBundle.getString("flightNumberError"),
					resourceBundle.getString("invalidInput"), JOptionPane.ERROR_MESSAGE);
		}
		return nr;
	}

	public void printPlane(String text) {
		numberTextArea.append(text + "\n\n");
	}

	public void showPlaneNotFound() {
		JOptionPane.showMessageDialog(null, resourceBundle.getString("showPlaneNotFound"),
				resourceBundle.getString("noResult"), JOptionPane.INFORMATION_MESSAGE);
	}

	public void clearText() {
		number.setText("");
	}

	public String getDepartureBoxText() {
		return (String) departureBox.getSelectedItem();
	}

	public String getArrivalBoxText() {
		return (String) arrivalBox.getSelectedItem();
	}

	public String getDurationBoxText() {
		return (String) durationBox.getSelectedItem();
	}

	public String getRoute1BoxText() {
		return (String) route1Box.getSelectedItem();
	}

	public String getRoute2BoxText() {
		return (String) route2Box.getSelectedItem();
	}

	public void printDeparturePlanes(String text) {
		departureTextArea.setText(text + "\n\n");
	}

	public void printArrivalPlanes(String text) {
		arrivalTextArea.setText(text + "\n\n");
	}

	public void printDurationPlanes(String text) {
		durationTextArea.setText(text + "\n\n");
	}

	public void printRoutePlanes(String text) {
		routeTextArea.setText(text + "\n\n");
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

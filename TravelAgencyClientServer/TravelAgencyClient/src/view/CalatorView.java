package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CalatorView extends JDialog {

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

	public CalatorView() {
		viewLabel = new JLabel("1. View the list of airplanes by:");
		departureButton = new JRadioButton("Departure");
		arrivalButton = new JRadioButton("Arrival");
		durationButton = new JRadioButton("Duration");
		routeButton = new JRadioButton("Route");
		buttonGroup = new ButtonGroup();
		searchLabel = new JLabel("2. Search for an airplane by number!");
		searchButton = new JButton("Search by number");
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
		this.setVisible(true);
		this.setPreferredSize(new Dimension(600, 600));
		this.setTitle("Traveller");
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void addSearchByNumberDialog() {
		numberTextArea = new JTextArea(22, 30);
		numberTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(numberTextArea);
		JLabel enter = new JLabel("Please enter the flight number!");

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.add(enter);
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
		JLabel select = new JLabel("Select a departure airport!");
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(values);
		departureBox.setModel(model);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.add(select);
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
		JLabel select = new JLabel("Select an arrival airport!");
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(values);
		arrivalBox.setModel(model);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		p1.add(select);
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
		JLabel select = new JLabel("Select a duration in minutes!");
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addAll(values);
		durationBox.setModel(model);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();

		p1.add(select);
		p1.add(Box.createVerticalStrut(20));
		p1.add(durationBox);
		p2.add(scroll);
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		p3.add(p1);
		p3.add(p2);

		setFunctionalityPanelContentPane(p3);
	}

	public void addSearchByRoute(List<String> departure, List<String> arrival) {
		routeTextArea = new JTextArea(22, 30);
		routeTextArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(routeTextArea);
		JLabel select1 = new JLabel("Select a departure airport!");
		JLabel select2 = new JLabel("Select an arrival airport!");
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
		p1.add(select1);
		p1.add(route1Box);
		p5.add(select2);
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

	public int getFligthNumber() {
		int nr = -1;
		try {
			nr = Integer.parseInt(number.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error!\nInvalid flight number format!\nPlease enter a valid format!",
					"Invalid input", JOptionPane.ERROR_MESSAGE);
		}
		return nr;
	}

	public void printPlane(String text) {
		numberTextArea.append(text + "\n\n");
	}

	public void showPlaneNotFound() {
		JOptionPane.showMessageDialog(null, "Sorry!\nWe can't found a plane with this number!\nTry a valid number!",
				"No result", JOptionPane.INFORMATION_MESSAGE);
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
}

package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.Observer;
import model.RBundle;

public class LogInPanel extends JDialog implements Observer {

	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JTextField userNameText;
	private JPasswordField passwordText;
	private JLabel pleaseLabel;
	private JButton logInButton;
	private JButton continueButton;
	private RBundle resourceBundle;
	private JMenu menu;
	private JMenuItem english;
	private JMenuItem romanian;
	private JMenuItem spanish;

	public LogInPanel(RBundle bundle) {
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

		userNameLabel = new JLabel(resourceBundle.getString("userNameLabel"));
		passwordLabel = new JLabel(resourceBundle.getString("passwordLabel"));
		userNameText = new JTextField(10);
		passwordText = new JPasswordField(10);
		pleaseLabel = new JLabel(resourceBundle.getString("pleaseCredentials"));
		logInButton = new JButton(resourceBundle.getString("logInButton"));
		continueButton = new JButton(resourceBundle.getString("continueButton"));

		JPanel user = new JPanel();
		JPanel password = new JPanel();
		JPanel please = new JPanel();
		JPanel logIn = new JPanel();
		JPanel cont = new JPanel();
		JPanel logInPanel = new JPanel();
		user.add(userNameLabel);
		user.add(userNameText);
		password.add(passwordLabel);
		password.add(passwordText);
		please.add(pleaseLabel);
		logIn.add(logInButton);
		cont.add(continueButton);
		logInPanel.setLayout(new BoxLayout(logInPanel, BoxLayout.PAGE_AXIS));
		logInPanel.add(please);
		logInPanel.add(user);
		logInPanel.add(password);
		logInPanel.add(logIn);
		logInPanel.add(cont);

		this.setContentPane(logInPanel);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		this.setPreferredSize(new Dimension(350, 300));
		this.setTitle(resourceBundle.getString("loginTitle"));
		this.pack();
		this.setLocationRelativeTo(null);

	}

	@Override
	public void update() {
		menu.setText(resourceBundle.getString("language"));
		english.setText(resourceBundle.getString("english"));
		romanian.setText(resourceBundle.getString("romanian"));
		spanish.setText(resourceBundle.getString("spanish"));
		userNameLabel.setText(resourceBundle.getString("userNameLabel"));
		passwordLabel.setText(resourceBundle.getString("passwordLabel"));
		pleaseLabel.setText(resourceBundle.getString("pleaseCredentials"));
		logInButton.setText(resourceBundle.getString("logInButton"));
		continueButton.setText(resourceBundle.getString("continueButton"));
		this.setTitle(resourceBundle.getString("loginTitle"));
	}

	public String getUserNameText() {
		return userNameText.getText();
	}

	public String getPasswordText() {
		return String.valueOf(passwordText.getPassword());
	}

	public void addLogInButtonListener(ActionListener listener) {
		logInButton.addActionListener(listener);
	}

	public void addContinueButtonListener(ActionListener listener) {
		continueButton.addActionListener(listener);
	}

	public void addLanguageListener(ActionListener listener) {
		english.addActionListener(listener);
		romanian.addActionListener(listener);
		spanish.addActionListener(listener);
	}

	public void clearTextFields() {
		userNameText.setText("");
		passwordText.setText("");
	}

	public void showLogInErrorMessage() {
		JOptionPane.showMessageDialog(null, resourceBundle.getString("logInErrorMessage"),
				resourceBundle.getString("wrongCredentials"), JOptionPane.ERROR_MESSAGE);
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

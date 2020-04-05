package view;

import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInPanel extends JPanel {

	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JTextField userNameText;
	private JPasswordField passwordText;
	private JLabel pleaseLabel;
	private JButton logInButton;
	private JButton continueButton;

	public LogInPanel() {
		userNameLabel = new JLabel("User name   ");
		passwordLabel = new JLabel("Password    ");
		userNameText = new JTextField(20);
		passwordText = new JPasswordField(20);
		pleaseLabel = new JLabel("Please enter your credentials!");
		logInButton = new JButton("Log In");
		continueButton = new JButton("Continue without log in");

		JPanel user = new JPanel();
		JPanel password = new JPanel();
		JPanel please = new JPanel();
		JPanel logIn = new JPanel();
		JPanel cont = new JPanel();
		user.add(userNameLabel);
		user.add(userNameText);
		password.add(passwordLabel);
		password.add(passwordText);
		please.add(pleaseLabel);
		logIn.add(logInButton);
		cont.add(continueButton);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(please);
		this.add(user);
		this.add(password);
		this.add(logIn);
		this.add(cont);

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

	public void clearTextFields() {
		userNameText.setText("");
		passwordText.setText("");
	}
}

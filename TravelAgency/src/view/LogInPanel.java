package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInPanel extends JDialog {

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
		userNameText = new JTextField(10);
		passwordText = new JPasswordField(10);
		pleaseLabel = new JLabel("Please enter your credentials!");
		logInButton = new JButton("Log In");
		continueButton = new JButton("Continue without log in");

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
		this.setVisible(true);
		this.setPreferredSize(new Dimension(350, 300));
		this.setTitle("Log In");
		this.pack();
		this.setLocationRelativeTo(null);

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

	public void showLogInErrorMessage() {
		JOptionPane.showMessageDialog(null,
				"Error!\nInvalid username or password!\nTry again with correct credentials!", "Wrong credentials",
				JOptionPane.ERROR_MESSAGE);
	}

	public void closeWindow() {
		this.dispose();
	}
}

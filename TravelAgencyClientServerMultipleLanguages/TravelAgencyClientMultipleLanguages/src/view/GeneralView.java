package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.*;

import model.Observer;
import model.RBundle;

public class GeneralView extends JFrame implements Observer {

	private JButton calatorButton;
	private JButton angajatButton;
	private JButton administratorButton;
	private JLabel welcomeLabel;
	private JLabel pleaseLabel;
	private RBundle resourceBundle;
	private JMenu menu;
	private JMenuItem english;
	private JMenuItem romanian;
	private JMenuItem spanish;

	public GeneralView(RBundle bundle) {

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

		calatorButton = new JButton(resourceBundle.getString("calatorButton"));
		angajatButton = new JButton(resourceBundle.getString("angajatButton"));
		administratorButton = new JButton(resourceBundle.getString("administratorButton"));
		welcomeLabel = new JLabel(resourceBundle.getString("welcome"));
		pleaseLabel = new JLabel(resourceBundle.getString("please"));

		calatorButton.setFont(new Font("Arial", Font.BOLD, 16));
		angajatButton.setFont(new Font("Arial", Font.BOLD, 16));
		administratorButton.setFont(new Font("Arial", Font.BOLD, 16));
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
		pleaseLabel.setFont(new Font("Arial", Font.BOLD, 16));

		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
		p1.add(welcomeLabel);
		p1.add(Box.createVerticalStrut(40));
		p1.add(pleaseLabel);
		p1.add(Box.createVerticalStrut(10));
		p1.add(calatorButton);
		p1.add(Box.createVerticalStrut(10));
		p1.add(angajatButton);
		p1.add(Box.createVerticalStrut(10));
		p1.add(administratorButton);

		JPanel p2 = new JPanel();
		p2.setLayout(new GridBagLayout());
		p2.add(p1);

		this.setContentPane(p2);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(350, 300));
		this.setTitle(resourceBundle.getString("title"));
		this.pack();
		this.setLocationRelativeTo(null);
	}

	@Override
	public void update() {
		calatorButton.setText(resourceBundle.getString("calatorButton"));
		angajatButton.setText(resourceBundle.getString("angajatButton"));
		administratorButton.setText(resourceBundle.getString("administratorButton"));
		welcomeLabel.setText(resourceBundle.getString("welcome"));
		pleaseLabel.setText(resourceBundle.getString("please"));
		menu.setText(resourceBundle.getString("language"));
		english.setText(resourceBundle.getString("english"));
		romanian.setText(resourceBundle.getString("romanian"));
		spanish.setText(resourceBundle.getString("spanish"));
		this.setTitle(resourceBundle.getString("title"));
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

	public void addCalatorButtonListener(ActionListener listener) {
		calatorButton.addActionListener(listener);
	}

	public void addAngajatButtonListener(ActionListener listener) {
		angajatButton.addActionListener(listener);
	}

	public void addAdministratorButtonListener(ActionListener listener) {
		administratorButton.addActionListener(listener);
	}

	public void addWindowExitListener(WindowAdapter listener) {
		this.addWindowListener(listener);
	}

	public void addLanguageListener(ActionListener listener) {
		english.addActionListener(listener);
		romanian.addActionListener(listener);
		spanish.addActionListener(listener);
	}

}

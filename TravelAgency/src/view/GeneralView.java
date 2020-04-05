package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GeneralView extends JFrame {

	private JButton calatorButton;
	private JButton angajatButton;
	private JButton administratorButton;
	private JLabel welcomeLabel;
	private JLabel pleaseLabel;

	public GeneralView() {

		calatorButton = new JButton("Continue as traveller");
		angajatButton = new JButton("Log in as employee");
		administratorButton = new JButton("Log in as administrator");
		welcomeLabel = new JLabel("Welcome at Travel Agency app!");
		pleaseLabel = new JLabel("Please select an option!");

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
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(350, 300));
		this.setTitle("Travel Agency Application");
		this.pack();
		this.setLocationRelativeTo(null);
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
}

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JLabel loginTitleLabel;
	private JLabel selectUserLabel;
	private JButton quitBttn;

	public Login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		List<UserAccounts> users = Database.load_user_accounts();
		String[] usernames = new String[users.size()];
		// loop through users and get users
		for (int i = 0; i < usernames.length; i++) {
			usernames[i] = users.get(i).getUsername();
		}

		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBox = new JComboBox(usernames);
		comboBox.setBounds(160, 124, 105, 22);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAccounts newUser = users.get(comboBox.getSelectedIndex());
				// Displays correct page based off user selected
				if (newUser.getRole().equals("customer")) {
					ShoppingList customerPage = new ShoppingList(newUser);
					customerPage.setVisible(true);
					dispose();
				}
				if (newUser.getRole().equals("admin")) {
					AdminPage adminPage = new AdminPage(newUser);
					adminPage.setVisible(true);
					dispose();
				}

			}
		});
		btnNewButton.setBounds(160, 174, 89, 23);
		contentPane.add(btnNewButton);

		loginTitleLabel = new JLabel("Computer Accessories Shop");
		loginTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		loginTitleLabel.setBounds(40, 30, 373, 50);
		contentPane.add(loginTitleLabel);

		selectUserLabel = new JLabel("Select user:");
		selectUserLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectUserLabel.setBounds(80, 126, 89, 14);
		contentPane.add(selectUserLabel);

		quitBttn = new JButton("Quit");
		quitBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		quitBttn.setBounds(356, 229, 70, 23);
		contentPane.add(quitBttn);
	}
}

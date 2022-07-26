import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Product> stock = Database.load_stock();
		List<UserAccounts> users = Database.load_user_accounts();

		Login frame = new Login();
		frame.setVisible(true);
	}
}

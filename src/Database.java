import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
	private Customer customer;

	public static void save_stock(List<Product> products) {
		try {
			// write to file
			FileWriter myWriter = new FileWriter("Resources/Stock.txt");
			String newLines = "";
			for (Product product : products) {
				String newLine = "";
				newLine += product.getBarcode();
				if (product.getType().equals("mouse")) {
					newLine += ", " + ((Mouse) product).getType().toLowerCase();
				}
				if (product.getType().equals("keyboard")) {
					newLine += ", " + ((Keyboard) product).getType().toLowerCase();
				}
				newLine += ", " + product.getEdition();
				newLine += ", " + product.getBrand();
				newLine += ", " + product.getColour();
				newLine += ", " + product.getConnectivity();
				newLine += ", " + product.getQuantity_in_stock();
				newLine += ", " + product.getOriginal_cost();
				newLine += ", " + String.valueOf(product.getRetail_price());
				if (product.getType().equals("mouse")) {
					newLine += ", " + ((Mouse) product).getnumberOfButtons();
				}
				if (product.getType().equals("keyboard")) {
					newLine += ", " + ((Keyboard) product).getLayout();
				}
				newLines += newLine + "\n";

			}
			myWriter.write(newLines);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static List<Product> load_stock() {
		List<Product> productList = new ArrayList<Product>();

		try {
			// reads the file
			File Stock = new File("Resources/Stock.txt");
			Scanner read = new Scanner(Stock);
			while (read.hasNextLine()) {

				String array = read.nextLine();

				String[] split = array.split(", ");
				// creates the products
				if (split[1].equals("mouse")) {
					Mouse newProduct = new Mouse(split[0], split[1], split[2], split[3], split[4], split[5],
							Integer.parseInt(split[6]), Float.parseFloat(split[7]), Float.parseFloat(split[8]),
							Integer.parseInt(split[9]));
					productList.add(newProduct);
				}
				if (split[1].equals("keyboard")) {
					Keyboard newProduct = new Keyboard(split[0], split[1], split[2], split[3], split[4], split[5],
							Integer.parseInt(split[6]), Float.parseFloat(split[7]), Float.parseFloat(split[8]),
							split[9]);
					productList.add(newProduct);
				}

			}
			read.close();

		} catch (FileNotFoundException failed) {
			System.out.println("An error has occured.");
			failed.printStackTrace();
		}

		return productList;
	}

	List<Product> searchBasket = customer.getProductsInBasket();

	public static void removeValidItem(List<Product> searchBasket) {
		List<Product> renderList = load_stock();

		for (Product product : searchBasket) {
		}
		for (Product product : renderList) {
		}
		for (int i = 0; i < searchBasket.size(); i++) {
			for (int j = 0; j < renderList.size(); j++) {
				if (searchBasket.get(i).getBarcode().equals(renderList.get(j).getBarcode())) {
					renderList.get(j).setQuantity_in_stock(
							renderList.get(j).getQuantity_in_stock() - searchBasket.get(i).getQuantity_in_stock());
				}
			}
		}
		save_stock(renderList);
	}

	public static List<UserAccounts> load_user_accounts() {
		List<UserAccounts> userList = new ArrayList<UserAccounts>();
		// reads the user accounts
		try {
			File Accounts = new File("Resources/UserAccounts.txt");
			Scanner read = new Scanner(Accounts);
			while (read.hasNextLine()) {
				String arrayacc = read.nextLine();
				String[] splitacc = arrayacc.split(", ");
				if (splitacc[6].equals("admin")) {
					Admin newAccount = new Admin(splitacc[0], splitacc[1], splitacc[2], Integer.parseInt(splitacc[3]),
							splitacc[4], splitacc[5], splitacc[6]);
					userList.add(newAccount);
				}
				if (splitacc[6].equals("customer")) {
					Customer newAccount = new Customer(splitacc[0], splitacc[1], splitacc[2],
							Integer.parseInt(splitacc[3]), splitacc[4], splitacc[5], splitacc[6]);
					userList.add(newAccount);
				}
			}
			read.close();
		} catch (FileNotFoundException failed) {
			System.out.println("An error has occured.");
			failed.printStackTrace();
		}
		return userList;
	}
}
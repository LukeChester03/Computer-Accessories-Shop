import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ShoppingList extends JFrame {
	// globals
	private JPanel contentPane;
	private JTable ItemList;
	private JTextField searchBoxField;
	private JTable shoppingBasketTable;
	private JScrollPane scrollPane;
	private String askQuantity;
	private ArrayList<Product> searchBasket;
	private Customer customer;
	private DefaultTableModel shoppingTable;
	private JTextField PaypalEmailTextField;
	private JTextField longDigitTextField;
	private JTextField shortCodeTextField;
	private JLabel totalPriceDisplay;
	private JComboBox paymentComboBox;

	/* Create the frame. */
	public ShoppingList(UserAccounts newUser) {
		customer = (Customer) newUser;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1144, 566);
		contentPane = new JPanel();
		contentPane.setForeground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ItemList = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		ItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ItemList.setModel(tableModel);
		// set table column names
		tableModel.setColumnIdentifiers(new Object[] { "Barcode", "Type", "Edition", "Brand", "Colour", "Connectivity",
				"Quantity in Stock", "Original price", "Retail price", "Additional Features" });
		ItemList.setModel(tableModel);

		scrollPane = new JScrollPane(ItemList);
		scrollPane.setBounds(70, 51, 1050, 153);
		contentPane.add(scrollPane);

		searchBoxField = new JTextField();
		searchBoxField.setBounds(77, 20, 96, 20);
		contentPane.add(searchBoxField);
		searchBoxField.setColumns(10);

		JLabel searchLabel = new JLabel("Search:");
		searchLabel.setBounds(28, 23, 49, 14);
		contentPane.add(searchLabel);

		shoppingBasketTable = new JTable();
		shoppingBasketTable.setBounds(77, 214, 541, 73);
		shoppingTable = (DefaultTableModel) shoppingBasketTable.getModel();
		// Set basket column titles
		shoppingTable.setColumnIdentifiers(new Object[] { "Barcode", "Type", "Edition", "Brand", "Colour",
				"Connectivity", "Stock Selected", "Original price", "Retail price", "Additional Features" });
		contentPane.add(shoppingBasketTable);

		scrollPane = new JScrollPane(shoppingBasketTable);
		scrollPane.setBounds(70, 214, 1050, 73);
		contentPane.add(scrollPane);

		JLabel shoppingBasketLabel = new JLabel("Basket");
		shoppingBasketLabel.setBounds(10, 215, 49, 14);
		contentPane.add(shoppingBasketLabel);

		JButton addItemButton = new JButton("Add to Basket");
		addItemButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Load stock from database
				List<Product> stock = Database.load_stock();
				TableModel itemListTable = ItemList.getModel();
				DefaultTableModel basketTable = (DefaultTableModel) shoppingBasketTable.getModel();
				Object[] row = new Object[10];

				askQuantity = JOptionPane.showInputDialog("Please Enter the quantity you wish to purchase:");
				int index = ItemList.getSelectedRow();

				String quantitySelected = (String) itemListTable.getValueAt(index, 6);

				if (Integer.parseInt(askQuantity) > Integer.parseInt(quantitySelected)) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Sorry, there is not enough stock for this product. Please check stock and try again.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					Product newProd;
					// gets selected item information from table
					String barcode = itemListTable.getValueAt(index, 0).toString();
					String type = itemListTable.getValueAt(index, 1).toString();
					String edition = itemListTable.getValueAt(index, 2).toString();
					String productBrand = itemListTable.getValueAt(index, 3).toString();
					String productColour = itemListTable.getValueAt(index, 4).toString();
					String productConnectivity = itemListTable.getValueAt(index, 5).toString();
					int qInStock = Integer.parseInt(askQuantity);
					float productOriginalCost = Float.parseFloat(itemListTable.getValueAt(index, 7).toString());
					float productRetailPrice = Float.parseFloat(itemListTable.getValueAt(index, 8).toString());
					// creates a new product into basket
					if (itemListTable.getValueAt(index, 1).toString().equals("mouse")) {
						int numberOfBttns = Integer.parseInt(itemListTable.getValueAt(index, 9).toString());
						Mouse adminAddItem = new Mouse(barcode, type, edition, productBrand, productColour,
								productConnectivity, qInStock, productOriginalCost, productRetailPrice, numberOfBttns);
						newProd = adminAddItem;

					} else {
						String keyboardLayout = itemListTable.getValueAt(index, 9).toString();
						Keyboard adminAddItem = new Keyboard(barcode, type, edition, productBrand, productColour,
								productConnectivity, qInStock, productOriginalCost, productRetailPrice, keyboardLayout);
						newProd = adminAddItem;
					}
					// function calls
					customer.addToBasket(newProd);
					updateCustomerTable(tableModel);

				}

			}
		});
		addItemButton.setBounds(100, 298, 139, 23);
		contentPane.add(addItemButton);

		JButton clearBasketBttn = new JButton("Clear Basket");
		clearBasketBttn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				customer.clearBasket();
				updateCustomerTable(tableModel);
			}
		});
		clearBasketBttn.setBounds(249, 298, 131, 23);
		contentPane.add(clearBasketBttn);

		JLabel checkoutLabel = new JLabel("Checkout");
		checkoutLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkoutLabel.setBounds(28, 356, 96, 27);
		contentPane.add(checkoutLabel);

		JButton signOutBttn = new JButton("Sign Out");
		signOutBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Signs out back to login page
				Login backToLogin = new Login();
				dispose();
				backToLogin.setVisible(true);
			}
		});
		signOutBttn.setBounds(774, 19, 89, 23);
		contentPane.add(signOutBttn);

		JLabel customerportalLabel = new JLabel("Customer Portal");
		customerportalLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		customerportalLabel.setBounds(408, 0, 264, 20);
		contentPane.add(customerportalLabel);

		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update customer table with search results upon button press
				updateCustomerTable(tableModel);
			}
		});
		searchButton.setBounds(183, 17, 97, 23);
		contentPane.add(searchButton);

		String[] paymentType = { "PayPal", "Credit-Card" };
		paymentComboBox = new JComboBox(paymentType);
		paymentComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paymentComboBox.getSelectedItem().equals("PayPal")) {

					longDigitTextField.setEnabled(false);
					shortCodeTextField.setEnabled(false);
					PaypalEmailTextField.setEnabled(true);
				}
				if (paymentComboBox.getSelectedItem().equals("Credit-Card")) {
					longDigitTextField.setEnabled(true);
					shortCodeTextField.setEnabled(true);
					PaypalEmailTextField.setEnabled(false);
				}
			}
		});
		paymentComboBox.setBounds(28, 394, 80, 22);
		contentPane.add(paymentComboBox);

		JLabel stockLabel = new JLabel("Stock");
		stockLabel.setBounds(11, 52, 49, 14);
		contentPane.add(stockLabel);

		PaypalEmailTextField = new JTextField();
		PaypalEmailTextField.setBounds(134, 395, 96, 20);
		contentPane.add(PaypalEmailTextField);
		PaypalEmailTextField.setColumns(10);

		longDigitTextField = new JTextField();
		longDigitTextField.setBounds(249, 395, 96, 20);
		contentPane.add(longDigitTextField);
		longDigitTextField.setColumns(10);
		longDigitTextField.setEnabled(false);

		shortCodeTextField = new JTextField();
		shortCodeTextField.setBounds(249, 457, 96, 20);
		contentPane.add(shortCodeTextField);
		shortCodeTextField.setColumns(10);
		shortCodeTextField.setEnabled(false);

		JLabel lblNewLabel = new JLabel("PayPal");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(134, 356, 49, 27);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Credit Card");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(247, 349, 86, 14);
		contentPane.add(lblNewLabel_1);

		JButton checkoutButton = new JButton("Checkout");
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateCheckout() == true) {

				} else {
					Address address = customer.getaddress();
					JOptionPane.showMessageDialog(new JFrame("Purchase Successful"),
							"You have successfully bought items and paid £" + calculateTotalPrice() + ". " + "Sent to"
									+ " " + address.getHouseNumber() + ", " + address.getCity() + ", "
									+ address.getPostCode());
					checkout();
					updateCustomerTable(tableModel);
				}
			}
		});
		checkoutButton.setBounds(383, 370, 89, 23);
		contentPane.add(checkoutButton);

		JLabel lblNewLabel_2 = new JLabel("Total Price:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(376, 418, 96, 14);
		contentPane.add(lblNewLabel_2);

		totalPriceDisplay = new JLabel("");
		totalPriceDisplay.setBounds(455, 420, 49, 14);
		contentPane.add(totalPriceDisplay);

		JLabel lblNewLabel_3 = new JLabel("Credit Card Number");
		lblNewLabel_3.setBounds(248, 374, 132, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Security Code");
		lblNewLabel_4.setBounds(248, 432, 97, 14);
		contentPane.add(lblNewLabel_4);

		// get rid of original price
		ItemList.getColumnModel().getColumn(7).setMinWidth(0);
		ItemList.getColumnModel().getColumn(7).setMaxWidth(0);
		ItemList.getColumnModel().getColumn(7).setWidth(0);

		// get rid of original price in basket
		shoppingBasketTable.getColumnModel().getColumn(7).setMinWidth(0);
		shoppingBasketTable.getColumnModel().getColumn(7).setMaxWidth(0);
		shoppingBasketTable.getColumnModel().getColumn(7).setWidth(0);

		// calls function to update customer table
		updateCustomerTable(tableModel);

	}

	private void updateCustomerTable(DefaultTableModel tableModel) {

		List<Product> stock = checkStock();

		stock = bubbleSort(stock);

		tableModel.getDataVector().removeAllElements();
		// searches table by brand
		stock.removeIf(e -> (!e.getBrand().toLowerCase().contains(searchBoxField.getText().toLowerCase())));
		// loops through products and adds to table rows
		for (int i = 0; i < stock.size(); i++) {
			String[] row = new String[10];
			row[0] = stock.get(i).getBarcode();
			row[1] = stock.get(i).getType();
			row[2] = stock.get(i).getEdition();
			row[3] = stock.get(i).getBrand();
			row[4] = stock.get(i).getColour();
			row[5] = stock.get(i).getConnectivity();
			row[6] = Integer.toString(stock.get(i).getQuantity_in_stock());
			row[7] = Float.toString(stock.get(i).getOriginal_cost());
			row[8] = Float.toString(stock.get(i).getRetail_price());
			if (row[1].equals("mouse")) {
				row[9] = Integer.toString(((Mouse) stock.get(i)).getnumberOfButtons());
			}
			if (row[1].equals("keyboard")) {
				row[9] = ((Keyboard) stock.get(i)).getLayout();
			}

			tableModel.addRow(row);
		}
		tableModel.fireTableDataChanged();
		updateCustomerBasketTable(shoppingTable);
	}

	private void updateCustomerBasketTable(DefaultTableModel basketTable) {

		List<Product> stock = customer.getProductsInBasket();
		basketTable.getDataVector().removeAllElements();
		// get product information and add to basket
		for (int i = 0; i < stock.size(); i++) {
			String[] row = new String[10];
			row[0] = stock.get(i).getBarcode();
			row[1] = stock.get(i).getType();
			row[2] = stock.get(i).getEdition();
			row[3] = stock.get(i).getBrand();
			row[4] = stock.get(i).getColour();
			row[5] = stock.get(i).getConnectivity();
			row[6] = Integer.toString(stock.get(i).getQuantity_in_stock());
			row[7] = Float.toString(stock.get(i).getOriginal_cost());
			row[8] = Float.toString(stock.get(i).getRetail_price());
			if (row[1].equals("mouse")) {
				row[9] = Integer.toString(((Mouse) stock.get(i)).getnumberOfButtons());
			}
			if (row[1].equals("keyboard")) {
				row[9] = ((Keyboard) stock.get(i)).getLayout();
			}

			basketTable.addRow(row);
		}
		// clears basket when clear button pressed
		basketTable.fireTableDataChanged();
		totalPriceDisplay.setText(Float.toString(calculateTotalPrice()));

	}

	static List<Product> bubbleSort(List<Product> products) {

		Product temp;
		// loops through and compares products by retail price
		for (int i = 0; i < products.size(); i++) {
			for (int j = 1; j < (products.size() - i); j++) {
				if (products.get(j - 1).getRetail_price() > products.get(j).getRetail_price()) {
					// swap
					temp = products.get(j - 1);
					products.set(j - 1, products.get(j));
					products.set(j, temp);
				}
			}
		}
		return products;
	}

//If quantity of products in stock reaches 0, remove from table
	public List<Product> removeProduct() {
		List<Product> readProducts = Database.load_stock();
		List<Product> newProducts = new ArrayList<Product>();
		for (int i = 0; i < readProducts.size(); i++) {
			if (readProducts.get(i).getQuantity_in_stock() != 0) {
				newProducts.add(readProducts.get(i));
			}
		}

		return newProducts;
	}

	// Takes away from the shopping list if added to basket
	public List<Product> checkStock() {
		List<Product> searchList = Database.load_stock();

		for (Product products : searchList) {
			for (Product basketProducts : customer.getProductsInBasket()) {
				if (products.getBarcode().equals(basketProducts.getBarcode())) {
					products.setQuantity_in_stock(
							products.getQuantity_in_stock() - basketProducts.getQuantity_in_stock());
				}
			}
		}
		for (int i = 0; i < searchList.size(); i++) {
			if (searchList.get(i).getQuantity_in_stock() == 0) {
				searchList.remove(i);
				i = 0;
			}
		}
		return searchList;
	}

	// Checks barcode to see if it should add to same product
	public Product findBarcode(String compareBarcode) {
		List<Product> listOfProducts = removeProduct();

		for (int i = 0; i < listOfProducts.size(); i++) {
			if (listOfProducts.get(i).getBarcode().equals(compareBarcode)) {
				return listOfProducts.get(i);
			}
		}
		return null;
	}

	public float calculateTotalPrice() {
		List<Product> basketProducts = customer.getProductsInBasket();
		float totalPrice = 0;
		for (int i = 0; i < basketProducts.size(); i++) {
			totalPrice += basketProducts.get(i).getQuantity_in_stock() * basketProducts.get(i).getRetail_price();

		}
		return totalPrice;
	}

	public void checkout() {
		Database.removeValidItem(customer.getProductsInBasket());
		customer.clearBasket();
		updateCustomerBasketTable(shoppingTable);
	}

	// validate email function

	public boolean validateCheckout() {
		boolean validateFailed = false;
		if (paymentComboBox.getSelectedItem().equals("PayPal")) {
			// validate email
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
					+ "A-Z]{2,7}$";
			String email = PaypalEmailTextField.getText();
			Pattern pat = Pattern.compile(emailRegex);
			boolean matches = pat.matcher(email).matches();
			if (PaypalEmailTextField.getText().length() == 0) {
				JOptionPane.showMessageDialog(new JFrame(), "Please Fill in email field", "Error",
						JOptionPane.ERROR_MESSAGE);
				validateFailed = true;
				return validateFailed;
			}
			if (matches == false) {
				JOptionPane.showMessageDialog(new JFrame(), "Email Invalid", "Error", JOptionPane.ERROR_MESSAGE);
				validateFailed = true;
				return validateFailed;
			}
		} else {
			if (shortCodeTextField.getText().length() != 3) {
				JOptionPane.showMessageDialog(new JFrame(), "Security code must be 3 digits", "Error",
						JOptionPane.ERROR_MESSAGE);
				validateFailed = true;
				return validateFailed;
			} else
				validateFailed = false;
			if (shortCodeTextField.getText().length() == 0) {
				JOptionPane.showMessageDialog(new JFrame(), "Please fill in security code field.", "Error",
						JOptionPane.ERROR_MESSAGE);
				validateFailed = true;
				return validateFailed;
			} else
				validateFailed = false;
			try {
				Integer.parseInt(shortCodeTextField.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(new JFrame(), "Security code must be a number", "Error",
						JOptionPane.ERROR_MESSAGE);
				validateFailed = true;
				return validateFailed;
			}

			// validation for long digit text field
			if (longDigitTextField.getText().length() != 6) {
				JOptionPane.showMessageDialog(new JFrame(), "Credit card number must be 6 digits.", "Error",
						JOptionPane.ERROR_MESSAGE);
				validateFailed = true;
				return validateFailed;
			}
			if (longDigitTextField.getText().length() == 0) {
				JOptionPane.showMessageDialog(new JFrame(), "Credit card number must be 6 digits.", "Error",
						JOptionPane.ERROR_MESSAGE);
				validateFailed = true;
				return validateFailed;
			}
			try {
				Integer.parseInt(longDigitTextField.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(new JFrame(), "Card number must be a number", "Error",
						JOptionPane.ERROR_MESSAGE);

				validateFailed = true;
				return validateFailed;

			}
		}
		return false;
	}

}

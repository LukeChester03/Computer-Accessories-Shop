import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminPage extends JFrame {
//global calls
	private JPanel contentPane;
	private JTextField barcodeField;
	private JTextField brandField;
	private JTextField colourField;
	private JScrollBar scrollBar;
	private JTextField qInStockField;
	private JTextField originalCostField;
	private JTextField retailPriceField;
	private JLabel adminLabel;
	private JLabel barcodeLabel;
	private JLabel addItemTitleLabel;
	private JLabel brandLabel;
	private JButton adminAddProductBttn;
	private JLabel colourLabel;
	private JLabel connectivityLabel;
	private JLabel qInStockLabel;
	private JLabel originalCostLabel;
	private JLabel retailPriceLabel;
	private static JTable ItemList;
	private JTable AdminTable;
	private JScrollPane scrollPane;
	private JLabel productTypeLabel;
	private JTextField numOfBttnsField;
	private JComboBox productTypeComboBox;
	private JComboBox connectivityComboBox;
	private JComboBox layoutComboBox;
	private JComboBox mouseEditionComboBox;
	private JButton signOutBttnAdmin;
	private JTextField editionTextField;
	private List<Product> newStockList;

	public AdminPage(UserAccounts newUser) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1124, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		barcodeField = new JTextField();
		barcodeField.setBounds(119, 51, 96, 20);
		contentPane.add(barcodeField);
		barcodeField.setColumns(10);

		brandField = new JTextField();
		brandField.setBounds(119, 109, 96, 20);
		contentPane.add(brandField);
		brandField.setColumns(10);

		colourField = new JTextField();
		colourField.setBounds(119, 140, 96, 20);
		contentPane.add(colourField);
		colourField.setColumns(10);

		ItemList = new JTable();
		DefaultTableModel adminTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		ItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ItemList.setModel(adminTableModel);

		DefaultTableModel tableModel = (DefaultTableModel) ItemList.getModel();
		// Set table column titles
		tableModel.setColumnIdentifiers(new Object[] { "Barcode", "Type", "Edition", "Brand", "Colour", "Connectivity",
				"Quantity in Stock", "Original price", "Retail price", "Additional Features" });

		scrollPane = new JScrollPane(ItemList);
		scrollPane.setBounds(225, 53, 875, 259);
		contentPane.add(scrollPane);

		qInStockField = new JTextField();
		qInStockField.setBounds(119, 202, 96, 20);
		contentPane.add(qInStockField);
		qInStockField.setColumns(10);

		originalCostField = new JTextField();
		originalCostField.setBounds(119, 233, 96, 20);
		contentPane.add(originalCostField);
		originalCostField.setColumns(10);

		retailPriceField = new JTextField();
		retailPriceField.setBounds(119, 264, 96, 20);
		contentPane.add(retailPriceField);
		retailPriceField.setColumns(10);

		adminLabel = new JLabel("Admin Page");
		adminLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		adminLabel.setBounds(10, 0, 133, 27);
		contentPane.add(adminLabel);

		barcodeLabel = new JLabel("Barcode:");
		barcodeLabel.setBounds(30, 54, 79, 14);
		contentPane.add(barcodeLabel);

		addItemTitleLabel = new JLabel("Add New Item");
		addItemTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addItemTitleLabel.setBounds(109, 21, 106, 14);
		contentPane.add(addItemTitleLabel);

		brandLabel = new JLabel("Brand:");
		brandLabel.setBounds(30, 112, 79, 14);
		contentPane.add(brandLabel);

		adminAddProductBttn = new JButton("Add item");
		adminAddProductBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Declaring strings, taken from the fields input
				String type = productTypeComboBox.getSelectedItem().toString().toLowerCase();
				String productConnectivity = connectivityComboBox.getSelectedItem().toString();

				String keyboardLayout = layoutComboBox.getSelectedItem().toString();
				// validation
				if (barcodeField.getText().length() != 6 || !barcodeField.getText().matches("[0-9]+")) {
					JOptionPane.showMessageDialog(new JFrame(), "Barcode must be 6 numbers. Please try again.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String barcode = barcodeField.getText();

				if (brandField.getText().length() == 0) {
					JOptionPane.showMessageDialog(new JFrame(), "Please fill in brand field", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String productBrand = brandField.getText();
				if (colourField.getText().length() == 0) {
					JOptionPane.showMessageDialog(new JFrame(), "Please fill the colour field.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String productColour = colourField.getText();
				if (editionTextField.getText().length() == 0) {
					JOptionPane.showMessageDialog(new JFrame(),
							"If keyboard, only 'Standard, Flexible or Gaming' must be entered. If mouse, only 'Standard or Flexible' must be entered",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String edition = editionTextField.getText();
				try {
					Integer.parseInt(qInStockField.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Stock inputted is invalid. Please try again.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int qInStock = Integer.parseInt(qInStockField.getText());
				try {
					Float.parseFloat(originalCostField.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Original cost field is invalid. Please try again.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				float productOriginalCost = Float.parseFloat(originalCostField.getText());
				try {
					Float.parseFloat(retailPriceField.getText());
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Retail price field is invalid. Please try again.",
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				float productRetailPrice = Float.parseFloat(retailPriceField.getText());

				if (productTypeComboBox.getSelectedItem().equals("Mouse")) {
					try {
						Integer.parseInt(numOfBttnsField.getText());

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(new JFrame(),
								"Number of buttons is invalid. Please check and try again.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				if (productTypeComboBox.getSelectedItem().equals("Mouse")) {
					int numberOfBttns = Integer.parseInt(numOfBttnsField.getText());
					String[] checkAllFields = { barcode, type, edition, productBrand, productColour,
							productConnectivity, Integer.toString(qInStock), Float.toString(productOriginalCost),
							Float.toString(productRetailPrice), Integer.toString(numberOfBttns) };
					for (String x : checkAllFields) {
						if (x.equals("")) {
							JOptionPane.showMessageDialog(new JFrame(),
									"MOUSE All fields must be filled. Please check and try again.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				if (productTypeComboBox.getSelectedItem().equals("Keyboard")) {
					String[] checkAllFields2 = { barcode, type, edition, productBrand, productColour,
							productConnectivity, Integer.toString(qInStock), Float.toString(productOriginalCost),
							Float.toString(productRetailPrice), keyboardLayout };
					for (String x : checkAllFields2) {
						if (x.equals("")) {
							JOptionPane.showMessageDialog(new JFrame(),
									"KEYBOARD All fields must be filled. Please check and try again.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
				}
				// create the new product
				Product newProd;
				// Create new product based on whether mouse or keyboard
				if (productTypeComboBox.getSelectedItem().equals("Mouse")) {
					int numberOfBttns = Integer.parseInt(numOfBttnsField.getText());
					Mouse adminAddItem = new Mouse(barcode, type, edition, productBrand, productColour,
							productConnectivity, qInStock, productOriginalCost, productRetailPrice, numberOfBttns);
					newProd = adminAddItem;

				} else {
					Keyboard adminAddItem = new Keyboard(barcode, type, edition, productBrand, productColour,
							productConnectivity, qInStock, productOriginalCost, productRetailPrice, keyboardLayout);
					newProd = adminAddItem;

				}

				List<Product> stock = Database.load_stock();
				addItemToStock(newProd);

			}
		});
		// Button adds product parameters
		adminAddProductBttn.setBounds(398, 365, 116, 38);
		contentPane.add(adminAddProductBttn);
		// labels
		colourLabel = new JLabel("Colour:");
		colourLabel.setBounds(30, 143, 79, 14);
		contentPane.add(colourLabel);

		connectivityLabel = new JLabel("Connectivity:");
		connectivityLabel.setBounds(10, 174, 99, 14);
		contentPane.add(connectivityLabel);

		qInStockLabel = new JLabel("# in stock:");
		qInStockLabel.setBounds(20, 205, 89, 14);
		contentPane.add(qInStockLabel);

		originalCostLabel = new JLabel("Original Cost:");
		originalCostLabel.setBounds(10, 236, 99, 14);
		contentPane.add(originalCostLabel);

		retailPriceLabel = new JLabel("Retail Price:");
		retailPriceLabel.setBounds(10, 267, 116, 14);
		contentPane.add(retailPriceLabel);

		productTypeLabel = new JLabel("Product type:");
		productTypeLabel.setBounds(10, 298, 116, 14);
		contentPane.add(productTypeLabel);
		// product type combo box
		String[] productTypes = { "Mouse", "Keyboard" };
		productTypeComboBox = new JComboBox(productTypes);
		productTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// only show based on type selected
				if (productTypeComboBox.getSelectedItem().equals("Mouse")) {
					// disables keyboard selection
					layoutComboBox.setEnabled(false);
					numOfBttnsField.setEnabled(true);
				}
				if (productTypeComboBox.getSelectedItem().equals("Keyboard")) {
					numOfBttnsField.setEnabled(false);
					layoutComboBox.setEnabled(true);

				}

			}
		});

		productTypeComboBox.setBounds(119, 290, 96, 22);
		contentPane.add(productTypeComboBox);

		JLabel keyboardLabel = new JLabel("Keyboard");
		keyboardLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		keyboardLabel.setBounds(69, 343, 116, 19);
		contentPane.add(keyboardLabel);

		// keyboard parameters combo box
		String[] keyboardParameter = { "US", "UK" };
		layoutComboBox = new JComboBox(keyboardParameter);
		layoutComboBox.setBounds(79, 373, 106, 22);
		contentPane.add(layoutComboBox);
		layoutComboBox.setEnabled(false);

		JLabel mouseLabel = new JLabel("Mouse");
		mouseLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mouseLabel.setBounds(229, 343, 59, 19);
		contentPane.add(mouseLabel);
		// num of buttons text field
		numOfBttnsField = new JTextField();
		numOfBttnsField.setBounds(326, 374, 43, 20);
		contentPane.add(numOfBttnsField);
		numOfBttnsField.setColumns(10);

		JLabel layoutLabel = new JLabel("Layout:");
		layoutLabel.setBounds(30, 377, 49, 14);
		contentPane.add(layoutLabel);

		JLabel numOfBttnsLabel = new JLabel("Number of Buttons:");
		numOfBttnsLabel.setBounds(209, 377, 126, 14);
		contentPane.add(numOfBttnsLabel);
		// sign out button back to login page
		signOutBttnAdmin = new JButton("Sign Out");
		signOutBttnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login backToLogin = new Login();
				dispose();
				backToLogin.setVisible(true);
			}
		});

		signOutBttnAdmin.setBounds(983, 19, 89, 23);
		contentPane.add(signOutBttnAdmin);

		JLabel editionLabel = new JLabel("Edition:");
		editionLabel.setBounds(40, 87, 49, 14);
		contentPane.add(editionLabel);

		// connectivity combo box
		String[] connectivityString = { "wired", "wireless" };
		connectivityComboBox = new JComboBox(connectivityString);
		connectivityComboBox.setBounds(119, 171, 96, 22);
		contentPane.add(connectivityComboBox);

		// edition text field
		editionTextField = new JTextField();
		editionTextField.setBounds(119, 82, 96, 20);
		contentPane.add(editionTextField);
		editionTextField.setColumns(10);

		List<Product> a = Database.load_stock();
		Database.save_stock(a);
		updateAdminTable();
	}

	static void updateAdminTable() {
		DefaultTableModel tableModel = (DefaultTableModel) ItemList.getModel();
		List<Product> stock = bubbleSort();
		tableModel.getDataVector().removeAllElements();
		// loop through and get products
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
			// add to table row after loop
			tableModel.addRow(row);
		}

	}

	static List<Product> bubbleSort() {
		List<Product> products = Database.load_stock();
		Product temporary;
		// loop through products
		for (int i = 0; i < products.size(); i++) {
			// loop through products
			for (int j = 1; j < (products.size() - i); j++) {
				// compares to items to check if retail price bigger or smaller
				if (products.get(j - 1).getRetail_price() > products.get(j).getRetail_price()) {
					// swap
					temporary = products.get(j - 1);
					products.set(j - 1, products.get(j));
					products.set(j, temporary);
				}
			}
		}
		return products;
	}

	static void addItemToStock(Product newProd) {
		List<Product> stock = Database.load_stock();
		for (Product itemStock : stock) {
			// checks for matching barcode, if so then add to same item
			if (itemStock.getBarcode().equals(newProd.getBarcode())) {
				itemStock.setQuantity_in_stock(newProd.getQuantity_in_stock() + itemStock.getQuantity_in_stock());
				Database.save_stock(stock);
				updateAdminTable();
				return;

			}
		}
		stock.add(newProd);
		Database.save_stock(stock);
		updateAdminTable();
	}

}

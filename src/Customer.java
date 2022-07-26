import java.util.ArrayList;
import java.util.List;

public class Customer extends UserAccounts {

	List<Product> basket;

	// set constructors
	public Customer(String userID, String username, String name, int houseNumber, String postcode, String city,
			String role) {
		super(userID, username, name, houseNumber, postcode, city, "customer");

		this.basket = new ArrayList<Product>();
	}

	// getters and setters
	public void clearBasket() {
		basket.clear();

	}

	public List<Product> getProductsInBasket() {
		return basket;
	}

	public void addToBasket(Product product) {
		boolean productAlreadyInBasket = false;
		for (Product productInBasket : basket) {
			if (productInBasket.getBarcode().equals(product.getBarcode())) {
				productInBasket
						.setQuantity_in_stock(productInBasket.getQuantity_in_stock() + product.getQuantity_in_stock());
				productAlreadyInBasket = true;
			}
		}
		if (productAlreadyInBasket == false) {
			basket.add(product);
		}
	}
}

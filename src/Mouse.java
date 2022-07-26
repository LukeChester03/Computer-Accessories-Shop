
public class Mouse extends Product {

	private int numberOfButtons;

	// constructor
	public Mouse(String barcode, String type, String edition, String brand, String colour, String connectivity,
			int quantity_in_stock, float original_cost, float retail_price, int numberOfButtons) {
		super(barcode, type, edition, brand, colour, connectivity, quantity_in_stock, original_cost, retail_price);
		this.numberOfButtons = numberOfButtons;

	}

	// getters and setters
	public int getnumberOfButtons() {
		return numberOfButtons;
	}

	public void setnumberOfButtons(int numberOfButtons) {
		this.numberOfButtons = numberOfButtons;
	}

}

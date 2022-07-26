public class Keyboard extends Product {

	private String layout;

	// constructor
	public Keyboard(String barcode, String type, String edition, String brand, String colour, String connectivity,
			int quantity_in_stock, float original_cost, float retail_price, String layout) {
		super(barcode, type, edition, brand, colour, connectivity, quantity_in_stock, original_cost, retail_price);

		this.layout = layout;

	}

	// getters and setters
	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

}

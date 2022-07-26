
public abstract class Product {

	private String barcode;
	private String type;
	private String edition;
	private String brand;
	private String colour;
	private String connectivity;
	private int quantity_in_stock;
	private float original_cost;
	private float retail_price;

	// constructor
	public Product(String barcode, String type, String edition, String brand, String colour, String connectivity,
			int quantity_in_stock, float original_cost, float retail_price) {
		super();
		this.barcode = barcode;
		this.type = type;
		this.edition = edition;
		this.brand = brand;
		this.colour = colour;
		this.connectivity = connectivity;
		this.quantity_in_stock = quantity_in_stock;
		this.original_cost = original_cost;
		this.retail_price = retail_price;
	}

	// getters and setters
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getConnectivity() {
		return connectivity;
	}

	public void setConnectivity(String connectivity) {
		this.connectivity = connectivity;
	}

	public int getQuantity_in_stock() {
		return quantity_in_stock;
	}

	public void setQuantity_in_stock(int quantity_in_stock) {
		this.quantity_in_stock = quantity_in_stock;
	}

	public float getOriginal_cost() {
		return original_cost;
	}

	public void setOriginal_cost(float original_cost) {
		this.original_cost = original_cost;
	}

	public float getRetail_price() {
		return retail_price;
	}

	public void setRetail_price(float retail_price) {
		this.retail_price = retail_price;
	}
}

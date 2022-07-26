
public class Address {

	private int houseNumber;
	private String postcode;
	private String city;

	// constructor
	public Address(int houseNumber, String postCode, String city) {
		super();
		this.houseNumber = houseNumber;
		this.postcode = postCode;
		this.city = city;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public String getPostCode() {
		return postcode;
	}

	public String getCity() {
		return city;
	}

}

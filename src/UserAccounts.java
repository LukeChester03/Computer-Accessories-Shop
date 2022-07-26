
public class UserAccounts {
	private String userID;
	private String username;
	private String name;
	private String role;
	private Address address;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// constructor
	public UserAccounts(String userID, String username, String name, int houseNumber, String postcode, String city,
			String role) {
		super();
		this.userID = userID;
		this.username = username;
		this.name = name;
		this.role = role;
		this.address = new Address(houseNumber, city, postcode);

	}

	// getters and setters
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getaddress() {
		return address;
	}

}

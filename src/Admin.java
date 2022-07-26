
public class Admin extends UserAccounts {
	// set constructors
	public Admin(String userID, String username, String name, int houseNumber, String postcode, String city,
			String role) {
		super(userID, username, name, houseNumber, postcode, city, "admin");
	}

}

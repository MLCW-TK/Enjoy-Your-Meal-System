package enjoyyourmeal;

import java.util.HashMap;

public class StaffUser extends ClientUser {
	public StaffUser(String firstname, String lastname, String username, String password, String email,
			HashMap<String, String> contact) {
		super(firstname, lastname, username, password, email, contact);
		this.user_type = "Staff";
	}
	
	public StaffUser(String firstname, String lastname, String username, String password, String email) {
		super(firstname, lastname, username, password, email);
		this.user_type = "Staff";
	}
	
	public StaffUser(String firstname, String lastname, String username, String password,
			HashMap<String, String> contact) {
		super(firstname, lastname, username, password,contact);
		this.user_type = "Staff";
	}
	
	public StaffUser(String firstname, String lastname, String username, String password) {
		super(firstname, lastname, username, password);
		this.user_type = "Staff";
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof ClientUser){
			ClientUser c1 = (ClientUser) o; 
			return (c1.getUsername().equals(this.user_name));
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return 41*((user_name).length()+19);
	}
}

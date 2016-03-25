package commandinterface;

import users.*;

public class CommandList {
	// Login commands
	public ClientUser registerClient(String firstname, String lastname, String username, String password){
		ClientUser newUser = new ClientUser(firstname, lastname, username, password);
		return newUser;
	}
	
	public ClientUser login(String username, String password){
		for (ClientUser user : CommandConsole.re1.getUserList()){
			if (user.getUsername().equals(username) && user.getPassword().equals(password)){
				return user;
			}
		}
		
		throw new RuntimeException("No user found!");
	}
	
	public StaffUser insertChef(String firstname, String lastname, String username, String password){
		StaffUser newUser = new StaffUser(firstname, lastname, username, password);
		return newUser;
	}
	
	// ClientUser commands
	
}

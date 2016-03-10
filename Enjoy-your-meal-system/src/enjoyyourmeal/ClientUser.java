package enjoyyourmeal;

import java.util.HashMap;

public class ClientUser{
	private String first_name;
	private String last_name;
	protected String user_name;
	private String password;
	protected String email = "" ;
	private HashMap<String, String> contact = new HashMap<String,String>();;
	boolean receive_updates = false;
	private String receive_address = this.email;
	protected String user_type = "Client";
	
	public ClientUser(String firstname, String lastname, String username, String password, String email, HashMap<String,String> contact){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}
	
	public ClientUser(String firstname, String lastname, String username, String password, String email){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.contact = new HashMap<String,String>();
	}
	
	public ClientUser(String firstname, String lastname, String username, String password, HashMap<String,String> contact){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = "";
		this.contact = contact;;
	}
	
	public ClientUser(String firstname, String lastname, String username, String password){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = "";
		this.contact = new HashMap<String,String>();
		this.receive_address = email;
	}
	
	protected String getUsername(){
		return this.user_name; 
	}
	
	protected String getUsertype(){
		return this.user_type;
	}
	
	protected String getPassword(){
		return this.password;
	}
	
	protected String getEmail(){
		return this.email;
	}
	
	protected HashMap<String, String> getContactHash(){
		return contact;
	}
	
	protected String getContactKeys(){
		return this.contact.keySet().toString();
	}
	
	protected String getContactValue(String ContactKey){
		return contact.get(ContactKey);
	}
	
	public void setReceiveUpdates(boolean bool){
		this.receive_updates = bool;
	}
	
	public boolean getReceiveUpdates(){
		return this.receive_updates;
	}
	
	protected void setEmail(String email){
		this.email = email;
	}
	
	protected String getFirstname() {
		return first_name;
	}

	protected void setFirstname(String first_name) {
		this.first_name = first_name;
	}
	
	protected void setReceiveAddress(String receive_address){
		this.receive_address = receive_address;
	}
	
	protected String getReceiveAddress(){
		return this.receive_address;
	}
	
	protected String getLastname() {
		return last_name;
	}

	protected void setLastname(String last_name) {
		this.last_name = last_name;
	}
	
	public String toString(){
		String s = new String();
		s += "Name: " + this.first_name + " " + this.last_name;
		s += "\r\n";
		s += "Username: " + this.user_name;
		s += "\r\n";
		s += "Email: " + this.email;
		s += "\r\n";
		if (!this.contact.isEmpty()){
			for (String contacts: this.contact.keySet()){
				s += contacts + ": " + contact.get(contacts);
				s += "\r\n";
			}
		}
		s = s.substring(0, s.length()-2);
		return s;
		
	}
	
	// Equals and hash
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

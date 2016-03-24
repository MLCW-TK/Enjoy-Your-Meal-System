package commandinterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import coresystem.RestaurantSystem;
import ingredients.Ingredient;
import mealsystem.Meal;
import orders.Order;
import users.ClientUser;

public class CommandConsole{
	static RestaurantSystem re1 = new RestaurantSystem("our restaurant!");
	static ArrayList<Order> orders = new ArrayList<Order>();
	static HashSet<Meal> meals = new HashSet<Meal>();;
	static HashSet<Ingredient> ingredients;
	private static Scanner sc = new Scanner(System.in);
	private static CommandList cl = new CommandList();
	static ClientUser currentUser;
	static boolean loginPhase = true;
	static boolean loggedinPhase = false;
	
	
	public static void registerUser(String input) throws RuntimeException{
			String command = input.substring(16,input.length()-1);
			String[] data = command.split(",");
			
			if (data.length < 4){
				System.out.println("Please enter valid commands");
				throw new RuntimeException("Eg. registerClient <firstname,lastname,username,password>");				
			}
			
			for (int i = 0; i < 4; i++){
				if (data[i].substring(0, 1).equals(" ")){
					data[i] = data[i].substring(1, data[i].length());
				}
			}
			
			re1.addUser(cl.register_user(data[0], data[1], data[2], data[3]));
			System.out.println("User successfully created!");
			System.out.println("Press ENTER to continue");
			sc.nextLine();
	}
	
	public static void loginUser(String input){
			String command = input.substring(7,input.length()-1);
			String[] data = command.split(",");
			
			if (data.length < 2){
				System.out.println("Please enter valid commands");
				throw new RuntimeException("Eg. login <username,password>");
			}
			
			for (int i = 0; i < 2; i++){
				if (data[i].substring(0, 1).equals(" ")){
					data[i] = data[i].substring(1, data[i].length());
				}
			}
			
			try{
				cl.login(data[0], data[1]);
			} catch (RuntimeException e){
				throw new RuntimeException("Invalid username/password. Please try again");
			}
			
			loginPhase = false;
			loggedinPhase = true;
			currentUser = cl.login(data[0], data[1]);
	}
	
	public static void inputTreatment(String input){
		int strlength = input.length();
		String lastLetter = input.substring(strlength-1, strlength);
		
		
		if ((input.length()>=17)&&((input.substring(0,16)).equals("registerClient <"))&&(lastLetter.equals(">"))){
		registerUser(input);
		return;}
		
		if ((input.length()>=8)&&((input.substring(0,7).equals("login <"))&&(lastLetter.equals(">")))){
		loginUser(input);
		return;}
		
		System.out.println("Wrong inputs detected. Please try again.");
		System.out.println("");
	}

	
	
	public static void main(String[] args) throws Exception {
		String input;
		
		while (loginPhase) {
			System.out.println("Welcome to our restaurant!");
			System.out.println("What would you like to do?");
			System.out.println("To register, please enter registerClient <firstname, lastname, username, password>");
			System.out.println("To login, please enter login <username, password>");
			input = sc.nextLine();
						
			//Stop the loop
			if (input.equalsIgnoreCase("EXIT")){
				System.out.print("Thanks for dining with us!");
				return;}
			
			if (input.equals("")){
				System.out.println("Wrong inputs detected. Please try again.");
				System.out.println("");
				continue;
			}
			
			try{
				inputTreatment(input);	
			} catch (RuntimeException e){
				System.out.println(e.getMessage());
			}
		} while (loggedinPhase){
			System.out.println("Welcome, " + currentUser.getFirstname() + " " + 
				currentUser.getLastname() + "!" + " " + "(" + currentUser.getUsertype() + ")");
			sc.nextLine();
			
		}
	}
}

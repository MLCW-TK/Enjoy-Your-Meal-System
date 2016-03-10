package enjoyyourmeal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import enjoyyourmeal.Meals.Meals;

public class LoadSystem extends Thread {
	RestaurantSystem re1;
	ClientUser LoginUser;
	LinkedList<Meals> meals;
	
	public LoadSystem(String name){
		re1 = new RestaurantSystem(name);
		meals = new LinkedList<Meals>();
	}
	
	public void addStaff(StaffUser staff){
		re1.addUser(staff);
	}
	
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		while (!re1.exit){
			while (re1.getRegistrationPhase()){
				System.out.println("Welcome to " + re1.getRestaurantName() + " !");
				System.out.println("Please type 'register' to register a new user, or 'login' to create a new one");
				String input = sc.nextLine();		
				switch(input.toUpperCase()){
				case "REGISTER":
					re1.register();
					System.out.println("");
					break;
				case "LOGIN":
					System.out.println("Please enter your username:");
					String username = sc.nextLine();
					System.out.println("Please enter your password:");
					String password = sc.nextLine();
					LoginUser = re1.login(username, password);
					if (LoginUser == null){
						System.out.println("User not found. Please try again");
						System.out.println("");
						break;
					} else {
						re1.setRegistrationPhase(false);
						break;
					}
					
				default:
					System.out.println("Invalid input. Please try again");
					System.out.println("");
					continue;
				}
			}
			while (re1.getUserPhase()){
				// If is a staff
				if(LoginUser instanceof StaffUser){	
					//
					// Implementation of birthday here
					//
					System.out.println("Welcome, " + LoginUser.getFirstname() + " " + LoginUser.getLastname() + "!" + " (" + LoginUser.getUsertype() + ")");
					System.out.println("What would you like to do today? (Enter numbers)");
					System.out.println("1. Order a meal");
					System.out.println("2. View your orders");
					System.out.println("3. Checkout");
					System.out.println("4. Inserting a meal");
					System.out.println("5. Adding a special price offer");
					System.out.println("6. Removing a special price offer");
					System.out.println("7. Logout");
					System.out.println("8. Shutdown system");
					String input = sc.nextLine();
					switch(input){
					case "1":
						break;
					case "2":
						break;
					case "3":
						break;
					case "4":
						break;
					case "5":
						break;
					case "6":
						break;
					case "7":
						re1.setUserPhase(false);
						re1.setRegistrationPhase(true);
						System.out.println("You have logged out. Thank you for using Enjoy-Your-Meal-System");
						System.out.println("");
						break;
					case "8":
						break;
					default: 
						break;
					}
					
				} else{
					System.out.println("Welcome, " + LoginUser.getFirstname() + " " + LoginUser.getLastname() + "!" + " (" + LoginUser.getUsertype() + ")");
					System.out.println("What would you like to do today? (Enter numbers)");
					System.out.println("1. Order a meal");
					System.out.println("2. View your orders");
					System.out.println("3. Checkout");
					System.out.println("4. Logout");
					String input = sc.nextLine();
					switch(input){
					case "1":
						break;
					case "2":
						break;
					case "3":
						break;
					case "4":
						re1.setUserPhase(false);
						re1.setRegistrationPhase(true);
						System.out.println("You have logged out. Thank you for using Enjoy-Your-Meal-System");
						System.out.println("");
						break;
					default:
						break;
					}
				}
			}
		}
		
	}
}

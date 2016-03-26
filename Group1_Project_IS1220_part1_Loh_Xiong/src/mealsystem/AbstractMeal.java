package mealsystem;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import customutilities.CustomUtilities;
import ingredients.Ingredient;
import ingredients.IngredientFactory;

public abstract class AbstractMeal{
	String name;
	double totalIngredientsPrice;
	double extraIngredientsPrice;
	double price;
	String stringPrice;
	double specialPrice;
	String description = "";
	boolean specialOfferToggle = false;
	HashSet<Ingredient> ingredients = new HashSet<Ingredient>();
	MealBehavior behavior = new NormalBehavior();
	DecimalFormat df = new DecimalFormat("#.00"); 

	double default_price;
	static HashSet<Ingredient> default_ingredients;
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param ingredients
	 */
	public AbstractMeal(String name, String description, Ingredient ...ingredients){
		this.name = name;
		for (Ingredient obj : ingredients){
			this.ingredients.add(obj);
		}
		this.description = description;
		this.extraIngredientsPrice = 0;
		this.totalIngredientsPrice = updatePrices();
		this.price = this.totalIngredientsPrice;
		
		this.default_price = this.price;
		for (Ingredient obj : ingredients){
			IngredientFactory s = new IngredientFactory();
			final Ingredient ss = s.createIngredient(obj.getName(), obj.getQuantity(), obj.getPriceperquantity());
			default_ingredients.add(ss);
		}
	}
	
	/**
	 * constructor Meals, specifying the price
	 * @param name
	 * @param description
	 * @param price
	 * @param ingredients
	 */
	public AbstractMeal(String name, String description, double price, Ingredient ...ingredients){
		this.name = name;
		for (Ingredient obj : ingredients){
			this.ingredients.add(obj);
		}
		this.description = description;
		this.price = price;
		this.extraIngredientsPrice = 0;
		this.totalIngredientsPrice = updatePrices();
		
		this.default_price = this.price;
		this.default_ingredients = this.ingredients;
	}
	
	public AbstractMeal(String name, String description, double price, MealBehavior behavior, Ingredient ...ingredients){
		this.name = name;
		for (Ingredient obj : ingredients){
			this.ingredients.add(obj);
		}
		this.description = description;
		this.price = price;
		this.extraIngredientsPrice = 0;
		this.totalIngredientsPrice = updatePrices();
		this.behavior = behavior;
		
		this.default_price = this.price;
		this.default_ingredients = this.ingredients;
	}
	
	public AbstractMeal(String name, double price){
		this.name = name;
		this.price = price;
	}
	
	public void insertInitialIngredients(Ingredient e){
		this.ingredients.add(e);
	}
	
	/**
	 * getTotalIngredientPrice to 2 decimal places, i.e. to cents
	 * @return
	 */
	public double getTotalIngredientPrice(){
		return CustomUtilities.round(this.totalIngredientsPrice,2);
	}
	
	/**
	 * updatePrices by summing up prices of each ingredient.
	 * @return
	 */
	public double updatePrices(){
		double price = 0;
		for (Ingredient obj : this.ingredients){
			price += obj.getTotalprice();
		}
		return CustomUtilities.round(price,2);
	}
	
	public double updateSpecialPrices(double price){
		if (this.specialPrice + price < 0){
			return CustomUtilities.round(0,2);
		} else {
			double newPrice = this.specialPrice;
			return CustomUtilities.round((newPrice += price),2);
		}
	}
	
	public String getName() {
		return name;
	}

	/**
	 * getPrice of the meal
	 * else, return normal price with consideration of extra ingredients. 
	 * @return
	 */
	public double getPrice() {
			if (this.extraIngredientsPrice+this.price < this.price){
				return (CustomUtilities.round(this.price,2));
			} else {
				return CustomUtilities.round(extraIngredientsPrice+this.price,2);
			}
	}
	
	public String getStringPrice(){
		this.stringPrice = df.format(getPrice());
		return this.stringPrice;
	}
	
	/**
	 * set the price of the meal
	 * @param price
	 */
	public void setPrice(double price){
		this.price = price;
	}
	/** 
	 * returns special price if the meal is on special offer
	 * else return price;
	 * @return
	 */
	public double getSpecialPrice() {
		if (isSpecialOffer()){
			return CustomUtilities.round(specialPrice,2);
		} else {
			return getPrice();
		}
	}

	/**
	 * set special price only when the offer is on
	 * @param specialPrice
	 */
	public void setSpecialPrice(double specialPrice) {
		if (isSpecialOffer() && this.price>=specialPrice){
			this.specialPrice = specialPrice;
		} else {
			this.specialPrice = this.price;
		}
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * check if this meal is on special offer
	 * @return
	 */
	public boolean isSpecialOffer() {
		return specialOfferToggle;
	}

	/**
	 * set the special offer state to be true or false
	 * @param specialOffer
	 */
	public void setSpecialOfferToggle(boolean specialOffer) {
		this.specialOfferToggle = specialOffer;
	}

	/**
	 * get the HashSet of ingredients
	 * @return
	 */
	public HashSet<Ingredient> getIngredients() {
		return ingredients;
	}
	
	/**
	 * return a string of the names of ingredients
	 * @return
	 */
	public String getIngredientsString(){
		TreeMap<String, Double> s = new TreeMap<String, Double>();
		for (Ingredient obj : ingredients){
			s.put(obj.getName(), obj.getQuantity());
		}
		s.toString();

		return s.toString();
	}
	
	public void setBehavior(MealBehavior b){
		this.behavior = b;
	}
	
	public void executeBehavior(Ingredient ingredient, Integer quantity){
		this.behavior.behavior(this, ingredient, quantity);
	}
	
	/**
	 * add an extra ingredient
	 * @param ingredient
	 */
//	public void addIngredient(Ingredient ingredient){
//		if (ingredients.contains(ingredient)){
//			throw new RuntimeException("Ingredient already exist!");
//		} else {
//			ingredients.add(ingredient);
//			this.extraIngredientsPrice += CustomUtilities.round(ingredient.getTotalprice(),2);
//			this.totalIngredientsPrice += CustomUtilities.round(this.extraIngredientsPrice,2);
//		}
//	}
//	
//	
//	public void removeIngredient(Ingredient ingredient){
//		if (ingredients.contains(ingredient)){
//			ingredients.remove(ingredient);
//			this.extraIngredientsPrice -= CustomUtilities.round(ingredient.getTotalprice(),2);
//			this.totalIngredientsPrice -= CustomUtilities.round(this.extraIngredientsPrice,2);
//		} else {
//			throw new RuntimeException("Ingredient does not exist!");
//		}
//	}
//	
//	public void changeIngredientQuantity(Ingredient ingredient, double quantity){
//		if (ingredients.contains(ingredient)){
//			if (ingredient.getQuantity() + quantity < 0){
//				throw new RuntimeException("Quantity cannot be less than 0!");
//			} else {
//				double quantity_changed = quantity - ingredient.getQuantity();
//				ingredient.setQuantity(quantity);
//				this.extraIngredientsPrice += CustomUtilities.round((quantity_changed)*ingredient.priceperquantity,2);
//				this.totalIngredientsPrice += CustomUtilities.round(this.extraIngredientsPrice,2);
//			}
//			
//		} else {
//			throw new RuntimeException("Ingredient not found!");
//		}
//	}
//
//	public static double round(double value, int places) {
//	    if (places < 0) throw new IllegalArgumentException();
//
//	    BigDecimal bd = new BigDecimal(value);
//	    bd = bd.setScale(places, RoundingMode.HALF_UP);
//	    return bd.doubleValue();
//	}
	
	public String toString(){
		String s = new String();
		s = this.getName()+": "+this.getDescription()+"\n" + "Original price: " + this.getPrice()+"\n"
				+ "Special price: " +this.getSpecialPrice()+"\n\n";
		return s;
	}
	
	public void printIngredients(){
		String s = "Meal: "+ this.getName();
		s+="\nIngredients:\n";
		for(Ingredient ingredient : this.ingredients){
			s+=ingredient.getQuantity()+"g "+ ingredient.getName() + "\n";
		}
		s = s.substring(0,s.length()-1);
		System.out.println(s);
	}
	
	// Equals and hash
	/**
	 * Check if the name is the same
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof AbstractMeal){
			AbstractMeal c1 = (AbstractMeal) o; 
			return (c1.getName().equals(this.name) && c1.getClass().equals(o.getClass()));
		}
		return false;
	}
	
	/**
	 * overridden hashCode method
	 */	
	@Override
	public int hashCode(){
    	int code = 0;
        for (int i=0; i < this.name.length(); i++){
        	char c = this.name.charAt(i);
        	int h = 41+((int)c+i)*(19+i);
        	code += h;
        }
		return code;
	}
	
}

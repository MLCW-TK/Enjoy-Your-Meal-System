package mealsystem;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.TreeMap;

import customutilities.CustomUtilities;
import ingredients.Ingredient;
import ingredients.IngredientFactory;

/**
 * AbstractMeal class
 * @author Xiong
 *
 */
public abstract class AbstractMeal{
	private String name;
	double totalIngredientsPrice;
	double extraIngredientsPrice;
	private double price;
	private String stringPrice;
	private double specialPrice;
	private String description = "";
	private boolean specialOfferToggle = false;
	private HashSet<Ingredient> ingredients = new HashSet<Ingredient>();
	private MealBehavior behavior = new NormalBehavior();
	private DecimalFormat df = new DecimalFormat("#.00"); 
	private Boolean personalized = false;
	private int asItIsCount = 0;
	private int asModifiedCount = 0;
	private int justOnSaleCount = 0;
	
	private double default_price;
	private final HashSet<Ingredient> default_ingredients = new HashSet<Ingredient>();
	
	/**
	 * AbstractMeal constructor 
	 * @param name
	 * @param description
	 * @param ingredients
	 */
	public AbstractMeal(String name, String description, Ingredient ...ingredients){
                this.setName(name);
		for (Ingredient obj : ingredients){
			this.getIngredients().add(obj);
		}
		this.setDescription(description);
		this.setExtraingredientsprice(0);
		this.setTotalingredientsprice(updatePrices());
		this.setPrice(this.getTotalIngredientPrice());
		
		this.setDefault_price(this.getPrice());
//		for (Ingredient obj : ingredients){
//			IngredientFactory s = new IngredientFactory();
//			final Ingredient ss = s.createIngredient(obj.getName(), obj.getQuantity(), obj.getPriceperquantity());
//			this.getDefault_ingredients().add(ss);
//		}
		
		this.default_ingredients.addAll(this.getIngredients()); 
	}

	/**
	 * constructor Meals, specifying the price
	 * @param name
	 * @param description
	 * @param price
	 * @param ingredients
	 */
	public AbstractMeal(String name, String description, double price, Ingredient ...ingredients){
        this.setName(name);
		for (Ingredient obj : ingredients){
			this.getIngredients().add(obj);
		}
		this.setDescription(description);
		this.setPrice(price);
		this.setExtraingredientsprice(0);
		this.setTotalingredientsprice(updatePrices());

		this.setDefault_price(this.getPrice());
		this.default_ingredients.addAll(this.ingredients);
		
		
	}
	

	/**
	 * AbstractMeal constructor
	 * @param name
	 * @param description
	 * @param price
	 * @param behavior
	 * @param ingredients
	 */
	public AbstractMeal(String name, String description, double price, MealBehavior behavior, Ingredient ...ingredients){
		this.setName(name);
		for (Ingredient obj : ingredients){
			this.ingredients.add(obj);
		}
		this.setDescription(description);
		this.setPrice(price);
		this.setExtraingredientsprice(0);
		this.setTotalingredientsprice(this.updatePrices());
		this.setBehavior(behavior);
		
		this.setDefault_price(this.getPrice());
		this.default_ingredients.addAll(this.ingredients);
	}
	
	/**
	 * AbstractMeal constructor
	 * @param name
	 * @param price
	 */
	public AbstractMeal(String name, double price){
		this.setName(name);
		this.setPrice(price);
	}
		
	
	/**
	 * default_price setter
	 * @param price2
	 */
	private void setDefault_price(double price2) {
            this.default_price = price2;
		
	}

	/**
	 * Totalingredientsprice setter
	 * @param updatePrices
	 */
	private void setTotalingredientsprice(double updatePrices) {
            this.totalIngredientsPrice = updatePrices;	
	}

	/**
	 * Extraingredientsprice setter
	 * @param i
	 */
	private void setExtraingredientsprice(int i) {
            this.extraIngredientsPrice = i;
	}

	/**
	 * name setter
	 * @param name2
	 */
	private void setName(String name2) {
            this.name = name2;	
	}

	/**
	 * add an ingredient to the meal
	 * @param e
	 */
	public void insertInitialIngredients(Ingredient e){
		this.ingredients.add(e);
	}
	
	
	/**
	 * create an identical instance of this AbstractMeal instance
	 * @return
	 */
	public AbstractMeal createNewInstance(){
		Ingredient[] editedIngredients = new Ingredient[this.getIngredients().size()];
		this.getIngredients().toArray(editedIngredients);
		AbstractMeal meal = new Meal(this.getName(), this.getDescription(), this.getPrice(), this.getBehavior(), editedIngredients);
		return meal;
	}
	
	/**
	 * behavior getter
	 * @return
	 */
	private MealBehavior getBehavior() {return this.behavior;}

	/**
	 * finalDefaultIngredients setter
	 * 
	 */
	public void setFinalDefaultIngredients(){
		this.setDefault_price(this.getPrice());
		if (!this.getIngredients().isEmpty()){
			for (Ingredient obj : this.getIngredients()){
				IngredientFactory s = new IngredientFactory();
				final Ingredient ss = s.createIngredient(obj.getName(), obj.getQuantity());
				default_ingredients.add(ss);
			}
		}
	}
	
	/**
	 * personalizedBoll setter
	 * @param bool
	 */
	public void setPersonalizedBool(Boolean bool){
		this.personalized = bool;
	}
	
	/**
	 * personalizedBool getter
	 * @return
	 */
	public Boolean getPersonalizedBool(){
		return this.personalized;
	}
	
	/**
	 * totalIngredientPrice getter
	 * to 2 decimal places
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
	
	/**
	 * update the special price by adding a given price
	 * the lowest value returned is 0.00
	 * @param price
	 * @return
	 */
	public double updateSpecialPrices(double price){
		if (this.specialPrice + price < 0){
			return CustomUtilities.round(0,2);
		} else {
			double newPrice = this.specialPrice;
			return CustomUtilities.round((newPrice += price),2);
		}
	}
	
	/**
	 * name getter
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * get Price of the meal
	 * if not specified, return normal price with consideration of extra ingredients. 
	 * @return
	 */
	public double getPrice() {
			if (this.extraIngredientsPrice+this.price < this.price){
				return (CustomUtilities.round(this.price,2));
			} else {
				return CustomUtilities.round(extraIngredientsPrice+this.price,2);
			}
	}
	
	/**
	 * return the price as a String
	 * @return
	 */
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
		if (this.isSpecialOffer()){
			return CustomUtilities.round(this.specialPrice,2);
		} else {
			return getPrice();
		}
	}

	/**
	 * set special price only when the offer is on
	 * @param specialPrice
	 */
	public void setSpecialPrice(double specialPrice) {
		if (isSpecialOffer() && this.getPrice()>=specialPrice){
			this.specialPrice = specialPrice;
		} else {
			this.specialPrice = this.getPrice();
		}
	}


	/**
	 * description getter
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}


	/**
	 * description setter
	 * @param description
	 */
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
		String ss = s.toString();
		String items[] = ss.split(",");
		items[0] = items[0].substring(1, items[0].length());
		items[items.length-1] = items[items.length-1].substring(0, items[items.length-1].length()-1);
		
		String output = new String();
		output += "(";
		for (String elem : items){
			output += elem + "g, ";
		}
		output = output.substring(0, output.length()-2);
		output += ")";
		return output;
	}
	
	/**
	 * behavior setter
	 * @param b
	 */
	public void setBehavior(MealBehavior b){
		this.behavior = b;
	}
	
	/**
	 * execute the behavior
	 * @param ingredient
	 * @param quantity
	 */
	public void executeBehavior(Ingredient ingredient, Integer quantity){
		this.behavior.behavior(this, ingredient, quantity);
	}
	

	/**
	 * print a summary of this AbstractMeal object
	 */
	public String toString(){
		String s = new String();
		s = this.getName()+": "+this.getDescription()+"\n" + "Original price: " + this.getPrice()+"\n"
				+ "Special price: " +this.getSpecialPrice()+"\n\n";
		return s;
	}
	
	/**
	 * print the ingredients
	 */
	public void printIngredients(){
		String s = "Meal: "+ this.getName();
		s+="\nIngredients:\n";
		for(Ingredient ingredient : this.getIngredients()){
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
			return (c1.getName().equals(this.getName()) && c1.getClass().equals(this.getClass()));
		}
		else{return false;}
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

	/**
	 * extraIngredientPrice getter
	 * @return
	 */
	public double getextraIngredientsPrice() {
		return this.extraIngredientsPrice;
	}

	/**
	 * defaultPrice getter
	 * @return
	 */
	public double getDefaultprice() {
		return this.default_price;
	}

	/**
	 * ingredients setter
	 * @param hashSet
	 */
	public void setIngredients(HashSet<Ingredient> hashSet) {
		this.ingredients = hashSet;
		
	}

	/**
	 * ingredients setter
	 * @return
	 */
	public HashSet<Ingredient> getDefault_ingredients() {
		return this.default_ingredients;
	}

	/**
	 * AsItisCount getter
	 * @return
	 */
	public int getAsItIsCount() {
		return asItIsCount;
	}

	/**
	 * asItIsCount setter
	 * @param asItIsCount
	 */
	public void setAsItIsCount(int asItIsCount) {
		this.asItIsCount = asItIsCount;
	}

	/**
	 * asModifiedCoiunt getter
	 * @return
	 */
	public int getAsModifiedCount() {
		return asModifiedCount;
	}

	/**
	 * asModifiedCount setter
	 * @param asModifiedCount
	 */
	public void setAsModifiedCount(int asModifiedCount) {
		this.asModifiedCount = asModifiedCount;
	}

	/**
	 * justOnSaeCount getter
	 * @return
	 */
	public int getJustOnSaleCount() {
		return justOnSaleCount;
	}

	/**
	 * justOnSaleCount setter
	 * @param justOnSaleCount
	 */
	public void setJustOnSaleCount(int justOnSaleCount) {
		this.justOnSaleCount = justOnSaleCount;
	}
	
}

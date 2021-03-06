package mealsystem;


import ingredients.Ingredient;

/**
 * AbstractMealFactory class
 * @author Xiong
 *
 */
public abstract class AbstractMealFactory {
	/**
	 * 
	 * Requirement stipulated by our system is that information must be complete before the item
	 * can be created. If certain information is missing (i.e. author name), illegal item_type
	 * exception would be thrown.
	 * @param name
	 * @param description
	 * @param ingredients
	 * @return
	 */
	public abstract AbstractMeal createMeal(String name, String description, Ingredient ...ingredients);
	public abstract AbstractMeal createMeal(String name, String description, double price, Ingredient ...ingredients);
	public abstract AbstractMeal createMeal(String name, double price);

}
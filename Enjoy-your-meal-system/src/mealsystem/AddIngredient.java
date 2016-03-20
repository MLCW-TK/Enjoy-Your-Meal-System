package mealSystem;
import customUtilities.*;
import ingredients.Ingredient;


public class AddIngredient implements MealBehavior{
	public void behavior(Meal meal, Ingredient ingredient, double quantity){
		if (meal.ingredients.contains(ingredient)){
			throw new RuntimeException("Ingredient already exist!");
		} else {
			meal.ingredients.add(ingredient);
			meal.extraIngredientsPrice += CustomUtilities.round(ingredient.getTotalprice(),2);
			meal.totalIngredientsPrice += CustomUtilities.round(meal.extraIngredientsPrice,2);
		}
	}
}

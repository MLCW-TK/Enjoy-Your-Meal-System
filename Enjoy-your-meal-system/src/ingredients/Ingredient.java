package ingredients;

import java.math.BigDecimal;
import CustomUtilities.CustomUtilities;

public class Ingredient {
	public String name;
	public double quantity;
	public double priceperquantity;
	public double totalprice;
	public double original_quantity;

	
	public Ingredient(String name, double priceperquantity){
		this.name = name;
		this.quantity = 1;
		this.priceperquantity = priceperquantity;
		this.totalprice = CustomUtilities.round(this.quantity * this.priceperquantity,2);
		this.original_quantity = this.quantity;
	}
	
	public Ingredient(String name, double quantity, double priceperquantity){
		this.name = name;
		this.quantity = quantity;
		this.priceperquantity = priceperquantity;
		this.totalprice = CustomUtilities.round(this.priceperquantity * this.quantity,2);
		this.original_quantity = this.quantity;

		
		if (this.quantity < 0){
			throw new RuntimeException("Quantity cannot be less than 0");
		}
	}
	
	public double getOriginalQuantity(){
		return original_quantity;
	}
	
	public void addQuantity(double quantity){
		this.quantity = quantity;
		this.totalprice = CustomUtilities.round(priceperquantity * this.quantity,2);
	}
	
	public void removeQuantity(double quantity){
		if (this.quantity-quantity < 0){
			throw new RuntimeException("Quantity cannot be less than 0");
		} else {
			this.quantity = quantity;
			this.totalprice = CustomUtilities.round(priceperquantity * this.quantity,2);
		}
	}

	public double getPriceperquantity() {
		return priceperquantity;
	}

	public void setPriceperquantity(double priceperquantity) {
		this.priceperquantity = priceperquantity;
	}

	public double getTotalprice() {
		this.totalprice = CustomUtilities.round(this.quantity*this.priceperquantity,2);
		return totalprice;
	}

	protected void setTotalprice(double totalprice) {
		this.totalprice = CustomUtilities.round(totalprice,2);
	}

	protected void setName(String name) {
		this.name = name;
	}

	public void setQuantity(double quantity) {
		if (quantity >= 0){
			this.quantity = quantity;
			this.totalprice = CustomUtilities.round(this.quantity * this.priceperquantity,2);
		} else {
			throw new RuntimeException("Quantity cannot be lesser than 0!");
		}
	}

	public String getName(){
		return this.name;
	}
	
	public double getQuantity(){
		return this.quantity;
	}
	
	
	
	// Equals and hash
	/**
	 * Check if the name is the same
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Ingredient){
			Ingredient c1 = (Ingredient) o; 
			return (c1.getName().equals(this.name));
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
		return (code + (int) this.quantity);
	}
	
	
}

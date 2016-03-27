package cardfidelitysystem;

import java.util.Random;

import users.User;


/**
 * Lottery fidelity card
 *	
 */
public class LotteryFidelityCard extends FidelityCard{
	Random rand = new Random();
	int percentage;
	
	/**
	 * 
	 * @param owner: the user
	 * @param percentage: % chance of striking "lottery"
	 */
	public LotteryFidelityCard(User owner, int percentage){
		super(owner, percentage);
		name = "BasicFidelityCard";
	}
	
	public LotteryFidelityCard(User owner){
		super(owner);
		this.percentage = 5;
		name = "LotteryFidelityCard";
	}
	
	public void setPercentage(int percentage){
		this.percentage = percentage;
	}

	/**
	 * This use feature rolls between 1 and 100.
	 * If your number that you get is < than the percentage set
	 * You get a free meal
	 */
	@Override
	public boolean useFeature() {
		int roll = rand.nextInt(100) + 1;
		if (roll < percentage){
			return true;
		} else {
			return false;
		}
	}

}

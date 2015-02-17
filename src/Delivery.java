public class Delivery {
	public Drink coffee = null;
	public Drink tea = null;
	public int returnedCoins = 0;
	
	// added for Umple model
	public void cupOfCoffee(Drink d){
		coffee = d;
	}
	public void cupOfTea(Drink d) {
		tea = d;
	}
}

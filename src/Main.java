
public class Main {
	public static Delivery del = new Delivery();
	public static CoffeeMachine cm = new CoffeeMachine(del);
	public static User user = new User(cm, del);
	public static void main ( String[] args ) {
		new Thread(user).start();
	}
}
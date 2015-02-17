public class User implements Runnable {
	private CoffeeMachine myCM; Delivery d;
	public User(CoffeeMachine m, Delivery del){
		myCM = m;  d = del;
	}
	public void run(){
		while (true) {
			myCM.insert(50);
			myCM.coffee();
			try {Thread.sleep(2000);} catch(InterruptedException ie){}
			if (d.coffee != null){
				// drink coffee
				d.coffee = null;
				System.out.println("got a coffee ");
			} else {System.out.println("there is no drink ");}
			
		}
	}
}

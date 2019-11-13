package space.arim.estimator;

public class Main {

	public static void main(String[] args) {
		/*
		(new Thread(() -> {
			(new User()).run();
		})).start();
		*/
		Estimator user = new Estimator(System.in, System.out);
		user.run();
		user.stop();
	}

}

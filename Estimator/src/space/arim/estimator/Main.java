package space.arim.estimator;

public class Main {

	public static void main(String[] args) {
		UserDisplay user = new UserDisplay(System.in, System.out, 4);
		user.run();
		user.stop();
	}

}

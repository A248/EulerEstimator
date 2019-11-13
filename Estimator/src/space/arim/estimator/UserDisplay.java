package space.arim.estimator;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UserDisplay {
	
	private final Scanner scanner;
	
	private final PrintStream output;
	
	private final int precision;
	
	private static final String invalid_point = "Please enter an ordered pair without parentheses, e.g. \"10,0.1\".";
	
	public UserDisplay(InputStream input, PrintStream output, int precision) {
		scanner = new Scanner(input);
		this.output = output;
		this.precision = precision;
	}
	
	private void say(String message) {
		output.println(message);
	}
	
	private String getNext() {
		String input = scanner.next();
		if (input.equalsIgnoreCase("STOP") || input.equalsIgnoreCase("EXIT")) {
			say("Shutting down...");
			stop();
		}
		return input;
	}
	
	public void run() {
		
		say("Welcome to the Estimator by Anand Beh!");
		say("\n");
		say("Please input an equation for dy/dx in terms of x and y.");
		say("For example, stating \"x + y\" would determine that dy/dx = x + y");
		
		String function = getNext();
		
		say("\n\nSpecified function: dy/dx =" + function + "\n");
		say("Please state:\n1.) The number of times you wish to iterate.\n2.) The step between subsequent x values when iterating.");
		
		say("Enter these as a pair without parentheses, e.g. 10,0.1.");
		int iterations = 0;
		double step = 0D;
		while (iterations <= 0 || step <= 0D) {
			String inputs = getNext();
			if (inputs.contains(",")) {
				String[] input = inputs.split(",");
				try {
					iterations = Integer.parseInt((input[0]));
					step = Double.parseDouble((input[1]));
				} catch (NumberFormatException ex) {
					say(invalid_point);
				}
			} else {
				say(invalid_point);
			}
		}
		
		say("Please state the initial point from which to calculate, such as 0,2.");
		double x = Double.POSITIVE_INFINITY;
		double y = Double.NEGATIVE_INFINITY;
		while (x == Double.POSITIVE_INFINITY || y == Double.NEGATIVE_INFINITY) {
			String inputs = getNext();
			if (inputs.contains(",")) {
				String[] input = inputs.split(",");
				try {
					x = Double.parseDouble((input[0]));
					y = Double.parseDouble((input[1]));
				} catch (NumberFormatException ex) {
					say(invalid_point);
				}
			} else {
				say(invalid_point);
			}
		}
		
		say("Ready to begin iteration? y/n");
		while (!ready(getNext())) {
			say("Looks like you aren't ready. How about now?");
		}
		long prev = System.nanoTime();
		EulerApproximator diffEQ = new EulerApproximator(function, x, y);
		say("Format:\n     x    |     y    |    dy/dx");
		spitLine(diffEQ.initialX(), diffEQ.initialY(), diffEQ.calculateDerivative());
		DoubleTriplet[] results = diffEQ.approximations(step, iterations);
		for (DoubleTriplet triplet : results) {
			spitLine(triplet.value1(), triplet.value2(), triplet.value3());
		}
		say("Finished in " + ((System.nanoTime() - prev)/(1000_000_000D)) + " seconds.");
	}
	
	private void spitLine(double x, double y, double derivative) {
		
		say(String.format("%." + precision + "f", x) + "    |    " + String.format("%." + precision + "f", y) + "    |    " + String.format("%." + precision + "f", derivative));
		
	}

	private boolean ready(String answer) {
		switch (answer.toLowerCase()) {
		case "yes":
			return true;
		case "okay":
			return true;
		case "ok":
			return true;
		case "y":
			return true;
		default:
			return Boolean.parseBoolean(answer);
		}
	}
	
	public void stop() {
		scanner.close();
		output.close();
	}
}

package space.arim.estimator;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class User {
	
	private final Scanner scanner;
	
	private final PrintStream output;
	
	private final int precision;
	
	public User(InputStream input, PrintStream output) {
		scanner = new Scanner(input);
		this.output = output;
		precision = 4;
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
		say("Please state:\n1.) The step between subsequent x values when iterating.\n2.) The number of times you wish to iterate.");
		say("The step value:");
		double step = 0D;
		while (step <= 0D) {
			try {
				step = Double.parseDouble(getNext());
				if (step == 0D) {
					say("Please enter a non-zero decimal!");
				}
			} catch (NumberFormatException ex) {
				say("Please enter a valid non-zero decimal!");
			}
		}
		say("\nThe number of times to iterate:");
		int iterations = 0;
		while (iterations <= 0) {
			try {
				iterations = Integer.parseInt(getNext());
				if (iterations <= 0) {
					say("Please enter a positive integer!");
				}
			} catch (NumberFormatException ex) {
				say("Please enter a valid positive integer!");
			}
		}
		say("Please state the initial x value from which to calculate.");
		double x = Double.POSITIVE_INFINITY;
		while (x == Double.POSITIVE_INFINITY) {
			try {
				x = Double.parseDouble(getNext());
			} catch (NumberFormatException ex) {
				say("Please enter a valid decimal!");
			}
		}
		say("Please state the initial y value from which to calculate.");
		double y = Double.NEGATIVE_INFINITY;
		while (y == Double.NEGATIVE_INFINITY) {
			try {
				y = Double.parseDouble(getNext());
			} catch (NumberFormatException ex) {
				say("Please enter a valid decimal!");
			}
		}
		say("Ready to begin iteration?");
		while (!ready(getNext())) {
			say("Looks like you aren't ready. How about now?");
		}
		EulerApproximator diffEQ = new EulerApproximator(function, x, y);
		say("Format:\n     x    |     y    |    dy/dx");
		spitLine(diffEQ.initialX(), diffEQ.initialY(), diffEQ.calculateDerivative());
		DoubleTriplet[] results = diffEQ.approximations(step, iterations);
		for (DoubleTriplet triplet : results) {
			spitLine(triplet.value1(), triplet.value2(), triplet.value3());
		}
		say("Finished!");
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

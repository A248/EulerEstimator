/* 
 * EulerEstimator
 * Copyright © 2019 Anand Beh <https://www.arim.space>
 * 
 * EulerEstimator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * EulerEstimator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with EulerEstimator. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU General Public License.
 */
package space.arim.estimator.console;

import java.io.PrintStream;
import java.util.Scanner;

import org.mariuszgromada.math.mxparser.Function;

import space.arim.estimator.EulerApproximator;
import space.arim.estimator.EulerEstimator;

@Deprecated
public class UserConsole implements AutoCloseable {
	
	private final Scanner scanner;
	
	private final PrintStream output;
	
	private final int precision;
	
	private static final String invalid_point = "Please enter an ordered pair without parentheses, e.g. \"10,0.1\".";
	
	public UserConsole(Scanner scanner, PrintStream output, int precision) {
		this.scanner = scanner;
		this.output = output;
		this.precision = precision;
	}
	
	private void say(String message) {
		output.println(message);
	}
	
	private String next() {
		String input = scanner.next();
		if (input.equalsIgnoreCase("STOP") || input.equalsIgnoreCase("EXIT")) {
			say("Shutting down...");
			stop();
		}
		return input;
	}
	
	private String nextLine() {
		String input = scanner.nextLine();
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
		
		String function = nextLine();
		
		say("\n\nSpecified function: dy/dx =" + function + "\n");
		say("Please state:\n1.) The number of times you wish to iterate.\n2.) The step between subsequent x values when iterating.");
		
		say("Enter these as a pair without parentheses, e.g. 10,0.1.");
		int iterations = 0;
		double step = 0D;
		while (iterations <= 0 || step <= 0D) {
			String inputs = nextLine();
			if (inputs.contains(",")) {
				String[] input = inputs.split(",");
				try {
					iterations = Integer.parseInt((input[0].replaceAll(" ", "")));
					step = Double.parseDouble((input[1].replaceAll(" ", "")));
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
			String inputs = nextLine();
			if (inputs.contains(",")) {
				String[] input = inputs.split(",");
				try {
					x = Double.parseDouble((input[0].replaceAll(" ", "")));
					y = Double.parseDouble((input[1].replaceAll(" ", "")));
				} catch (NumberFormatException ex) {
					say(invalid_point);
				}
			} else {
				say(invalid_point);
			}
		}
		
		say("Ready to begin iteration? y/n");
		while (!EulerEstimator.parseBoolean(next())) {
			say("Looks like you aren't ready. How about now?");
		}
		long prev = System.nanoTime();
		Function func = new Function("D(x,y) = " + function);
		EulerApproximator diffEQ = new EulerApproximator(func, x, y);
		EulerEstimator.spitFunction(output, func);
		EulerEstimator.spitInitial(output, precision);
		diffEQ.approximations(System.out, iterations, step, iterations);
		say("Finished in " + ((System.nanoTime() - prev)/(1000_000_000D)) + " seconds.");
	}
	

	
	public void stop() {
		scanner.close();
		output.close();
	}

	@Override
	public void close() throws Exception {
	}
}

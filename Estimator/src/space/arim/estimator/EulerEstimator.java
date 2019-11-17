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
package space.arim.estimator;

import java.io.PrintStream;
import org.mariuszgromada.math.mxparser.Function;

import space.arim.estimator.gui.UserFrame;

public class EulerEstimator {

	private static final String TITLE = "EulerEstimator";
	private static final String VERSION = "v0.2.2";
	
	private EulerEstimator() {}
	
	public static boolean parseBoolean(String input) {
		switch (input.toLowerCase()) {
		case "yes":
			return true;
		case "okay":
			return true;
		case "ok":
			return true;
		case "y":
			return true;
		default:
			return Boolean.parseBoolean(input);
		}
	}
	
	public static void main(String[] args) {		
		try (UserFrame frame = new UserFrame(TITLE, VERSION)) {
			frame.start();
		} catch (Exception ex) {
			System.out.println("ERROR: Please create an issue on Github!");
			ex.printStackTrace();
			System.out.println("ERROR: Please create an issue on Github!");
		}
		
	}
	
	public static void spitFunction(PrintStream output, Function function) {
		output.println("Function: " + function.getFunctionExpressionString());
	}
	
	public static void spitLine(PrintStream output, int precision, int iteration, double x, double y, double derivative) {
		
		output.println(iteration + "    |    " + String.format("%." + precision + "f", x) + "    |    " + String.format("%." + precision + "f", y) + "    |    " + String.format("%." + precision + "f", derivative));
		
	}
	
	public static void spitInitial(PrintStream output, int precision) {
		StringBuilder builder = (new StringBuilder()).append("     ");
		for (int n = 0; n < precision; n++) {
			builder.append(" ");
		}
		String space = builder.toString();
		output.println("n    " + "|" + space + "x" + space + "|" + space + "y" + space + "|" + space + "dy/dx");
	}
	
	public static void spitTime(PrintStream output, long previous, int precision) {
		output.println("Calculated in " + String.format("%." + precision + "f", ((System.nanoTime() - previous)/(1000_000_000D))) + " seconds.");
	}

}

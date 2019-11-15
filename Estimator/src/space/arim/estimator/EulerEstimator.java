package space.arim.estimator;

import java.io.PrintStream;
import org.mariuszgromada.math.mxparser.Function;

import space.arim.estimator.gui.UserFrame;

public class EulerEstimator {

	private static final String TITLE = "EulerEstimator";
	private static final String VERSION = "v0.2.0";
	
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

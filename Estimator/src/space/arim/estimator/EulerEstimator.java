package space.arim.estimator;

import java.io.PrintStream;
import org.mariuszgromada.math.mxparser.Function;

import space.arim.estimator.gui.UserFrame;

public class EulerEstimator {

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
		try (UserFrame frame = new UserFrame("EulerEstimator")) {
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
	
	public static void spitLine(PrintStream output, int precision, double x, double y, double derivative) {
		
		output.println(String.format("%." + precision + "f", x) + "    |    " + String.format("%." + precision + "f", y) + "    |    " + String.format("%." + precision + "f", derivative));
		
	}
	
	public static void spitInitial(PrintStream output, int precision) {
		StringBuilder builder = (new StringBuilder()).append("     ");
		for (int n = 0; n < precision; n++) {
			builder.append(" ");
		}
		String space = builder.toString();
		output.println("    x" + space + "|" + space + "y" + space + "|" + space + "dy/dx");
	}

}

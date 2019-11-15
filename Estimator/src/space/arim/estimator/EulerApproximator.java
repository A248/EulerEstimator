package space.arim.estimator;

import java.io.PrintStream;

import org.mariuszgromada.math.mxparser.Function;

public class EulerApproximator {
	private final Function function;
	private final double initialX;
	private final double initialY;
	
	public EulerApproximator(Function function, double initialX, double initialY) {
		this.function = function;
		this.initialX = initialX;
		this.initialY = initialY;
	}
	
	public final Function function() {
		return function;
	}
	
	public final double initialX() {
		return initialX;
	}
	
	public final double initialY() {
		return initialY;
	}
	
	public void approximations(PrintStream output, int precision, double step, int iterations) {
		double x = initialX;
		double y = initialY;
		EulerEstimator.spitFunction(output, function);
		EulerEstimator.spitInitial(output, precision);
		EulerEstimator.spitLine(output, precision, x, y, function.calculate(x, y));
		for (int n = 0; n < iterations; n++) {
			double deriv = function.calculate(x, y);
			y = y + step*deriv;
			x = x + step;
			EulerEstimator.spitLine(output, precision, x, y, deriv);
		}
	}
}

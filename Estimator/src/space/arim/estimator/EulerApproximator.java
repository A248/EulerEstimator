package space.arim.estimator;

import org.mariuszgromada.math.mxparser.Function;

public class EulerApproximator {
	private final Function function;
	private final double initialX;
	private final double initialY;
	
	public EulerApproximator(String expression, double initialX, double initialY) {
		function = new Function("D(x,y) = " + expression);
		this.initialX = initialX;
		this.initialY = initialY;
	}
	
	public final double initialX() {
		return initialX;
	}
	
	public final double initialY() {
		return initialY;
	}
	
	public final double calculateDerivative() {
		return calculateDerivative(initialX, initialY);
	}
	
	public final double calculateDerivative(double x, double y) {
		return function.calculate(x, y);
	}

	public DoubleTriplet[] approximations(double step, int iterations) {
		DoubleTriplet[] results = new DoubleTriplet[iterations];
		assert results.length == iterations;
		double x = initialX;
		double y = initialY;
		for (int n = 0; n < iterations; n++) {
			double deriv = function.calculate(x, y);
			y = y + step*deriv;
			x = x + step;
			results[n] = new DoubleTriplet(x, y, deriv);
		}
		return results;
	}
}

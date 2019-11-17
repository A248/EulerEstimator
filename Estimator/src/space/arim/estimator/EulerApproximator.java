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
		double deriv = function.calculate(x, y);
		EulerEstimator.spitFunction(output, function);
		EulerEstimator.spitInitial(output, precision);
		for (int n = 0; n < iterations + 1; n++) {
			EulerEstimator.spitLine(output, precision, n, x, y, deriv);
			y = y + step*deriv;
			x = x + step;
			deriv = function.calculate(x, y);
		}
	}
}

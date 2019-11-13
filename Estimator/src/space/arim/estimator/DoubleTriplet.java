package space.arim.estimator;

public final class DoubleTriplet {
	private final double value1;
	private final double value2;
	private final double value3;
	public DoubleTriplet(double value1, double value2, double value3) {
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}
	
	public double value1() {
		return value1;
	}
	
	public double value2() {
		return value2;
	}
	
	public double value3() {
		return value3;
	}
}

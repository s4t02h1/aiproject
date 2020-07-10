package com.se;

class Range {
	private double min = 50.0;
	private double max = 250.0;
	public boolean isOk(double a) {
		if (a >= min && a < max)
		return true;
		else
			return false;
	}
	public void disp() {
		System.out.println("範囲は、" + min + "から" + max + "です。");
	}
	public Range(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

}

class ExecRange {
	public static void main(String[] args) {
		Range rg = new Range(50.0, 250.0);
		rg.disp();

		double[] data = {150.5, 75.1, 35.3, 281.2, 210.3};
		for(double d : data) {
		//	boolean ret = rg.isOk(d);
			System.out.println(d + ":" + rg.isOk(d));
		}
	}

}
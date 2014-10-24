package com.pm.rap.toe.model;

public class CurrentSource extends BaseElement {

	private double i;

	public CurrentSource(Branch branch) {
		super(branch);
	}

	public CurrentSource(Branch branch, double i) {
		super(branch);
		this.i = i;
	}

	@Override
	public double getI() {
		return i;
	}

	@Override
	public double getR() {
		return 0;
	}

	@Override
	public double getU() {
		return 0;
	}

	@Override
	public void setI(double i) {
		this.i = i;
	}

}

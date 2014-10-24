package com.pm.rap.toe.model;

public class Resistance extends BaseElement {

	private double r;

	public Resistance(Branch branch) {
		super(branch);
	}

	public Resistance(Branch branch, double r) {
		super(branch);
		this.r = r;
	}

	@Override
	public double getI() {
		return getParent().getI();
	}

	@Override
	public double getR() {
		return r;
	}

	@Override
	public double getU() {
		return 0;
	}

	@Override
	public void setR(double r) {
		this.r = r;
	}

}

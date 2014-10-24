package com.pm.rap.toe.model;

public class VoltageSource extends BaseElement {

	private double u;

	public VoltageSource(Branch branch) {
		super(branch);
	}

	public VoltageSource(Branch branch, double u) {
		super(branch);
		this.u = u;
	}

	@Override
	public double getI() {
		return getParent().getI();
	}

	@Override
	public double getR() {
		return 0;
	}

	@Override
	public double getU() {
		return u;
	}

	@Override
	public void setU(double u) {
		this.u = u;
	}

}

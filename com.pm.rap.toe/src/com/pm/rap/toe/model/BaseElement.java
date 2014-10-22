package com.pm.rap.toe.model;

public abstract class BaseElement {

	private double u = 0;
	private double i = 0;
	private double r = 0;

	private Branch parent;

	public BaseElement(Branch branch) {
		this.parent = branch;
		parent.addElement(this);
	}

	protected BaseElement(BaseElement e, Branch branch) {
		this(branch);
		this.u = e.u;
		this.i = e.i;
		this.r = e.r;
	}

	public double getU() {
		return u;
	}

	public void setU(double u) {
		this.u = u;
	}

	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public Branch getParent() {
		return parent;
	}
	
	public abstract BaseElement clone(Branch newParent);

}

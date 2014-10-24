package com.pm.rap.toe.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class BaseElement {

	private Branch parent;
	private final String name;

	public BaseElement(Branch branch) {
		this(branch, "");
	}

	public BaseElement(Branch branch, String name) {
		parent = branch;
		parent.addElement(this);
		this.name = name;
	}

	public double getI() {
		throw new NotImplementedException();
	}

	public String getName() {
		return name;
	}

	public Branch getParent() {
		return parent;
	}

	public double getR() {
		throw new NotImplementedException();
	}

	public double getU() {
		throw new NotImplementedException();
	}

	public void setI(double i) {
		throw new NotImplementedException();
	}

	public void setR(double r) {
		throw new NotImplementedException();
	}

	public void setU(double u) {
		throw new NotImplementedException();
	}

}

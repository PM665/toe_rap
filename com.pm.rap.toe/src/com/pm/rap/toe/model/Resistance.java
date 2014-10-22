package com.pm.rap.toe.model;

public class Resistance extends BaseElement {

	public Resistance(Branch branch) {
		super(branch);
	}

	protected Resistance(Resistance e, Branch branch) {
		super(e, branch);
	}

	public Resistance clone(Branch newParent) {
		return new Resistance(this, newParent);
	}

	@Override
	public double getI() {
		return getParent().getI();
	}

}

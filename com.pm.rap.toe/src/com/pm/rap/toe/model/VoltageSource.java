package com.pm.rap.toe.model;

public class VoltageSource extends BaseElement {

	public VoltageSource(Branch branch) {
		super(branch);
	}

	protected VoltageSource(VoltageSource e, Branch branch) {
		super(e, branch);
	}

	public VoltageSource clone(Branch newParent) {
		return new VoltageSource(this, newParent);
	}

}

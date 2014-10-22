package com.pm.rap.toe.model;

public class CurrentSource extends BaseElement {

	public CurrentSource(Branch branch) {
		super(branch);
	}

	protected CurrentSource(CurrentSource e, Branch branch) {
		super(e, branch);
	}

	public CurrentSource clone(Branch newParent) {
		return new CurrentSource(this, newParent);
	}

}

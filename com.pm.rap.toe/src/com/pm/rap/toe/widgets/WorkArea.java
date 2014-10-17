package com.pm.rap.toe.widgets;

import org.eclipse.swt.widgets.Composite;

import com.pm.rap.toe.model.ChainModel;

public class WorkArea extends Composite {
	private static final long serialVersionUID = -2899718336133655867L;

	private final ChainModel model;

	public WorkArea(Composite parent, int style) {
		super(parent, style);
		model = new ChainModel();
	}

	public ChainModel getModel() {
		return model;
	}

}

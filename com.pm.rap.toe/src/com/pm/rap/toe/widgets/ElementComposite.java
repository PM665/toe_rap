package com.pm.rap.toe.widgets;

import org.eclipse.swt.widgets.Composite;

import com.pm.rap.toe.model.BaseElement;

public class ElementComposite extends Composite {
	private static final long serialVersionUID = 5225884143899084684L;

	private final BaseElement element;

	public ElementComposite(Composite parent, int style, BaseElement element) {
		super(parent, style);
		this.element = element;
	}

	public BaseElement getElement() {
		return element;
	}

}

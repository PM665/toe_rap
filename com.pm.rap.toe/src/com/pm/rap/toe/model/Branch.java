package com.pm.rap.toe.model;

import java.util.Collection;
import java.util.LinkedList;

public class Branch {

	private final Node from;
	private final Node to;

	private final Collection<BaseElement> elements;

	public Branch(Node from, Node to) {
		super();
		this.from = from;
		this.to = to;
		this.elements = new LinkedList<BaseElement>();
	}

	public boolean addElement(BaseElement e) {
		return elements.add(e);
	}

	public boolean removeElement(BaseElement o) {
		return elements.remove(o);
	}

	public Collection<BaseElement> getElements() {
		return elements;
	}

	public Node getFrom() {
		return from;
	}

	public Node getTo() {
		return to;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

}

package com.pm.rap.toe.model;

import java.util.Collection;
import java.util.LinkedList;

public class Branch {

	private int id = 1;
	private Node from;
	private Node to;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void switchSides() {
		Node tmp = from;
		from = to;
		to = tmp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + id;
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
		boolean opp = false;
		if (id != other.id)
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from)) {
			opp = from.equals(other.to);
			if (!opp) {
				return false;
			}
		}

		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to)) {
			opp = opp && to.equals(other.from);
			if (!opp) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return from.toString() + ":" + to.toString()
				+ (id == 1 ? "" : "(" + id + ")");
	}

}

package com.pm.rap.toe.model;

import java.util.Collection;
import java.util.LinkedList;

public class Branch {

	private int id = 1;
	private Node from;
	private Node to;

	private final ChainModel model;

	private final Collection<BaseElement> elements;

	public Branch(Node from, Node to) {
		super();
		this.from = from;
		this.to = to;
		elements = new LinkedList<BaseElement>();
		model = from.getModel();
	}

	public boolean addElement(BaseElement e) {
		return elements.add(e);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Branch other = (Branch) obj;
		boolean opp = false;
		if (id != other.id) {
			return false;
		}
		if (from == null) {
			if (other.from != null) {
				return false;
			}
		} else if (!from.equals(other.from)) {
			opp = from.equals(other.to);
			if (!opp) {
				return false;
			}
		}

		if (to == null) {
			if (other.to != null) {
				return false;
			}
		} else if (!to.equals(other.to)) {
			opp = opp && to.equals(other.from);
			if (!opp) {
				return false;
			}
		}
		return true;
	}

	public Collection<BaseElement> getElements() {
		return elements;
	}

	public Node getFrom() {
		return from;
	}

	public double getI() {
		return model.getI(this);
	}

	public int getId() {
		return id;
	}

	public double getR() {
		double r = 0;
		for (BaseElement e : elements) {
			r += e.getR();
		}
		return r;
	}

	public Node getTo() {
		return to;
	}

	public double getU() {
		double u = 0;
		for (BaseElement e : elements) {
			u += e.getU();
		}
		return u;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (from == null ? 0 : from.hashCode());
		result = prime * result + id;
		result = prime * result + (to == null ? 0 : to.hashCode());
		return result;
	}

	public boolean removeElement(BaseElement o) {
		return elements.remove(o);
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
	public String toString() {
		return from.toString() + ":" + to.toString()
				+ (id == 1 ? "" : "(" + id + ")");
	}
}

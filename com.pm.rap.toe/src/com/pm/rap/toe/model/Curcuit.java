package com.pm.rap.toe.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Curcuit {

	private final ChainModel model;
	private final LinkedList<Branch> branches;
	private Node first = null;
	private Node last = null;

	private int id;
	private double i;

	public Curcuit(ChainModel model) {
		branches = new LinkedList<Branch>();
		this.model = model;
	}

	public Curcuit(Curcuit curcuit) {
		this(curcuit.model);
		if (curcuit != null) {
			this.branches.addAll(curcuit.branches);
			this.first = curcuit.first;
			this.last = curcuit.last;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getFirst() {
		return first;
	}

	public Node getLast() {
		return last;
	}

	public Collection<Branch> getBranches() {
		return branches;
	}

	public void addBranch(Branch b) {
		if (!branches.isEmpty()) {
			Branch lb = branches.getLast();
			Node[] connection = getConnectionNodes(lb, b);
			if (connection.length == 0) {
				throw new IllegalArgumentException(
						"Branch is not connected with the last branch in curcuit");
			} else if (connection.length == 2) {
				if (branches.size() == 1 && !b.equals(lb)) {
					first = connection[0];
					last = first;
				}
			} else if (connection.length == 1) {
				last = b.getFrom().equals(connection[0]) ? b.getTo() : b
						.getFrom();
				if (branches.size() == 1) {
					first = lb.getFrom().equals(connection[0]) ? lb.getTo()
							: lb.getFrom();
				}
			}
		}
		branches.add(b);
	}

	private Node[] getConnectionNodes(Branch b1, Branch b2) {
		if (b1.equals(b2)) {
			return new Node[0];
		}
		if (b1.getFrom().equals(b2.getFrom()) && b1.getTo().equals(b2.getTo())
				|| b1.getFrom().equals(b2.getTo())
				&& b1.getTo().equals(b2.getTo())) {
			return new Node[] { b1.getFrom(), b1.getTo() };
		}
		if (b1.getFrom().equals(b2.getFrom())) {
			return new Node[] { b1.getFrom() };
		}
		if (b1.getFrom().equals(b2.getTo())) {
			return new Node[] { b1.getFrom() };
		}
		if (b1.getTo().equals(b2.getFrom())) {
			return new Node[] { b1.getTo() };
		}
		if (b1.getTo().equals(b2.getTo())) {
			return new Node[] { b1.getTo() };
		}
		return new Node[0];

	}

	public boolean containsBranch(Branch b) {
		return branches.contains(b);
	}

	public boolean containsNode(Node n) {
		if (n.equals(first)) {
			return false;
		}
		if (n.equals(last)) {
			return false;
		}
		if (branches.size() == 1) {
			return false;
		}
		for (Branch b : branches) {
			if (b.getFrom().equals(n) || b.getTo().equals(n)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasConflicts() {
		boolean allOk = false;
		for (Node n : model.getNodes()) {
			boolean ok = false;
			if (n != first && !containsNode(n)) {
				ok = true;
				allOk = true;
			}
			if (!ok) {
				int count = 0;
				for (Branch b : branches) {
					if (b.getFrom().equals(n) || b.getTo().equals(n)) {
						count++;
					}
				}
				if (count != 0 && count != 2) {
					return true;
				}
			}
		}
		for (Branch b : model.getBranches()) {
			if (!containsBranch(b)) {
				allOk = true;
			}
		}
		return !allOk;
	}

	public boolean isCurcuit() {
		return branches.size() >= 2 && last != null && last.equals(first);
	}

	public int getBranchCount() {
		return branches.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		for (Branch b : branches) {
			result = prime * result + ((b == null) ? 0 : b.hashCode());
		}
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
		Curcuit other = (Curcuit) obj;
		if (branches == null) {
			if (other.branches != null)
				return false;
		} else if (!branches.containsAll(other.branches)) {
			return false;
		} else if (!other.branches.containsAll(branches)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		if (first == null) {
			return branches.toString();
		}
		Node n = first;
		StringBuilder sb = new StringBuilder(n.toString());
		Iterator<Branch> iterator = branches.iterator();
		while (iterator.hasNext()) {
			Branch b = iterator.next();
			n = b.getFrom().equals(n) ? b.getTo() : b.getFrom();
			sb.append(' ');
			sb.append(n.toString());
		}
		return sb.toString();
	}

	public double getR() {
		double r = 0;
		for (Branch b : branches) {
			r += b.getR();
		}
		return r;
	}

	public double getI() {
		internalFindCurrentSource();
		return i;
	}

	public void setI(double i) {
		if (!hasCurrentSource()) {
			this.i = i;
		}
	}

	public static double getMutualR(Curcuit src, Curcuit rel) {
		double r = 0;
		for (Branch b : src.getBranches()) {
			if (rel.containsBranch(b)) {
				Node srcB = src.getFromForBranch(b);
				Node relB = rel.getFromForBranch(b);
				if (srcB != null) {
					boolean minus = !srcB.equals(relB);
					r += (minus ? -1 : 1) * b.getR();
				}
			}
		}
		return r;
	}

	public Node getFromForBranch(Branch b) {
		if (!containsBranch(b)) {
			return null;
		}
		Iterator<Branch> itr = branches.iterator();
		Node result = first;
		while (itr.hasNext()) {
			Branch next = itr.next();
			if (next.equals(b)) {
				break;
			}
			result = next.getFrom().equals(result) ? next.getTo() : next
					.getFrom();
		}
		return result;
	}

	public boolean isStraightBranch(Branch b) {
		return b.getFrom().equals(getFromForBranch(b));
	}

	public Curcuit switchDirection() {
		Curcuit res = new Curcuit(model);
		for (int i = branches.size() - 1; i >= 0; i--) {
			res.addBranch(branches.get(i));
		}
		return res;
	}

	private boolean internalFindCurrentSource() {
		for (Branch b : branches) {
			for (BaseElement e : b.getElements()) {
				if (e instanceof CurrentSource) {
					i = e.getI();
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasCurrentSource() {
		return internalFindCurrentSource();
	}
}

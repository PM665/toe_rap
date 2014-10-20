package com.pm.rap.toe.mkt;

import java.util.ArrayList;
import java.util.Collection;

import com.pm.rap.toe.model.Branch;
import com.pm.rap.toe.model.ChainModel;
import com.pm.rap.toe.model.Curcuit;
import com.pm.rap.toe.model.Node;

public class CurcuitsFinder {

	private final ChainModel model;

	public CurcuitsFinder(ChainModel model) {
		this.model = model;
	}

	public ChainModel getModel() {
		return model;
	}

	public Collection<Curcuit> findCurcuits() {
		ArrayList<Curcuit> curcuits = new ArrayList<Curcuit>();
		for (Branch b : model.getBranches()) {
			Curcuit c = new Curcuit(model);
			c.addBranch(b);
			stepBranches(b.getFrom(), c, curcuits);
			stepBranches(b.getTo(), c, curcuits);
		}
		return curcuits;
	}

	private void stepBranches(Node node, Curcuit src,
			Collection<Curcuit> curcuits) {
		if (src == null) {
			src = new Curcuit(model);
		}
		for (Branch b : model.getBranches()) {
			if (src.containsBranch(b)) {
				continue;
			}
			if (node.equals(b.getFrom()) || node.equals(b.getTo())) {
				Node to = node.equals(b.getFrom()) ? b.getTo() : b.getFrom();
				if (src.containsNode(to)) {
					continue;
				}
				Curcuit c = new Curcuit(src);
				c.addBranch(b);
				if (c.isCurcuit()) {
					if (!c.hasConflicts() && !curcuits.contains(c)) {
						curcuits.add(c);
					}
				} else {
					stepBranches(to, c, curcuits);
				}
			}
		}
	}

}

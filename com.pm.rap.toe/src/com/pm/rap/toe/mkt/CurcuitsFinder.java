package com.pm.rap.toe.mkt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.pm.rap.toe.model.Branch;
import com.pm.rap.toe.model.ChainModel;
import com.pm.rap.toe.model.Curcuit;
import com.pm.rap.toe.model.Node;
import com.pm.rap.toe.util.IntObject;

public class CurcuitsFinder {

	private final ChainModel model;

	public CurcuitsFinder(ChainModel model) {
		this.model = model;
	}

	public ChainModel getModel() {
		return model;
	}

	public Collection<Curcuit> findCurcuits() {
		Collection<Curcuit> rest = findAllCurcuits();
		ArrayList<Curcuit> result = new ArrayList<Curcuit>();

		boolean resultComplete = false;

		while (!resultComplete) {
			// XXX
			int minCnt = Integer.MAX_VALUE;
			Curcuit minCurcuit = null;
			for (Curcuit c : rest) {
				int count = c.getBranchCount();
				if (count < minCnt) {
					minCnt = count;
					minCurcuit = c;
				}
			}
			if (minCurcuit != null) {
				result.add(minCurcuit);
				rest.remove(minCurcuit);
				resultComplete = processRest(rest, result);
			}
			// XXX
		}
		return result;
	}

	private boolean processRest(Collection<Curcuit> rest,
			ArrayList<Curcuit> result) {
		Map<Node, IntObject> nodesUsed = new HashMap<Node, IntObject>();
		Map<Branch, IntObject> branchesUsed = new HashMap<Branch, IntObject>();
		for (Branch b : model.getBranches()) {
			IntObject count = new IntObject();
			branchesUsed.put(b, count);
			for (Curcuit c : result) {
				if (c.getBranches().contains(b)) {
					count.inc();
				}
			}
		}
		for (Node n : model.getNodes()) {
			IntObject count = new IntObject();
			nodesUsed.put(n, count);
			for (Curcuit c : result) {
				if (c.containsNode(n)) {
					count.inc();
				}
			}
		}
		boolean ok = true;
		for (Node n : nodesUsed.keySet()) {
			int count = nodesUsed.get(n).get();
			Collection<Branch> branches = model.getNodeForBranch(n);
			boolean bIsOk = branches.size() == count;
			ok &= bIsOk;
			if (bIsOk) {
				for (Branch b : branches) {
					for (Curcuit c : rest.toArray(new Curcuit[0])) {
						if (c.containsBranch(b)) {
							rest.remove(c);
						}
					}
				}
			}
		}
		for (Branch b : branchesUsed.keySet()) {
			int count = branchesUsed.get(b).get();
			ok &= count == 1 || count == 2;
		}
		return ok;
	}

	public Collection<Curcuit> findAllCurcuits() {
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

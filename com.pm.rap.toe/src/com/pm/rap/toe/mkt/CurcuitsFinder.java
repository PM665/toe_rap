package com.pm.rap.toe.mkt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.pm.rap.toe.model.BaseElement;
import com.pm.rap.toe.model.Branch;
import com.pm.rap.toe.model.ChainModel;
import com.pm.rap.toe.model.Curcuit;
import com.pm.rap.toe.model.CurrentSource;
import com.pm.rap.toe.model.Node;
import com.pm.rap.toe.util.IntObject;

public class CurcuitsFinder {

	private final ChainModel model;

	public CurcuitsFinder(ChainModel model) {
		this.model = model;
	}

	private Collection<Curcuit> findAllCurcuits() {
		ArrayList<Curcuit> curcuits = new ArrayList<Curcuit>();
		for (Branch b : model.getBranches()) {
			Curcuit c = new Curcuit(model);
			c.addBranch(b);
			stepBranches(b.getFrom(), c, curcuits);
			stepBranches(b.getTo(), c, curcuits);
		}
		curcuits.sort(new Comparator<Curcuit>() {

			@Override
			public int compare(Curcuit o1, Curcuit o2) {
				int a = o1.getBranchCount() - o2.getBranchCount();
				return a != 0 ? a / Math.abs(a) : a;
			}
		});
		return curcuits;
	}

	public Collection<Collection<Curcuit>> findCurcuits() {
		Collection<Curcuit> all = findAllCurcuits();
		Set<Collection<Curcuit>> result = new HashSet<Collection<Curcuit>>();
		int cCount = model.getBranches().size() - (model.getNodes().size() - 1);

		getAllCurcuitSets(all.toArray(new Curcuit[0]), 0, cCount, null, result);
		postProcessResult(result);
		return result;
	}

	private void getAllCurcuitSets(Curcuit[] array, int index, int count,
			Set<Curcuit> srcSet, Set<Collection<Curcuit>> result) {
		if (count == 0) {
			if (setIsOk(srcSet)) {
				result.add(srcSet);
			}
			return;
		}
		for (int i = index; i < array.length; i++) {
			Set<Curcuit> set = new HashSet<Curcuit>();
			if (srcSet != null) {
				set.addAll(srcSet);
			}
			set.add(array[i]);
			getAllCurcuitSets(array, i + 1, count - 1, set, result);
		}
	}

	public ChainModel getModel() {
		return model;
	}

	private void postProcessResult(Set<Collection<Curcuit>> result) {
		HashSet<Collection<Curcuit>> copy = new HashSet<Collection<Curcuit>>(
				result);
		Map<Branch, IntObject> currents = new HashMap<Branch, IntObject>();
		for (Branch b : model.getBranches()) {
			for (BaseElement e : b.getElements()) {
				if (e instanceof CurrentSource) {
					currents.put(b, new IntObject());
					break;
				}
			}
		}
		for (Collection<Curcuit> set : copy) {
			Map<Branch, IntObject> branches = new HashMap<Branch, IntObject>();
			for (Curcuit c : set) {
				for (Branch b : c.getBranches()) {
					IntObject curCnt = currents.get(b);
					if (curCnt != null) {
						curCnt.inc();
					}
					IntObject count = branches.get(b);
					if (count == null) {
						count = new IntObject();
						branches.put(b, count);
					}
					count.inc();
				}
			}
			for (Branch b : branches.keySet()) {
				int count = branches.get(b).get();
				if (count >= 3) {
					result.remove(set);
					break;
				}
			}
			boolean fail = false;
			for (IntObject o : currents.values()) {
				if (o.get() > 1) {
					fail = true;
				}
				o.set(0);
			}
			if (fail) {
				result.remove(set);
			}
		}
		if (result.isEmpty()) {
			result.addAll(copy);
		}
	}

	private boolean setIsOk(Set<Curcuit> set) {
		Collection<Branch> branches = new ArrayList<Branch>(model.getBranches());
		for (Curcuit c : set) {
			for (Branch b : c.getBranches()) {
				branches.remove(b);
			}
		}
		return branches.isEmpty();
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

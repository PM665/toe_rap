package com.pm.rap.toe.mkt;

import java.util.ArrayList;
import java.util.Collection;

import com.pm.rap.toe.model.ChainModel;
import com.pm.rap.toe.model.Curcuit;

public class MktSolver {

	private final ChainModel model;

	public MktSolver(ChainModel model) {
		super();
		this.model = model;
	}

	public Collection<MktSolution> getSolution() {
		ArrayList<MktSolution> result = new ArrayList<MktSolution>();
		CurcuitsFinder finder = new CurcuitsFinder(model);
		Collection<Collection<Curcuit>> curcuits = finder.findCurcuits();

		for (Collection<Curcuit> set : curcuits) {
			CurrentFinder mkt = new CurrentFinder(model);
			result.add(mkt.solve(set));
		}
		return result;
	}

}

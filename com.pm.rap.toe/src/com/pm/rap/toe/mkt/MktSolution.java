package com.pm.rap.toe.mkt;

import com.pm.rap.toe.model.Branch;
import com.pm.rap.toe.model.ChainModel;
import com.pm.rap.toe.model.Curcuit;

public class MktSolution {

	private final ChainModel model;
	private final Curcuit[] curcuits;
	private final double[] currents;

	public MktSolution(ChainModel model, Curcuit[] curcuits, double[] currents) {
		super();
		this.model = model;
		this.curcuits = curcuits;
		this.currents = currents;
		model.addSolution(this);
	}

	public double getIForBranch(Branch b) {
		double current = 0;
		for (int i = 0; i < currents.length; i++) {
			current += (curcuits[i].isStraightBranch(b) ? 1 : -1) * currents[i];
		}
		return current;
	}

	public ChainModel getModel() {
		return model;
	}
}

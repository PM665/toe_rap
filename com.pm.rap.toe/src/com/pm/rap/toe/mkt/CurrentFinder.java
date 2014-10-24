package com.pm.rap.toe.mkt;

import java.util.Collection;

import com.pm.rap.toe.model.Branch;
import com.pm.rap.toe.model.ChainModel;
import com.pm.rap.toe.model.Curcuit;
import com.pm.rap.toe.util.gauss.GaussSolver;

public class CurrentFinder {

	private final ChainModel model;

	public CurrentFinder(ChainModel model) {
		super();
		this.model = model;
		model.clearSolutions();
	}

	public MktSolution solve(Collection<Curcuit> curcuits) {

		int count = curcuits.size();

		double[][] matrix = new double[count][count + 1];

		Curcuit[] array = curcuits.toArray(new Curcuit[0]);

		for (int i = 0; i < count; i++) {
			Curcuit curcuit = array[i];
			boolean hasCurSrc = curcuit.hasCurrentSource();
			double cI = -curcuit.getI();
			double cR = curcuit.getR();
			for (int j = i + 1; j < count; j++) {
				double r;
				Curcuit otherC = array[j];
				r = Curcuit.getMutualR(curcuit, otherC);
				boolean hasCurSrc2 = otherC.hasCurrentSource();
				double c2I = Curcuit.isSameDirectionOnCOmmonBranch(curcuit,
						otherC) * otherC.getI();
				if (!hasCurSrc && !hasCurSrc2) {
					matrix[i][j] = r;
					matrix[j][i] = r;
				} else {
					matrix[i][j] = 0;
					matrix[j][i] = 0;
					matrix[i][count] += cR * cI;
					matrix[j][count] += r * c2I;
				}
			}
			if (!hasCurSrc) {
				matrix[i][i] = cR;
				for (Branch b : curcuit.getBranches()) {
					boolean minus = !curcuit.isStraightBranch(b);
					matrix[i][count] += (minus ? -1 : 1) * b.getU();
				}
			} else {
				matrix[i][i] = 0;
			}
		}
		double[] result = GaussSolver.solve(matrix);
		return new MktSolution(model, array, result);
	}

}

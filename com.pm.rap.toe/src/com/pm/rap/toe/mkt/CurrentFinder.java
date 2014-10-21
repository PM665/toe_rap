package com.pm.rap.toe.mkt;

import java.util.Collection;

import com.pm.rap.toe.gauss.GaussSolver;
import com.pm.rap.toe.model.Branch;
import com.pm.rap.toe.model.ChainModel;
import com.pm.rap.toe.model.Curcuit;

public class CurrentFinder {

	private final ChainModel model;

	public CurrentFinder(ChainModel model) {
		super();
		this.model = model;
	}

	public ChainModel getModel() {
		return model;
	}

	public void solve(Collection<Curcuit> curcuits) {

		System.out.println(curcuits);
		int count = curcuits.size();

		double[][] matrix = new double[count][count + 1];

		Curcuit[] array = curcuits.toArray(new Curcuit[0]);

		for (int i = 0; i < count; i++) {
			Curcuit curcuit = array[i];
			matrix[i][i] = curcuit.getR();
			for (int j = i + 1; j < count; j++) {
				double r;
				r = Curcuit.getMutualR(curcuit, array[j]);
				matrix[i][j] = r;
				matrix[j][i] = r;
			}
			for (Branch b : curcuit.getBranches()) {
				boolean minus = !curcuit.isStraightBranch(b);
				matrix[i][count] += (minus ? -1 : 1) * b.getU();
			}
		}
		for (double[] dd : matrix) {
			for (double d : dd) {
				System.out.print(d + "\t");
			}
			System.out.println();
		}
		double[] result = GaussSolver.solve(matrix);
		for (double d : result) {
			System.out.print(d + " ");
		}
		System.out.println();
		System.out.println();
	}

}

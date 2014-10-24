package com.pm.rap.toe.util.gauss;

import java.util.Arrays;

public class GaussSolver {

	public static double[] solve(double[][] a) {
		double[][] x = new double[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			x[i] = Arrays.copyOf(a[i], a[i].length);
		}
		double p;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				if (i != j) {
					p = x[j][i] / x[i][i];
					for (int k = i; k <= x.length; ++k) {
						x[j][k] -= p * x[i][k];
					}
				}
			}
		}
		double[] b = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			b[i] = x[i][x.length] / x[i][i];
		}
		return b;
	}
}

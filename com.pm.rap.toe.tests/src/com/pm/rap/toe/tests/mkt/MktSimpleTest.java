package com.pm.rap.toe.tests.mkt;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.pm.rap.toe.mkt.CurcuitsFinder;
import com.pm.rap.toe.mkt.CurrentFinder;
import com.pm.rap.toe.mkt.MktSolution;
import com.pm.rap.toe.model.Branch;
import com.pm.rap.toe.model.ChainModel;
import com.pm.rap.toe.model.Curcuit;
import com.pm.rap.toe.model.CurrentSource;
import com.pm.rap.toe.model.Node;
import com.pm.rap.toe.model.Resistance;
import com.pm.rap.toe.model.VoltageSource;

public class MktSimpleTest {

	@Test
	public void task1() {
		ChainModel model = new ChainModel();
		Node n1 = new Node(model);
		n1.id = 1;
		Node n2 = new Node(model);
		n2.id = 2;

		Branch b1 = model.addBranch(new Branch(n1, n2));
		Branch b2 = model.addBranch(new Branch(n1, n2));
		Branch b3 = model.addBranch(new Branch(n1, n2));

		Resistance r1 = new Resistance(b1);
		Resistance r2 = new Resistance(b1);
		Resistance r3 = new Resistance(b2);
		Resistance r4 = new Resistance(b3);
		Resistance r5 = new Resistance(b3);
		VoltageSource e1 = new VoltageSource(b1);
		VoltageSource e2 = new VoltageSource(b1);
		VoltageSource e4 = new VoltageSource(b3);
		VoltageSource e5 = new VoltageSource(b2);
		e1.setU(10);
		e2.setU(30);
		e5.setU(15);
		e4.setU(35);
		r1.setR(20);
		r2.setR(30);
		r3.setR(10);
		r4.setR(15);
		r5.setR(25);

		CurcuitsFinder finder = new CurcuitsFinder(model);
		Collection<Collection<Curcuit>> curcuits = finder.findCurcuits();

		ArrayList<MktSolution> solutions = new ArrayList<MktSolution>();
		for (Collection<Curcuit> set : curcuits) {
			CurrentFinder mkt = new CurrentFinder(model);
			solutions.add(mkt.solve(set)); // TODO
		}

		for (MktSolution s : solutions) {
			model.setActiveSolution(s);
		}
		Assert.assertEquals(3, solutions.size());
		// TODO add conditions
	}

	@Test
	public void task2() {
		ChainModel model = new ChainModel();
		Node n1 = new Node(model);
		n1.id = 1;
		Node n2 = new Node(model);
		n2.id = 2;
		Node n3 = new Node(model);
		n3.id = 3;
		Node n4 = new Node(model);
		n4.id = 4;
		Node n5 = new Node(model);
		n5.id = 5;

		Branch b1 = model.addBranch(new Branch(n1, n2));
		Branch b2 = model.addBranch(new Branch(n1, n2));
		Branch b3 = model.addBranch(new Branch(n1, n3));
		Branch b4 = model.addBranch(new Branch(n2, n4));
		Branch b5 = model.addBranch(new Branch(n3, n4));
		Branch b6 = model.addBranch(new Branch(n3, n5));
		Branch b8 = model.addBranch(new Branch(n5, n4));
		Branch b9 = model.addBranch(new Branch(n5, n4));

		CurrentSource j = new CurrentSource(b1);
		Resistance r1 = new Resistance(b2);
		Resistance r2 = new Resistance(b3);
		Resistance r3 = new Resistance(b5);
		Resistance r4 = new Resistance(b6);
		Resistance r5 = new Resistance(b8);
		Resistance r6 = new Resistance(b9);
		Resistance r7 = new Resistance(b4);

		j.setI(16);
		r1.setR(5);
		r2.setR(2);
		r3.setR(8);
		r4.setR(2);
		r5.setR(1);
		r6.setR(2);
		r7.setR(1);

		CurcuitsFinder finder = new CurcuitsFinder(model);
		Collection<Collection<Curcuit>> curcuits = finder.findCurcuits();

		ArrayList<MktSolution> solutions = new ArrayList<MktSolution>();
		for (Collection<Curcuit> set : curcuits) {
			CurrentFinder mkt = new CurrentFinder(model);
			solutions.add(mkt.solve(set)); // TODO
		}

		double[] expecteds = new double[] { 8, 8, 2, 6, 4, 2 };
		for (double d : expecteds) {
			System.out.print(d + " ");
		}
		System.out.println();
		for (MktSolution s : solutions) {
			int ind = solutions.indexOf(s);
			String error = String.format("The solution %d is wrong", ind);
			model.setActiveSolution(s);

			double[] actuals = new double[] { r1.getI(), r2.getI(), r3.getI(),
					r4.getI(), r5.getI(), r6.getI() };
			for (double d : actuals) {
				System.out.print(d + " ");
			}
			System.out.println();
			// Assert.assertArrayEquals(error, expected,
			// actuals, .1);

		}
	}

}

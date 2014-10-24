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
			System.out.println(r1.getI());
		}
		Assert.assertEquals(3, solutions.size());

	}

}

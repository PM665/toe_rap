package com.pm.rap.toe.tests.mkt;

import java.util.Collection;

import org.junit.Test;

import com.pm.rap.toe.mkt.CurcuitsFinder;
import com.pm.rap.toe.mkt.CurrentFinder;
import com.pm.rap.toe.model.Branch;
import com.pm.rap.toe.model.ChainModel;
import com.pm.rap.toe.model.Curcuit;
import com.pm.rap.toe.model.Node;
import com.pm.rap.toe.model.Resistance;
import com.pm.rap.toe.model.VoltageSource;

public class MktSimpleTest {

	@Test
	public void test1() {
		ChainModel model = new ChainModel();
		Node n1 = model.addNode(new Node());
		n1.id = 1;
		Node n2 = model.addNode(new Node());
		n2.id = 2;

		Branch b1 = model.addBranch(new Branch(n1, n2));
		Branch b2 = model.addBranch(new Branch(n1, n2));
		Branch b3 = model.addBranch(new Branch(n1, n2));

		Resistance r1 = new Resistance(b1);
		Resistance r2 = new Resistance(b1);
		Resistance r3 = new Resistance(b2);
		Resistance r4 = new Resistance(b3);
		Resistance r5 = new Resistance(b3);
		Resistance r6 = new Resistance(b3);
		VoltageSource e1 = new VoltageSource(b2);
		VoltageSource e2 = new VoltageSource(b3);
		e1.setU(130);
		e2.setU(110);
		r1.setR(15);
		r2.setR(11);
		r3.setR(19);
		r4.setR(14);
		r5.setR(21);
		r6.setR(16);

		CurcuitsFinder finder = new CurcuitsFinder(model);
		Collection<Collection<Curcuit>> curcuits = finder
				.findCurcuits();

		for (Collection<Curcuit> set : curcuits) {
			CurrentFinder mkt = new CurrentFinder(model);
			mkt.solve(set); // TODO 
		}

	}

}
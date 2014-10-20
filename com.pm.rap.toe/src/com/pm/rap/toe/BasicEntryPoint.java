package com.pm.rap.toe;

import java.util.Collection;

import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.pm.rap.toe.gauss.GaussSolver;
import com.pm.rap.toe.mkt.CurcuitsFinder;
import com.pm.rap.toe.model.Branch;
import com.pm.rap.toe.model.ChainModel;
import com.pm.rap.toe.model.Curcuit;
import com.pm.rap.toe.model.Node;

public class BasicEntryPoint extends AbstractEntryPoint {

	@Override
	protected void createContents(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Solve example");
		button.addSelectionListener(new SelectionAdapter() {
			private static final long serialVersionUID = -5716606111780566444L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				double[] solve = GaussSolver.solve(new double[][] {
						new double[] { 2, 1, -1, 8 },
						new double[] { -3, -1, 2, -11 },
						new double[] { -2, 1, 2, -3 } });
				for (int i = 0; i < solve.length; i++) {
					System.out.println(solve[i]);
				}
			}

		});
		Button button2 = new Button(parent, SWT.PUSH);
		button2.setText("Find curcuits example");
		button2.addSelectionListener(new SelectionAdapter() {
			private static final long serialVersionUID = -5716606111780566444L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				ChainModel model = new ChainModel();
				Node n1 = model.addNode(new Node());
				n1.id = 1;
				Node n2 = model.addNode(new Node());
				n2.id = 2;
				Node n3 = model.addNode(new Node());
				n3.id = 3;
				Node n4 = model.addNode(new Node());
				n4.id = 4;
//				Node n5 = model.addNode(new Node());
//				n5.id = 5;
//				Node n6 = model.addNode(new Node());
//				n6.id = 4;

				model.addBranch(new Branch(n1, n2));
				model.addBranch(new Branch(n1, n3));
				model.addBranch(new Branch(n1, n4));
				model.addBranch(new Branch(n2, n3));
				model.addBranch(new Branch(n3, n4));
				model.addBranch(new Branch(n2, n4));
				model.addBranch(new Branch(n2, n4));
//				model.addBranch(new Branch(n1, n3));
//				model.addBranch(new Branch(n1, n3));

				CurcuitsFinder finder = new CurcuitsFinder(model);
				Collection<Curcuit> findCurcuits = finder.findCurcuits();
				System.out.println(findCurcuits);
			}

		});
	}
}

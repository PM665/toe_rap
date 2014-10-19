package com.pm.rap.toe;

import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.pm.rap.toe.gauss.GaussSolver;

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
	}
}

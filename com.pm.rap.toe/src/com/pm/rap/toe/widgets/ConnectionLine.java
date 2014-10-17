package com.pm.rap.toe.widgets;

import org.eclipse.swt.widgets.Composite;

import com.pm.rap.toe.model.Connection;

public class ConnectionLine extends Composite {
	private static final long serialVersionUID = -3132217149813800301L;

	private final Connection connection;

	public ConnectionLine(Composite parent, int style, Connection connection) {
		super(parent, style);
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

}

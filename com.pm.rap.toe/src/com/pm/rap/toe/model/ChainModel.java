package com.pm.rap.toe.model;

import java.util.Collection;

public class ChainModel {

	private Collection<BaseElement> elements;
	private Collection<Connection> connections;

	public ChainModel() {
		// TODO Auto-generated constructor stub
	}

	public Collection<BaseElement> getElements() {
		return elements;
	}

	public Collection<Connection> getConnections() {
		return connections;
	}

	public boolean addConnection(Connection conn) {
		return connections.add(conn);
	}

	public boolean removeConnection(Connection conn) {
		return connections.remove(conn);
	}

	public boolean addElement(BaseElement element) {
		return elements.add(element);
	}

	public boolean removeElement(BaseElement element) {
		return elements.remove(element);
	}

	public boolean validateModel() {
		// TODO 
		return true;
	}
}

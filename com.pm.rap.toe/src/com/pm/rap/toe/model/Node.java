package com.pm.rap.toe.model;

public class Node {

	public int id;
	private final ChainModel model;

	public Node(ChainModel model) {
		this.model = model;
		model.addNode(this);
	}

	public ChainModel getModel() {
		return model;
	}

	@Override
	public String toString() {
		return "N" + id;
	}

}

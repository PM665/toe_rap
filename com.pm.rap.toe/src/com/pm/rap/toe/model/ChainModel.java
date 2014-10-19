package com.pm.rap.toe.model;

import java.util.Collection;

public class ChainModel {

	private Collection<Node> nodes;
	private Collection<Branch> branches;

	public ChainModel() {
		// TODO Auto-generated constructor stub
	}

	public Collection<Node> getNodes() {
		return nodes;
	}

	public Collection<Branch> getBranches() {
		return branches;
	}

	public boolean addBranch(Branch conn) {
		return branches.add(conn);
	}

	public boolean removeBranch(Branch conn) {
		return branches.remove(conn);
	}

	public boolean addNode(Node element) {
		return nodes.add(element);
	}

	public boolean removeNode(Node element) {
		return nodes.remove(element);
	}

	public boolean validateModel() {
		// TODO 
		return true;
	}
}

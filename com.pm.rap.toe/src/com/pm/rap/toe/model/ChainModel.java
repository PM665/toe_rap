package com.pm.rap.toe.model;

import java.util.ArrayList;
import java.util.Collection;

public class ChainModel {

	private final Collection<Node> nodes;
	private final Collection<Branch> branches;

	public ChainModel() {
		nodes = new ArrayList<Node>();
		branches = new ArrayList<Branch>();
	}

	public Collection<Node> getNodes() {
		return nodes;
	}

	public Collection<Branch> getBranches() {
		return branches;
	}

	public Branch addBranch(Branch branch) {
		branches.add(checkConnected(branch));
		return branch;
	}

	public Node addNode(Node node) {
		nodes.add(node);
		return node;
	}

	public boolean removeBranch(Branch branch) {
		return branches.remove(branch);
	}

	public boolean removeNode(Node node) {
		return nodes.remove(node);
	}

	public boolean validateModel() {
		// TODO
		return true;
	}

	public Collection<Branch> getNodeForBranch(Node n) {
		ArrayList<Branch> list = new ArrayList<Branch>();
		for (Branch b : branches) {
			if (b.getFrom().equals(n) || b.getTo().equals(n)) {
				list.add(b);
			}
		}
		return list;
	}

	private Branch checkConnected(Branch b) {
		while (branches.contains(b)) {
			b.setId(b.getId() + 1);
		}
		return b;
	}
}

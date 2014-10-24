package com.pm.rap.toe.model;

import java.util.ArrayList;
import java.util.Collection;

import com.pm.rap.toe.mkt.MktSolution;

public class ChainModel {

	private final Collection<Node> nodes;
	private final Collection<Branch> branches;
	private final ArrayList<MktSolution> solutions = new ArrayList<MktSolution>();
	private MktSolution activeSolution = null;

	public ChainModel() {
		nodes = new ArrayList<Node>();
		branches = new ArrayList<Branch>();
	}

	public Branch addBranch(Branch branch) {
		branches.add(checkConnected(branch));
		return branch;
	}

	public Node addNode(Node node) {
		nodes.add(node);
		return node;
	}

	public void addSolution(MktSolution mktSolution) {
		solutions.add(mktSolution);
	}

	private Branch checkConnected(Branch b) {
		while (branches.contains(b)) {
			b.setId(b.getId() + 1);
		}
		return b;
	}

	public void clearSolutions() {
		activeSolution = null;
		solutions.clear();
	}

	public MktSolution getActiveSolution() {
		if (activeSolution == null) {
			if (!solutions.isEmpty()) {
				activeSolution = solutions.get(0);
			} else {
				throw new IllegalStateException(
						"There are no solutions for the model");
			}
		}
		return activeSolution;
	}

	public Collection<Branch> getBranches() {
		return branches;
	}

	public double getI(Branch branch) {
		return getActiveSolution().getIForBranch(branch);
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

	public Collection<Node> getNodes() {
		return nodes;
	}

	public int getSolutionCount() {
		return solutions.size();
	}

	public Collection<MktSolution> getSolutions() {
		return solutions;
	}

	public boolean removeBranch(Branch branch) {
		return branches.remove(branch);
	}

	public boolean removeNode(Node node) {
		return nodes.remove(node);
	}

	public void setActiveSolution(MktSolution solution) {
		activeSolution = solution;
	}

	public boolean validateModel() {
		// TODO
		return true;
	}
}

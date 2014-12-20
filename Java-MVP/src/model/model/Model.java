package model.model;

import model.Structures.Cell;
import model.Structures.Solution;

/*
 * interface Model
 * define all the methods of the models
 */

public interface Model {
	public void selectDomain(String domainName);
	public void selectAlgorithm(String AlgorithmName);
	public void solveDomain();
	public Solution getSolution();
	public void reset();
	public Cell[][] currentState();
}

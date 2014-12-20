package model.Heuristics;

import model.BoardDomain.AI_Domain;




public abstract class HeuristicsCommon implements HeuristicFunction {
	
	public abstract int calculateScore(AI_Domain game);
	
}

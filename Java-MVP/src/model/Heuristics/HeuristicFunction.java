package model.Heuristics;

import model.BoardDomain.AI_Domain;


/*
 * Interface
 * Define the way we calculate the score for the minimax algorithm compare
 */

public interface HeuristicFunction {
	
	public int calculateScore(AI_Domain game);
	
}

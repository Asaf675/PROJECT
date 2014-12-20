package model.AI;


import model.BoardDomain.AI_Domain;
import model.Heuristics.HeuristicFunction;
import model.Structures.Action;

/*
 * Abstract class MiniMaxCommon
 * contains all the common items that all MINIMAX algorithm versions share
 */

public abstract class MiniMaxCommon extends CommonAI {
	
	protected HeuristicFunction H; // heuristic function
	protected int depth; // depth of the "tree" that MINIMAX build while solving the problem
	
	//C'tor that gets the specific heuristic function and the depth of the tree
	public MiniMaxCommon(HeuristicFunction H,int depth) {
		this.H = H;
		this.depth = depth;
	}

	
public abstract Action BestMove(AI_Domain game);	
}
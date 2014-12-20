package model.AI;


import model.BoardDomain.AI_Domain;
import model.Structures.Action;

/*
 *  Interface
 *  AI to find the best move
 *  Two-dimensional array
*/ 

public interface AI {
	
	//returns the best move based on an artificial intelligence algorithm
	public Action BestMove(AI_Domain game);
	
}

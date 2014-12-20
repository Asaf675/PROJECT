package model.AI;

import model.BoardDomain.AI_Domain;
import model.Structures.Action;

/*
 * Abstract class CommonAI :
 * contains all the common items that the artificial intelligence 
 * algorithms share
 */


public abstract class CommonAI implements AI {
	
public abstract Action BestMove(AI_Domain game);

}

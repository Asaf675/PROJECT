package model.AI;

import java.util.ArrayList;

import model.BoardDomain.AI_Domain;
import model.Heuristics.HeuristicFunction;
import model.Structures.Action;
import model.Structures.Leaf;

/*
 * class MiniMax :
 * Inherits from MiniMaxCommon
 * Implements the method BestMove that returns an action which describe
 * the best possible move for a given problem by using 
 * the artificial intelligence algorithm minimax in its simple execution
 */


public class MiniMax extends MiniMaxCommon {
		
		//C'tor that gets the heuristic function and the depth of the tree
		// and send them to the super's C'tor
		public MiniMax(HeuristicFunction H,int depth) {
			super(H,depth);
		}
		
		//the implemented method :
		//gets AI_Domain and returns the best possible move based on minimax algorithm
		@Override
		public Action BestMove(AI_Domain game) {
			 return new Action(minimax(game,true,depth,true,null).getPath());
		}
		
		//the artificial intelligence algorithm that find the best possible move 
		// for a given problem based on the heuristic score
		private Leaf minimax(AI_Domain game,boolean turn,int depth, boolean flag,String path) {
			
			// if the depth is zero or the game is over,
			// return the heuristic value of this state
			if(depth == 0 || game.gameOver()){
				Leaf L = new Leaf(H.calculateScore(game));
				L.insertPath(path);
				return L;
			}
				
			
			ArrayList<String> moves = new ArrayList<String>();
			
			// get all the possible moves
			moves = game.allPossibleMoves(turn);

			Leaf val;
			Leaf bestVal = new Leaf(0);
			
			// its the maximizing  player turn
			if(turn) {
				// 	set the best value to be 0 (minus infinity)
					bestVal.setValue(0);
				
				// for each possible move
				for(String str : moves){
					
					//create a copy of the given problem
					AI_Domain copy = game.clone();		
					
					//execute possible move
					copy.executeCommand(str,"MiniMax");
				
						// if it's the first enter to the minimax :
						// set the path of the this root to be the first move that executed
						// and lead to the next leaves
						if(flag) {
							path = str;
							
							//recursive check the best move for the copied problem after executed the move
							val = minimax(copy,!turn,depth-1,false,path);
							// set the bestval to be the move that has higher heuristic score
							if( val.getValue() >= bestVal.getValue())
								bestVal = val.clone();
							}
						// not the first time entered to minimax
						else {
							
							//recursive check the best move for the copied problem after executed the move
							val = minimax(copy,!turn,depth-1,false,path);
							// set the bestval to be the move that has higher heuristic score
							if( val.getValue() >= bestVal.getValue())
							bestVal = val.clone();
	
						}	
					
					}
				
			}
				// it's the other player turn
			   else { 
				   // set the best value to be plus infinity
					bestVal.setValue(Integer.MAX_VALUE-1);
					for(String str : moves){
						
						//create a copy of the given problem
						AI_Domain copy = game.clone();
						//execute possible move
						copy.executeCommand(str,"MiniMax");
						
						//recursive check the best move for the copied problem after executed the move
						val = minimax(copy,!turn,depth-1,false,path);
						// we assume that the other player will try to maximize his score too
						//and therefore it will minimize our score
						if(val.getValue() < bestVal.getValue())
						  bestVal = val.clone();

			   	}	
			   }
			
			
			return bestVal;		
	}
}


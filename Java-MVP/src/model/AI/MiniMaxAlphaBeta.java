package model.AI;


import java.util.ArrayList;

import model.BoardDomain.AI_Domain;
import model.Heuristics.HeuristicFunction;
import model.Structures.Action;
import model.Structures.Leaf;

/*
 * class MiniMaxAlphaBeta :
 * Inherits from MiniMaxCommon
 * Implements the method BestMove that returns an action which describe
 * the best possible move for a given problem by using 
 * the artificial intelligence algorithm minimaxAlphaBeta 
 * cuts leafs based on the given knowledge it has at the moment
 * and save a lot of time by skipping possible moves that are not worthy
 */


public class MiniMaxAlphaBeta extends MiniMaxCommon {
	
	
	//C'tor that gets the heuristic function and the depth of the tree
	// and send them to the super's C'tor
	 public MiniMaxAlphaBeta(HeuristicFunction H,int depth) {
		super(H,depth);
	}
	
	//the implemented method :
	//gets AI_Domain and returns the best possible move based on minimaxAlphaBeta algorithm
	@Override
	public Action BestMove(AI_Domain game) {
	// sends to AlphaBeta the problem, alpha as 0 (minus infinity ), beta as plus infinity
	return new Action( AlpaBeta(game,new Leaf(Integer.MIN_VALUE+10),new Leaf(Integer.MAX_VALUE-10),true,depth,true,null).getPath() );
	}
	
	//the artificial intelligence algorithm that find the best possible move 
	// for a given problem based on the heuristic score
	private Leaf AlpaBeta(AI_Domain game,Leaf alpa,Leaf beta,boolean turn,int depth, boolean flag,String path) {
		
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
			
			//creating a temp leaf that will hold the returned value of the recursion
			Leaf temp;
			
			// its the maximizing  player turn
			if(turn) {
				
				//for each possbile move
				for(String str : moves) {
					//create a copy of the given problem
					AI_Domain copy = game.clone();
					
					//execute possible move
					copy.executeCommand(str,"MiniMaxAlphaBeta");
						
						// if it's the first enter to the AlphaBeta :
						// set the path of the this root to be the first move that executed
						// and lead to the next leaves
						if(flag) {
							path = str;
							
							//recursive check the best move for the copied problem after executed the move
							temp = AlpaBeta(copy,new Leaf(alpa),new Leaf(beta),!turn,depth-1,false,path);
							
							// set alpha to be the move that has higher heuristic score
							if( alpa.getValue() < temp.getValue() ) 
								alpa =  temp.clone();
							
							// if alpha has higher value than beta
							//cut the loop since that part of the tree is not worthy and will waste time
							if(alpa.getValue() >= beta.getValue()) 
								break;
							
							}
							
						// not the first time entered to AlphaBeta
						else {
							
							//recursive check the best move for the copied problem after executed the move
							temp = AlpaBeta(copy,new Leaf(alpa),new Leaf(beta),!turn,depth-1,false,path);
							
							// set alpha to be the move that has higher heuristic score
							if( alpa.getValue() < temp.getValue() ) 
								alpa = temp.clone();
								
							// if alpha has higher value than beta
							//cut the loop since that part of the tree is not worthy and will waste time
							if( alpa.getValue() >= beta.getValue()) 
								break;
							
						}
				}
				
			return alpa;
			}


			  // it's the other player turn
			   else { 
				   
				   for(String str : moves) {
					   
					   //create a copy of the given problem
						AI_Domain copy = game.clone();
						//execute possible move
						copy.executeCommand(str,"MiniMaxAlphaBeta");
						
						//recursive check the best move for the copied problem after executed the move
						temp=AlpaBeta(copy,new Leaf(alpa),new Leaf(beta),!turn,depth-1,false,path);
						
						// we assume that the other player will try to maximize his score too
						//and therefore it will minimize our score
			        	if(beta.getValue() > temp.getValue()) 
			        		beta = temp.clone();
			        	
						// if alpha has higher value than beta
						//cut the loop since that part of the tree is not worthy and will waste time
					    if(beta.getValue() <= alpa.getValue()) 
					    	break;

			   	}
				   return beta;
			  }
		}

	}


package model.BoardDomain;

import java.util.ArrayList;

import model.Structures.Cell;


/*
 * Interface
 * the game model
 * defines all the method that has to be implemented so the AI algorithm will be able
 * to solve the domain
 */

public interface AI_Domain {
	
	// Execute the command that sent - have to be able to execute the commands from allPossibleMoves
	// arg is an option to add information about the object that used the method
	public void executeCommand(String command,Object arg);
	
	// get the player's move
	public void getPlayerAction();
	
	// returns all the possible moves for a given board and who takes the turn
	// true for player1, false for player2
	public ArrayList<String> allPossibleMoves(boolean whoPlays);
	
	// return a copy of a given board
	public AI_Domain clone();
	
	//returns true if the game is over, otherwise return false
	public boolean gameOver();
	
	// true for player1, false for player 2
	public boolean wonTheGame(boolean whoPlays);
	
	// returns the current status of the domain
	public Cell[][] getCurrentStatus();
	
	// reset the game to the beginning state
	public void reset();
	
}
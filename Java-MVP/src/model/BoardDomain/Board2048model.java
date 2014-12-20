package model.BoardDomain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import model.Structures.Cell;

/*
 * class Board2048model :
 * Inherits from BoardCommon
 * the game 2048
 */

public class Board2048model extends BoardCommon {
	
	private Cell2048 game[][]; // the board cells
	private int score; // the current score
	
	//C'tor that set by default size 4,4 and initializes the cells
	// puts two random values ( 2 or 4 ) in two different cells
	public Board2048model() {
		super(4, 4);
		game = new Cell2048[this.rows_size][this.columns_size];
		score = 0;
		
		for(int i = 0 ; i < rows_size; i++) {
			for(int j = 0; j < columns_size; j++) {
			game[i][j] = new Cell2048();
			}
		}
		
		Random rand = new Random();
		
		int value1 = rand.nextInt(this.columns_size);
		int value2 = rand.nextInt(this.rows_size);
		int value3 = rand.nextInt(this.columns_size);
		int value4=rand.nextInt(this.rows_size); 
		
		while ( (value1 == value3) && (value2 == value4) ) {
			value3 = rand.nextInt(this.columns_size);
			value4=rand.nextInt(this.rows_size); 
		}
		this.game[value1][value2].setValue(randomValue());
		this.game[value3][value4].setValue(randomValue());
		score = 0;
		freeCells -= 2;
		takenCells += 2;
		
	}	
	
	//Copy C'tor
	public Board2048model(Board2048model original) {
		super(original); 
		
		this.game = new Cell2048[rows_size][columns_size];
		
		for(int i = 0 ; i < rows_size; i++) {
			for(int j = 0; j < columns_size; j++)
			game[i][j] = new Cell2048((original.GETslot(i, j)));
		}
		this.score = original.GETscore();
	}
	
	public int GETslot(int row, int column) {
		return game[row][column].getValue();
	}
	
	protected void SETslot(int row, int column, int num) {
		if(GETslot(row,column ) == 0) {
			game[row][column].setValue(num);
			--freeCells;
			++takenCells;
		}
		else
			game[row][column].setValue(num);
			
	}
	
	// return 2 or 4 - 90% to get 2 and 10% to get 4
	private int randomValue() {
		Random rand = new Random();
		int val = rand.nextInt(10);
		if(val == 9)
			return 4;
		else
			return 2;
	}
	
	// *to change to STATE with 2 Coordinates (x,y)
	private Point randomIndex() {
		Random rand = new Random();
		int val1 = rand.nextInt(rows_size);
		int val2 = rand.nextInt(columns_size);
		while (!isEmpty(val1,val2)) {
			val1 = rand.nextInt(rows_size);
			val2 = rand.nextInt(columns_size);
		}
		return new Point(val1,val2);
	}
	
	// the computer's turn
	//get random index and set there random value ( 2 or 4 )
	private void computerTurn() {
		Point index = randomIndex();
		SETslot((int)index.getX(), (int)index.getY(), randomValue());
	}
	
	public int GETscore() {
		return score;
	}
	
	// set the flags in all cells to be false
	private void FalseFlag() {
		for(int i = 0; i < rows_size; i ++ ) {
			for(int j = 0; j < columns_size; j++)
				game[i][j].setFlag(false);
		}
	}
	
	// execute the move up option
	// returns true is succeed, false if is not able to move up
	private boolean moveUP() {
		int i,j,k;
		boolean flag = false;
		for(i = 0 ; i < rows_size; i++) {
			for( j = 0; j < columns_size; j++) {
				if( !isEmpty(i,j) ) {
					for(k = i; k >= 0 && isEmpty(k-1,j); k--);
						if(k == 0) {
							if(k != i) {
							game[k][j] = new Cell2048(game[i][j]);
							game[i][j].setValue(0);
							flag = true;
							}
						}
						else {
							if(game[i][j].isEqual(game[k-1][j]) && game[k-1][j].getFlag() == false) {
								game[k-1][j].setValue(game[k-1][j].getValue()*2);
								game[k-1][j].setFlag(true);
								game[i][j].setValue(0);
								score += game[k-1][j].getValue();
								--takenCells;
								++freeCells;
								flag = true;
							}
							else {
								if(k != i) {
								game[k][j] = new Cell2048 (game[i][j]);
								game[i][j].setValue(0);
								flag = true;
								}
							}
						}
					}
				}
			}
		FalseFlag();
		return flag;
	}
	
	// execute the move down option
	// returns true if succeed, false if is not able to move down
	private boolean moveDOWN() {
		int i,j,k;
		boolean flag = false;
		
		for(i = (rows_size-1) ; i >= 0 ; i--) {
			for( j = (columns_size -1); j >= 0; j--) {
				if( !isEmpty(i,j) ) {
					for(k = i; k < rows_size && isEmpty(k+1,j); k++);
					if(k == (rows_size-1)) {
							if(k != i) {
							game[k][j] = new Cell2048(game[i][j]);
							game[i][j].setValue(0);
							flag = true;
							}
						}
						else {
							if(game[i][j].isEqual(game[k+1][j]) && game[k+1][j].getFlag() == false) {
								game[k+1][j].setValue(game[k+1][j].getValue()*2);
								game[k+1][j].setFlag(true);
								game[i][j].setValue(0);
								score += game[k+1][j].getValue();
								--takenCells;
								++freeCells;
								flag = true;
							}
							else {
									if(k != i) {
									game[k][j] = new Cell2048(game[i][j]);
									game[i][j].setValue(0);
									flag = true;

						}
					}
				}
			}
		}		
	}
	FalseFlag();
	return flag;
	}
	
	// execute the move right option
	//returns true if succeed false if is not able to move right
	public boolean moveRIGHT() {
		int i,j,k;
		boolean flag = false;
		
		for(i = (rows_size-1) ; i >= 0 ; i--) {
			for( j = (columns_size-1); j >= 0 ; j--) {
				if( !isEmpty(i,j) ) {
					for(k = j; k < columns_size && isEmpty(i,k+1); k++);
						if(k == (columns_size-1)) {					
							if(k != j) {
							game[i][k] = new Cell2048(game[i][j]);
							game[i][j].setValue(0);
							flag = true;
							}
						}
						else {
							if(game[i][j].isEqual(game[i][k+1]) && game[i][k+1].getFlag() == false) {
								game[i][k+1].setValue(game[i][k+1].getValue()*2);
								game[i][k+1].setFlag(true);
								game[i][j].setValue(0);
								score += game[i][k+1].getValue();
								--takenCells;
								++freeCells;
								flag = true;
							}
							else {
								if(k != j) {
								game[i][k] = new Cell2048(game[i][j]);
								game[i][j].setValue(0);
								flag = true;
							}
							}
								
							}
					}
				}
			}
	FalseFlag();	
	return flag;
	}
	
	// execute move left option
	//returns true if succeed false if is not able to move left
	public boolean moveLEFT() {
		int i,j,k;
		boolean flag = false;
		
		for(i = (rows_size-1) ; i >= 0 ; i--) {
			for( j = 0; j < columns_size; j++) {
				if( !isEmpty(i,j) ) {
					for(k = j; k >= 0 && isEmpty(i,k-1); k--);
						if(k == 0) {	
							if( k != j) {
							game[i][k] = new Cell2048(game[i][j]);
							game[i][j].setValue(0);
							flag = true;
							}
						}
						else {
							if(game[i][j].isEqual(game[i][k-1]) && game[i][k-1].getFlag() == false) {
								game[i][k-1].setValue(game[i][k-1].getValue()*2);
								game[i][k-1].setFlag(true);
								game[i][j].setValue(0);
								score += game[i][k-1].getValue();
								--takenCells;
								++freeCells;
								flag = true;
							}
							else {
								if( k != j) {
								game[i][k] = new Cell2048(game[i][j]);
								game[i][j].setValue(0);
								flag = true;
								}
							}
						}
					}
			}		
	}
	FalseFlag();	
	return flag;
	}
	
	// prints the current state of the board
	public void Print() {
		for(Cell2048[] a : game) {
			for(Cell2048 b : a) {
				System.out.print(b.getValue() + " ");
			}
			System.out.println();
		}
		System.out.println("score : "+ score);
	}
	
	// returns true if the game is over, false if not
	@Override
	public boolean gameOver() {
		// the game might be over only if the freecells is zero
		if(freeCells == 0) {
			// checks if there is any possible move
			return !((new Board2048model(this)).moveUP() || (new Board2048model(this)).moveDOWN()
					|| (new Board2048model(this)).moveLEFT() || (new Board2048model(this)).moveRIGHT());
		}
		else 
			return false;
	}
	
	@Override
	public boolean wonTheGame(boolean whoPlays)
	{
		if( score >= 2048) {
			if( theHighest() == 2048)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	//checks if the cell is empty
	public boolean isEmpty(int row, int column) {
		if(  (row < rows_size) && (row >= 0) && (column < columns_size) && (column >= 0) ) 
			return (game[row][column].getValue() == 0) ? true : false;
		
		else
			return false;
	}
	
	// returns Point object that contains the coordinates of given value
	public Point getCoordinates(int val) {
		for(int i = 0; i < rows_size; i++) {
			for(int j = 0; j < columns_size; j++) {
				if(game[i][j].getValue() == val)
					return new Point(i,j);
			}
		}
		return new Point(-1,-1);
	}
	
	//the player action is the computer's turn
	@Override
	public void getPlayerAction() {
		if(!gameOver())
			computerTurn();
	}

	// execute String command
	// executeCommand is able to execute every command that sent of "AllPossibleMoves" method
	@Override
	public void executeCommand(String command,Object arg) {	

		switch(command) {
		case "UP" : moveUP();
		break;
		case "DOWN" : moveDOWN();
		break;
		case "RIGHT" : moveRIGHT();
		break;
		case "LEFT" : moveLEFT();
		break;
		// computer's turn
		default : String arr[] = command.split(",");
		SETslot(Integer.parseInt( arr[0] ), Integer.parseInt( arr[1] ), Integer.parseInt(arr[2]));
		break;
		
		}
	}

	// return arraylist of strings that contains all the possible moves of the given player
	// true for player1, false for player2
	// player1 is the player in 2048, player2 is the computer
	@Override
	public ArrayList<String> allPossibleMoves(boolean whoPlays) {
		
		ArrayList<String> Moves = new ArrayList<String>();
		
		if(whoPlays) {
			if( (new Board2048model(this)).moveUP()) 
			Moves.add("UP");
			if( (new Board2048model(this)).moveDOWN()) 
			Moves.add("DOWN");
			if( (new Board2048model(this)).moveRIGHT()) 
			Moves.add("RIGHT");
			if( (new Board2048model(this)).moveLEFT()) 
			Moves.add("LEFT");
			}
		else  { 
			for( int i = 0; i < rows_size ; i++) {
				for(int j = 0; j < columns_size; j++) {
					if(isEmpty(i,j)) {
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + "," + Integer.toString(2)));
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + "," + Integer.toString(4)));
					}
				}
			}  
		}
	return Moves;
	}
	
	// finds the highest value in the board
	public int theHighest() {
		int highest = 0;
		for(int i = 0; i < rows_size; i++) {
			for(int j = 0; j < columns_size; j++) {
				if(game[i][j].getValue() > highest)
					highest = game[i][j].getValue();
			}
		}
		return highest;
	}
	
	// returns a clone of the object
	@Override
	public AI_Domain clone() {
		return new Board2048model(this);
	}

	@Override
	public Cell[][] getCurrentStatus() {
		
		return game;
	}
	
	//reset the game to the beginning state
	@Override
	public void reset() {
		for(int i = 0; i < rows_size; i++) {
			for(int j = 0; j < columns_size; j++)
				game[i][j].Clear();
		}
		computerTurn();
		computerTurn();
		freeCells = rows_size*columns_size - 2;
		takenCells = 2;
	}
	
	//convert the board to unique string
	@Override
	public String toString() {
		String str = new String();
		for(Cell2048[] a : game) {
			for(Cell2048 b : a)
				str += b.getValue();
		}
		return  Integer.toString(rows_size) + "," + Integer.toString(columns_size) +"," +str;
	}	
}

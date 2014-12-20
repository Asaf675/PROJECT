package model.BoardDomain;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import model.Structures.Cell;

/*
 * class BoardRPGmodel :
 * Inherits from BoardCommon
 * the game RPG is an old game of ICQ you can find video of the game : http://youtu.be/Q2sDtTbzHiI
 * **will add explanation about the game once i have more time(its 10minuts before deadline)
 */


public class BoardRPGmodel extends BoardCommon {
	
	private CellRPG game[][];
	private int red;
	 private int blue;
	private int LinesOfPlayers;
	
	//C'tor that sets by default the board to be 6,7
	public BoardRPGmodel() {
		super(6,7);
		
		game = new CellRPG[this.rows_size][this.columns_size];
		LinesOfPlayers = rows_size/3; // the way to determine how many lines of players should be
		red = blue = columns_size*LinesOfPlayers; // the number of the players at the beginning
		
		reset(); // sets the game to the beginning
		}

	// Copy C'tor
	public BoardRPGmodel(BoardRPGmodel original) {
		super(original);
		
		this.game = new CellRPG[rows_size][columns_size];
		this.red = original.getRedNum();
		this.blue = original.getBlueNum();
		this.LinesOfPlayers = original.getLinesOfPlayers();
		
		for(int i = 0 ; i < rows_size; i++) {
			for(int j = 0; j < columns_size; j++)
			game[i][j] = new CellRPG((original.getCell(new Point(i,j))));
		}
	}
	
	public int getLinesOfPlayers() {return LinesOfPlayers;}
	
	public int getRedNum() {return red;}
	
	public int getBlueNum() {return blue;}
	
	//prints the board
	public void Print() {
		for(CellRPG[] i : game) {
			
			if(i[0].isEmpty())
				System.out.print("    ");
			
			for(CellRPG j : i ) {
				j.Print();
				if(j.isEmpty())
					System.out.print("   ");
				else
					System.out.print("  ");
			}
			System.out.println("\n\n");
		}
		System.out.println("The number of red players is : " +red 
							+ "\n The number of blue players is : " + blue);
		System.out.println("\n = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
	}
	
	public CellRPG getCell(Point a) {
		return game[(int) a.getX()][(int) a.getY()];
	}
	
	public CellRPG getCell(int i, int j) {
		return game[i][j];
	}
	
	// return number random number between 1 to 3 that represents a weapon
	//  Scissors / Paper / Rock 
	private int randomWeapon() {
		Random rand = new Random();
		return rand.nextInt(3) +1;
	}
	// returns true if the cell is empty
	public boolean isEmpty(CellRPG cell ) {
		if( cell.getX() >= rows_size || cell.getY() >= columns_size )
			return false;
		else
		return cell.getDescription().equals("");
	}
	
	//returns true if the cell is empty
	public boolean isEmpty(int i, int j) {
		if( i >= rows_size || j >= columns_size )
			return false;
		else
			return getCell(new Point(i,j)).getDescription().equals("");
	}

	// returns 1 if cell1 won, -1 if cell2 won, 0 if its a draw and -2 if one of the cells is empty
	private int RockPaperOrScissors(CellRPG cell1, CellRPG cell2) {
		
		if(isEmpty(cell1) || isEmpty(cell2))
			return -2;
		
		if(cell1.getValue() == cell2.getValue())
			return 0;
		else {
			switch(cell1.getValue()) {
			
			case 1 : if(cell2.getValue() == 2) return 1; else return -1;
			case 2 : if(cell2.getValue() == 3) return 1; else return -1;
			case 3 : if(cell2.getValue() == 1) return 1; else return -1;
			}
		}
		
		return -1;
		
	}	
	
	// Handles a draw
	private int drawMode(CellRPG cell1, CellRPG cell2) {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		int value = -1;
		
		do {
				
			System.out.println("It's a draw, please enter Scissors, Paper or Rock ");
			
			try {
				String input = in.readLine();
				value = StringCommandToInt(input);
					
			} catch (IOException e) {e.printStackTrace();}
				
		}while(value == -1);
			
		if( cell1.getDescription().equals("RED")) { 	// cell1 is the computer
			cell1.setValue(randomWeapon());
			cell2.setValue(value);
		}
		else { // cell2 is the computer
			cell2.setValue(randomWeapon());
			cell1.setValue(value);
			}
			
		return RockPaperOrScissors(cell1, cell2);
	}
	
	//convert string to int that represent weapon
	private int StringCommandToInt(String input) {
		
		switch(input) {
		case "Scissors" : return 1;
		case "Paper" : return 2;
		case "Rock" : return 3;
		}
		return -1;
	}
	
	// switch the details between two different cells but keeps the original coordinates
	private void switchCells(CellRPG cell1, CellRPG cell2) {
		if( !cell1.isEqual(cell2) ) {
		CellRPG temp = new CellRPG(cell2);
		
		cell2.setDescription(new String(cell1.getDescription()));
		cell2.setValue(cell1.getValue());
		cell2.setFlag(cell1.getFlag());
		
		cell1.setDescription(new String(temp.getDescription()));
		cell1.setValue(temp.getValue());
		cell1.setFlag(temp.getFlag());
		}
	}
	
	// reduce the number of the player in the given team
	private void reducePlayer(String team) {
		if(team.equals("RED")) 
			--red;
		else
			--blue;
	}
	
	// execute the move up option
	// returns true is succeed, false if is not able to move up
	private boolean moveUP(int i, int j) {
		
		//if moveUP is possible
		if( isMoveUpPossible(i,j) != 0 ) {
			
			//the top cell is empty, switch cells
			if(isEmpty(i+1,j)) {
				switchCells(game[i][j],game[i+1][j]);
				return true;
			}
			//the next top cell is taken, checks if they are from the same team
			else if(!game[i+1][j].getDescription().equals(game[i][j].getDescription())) {
				if(game[i+1][j].getValue() == 5) {
					String team = game[i+1][j].getDescription();
					game[i+1][j].setDescription(team +"LOST");
					return true;
				}
				else if(game[i+1][j].getValue() == 4) { // it's the BLOCK
					reducePlayer(game[i][j].getDescription());
					game[i][j].Clear();
					return true;
				}
				else { // Rock Paper or Scissors fight!
					int temp = RockPaperOrScissors(game[i][j], game[i+1][j]);
					
					if( temp == 0) { // its a draw
						int result;
						do {
							result = drawMode(game[i][j],game[i+1][j]);
						}while(result == 0); // while it's a draw
						temp = result; // won't be a draw anymore
					}
					
					if( temp == 1) { // game[i][j] won the fight
						reducePlayer(game[i+1][j].getDescription());
						game[i+1][j] = new CellRPG(game[i][j]);
						game[i][j].Clear();
						return true;
					}
					else if( temp == -1) { // game[i][j] lost the fight
						reducePlayer(game[i][j].getDescription());
						game[i][j].Clear();
						return true;
					}
				}
			}
		}
		return false;
	}

	// execute the move down option
	//returns true if succeed, false if is not able to move down
	private boolean moveDOWN(int i, int j) {
		
		//if moveDOWN is possible
		if( isMoveDownPossible(i, j) != 0 ) {
			
			//the bottom cell is empty, switch cells
			if(isEmpty(i-1,j)) {
				switchCells(game[i][j],game[i-1][j]);
				return true;
			}
			//the next top cell is taken, checks if they are from the same team
			else if(!game[i-1][j].getDescription().equals(game[i][j].getDescription())) { 
				
				if(game[i-1][j].getValue() == 5) { 
					String team = game[i-1][j].getDescription();
					game[i-1][j].setDescription(team +"LOST");
					return true;
				}
				else if(game[i-1][j].getValue() == 4) { // it's the BLOCK
					reducePlayer(game[i][j].getDescription());
					game[i][j].Clear();
					return true;
				}
				else { // Rock Paper or Scissors fight!
					int temp = RockPaperOrScissors(game[i][j], game[i-1][j]);
					
					if( temp == 0) { // its a draw
						int result;
						do {
							result = drawMode(game[i][j],game[i-1][j]);
						}while(result == 0); // while it's a draw
						temp = result; // won't be a draw anymore
					}
					
					if( temp == 1) { // game[i][j] won the fight
						reducePlayer(game[i-1][j].getDescription());
						game[i-1][j] = new CellRPG(game[i][j]);
						game[i][j].Clear();
						return true;
					}
					else if( temp == -1) { // game[i][j] lost the fight
						reducePlayer(game[i][j].getDescription());
						game[i][j].Clear();
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// execute the move right option
	//returns true if succeed false if is not able to move right
	private boolean moveRIGHT(int i, int j) {
		
		//if moveRIGHT is possible
		if( isMoveRightPossible(i, j) != 0 ) {
			
			//the right cell is empty, switch cells
			if(isEmpty(i,j+1)) {
				switchCells(game[i][j],game[i][j+1]);
				return true;
			}
			//the next right cell is taken, checks if they are from the same team
			else if(!game[i][j+1].getDescription().equals(game[i][j].getDescription())) { 
				if(game[i][j+1].getValue() == 5) { 
					String team = game[i][j+1].getDescription();
					game[i][j+1].setDescription(team +"LOST");
					return true;
				}
				else if(game[i][j+1].getValue() == 4) { // it's the BLOCK
					reducePlayer(game[i][j].getDescription());
					game[i][j].Clear();
					return true;
				}
				else { // Rock Paper or Scissors fight!
					int temp = RockPaperOrScissors(game[i][j], game[i][j+1]);
					
					if( temp == 0) { // its a draw
						int result;
						do {
							result = drawMode(game[i][j],game[i][j+1]);
						}while(result == 0); // while it's a draw
						temp = result; // won't be a draw anymore
					}
					
					if( temp == 1) { // game[i][j] won the fight
						reducePlayer(game[i][j+1].getDescription());
						game[i][j+1] = new CellRPG(game[i][j]);
						game[i][j].Clear();
						return true;
					}
					else if( temp == -1) { // game[i][j] lost the fight
						reducePlayer(game[i][j].getDescription());
						game[i][j].Clear();
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// execute move left option
	//returns true if succeed false if is not able to move left
	private boolean moveLEFT(int i, int j) {

			
		//if moveLEFT is possible
		if( isMoveLeftPossible(i, j) != 0  ){
			
			//the left cell is empty, switch cells
			if(isEmpty(i,j-1)) {
				switchCells(game[i][j],game[i][j-1]);
				return true;
			}
			//the next left cell is taken, checks if they are from the same team
			else if(!game[i][j-1].getDescription().equals(game[i][j].getDescription())) { 
				if(game[i][j-1].getValue() == 5) { // won the game!
					String team = game[i][j-1].getDescription();
					game[i][j-1].setDescription(team+ "LOST");
					return true;
				}
				else if(game[i][j-1].getValue() == 4) { // it's the BLOCK
					reducePlayer(game[i][j].getDescription());
					game[i][j].Clear();
					return true;
				}
				else { // Rock Paper or Scissors fight!
					int temp = RockPaperOrScissors(game[i][j], game[i][j-1]);
					
					if( temp == 0) { // its a draw
						int result;
						do {
							result = drawMode(game[i][j],game[i][j-1]);
						}while(result == 0); // while it's a draw
						temp = result; // won't be a draw anymore
					}
					
					if( temp == 1) { // game[i][j] won the fight
						reducePlayer(game[i][j-1].getDescription());
						game[i][j-1] = new CellRPG(game[i][j]);
						game[i][j].Clear();
						return true;
					}
					else if( temp == -1) { // game[i][j] lost the fight
						reducePlayer(game[i][j].getDescription());
						game[i][j].Clear();
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// execute String command
	// executeCommand is able to execute every command that sent of "AllPossibleMoves" method
	@Override
	public void executeCommand(String command,Object arg) {
		
		String arr[] = command.split(",");
		int i = Integer.parseInt(arr[0]);
		int j = Integer.parseInt(arr[1]);
		
			switch(arr[2]) {
			case "UP" : if(arr.length == 3 || arg == null)  moveUP(i,j);
						else if (arr[3].equals("LOSE")){ reducePlayer(game[i][j].getDescription()); game[i][j].Clear();}
						else {reducePlayer(game[i+1][j].getDescription()); game[i+1][j] = new CellRPG(game[i][j]); game[i][j].Clear(); }
			break;
			case "DOWN" : if(arr.length == 3 || arg == null)  moveDOWN(i,j);
						  else if(arr[3].equals("LOSE")){ reducePlayer(game[i][j].getDescription()); game[i][j].Clear();}
						  else {reducePlayer(game[i-1][j].getDescription()); game[i-1][j] = new CellRPG(game[i][j]); game[i][j].Clear(); }
			break;
			case "RIGHT" : if(arr.length == 3 || arg == null)  moveRIGHT(i,j);
						   else if(arr[3].equals("LOSE")){ reducePlayer(game[i][j].getDescription()); game[i][j].Clear();}
						   else {reducePlayer(game[i][j+1].getDescription()); game[i][j+1] = new CellRPG(game[i][j]); game[i][j].Clear(); }
			break;
			case "LEFT" : if(arr.length == 3 || arg == null)  moveLEFT(i,j);
						  else if(arr[3].equals("LOSE")){ reducePlayer(game[i][j].getDescription()); game[i][j].Clear();}
						  else {reducePlayer(game[i][j-1].getDescription()); game[i][j-1] = new CellRPG(game[i][j]); game[i][j].Clear(); }
			break;
			}
		}

	// return arraylist of strings that contains all the possible moves of the given player
	// true for player1, false for player2
	@Override
	public ArrayList<String> allPossibleMoves(boolean whoPlays) {
		
		ArrayList<String> Moves = new ArrayList<String>();
		
		for( int i = 0; i < rows_size ; i++) {
			for(int j = 0; j < columns_size; j++) {
				 // whoPlays == true -> it's the red's turn and therefore skip blue players
				if(whoPlays && game[i][j].getDescription().equals("BLUE")) {
					continue;
				}
				
				 // whoPlays == false -> it's the blues's turn and therefore skip red players
				else if(!whoPlays &&  game[i][j].getDescription().equals("RED") ) {
					continue;
				}
				
				if( !isEmpty(i,j) ) {
					int result;
					
					result = isMoveUpPossible(i, j);
					if( result == 1 ) 
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",UP"));
					else if(result == -1) {
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",UP,LOSE"));
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",UP,WIN"));
					}
					
					result = isMoveDownPossible(i, j);
					if( result == 1 ) 
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",DOWN"));
					else if( result == -1 ) {
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",DOWN,LOSE"));
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",DOWN,WIN"));
					}
						
					result = isMoveRightPossible(i, j);
					if( result == 1 ) 
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",RIGHT"));
					else if ( result == -1 ) {
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",RIGHT,LOSE"));
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",RIGHT,WIN"));
					}
					
					result = isMoveLeftPossible(i, j);
					if( result == 1 ) 
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",LEFT"));
					else if( result == -1 ) {
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",LEFT,LOSE"));
						Moves.add(new String(Integer.toString(i) + "," + Integer.toString(j) + ",LEFT,WIN"));
					}
				}
			}
		}  
		return Moves;
	}
	
	// return arraylist of strings that contains all the possible moves of the given player
	// this method refers to specific player in a specific cell
	 private ArrayList<String> specificPossibleMoves(int i, int j, boolean whoPlays) {
		
		ArrayList<String> Moves = new ArrayList<String>();
		
		if( !isEmpty(i,j) ) {
			
			if( isMoveUpPossible(i, j) != 0 ) {
				if(whoPlays) // the player's turn
					Moves.add("DOWN");
				else
					Moves.add("UP");
			}
			
			if( isMoveDownPossible(i, j) != 0 ) {
				if(whoPlays) // the player's turn
					Moves.add("UP");
				else
					Moves.add("DOWN");
			}
			
			if( isMoveRightPossible(i, j) != 0 ) 
				Moves.add("RIGHT");
			
			if( isMoveLeftPossible(i, j) != 0 ) 
				Moves.add("LEFT");
		}
		return Moves;
	}
	
	//returns 1 if possible, 0 if impossible, -1 if possible but will be a draw
	private int isMoveUpPossible(int i, int j) {
		if(!isEmpty(i,j) 
				&& i+1 < rows_size
					&& !game[i][j].isEqual(game[i+1][j])
						&& !(game[i][j].getValue() == 4)
							&& !(game[i][j].getValue() == 5)
								&& !game[i][j].getDescription().equals("FLAG") 
									&& !game[i][j].getDescription().equals("BLOCK") ) {
			
			if( RockPaperOrScissors(game[i][j], game[i+1][j]) == 0) 
			    return -1;
			else
				return 1;
		}
		else
			return 0;
	}
	
	//returns 1 if possible, 0 if impossible, -1 if possible but will be a draw
	private int isMoveDownPossible(int i, int j) {
		if( !isEmpty(i,j)
				&& i-1 >= 0
					&& !game[i][j].isEqual(game[i-1][j])
						&& !(game[i][j].getValue() == 4)
							&& !(game[i][j].getValue() == 5) 
								&& !game[i][j].getDescription().equals("FLAG") 
									&& !game[i][j].getDescription().equals("BLOCK") ) {
			
			if( RockPaperOrScissors(game[i][j], game[i-1][j]) == 0) 
		    	return -1;
			else
				return 1;
		}
		else 
			return 0;
	}
	
	//returns 1 if possible, 0 if impossible, -1 if possible but will be a draw
	private int isMoveRightPossible(int i, int j) {
		if( !isEmpty(i,j)
				&& j+1 < columns_size
					&& !game[i][j].isEqual(game[i][j+1])
						&& !(game[i][j].getValue() == 4)
							&& !(game[i][j].getValue() == 5)
								&& !game[i][j].getDescription().equals("FLAG") 
									&& !game[i][j].getDescription().equals("BLOCK") ) {

			if( RockPaperOrScissors(game[i][j], game[i][j+1]) == 0) 
		    	return -1;
			else
				return 1;
		}
		else
			return 0;
	}
	
	//returns 1 if possible, 0 if impossible, -1 if possible but will be a draw
	private int isMoveLeftPossible(int i, int j) {
		if( !isEmpty(i,j) 
				&& j-1 >= 0
					&& !game[i][j].isEqual(game[i][j-1])
						&& !(game[i][j].getValue() == 4)
							&& !(game[i][j].getValue() == 5)
								&& !game[i][j].getDescription().equals("FLAG") 
									&& !game[i][j].getDescription().equals("BLOCK") ){

			if( RockPaperOrScissors(game[i][j], game[i][j-1]) == 0) 
		    	return -1;
			else
				return 1;
		}
		else
			return 0;
	}
	
	// returns a clone of this object
	@Override
	public AI_Domain clone() {
		return new BoardRPGmodel(this);
	}

	// returns true if the game is over
	@Override
	public boolean gameOver() {
		return
		   ( game[0][0].getDescription().contains("LOST") || game[rows_size-1][columns_size-1].getDescription().contains("LOST") )
			  ? true
				: false;
	}

	// whoPlays : true for red, false for blue
	@Override
	public boolean wonTheGame(boolean whoPlays) {
		// checking if the red team won and the blue's flag has the mark LOST
		if( game[rows_size-1][columns_size-1].getDescription().equals("LOST") && whoPlays)
			return true;
		// checking if the blue team won and the red's flag has the mark LOST
		else if(game[0][0].getDescription().equals("LOST") && !whoPlays) 
			return true;
		else 
			return false;
	}
	
	// handles the player's turn
	@Override
	public void getPlayerAction() {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		String input;
		boolean flag = true;
		
		do {
		
			System.out.println("Enter the coordinates of the player you want to move \n" +
								"you play the blue");
			System.out.println("For example : 3,4");
			
		try {
			input = in.readLine();
			String arr[] = input.split(",");
			
			if(arr.length > 1 ) {
				int i = Integer.parseInt( arr[0] );
				int j = Integer.parseInt(arr[1]);
				
				if(game[i][j].getDescription().equals("BLUE")) {
					ArrayList<String> moves = specificPossibleMoves(i, j,true);
					if (moves.size() > 0) {
						do {
							System.out.println("Please enter the move you would like to do : ");
							System.out.println("The possible moves are : \n"
									+ moves);
							
							input = in.readLine();
							if( moves.contains(input)) {
								switch(input) {
								case "UP" : moveDOWN(i,j); flag = true;
								break;
								case "DOWN" : moveUP(i,j); flag = true;
								break;
								case "RIGHT" : moveRIGHT(i,j); flag = true;
								break;
								case "LEFT" : moveLEFT(i,j); flag = true;
								break;
								default : flag = false;
								}
							}
							else {
								System.out.println("impossible move, please enter again");
								flag = false;
							} 
						} while(!flag);
							
					}
					else {
						System.out.println("not move-able");
						flag = false;
					}
				}
				
				else
					flag = false;
			}
			else
				flag = false;
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}while(!flag);
		

	}

	// returns the current status of the board
	@Override
	public Cell[][] getCurrentStatus() {
		return game;
	}
	
	// reset the game to the beginning point
	@Override
	public void reset() {
		
		int weaponsRED[] = new int[4];
		int weaponsBLUE[] = new int[4];
		
		Random rand = new Random();
		int random;
		
		//the flag for RED's
		game[0][0] = new CellRPG(0,0,"RED",5);
		// the flag for BLUE's
		game[rows_size-1][columns_size-1] = new CellRPG(rows_size-1,columns_size-1,"BLUE",5);
		
		for(int i = 0; i < rows_size; i++) {
			for(int j = 0; j < columns_size; j++) {
				//these cells have been created
				if( ( (i == 0 && j == 0) ) || ( ( i == rows_size -1 ) && ( j ==  columns_size -1 ) ) ) {
					continue;
				}
				// creating the red's players
				else if(i < 2) {
					// get random number between 1 to 4 which represents weapon : scissors or rock or paper or a Block
					// makes sure that there will be equal number of each weapon
					do{
					random = rand.nextInt(4) + 1;
					}
					while(weaponsRED[random-1] >= ( ((columns_size*LinesOfPlayers)- 2 ) / 3 ) ||
							( (weaponsRED[3] == 1) && random == 4 ) ); 
					
						weaponsRED[random-1]++;
					game[i][j] = new CellRPG(i, j, "RED",random);
				}
				// creating the blue's players
				else if(i > (rows_size-3)) {
					// get random number between 1 to 4 which represents weapon : scissors or rock or paper or a Block
					// makes sure that there will be equal number of each weapon
					do
					random = rand.nextInt(4) + 1;
					while(weaponsBLUE[random-1] >= ( ((columns_size*LinesOfPlayers)- 2 ) / 3 ) ||
							( (weaponsBLUE[3] == 1) && random == 4 ) );
					
						weaponsBLUE[random-1]++;
					game[i][j] = new CellRPG(i,j,"BLUE",random);
				}
				// empty cells
				else
					game[i][j] = new CellRPG(i,j,"",0);
			}
		}
		}
	
}

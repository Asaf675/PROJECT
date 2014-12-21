package model.BoardDomain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.Structures.Cell;


public class BoardTicTacToeModel extends BoardCommon {
	
	private CellTicTacToe[][] game;
	HashMap<String, Point> map;
	int score;
	
	//constructor
     public BoardTicTacToeModel(){
		super(3,3);
		this.game=new CellTicTacToe[this.rows_size][this.columns_size];
		this.map=new HashMap<String, Point>();
		this.score=0;
		String str=new String();
		for(int i=0;i<this.rows_size;i++)
		{
			for(int j=0;j<this.columns_size;j++)
			{
				this.game[i][j]=new CellTicTacToe();
				str=Integer.toString(i)+","+Integer.toString(j)+","+"X";
				this.map.put(str, new Point(i,j));
				str=Integer.toString(i)+","+Integer.toString(j)+","+"O";
				this.map.put(str, new Point(i,j));
			}
		}
	}
     
     //copy constructor
     public BoardTicTacToeModel(BoardTicTacToeModel board)
     {
    	 super(board);
    	 this.score=board.score;
    	 this.map=board.map;
    	 this.game=new CellTicTacToe[this.rows_size][this.columns_size];
    	 for(int i=0;i<this.rows_size;i++)
    	 {
    		 for(int j=0;j<this.columns_size;j++)
    		 {
    			 this.game[i][j]=new CellTicTacToe(board.getSlot(i, j));
    		 }
    	 }
     }
     
     //the function get indexes i and j =(i,j)=Coordinates return the value of this cell
     public char getSlot(int i,int j){
    		 return this.game[i][j].getTav();
     }
     
     //the function get indexes i and j =(i,j)=Coordinates and tav- X or O and change the value of this cell  to this tav
     public void setSlot(int i,int j,char newTav){
    	 if((newTav=='X' || newTav=='O' ) && (isEmpty(i, j)))
    	 {
    		 this.game[i][j].setTav(newTav);
    		 this.takenCells++;
    		 this.freeCells--;
    	 }
     }
     
     //the function get indexes i and j =(i,j)=Coordinates and return true if the cell empty else return false
     public boolean isEmpty(int i,int j){
    	 if((i>=0 && i<this.rows_size) && (j>=0 && j<this.columns_size))
    	 {
    		 if(this.game[i][j].getTav()=='*')
    			 return true;
    		 else
    			 return false;
    	 }
    	 else
    	 {
    		 return false;
    	 }
     }
     
     //the function print the board
     public void Print(){
    	 for(int i=0;i<this.rows_size;i++){
    		 for(int j=0;j<this.columns_size;j++)
    		 {
    			 System.out.print(this.game[i][j].getTav()+"  ");
    		 }
    		 System.out.println();
    	 }
     }
     
     //the function check if the board is full,if yes return true else return fasle
     public boolean isFull()
     {
    	 for(int i=0;i<this.rows_size;i++)
    	 {
    		 for(int j=0;j<this.columns_size;j++) 
    		 {
    			 if(isEmpty(i, j))
    				 return false;
    		 }
    	 }
    	 return true;
     }
     
     //the function get command from all possible moves and execute her on the board
	@Override
	public void executeCommand(String command,Object arg) {

		Point temp=this.map.get(command);
		String arr[]=command.split(",");
		setSlot((int)temp.getX(), (int)temp.getY(),arr[2].charAt(0));

	}

	//the function get flag
	//true-first player
	//false- second player
	//the function return array list of all the possible moves of the player in this board
	@Override
	public ArrayList<String> allPossibleMoves(boolean whoPlays) {
		ArrayList<String> arr=new ArrayList<String>();
		if(whoPlays)
		{
			for(int i=0;i<this.rows_size;i++)
			{
				for(int j=0;j<this.columns_size;j++)
				{
					if(isEmpty(i, j))
					{
						arr.add(new String(Integer.toString(i)+","+Integer.toString(j)+","+"X"));
					}
				}
			}
		}
		else
		{
			for(int i=0;i<this.rows_size;i++)
			{
				for(int j=0;j<this.columns_size;j++)
				{
					if(isEmpty(i, j))
					{
						arr.add(new String(Integer.toString(i)+","+Integer.toString(j)+","+"O"));
					}
				}
			}
		}
		return arr;
	}
  
	
    //if X win return 1 ,if he lose return -1 else return 0
	public int isWon()
	{
		int mone1x=0;
		int mone1o=0;
		int mone2x=0;
		int mone2o=0;
        for(int i=0;i<this.rows_size;i++)
        {
        	for(int j=0;j<this.columns_size;j++)
        	{
        		if(this.game[i][j].getTav()=='X')
        			mone1x++;
        		if(this.game[i][j].getTav()=='O')
        			mone1o++;
        		if(this.game[j][i].getTav()=='X')
        			mone2x++;
        		if(this.game[j][i].getTav()=='O')
        			mone2o++;
        	}
        	if(mone1x==this.rows_size)
        	{
        		return 1;
        	}
        	if(mone2x==this.rows_size)
        	{
        		return 1;
        	}
        	if(mone1o==this.rows_size)
        	{
        		return -1;
        	}
        	if(mone2o==this.rows_size)
        	{
        		return -1;
        	}
    		 mone1x=0;
    		 mone1o=0;
    		 mone2x=0;
    		 mone2o=0;
        }
        for(int i=0;i<this.rows_size;i++)
        {
        	if(this.game[i][i].getTav()=='X')
        		mone1x++;
        	if(this.game[i][i].getTav()=='O')
        		mone1o++;
        	if(this.game[i][this.rows_size-1-i].getTav()=='X')
        		mone2x++;
        	if(this.game[i][this.rows_size-1-i].getTav()=='O')
        		mone2o++;
        }
    	if(mone1x==this.rows_size)
    	{
    		return 1;
    	}
    	if(mone2x==this.rows_size)
    	{
    		return 1;
    	}
    	if(mone1o==this.rows_size)
    	{
    		return -1;
    	}
    	if(mone2o==this.rows_size)
    	{
    		return -1;
    	}
        return 0;
	}

	
	
	//the function return copy this board
	@Override
	public AI_Domain clone() {
		return new BoardTicTacToeModel(this);
	}

	//the function return true if the game is over else return false
	@Override
	public boolean gameOver() {
       if(isFull() || isWon()!=0)
       {
    	   return true;
       }
       else
    	   return false;
	}

	//the function return true if the player won else return false
	@Override
	public boolean wonTheGame(boolean whoPlays) {
        if(isWon()==1)
        	return true;
        else
        	return false;
	}
	

	//the function get from the player command and execute it on the board
	public void getPlayerAction(){
		if(!gameOver())
		{
		System.out.println("insert your first index");
		Scanner s = new Scanner(System.in);
		int keep1=s.nextInt();
		while(keep1<0 || keep1>=this.rows_size)
		{
			System.out.println("problem at the first index! ");
			System.out.println("insert again!");
			keep1=s.nextInt();
		}
		System.out.println("insert your second index");
		int keep2=s.nextInt();
		while(keep2<0 || keep2>=this.rows_size)
		{
			System.out.println("problem at the second index! ");
			System.out.println("insert again!");
			keep2=s.nextInt();
		}
		while(!isEmpty(keep1, keep2))
		{
			System.out.println("sorry the place is taken! insert again two index");
			this.Print();
			System.out.println("insert your first index");
			keep1=s.nextInt();
			System.out.println("insert your second index");
		    keep2=s.nextInt();
			while(keep1<0 || keep1>=this.rows_size)
			{
				System.out.println("problem at the first index! ");
				System.out.println("insert again!");
				keep1=s.nextInt();
			}
			while(keep2<0 || keep2>=this.rows_size)
			{
				System.out.println("problem at the second index! ");
				System.out.println("insert again!");
				keep2=s.nextInt();
			}
			
		}
		executeCommand(new String(Integer.toString(keep1)+","+Integer.toString(keep2)+","+"O"),null);
		}
	}

	//the function return the current board
	@Override
	public Cell[][] getCurrentStatus() {
		return this.game;
	}

	//the function reset the game to a new game
	@Override
	public void reset() {
		for(int i=0;i<this.rows_size;i++)
		{
			for(int j=0;j<this.columns_size;j++)
			{
				this.game[i][j].Clear();
			}
		}
		this.freeCells=this.rows_size*this.columns_size;
		this.takenCells=0;
		
	}

	//if X(Computer) Winner print-You are Loser!
	//if O(player) Winner print- You are Winner!
	//else print-Draw!
	public void printStatus() {
		
		if(gameOver())
		{
			if(isWon()==1)
				System.out.println("You are Loser!");
			else
			if(isWon()==-1)
				System.out.println("You are Winner!");
			else
				System.out.println("Draw!");
		}
		
	}

	
	public void getPlayerAction2(char tav){
		if(!gameOver())
		{
		System.out.println("insert your first index");
		Scanner s = new Scanner(System.in);
		int keep1=s.nextInt();
		while(keep1<0 || keep1>=this.rows_size)
		{
			System.out.println("problem at the first index! ");
			System.out.println("insert again!");
			keep1=s.nextInt();
		}
		System.out.println("insert your second index");
		int keep2=s.nextInt();
		while(keep2<0 || keep2>=this.rows_size)
		{
			System.out.println("problem at the second index! ");
			System.out.println("insert again!");
			keep2=s.nextInt();
		}
		while(!isEmpty(keep1, keep2))
		{
			System.out.println("sorry the place is taken! insert again two index");
			this.Print();
			System.out.println("insert your first index");
			keep1=s.nextInt();
			System.out.println("insert your second index");
		    keep2=s.nextInt();
			while(keep1<0 || keep1>=this.rows_size)
			{
				System.out.println("problem at the first index! ");
				System.out.println("insert again!");
				keep1=s.nextInt();
			}
			while(keep2<0 || keep2>=this.rows_size)
			{
				System.out.println("problem at the second index! ");
				System.out.println("insert again!");
				keep2=s.nextInt();
			}
			
		}
		Character c=tav;
		executeCommand(new String(Integer.toString(keep1)+","+Integer.toString(keep2)+","+c.toString()),null);
		}
	}

	@Override
	public String toString() {
		String str = new String();
		for(CellTicTacToe[] a : game) {
			for(CellTicTacToe b : a)
				str += b.getTav();
		}
		return  Integer.toString(rows_size) + "," + Integer.toString(columns_size) +"," +str;
	}
}
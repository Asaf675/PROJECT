package model.BoardDomain;

import model.Structures.Cell;

public class CellTicTacToe extends Cell {
	
	private char tav;
	
    //default constructor
	public CellTicTacToe()
	{
		this.tav='*';
	}
	
	//constructor that get X or O and insert it to the cell
	public CellTicTacToe(char tav)
	{
		super();
		this.tav=tav;
	}
	
	//return the value of the cell
	public char getTav()
	{
		return this.tav;
	}
	
	//the function get tav-X or O and set the cell to this value
	public void setTav(char newTav)
	{
	   this.tav=newTav;
	}
	
	//the function get Cell and check if the cell equals to this cell
	//if yes return true,else false
	public boolean isEqual(Cell a) 
	{
		CellTicTacToe xo=(CellTicTacToe)a;
       if(this.tav==xo.getTav())
       {
    	   return true;
       }
       else
    	   return false;
	}

	//the function print the value of the cell
	@Override
	public void Print() {
		System.out.print(this.tav+" ");
		
	}

	//the function clear the cell
	@Override
	public void Clear() {
		this.tav='*';	
	}

}
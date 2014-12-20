package model.BoardDomain;

import java.awt.Point;

import model.Structures.Cell;

/*
 * Class CellRPG :
 * Inherits from Cell
 * holds the details of the cells in the RPG domain
 */

public class CellRPG extends Cell {

	private Point coordinates; // holds the coordinates of the cell in the cells array
	private boolean flag; // notes if this cell was attacked before 
	
	// C'tor that gets int x and y and set them as coordinates
	// String description that notes which team the player in the current cell belongs to
	//int value as the weapon of the player in the current cell
	public CellRPG(int x, int y, String description, int value) {
		super(value,description);
		
		coordinates = new Point(x,y);
		flag = false;
	}
	
	//Copy C'tor
	public CellRPG(CellRPG original) {
		super(original);
		this.coordinates = new Point(original.getCoordinates());
		this.flag = original.flag;
	}
	
	// equals if they both has the same "color" suitable string description
	// 1 for Scissors, 2 for Paper, 3 for Rock, 4 for BLOCK, 5 for FLAG
	public boolean isEqual(CellRPG a) {
		return  this.description.equals(a.getDescription()) && !this.getDescription().equals("");
	}
	
	// converter from int to the 
	private String intToWeapon(int num) {
		
		switch(num) {
		case 1 : return "Scissors";
	//	break;
		case 2 : return "Paper";
	//	break;
		case 3 : return "Rock";
		//break;
		case 4 : return "BLOCK";
		//break;
		case 5 : return "FLAG";
		}
		
		return "";
		
	}
	
	//prints the cell
	@Override
	public void Print() {
		if(this.description.equals(""))
			System.out.print("   {  0  }");
		else
			System.out.print("{ " + this.description + ", " + intToWeapon(value) +" }");
	}
	
	//clear the cell
	@Override
	public void Clear() {
		this.setValue(0);
		this.setDescription("");
		this.setFlag(false);
	}
	
	// checks if the cell has player 
	public boolean isEmpty()
	{ return this.description.equals("") ? true : false; }
	
	public Point getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public void setX(int x) {
		coordinates.x = x;
	}
	
public int getX() {
		return coordinates.x;
	}
	
	public void setY(int y) {
		coordinates.y = y;
	}
	
	public int getY() {
		return coordinates.y;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
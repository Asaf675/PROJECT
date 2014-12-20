package model.BoardDomain;

import model.Structures.Cell;

/*
 * Class Cell2048 :
 * Inherits from Cell
 * holds the details of the cells in the 2048 domain
 */

public class Cell2048 extends Cell {
	
	// flag that notes if this cell was merged in the current move
	private boolean flag;	
	
	//defualt C'tor
	public Cell2048() {value = 0; flag = false; }
	
	//C'tor that gets value and send it to the super C'tor
	public Cell2048(int value) {super(value); flag = false;}
	
	//Copy C'tor
	public Cell2048(Cell2048 original) {super(original); this.flag = original.getFlag();}

	public void setFlag(boolean flag) {this.flag = flag;}
	
	public boolean getFlag() {return flag;}
	
	// method that compare between two cells
	public boolean isEqual(Cell2048 a) {
		return this.getValue() == a.getValue();
	}

	//prints the relevant information about the cell 
	@Override
	public void Print() {
		System.out.print(this.value);
	}
	
	//clear the details of the cell
	@Override
	public void Clear() {
		this.value = 0;
		this.flag = false;
	}

}
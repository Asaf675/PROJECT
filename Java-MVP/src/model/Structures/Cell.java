package model.Structures;

/*
 * Abstract class cell :
 * holds the details of the domain
 * the Inheritors will add the missing information
 */


public abstract class Cell {

	// the string representation of the cell
	protected String description;
	protected int value;
	
	// defualt C'tor
	public Cell() { this.value = 0; this.description = "";};
	
	// C'tor that gets String 
	public Cell(String description) {this.description = new String ( description ); this.value = 0;}
	
	//C'tor that gets value
	public Cell(int value) {this.value = value; this.description = "";}
	
	//C'tor that gets value and string
	public Cell(int value, String description) {this.value = value; this.description = new String ( description );}
	
	//Copy C'tor
	public Cell(Cell original) { this.description = new String( original.description ); this.value = original.value;}

	public void setDescription(String description) {this.description = new String ( description );}   
	
	public String getDescription() {return description;}
	
	public void setValue(int value) {this.value = value;}
	
	public int getValue() {return value;}
	
	
	/** abstract method **/
	public abstract void Print(); // Prints the information relevant in the cell
	public abstract void Clear(); // Clear the details of the cell
}
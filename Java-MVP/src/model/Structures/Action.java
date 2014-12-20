package model.Structures;

/*
 * Class Action :
 * contains the details of specific action
 */


public class Action {
	
	private String description;
	
	//C'tor
	public Action(String description) {this.description= new String(description);}
		
	public String getDescription(){return description;}
	
	public void setDescription(String desciption) {this.description = new String (desciption);}
	
	//method that prints the description of the action
	public void Print() { System.out.println(description); }
	

}

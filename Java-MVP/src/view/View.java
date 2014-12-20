package view;

import java.io.IOException;

import model.Structures.Cell;
import model.Structures.Solution;

/*
 * interface View :
 * defines all the methods of the view
 */

public interface View  {
	
	public void start() throws IOException;
	
	public void displayCurrentState(Cell[][] currentState);
	
	public void displaySolution(Solution solution);
	
	public String getUserAction();
	
	// a flag that notes if everything went well
	public void setFlag(boolean flag);
	
	// returns true if everything went well and the program may continue
	public boolean mayContinue();
}

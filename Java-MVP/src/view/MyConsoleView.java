package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import model.Structures.Cell;
import model.Structures.Solution;

/*
 * Class MyConsoleView :
 * implements View
 */


public class MyConsoleView extends Observable implements View {
	
	String command;
	boolean flag;
	
	public MyConsoleView() {
		command = new String();
		flag = false;
	}
	
	@Override
	public void start() {	
		
	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	String inputDomain,inputAlgorithm;
	
	System.out.println("Enter types of domain: ");
	System.out.println("The options of the domains are : \n" +
			"2048\n" +
			"RPG\n" +
			"Tic Tac Toe");
	
	try {
		while(!(inputDomain=in.readLine()).equals("exit")) {
			command = "SelectDomain " + inputDomain;
			
			setChanged();
			notifyObservers();
			
			if(mayContinue()) {
			
			System.out.println("Enter the Algorithem to solve the domain");
			
			System.out.println("The options of the algorithms are : \n" +
					"MiniMax\n" +
					"AlphaBeta");
			
			while(!(inputAlgorithm=in.readLine()).equals("exit")) {
				
				command = "SelectAlgorithm " + inputAlgorithm;
				setChanged();
				notifyObservers();
				if(mayContinue()) {
				command = "SolveDomain";
				setChanged();
				notifyObservers();
				command = "DisplaySolution";
				setChanged();
				notifyObservers();
				command = "Reset";
				setChanged();
				notifyObservers();
				}
				System.out.println("Enter the Algorithem to solve the domain");
				System.out.println("The options of the algorithms are : \n" +
						"MiniMax\n" +
						"AlphaBeta");
				
				}
			}
			System.out.println("Enter types of domain: ");
			System.out.println("The options of the domains are : \n" +
					"2048\n" +
					"RPG");
			}
	} catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public String getUserAction() {
		return command;
	}

	@Override
	public void displaySolution(Solution solution) {
		System.out.println("THE SOLUTION IS :\n");
		solution.Print();
	}

	@Override
	public void displayCurrentState(Cell[][] currentState) {
		System.out.println("\n\n");
		for(Cell[] a : currentState) {
			for(Cell b : a) {
				b.Print();
				System.out.print("          ");
			}
			System.out.println("\n\n");
		}
		System.out.println(" = = = = = = = = = = = = = ");
		}

	// a flag that notes if everything went well
	@Override
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	// returns true if everything went well and the program may continue
	@Override
	public boolean mayContinue() {
		boolean temp = flag;
		flag = false;
		return temp;
	}
	}

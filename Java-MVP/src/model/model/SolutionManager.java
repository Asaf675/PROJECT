package model.model;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

import model.Structures.Action;

public class SolutionManager {
	
	HashMap<String,Action> formerSolution;
	
	public SolutionManager() {
		
		ObjectInput input = null;
		File f = new File("solution.txt");
		
		try {
		InputStream file = new FileInputStream("solution.txt");
	    InputStream buffer = new BufferedInputStream(file);
	    input = new ObjectInputStream (buffer);
	    formerSolution = (HashMap<String,Action>)input.readObject();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			} 
		catch (IOException e) {
			e.printStackTrace();
			} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
	}	
		
}


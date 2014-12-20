package model.Structures;

import java.util.ArrayList;

public class Solution {
	
	ArrayList<Action> actions;
	
	public Solution() {actions = new ArrayList<Action>();}
	
	public void addAction(Action action) {
		actions.add(action);
	}
	
	public void Print() {
		System.out.print("{ ");
		for(int i = 0; i < actions.size(); i++) {
			System.out.print(actions.get(i).getDescription() +", ");
			if(i%10 == 0 && i != 0)
				System.out.println();
		}
		System.out.println(" }");
	}
}

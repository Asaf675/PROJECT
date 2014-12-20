package model.model;

import java.util.HashMap;

import view.View;


public class ModelAndViewCommand {
	
	View ui;
	Model model;
	HashMap<String, Command> command;
	
	private interface Command {
		public void doCommand(String type);
	}
	
	private class doSelectDomain implements Command {

		@Override
		public void doCommand(String type) {
			model.selectDomain(type);	
		}
	}
	
	private class doSelectAlgorithm implements Command {

		@Override
		public void doCommand(String type) {
			model.selectAlgorithm(type);
		}
	}

	private class doSolveDomain implements Command {

		@Override
		public void doCommand(String type) {
			model.solveDomain();
		}
	}
	
	private class doReset implements Command {

		@Override
		public void doCommand(String type) {
			model.reset();
		}
	}
	
	private class doDisplayCurrentState implements Command {

		@Override
		public void doCommand(String type) {
			ui.displayCurrentState(model.currentState());	
		}
	}
	
	private class doDisplaySolution implements Command {

		@Override
		public void doCommand(String type) {
			ui.displaySolution(model.getSolution());
		}
	}
	
	private class doSetFlag implements Command {

		@Override
		public void doCommand(String type) {
			ui.setFlag(type.equals("TRUE") ? true : false);
		}
	}
	
	public ModelAndViewCommand(View ui, Model model) {
		
		this.ui = ui;
		this.model = model;
		
		command = new HashMap<String,Command>();
		
		command.put("SelectDomain", new doSelectDomain());
		command.put("SelectAlgorithm", new doSelectAlgorithm());
		command.put("SolveDomain", new doSolveDomain());
		command.put("Reset", new doReset());
		command.put("DisplayCurrentState", new doDisplayCurrentState());
		command.put("DisplaySolution", new doDisplaySolution());
		command.put("setFlag", new doSetFlag());
	}
	
	public void executeCommand(String type) {
		String arr[] = type.split(" ");
		Command c = command.get(arr[0]);
		
		if(c!=null) c.doCommand( (arr.length > 1) ? arr[1] : arr[0] );
		else System.out.println("Eror 404 command not found");
	}
}


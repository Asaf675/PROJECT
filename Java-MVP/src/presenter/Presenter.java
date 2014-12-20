package presenter;

import java.util.Observable;
import java.util.Observer;

import view.View;


import model.model.Model;
import model.model.ModelAndViewCommand;

/*
 * Class Presenter :
 * implements Observer
 */

public class Presenter implements Observer {
	
	View ui;
	Model model;
	ModelAndViewCommand command; // handles all the commands
	
	public Presenter(View ui, Model model) {
		this.ui = ui;
		this.model= model;
		command = new ModelAndViewCommand(ui,model);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {

		if(arg0 == ui) {
			String input = ui.getUserAction();
			command.executeCommand(input);
	}
		
		else if(arg0 == model) {
			command.executeCommand(arg1.toString());
		}

	}
}

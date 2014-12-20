package TESTS;

import presenter.Presenter;
import model.model.MyModel;
import view.MyConsoleView;

public class MainTEST {

	public static void main(String[] args) {
		
		MyConsoleView ui = new MyConsoleView();
		MyModel model = new MyModel();
		Presenter p = new Presenter(ui,model);
		ui.addObserver(p);
		model.addObserver(p);
		ui.start();
	}
}

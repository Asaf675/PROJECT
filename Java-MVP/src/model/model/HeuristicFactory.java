package model.model;

import java.util.HashMap;

import model.Heuristics.Heuristic2048;
import model.Heuristics.HeuristicFunction;
import model.Heuristics.HeuristicRPG;
import model.Heuristics.HeuristicTicTacToe;
import model.Heuristics.HeuristicsCommon;

public class HeuristicFactory {
		
	private interface Creator {
		public HeuristicFunction create();
	}
	
	private class Heuristic2048Creator implements Creator {
		@Override
		public HeuristicFunction create() {
			return new Heuristic2048();
		}	
	}
	
	private class HeuristicRPGCreator implements Creator {

		@Override
		public HeuristicFunction create() {
			return new HeuristicRPG();
		}
	}
	
	private class HeuristicTicTacToeCreator implements Creator {

		@Override
		public HeuristicFunction create() {
			return new HeuristicTicTacToe();
		}
		
	}
	
	HashMap<String, Creator> HeuristicCreators;
	
	public HeuristicFactory() {
		HeuristicCreators = new HashMap<String,Creator>();
		HeuristicCreators.put("2048",new Heuristic2048Creator() );
		HeuristicCreators.put("RPG", new HeuristicRPGCreator());
		HeuristicCreators.put("Tic Tac Toe", new HeuristicTicTacToeCreator());
	}
	
	public HeuristicFunction createHeuristic(String type) {
		
		Creator h = HeuristicCreators.get(type);
		
		if(h!=null) return h.create();
		return null;
	}

}

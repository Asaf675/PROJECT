package model.model;

import java.util.HashMap;

import model.Heuristics.Calculator2048;
import model.Heuristics.HeuristicFunction;
import model.Heuristics.HeuristicRPG;

public class HeuristicFactory {
		
	private interface Creator {
		public HeuristicFunction create();
	}
	
	private class Heuristic2048Creator implements Creator {
		@Override
		public HeuristicFunction create() {
			return new Calculator2048();
		}	
	}
	
	private class HeuristicRPGCreator implements Creator {

		@Override
		public HeuristicFunction create() {
			return new HeuristicRPG();
		}
	}
	
	HashMap<String, Creator> HeuristicCreators;
	
	public HeuristicFactory() {
		HeuristicCreators = new HashMap<String,Creator>();
		HeuristicCreators.put("2048",new Heuristic2048Creator() );
		HeuristicCreators.put("RPG", new HeuristicRPGCreator());
	}
	
	public HeuristicFunction createHeuristic(String type) {
		
		Creator h = HeuristicCreators.get(type);
		
		if(h!=null) return h.create();
		return null;
	}

}

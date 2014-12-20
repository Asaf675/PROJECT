package model.model;

import java.util.HashMap;

import model.AI.AI;
import model.AI.MiniMax;
import model.AI.MiniMaxAlphaBeta;
import model.Heuristics.HeuristicFunction;

public class AlgorithmFactory {
	
	private interface Creator {
		public AI create(HeuristicFunction h);
	}
	
	private class MiniMaxCreator implements Creator {

		@Override
		public AI create(HeuristicFunction h) {
			return new MiniMax(h,5);
		}
	}
	
	private class AlphaBetaCreator implements Creator {

		@Override
		public AI create(HeuristicFunction h) {
			return new MiniMaxAlphaBeta(h,7);
		}
		
	}
	
	HashMap<String,Creator> AlgorithmCreator;
	
	public AlgorithmFactory() {
		AlgorithmCreator = new HashMap<String,Creator>();
		AlgorithmCreator.put("MiniMax", new MiniMaxCreator());
		AlgorithmCreator.put("AlphaBeta", new AlphaBetaCreator());
	}
	
	// HeuristicFunction h will be null if the selected algorithm does not require heuristic
	public AI createAlgorithm(String type, HeuristicFunction h) {
		Creator a = AlgorithmCreator.get(type);
		
		if(a!=null) return a.create(h);
		return null;
	}

}
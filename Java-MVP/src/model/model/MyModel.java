package model.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import model.AI.AI;
import model.BoardDomain.AI_Domain;
import model.BoardDomain.Board2048model;
import model.Heuristics.HeuristicFunction;
import model.Structures.Action;
import model.Structures.Cell;
import model.Structures.Solution;

public class MyModel extends Observable implements Model {
	
	AI_Domain domain;
	AI algorithm;
	Solution solution;
	
	@Override
	public void selectDomain(String domainName) {
		
		DomainFactory f = new DomainFactory();
		
		domain = f.createDomain(domainName);
		
		if(domain == null) {
			System.out.println("error 404 domain not found");
		}
		else {
			setChanged();
			notifyObservers("setFlag TRUE");
		}
	}

	@Override
	public void selectAlgorithm(String AlgorithmName) {
		
		AlgorithmFactory f = new AlgorithmFactory();
		
		if(AlgorithmName.equals("AlphaBeta") || AlgorithmName.equals("MiniMax")) {
			
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
			HeuristicFactory h = new HeuristicFactory();
			HeuristicFunction heuristic;
			try {
				do{ 
					System.out.println("Enter the Heuristic function : ");
					if(domain.getClass().getName().contains("2048"))
						System.out.println("The options are : \n" +
											"2048\n" );
					else if(domain.getClass().getName().contains("RPG"))
						System.out.println("The options are : \n " +
											"RPG");
							
					heuristic = h.createHeuristic(in.readLine());
					if(heuristic == null)
						System.out.println("error 404 heuristic not found");
				} while(heuristic == null);
			 
			 algorithm = f.createAlgorithm(AlgorithmName,heuristic);
				if(algorithm == null)
					System.out.println("error 404 heuristic not found");
				else {
					setChanged();
					notifyObservers("setFlag TRUE");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
			algorithm = f.createAlgorithm(AlgorithmName,null);
			
			if(algorithm == null)
				System.out.println("error 404 algorithm not found");
			else {
				setChanged();
				notifyObservers("setFlag TRUE");
			}
		}
	}

	@Override
	public void solveDomain() {
		
		if( domain != null && algorithm != null) {
			Long beginning = System.nanoTime();
			Action action;
			solution = new Solution();
			// print the beginning domain
			setChanged();
			notifyObservers("DisplayCurrentState");
			while(!domain.gameOver()) {
				action = new Action( algorithm.BestMove(domain).getDescription() );
				solution.addAction(action);
				domain.executeCommand( (action.getDescription()),null);
				setChanged();
				notifyObservers("DisplayCurrentState");
				
				if(!domain.gameOver()) {
					domain.getPlayerAction();
					setChanged();
					notifyObservers("DisplayCurrentState");
				}
				}
			Long end = System.nanoTime();
			System.out.println("The time that took to solve the domain is : " +(end - beginning));
		}
		else {
			System.out.println("the domain or the algorithm were null therefore cannot solve the domain");
			return;
		}
	}

	@Override
	public Solution getSolution() {
			return solution;
	}

	@Override
	public void reset() {
		domain.reset();	
	}

	@Override
	public Cell[][] currentState() {
		return domain.getCurrentStatus();
	}
}

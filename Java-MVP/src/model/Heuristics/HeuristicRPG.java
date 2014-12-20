package model.Heuristics;

import model.BoardDomain.AI_Domain;
import model.BoardDomain.BoardRPGmodel;
import model.BoardDomain.CellRPG;

public class HeuristicRPG extends HeuristicsCommon {

	
	@Override // always tries to maximize the score for the red team
	public int calculateScore(AI_Domain game) {
		
		BoardRPGmodel gameRPG = (BoardRPGmodel)game;
		
		int score;
		
		score = ( gameRPG.getRedNum() - gameRPG.getBlueNum() )*2;
		
		//	which players surround the flag
		
		CellRPG arr[] = new CellRPG[3];
		arr[0] = new CellRPG(gameRPG.getCell(0,1));
		arr[1] = new CellRPG(gameRPG.getCell(1,0));
		arr[2] = new CellRPG(gameRPG.getCell(1,1));
		
		for(CellRPG a : arr) {
		
			if(a.getDescription().equals("RED"))
				score += 5;
			else if(a.getDescription().equals("BLUE"))
				score -= 50;
			else // better have players around the flag
				score -= 5;
		}

		
		// which players surround the blue team flag 
		
		arr[0] = new CellRPG(gameRPG.getCell(gameRPG.GETrows_size()-1,gameRPG.GETcolumns_size()-2));
		arr[1] = new CellRPG(gameRPG.getCell(gameRPG.GETrows_size()-2,gameRPG.GETcolumns_size()-1));
		arr[2] = new CellRPG(gameRPG.getCell(gameRPG.GETrows_size()-2,gameRPG.GETcolumns_size()-2));
		
		for(CellRPG a : arr) {
		
		if(a.getDescription().equals("RED"))
			score += 100;
		else if(a.getDescription().equals("BLUE"))
			score -= 5;
		else // better with no players around
			score += 5;
		}
		
	return score;	
	}

}

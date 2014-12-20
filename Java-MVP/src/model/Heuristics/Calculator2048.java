package model.Heuristics;

import java.awt.Point;

import model.BoardDomain.AI_Domain;
import model.BoardDomain.Board2048model;



public class Calculator2048 extends HeuristicsCommon {
	
	public Calculator2048() {
			
	}
	
	@Override
	public int calculateScore(AI_Domain game) {
		Board2048model game2048 = (Board2048model)game;
		
		//else
		Point a = new Point(game2048.GETrows_size(),game2048.GETcolumns_size());
		int highest = game2048.theHighest();
		int score = (int)( game2048.GETscore() + Math.log(game2048.GETscore())*game2048.GETfreeCells() - calculateClusteringScore(game2048) )
				- distance(game2048, a, highest)*8 - distance(game2048, a, highest/2)*4 - distance(game2048, a, highest/4)*2 ;
		return Math.max(score, Math.min(game2048.GETscore(), 1));
		
	}
	
    private int calculateClusteringScore(Board2048model game2048) {
        int clusteringScore=0;
        
        int[] neighbors = {-1,0,1};
        
        for(int i=0;i< game2048.GETrows_size();++i) {
            for(int j=0;j<game2048.GETcolumns_size();++j) {
                if(game2048.isEmpty(i, j)) {
                	
                    continue; //ignore empty cells
                }
                
                //clusteringScore-=boardArray[i][j];
                
                //for every pixel find the distance from each neightbors
                int numOfNeighbors=0;
                int sum=0;
                for(int k : neighbors) {
                    int x=i+k;
                    if(x<0 || x>=game2048.GETrows_size()) {
                        continue;
                    }
                    for(int l : neighbors) {
                        int y = j+l;
                        if(y<0 || y>=game2048.GETcolumns_size()) {
                            continue;
                        }
                        
                        if(game2048.GETslot(x, y)>0) {
                            ++numOfNeighbors;
                            sum+=Math.abs(game2048.GETslot(i, j)-game2048.GETslot(x, y));
                        }
                        
                    }
                }
                
                clusteringScore+=sum/numOfNeighbors;
            }
        }
        
        return clusteringScore;
    }

    
    private int distance(Board2048model game2048,Point coordinates, int num) {
    	
    	if(num >= 64) {
	    	Point a = game2048.getCoordinates(num);
	    	
	    	if(a.getX() != -1) {
	    		return (int) (Math.sqrt(Math.pow(( coordinates.getX()  - (a.getX() +1)),2)) + Math.pow(coordinates.getY()- (a.getY()+1), 2));
	    	}
    	
    	}
    	return 0;
    }
}
	
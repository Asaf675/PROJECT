package model.Heuristics;

import model.BoardDomain.AI_Domain;
import model.BoardDomain.BoardTicTacToeModel;


public class HeuristicTicTacToe extends HeuristicsCommon{
	
	//the function get a game of XO and return the heuristic function of him
	@Override
	public int calculateScore(AI_Domain game) {
		BoardTicTacToeModel board=(BoardTicTacToeModel)game;
		return scoreRows(board)+ScoreColumns(board)+Maindiagonal(board)+TwoDiagonal(board);
				
	}
	
	public int scoreRows(BoardTicTacToeModel game)
	{
		int sum=0;
		int moneX=0;
		int moneO=0;
		for(int i=0;i<game.GETrows_size();i++)
		{
			for(int j=0;j<game.GETcolumns_size();j++)
			{
				if(game.getSlot(i, j)=='X')
				{
					moneX++;
				}
				if(game.getSlot(i, j)=='O')
				{
					moneO++;
				}
			}
			if(moneX>0 && moneO==0)
			{
				if(moneX==game.GETrows_size())
				{
					sum+=9999;
				}
				else
				if(moneX==game.GETrows_size()-1)
				{
					sum+=Math.pow(moneX, 2);
				}
				else
					sum+=moneX;
			}
			if(moneO>0 && moneX==0)
			{
				if(moneO==game.GETrows_size())
				{
					sum-=9999;
				}
				else
				if(moneO==game.GETrows_size()-1)
					sum-=Math.pow(moneO, 2);
				else
					sum-=moneO;
			}
		   moneX=0;
		   moneO=0;

		}
		
		return sum;
	}

	
	public int ScoreColumns(BoardTicTacToeModel game)
	{
		int sum=0;
		int moneX=0;
		int moneO=0;
		for(int i=0;i<game.GETrows_size();i++)
		{
			for(int j=0;j<game.GETcolumns_size();j++)
			{
				if(game.getSlot(j, i)=='X')
				{
					moneX++;
				}
				if(game.getSlot(j, i)=='O')
				{
					moneO++;
				}
			}

			if(moneX>0 && moneO==0)
			{
				if(moneX==game.GETrows_size())
				{
					sum+=9999;
				}
				else
				if(moneX==game.GETrows_size()-1)
				{
					sum+=Math.pow(moneX, 2);
				}
				else
					sum+=moneX;
			}
			if(moneO>0 && moneX==0)
			{
				if(moneO==game.GETrows_size())
				{
					sum-=9999;
				}
				else
				if(moneO==game.GETrows_size()-1)
					sum-=Math.pow(moneO, 2);
				else
					sum-=moneO;
			}
					moneX=0;
					moneO=0;

		}
		
		return sum;

	}
	
	public int Maindiagonal(BoardTicTacToeModel game)
	{
		int sum=0;
		int moneX=0;
		int moneO=0;
		
		for(int i=0;i<game.GETrows_size();i++)
		{

        		   if(game.getSlot(i, i)=='X')
        		   {
        			   moneX++;
        		   }
        		   if(game.getSlot(i, i)=='O')
        		   {
        			   moneO++;
        		   }
        	   
		}
		if(moneX>0 && moneO==0)
		{
			if(moneX==game.GETrows_size())
			{
				sum+=9999;
			}
			else
			if(moneX==game.GETrows_size()-1)
			{
				sum+=Math.pow(moneX, 2);
			}
			else
				sum+=moneX;
		}
		if(moneO>0 && moneX==0)
		{
			if(moneO==game.GETrows_size())
			{
				sum-=9999;
			}
			else
			if(moneO==game.GETrows_size()-1)
				sum-=Math.pow(moneO, 2);
			else
				sum-=moneO;
		}
		return sum;
					
	}
	
	public int TwoDiagonal(BoardTicTacToeModel game)
	{
		int sum=0;
		int moneX=0;
		int moneO=0;
		
		for(int i=0;i<game.GETrows_size();i++)
		{

        		   if(game.getSlot(i, game.GETrows_size()-1-i)=='X')
        		   {
        			   moneX++;
        		   }
        		   if(game.getSlot(i,  game.GETrows_size()-1-i)=='O')
        		   {
        			   moneO++;
        		   }
        	   
		}
		if(moneX>0 && moneO==0)
		{
			if(moneX==game.GETrows_size())
			{
				sum+=9999;
			}
			else
			if(moneX==game.GETrows_size()-1)
			{
				sum+=Math.pow(moneX, 2);
			}
			else
				sum+=moneX;
		}
		if(moneO>0 && moneX==0)
		{
			if(moneO==game.GETrows_size())
			{
				sum-=9999;
			}
			else
			if(moneO==game.GETrows_size()-1)
				sum-=Math.pow(moneO, 2);
			else
				sum-=moneO;
		}
		return sum;
					
					
	}
}
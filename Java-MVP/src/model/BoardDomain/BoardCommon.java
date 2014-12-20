package model.BoardDomain;

import java.util.ArrayList;

import model.BoardDomain.AI_Domain;
import model.Structures.Cell;

/*
 * Abstract class MiniMaxCommon
 * contains all the common items that all Boards versions share
 */


public abstract class BoardCommon implements AI_Domain {

	protected int rows_size;
	protected int columns_size;
 	protected int freeCells;
	protected int takenCells;
	
	public BoardCommon(int rows_size, int columns_size) {
		this.rows_size = rows_size;
		this.columns_size = columns_size;
		this.freeCells = columns_size*rows_size;
		this.takenCells = 0;	
	}
	
	public BoardCommon(BoardCommon original) {
		this.rows_size = original.GETrows_size();
		this.columns_size = original.GETcolumns_size();
		this.freeCells = original.GETfreeCells();
		this.takenCells = original.GETtakenCells();
		
	}
	
	
	public int GETrows_size() {
		return rows_size;
	}
	
	
	public int GETcolumns_size() {
		return columns_size;
	}

	
	public int GETfreeCells() {
		return freeCells;
	}
	
	public int GETtakenCells() {
		return takenCells;
	}
	
	// *** ABSTRACT METHODS *** //
	public abstract void executeCommand(String command, Object arg);
	public abstract ArrayList<String> allPossibleMoves(boolean whoPlays);
	public abstract AI_Domain clone();
	public abstract boolean gameOver();
	public abstract boolean wonTheGame(boolean whoPlays);
	public abstract void getPlayerAction();
	public abstract Cell[][] getCurrentStatus();
	public abstract void reset();
	public abstract String toString();

}


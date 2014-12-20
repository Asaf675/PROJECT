package model.model;

import java.util.HashMap;

import model.BoardDomain.AI_Domain;
import model.BoardDomain.Board2048model;
import model.BoardDomain.BoardRPGmodel;


public class DomainFactory {
	
	private interface Creator{
		public AI_Domain create();
	}
	
	private class Creator2048 implements Creator {
		@Override
		public AI_Domain create() {
			return new Board2048model();
		}
	}
	
	private class CreatorRPG implements Creator {

		@Override
		public AI_Domain create() {
		return new BoardRPGmodel();
		}
		
	}
	
	HashMap<String, Creator> DomainsCreators;
	
	public DomainFactory() {
		DomainsCreators = new HashMap<String, Creator>();
		DomainsCreators.put("2048", new Creator2048());
		DomainsCreators.put("RPG", new CreatorRPG());
	}
	
	public AI_Domain createDomain(String type) {
		
		Creator b = DomainsCreators.get(type);
		
		if(b!=null) return b.create();
		return null;
	}
	
}

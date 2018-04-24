package core;

import java.util.ArrayList;

public class Player {
	private String name; //players name
	private int score; //players score
	private ArrayList<Domino> hand = new ArrayList<Domino>(14); //players hand of Dominoes
	
	public Player(String nameStr) {
		if(nameStr != null) {
			name = nameStr;
		}else {
			name = "Player";
		}
	}//Constructor
	
	public Domino getDominoFromHand(int index) {
		return hand.get(index);
	}//getDominoFromHand
	
	public Domino removeDominoFromHand(int index) {
		if(index >= 0 && index < hand.size()) {
			Domino tempDomino = getDominoFromHand(index);
			hand.remove(index);
			
			return tempDomino;
		}else {
			return null;
		}
	}//removeDominoFromHand
	
	public int dominoCount(){
		return hand.size();
	}//dominoCount

	public boolean dealDomino(Domino dom) {
		if(dom != null) {
			hand.add(dom);
			return true;
		}else {
			return false;
		}
	}//dealTile
	
	public int findDomino(int value) {
		for(int i = 0; i < hand.size(); i++) {
			Domino dom = getDominoFromHand(i);
			if(value == dom.getLeftVal() || value == dom.getRightVal())
				return i;
		}
		return -1;
	}//findTile
	
	public boolean hasDoubleInHand() {
		for (int i = 0; i < hand.size(); i++) {
			Domino dom = getDominoFromHand(i);
			if(dom.isDouble())
				return true;
		}
		return false;
	}
	
	public int findHighestDouble() {
		int highest = -1;
		for(int i = 0; i < hand.size(); i++) {
			Domino dom = getDominoFromHand(i);
			if(dom.isDouble() && dom.getLeftVal() >= getDominoFromHand(highest).getLeftVal()) {
				highest = i;
			}
		}
		return highest;
	}
	
	public void clearHand() {
		hand.clear();
	}//clearHand
	
	public void clearScore() {
		score = 0;
	}//clearScore
	
	public void addToScore(int plus) {
		score += plus;
	}//addToScore
	
	/* Getters and Setters -----------------------------------------*/
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getScore() {return score;}
}//Class Player
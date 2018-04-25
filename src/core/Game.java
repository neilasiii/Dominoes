package core;

import java.util.ArrayList;

import javax.swing.JLabel;

/**
 * Represents a single game of Block dominoes.
 * @author neila
 *
 */
public class Game {
	public Dominoes dominoes;
	public ArrayList<Domino> playedDominoes;
	public JLabel label;
	public Player player;
	public Player computer;
	public int computerDominoIndex = -1;
	
	public Game() {
		dominoes = new Dominoes();
		playedDominoes = new ArrayList<Domino>();
		player = new Player("Player");
		computer = new Player("Computer");
	}//Constructor
	
	/**
	 * Initializes the game by shuffling dominoes and clearing hands and scores
	 */
	public void initGame() {
		dominoes.shuffleDominoes();
		playedDominoes.clear();
		player.clearHand();
		player.clearScore();
		computer.clearHand();
		computer.clearScore();
	}//initGame
	
	/**
	 * Deals seven dominoes to both the player and computer.
	 */
	public void deal() {
		while(true) {
			player.dealDomino(dominoes.drawDomino());
			computer.dealDomino(dominoes.drawDomino());
			
			if(player.dominoCount() == 7 && computer.dominoCount() == 7)
				return;
		}
	}//deal
	
	
	/**
	 * Places a domino to the left.
	 * Also used for placing the first domino
	 * @param dom the domino that will be placed
	 * @return true if was able to play
	 * @return false if not able to play
	 */
	public boolean doBegMove(Domino dom) {
		if(playedDominoes.size() == 0) {
			playedDominoes.add(dom);
			return true;
		}
		
		Domino firstDomino = playedDominoes.get(0);
		
		if(dom.getRightVal() == firstDomino.getLeftVal()) {
			playedDominoes.add(0, dom);
			return true;
		}
		
		if(dom.getLeftVal() == firstDomino.getLeftVal()) {
			dom.flip();
			playedDominoes.add(0, dom);
			return true;
		}else {
			return false;
		}
	}//doRightMove
	
	/**
	 * Places a domino to the Right.
	 * @param dom the domino that will be placed
	 * @return true if was able to play
	 * @return false if not able to play
	 */
	public boolean doEndMove(Domino dom) {	
		Domino lastDomino = playedDominoes.get(playedDominoes.size()-1);
		
		if(dom.getLeftVal() == lastDomino.getRightVal()) {
			playedDominoes.add(dom);
			return true;
		}
		
		if(dom.getRightVal() == lastDomino.getRightVal()) {
			dom.flip();
			playedDominoes.add(dom);
			return true;
		}else {
			return false;
		}
	}//doLeftMove
	
	/**
	 * The artificial inteligence of the computer.
	 * Plays Domino and updates score.
	 * @return true if able to play and change score.
	 * @return false if unable to play.
	 */
	public boolean playComputer() {
		Domino firstDomino = playedDominoes.get(0);
		Domino lastDomino  = playedDominoes.get(playedDominoes.size()-1);
		computerDominoIndex = computer.findDomino(firstDomino.getLeftVal());
		
		if(computerDominoIndex != -1) {
			Domino insert = computer.getDominoFromHand(computerDominoIndex);
			doBegMove(insert);
			computer.removeDominoFromHand(computerDominoIndex);
		}else {
			computerDominoIndex = computer.findDomino(lastDomino.getRightVal());
			
			if(computerDominoIndex != -1) {
				Domino insert = computer.getDominoFromHand(computerDominoIndex);
				doEndMove(insert);
				computer.removeDominoFromHand(computerDominoIndex);
			}else
				return false;
		}
		computer.addToScore(playedDominoes.get(0).getLeftVal() + playedDominoes.get(playedDominoes.size()-1).getRightVal());
		return true;
	}//playComputer
	
	/**
	 * Deals to computer and palyer.
	 * Draws tile from dominoes and plays it;
	 */
	public void doFirstMove() {
		deal();
		doBegMove(dominoes.drawDomino());
	}//doFirstMove
	
	public String ToStringPlayedTiles() {
		String toReturn = new String();
		
		for (int i = 0; i < playedDominoes.size(); i++) {
			Domino dom = playedDominoes.get(i);
			toReturn = toReturn + dom.toString();
		}
		return toReturn;
	}
}

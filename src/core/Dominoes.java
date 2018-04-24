package core;

/**
 * This class represents all possible dominoes.
 * The game uses double 6 dominoes therefore there are a total of 28 dominoes.
 * @author neila
 *
 */
public class Dominoes {
	private Domino []dominoes = new Domino[28];
	int currIndex;
	
	public Dominoes() {
		int counter = 0;
		
		for(int leftVal = Domino.minValue; leftVal <= Domino.maxValue; leftVal++) {
			for (int rightVal = leftVal; rightVal <= Domino.maxValue; rightVal++) {
				dominoes[counter++] = new Domino(leftVal,rightVal);
			}
		}
		currIndex = 0;
	}//Constructor
	
	public void shuffleDominoes() {
		for(int i = 0; i < dominoes.length; i++) {
			Domino temp = dominoes[i];
			int swapper = (int)(Math.random()*27);
			dominoes[i] = dominoes[swapper];
			dominoes[swapper] = temp;
			currIndex = 0;
		}
	}//shuffle
	
	public Domino drawDomino() {
		if(currIndex < dominoes.length)
			return dominoes[currIndex++];
		else return null;
	}//drawDomino
	
	public boolean canDraw() {
		return currIndex < dominoes.length;
	}//canDraw
}//Dominoes
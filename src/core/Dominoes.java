package core;

/**
 * This class represents all possible dominoes.
 * The game uses double 6 dominoes therefore there are a total of 28 dominoes.
 * @author neila
 *
 */
public class Dominoes {
	private Domino []dominoes = new Domino[28];
	int index;
	
	public Dominoes() {
		int counter = 0;
		
		for(int leftVal = Domino.min; leftVal <= Domino.max; leftVal++) {
			for (int rightVal = leftVal; rightVal <= Domino.max; rightVal++) {
				dominoes[counter++] = new Domino(leftVal,rightVal);
			}
		}
		index = 0;
	}
	
	public boolean canDraw() {
		return index < dominoes.length;
	}
	
	public Domino drawDomino() {
		if(index < dominoes.length)
			return dominoes[index++];
		else return null;
	}
	
	public void shuffleDominoes() {
		for(int i = 0; i < dominoes.length; i++) {
			Domino temp = dominoes[i];
			int swapper = (int)(Math.random()*27);
			dominoes[i] = dominoes[swapper];
			dominoes[swapper] = temp;
			index = 0;
		}
	}
}
package core;

/**
 * This class represents a single Domino.
 * @author Neil Stagner
 */
public class Domino {
	public static final int min = 0; //minimum value of numbers on domino
	public static final int max = 6; //maximum value of numbers on domino

	private int leftVal  = min; // leftVal is the value on the left of the Domino
	private int rightVal = min; // rightVal is the value oon the right of the Domino
	private boolean isDouble; // is true if right and left are equal

	public Domino(int left, int right) {
		if(left >= min && left <= max) {
			leftVal = left;
		}
		if(right >= min && right <= max) {
			rightVal = right;
		}
		if(right == left) {
			isDouble = true;
		}
	}

	/**
	 * Flips the Domino
	 */
	public void flip() {
		int temp = rightVal;
		rightVal = leftVal;
		leftVal  = temp;		
	}

	public int getLeftVal() {
		return leftVal;
	}

	public int getRightVal() {
		return rightVal;
	}

	public boolean isDouble() {
		return isDouble;
	}

	@Override
	public String toString()
	{
		String leftStr;
		String rightStr;

		if(leftVal >= 1  && leftVal <= max)
			leftStr = "[" +leftVal;
			else leftStr = "[ ";

		if(rightVal >= 1  && rightVal <= max)
			rightStr = rightVal + "]";
		else rightStr = " ]";

		return leftStr + "|"+ rightStr;
	}

	public String toValue() {
		String left = "";
		String right = "";
		left = left + leftVal;
		right = right + rightVal;
		return left + right;

	}
}

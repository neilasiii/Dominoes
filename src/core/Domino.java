package core;

/**
 * This class represents a single Domino.
 * @author Neil Stagner
 */
public class Domino {
	public static final int minValue = 0; //minimum value of numbers on domino
	public static final int maxValue = 6; //maximum value of numbers on domino
	
	private int leftVal  = minValue; // leftVal is the value on the left of the Domino
	private int rightVal = minValue; // rightVal is the value oon the right of the Domino
	private boolean isDouble; // is true if right and left are equal
		
	public Domino(int left, int right) {
		if(left >= minValue && left <= maxValue) {
			leftVal = left;
		}
		if(right >= minValue && right <= maxValue) {
			rightVal = right;
		}
		if(right == left) {
			isDouble = true;
		}
	} //Constructor
	
	public boolean isDouble() {
		return isDouble;
	}

	/**
	 * Flips the Domino
	 */
	public void flip() {
		int temp = rightVal;
		rightVal = leftVal;
		leftVal  = temp;		
	} //flip

	public int getLeftVal() {
		return leftVal;
	}

	public int getRightVal() {
		return rightVal;
	}
	
    public String toString()
    {
            String leftStr;
            String rightStr;

            if(leftVal >= 1  && leftVal <= maxValue)
                leftStr = "[" +leftVal;
            else leftStr = "[ ";

            if(rightVal >= 1  && rightVal <= maxValue)
                 rightStr = rightVal + "]";
            else rightStr = " ]";

            return leftStr + "|"+ rightStr;
    }//toString
    
    public String toValue() {
    	String left = "";
    	String right = "";
    	
    	left = left + leftVal;
    	right = right + rightVal;
    	System.out.println(left + right);
    	return left + right;
    	
    }
}//Class Domino

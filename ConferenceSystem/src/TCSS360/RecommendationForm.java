package TCSS360;

import java.io.Serializable;

/**
 * One of the subclass of Roles called Author.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0 5/5/2016
 */
public class RecommendationForm extends Paper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6800776639147002957L;
	private int myScore; 
	
	public RecommendationForm(String thePath, String theAuthor, String theSubmitDate, String theTitle, int theScore) {
		super(thePath, theAuthor, theSubmitDate, theTitle);
		myScore = theScore;
	}

	public int getScore() {
		return myScore;
	}

	public void setScore(int theScore) {
		this.myScore = theScore;
	}

	@Override
	public String toString() {
		return "Recommendation score: " + myScore + " "
				+ "Author: ";
	}
}

package model;

import java.io.Serializable;

/**
 * One of the subclass of Roles called Author.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0 5/5/2016
 */
public class RecommendationForm extends Paper implements Serializable{

	private static final long serialVersionUID = -6800776639147002957L;
	private int myScore; 
	
	/**
	 * @param thePath the file path
	 * @param theAuthor the author name
	 * @param theSubmitDate the submitted date
	 * @param theTitle the title
	 * @param theScore the score
	 */
	public RecommendationForm(String thePath, String theAuthor, String theSubmitDate, String theTitle, int theScore) {
		super(thePath, theAuthor, theSubmitDate, theTitle);
		myScore = theScore;
	}

	/**
	 * Gets the score of the recommendation form.
	 * @return score
	 */
	public int getScore() {
		return myScore;
	}

	/**
	 * Sets the score of the recommendation form.
	 * @param theScore 
	 */
	public void setScore(int theScore) {
		this.myScore = theScore;
	}

	/**
	 * Returns a string representation of a recommendation form.
	 * @return string
	 */
	@Override
	public String toString() {
		return "Recommendation score: " + myScore + " "
				+ "Author: ";
	}
	 
}
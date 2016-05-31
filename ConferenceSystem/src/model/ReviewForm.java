package model;

import java.io.Serializable;

/**
 * This class represents a review form object.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0
 */
public class ReviewForm  extends Paper implements Serializable{
	/**
	 * Serial identification number
	 */
	private static final long serialVersionUID = -3469664696759061781L;
	private User myReviewer;
	
	/**
	 * 
	 * @param thePath the file path
	 * @param theAuthor the author name
	 * @param theSubmitDate the submitted date
	 * @param theTitle the title
	 * @param theReviewer A reviewer
	 */
	public ReviewForm(String thePath, String theAuthor, String theDate, String theTitle, User theReviewer) {
		super(thePath, theAuthor, theDate, theTitle);
		myReviewer = theReviewer;
	}

	/**
	 * Get reviewer's name for this review form
	 * @return reviewer
	 */
	public User getReviewer() {
		return myReviewer;
	}

	/**
	 * Assign reviewer for this review form.
	 * @param theReviewer A reviewer 
	 */ 
	public void setReviewer(User theReviewer) {
		this.myReviewer = theReviewer;
	}
}


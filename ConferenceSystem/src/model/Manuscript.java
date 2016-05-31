package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a manuscript object.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 5/8/2016
 *
 */
public class Manuscript extends Paper implements Serializable {

	/**
	 * Serial identification number
	 */
	private static final long serialVersionUID = -8730353916258807055L;
	private Status myStatus;
	private List<ReviewForm> myReviewFormList;
	private List<User> myReviewerList;
	private List<RecommendationForm> myRecomFormList;
	

	/**
	 * This enum class will represent a manuscripts state.
	 * 
	 * @author Bincheng Li
	 * @version 5/5/2016
	 */
	 public static enum Status {
		 //Paper status
		 SUBMITTED, RECOMMENDED, ACCEPTED, REJECTED;
	 }

	 /**
	  * 
	  * @param thePath file path
	  * @param theAuthor user who created file
	  * @param theSubmitDate date submitted
	  * @param theTitle title of manuscript
	  */
	public Manuscript(String thePath, String theAuthor, String theSubmitDate,
			String theTitle) {
		super(thePath, theAuthor, theSubmitDate, theTitle);
		myStatus = Status.SUBMITTED;
		myRecomFormList = new ArrayList<RecommendationForm>();
		myReviewerList = new ArrayList<User>();
		myReviewFormList = new ArrayList<ReviewForm>();
	}
	
	public Manuscript(String thePath, String theAuthor, String theSubmitDate,
			String theTitle, Status theStatus, List<RecommendationForm> theRecomFormList,
			List<User> theReviewerList, List<ReviewForm> theReviewFormList) {
		super(thePath, theAuthor, theSubmitDate, theTitle);
		myStatus = theStatus;
		myRecomFormList = theRecomFormList;
		myReviewerList = theReviewerList;
		myReviewFormList = theReviewFormList;
	} 

	/**
	 * Gets the status of a manuscript.
	 * @return the status
	 */
	public Status getStatus() {
		return myStatus;
	}

	/**
	 * Sets the status of a manuscript.
	 * @param status
	 */
	public void setStatus(Status status) {
		this.myStatus = status;
	}

	/**
	 * Gets a list of review forms assigned to the manuscript.
	 * @return a list of review forms
	 */
	public List<ReviewForm> getReviewList() {
		return myReviewFormList;
	}
	
	/**
	 * Adds a ReviewForm to the manuscript.
	 * @param theReview a review form
	 */
	public void addReviewForm(ReviewForm theReview){
		myReviewFormList.add(theReview);
	}

	/**
	 * Gets a List of Reviewers assigned to the Manuscript.
	 * @return a list of reviewers
	 */
	public List<User> getReviewerList() {
		return myReviewerList;
	}
	
	/** 
	 * Adds a Reviewer to the list of reviewers.
	 * @param theReviewer a reviewer
	 */
	public void addReviewer(User theReviewer){
		myReviewerList.add(theReviewer);
	}
	
	/**
	 * Gets a list of Recommendation Forms assigned to manuscript.
	 * @return a list of recommendation
	 */
	public List<RecommendationForm> getRecomFormList() {
		return myRecomFormList;
	}
	
	/**
	 * Adds a RecommendationForm to the manuscript
	 * @param theRecom a recommendation
	 */
	public void addRecommendation (RecommendationForm theRecom){
		myRecomFormList.add(theRecom);
	}
	
	/**
	 * Overrides the toString() object method. Returns a string
	 * representation of a Manuscript (title only). 
	 * @return a string representation for manuscript
	 */
	@Override
	public String toString() {
		return super.getTitle();
	}
}

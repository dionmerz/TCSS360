package TCSS360;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Manuscript extends Paper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8730353916258807055L;
	private Status myStatus;
	private List<ReviewForm> myReviewFormList;
	private List<User> myReviewerList;
	private List<RecommendationForm> myRecomFormList;
	

	 public static enum Status {
		 //Paper status
		 SUBMITTED, RECOMMENDED, ACCEPTED, REJECTED;
	 }

	public Manuscript(String thePath, String theAuthor, String theSubmitDate,
			String theTitle) {
		super(thePath, theAuthor, theSubmitDate, theTitle);
		myStatus = Status.SUBMITTED;
		myRecomFormList = new ArrayList<RecommendationForm>();
		myReviewerList = new ArrayList<User>();
		myReviewFormList = new ArrayList<ReviewForm>();
	}

	public Status getStatus() {
		return myStatus;
	}

	public void setStatus(Status status) {
		this.myStatus = status;
	}

	public List<ReviewForm> getReviewList() {
		return myReviewFormList;
	}
	
	public void addReviewForm(ReviewForm theReview){
		myReviewFormList.add(theReview);
	}

	public List<User> getReviwerList() {
		// Iterating the list then print out everything
		return myReviewerList;
	}
	
	public void addReviewer(User theReviewer){
		myReviewerList.add(theReviewer);
	}
	
	public List<RecommendationForm> getRecomFormList() {
		return myRecomFormList;
	}
	
	public void addRecommendation (RecommendationForm theRecom){
		myRecomFormList.add(theRecom);
	}
	
	@Override
	public boolean equals(Object theOther) {
		
		boolean equal = false;
		Manuscript other;
		
		if ((theOther instanceof Manuscript)){
			other = (Manuscript) theOther;
			if (this.getAuthor().equals(other.getAuthor()) && this.getTitle().equals(other.getTitle())) {
				equal = true;
				
			}

		}
		return equal;
		
	}

}

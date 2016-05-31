package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Manuscript.Status;

/**
 * This class represents a Subprogram Chair role. 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @author Bernabe Guzman maintained
 * @version 1.0 5/27/16
 */
public class SubprogramChair extends Roles implements Serializable {

	private static final long serialVersionUID = 560617491878906874L;
	private static final int MAX_PAPERS = 4;

	/**
	 * @param theConference the current conference
	 */
	public SubprogramChair(Conference theConference) {
		super(theConference);
	}

	/**
	 * Assigns a reviewer to a manuscript. If the action was successful the method
	 * will return a list of true booleans. 
	 * @param theUserWithReviewerRole the user with reviewer role
	 * @param theManuscript the manuscript to review
	 * @return a boolean list
	 */
	public List<Boolean> assignReviewerManuscript(User theUserWithReviewerRole, Manuscript theManuscript) {
		//Get instance of Reviewer 	
		List<Boolean> result = new ArrayList<Boolean>();
		result.add(false);
		result.add(false);
		if(!theUserWithReviewerRole.getMyName().equals(theManuscript.getAuthor())) {
			result.set(0, true);
			
			if(theUserWithReviewerRole.getMyManuscriptsToReview().size() < MAX_PAPERS) {
				result.set(1, true);
				theUserWithReviewerRole.addMyManuscriptsToReview(theManuscript);
			} else {
				result.set(1, false);				
			}
		} else {
			result.set(0, false);			
		}		
		return result;
	}
	
	/**
	 * Appends a recommendation form to a manuscript along with its score. 
	 * @param theCurrentUser the author of the review form
	 * @param theCurrentConference the current conference
	 * @param theManuscript the manuscript
	 * @param score the score awarded
	 * @param thePath the file path
	 * @param theTitle the title
	 */
	public void appendRecomendationToManuscript(User theCurrentUser, Conference theCurrentConference, Manuscript theManuscript, 
									int score, String thePath, String theTitle) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());	
		RecommendationForm form = new RecommendationForm(thePath, theCurrentUser.getMyName(), date, theTitle, score);
		theManuscript.addRecommendation(form);
		theManuscript.setStatus(Status.RECOMMENDED);	
		theCurrentConference.removeManuscript(theManuscript); // Remove the manuscript and add it back on.
		theCurrentConference.addManuscript(theManuscript);
	}		
	
	/**
	 * Gets a list of reviewers from a list of users. This method only used by Subprogram chair UI.
	 * @param theUserList the user list
	 * @return the reviewer list
	 */
	public List<User> getListOfReviewersFromListOfUsers(List<User> theUserList) {
		List<User> reviewerList = new ArrayList<User>();
		for (User u : theUserList) {
			Reviewer tempRev = u.findReviewerRole();
			if (tempRev != null) {
				reviewerList.add(u);
			}
		}
			return reviewerList;
	}
	
	/**
	 * Assigns the Reviewer role to designated registered User.
	 * 
	 * @param theUser the User to be assigned Role of Reviewer
	 */
	public void designateReviewer(User theUser) {
		theUser.addMyRole(new Reviewer(this.getConference()));
	
	}
	
}

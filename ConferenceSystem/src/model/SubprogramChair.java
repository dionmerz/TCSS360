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

	public List<Boolean> assignReviewerManuscript(User theUser, Manuscript theManuscript) {
		//Get instance of Reviewer 	
		List<Boolean> result = new ArrayList<Boolean>();
		result.add(false);
		result.add(false);
		
		if(!theUser.getMyName().equals(theManuscript.getAuthor())) {
			result.set(0, true);
			if(theUser.getMyManuscriptsToReview().size() < MAX_PAPERS) {
				result.set(1, true);
				theUser.addMyManuscriptsToReview(theManuscript);
			} else {
				result.set(1, false);				
			}
		} else {
			result.set(0, false);			
		}		
		return result;
	}
	
	
	public void submitRecomendation(User theCurrentUser, Conference theCurrentConference, Manuscript theManuscript, 
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
	
}

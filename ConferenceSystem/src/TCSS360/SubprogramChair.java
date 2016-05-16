package TCSS360;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import TCSS360.Manuscript.Status;

public class SubprogramChair extends Roles implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 560617491878906874L;
	
	private static final int MAX_PAPERS = 4;


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
	
	
	public void submitRecomendation(User currentUser, Manuscript theManuscript, int score, String thePath, String theTitle) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());	
		RecommendationForm form = new RecommendationForm(thePath, currentUser.getMyName(), date, theTitle, score);
		theManuscript.addRecommendation(form);
		theManuscript.setStatus(Status.RECOMMENDED);
		
		Main.currentConference.removeManuscript(theManuscript);
		Main.currentConference.addManuscript(theManuscript);
		// Remove the manuscript and add it back on.
		
	}		
}

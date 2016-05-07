package TCSS360;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import TCSS360.Manuscript.Status;

public class SubprogramChair extends Roles implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 560617491878906874L;


	public SubprogramChair(Conference theConference) {
		super(theConference);
		// TODO Auto-generated constructor stub
	}


	public void assignReviewerManuscript(User theUser, Manuscript theManuscript) {
		//Get instance of Reviewer 	
		
		if(!theUser.getMyName().equals(theManuscript.getAuthor())) {
			if(theUser.getMyManuscriptsToReview().size() <= 3) {
				theUser.addMyManuscriptsToReview(theManuscript);
				System.out.println(theManuscript.getTitle() + " assigned to " + theUser.getMyName());
			} else {
				System.out.println("Failed to assign review to " + theUser.getMyName() + " because of review limit");
			}
		} else {
			System.out.println("Cannot assign a review to the author of the manuscript");
		}
		

	}
	
	
	public void submitRecomendation(Manuscript theManuscript, int score, String thePath, String theTitle) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		
		
		
		RecommendationForm form = new RecommendationForm(thePath, Main.currentUser.getMyName(), date, theTitle, score);
		theManuscript.addRecommendation(form);
		theManuscript.setStatus(Status.RECOMMENDED);
		
		System.out.println("Reccommendation Form for " + theManuscript.getTitle() + " submitted.");
	}

	
	
}

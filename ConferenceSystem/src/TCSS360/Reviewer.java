package TCSS360;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reviewer extends Roles implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2085432913222156308L;

	public Reviewer(Conference theConference) {
		super(theConference);
	}
	
	public void uploadReviewForm(final String thePath, final String theAuthor, final String theTitle, Manuscript theManuscript) {
		boolean isAllowed = false;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());

		ReviewForm r = new ReviewForm(thePath, theAuthor, date, theTitle, Main.currentUser);
		if(cal.before(Main.currentConference.getReviewDeadlineDate())) {
			for(Manuscript m: Main.currentUser.getMyManuscriptsToReview()) {
				if (m.getTitle() == theManuscript.getTitle() && m.getAuthor() == theManuscript.getAuthor()) {
					isAllowed = true;
				}
			}
			if (isAllowed) {
				Main.currentUser.addReview(r);
				theManuscript.addReviewForm(r);
				System.out.println("Review " + r.getTitle() + " submitted.");
			}
			else {
				System.out.println("Not assigned this Paper to review...");
			}
		} else {
			System.out.println("Submission failed review deadline has passed.");
		}
		
	}
	
	public void viewAssignedManuscripts() {
		// Prints all the manuscripts out from a list provided by user.
		int count = 1;
		
		for(Manuscript m: Main.currentUser.getMyManuscriptsToReview()) {
			System.out.println(count + ". " + m.getTitle());
		}
		System.out.println();
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
		
	}

}
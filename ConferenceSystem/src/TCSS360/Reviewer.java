package TCSS360;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Tests for the Reviewer Class.
 * 
 * @author Andrew Merz, Bernabe Guzman
 * @version 5/8/2016
 */
public class Reviewer extends Roles implements Serializable {	

	/**
	 * Serial identification number
	 */
	private static final long serialVersionUID = 2085432913222156308L;

	/**
	 * Reviewer Class Constructor
	 * @param theConference
	 */
	public Reviewer(Conference theConference) {
		super(theConference);
	}
	
	/**
	 * Uploads a review form to a manuscript
	 * @param currentUser
	 * @param thePath
	 * @param theAuthor
	 * @param theTitle
	 * @param theManuscript
	 */
	public void uploadReviewForm(User currentUser, final String thePath, final String theAuthor, 
			final String theTitle, Manuscript theManuscript) {
		boolean isAllowed = false;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());

		ReviewForm r = new ReviewForm(thePath, theAuthor, date, theTitle, currentUser);
		if(cal.before(getConference().getReviewDeadlineDate())) {//currentConference.getReviewDeadlineDate())) {
			for(Manuscript m: currentUser.getMyManuscriptsToReview()) {
				if (m.getTitle() == theManuscript.getTitle() && m.getAuthor() == theManuscript.getAuthor()) {
					isAllowed = true;
				}
			}
			if (isAllowed) {
				currentUser.addReview(r);
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
	
	/**
	 * Prints out the assigned manuscripts 
	 * @param currentUser 
	 */
	public void viewAssignedManuscripts(User currentUser) {
		// Prints all the manuscripts out from a list provided by user.
		int count = 1;
		
		for(Manuscript m: currentUser.getMyManuscriptsToReview()) {
			System.out.println(count + ". " + m.getTitle());
		}
		System.out.println();
	}
	
	/**
	 * Overrides object to string method. Returns a
	 * string representation of a reviewer (user name).
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
		
	}

}
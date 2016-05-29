package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A Reviewer role for a specific Conference.
 * 
 * @author Andrew Merz, Bernabe Guzman
 * @version 5/8/2016
 */
public class Reviewer extends Roles implements Serializable {	

	private static final long serialVersionUID = 2085432913222156308L;

	/**
	 * @param theConference, The conference that this Reviewer is an 
	 * 			Reviewer for.
	 */
	public Reviewer(Conference theConference) {
		super(theConference);
	}

	/**
	 * Uploads a review form to a manuscript
	 * @param currentUser The current User who is logged in as a Reviewer.
	 * @param thePath The file path of the review form.
	 * @param theAuthor The author of the review form's name.
	 * @param theTitle  The title of the review form.
	 * @param theManuscript The manuscript that is being reviewed.
	 */
	public List<Boolean> uploadReviewForm(User currentUser,Conference currentConference, 
			final String thePath, final String theAuthor, final String theTitle, Manuscript theManuscript) {
		boolean isAllowed = false;
		boolean inTime = false;
		List<Boolean> allowed = new ArrayList<Boolean>();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());

		ReviewForm r = new ReviewForm(thePath, theAuthor, date, theTitle, currentUser);
		if(cal.before(currentConference.getReviewDeadlineDate())) {
			inTime = true;
			for(Manuscript m: currentUser.getMyManuscriptsToReview()) {
				if (m.getTitle() == theManuscript.getTitle() && m.getAuthor() == theManuscript.getAuthor()) {
					isAllowed = true;
				}
			}
			
			if (isAllowed)  {
				currentUser.addReview(r);
				theManuscript.addReviewForm(r);
			}
			
		}
		
		allowed.add(isAllowed);
		allowed.add(inTime);
		return allowed;
		
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
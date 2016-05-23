package TCSS360;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class represents a user object.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0 5/5/2016
 */
public class User implements Serializable {
	
	/**
	 * Serial identification number 
	 */
	private static final long serialVersionUID = -5949934155104871686L;

	/**
	 * User name.
	 */
	private String myName;
	
	/**
	 * User login name.
	 */
	private String myLoginName;
	
	/**
	 * User email.
	 */
	private String myEmail;
	
	/**
	 * User's submit manuscript.
	 */
	private List<Manuscript> myAuthoredManuscripts;
	
	/**
	 * User's roles list.
	 */
	private List<Roles> myRoles;
	
	/**
	 * List of manuscript to review for user.
	 */
	private List<Manuscript> myManuscriptsToReview;
	
	/**
	 * List of manuscript assigned by program chair from subprogram chair.
	 */
	private List<Manuscript> mySubProgManuscripts;
	
	/**
	 * List of reviews that user submitted.
	 */
	private List<ReviewForm> myReviews;
	
	/**
	 * List of reviewers for user. 
	 */
	//private List<User> myReviewers;
	//private List<Conference> mySubprogChairsConferences;
	
	/**
	 * Constructor for user object using minimum arguments for instantiation.
	 * @param theName User name.
	 * @param theLoginName User login name.
	 * @param theEmail User email.
	 */
	public User(String theName, String theLoginName, String theEmail) {
		super();
		this.myName = theName;
		this.myLoginName = theLoginName;
		this.myEmail = theEmail;
		this.myAuthoredManuscripts = new ArrayList<Manuscript>();
		this.myRoles = new ArrayList<Roles>();
		this.myManuscriptsToReview = new ArrayList<Manuscript>();
		this.myReviews = new ArrayList<ReviewForm>();
		this.mySubProgManuscripts = new ArrayList<Manuscript>();
	}
	
	/**
	 * Constructor for user object using minimum arguments for instantiation.
	 * @param theName User name.
	 * @param theLoginName User login name.
	 * @param theEmail User email.
	 */
	public User(String theName, String theLoginName, String theEmail, List<Roles> theRoles, List<Manuscript> theAuthoredManuscripts,
				List<Manuscript> theManuscriptsToReview, List<ReviewForm> theReviews, List<Manuscript> theSubprogramManuscripts) {
		super();
		this.myName = theName;
		this.myLoginName = theLoginName;
		this.myEmail = theEmail;
		this.myRoles = theRoles;
		this.myAuthoredManuscripts = theAuthoredManuscripts;
		this.myManuscriptsToReview = theManuscriptsToReview;
		this.myReviews = theReviews;
		this.mySubProgManuscripts = theSubprogramManuscripts;
	}

	/**
	 * Getter for user's name.
	 * @return user's name.
	 */
	public String getMyName() {
		return myName;
	}

	/**
	 * Setter for user's name.
	 * @param theName user's name
	 */
	public void setMyName(String theName) {
		this.myName = theName;
	}

	/**
	 * Getter for user's login name.
	 * @return user's login name.
	 */
	public String getMyLoginName() {
		return myLoginName;
	}

	/**
	 * Setter for user's login name.
	 * @param theLoginName user's login name.
	 */
	public void setMyLoginName(String theLoginName) {
		this.myLoginName = theLoginName;
	}

	/**
	 * Getter for user's email. 
	 * @return 
	 */
	public String getMyEmail() {
		return myEmail;
	}

	/**
	 * Setter for emial
	 * @param theEmail
	 */
	public void setMyEmail(String theEmail) {
		this.myEmail = theEmail;
	}

	/**
	 * Gets a list of manuscripts
	 * @return manuscripts
	 */
	public List<Manuscript> getMyManuscripts() {
		return myAuthoredManuscripts;
	}

	/**
	 * Adds a manuscript
	 * @param theManuscript
	 */
	public void addMyManuscript(Manuscript theManuscript) {
		this.myAuthoredManuscripts.add(theManuscript);
	}

	/**
	 * Removes a manuscript
	 * @param theManuscript
	 */
	public void removeManuscript(Manuscript theManuscript) {
		this.myAuthoredManuscripts.remove(theManuscript);
	}
	
	/**
	 * Gets a list of Subprogram Chairs
	 * @return Subprogram Chairs
	 */
	public List<Manuscript> getSubProgManuscript() {
		return this.mySubProgManuscripts;
	}
	
	/**
	 * Adds a manuscript the the Subprogram Chairs manuscript list.
	 * @param theManuscript
	 */
	public void addSubProgManuscript(Manuscript theManuscript) {
		this.mySubProgManuscripts.add(theManuscript);
	}
	
	/**
	 * Gets a list of roles.
	 * @return roles
	 */
	public List<Roles> getMyRoles() {
		return myRoles;
	}

	/**
	 * Adds a role 
	 * @param theRole
	 */
	public void addMyRole(Roles theRole) {
		this.myRoles.add(theRole);
	}
	
	/**
	 * Removes a role
	 * @param theRole
	 */
	public void removeMyRole(Roles theRole) {
		this.myRoles.remove(theRole);
	}

	/**
	 * Gets a list of manuscripts to review
	 * @return manuscripts to review
	 */
	public List<Manuscript> getMyManuscriptsToReview() {
		return myManuscriptsToReview;
	}

	/**
	 * Adds a manuscript to a list of manuscripts to review
	 * @param theManuscript
	 */
	public void addMyManuscriptsToReview(Manuscript theManuscript) {
		this.myManuscriptsToReview.add(theManuscript);
	}
	
	/**
	 * Removes a manuscript from the list of manuscripts to review
	 * @param theManuscript
	 */
	public void removeMyManuscriptsToReview(Manuscript theManuscript) {
		this.myManuscriptsToReview.remove(theManuscript);
	}
	
	/**
	 * Gets a list of my review forms
	 * @return
	 */
	public List<ReviewForm> getMyReviews() {
		return myReviews;
	}
	
	/**
	 * Adds a review form to list
	 * @param theReview
	 */
	public void addReview(ReviewForm theReview) {
		this.myReviews.add(theReview);
	}
	
	/**
	 * Removes a review form 
	 * @param theReview
	 */
	public void removeReview(ReviewForm theReview) {
		this.myReviews.remove(theReview);
	}
	
	/**
	 * Submits a manuscript to a conference.
	 * @param thePath file path
	 * @param theTitle title 
	 * @param currentUser current user
	 * @param currentConference conference
	 */
	public boolean submitManuscript(final String thePath, String theTitle, User currentUser, Conference currentConference) {
		boolean isAllowed = false;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		
		Manuscript newPaper = new Manuscript(thePath, currentUser.getMyName(), date, theTitle);
		
		if(cal.before(currentConference.getPaperDeadlineDate())) {
			currentUser.addMyManuscript(newPaper);
			currentConference.addManuscript(newPaper);
			if(!hasRole(currentConference, Main.AUTHOR, currentUser)) {
				currentUser.addMyRole(new Author(currentConference));
			}
			isAllowed = true;
		}
		return isAllowed;
	}

	/**
	 * Finds and returns author role
	 * @return author
	 */
	public Author findAuthorRole() {
		Author AuthorRole = null;
		for(Roles r : this.getMyRoles()) {
			if(r instanceof Author) {
				AuthorRole = (Author) r;
			}
		}
		return AuthorRole;
	}
	
	/**
	 * Finds and returns the Program Chari role
	 * @return program chair 
	 */
	public ProgramChair findProgramChairRole() {
		ProgramChair ProgramChairRole = null;
		for(Roles r : this.getMyRoles()) {
			if(r instanceof ProgramChair) {
				ProgramChairRole = (ProgramChair) r;
			}
		}
		return ProgramChairRole;
	}
	
	/**
	 * Finds and returns the Subprogram Chair role. 
	 * @return Subprogram Chair
	 */
	public SubprogramChair findSubprogramChairRole() {
		SubprogramChair SubprogramChairRole = null;
		for(Roles r : this.getMyRoles()) {
			if(r instanceof SubprogramChair) {
				SubprogramChairRole = (SubprogramChair) r;
			}
		}
		return SubprogramChairRole;
	}
	
	/**
	 * Finds and returns the reviewer role
	 * @return a reviewer
	 */
	public Reviewer findReviewerRole() {
		Reviewer ReviewerRole = null;
		for(Roles r : this.getMyRoles()) {
			if(r instanceof Reviewer) {
				ReviewerRole = (Reviewer) r;
			}
		}
		return ReviewerRole;
	}
	
	/**
	 * Overrides the object equals method. Checks to see if the
	 * user has the same login.
	 * @param theOther object
	 * @return true or false
	 */
	@Override
	public boolean equals(Object theOther) {
		
		boolean equal = false;
		User other;
		
		if ((theOther instanceof User)){
			other = (User) theOther;
			if (this.getMyLoginName().equals(other.getMyLoginName())) {
				equal = true;
			}
		}
		return equal;
	}
	
	
	/**
	 * This method checks to see if the user has a specified role in a
	 * conference.
	 * 
	 * @param theConference a conference
	 * @param theRole a role
	 * @param theUser a user
	 * @return true or false
	 */
	public boolean hasRole(Conference theConference, Roles theRole, User theUser) {
		boolean result = false;

		for (Roles r : theUser.getMyRoles()) {
			if (r.getConference().getName().equals(theConference.getName())
					&& theRole.getClass().getSimpleName().equals(r.getClass().getSimpleName())) {
				result = true;
			}
		}
		return result;
	}
	
	
}

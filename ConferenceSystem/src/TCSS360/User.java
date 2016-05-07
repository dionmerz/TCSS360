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
	 * 
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
	private List<User> myReviewers;
	//private List<Conference> mySubprogChairsConferences;
	
	/**
	 * Constructor for user object. 
	 * 
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
		//this.mySubprogChairsConferences = theSubprogChairsConferences;
	}

	/**
	 * Getter for user's name.
	 * 
	 * @return user's name.
	 */
	public String getMyName() {
		return myName;
	}

	/**
	 * Setter for user's name.
	 * 
	 * @param theName user's name
	 */
	public void setMyName(String theName) {
		this.myName = theName;
	}

	/**
	 * Getter for user's login name.
	 * 
	 * @return user's login name.
	 */
	public String getMyLoginName() {
		return myLoginName;
	}

	/**
	 * Setter for user's login name.
	 * 
	 * @param theLoginName user's login name.
	 */
	public void setMyLoginName(String theLoginName) {
		this.myLoginName = theLoginName;
	}

	/**
	 * Getter for user's email. 
	 * 
	 * @return 
	 */
	public String getMyEmail() {
		return myEmail;
	}

	public void setMyEmail(String theEmail) {
		this.myEmail = theEmail;
	}

	public List<Manuscript> getMyManuscripts() {
		return myAuthoredManuscripts;
	}
	
	public List<Manuscript> getSubProgManuscript() {
		return this.mySubProgManuscripts;
	}
	
	public void addSubProgManuscript(Manuscript theManuscript) {
		this.mySubProgManuscripts.add(theManuscript);
	}

	public void addMyManuscript(Manuscript theManuscript) {
		this.myAuthoredManuscripts.add(theManuscript);
	}

	public void removeManuscript(Manuscript theManuscript) {
		this.myAuthoredManuscripts.remove(theManuscript);
	}
	
	public List<Roles> getMyRoles() {
		return myRoles;
	}

	public void addMyRole(Roles theRole) {
		this.myRoles.add(theRole);
	}
	
	public void removeMyRole(Roles theRole) {
		this.myRoles.remove(theRole);
	}

	public List<Manuscript> getMyManuscriptsToReview() {
		return myManuscriptsToReview;
	}

	public void addMyManuscriptsToReview(Manuscript theManuscript) {
		this.myManuscriptsToReview.add(theManuscript);
	}
	
	public void removeMyManuscriptsToReview(Manuscript theManuscript) {
		this.myManuscriptsToReview.remove(theManuscript);
	}
	

	public List<ReviewForm> getMyReviews() {
		return myReviews;
	}
	
	public void addReview(ReviewForm theReview) {
		this.myReviews.add(theReview);
	}
	
	public void removeReview(ReviewForm theReview) {
		this.myReviews.remove(theReview);
	}

	public List<User> getMyReviewers() {
		return myReviewers;
	}

	public void addMyReviewers(User theReviewers) {
		this.myReviewers.add(theReviewers);
	}
	
	public void submitManuscript(final String thePath, String theTitle) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		
		Manuscript newPaper = new Manuscript(thePath, Main.currentUser.getMyName(), date, theTitle);
		
		if(cal.before(Main.currentConference.getPaperDeadlineDate())) {
			Main.currentUser.addMyManuscript(newPaper);
			Main.currentConference.addManuscript(newPaper);
			if(!Main.hasRole(Main.currentConference, Main.AUTHOR, Main.currentUser)) {
				Main.currentUser.addMyRole(new Author(Main.currentConference));
			}
			System.out.println(newPaper.getTitle() + " submitted to Conference " + Main.currentConference.getName());
		} else {
			System.out.println("The deadline for manuscript submission has passed.\n");
		}
		

	}

	public Author findAuthorRole() {
		Author AuthorRole = null;
		for(Roles r : this.getMyRoles()) {
			if(r instanceof Author) {
				AuthorRole = (Author) r;
			}
		}
		return AuthorRole;
	}
	
	public ProgramChair findProgramChairRole() {
		ProgramChair ProgramChairRole = null;
		for(Roles r : this.getMyRoles()) {
			if(r instanceof ProgramChair) {
				ProgramChairRole = (ProgramChair) r;
			}
		}
		return ProgramChairRole;
	}
	
	public SubprogramChair findSubprogramChairRole() {
		SubprogramChair SubprogramChairRole = null;
		for(Roles r : this.getMyRoles()) {
			if(r instanceof SubprogramChair) {
				SubprogramChairRole = (SubprogramChair) r;
			}
		}
		return SubprogramChairRole;
	}
	
	public Reviewer findReviewerRole() {
		Reviewer ReviewerRole = null;
		for(Roles r : this.getMyRoles()) {
			if(r instanceof Reviewer) {
				ReviewerRole = (Reviewer) r;
			}
		}
		return ReviewerRole;
	}
}

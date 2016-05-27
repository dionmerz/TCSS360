package model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
	
	private static final long serialVersionUID = -5949934155104871686L;

	private String myName;
	private String myLoginName;
	private String myEmail;
	
	private List<Manuscript> myAuthoredManuscripts;
	private List<Manuscript> myManuscriptsToReview;
	private List<Manuscript> mySubProgManuscripts;
	
	private List<Roles> myRoles;
	
	private List<ReviewForm> myReviews;
	
	/**
	 * @param theName The full name of the user.
	 * @param theLoginName The login name of the user.
	 * @param theEmail The user's email address.
	 */
	public User(String theName, String theLoginName, String theEmail) {
		super();
		this.myName = theName;
		this.myLoginName = theLoginName;
		this.myEmail = theEmail;
		this.myRoles = new ArrayList<Roles>();
		this.myAuthoredManuscripts = new ArrayList<Manuscript>();
		this.myManuscriptsToReview = new ArrayList<Manuscript>();
		this.myReviews = new ArrayList<ReviewForm>();
		this.mySubProgManuscripts = new ArrayList<Manuscript>();
	}
	
	/**
	 * Constructor only for use in testing.
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
	 * Getter for the user's full name.
	 * @return The user's full name.
	 */
	public String getMyName() {
		return myName;
	}

	/**
	 * Setter for user's full name.
	 * @param theName The user's full name.
	 */
	public void setMyName(String theName) {
		this.myName = theName;
	}

	/**
	 * Getter for the user's login name.
	 * @return The user's login name.
	 */
	public String getMyLoginName() {
		return myLoginName;
	}

	/**
	 * Setter for the user's login name.
	 * @param theLoginName The user's login name.
	 */
	public void setMyLoginName(String theLoginName) {
		this.myLoginName = theLoginName;
	}

	/**
	 * Getter for the user's email address. 
	 * @return the user's email address.
	 */
	public String getMyEmail() {
		return myEmail;
	}

	/**
	 * Setter for the user's email address.
	 * @param theEmail The user's email address.
	 */
	public void setMyEmail(String theEmail) {
		this.myEmail = theEmail;
	}

	/**
	 * Getter for the list of manuscripts authored by the user.
	 * @return manuscripts The list of manuscripts authored by the user.
	 */
	public List<Manuscript> getMyManuscripts() {
		return myAuthoredManuscripts;
	}

	/**
	 * Adds a manuscript to the list of manuscripts authored by the user.
	 * @param theManuscript The manuscript to add to the list of manuscripts authored by the user.
	 */
	public void addMyManuscript(Manuscript theManuscript) {
		this.myAuthoredManuscripts.add(theManuscript);
	}

	/**
	 * Removes a manuscript from the list of manuscripts authored by the user.
	 * @param theManuscript The manuscript to remove from the list of manuscripts authored by the user.
	 */
	public void removeManuscript(Manuscript theManuscript) {
		this.myAuthoredManuscripts.remove(theManuscript);
	}
	
	/**
	 * Getter for the list of manuscripts assigned by a program chair.
	 * @return mySubProgManuscripts A list of all manuscripts assigned by a program chair.
	 */
	public List<Manuscript> getSubProgManuscript() {
		return this.mySubProgManuscripts;
	}
	
	/**
	 * Adds a manuscript to the Subprogram Chairs manuscript list.
	 * @param theManuscript The manuscript to be added to the Subprogram Chairs manuscript list.
	 */
	public void addSubProgManuscript(Manuscript theManuscript) {
		this.mySubProgManuscripts.add(theManuscript);
	}
	
	/**
	 * Getter for the list of all roles this user contains.
	 * @return roles the list of all roles this user contains.
	 */
	public List<Roles> getMyRoles() {
		return myRoles;
	}

	/**
	 * Adds a role to this user's list of roles.
	 * @param theRole the role to add to this users list of roles.
	 */
	public void addMyRole(Roles theRole) {
		this.myRoles.add(theRole);
	}
	
	/**
	 * Removes a role from this user's list of roles.
	 * @param theRole The role to remove from this user's list of roles.
	 */
	public void removeMyRole(Roles theRole) {
		this.myRoles.remove(theRole);
	}

	/**
	 * Getter for the list of manuscripts this user has been assigned to review.
	 * @return myManuscriptsToReview the list of manuscripts a user has been assigned to review.
	 */
	public List<Manuscript> getMyManuscriptsToReview() {
		return myManuscriptsToReview;
	}

	/**
	 * Adds a manuscript to the list of manuscripts this user has been assigned to review.
	 * @param theManuscript The manuscript to add to the list of manuscripts a user has been assigned to review.
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
	 * Getter for the list of review forms submitted by this user.
	 * @return
	 */
	public List<ReviewForm> getMyReviews() {
		return myReviews;
	}
	
	/**
	 * Adds a review form to the list of review forms submitted by this user.
	 * @param theReview
	 */
	public void addReview(ReviewForm theReview) {
		this.myReviews.add(theReview);
	}
	
	/**
	 * Removes a review form from the list of review forms submitted by this user.
	 * @param theReview
	 */
	public void removeReview(ReviewForm theReview) {
		this.myReviews.remove(theReview);
	}
	
	/**
	 * Submits a manuscript to a conference and uploads the file.
	 * @param thePath Absolute path of the manuscript to be submitted.
	 * @param theTitle The title of the manuscript to be submitted.
	 * @param currentUser The author of the manuscript to be submitted.
	 * @param currentConference The conference the manuscript will be submitted to.
	 */
	public boolean submitManuscript(final String thePath, String theTitle, User currentUser, Conference currentConference) {
		boolean isAllowed = false;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		
		
		Path localFile = Paths.get(thePath);
		//new File("","");
		File uploadedFile = new File(Paths.get(".").toAbsolutePath().normalize().toString() +"/uploaded/" + currentConference.getName() + "_" + localFile.getFileName());
		System.out.println(uploadedFile.getPath());
		try {
			Files.copy(localFile, uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);	
			Manuscript newPaper = new Manuscript(uploadedFile.getPath(), currentUser.getMyName(), date, theTitle);
			
			
			if(cal.before(currentConference.getPaperDeadlineDate())) {
				currentUser.addMyManuscript(newPaper);
				currentConference.addManuscript(newPaper);
				
				if (this.findAuthorRole() == null) {
					currentUser.addMyRole(new Author(currentConference));
				}
				isAllowed = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return isAllowed;
	}

	/**
	 * Finds and returns this user's author role if it exists.
	 * @return author This user's Author role if found, null otherwise.
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
	 * Finds and returns this user's Program Chair role if it exists.
	 * @return program chair This user's ProgramChair role if found, null otherwise.
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
	 * Finds and returns this user's Subprogram Chair role if it exists.
	 * @return Subprogram Chair This user's SubprogramChair role if found, null otherwise.
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
	 * Finds and returns this user's reviewer role if it exists.
	 * @return a reviewer This user's Reviewer role if found, null otherwise.
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
	 * user has the same login name.
	 * @param theOther Object to be compared with
	 * @return equal true if both objects are the same, false otherwise.
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
	 * specified conference.
	 * 
	 * @param theConference The specified conference the role has permissions for.
	 * @param theRole The specified role to check for.
	 * @param theUser The user to check for the specified role in.
	 * @return result true if the user has the specified role in the specified conference, false otherwise.
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

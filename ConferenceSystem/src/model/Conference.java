package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class represents a conference object. The conference has a starting date
 * and ending date along with manuscript and review deadlines.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0 5/5/2016
 */
public class Conference implements Serializable {
	
	private static final long serialVersionUID = 2834762961375246670L;
	private String Name;
	private User programChair;
	private String startingDate;
	private String endingDate;
	private String manuscriptDeadline;
	private String reviewDeadline;
	private List<Manuscript> mySubmittedManuscriptList;
	private List<User> subProChairList;
	private Calendar myReviewDeadline;
	private Calendar myManuscriptDeadline;
	
	/**
	 * Constructor for conference class.
	 * 
	 * @param theName Name of conference
	 * @param theProgramChair The program chair
	 * @param theStartingDate The starting date of the conference.
	 * @param theEndingDate The ending date of the conference.
	 * @param thePaperDeadline The paper submission deadline.
	 * @param theReviewDeadline The review submission deadline.
	 */
	public Conference(String theName, User theProgramChair,
					String theStartingDate, String theEndingDate, 
					String thePaperDeadline,String theReviewDeadline, int reviewDeadlineOffset, int paperDeadlineOffset){
		this.Name = theName;
		this.programChair = theProgramChair;
		this.startingDate = theStartingDate;
		this.endingDate = theEndingDate;
		this.manuscriptDeadline = thePaperDeadline;
		this.reviewDeadline = theReviewDeadline;
		this.mySubmittedManuscriptList = new ArrayList<Manuscript>();
		this.subProChairList = new ArrayList<User>();
		
		myReviewDeadline = Calendar.getInstance();
		myManuscriptDeadline = Calendar.getInstance();
		myReviewDeadline.add(Calendar.DATE, reviewDeadlineOffset);
		myManuscriptDeadline.add(Calendar.DATE, paperDeadlineOffset);
	}
	
	/**
	 * Getter for conference name.
	 * 
	 * @return conference name
	 */
	public String getName() {
		return Name;
	}
	
	/**
	 * Setter for conference name.
	 * 
	 * @param name conference name
	 */
	public void setName(String name) {
		Name = name;
	}
	
	/**
	 * Getter for program chair
	 * 
	 * @return User
	 */
	public User getProgramChair() {
		return programChair;
	}
	
	/**
	 * Setter for program chair.
	 * 
	 * @param programChair User
	 */
	public void setProgramChair(User programChair) {
		this.programChair = programChair;
	}
	
	/**
	 * Getter for conference starting date.
	 * 
	 * @return conference starting date.
	 */
	public String getStartingDate() {
		return startingDate;
	}
	
	/**
	 * Setter for conference starting date. 
	 *  
	 * @param startingDate Date
	 */
	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}
	
	/**
	 * Getting for conference ending date.
	 * 
	 * @return conference ending date
	 */
	public String getEndingDate() {
		return endingDate;
	}
	
	/**
	 * Setter for conference ending date.
	 * 
	 * @param endingDate conference ending date
	 */
	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}
	
	/**
	 * Getter for conference paper deadline.
	 * 
	 * @return conference paper deadline
	 */
	public String getPaperDeadline() {
		return manuscriptDeadline;
	}
	
	/**
	 * Setter for conference paper deadline
	 * 
	 * @param paperDeadline date
	 */
	public void setPaperDeadline(String paperDeadline) {
		this.manuscriptDeadline = paperDeadline;
	}
	
	/**
	 * Getter for review deadline.
	 * 
	 * @return review deadline.
	 */
	public String getReviewDeadline() {
		return reviewDeadline;
	}
	
	/**
	 * Setter for review deadline.
	 * @param reviewDeadline
	 */
	public void setReviewDeadline(String reviewDeadline) {
		this.reviewDeadline = reviewDeadline;
	}
	
	/**
	 * Adds a manuscript to the list of manuscripts in conference. 
	 * @param theManuscript to add to list
	 */
	public void addManuscript(Manuscript theManuscript) {
		this.mySubmittedManuscriptList.add(theManuscript);
	}
	
	/**
	 * Removes the manuscript specified from a list of manuscripts in conference. 
	 * @param theManuscript to remove from list 
	 */
	public void removeManuscript(Manuscript theManuscript) {
		this.mySubmittedManuscriptList.remove(theManuscript);
	}
	
	/**
	 * Returns a list of manuscripts in the conference
	 * @return a list of manuscripts
	 */
	public List<Manuscript> getManuscripts() {
		return mySubmittedManuscriptList;
	}
	
	/**
	 * Returns a list of SubProgramChairs in conference.  
	 * @return list of subprogram chairs
	 */
	public List<User> getSubProChairList() {
		return subProChairList;
	}
	
	/**
	 * Adds a Subprogram Chair to a list in conference.
	 * @param theUser a SubprogramChair
	 */
	public void addSubProChairList(User theUser) {
		subProChairList.add(theUser);
	}
	
	/**
	 * Gets the deadline date for submitting manuscripts.
	 * @return Calendar date
	 */
	public Calendar getPaperDeadlineDate() {
		return myManuscriptDeadline;
	}
	
	/**
	 * Sets the deadline for submitting manuscripts.
	 * @param theCalendar a calendar date
	 */
	public void setPaperDeadlineDate(Calendar theCalendar) {
		myManuscriptDeadline = theCalendar;
	}
	
	/**
	 * Gets the deadline date for submitting reviews.
	 * @return Calendar date
	 */
	public Calendar getReviewDeadlineDate() {
		return myReviewDeadline;
	}

	/**
	 * Sets the deadline for submitting reviews.
	 * @param theCalendar a calendar date
	 */
	public void setReviewDeadlineDate(Calendar theCalendar) {
		myReviewDeadline = theCalendar;
	}
	
	/**
	 * Overrides toString object method and returns 
	 * a String representation of a conference.
	 * @return String representation of conference
	 */
	@Override
	public String toString() {
		return "conference [Name: " + Name + ", programChair: " + programChair
				+ ", startingDate: " + startingDate + ", endingDate: "
				+ endingDate + ", paperDeadline: " + manuscriptDeadline
				+ ", reviewDeadline: " + reviewDeadline + "]";
	}
}

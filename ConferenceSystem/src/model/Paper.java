package model;
import java.io.File;
//
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represent a paper object. 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 5/8/2016
 */
public class Paper implements Serializable {
	
	/**
	 * Serial identification number
	 */
	private static final long serialVersionUID = -8190150644233894201L;
	private File myFile;
	private String myAuthor;
	private String myTitle;
	private String mySubmitDateString;
	private Calendar mySubmittedDateCalendar;
	
	/**
	 *
	 * @param thePath file path
	 * @param theAuthor the author
	 * @param theDate the date created
	 * @param theTitle the title
	 */
	public Paper(String thePath, String theAuthor, String theDate,
			     String theTitle) {
		myFile = new File(thePath);
		myAuthor = theAuthor;
		mySubmitDateString = theDate;
		myTitle = theTitle;
		new Date();
		Calendar.getInstance();
		mySubmittedDateCalendar = Calendar.getInstance();
	}
	
	/**
	 * Gets the path of the paper.
	 * @return the path of the paper
	 */
	public String getPath() {
		return myFile.getAbsolutePath();
	}

	/**
	 * Gets the author of the paper.
	 * @return the name of the author
	 */
	public String getAuthor() {
		return myAuthor;
	}

	/**
	 * Sets the author of the paper.
	 * @param author the name of the author
	 */
	public void setAuthor(String author) {
		this.myAuthor = author;
	}

	/**
	 * Gets the submitted date of the paper.
	 * @return the submitted date for this paper.
	 */
	public String getSubmittedDateString() {
		return mySubmitDateString;
	}

	/**
	 * Sets the date of the paper as a string.
	 * @param submitDate: the submitted date for this paper.
	 */
	public void setSubmittedDateString(String submitDate) {
		this.mySubmitDateString = submitDate;
	}
	
	/**
	 * Gets the submitted date of the paper as a calendar object.
	 * @return a Calendar object that represents the submitted date.
	 */
	public Calendar getSubmittedDateCalendar() {
		return mySubmittedDateCalendar;
	}
	
	/**
	 * Sets the submitted date of a paper as a calendar object. 
	 * @param theSubmittedDate: the submitted date for this paper. 
	 */
	public void setSubmittedDateCalendar(Calendar theSubmittedDate) {
		mySubmittedDateCalendar = theSubmittedDate;
	}
	
	/**
	 * Gets the title of the paper.
	 * @return the title of the paper.
	 */
	public String getTitle(){
		return myTitle;
	}
	
	/**
	 * Sets the title of the paper.
	 * @param theTitle the title for this paper.
	 */
	public void setTitle(String theTitle) {
		myTitle = theTitle;
	}
	
	/**
	 * Checks to see if Paper is equal to another. 
	 * @param theOther Paper object
	 * @returns true or false
	 */
	@Override
	public boolean equals(Object theOther) {
		boolean equal = false;
		Paper other;
		
		if ((theOther instanceof Paper)){
			other = (Paper) theOther;
			if (this.getAuthor().equals(other.getAuthor()) && this.getTitle().equals(other.getTitle())) {
				equal = true;
			}
		}
		return equal;
	}

}

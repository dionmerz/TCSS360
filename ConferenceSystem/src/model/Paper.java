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
	 * Paper Class Constructor
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
	 * @return the path as a string
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
	 * @return a string representation of a date
	 */
	public String getSubmittedDateString() {
		return mySubmitDateString;
	}

	/**
	 * Sets the date of the paper as a string.
	 * @param submitDate a string representation of a date
	 */
	public void setSubmittedDateString(String submitDate) {
		this.mySubmitDateString = submitDate;
	}
	
	/**
	 * Gets the submitted date of the paper as a calendar object.
	 * @return a Calendar object 
	 */
	public Calendar getSubmittedDateCalendar() {
		return mySubmittedDateCalendar;
	}
	
	/**
	 * Sets the submitted date of a paper as a calendar object. 
	 * @param theSubmittedDate a calendar object. 
	 */
	public void setSubmittedDateCalendar(Calendar theSubmittedDate) {
		mySubmittedDateCalendar = theSubmittedDate;
	}
	
	/**
	 * Gets the title of the paper.
	 * @return the title
	 */
	public String getTitle(){
		return myTitle;
	}
	
	/**
	 * Sets the title of the paper.
	 * @param theTitle the title
	 */
	public void setTitle(String theTitle) {
		myTitle = theTitle;
	}

}

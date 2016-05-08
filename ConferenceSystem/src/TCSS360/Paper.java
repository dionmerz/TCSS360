package TCSS360;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Paper implements Serializable {
	
	/**
	 * Serial identification number
	 */
	private static final long serialVersionUID = -8190150644233894201L;
	private String myPath;
	private String myAuthor;
	private String myTitle;
	private String mySubmitDateString;
	private Calendar mySubmittedDateCalendar;
	
	public Paper(String thePath, String theAuthor, String theDate,
			     String theTitle) {
		myPath = thePath;
		myAuthor = theAuthor;
		mySubmitDateString = theDate;
		myTitle = theTitle;
		new Date();
		Calendar.getInstance();
		mySubmittedDateCalendar = Calendar.getInstance();
	}
	
	public String getPath() {
		return myPath;
	}

	public void setPath(String path) {
		this.myPath = path;
	}

	public String getAuthor() {
		return myAuthor;
	}

	public void setAuthor(String author) {
		this.myAuthor = author;
	}

	public String getSubmittedDateString() {
		return mySubmitDateString;
	}

	public void setSubmittedDateString(String submitDate) {
		this.mySubmitDateString = submitDate;
	}
	
	public Calendar getSubmittedDateCalendar() {
		return mySubmittedDateCalendar;
	}
	public void setSubmittedDateCalendar(Calendar theSubmittedDate) {
		mySubmittedDateCalendar = theSubmittedDate;
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	public void setTitle(String theTitle) {
		myTitle = theTitle;
	}

}

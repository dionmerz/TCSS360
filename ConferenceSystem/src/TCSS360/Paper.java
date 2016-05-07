package TCSS360;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Paper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8190150644233894201L;
	private String myPath;
	private String myAuthor;
	private String mySubmitDate;
	private String myTitle;
	private Calendar mySubmittedDate;
	
	public Paper(String thePath, String theAuthor, String theDate,
			     String theTitle) {
		myPath = thePath;
		myAuthor = theAuthor;
		mySubmitDate = theDate;
		myTitle = theTitle;
		new Date();
		Calendar.getInstance();
		mySubmittedDate = Calendar.getInstance();
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

	public String getSubmitDate() {
		return mySubmitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.mySubmitDate = submitDate;
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	public void setTitle(String theTitle) {
		myTitle = theTitle;
	}

}

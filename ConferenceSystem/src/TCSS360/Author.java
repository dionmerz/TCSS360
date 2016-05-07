package TCSS360;

import java.io.Serializable;
import java.util.List;

/**
 * One of the subclass of Roles called Author.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0 5/5/2016
 */
public class Author extends Roles implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3594012237429715507L;

	/**
	 * Constructor for author class
	 * 
	 * @param theConference A conference
	 */
	public Author(Conference theConference) {
		super(theConference);
	}
	
	/**
	 * View all your submitted manuscript.
	 */
	public void viewAuthorSubManuscript() {
		List<Manuscript> manuList = Main.currentUser.getMyManuscripts();
		int i = 1;
		for (Manuscript manu : manuList){
			System.out.print(i + ". ");
			System.out.println(manu.toString());
			i++;
		}
	}
	
	/**
	 * Update one of your submitted manuscript.
	 * 
	 * @param theManuscript The submitted manuscript. 
	 * @param theConferences List of conference
	 */
	public void updateAuthoredManuscript(Manuscript theManuscript, List<Conference> theConferences) {
		boolean found = false;
		
		for(Conference c: theConferences) {
			if (c.getManuscripts().contains(theManuscript)) {
				c.removeManuscript(theManuscript);
				Main.currentUser.removeManuscript(theManuscript);
				
				c.addManuscript(theManuscript);
				Main.currentUser.addMyManuscript(theManuscript);
				found = true;
				
			}
		}
		
		if (found) {
			System.out.println(theManuscript.getTitle() + " has been updated.");
		}
		else {
			System.out.println(theManuscript.getTitle() + " was not found.");
		}
		
		
	}
	
	/**
	 * Unsubmit one of your manuscript. 
	 * 
	 * @param theManuscript The manuscript you want to remove.
	 * @param theConferences A list of conference.
	 */
	public void unsubmitManuscript(Manuscript theManuscript, List<Conference> theConferences) {
		for(Conference c: theConferences) {
			if (c.getManuscripts().contains(theManuscript)) {
				c.removeManuscript(theManuscript);
				Main.currentUser.removeManuscript(theManuscript);
			}
		}
		
		System.out.println(theManuscript.getTitle() + " has been removed.");
	}
}
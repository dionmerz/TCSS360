package model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * One of the subclass of Roles called Author.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0 5/5/2016
 */
public class Author extends Roles implements Serializable {
	
	/**
	 * Serial identification number
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
	public void viewAuthorSubManuscript(User currentUser) {
		List<Manuscript> manuList = currentUser.getMyManuscripts();
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
	public boolean updateAuthoredManuscript(User currentUser, Manuscript theManuscript, List<Conference> theConferences) {
		
		boolean found = false;
		
		for(Conference c: theConferences) {
			if (c.getManuscripts().contains(theManuscript)) {
				c.removeManuscript(theManuscript);
				currentUser.removeManuscript(theManuscript);
				
				c.addManuscript(theManuscript);
				currentUser.addMyManuscript(theManuscript);
				found = true;	
				
				try {
					Path localFile = Paths.get(theManuscript.getPath());
					File uploadedFile = new File(Paths.get(".").toAbsolutePath().normalize().toString() + "/uploaded/" + c.getName() + "_" + localFile.getFileName());
					Files.copy(localFile, uploadedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		return found;
		
		
	}
	
	/**
	 * Unsubmit one of your manuscript. 
	 * 
	 * @param theManuscript The manuscript you want to remove.
	 * @param theConferences A list of conference.
	 */
	public boolean unsubmitManuscript(User currentUser, Manuscript theManuscript, List<Conference> theConferences) {
		boolean found = false;
		for(Conference c: theConferences) {
			if (c.getManuscripts().contains(theManuscript)) {
				c.removeManuscript(theManuscript);
				currentUser.removeManuscript(theManuscript);
				found = true;
				
				try {
					System.out.println(theManuscript.getPath());
					Files.delete(Paths.get(theManuscript.getPath()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}
		return found;
		
	}
}
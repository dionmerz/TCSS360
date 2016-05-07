package TCSS360;

import java.io.Serializable;

import TCSS360.Manuscript.Status;

public class ProgramChair extends Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4555961000972804977L;

	public ProgramChair(Conference theConference) {
		super(theConference);
		// TODO Auto-generated constructor stub
	}
	
	
	public void viewAllManuscripts() {	
		int count = 1;
		for (Manuscript m : Main.currentConference.getManuscripts()) {
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
	}
	
	public void rejectManuscript(Manuscript theManuscript){
		theManuscript.setStatus(Status.REJECTED);
		System.out.println(theManuscript.getTitle() + " by " + theManuscript.getAuthor() + " Rejected.");
		
	}
	
	public void acceptManuscript(Manuscript theManuscript){
		theManuscript.setStatus(Status.ACCEPTED);
		System.out.println(theManuscript.getTitle() + " by " + theManuscript.getAuthor() + " Accepted.");
		
	}
	
	
	public void viewAssignedSubProgManuscripts() {	
		
		
		for(User u : Main.currentConference.getSubProChairList()) {
			if(!u.getSubProgManuscript().isEmpty()) {
				System.out.println(u.getMyName() + ":");
				for (Manuscript m: u.getSubProgManuscript()) {
					System.out.println("\t" + m.getTitle());
				}
			}
		}
	}
	
	public void assignSubProgManuscript(User theUser, Manuscript theManuscript) {
		
		if(!theUser.getMyName().equals(theManuscript.getAuthor())) {	// If the name does not == author
			
			if(theUser.getSubProgManuscript().size() <= 3) {			// less than 4 papers
			
				theUser.addSubProgManuscript(theManuscript);

				System.out.println(theManuscript.getTitle() + " assigned to " + theUser.getMyName());
			
			} else {
				System.out.println("Failed to assign manuscript to " + theUser.getMyName() + " because of manuscript limit");
			}
		} else {
			System.out.println("Cannot assign a manuscript to the author");
		}
		
	}

}

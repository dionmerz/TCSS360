package TCSS360;

import java.io.Serializable;

import TCSS360.Manuscript.Status;

/**
 * This class represents a Program Chair role. 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0 5/5/2016
 */
public class ProgramChair extends Roles implements Serializable {

	/**
	 * Serial identification number
	 */
	private static final long serialVersionUID = 4555961000972804977L;
	
	private static final int MAX_PAPERS = 4;

	/**
	 * Program Chair Class Constructor
	 * @param theConference
	 */
	public ProgramChair(Conference theConference) {
		super(theConference);
	
	}
	
	/**
	 * Prints a list of manuscripts in program chair conference to the console.
	 */
	public void viewAllManuscripts(Conference currentConference) {	
		int count = 1;
		for (Manuscript m : currentConference.getManuscripts()) {
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
	}
	
	/**
	 * Changes the state of a manuscript to rejected.
	 * @param theManuscript a manuscript
	 */
	public void rejectManuscript(Manuscript theManuscript){
		theManuscript.setStatus(Status.REJECTED);
		System.out.println(theManuscript.getTitle() + " by " + theManuscript.getAuthor() + " Rejected.");
	}
	
	/**
	 * Changes the state of a manuscript to accepted.
	 * @param theManuscript a manuscript
	 */
	public void acceptManuscript(Manuscript theManuscript){
		theManuscript.setStatus(Status.ACCEPTED);
		System.out.println(theManuscript.getTitle() + " by " + theManuscript.getAuthor() + " Accepted.");	
	}
	
	/**
	 * Prints a list of Subprogram chairs and assigned manuscripts.
	 */
	public void viewAssignedSubProgManuscripts(Conference theConference) {	
		System.out.println("\nSubprogam Chair and Assigned Manuscripts List: ");
		if (!theConference.getSubProChairList().isEmpty()) {
			for(User u : theConference.getSubProChairList()) {
				if(!u.getSubProgManuscript().isEmpty()) {
					System.out.println(u.getMyName() + ":");
					for (Manuscript m: u.getSubProgManuscript()) {
						System.out.println("\t" + m.getTitle());
					}
				} 
				else 
				{
					System.out.println(u.getMyName() + " no manuscripts assigned yet.");
				}
			}
		}
		else {
			System.out.println("No Subprogram Chairs in this conference");
		}
		System.out.println();
	}
	
	/**
	 * Assigns a Subprogram Chair a Manuscript to review. 
	 * @param theUser a Subprogram Chair
	 * @param theManuscript a Manuscript
	 */
	public void assignSubProgManuscript(User theUser, Manuscript theManuscript) {
		
		if(!theUser.getMyName().equals(theManuscript.getAuthor())) {	// If the name does not == author
			
			if(theUser.getSubProgManuscript().size() < MAX_PAPERS) {			// less than 4 papers
			
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

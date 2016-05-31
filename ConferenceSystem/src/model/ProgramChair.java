package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Manuscript.Status;

/**
 * This class represents a Program Chair role. 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @author Bernabe Guzman maintained
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
	 * Changes the state of a manuscript to rejected.
	 * @param theManuscript a manuscript
	 */
	public void rejectManuscript(Manuscript theManuscript){
		theManuscript.setStatus(Status.REJECTED);
	}
	
	/**
	 * Changes the state of a manuscript to accepted.
	 * @param theManuscript a manuscript
	 */
	public void acceptManuscript(Manuscript theManuscript){
		theManuscript.setStatus(Status.ACCEPTED);	
	}
	
	/**
	 * Returns a list of manuscripts with recommendations. 
	 * @param theCurrentConference
	 * @return a list of manuscripts with recommendations
	 */
	public List<Manuscript> getListOfManuscriptsWithRecommendations(Conference theCurrentConference) {
		List<Manuscript> reccommendedList = new ArrayList<Manuscript>();
		for (Manuscript m : theCurrentConference.getManuscripts()) {
			if (m.getStatus() == Status.RECOMMENDED) {
				reccommendedList.add(m);
			}
		}
		return reccommendedList;
	}
	
	/**
	 * Assigns a Subprogram Chair a Manuscript to review. 
	 * @param theUser a Subprogram Chair
	 * @param theManuscript a Manuscript
	 */
	public ArrayList<Boolean> assignSubProgManuscript(User theUser, Conference theConference, Manuscript theManuscript) {
		
		ArrayList<Boolean> assignSubProgramChairManuscript = new ArrayList<Boolean>();
		Boolean limit = false;
		Boolean author = false;
		Boolean assigned = false;
		
		for(Manuscript manuscript: theConference.getManuscripts()) { // check if manuscript already assigned to a subprogram chair
			if(manuscript.equals(theManuscript)) {
				assigned = true;
			}
		}
		if(!theUser.getMyName().equals(theManuscript.getAuthor())) {	// If the name does not == author
			author = true;
			if(theUser.getSubProgManuscript().size() < MAX_PAPERS) {			// less than 4 papers
				if(!assigned) {
					theUser.addSubProgManuscript(theManuscript);
				}
				limit = true;
			}
		}
		
		assignSubProgramChairManuscript.add(limit);
		assignSubProgramChairManuscript.add(author);
		assignSubProgramChairManuscript.add(assigned);
		return assignSubProgramChairManuscript;
	}
	
	/**
	 * Assigns the Subprogram Chair role to designated registered User.
	 * 
	 * @param theUser the User to be assigned Role of Subprogram Chair
	 */
	public void designateSubProgramChair(User theUser) {
		theUser.addMyRole(new SubprogramChair(this.getConference()));
	}

}

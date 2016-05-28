package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.ProgramChair;
import model.RecommendationForm;
import model.User;
import model.Manuscript.Status;

public class ProgramChairMenu implements Serializable{
	
	private static final long serialVersionUID = 3967503335206776162L;
	private transient Scanner myUserConsoleInput;
	private boolean hasExitedProgramChairMenu;

	/**
	 * @param theUserInput The user input from the console.
	 */
	public ProgramChairMenu(Scanner theUserInput) {
		myUserConsoleInput = theUserInput;
		hasExitedProgramChairMenu = false;
	}
	
	/**
	 * Program Chair UI menu. Runs a logical finite state machine with Program Chair options. 
	 * 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public boolean initialProgramChairMenu(List<User> theUserList, List<Conference> theConferenceList, User theUser, Conference theConference) {
		printProgramChairMenuHeader(theUser, theConference);
		hasExitedProgramChairMenu = false;
		System.out.println("Select an option: ");
		System.out.println("1. View all manuscripts");
		System.out.println("2. Reject/Accept Manuscript");
		System.out.println("3. View all assigned Subprogram Chair and manuscripts");
		System.out.println("4. Assign a manuscript to a Subprogram Chair");
		System.out.println("5. Back");
		System.out.println("6. Exit");
		
		if (myUserConsoleInput == null) {
			myUserConsoleInput = new Scanner(System.in);
		}

		promptSymbol();
		int programChairMenuOption = myUserConsoleInput.nextInt();
		ProgramChair currentProgramChair = theUser.findProgramChairRole();

		switch (programChairMenuOption) {
		case 1:
			printProgramChairMenuHeader(theUser, theConference);
			printNumberedListOfProgramChairConferenceManuscripts(theConference);
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		case 2:
			printProgramChairMenuHeader(theUser, theConference);
			acceptOrRejectManuscript(theUserList, theConferenceList, currentProgramChair, theUser, theConference);
			break;
		case 3:
			printProgramChairMenuHeader(theUser, theConference);
			viewAssignedSubProgManuscripts(theConference);
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		case 4:
			// print list of SPCs, pick manuscript
			printProgramChairMenuHeader(theUser, theConference);
			viewAllAssignedSubprogramChairAndManuscript(theUserList, theConferenceList, currentProgramChair, theUser, theConference);
			break;
		case 5:
			// Back to previous menu
			break;
		case 6:
			exit();
			break;
		default:
			System.out.println("Invalid Selection returning to last menu");
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		}
		return hasExitedProgramChairMenu;
	}

	/**
	 * Prints a list of Subprogram chairs and assigned manuscripts.
	 * @param theConferenceList
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
	 * Program Chair accept or reject a manuscript user interface menu.
	 * @param count
	 * @param theUserList
	 * @param theConferenceList
	 * @param tempProgramChair
	 */
	public void acceptOrRejectManuscript( List<User> theUserList, List<Conference> theConferenceList,
	    ProgramChair tempProgramChair, User theUser, Conference theConference) {
		System.out.println("Choose a Manuscript to accept/reject:");
		boolean hasManuscripts = printNumberedListOfManuscriptsAndRecommendations(theConference);
		if (!hasManuscripts) {
			System.out.println("No Manuscripts");
			System.out.println("");
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
		}
		promptSymbol();
		int manuscriptIndex = myUserConsoleInput.nextInt();
		List<Manuscript> recomendedList = theUser.findProgramChairRole().getListOfManuscriptsWithRecommendations(theConference);
		Manuscript selectedManuscript = recomendedList.get(manuscriptIndex - 1);

		System.out.println("1. Accept");
		System.out.println("2. Reject");
		System.out.println("3. Back");
		System.out.println("4. Exit");
		promptSymbol();
		int acceptOrRejectMenuOption = myUserConsoleInput.nextInt();

		switch (acceptOrRejectMenuOption) {
		case 1:
			tempProgramChair.acceptManuscript(selectedManuscript);
			System.out.println(selectedManuscript.getTitle() + " by " + selectedManuscript.getAuthor() + " Accepted.");
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		case 2:
			tempProgramChair.rejectManuscript(selectedManuscript);
			System.out.println(selectedManuscript.getTitle() + " by " + selectedManuscript.getAuthor() + " Rejected.");
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		case 3:
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		case 4:
			exit();
			break;
		}
	}

	/**
	 * View all Subprogram Chairs and assigned manuscripts.
	 * @param count
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 * @param theProgramChair Program Chair
	 */
	public void viewAllAssignedSubprogramChairAndManuscript(List<User> theUserList, List<Conference> theConferenceList, 
			ProgramChair theProgramChair, User theUser, Conference theConference) {
		System.out.println("\nSubProgram Chair List");
		printNumberedListOfSubprogramChairUsers(theConference);
		promptSymbol();
		int subprogamChairIndex = myUserConsoleInput.nextInt();
		User selectedUser = theConference.getSubProChairList().get(subprogamChairIndex - 1);
		printNumberedListOfProgramChairConferenceManuscripts(theConference);
		System.out.println("Select a manuscript to assign to " + selectedUser.getMyName());
		promptSymbol();
		subprogamChairIndex = myUserConsoleInput.nextInt();
		Manuscript selectedManuscript = theConference.getManuscripts().get(subprogamChairIndex - 1);
		
		ArrayList<Boolean> subprogramChairAllowedManuscript = new ArrayList<Boolean>();
		subprogramChairAllowedManuscript = theProgramChair.assignSubProgManuscript(selectedUser, selectedManuscript);
		if (!subprogramChairAllowedManuscript.get(1)) {
			System.out.println("Cannot assign a manuscript to the author");
		} else if (!subprogramChairAllowedManuscript.get(0)) {
			System.out.println("Failed to assign manuscript to " + selectedUser.getMyName() + " because of manuscript limit");
		} else {
			System.out.println(selectedManuscript.getTitle() + " assigned to " + selectedUser.getMyName());
			theUserList.remove(selectedUser);
			theUserList.add(selectedUser);
		}
		initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
	}
	
	/**
	 * Prints a list of manuscripts with recommendations.
	 * @param theCurrentConference
	 */
	public boolean printNumberedListOfManuscriptsAndRecommendations(Conference theCurrentConference) {
		int count = 1;
		boolean hasManuscript = false;
		//ArrayList<Manuscript> reccomendedList = new ArrayList<Manuscript>();
		for (Manuscript m : theCurrentConference.getManuscripts()) {
			if (m.getStatus() == Status.RECOMMENDED) {
				//reccomendedList.add(m);
				System.out.println(count + ". " + m.getTitle());
				System.out.print("\tRecommendations: ");
				for (RecommendationForm rf : m.getRecomFormList()) {
					System.out.print(rf.getScore() + ", ");
				}
				System.out.println();
				count++;
				hasManuscript = true;
			}
		}
		return hasManuscript;
	}

	/**
	 * Prints a list of Subprogram Chair users in the conference that the Program Chair is assigned to. 
	 * @param theCurrentConference
	 */
	public void printNumberedListOfSubprogramChairUsers(Conference theCurrentConference) {
		int count = 1;
		for (User sc : theCurrentConference.getSubProChairList()) {
			System.out.println(count + ". " + sc.getMyName());
			count++;
		}
	}
	
	/**
	 * Prints a list of manuscripts in the conference that the Program Chair is assigned to. 
	 * @param theCurrentConference
	 */
	public void printNumberedListOfProgramChairConferenceManuscripts(Conference theCurrentConference) {
		int count = 1; 
		for (Manuscript m : theCurrentConference.getManuscripts()) { // view assigned manuscripts
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
	}
	
	/**
	 * User input indicator
	 */
	public void promptSymbol() {
		System.out.print(">> ");
	}
	
	/**
	 * Prints the Program Chairs Menu header containing program title, name of current user, role of current user,
	 * and current conference.
	 * @param theCurrentUser
	 * @param theCurrentConference
	 */
	private void printProgramChairMenuHeader(User theCurrentUser, Conference theCurrentConference) {
		System.out.println();
		System.out.println("---Scientific Manuscripts Are Reviewed in Terminal---");
		System.out.println("User: " + theCurrentUser.getMyName());
		System.out.println("Current Role: Program Chair");
		System.out.println("Conference: " + theCurrentConference.getName());
		System.out.println("_______________________________________________________");
		System.out.println();
	}
	
	/**
	 * UI exit menu
	 */
	public void exit() {
		hasExitedProgramChairMenu = true;			
	}
}

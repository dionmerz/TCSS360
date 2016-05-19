package TCSS360;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import TCSS360.Manuscript.Status;

public class ProgramChairMenu {
	
	Scanner myUserInput;
	TerminalUserInterface myTerminalUI;

	public ProgramChairMenu(Scanner theUserInput, TerminalUserInterface theTerminalUI) {
		myUserInput = theUserInput;
		myTerminalUI = theTerminalUI;
	}
	
	
	/**
	 * Program Chair UI menu.
	 * 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public void initialProgramChairMenu(List<User> theUserList, List<Conference> theConferenceList, User theUser, Conference theConference) {
		header(theUser, theConference);
		System.out.println("Select an option: ");
		System.out.println("1. View all manuscripts");
		System.out.println("2. Reject/Accept Manuscript");
		System.out.println("3. View all assigned Subprogram Chair and manuscripts");
		System.out.println("4. Assign a manuscript to a Subprogram Chair");
		System.out.println("5. Back");
		System.out.println("6. Exit");

		prompt();
		int input = myUserInput.nextInt();
		int count = 1;
		ProgramChair tempProgramChair = theUser.findProgramChairRole();

		switch (input) {
		case 1:
			header(theUser, theConference);
			count = 1;
			for (Manuscript m : theConference.getManuscripts()) { // view assigned manuscripts
				System.out.println(count + ". " + m.getTitle());
				count++;
			}
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		case 2:
			header(theUser, theConference);
			acceptOrRejectManuscript(count, theUserList, theConferenceList, tempProgramChair, theUser, theConference);
			break;
		case 3:
			header(theUser, theConference);
			viewAssignedSubProgManuscripts(theConference);
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		case 4:
			// print list of SPCs, pick manuscript
			header(theUser, theConference);
			viewAllAssignedSubprogramChairAndManuscript(count, theUserList, theConferenceList, tempProgramChair, theUser, theConference);
			break;
		case 5:
			myTerminalUI.selectRoleMenu(theUserList, theConferenceList);
			break;
		case 6:
			exit();
			break;
		default:
			System.out.println("Invalid Selection returning to last menu");
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		}
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
	 * Program Chair accept or reject a manuscript.
	 * 
	 * @param count
	 * @param theUserList
	 * @param theConferenceList
	 * @param tempProgramChair
	 */
	public void acceptOrRejectManuscript(int count, List<User> theUserList, List<Conference> theConferenceList,
			ProgramChair tempProgramChair, User theUser, Conference theConference) {
		System.out.println("Choose a Manuscript to accept/reject:");
		ArrayList<Manuscript> reccomendedList = new ArrayList<Manuscript>();
		int total = 0;
		for (Manuscript m : theConference.getManuscripts()) {

			if (m.getStatus() == Status.RECOMMENDED) {
				reccomendedList.add(m);
				System.out.println(count + ". " + m.getTitle());
				System.out.print("\tRecommendations: ");
				for (RecommendationForm rf : m.getRecomFormList()) {
					System.out.print(rf.getScore() + ", ");
				}
				System.out.println();
				count++;
				total++;
			}
		}

		if (total == 0) {
			System.out.println("No Manuscripts");
			System.out.println("");
			initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);

		}
		prompt();
		int input = myUserInput.nextInt();
		Manuscript selectedManuscript = reccomendedList.get(input - 1);

		System.out.println("1. Accept");
		System.out.println("2. Reject");
		System.out.println("3. Back");
		System.out.println("4. Exit");
		prompt();
		input = myUserInput.nextInt();

		switch (input) {
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
	 * 
	 * @param count
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 * @param tempProgramChair Program Chair
	 */
	public void viewAllAssignedSubprogramChairAndManuscript(int count, List<User> theUserList,
			List<Conference> theConferenceList, ProgramChair tempProgramChair, User theUser, Conference theConference) {
		System.out.println("\nSubProgram Chair List");
		count = 1;
		for (User sc : theConference.getSubProChairList()) {
			System.out.println(count + ". " + sc.getMyName());
			count++;
		}

		prompt();
		int input = myUserInput.nextInt();
		User selected = theConference.getSubProChairList().get(input - 1);
		count = 1;
		for (Manuscript m : theConference.getManuscripts()) { // view list
																	// of
																	// manuscripts
																	// assigned
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
		System.out.println("Select a manuscript to assign to " + selected.getMyName());
		prompt();
		input = myUserInput.nextInt();
		Manuscript selectedManuscript = theConference.getManuscripts().get(input - 1);
		ArrayList<Boolean> temp = new ArrayList<Boolean>();

		temp = (ArrayList<Boolean>) tempProgramChair.assignSubProgManuscript(selected, selectedManuscript);

		if (!temp.get(1)) {
			System.out.println("Cannot assign a manuscript to the author");
		} else if (!temp.get(0)) {
			System.out
					.println("Failed to assign manuscript to " + selected.getMyName() + " because of manuscript limit");
		} else {
			System.out.println(selectedManuscript.getTitle() + " assigned to " + selected.getMyName());
			theUserList.remove(selected);
			theUserList.add(selected);
		}
		initialProgramChairMenu(theUserList, theConferenceList, theUser, theConference);
	}

	
	/**
	 * User input indicator
	 */
	public void prompt() {
		System.out.print(">> ");
	}
	
	private void header(User theCurrentUser, Conference theCurrentConference) {
		System.out.println();
		System.out.println("---Scientific Manuscripts Are Reviewed in Terminal---");
		System.out.println("User: " + theCurrentUser.getMyName());
		System.out.println("Conference: " + theCurrentConference.getName());
		System.out.println("_______________________________________________________");
		System.out.println();
	}
	
	/**
	 * UI exit menu
	 */
	public void exit() {
		System.out.println("You selected exit.");

//		try {
//			
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		
	}
}

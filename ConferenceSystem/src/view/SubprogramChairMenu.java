package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.SubprogramChair;
import model.User;

/**
 * This class is a sub menu of the user interface which carries 
 * Subprogram Chair menu options. 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @author Bernabe Guzman maintained
 * @version 1.0 5/27/2016
 */
public class SubprogramChairMenu implements Serializable  {

	private static final long serialVersionUID = -3353649043191045626L;
	private transient Scanner myUserConsoleInput;
	private boolean hasExitedSubprogramChairMenu;
	
	/**
	 * @param theUserInput The user input from the console.
	 */
	public SubprogramChairMenu(Scanner theUserInput) {
		myUserConsoleInput = theUserInput;
		hasExitedSubprogramChairMenu = false;
	}
	
	/**
     * Subprogram Chair UI menu. Runs a logical finite state machine with Subprogram Chair options. 
	 * @param theFinishedFlag login flag
	 * @param theExitFlag exit flag
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 * @return true if exited, false otherwise.
	 */
	public boolean initialSubprogramChairMenu(List<User> theUserList, List<Conference> theConferenceList, User theCurrentUser,
			Conference theCurrentConference) {	
		if (myUserConsoleInput == null) {
			myUserConsoleInput = new Scanner(System.in);
		}
		hasExitedSubprogramChairMenu = false;
		printSubprogramChairMenuHeader(theCurrentUser, theCurrentConference);
		System.out.println("Select an option: ");
		System.out.println("1. Assign a manuscript to a reviewer");
		System.out.println("2. Submit a recommendation for a manuscript");
		System.out.println("3. Designate User as Reviewer");
		System.out.println("4. Back");
		System.out.println("5. Exit");

		SubprogramChair tempSubprogramChair = theCurrentUser.findSubprogramChairRole();
		promptSymbol();
		int subprogramChairMenuOption = myUserConsoleInput.nextInt();
		switch (subprogramChairMenuOption) {
		case 1:
			printSubprogramChairMenuHeader(theCurrentUser, theCurrentConference);
			assignManuscriptToReviewer(tempSubprogramChair, theUserList, theCurrentUser, theCurrentConference);
			initialSubprogramChairMenu(theUserList, theConferenceList, theCurrentUser, theCurrentConference);
			break;
		case 2:
			printSubprogramChairMenuHeader(theCurrentUser, theCurrentConference);
			submitRecommendationForManuscript(tempSubprogramChair, theCurrentUser, theCurrentConference);
			initialSubprogramChairMenu(theUserList, theConferenceList, theCurrentUser, theCurrentConference);
			break;
		case 3:
			printAllUserToDesignateAsReviewer(theUserList, theCurrentConference, theCurrentUser);
			initialSubprogramChairMenu(theUserList, theConferenceList, theCurrentUser, theCurrentConference);
			break;
		case 4:
			// Returns to previous menu.
			break;
		case 5:
			exit();
			break;
		}
		return hasExitedSubprogramChairMenu;
	}


	/**
	 * Assigns a manuscript to a Reviewer
	 * @param theSubprogramChairRole The Subprogram chair role.
	 * @param theUserList The list of all users.
	 * @param theCurrentUser the current logged in user.
	 * @param theCurrentConference the current selected conference.
	 */
	public void assignManuscriptToReviewer(SubprogramChair theSubprogramChairRole, List<User> theUserList, 
			User theCurrentUser, Conference theCurrentConference) {
		if (!theCurrentUser.getSubProgManuscript().isEmpty()) {
			System.out.println("Select a manuscript to assign to a reviewer");
			printNumberedListOfSubprogramChairManuscripts(theCurrentUser);
			promptSymbol();
			int subprogramChairManuscriptIndex = myUserConsoleInput.nextInt();
			Manuscript selectedManuscript = theCurrentUser.getSubProgManuscript().get(subprogramChairManuscriptIndex - 1);
			List<User> reviewerList = theCurrentUser.findSubprogramChairRole().getListOfReviewersFromListOfUsers(theUserList);		
			if (reviewerList.isEmpty()) {
				System.out.println("No Reviewers to assign for Conference: " + theCurrentConference.getName());
			} else {
				System.out.println("Select a reviewer to assign the selected manuscript");
				printNumberedListOfReviewers(reviewerList);
				promptSymbol();
				int reviewerIndex = myUserConsoleInput.nextInt();
				User selectedReviewer = reviewerList.get(reviewerIndex - 1);
				List<Boolean> canAssignReviewerManuscript = theSubprogramChairRole.assignReviewerManuscript(selectedReviewer, selectedManuscript);
				if (!canAssignReviewerManuscript.get(0)) {
					System.out.println("Cannot assign a review to the author of the manuscript");
				} else if (!canAssignReviewerManuscript.get(1)) {
					System.out.println("Failed to assign review to " + selectedReviewer.getMyName() + " because of review limit");
				} else {
					System.out.println(selectedManuscript.getTitle() + " assigned to " + selectedReviewer.getMyName());
				}
			}
		} else {
			System.out.println("\nNo manuscripts currently assigned to subprogram chair");
		}
	}


	/**
	 * Submits a recommendation form for a specific manuscript.
	 * @param theSubprogramChairRole The subprogram chair role.
	 * @param theCurrentUser the current logged in user
	 * @param theCurrentConference the current selected conference.
	 */
	public void submitRecommendationForManuscript(SubprogramChair theSubprogramChairRole,
			User theCurrentUser, Conference theCurrentConference) {
		System.out.println("Select a manuscript to assign a recommendation");
		printNumberedListOfSubprogramChairManuscripts(theCurrentUser);
		if (!theCurrentUser.getSubProgManuscript().isEmpty()) {
			promptSymbol();
			int subProgramChairManuscriptIndex = myUserConsoleInput.nextInt();
			Manuscript selectedManuscript = theCurrentUser.getSubProgManuscript().get(subProgramChairManuscriptIndex - 1);
			System.out.println("Enter the path to the recommendation form");
			myUserConsoleInput.nextLine();
			String path = myUserConsoleInput.nextLine();
			System.out.println("Enter a recommendation score");
			int score = myUserConsoleInput.nextInt();
			System.out.println("Enter a title for the recommendation form");
			myUserConsoleInput.nextLine();
			String title = myUserConsoleInput.nextLine();
			theSubprogramChairRole.appendRecomendationToManuscript(theCurrentUser, theCurrentConference, selectedManuscript, score, path, title);
			System.out.println("Reccommendation Form for " + selectedManuscript.getTitle() + " submitted.");
		} else {
			System.out.println("No Manuscripts assigned, returning to last menu.");
		}
	}
	
	/**
	 * Prints a list of reviewers assigned to the Subprogram Chair.
	 * @param theReviewerList the list of all reviewers
	 */
	public void printNumberedListOfReviewers(List<User> theReviewerList) {
		int count = 1;
		for (User u : theReviewerList) {
			System.out.println(count + ". " + u.getMyName());
			count++;
		}
	}
	
	/**
	 * Prints a list of manuscripts assigned to the Subprogram Chair.
	 * @param theCurrentUser the current logged in user.
	 */
	public void printNumberedListOfSubprogramChairManuscripts(User theCurrentUser){
		int count = 1;
		for (Manuscript m : theCurrentUser.getSubProgManuscript()) {
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
	 * Prints a header indicating the Name of the user and current conference.
	 * @param theCurrentUser the current logged in user.
	 * @param theCurrentConference the current selected conference.
	 */
	private void printSubprogramChairMenuHeader(User theCurrentUser, Conference theCurrentConference) {
		System.out.println();
		System.out.println("---Scientific Manuscripts Are Reviewed in Terminal---");
		System.out.println("User: " + theCurrentUser.getMyName());
		System.out.println("Current Role: Subprogram Chair");
		System.out.println("Conference: " + theCurrentConference.getName());
		System.out.println("_______________________________________________________");
		System.out.println();
	}
	
	/**
	 * Menu to print out all users registers in the System,
	 * Designates selected User as a Subprogram Chair.
	 * @param theUserList The list of all users.
	 * @param theConference The currently selected conference.
	 * @param theUser The current logged in User.
	 */
	private void printAllUserToDesignateAsReviewer(List<User> theUserList, Conference theConference, User theUser) {
		Reviewer reviewerRole = new Reviewer(theConference);
		SubprogramChair currentSubprogramChair = theUser.findSubprogramChairRole();
		List<User> targetUsers = new ArrayList<User>();
		for (User user: theUserList) {
			if (!user.hasRole(theConference, reviewerRole, user)) {
				targetUsers.add(user);
			}
		}
		if (!targetUsers.isEmpty()){
			int userCount = 1;
			for (User targetUser: targetUsers) {
				System.out.print(userCount + ". ");
				System.out.println(targetUser.getMyName());
				userCount++;
			}
			promptSymbol();
			int input = myUserConsoleInput.nextInt();
			currentSubprogramChair.designateReviewer(targetUsers.get(input - 1));
			myUserConsoleInput.nextLine();
			System.out.println(targetUsers.get(input - 1).getMyName() + " designated as Reviewer.");
		}
		else{
			System.out.println("No Users to designate as a Reviewer");
			
		}
	}
	
	/**
	 * UI exit menu
	 */
	public void exit() {
		hasExitedSubprogramChairMenu = true;
		
	}
	
}

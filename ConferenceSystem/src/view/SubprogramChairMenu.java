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
	private transient Scanner myUserInput;
	private boolean hasExitedSubprogramChairMenu;
	
	/**
	 * @param theUserInput The user input from the console.
	 */
	public SubprogramChairMenu(Scanner theUserInput) {
		myUserInput = theUserInput;
		hasExitedSubprogramChairMenu = false;
	}
	
	/**
     * Subprogram Chair UI menu. Runs a logical finite state machine with Subprogram Chair options. 
	 * @param theFinishedFlag login flag
	 * @param theExitFlag exit flag
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 */
	public boolean initialSubprogramChairMenu(List<User> theUserList, List<Conference> theConferenceList, User theCurrentUser,
			Conference theCurrentConference) {
		
		if (myUserInput == null) {
			myUserInput = new Scanner(System.in);
		}
		hasExitedSubprogramChairMenu = false;
		printSubprogramChairMenuHeader(theCurrentUser, theCurrentConference);
		System.out.println("Select an option: ");
		System.out.println("1. Assign a manuscript to a reviewer");
		System.out.println("2. Submit a recommendation for a manuscript");
		System.out.println("3. Back");
		System.out.println("4. Exit");

		SubprogramChair tempSubprogramChair = theCurrentUser.findSubprogramChairRole();
		promptSymbol();
		int subprogramChairMenuOption = myUserInput.nextInt();

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
			// Returns to previous menu.
			break;
		case 4:
			exit();
			break;
		}
		return hasExitedSubprogramChairMenu;
	}

	/**
	 * Assigns a manuscript to a reviewer.
	 * 
	 * @param count
	 * @param input user input
	 * @param tempSubprogramChair Subprogram Chair
	 */
	public void assignManuscriptToReviewer(SubprogramChair tempSubprogramChair, List<User> theUserList, 
			User theCurrentUser, Conference theCurrentConference) {
		int count = 1;
		if (!theCurrentUser.getSubProgManuscript().isEmpty()) {
			System.out.println("Select a manuscript to assign to a reviewer");
			printNumberedListOfSubprogramChairManuscripts(theCurrentUser);
			promptSymbol();
			int subprogramChairManuscriptIndex = myUserInput.nextInt();
			Manuscript selectedManuscript = theCurrentUser.getSubProgManuscript().get(subprogramChairManuscriptIndex - 1);
			ArrayList<User> reviewerList = new ArrayList<User>();

			for (User u : theUserList) {
				Reviewer tempRev = u.findReviewerRole();
				if (tempRev != null) {
					reviewerList.add(u);
				}
			}
			if (reviewerList.isEmpty()) {
				System.out.println("No Reviewers to assign for Conference: " + theCurrentConference.getName());
			} else {
				count = 1;
				System.out.println("Select a reviewer to assign the selected manuscript");

				for (User u : reviewerList) {
					System.out.println(count + ". " + u.getMyName());
					count++;
				}

				promptSymbol();
				int userIndex = myUserInput.nextInt();
				User selectedReviewer = reviewerList.get(userIndex - 1);
				List<Boolean> result = tempSubprogramChair.assignReviewerManuscript(selectedReviewer,
						selectedManuscript);

				if (!result.get(0)) {
					System.out.println("Cannot assign a review to the author of the manuscript");
				} else if (!result.get(1)) {
					System.out.println(
							"Failed to assign review to " + selectedReviewer.getMyName() + " because of review limit");
				} else {
					System.out.println(selectedManuscript.getTitle() + " assigned to " + selectedReviewer.getMyName());
				}
			}
		} else {
			System.out.println("\nNo manuscripts currently assigned to subprogram chair");
		}
	}

	/**
	 * Submits a recommendation to a manuscript.
	 * @param count
	 * @param input user choice
	 * @param tempSubprogramChair Subprogram Chair
	 */
	public void submitRecommendationForManuscript(SubprogramChair tempSubprogramChair,
			User theCurrentUser, Conference theCurrentConference) {
		System.out.println("Select a manuscript to assign a recommendation");
		printNumberedListOfSubprogramChairManuscripts(theCurrentUser);

		if (!theCurrentUser.getSubProgManuscript().isEmpty()) {
			promptSymbol();
			int subProgramChairManuscriptIndex = myUserInput.nextInt();
			Manuscript selectedManuscript = theCurrentUser.getSubProgManuscript().get(subProgramChairManuscriptIndex - 1);
			System.out.println("Enter the path to the recommendation form");
			myUserInput.nextLine();
			String path = myUserInput.nextLine();
			System.out.println("Enter a recommendation score");
			int score = myUserInput.nextInt();
			System.out.println("Enter a title for the recommendation form");
			myUserInput.nextLine();
			String title = myUserInput.nextLine();
			tempSubprogramChair.submitRecomendation(theCurrentUser, theCurrentConference, selectedManuscript, score, path, title);
			System.out.println("Reccommendation Form for " + selectedManuscript.getTitle() + " submitted.");
		} else {
			System.out.println("No Manuscripts assigned, returning to last menu.");
		}
	}
	
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
	 * @param theCurrentUser
	 * @param theCurrentConference
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
	 * UI exit menu
	 */
	public void exit() {
		hasExitedSubprogramChairMenu = true;
		
	}
	
}

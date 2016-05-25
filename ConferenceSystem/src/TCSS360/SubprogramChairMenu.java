package TCSS360;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubprogramChairMenu {
	private transient Scanner myUserInput;
	private TerminalUserInterface myTerminalUI;
	
	public SubprogramChairMenu(Scanner theUserInput, TerminalUserInterface theTerminalUI) {
		myUserInput = theUserInput;
		myTerminalUI = theTerminalUI;
	}
	
	/**
	 * Subprogram chair UI menu.
	 * 
	 * @param theFinishedFlag login flag
	 * @param theExitFlag exit flag
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 */
	public void initialSubprogramChairMenu(List<User> theUserList, List<Conference> theConferenceList, User theCurrentUser,
			Conference theCurrentConference) {
		
		if (myUserInput == null) {
			myUserInput = new Scanner(System.in);
		}
		
		header(theCurrentUser, theCurrentConference);
		System.out.println("Select an option: ");
		System.out.println("1. Assign a manuscript to a reviewer");
		System.out.println("2. Submit a recommendation for a manuscript");
		System.out.println("3. Back");
		System.out.println("4. Exit");

		SubprogramChair tempSubprogramChair = theCurrentUser.findSubprogramChairRole();
		int count = 1;
		prompt();
		int input = myUserInput.nextInt();

		switch (input) {

		case 1:
			header(theCurrentUser, theCurrentConference);
			assignManuscriptToReviewer(count, input, tempSubprogramChair, theUserList, theCurrentUser, theCurrentConference);
			initialSubprogramChairMenu(theUserList, theConferenceList, theCurrentUser, theCurrentConference);
			break;
		case 2:
			header(theCurrentUser, theCurrentConference);
			submitRecommendationForManuscript(count, input, tempSubprogramChair, theCurrentUser, theCurrentConference);
			initialSubprogramChairMenu(theUserList, theConferenceList, theCurrentUser, theCurrentConference);
			break;
		case 3:
			myTerminalUI.selectRoleMenu(theUserList, theConferenceList);
			break;
		case 4:
			exit();
			break;
		}
	}

	/**
	 * Assigns a manuscript to a reviewer.
	 * 
	 * @param count
	 * @param input user input
	 * @param tempSubprogramChair Subprogram Chair
	 */
	public void assignManuscriptToReviewer(int count, int input, SubprogramChair tempSubprogramChair, List<User> theUserList, 
			User theCurrentUser, Conference theCurrentConference) {

		if (!theCurrentUser.getSubProgManuscript().isEmpty()) {
			System.out.println("Select a manuscript to assign to a reviewer");
			for (Manuscript m : theCurrentUser.getSubProgManuscript()) {
				System.out.println(count + ". " + m.getTitle());
				count++;
			}
			prompt();
			input = myUserInput.nextInt();
			Manuscript selectedManuscript = theCurrentUser.getSubProgManuscript().get(input - 1);
			ArrayList<User> reviewerList = new ArrayList<User>();

			for (User u : theUserList) {
//				if (hasRole(theCurrentConference, REVIEWER, u)) { ------------------------------------------------------------
//					reviewerList.add(u);
//				}
				Reviewer tempRev = u.findReviewerRole();
				if (tempRev != null) {
					reviewerList.add(u);
				}
			}
			// System.out.println(reviewerList.size());
			if (reviewerList.isEmpty()) {
				System.out.println("No Reviewers to assign for Conference: " + theCurrentConference.getName());
			} else {
				count = 1;
				System.out.println("Select a reviewer to assign the selected manuscript");

				for (User u : reviewerList) {
					System.out.println(count + ". " + u.getMyName());
					count++;
				}

				prompt();
				input = myUserInput.nextInt();
				User selectedReviewer = reviewerList.get(input - 1);
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
	 * 
	 * @param count
	 * @param input user choice
	 * @param tempSubprogramChair Subprogram Chair
	 */
	public void submitRecommendationForManuscript(int count, int input, SubprogramChair tempSubprogramChair,
			User theCurrentUser, Conference theCurrentConference) {
		System.out.println("Select a manuscript to assign a recommendation");
		for (Manuscript m : theCurrentUser.getSubProgManuscript()) {
			System.out.println(count + ". " + m.getTitle());
			count++;
		}

		if (!theCurrentUser.getSubProgManuscript().isEmpty()) {
			prompt();
			input = myUserInput.nextInt();
			Manuscript selectedManuscript = theCurrentUser.getSubProgManuscript().get(input - 1);
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

	/**
	 * User input indicator
	 */
	public void prompt() {
		System.out.print(">> ");
	}
	
	/**
	 * Prints a header indicating the Name of the user and current conference.
	 * @param theCurrentUser
	 * @param theCurrentConference
	 */
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
		
	}
	
}

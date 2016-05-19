package TCSS360;

import java.util.List;
import java.util.Scanner;

public class ReviewerMenu {
	Scanner myUserInput;
	TerminalUserInterface myTerminalUI;
	
	public ReviewerMenu(Scanner theUserInput, TerminalUserInterface theTerminalUI) {
		myUserInput = theUserInput;
		myTerminalUI = theTerminalUI;
	}
	
	public void initialReviewerMenu(List<User> theUserList, List<Conference> theConferenceList, User myCurrentUser,
			Conference myCurrentConference) {
		
		
		
			header(myCurrentUser, myCurrentConference);
			System.out.println("Select an option: ");
			System.out.println("1. View assigned manuscripts to review");
			System.out.println("2. Upload a review form");
			System.out.println("3. Back");
			System.out.println("4. Exit");

			Reviewer tempReview = myCurrentUser.findReviewerRole();

			prompt();
			int input = myUserInput.nextInt();

			switch (input) {
			case 1:
				header(myCurrentUser, myCurrentConference);
				viewReviewerManuscripts(myCurrentConference, myCurrentUser);
				initialReviewerMenu(theUserList, theConferenceList, myCurrentUser, myCurrentConference);
				break;
			case 2:
				header(myCurrentUser, myCurrentConference);
				uploadReviewForm(input, tempReview, myCurrentUser, myCurrentConference);
				initialReviewerMenu(theUserList, theConferenceList, myCurrentUser, myCurrentConference);
				break;
			case 3:
				myTerminalUI.selectRoleMenu(theUserList, theConferenceList);
				break;
			case 4:
				exit();
				break;
			default:
				System.out.println("Invalid selection.");
				initialReviewerMenu(theUserList, theConferenceList, myCurrentUser, myCurrentConference);
				break;
			}
		
			
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
	 * Upload a review form and attach to manuscript.
	 * 
	 * @param theInput user input
	 * @param theReviewer reviewer
	 */
	public void uploadReviewForm(int theInput, Reviewer theReviewer, User theCurrentUser, Conference theCurrentConference) {
		System.out.println("Select a manuscript to upload a review for");
		

		if(viewReviewerManuscripts(theCurrentConference, theCurrentUser)) {
			theInput = myUserInput.nextInt();
			Manuscript selectedManuscript = theCurrentUser.getMyManuscriptsToReview().get(theInput - 1);
			System.out.println("Enter the path to the review form");
			myUserInput.nextLine();
			String path = myUserInput.nextLine();
			System.out.println("Enter the title of the review form");
			String title = myUserInput.nextLine();

			List<Boolean> allowed = theReviewer.uploadReviewForm(theCurrentUser, theCurrentConference, path,
					theCurrentUser.getMyName(), title, selectedManuscript);

			Boolean isAllowed = allowed.get(0);
			Boolean inTime = allowed.get(1);

			if (!isAllowed) {
				System.out.println("Not assigned this Paper to review...\n");
			}

			if (!inTime) {
				System.out.println("Submission failed review deadline has passed.\n");
			}

			if (isAllowed && inTime) {
				System.out.println("Review submitted successfully.\n");
			}
		} 
	}

	public boolean viewReviewerManuscripts(Conference theCurrentConference, User theCurrentUser) {
		int count = 1;
		boolean isNotEmpty = false;
		if (!theCurrentUser.getMyManuscriptsToReview().isEmpty()) {
			isNotEmpty = true;
			for (Manuscript m : theCurrentUser.getMyManuscriptsToReview()) {
				System.out.println(count + ". " + m.getTitle());
			}
		} else {
			System.out.println("No manuscripts to review");
		}
		System.out.println();
		return isNotEmpty;
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

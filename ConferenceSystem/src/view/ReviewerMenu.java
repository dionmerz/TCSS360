package view;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.ReviewForm;
import model.Reviewer;
import model.User;

public class ReviewerMenu implements Serializable  {

	private static final long serialVersionUID = -8912679648268928423L;
	private transient Scanner myUserInput;
	private boolean hasExited;
	
	/**
	 * 
	 * @param theUserInput Scanner for UI input.
	 */
	public ReviewerMenu(Scanner theUserInput) {
		myUserInput = theUserInput;
		hasExited = false;
	}

	/**
	 * The first Reviewer Role Menu, this menu
	 * displays the options of what a Reviewer can do.
	 * @param theUserList The list of all Users
	 * @param theConferenceList The list of all Conferences
	 * @param myCurrentUser The currently logged in User.
	 * @param myCurrentConference The current Conference that is selected.
	 * @return true if exited, false otherwise.
	 */
	public boolean initialReviewerMenu(List<User> theUserList, List<Conference> theConferenceList, 
			User myCurrentUser, Conference myCurrentConference) {

		if (myUserInput == null) {
			myUserInput = new Scanner(System.in);
		}
		hasExited = false;
		header(myCurrentUser, myCurrentConference);
		System.out.println("Select an option: ");
		System.out.println("1. View assigned manuscripts and submitted reviews");
		System.out.println("2. Upload a review form");
		System.out.println("3. Back");
		System.out.println("4. Exit");

		Reviewer userReviewerRole = myCurrentUser.findReviewerRole();

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
			uploadReviewForm(userReviewerRole, myCurrentUser, myCurrentConference);
			initialReviewerMenu(theUserList, theConferenceList, myCurrentUser, myCurrentConference);
			break;
		case 3:
			//Backs to previous menu
			break;
		case 4:
			exit();
			break;
		default:
			System.out.println("Invalid selection.");
			initialReviewerMenu(theUserList, theConferenceList, myCurrentUser, myCurrentConference);
			break;
		}
		return hasExited;
	}

	/**
	 * Upload a Review form for a specific Manuscript.
	 * 
	 * @param theReviewer The reviewer role uploading the form.
	 * @param theCurrentUser The currently logged in User.
	 * @param theCurrentConference The current conference selected.	
	 */
	public void uploadReviewForm(Reviewer theReviewer, User theCurrentUser, Conference theCurrentConference) {
		System.out.println("Select a manuscript to upload a review for");
		
		int selectedInput;
		if(viewReviewerManuscripts(theCurrentConference, theCurrentUser)) {
			prompt();
			selectedInput = myUserInput.nextInt();
			Manuscript selectedManuscript = theCurrentUser.getMyManuscriptsToReview().get(selectedInput - 1);
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

	/**
	 * Displays a list of all the manuscripts which 
	 * are assigned to the logged in User for the User to Review.
	 * 
	 * @param theCurrentConference The current conference that is selected.
	 * @param theCurrentUser The current user who is logged in.
	 * @return true if the current User is assign manuscripts to review.
	 */
	public boolean viewReviewerManuscripts(Conference theCurrentConference, User theCurrentUser) {
		int count = 1;
		boolean isNotEmpty = false;
		if (!theCurrentUser.getMyManuscriptsToReview().isEmpty()) {
			isNotEmpty = true;
			for (Manuscript m : theCurrentUser.getMyManuscriptsToReview()) {
				System.out.println(count + ". " + m.getTitle());
				count++;
				for(ReviewForm r: theCurrentUser.getMyReviews()) {
					if(m.getReviewList().contains(r)) {
						System.out.println("\t" + r.getTitle());
					}
				}
			}
		} else {
			System.out.println("No manuscripts to review");
		}
		System.out.println();
		return isNotEmpty;
	}
	
	/**
	 * User input indicator
	 */
	public void prompt() {
		System.out.print(">> ");
	}
	
	/**
	 * Prints a header indicating the Name of the user and current conference.
	 * @param theCurrentUser The current logged in User.
	 * @param theCurrentConference the current selected Conference.
	 */
	private void header(User theCurrentUser, Conference theCurrentConference) {
		System.out.println();
		System.out.println("---Scientific Manuscripts Are Reviewed in Terminal---");
		System.out.println("User: " + theCurrentUser.getMyName());
		System.out.println("Current Role: Reviewer");
		System.out.println("Conference: " + theCurrentConference.getName());
		System.out.println("_______________________________________________________");
		System.out.println();
	}
	
	/**
	 * UI exit menu
	 */
	public void exit() {
		hasExited = true;
		
	}

}

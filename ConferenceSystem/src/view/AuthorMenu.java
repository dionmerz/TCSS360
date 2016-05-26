package view;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.User;

public class AuthorMenu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5035785019253162377L;
	
	private transient Scanner myUserInput;
	private boolean hasExited;

	public AuthorMenu(Scanner theUserInput) {
		myUserInput = theUserInput;
		hasExited = false;
	}
	
	/**
	 * Author UI menu.
	 * 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public boolean initialAuthorMenu(List<User> theUserList, List<Conference> theConferenceList, User theUser, Conference theConference) {
		if (myUserInput == null) {
			myUserInput = new Scanner(System.in);
		}
		hasExited = false;
		
		header(theUser, theConference);
		
		System.out.println("Select an option: ");
		if (theUser.findAuthorRole() != null) {
			System.out.println("D. Update Manuscript");
			System.out.println("N. Unsubmit Manuscript");
		}
			System.out.println("S. Submit manuscript");
			System.out.println("B. Back");
			System.out.println("E. Exit");

		prompt();
		String input = myUserInput.next();
		int count = 1;
		Author tempAuthor = theUser.findAuthorRole();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());

		switch (input.toUpperCase()) {
		case "D":
			// Update Manuscript
			updateManuscriptAuthor(count, input, date, theUserList, theConferenceList, tempAuthor, theUser, theConference);
			break;
		case "N":
			// Unsubmit Manuscript
			unsubmitManuscriptAuthor(count, input, theUserList, theConferenceList, tempAuthor, theUser, theConference);
			break;
		case "S":
			// Submit Manuscript
			submitManuscript(theUserList, theConferenceList, theUser, theConference);
			break;
		case "B":
			// Back to previous menu.
			break;
		case "E":
			exit();
			break;
		default:
			System.out.println("Invalid Selection returning to last menu");
			initialAuthorMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		}
		return hasExited;
		
	}
	
	public void submitManuscript(List<User> theUserList, List<Conference> theConferenceList, User theUser, Conference theConference) {
		System.out.println("Enter the path to the manuscript: ");
		prompt();
		myUserInput.nextLine(); // System.in newline buffer
		String path = myUserInput.nextLine();
		System.out.println("Enter the title of the manuscript: ");
		prompt();
		String title = myUserInput.nextLine();
		if (theUser.submitManuscript(path, title, theUser, theConference)) {
			System.out.println();
			System.out.println(title + " submitted to Conference " + theConference.getName());
		} else {
			System.out.println();
			System.out.println("The deadline for manuscript submission has passed.\n");
		}
		initialAuthorMenu(theUserList, theConferenceList, theUser, theConference);
	}
	
	/**
	 * Update author's manuscript.
	 * 
	 * @param count
	 * @param input users input
	 * @param date data
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 * @param tempAuthor author
	 */
	public void updateManuscriptAuthor(int count, String input, String date, List<User> theUserList,
			List<Conference> theConferenceList, Author tempAuthor, User theCurrentUser, Conference theConference) {
		System.out.println("Select a manuscript to update or command: ");
		for (Manuscript m : theCurrentUser.getMyManuscripts()) {
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
		System.out.println("B. Back");
		input = myUserInput.next();
		if (!input.equals("B")) {
			Manuscript tempManuscript = theCurrentUser.getMyManuscripts().get(Integer.parseInt(input) - 1);
			System.out.println("Enter the path of the updated manuscript");
			String path = myUserInput.next();

			Manuscript updatedManuscript = new Manuscript(path, theCurrentUser.getMyName(), date,
					tempManuscript.getTitle());
			if (tempAuthor.updateAuthoredManuscript(theCurrentUser, updatedManuscript, theConferenceList)) {
				System.out.println(updatedManuscript.getTitle() + " has been updated.\n");
			} else {
				System.out.println(updatedManuscript.getTitle() + " was not found.\n");
			}

			initialAuthorMenu(theUserList, theConferenceList, theCurrentUser, theConference);
		} else {
			initialAuthorMenu(theUserList, theConferenceList, theCurrentUser, theConference);
		}
	}

	/**
	 * Unsubmit one of author's manuscript.
	 * 
	 * @param count
	 * @param input user input
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 * @param tempAuthor author
	 */
	public void unsubmitManuscriptAuthor(int count, String input, List<User> theUserList,
			List<Conference> theConferenceList, Author tempAuthor, User theCurrentUser, Conference theConference) {
		System.out.println("Select a manuscript to unsubmit or command: ");
		for (Manuscript m : theCurrentUser.getMyManuscripts()) {
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
		System.out.println("B. Back");
		input = myUserInput.next();
		if (!input.equals("B")) {

			Manuscript removedManuscript = theCurrentUser.getMyManuscripts().get(Integer.parseInt(input) - 1);

			if (tempAuthor.unsubmitManuscript(theCurrentUser, removedManuscript, theConferenceList)) {
				System.out.println(removedManuscript.getTitle() + " successfully removed.\n");

			} else {
				System.out.println("Manuscript not found.\n");
			}

			initialAuthorMenu(theUserList, theConferenceList, theCurrentUser, theConference);
		} else {
			initialAuthorMenu(theUserList, theConferenceList, theCurrentUser, theConference);
		}
	}
	
	/**
	 * User input indicator
	 */
	public void prompt() {
		System.out.print(">> ");
	}
	
	private static void header(User theCurrentUser, Conference theCurrentConference) {
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
		hasExited = true;
	}
	
	
}



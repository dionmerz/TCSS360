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

/**
 * User Interface Menu that displays all options
 * for an Author.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 *
 */
public class AuthorMenu implements Serializable {
	
	private static final long serialVersionUID = -5035785019253162377L;
	
	private transient Scanner myUserInput;
	private boolean hasExited;

	public AuthorMenu(Scanner theUserInput) {
		myUserInput = theUserInput;
		hasExited = false;
	}
	
	/**
	 * The first Author Role Menu, this menu
	 * displays the options of what a Author can do.
	 * 
	 * @param theUserList The list of all the Users
	 * @param theConferenceList The list of all the Conferences
	 * @param theUser The currently logged in User.
	 * @param theConference The current selected Conference.
	 * @return True if exit is selected, false otherwise.
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
			System.out.println("V. View Authored Manuscripts");
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
			updateManuscriptAuthor(date, theUserList, theConferenceList, tempAuthor, theUser, theConference);
			break;
		case "N":
			// Unsubmit Manuscript
			unsubmitManuscriptAuthor(theUserList, theConferenceList, tempAuthor, theUser, theConference);
			break;
		case "S":
			// Submit Manuscript
			submitManuscriptUI(theUserList, theConferenceList, theUser, theConference);
			break;
		case "B":
			// Back to previous menu.
			hasExited = false;
			break;
		case "E":
			exit();
			break;
		case "V": 
			if(!theUser.getMyManuscripts().isEmpty()) {
				count = 1;
				System.out.println("Manuscripts authored by " + theUser.getMyName());
				for(Manuscript m: theUser.getMyManuscripts()) {
					System.out.println(count + ". " + m.getTitle());
					count++;
				}
			}
			else {
				System.out.println("No submitted manuscripts");
			}
			break;
		default:
			System.out.println("Invalid Selection returning to last menu");
			initialAuthorMenu(theUserList, theConferenceList, theUser, theConference);
			break;
		}
		return hasExited;
		
	}
	
	/**
	 * Displays the menu to submit a manuscript to the selected conference.
	 * 
	 * @param theUserList The list of all the Users
	 * @param theConferenceList The list of all the Conferences
	 * @param theUser The currently logged in User.
	 * @param theConference The current selected Conference.
	 */
	public void submitManuscriptUI(List<User> theUserList, List<Conference> theConferenceList, User theUser, Conference theConference) {
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
	 * 
	/**
	 * Update author's manuscript.
	 * 
	 * @param date the date manuscript updated
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 * @param theAuthorRole the role of the author
	 * @param theCurrentUser The current logged in user.
	 * @param theConference The current selected conference.
	 */
	public void updateManuscriptAuthor(String date, List<User> theUserList,
			List<Conference> theConferenceList, Author theAuthorRole, User theCurrentUser, Conference theConference) {
		
		int count = 1;
		
		System.out.println("Select a manuscript to update or command: ");
		for (Manuscript m : theCurrentUser.getMyManuscripts()) {
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
		System.out.println("B. Back");
		String input = myUserInput.next();
		if (!input.equals("B")) {
			Manuscript tempManuscript = theCurrentUser.getMyManuscripts().get(Integer.parseInt(input) - 1);
			System.out.println("Enter the path of the updated manuscript");
			myUserInput.nextLine();
			String path = myUserInput.nextLine();

			Manuscript updatedManuscript = new Manuscript(path, theCurrentUser.getMyName(), date,
					tempManuscript.getTitle());
			if (theAuthorRole.updateAuthoredManuscript(theCurrentUser, updatedManuscript, theConferenceList)) {
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
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 * @param theAuthorRole The author role of the user.
	 * @param theCurrentUser The current logged in user.
	 * @param theConference The current selected conference.
	 */
	public void unsubmitManuscriptAuthor(List<User> theUserList, List<Conference> theConferenceList, 
			Author theAuthorRole, User theCurrentUser, Conference theConference) {
		int count = 1;
		
		System.out.println("Select a manuscript to unsubmit or command: ");
		for (Manuscript m : theCurrentUser.getMyManuscripts()) {
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
		System.out.println("B. Back");
		String input = myUserInput.next();
		if (!input.equals("B")) {

			Manuscript removedManuscript = theCurrentUser.getMyManuscripts().get(Integer.parseInt(input) - 1);

			if (theAuthorRole.unsubmitManuscript(theCurrentUser, removedManuscript, theConferenceList)) {
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
		System.out.println("Current Role: Author");
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



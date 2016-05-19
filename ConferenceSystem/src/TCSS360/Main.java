package TCSS360;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import TCSS360.Manuscript.Status;

/**
 * This class represents a conference object.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0 5/8/2016
 */
public class Main implements Serializable {

	/**
	 * Serial identification number
	 */
//	private static final long serialVersionUID = -7648819537622791186L;
//	public static User currentUser;
//	public static Conference currentConference;
	public static final Author AUTHOR = new Author(null);
//	public static final Reviewer REVIEWER = new Reviewer(null);
//	public static final SubprogramChair SUBPROGRAM_CHAIR = new SubprogramChair(null);
//	public static final ProgramChair PROGRAM_CHAIR = new ProgramChair(null);
//	private static boolean initialized = false;
//	private static List<Conference> conferenceList;
//	private static List<User> userList;
//	private static Scanner userInput = new Scanner(System.in);

	/**
	 * Runs the SMART Scientific Manuscripts Are Reviewed in Terminal program
	 * 
	 * @param theargs
	 */
	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] theargs) {

//		try {
//			FileInputStream fileIn = new FileInputStream("userList.ser");
//			ObjectInputStream in = new ObjectInputStream(fileIn);
//			userList = (ArrayList<User>) in.readObject();
//
//			fileIn = new FileInputStream("conferenceList.ser");
//			in = new ObjectInputStream(fileIn);
//			conferenceList = (ArrayList<Conference>) in.readObject();
//
//			fileIn = new FileInputStream("initialized.ser");
//			DataInputStream dataIn = new DataInputStream(fileIn);
//			initialized = (boolean) dataIn.readBoolean();
//
//		} catch (ClassNotFoundException e) {
//
//		} catch (IOException e) {
//
//		}
//
//		if (!initialized) {
//			initialized = setup();
//		}

		// First menu
		TerminalUserInterface UI = new TerminalUserInterface();
		UI.loginRegisterMenu();
	

		// display options for current conference based on roles.

	}

	/**
	 * Initializes the state of the program
	 * 
	 * @return true or false
	 */
	
//	/**
//	 * Print roles available to the user
//	 */
//	public static void printCurrentRoles() {
//		for (Roles r : currentUser.getMyRoles()) {
//			if (r.getClass().getSimpleName().equals("Author")
//					&& r.getConference().getName().equals(currentConference.getName())) {
//				System.out.println("A. Author Options");
//			}
//			if (r.getClass().getSimpleName().equals("ProgramChair")
//					&& r.getConference().getName().equals(currentConference.getName())) {
//				System.out.println("P. Program Chair Options");
//			}
//			if (r.getClass().getSimpleName().equals("Reviewer")
//					&& r.getConference().getName().equals(currentConference.getName())) {
//				System.out.println("R. Reviewer Options");
//			}
//			if (r.getClass().getSimpleName().equals("SubprogramChair")
//					&& r.getConference().getName().equals(currentConference.getName())) {
//				System.out.println("S. Subprogram Chair Options");
//			}
//		}
//	}





//	/**
//	 * Program Chair UI menu.
//	 * 
//	 * @param theFinishedFlag the login flag
//	 * @param theExitFlag the exit flag
//	 * @param theUserList a user list
//	 * @param theConferenceList a conference list
//	 */
//	public static void programChairMenu(List<User> theUserList, List<Conference> theConferenceList) {
//		header();
//		System.out.println("Select an option: ");
//		System.out.println("1. View all manuscripts");
//		System.out.println("2. Reject/Accept Manuscript");
//		System.out.println("3. View all assigned Subprogram Chair and manuscripts");
//		System.out.println("4. Assign a manuscript to a Subprogram Chair");
//		System.out.println("5. Back");
//		System.out.println("6. Exit");
//
//		prompt();
//		int input = userInput.nextInt();
//		int count = 1;
//		ProgramChair tempProgramChair = currentUser.findProgramChairRole();
//
//		switch (input) {
//		case 1:
//			header();
//			count = 1;
//			for (Manuscript m : currentConference.getManuscripts()) { // view assigned manuscripts
//				System.out.println(count + ". " + m.getTitle());
//				count++;
//			}
//			programChairMenu(theUserList, theConferenceList);
//			break;
//		case 2:
//			header();
//			acceptOrRejectManuscript(count, theUserList, theConferenceList, tempProgramChair);
//			break;
//		case 3:
//			header();
//			viewAssignedSubProgManuscripts(currentConference);
//			programChairMenu(theUserList, theConferenceList);
//			break;
//		case 4:
//			// print list of SPCs, pick manuscript
//			header();
//			viewAllAssignedSubprogramChairAndManuscript(count, theUserList, theConferenceList, tempProgramChair);
//			break;
//		case 5:
//			selectRoleMenu(theUserList, theConferenceList);
//			break;
//		case 6:
//			exit();
//			break;
//		default:
//			System.out.println("Invalid Selection returning to last menu");
//			programChairMenu(theUserList, theConferenceList);
//			break;
//		}
//	}

//	/**
//	 * Prints a list of Subprogram chairs and assigned manuscripts.
//	 */
//	public static void viewAssignedSubProgManuscripts(Conference theConference) {	
//		System.out.println("\nSubprogam Chair and Assigned Manuscripts List: ");
//		if (!theConference.getSubProChairList().isEmpty()) {
//			for(User u : theConference.getSubProChairList()) {
//				if(!u.getSubProgManuscript().isEmpty()) {
//					System.out.println(u.getMyName() + ":");
//					for (Manuscript m: u.getSubProgManuscript()) {
//						System.out.println("\t" + m.getTitle());
//					}
//				} 
//				else 
//				{
//					System.out.println(u.getMyName() + " no manuscripts assigned yet.");
//				}
//			}
//		}
//		else {
//			System.out.println("No Subprogram Chairs in this conference");
//		}
//		System.out.println();
//	}
//	
//	/**
//	 * Program Chair accept or reject a manuscript.
//	 * 
//	 * @param count
//	 * @param theUserList
//	 * @param theConferenceList
//	 * @param tempProgramChair
//	 */
//	public static void acceptOrRejectManuscript(int count, List<User> theUserList, List<Conference> theConferenceList,
//			ProgramChair tempProgramChair) {
//		System.out.println("Choose a Manuscript to accept/reject:");
//		ArrayList<Manuscript> reccomendedList = new ArrayList<Manuscript>();
//		int total = 0;
//		for (Manuscript m : currentConference.getManuscripts()) {
//
//			if (m.getStatus() == Status.RECOMMENDED) {
//				reccomendedList.add(m);
//				System.out.println(count + ". " + m.getTitle());
//				System.out.print("\tRecommendations: ");
//				for (RecommendationForm rf : m.getRecomFormList()) {
//					System.out.print(rf.getScore() + ", ");
//				}
//				System.out.println();
//				count++;
//				total++;
//			}
//		}
//
//		if (total == 0) {
//			System.out.println("No Manuscripts");
//			System.out.println("");
//			programChairMenu(theUserList, theConferenceList);
//
//		}
//		prompt();
//		int input = userInput.nextInt();
//		Manuscript selectedManuscript = reccomendedList.get(input - 1);
//
//		System.out.println("1. Accept");
//		System.out.println("2. Reject");
//		System.out.println("3. Back");
//		System.out.println("4. Exit");
//		prompt();
//		input = userInput.nextInt();
//
//		switch (input) {
//		case 1:
//			tempProgramChair.acceptManuscript(selectedManuscript);
//			System.out.println(selectedManuscript.getTitle() + " by " + selectedManuscript.getAuthor() + " Accepted.");
//			programChairMenu(theUserList, theConferenceList);
//			break;
//		case 2:
//			tempProgramChair.rejectManuscript(selectedManuscript);
//			System.out.println(selectedManuscript.getTitle() + " by " + selectedManuscript.getAuthor() + " Rejected.");
//			programChairMenu(theUserList, theConferenceList);
//			break;
//		case 3:
//			programChairMenu(theUserList, theConferenceList);
//			break;
//		case 4:
//			exit();
//			break;
//		}
//	}
//
//	/**
//	 * View all Subprogram Chairs and assigned manuscripts.
//	 * 
//	 * @param count
//	 * @param theUserList list of users
//	 * @param theConferenceList list of conferences
//	 * @param tempProgramChair Program Chair
//	 */
//	public static void viewAllAssignedSubprogramChairAndManuscript(int count, List<User> theUserList,
//			List<Conference> theConferenceList, ProgramChair tempProgramChair) {
//		System.out.println("\nSubProgram Chair List");
//		count = 1;
//		for (User sc : currentConference.getSubProChairList()) {
//			System.out.println(count + ". " + sc.getMyName());
//			count++;
//		}
//
//		prompt();
//		int input = userInput.nextInt();
//		User selected = currentConference.getSubProChairList().get(input - 1);
//		count = 1;
//		for (Manuscript m : currentConference.getManuscripts()) { // view list
//																	// of
//																	// manuscripts
//																	// assigned
//			System.out.println(count + ". " + m.getTitle());
//			count++;
//		}
//		System.out.println("Select a manuscript to assign to " + selected.getMyName());
//		prompt();
//		input = userInput.nextInt();
//		Manuscript selectedManuscript = currentConference.getManuscripts().get(input - 1);
//		ArrayList<Boolean> temp = new ArrayList<Boolean>();
//
//		temp = (ArrayList<Boolean>) tempProgramChair.assignSubProgManuscript(selected, selectedManuscript);
//
//		if (!temp.get(1)) {
//			System.out.println("Cannot assign a manuscript to the author");
//		} else if (!temp.get(0)) {
//			System.out
//					.println("Failed to assign manuscript to " + selected.getMyName() + " because of manuscript limit");
//		} else {
//			System.out.println(selectedManuscript.getTitle() + " assigned to " + selected.getMyName());
//			userList.remove(selected);
//			userList.add(selected);
//		}
//		programChairMenu(theUserList, theConferenceList);
//	}
//


//
//	/**
//	 * Subprogram chair UI menu.
//	 * 
//	 * @param theFinishedFlag login flag
//	 * @param theExitFlag exit flag
//	 * @param theUserList list of users
//	 * @param theConferenceList list of conferences
//	 */
//	public static void subprogramChairMenu(List<User> theUserList, List<Conference> theConferenceList) {
//		header();
//		System.out.println("Select an option: ");
//		System.out.println("1. Assign a manuscript to a reviewer");
//		System.out.println("2. Submit a recommendation for a manuscript");
//		System.out.println("3. Back");
//		System.out.println("4. Exit");
//
//		SubprogramChair tempSubprogramChair = currentUser.findSubprogramChairRole();
//		int count = 1;
//		prompt();
//		int input = userInput.nextInt();
//
//		switch (input) {
//
//		case 1:
//			header();
//			assignManuscriptToReviewer(count, input, tempSubprogramChair);
//			subprogramChairMenu(theUserList, theConferenceList);
//			break;
//		case 2:
//			header();
//			submitRecommendationForManuscript(count, input, tempSubprogramChair);
//			subprogramChairMenu(theUserList, theConferenceList);
//			break;
//		case 3:
//			selectRoleMenu(theUserList, theConferenceList);
//			break;
//		case 4:
//			exit();
//			break;
//		}
//	}
//
//	/**
//	 * Assigns a manuscript to a reviewer.
//	 * 
//	 * @param count
//	 * @param input user input
//	 * @param tempSubprogramChair Subprogram Chair
//	 */
//	public static void assignManuscriptToReviewer(int count, int input, SubprogramChair tempSubprogramChair) {
//		System.out.println("Select a manuscript to assign to a reviewer");
//
//		if (!currentUser.getSubProgManuscript().isEmpty()) {
//			for (Manuscript m : currentUser.getSubProgManuscript()) {
//				System.out.println(count + ". " + m.getTitle());
//				count++;
//			}
//			prompt();
//			input = userInput.nextInt();
//			Manuscript selectedManuscript = currentUser.getSubProgManuscript().get(input - 1);
//			ArrayList<User> reviewerList = new ArrayList<User>();
//
//			for (User u : userList) {
//				if (hasRole(currentConference, REVIEWER, u)) {
//					reviewerList.add(u);
//				}
//			}
//			// System.out.println(reviewerList.size());
//			if (reviewerList.isEmpty()) {
//				System.out.println("No Reviewers to assign for Conference: " + currentConference.getName());
//			} else {
//				count = 1;
//				System.out.println("Select a reviewer to assign the selected manuscript");
//
//				for (User u : reviewerList) {
//					System.out.println(count + ". " + u.getMyName());
//					count++;
//				}
//
//				prompt();
//				input = userInput.nextInt();
//				User selectedReviewer = reviewerList.get(input - 1);
//				List<Boolean> result = tempSubprogramChair.assignReviewerManuscript(selectedReviewer,
//						selectedManuscript);
//
//				if (!result.get(0)) {
//					System.out.println("Cannot assign a review to the author of the manuscript");
//				} else if (!result.get(1)) {
//					System.out.println(
//							"Failed to assign review to " + selectedReviewer.getMyName() + " because of review limit");
//				} else {
//					System.out.println(selectedManuscript.getTitle() + " assigned to " + selectedReviewer.getMyName());
//				}
//			}
//		} else {
//			System.out.println("\nNo manuscripts currently assigned to subprogram chair");
//		}
//	}
//
//	/**
//	 * Submits a recommendation to a manuscript.
//	 * 
//	 * @param count
//	 * @param input user choice
//	 * @param tempSubprogramChair Subprogram Chair
//	 */
//	public static void submitRecommendationForManuscript(int count, int input, SubprogramChair tempSubprogramChair) {
//		System.out.println("Select a manuscript to assign a recommendation");
//		for (Manuscript m : currentUser.getSubProgManuscript()) {
//			System.out.println(count + ". " + m.getTitle());
//			count++;
//		}
//
//		if (!currentUser.getSubProgManuscript().isEmpty()) {
//			prompt();
//			input = userInput.nextInt();
//			Manuscript selectedManuscript = currentUser.getSubProgManuscript().get(input - 1);
//			System.out.println("Enter the path to the recommendation form");
//			userInput.nextLine();
//			String path = userInput.nextLine();
//			System.out.println("Enter a recommendation score");
//			int score = userInput.nextInt();
//			System.out.println("Enter a title for the recommendation form");
//			userInput.nextLine();
//			String title = userInput.nextLine();
//			tempSubprogramChair.submitRecomendation(currentUser, selectedManuscript, score, path, title);
//			System.out.println("Reccommendation Form for " + selectedManuscript.getTitle() + " submitted.");
//		} else {
//			System.out.println("No Manuscripts assigned, returning to last menu.");
//		}
//	}
//

//
//	/**
//	 * Login menu checks to see if person is registered.
//	 * 
//	 * @param userList list of system users
//	 * @return true or false
//	 */
//	public static boolean login(List<User> userList) {
//		System.out.print("Enter your username: ");
//		String input = userInput.next();
//		boolean success = false;
//
//		for (User u : userList) {
//			if (u.getMyLoginName().equals(input)) {
//				currentUser = u;
//				success = true;
//			}
//		}
//		if (success) {
//			System.out.println("\nSuccessfully logged in as " + currentUser.getMyLoginName());
//			return true;
//		} else {
//			System.out.println("\nNo such user exists, returning to last menu.");
//			return false;
//		}
//	}
//
//	/**
//	 * Register menu, returns user
//	 * 
//	 * @return user
//	 */
//	public static User register() {
//		userInput.nextLine();
//		System.out.println("Please enter your full name");
//		prompt();
//		String input = userInput.nextLine();
//		System.out.println("Please enter your desired login name");
//		prompt();
//		String input2 = userInput.next();
//		System.out.println("Please enter your email address");
//		prompt();
//		String input3 = userInput.next();
//
//		return new User(input, input2, input3);
//	}
//
//	public static void header() {
//		System.out.println();
//		System.out.println("---Scientific Manuscripts Are Reviewed in Terminal---");
//		System.out.println("User: " + currentUser.getMyName());
//		System.out.println("Conference: " + currentConference.getName());
//		System.out.println("_______________________________________________________");
//		System.out.println();
//	}
//
//	public static void prompt() {
//		System.out.print(">> ");
//	}
//
//	/**
//	 * UI exit menu
//	 */
//	public static void exit() {
//		System.out.println("You selected exit.");
//
//		try {
//			FileOutputStream fileOut = new FileOutputStream("userList.ser");
//			ObjectOutputStream output = new ObjectOutputStream(fileOut);
//			output.writeObject(userList);
//
//			fileOut = new FileOutputStream("conferenceList.ser");
//			output = new ObjectOutputStream(fileOut);
//			output.writeObject(conferenceList);
//
//			fileOut = new FileOutputStream("initialized.ser");
//			DataOutputStream dataOutput = new DataOutputStream(fileOut);
//			dataOutput.writeBoolean(initialized);
//
//			output.close();
//			fileOut.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}// end of main

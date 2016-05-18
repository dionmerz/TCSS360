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
	private static final long serialVersionUID = -7648819537622791186L;
	public static User currentUser;
	public static Conference currentConference;
	public static final Author AUTHOR = new Author(null);
	public static final Reviewer REVIEWER = new Reviewer(null);
	public static final SubprogramChair SUBPROGRAM_CHAIR = new SubprogramChair(null);
	public static final ProgramChair PROGRAM_CHAIR = new ProgramChair(null);
	private static boolean initialized = false;
	private static List<Conference> conferenceList;
	private static List<User> userList;
	private static Scanner userInput = new Scanner(System.in);

	/**
	 * Runs the SMART Scientific Manuscripts Are Reviewed in Terminal program
	 * 
	 * @param theargs
	 */
	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] theargs) {

		try {
			FileInputStream fileIn = new FileInputStream("userList.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			userList = (ArrayList<User>) in.readObject();

			fileIn = new FileInputStream("conferenceList.ser");
			in = new ObjectInputStream(fileIn);
			conferenceList = (ArrayList<Conference>) in.readObject();

			fileIn = new FileInputStream("initialized.ser");
			DataInputStream dataIn = new DataInputStream(fileIn);
			initialized = (boolean) dataIn.readBoolean();

		} catch (ClassNotFoundException e) {

		} catch (IOException e) {

		}

		if (!initialized) {
			initialized = setup();
		}

		// First menu
		registerLoginMenu(userList, conferenceList);

		// display options for current conference based on roles.

	}

	/**
	 * Initializes the state of the program
	 * 
	 * @return true or false
	 */
	private static boolean setup() {
		userList = new ArrayList<User>();
		userList.add(new User("Adam", "adamlogin", "adam@gmail.com"));
		userList.add(new User("Kevin", "kevinlogin", "kevin@gmail.com"));
		userList.add(new User("Andrew", "andrewlogin", "andrew@gmail.com"));
		userList.add(new User("Bernie", "bernielogin", "bernie@gmail.com"));

		conferenceList = new ArrayList<Conference>();
		conferenceList.add(new Conference("Conference on Programming Language Design and Implementation",
				userList.get(0), "start date", "end date", "paper deadline", "rev deadline", 60, 30));
		conferenceList.add(new Conference("International Conference on Automated Software Engineering", userList.get(0),
				"start date", "end date", "paper deadline", "rev deadline", 0, 0));
		conferenceList.add(new Conference("Conference on Computer Aided Verification", userList.get(0), "start date",
				"end date", "paper deadline", "rev deadline", 60, 30));

		userList.get(0).addMyRole(new SubprogramChair(conferenceList.get(0))); // adam
																				// login
																				// and
																				// roles
		userList.get(0).addMyRole(new Author(conferenceList.get(0)));
		userList.get(0).addMyRole(new ProgramChair(conferenceList.get(0)));
		userList.get(0).addMyRole(new Reviewer(conferenceList.get(0)));

		userList.get(1).addMyRole(new SubprogramChair(conferenceList.get(0))); // Kevein
																				// login
																				// /
																				// roles

		userList.get(2).addMyRole(new Reviewer(conferenceList.get(0))); // Andrew
																		// login
																		// /
																		// roles

		userList.get(3).addMyRole(new Reviewer(conferenceList.get(0))); // Bernie
																		// login
																		// /roles

		currentUser = userList.get(3);
		currentConference = conferenceList.get(0);
		currentConference.addSubProChairList(userList.get(1));

		currentUser.submitManuscript("CoolCS.txt", "How Good is Almost Perfect?", currentUser, currentConference);
		currentUser.submitManuscript("IEEE.txt", "Learning Natural Coding Conventions", currentUser, currentConference);
		currentUser.submitManuscript("PP.txt", "On the Steady-State of Cache Networks", currentUser, currentConference);

		currentUser = userList.get(0);
		SubprogramChair initSubprogramChair = currentUser.findSubprogramChairRole();
		// ProgramChair initProgramChair = currentUser.findProgramChairRole();
		// initProgramChair.assignSubProgManuscript(userList.get(1),
		// currentConference.getManuscripts().get(0));
		// initProgramChair.assignSubProgManuscript(userList.get(1),
		// currentConference.getManuscripts().get(1));
		initSubprogramChair.assignReviewerManuscript(userList.get(1), currentConference.getManuscripts().get(2));

		currentUser = userList.get(1);

		initSubprogramChair.submitRecomendation(currentUser, currentConference.getManuscripts().get(0), 1,
				"reccomend1.txt", "rectitle1");
		initSubprogramChair.submitRecomendation(currentUser, currentConference.getManuscripts().get(1), 3,
				"reccomend2.txt", "rectitle2");
		initSubprogramChair.submitRecomendation(currentUser, currentConference.getManuscripts().get(2), 2,
				"reccomend3.txt", "rectitle3");

		currentConference = conferenceList.get(1);
		currentUser = userList.get(1);
		currentUser.submitManuscript("path1", "Queues Don't Matter When You Can JUMP Them!", currentUser,
				currentConference);
		// initSubprogramChair.assignReviewerManuscript(userList.get(0),
		// currentConference.getManuscripts().get(0));
		// initSubprogramChair.assignReviewerManuscript(userList.get(1),
		// currentConference.getManuscripts().get(0));

		currentUser = userList.get(1);
		currentConference.addSubProChairList(currentUser);
		currentUser = null;
		currentConference = null;
		return true;
	}

	/**
	 * Register/Login UI menu.
	 * 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void registerLoginMenu(List<User> theUserList, List<Conference> theConferenceList) {
		// System.out.println("---Conference Management Systems---\n");
		System.out.println("---Scientific Manuscripts Are Reviewed in Terminal---\n"
				+ "_______________________________________________________\n");
		System.out.println("Welcome to S.M.A.R.T, please select an option: \n1.Login\n2.Register\n3.Exit");
		prompt();

		int input = userInput.nextInt();

		switch (input) {
		case 1:
			if (login(theUserList)) {
				selectConferenceMenu(theUserList, theConferenceList);
			} else {
				registerLoginMenu(theUserList, theConferenceList);
			}
			break;
		case 2:
			theUserList.add(register());
			break;
		case 3:
			exit();
			break;
		default:
			System.out.println("Invalid selection, returning to last menu");
			break;
		}
	}

	/**
	 * Select a conference UI menu.
	 * 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void selectConferenceMenu(List<User> theUserList, List<Conference> theConferenceList) {
		int count = 0;
		System.out.println("Select a conference or option: ");

		for (Conference c : theConferenceList) {
			count++;
			System.out.println(count + ". " + c.getName());
		}
		System.out.println("B. Back\nE. Exit");

		prompt();
		String input = userInput.next();

		switch (input.toUpperCase()) {
		case "E":
			exit();
			break;
		case "B":
			registerLoginMenu(theUserList, theConferenceList);
			break;
		default:
			if (Integer.parseInt(input) > theConferenceList.size()) {
				System.out.println("Invalid selection returning to last menu");
				selectConferenceMenu(theUserList, theConferenceList);
			} else {
				currentConference = theConferenceList.get(Integer.parseInt(input) - 1);
				System.out.println(currentConference.getName() + " selected.");
				// call next menu
				selectRoleMenu(theUserList, theConferenceList);
			}
			break;
		}
	}

	/**
	 * Select a role UI menu.
	 * 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void selectRoleMenu(List<User> theUserList, List<Conference> theConferenceList) {
		header();
		System.out.println("Select an option:\nM. Submit Manuscript");
		printCurrentRoles();
		System.out.println("B. Back\nE. Exit");

		prompt();
		String roleChoiceInput = userInput.next();
		switch (roleChoiceInput.toUpperCase()) {
		case "A":
			hasRole(currentConference, AUTHOR, currentUser);
			authorMenu(theUserList, theConferenceList);
			break;
		case "E":
			exit();
			break;
		case "B":
			selectConferenceMenu(theUserList, theConferenceList);
			break;
		case "S":
			hasRole(currentConference, SUBPROGRAM_CHAIR, currentUser);
			subprogramChairMenu(theUserList, theConferenceList);
			break;
		case "M":
			submitManuscript(theUserList, theConferenceList);
			break;
		case "P":
			hasRole(currentConference, PROGRAM_CHAIR, currentUser);
			programChairMenu(theUserList, theConferenceList);
			break;
		case "R":
			hasRole(currentConference, REVIEWER, currentUser);
			reviewerMenu(theUserList, theConferenceList);
			break;
		default:
			System.out.println("Invalid selection, returning to last menu");
			selectRoleMenu(theUserList, theConferenceList);
			break;
		}
	}

	/**
	 * Print roles available to the user
	 */
	public static void printCurrentRoles() {
		for (Roles r : currentUser.getMyRoles()) {
			if (r.getClass().getSimpleName().equals("Author")
					&& r.getConference().getName().equals(currentConference.getName())) {
				System.out.println("A. Author Options");
			}
			if (r.getClass().getSimpleName().equals("ProgramChair")
					&& r.getConference().getName().equals(currentConference.getName())) {
				System.out.println("P. Program Chair Options");
			}
			if (r.getClass().getSimpleName().equals("Reviewer")
					&& r.getConference().getName().equals(currentConference.getName())) {
				System.out.println("R. Reviewer Options");
			}
			if (r.getClass().getSimpleName().equals("SubprogramChair")
					&& r.getConference().getName().equals(currentConference.getName())) {
				System.out.println("S. Subprogram Chair Options");
			}
		}
	}

	public static void submitManuscript(List<User> theUserList, List<Conference> theConferenceList) {
		System.out.println("Enter the path to the manuscript: ");
		prompt();
		String path = userInput.next();
		System.out.println("Enter the title of the manuscript: ");
		prompt();
		String title = userInput.next();
		if (currentUser.submitManuscript(path, title, currentUser, currentConference)) {
			System.out.println();
			System.out.println(title + " submitted to Conference " + currentConference.getName());
		} else {
			System.out.println();
			System.out.println("The deadline for manuscript submission has passed.\n");
		}
		selectRoleMenu(theUserList, theConferenceList);
	}

	/**
	 * Author UI menu.
	 * 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void authorMenu(List<User> theUserList, List<Conference> theConferenceList) {
		header();
		System.out.println("Select an option: ");
		System.out.println("1. Update Manuscript");
		System.out.println("2. Unsubmit Manuscript");
		System.out.println("3. Back");
		System.out.println("4. Exit");

		prompt();
		String input = userInput.next();
		int count = 1;
		Author tempAuthor = currentUser.findAuthorRole();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());

		switch (Integer.parseInt(input)) {
		case 1:
			// Update Manuscript
			updateManuscriptAuthor(count, input, date, theUserList, theConferenceList, tempAuthor);
			break;
		case 2:
			// Unsubmit Manuscript
			unsubmitManuscriptAuthor(count, input, theUserList, theConferenceList, tempAuthor);
			break;
		case 3:
			selectRoleMenu(theUserList, theConferenceList);
			break;
		case 4:
			exit();
			break;
		default:
			System.out.println("Invalid Selection returning to last menu");
			authorMenu(theUserList, theConferenceList);
			break;
		}
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
	public static void updateManuscriptAuthor(int count, String input, String date, List<User> theUserList,
			List<Conference> theConferenceList, Author tempAuthor) {
		System.out.println("Select a manuscript to update or command: ");
		for (Manuscript m : currentUser.getMyManuscripts()) {
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
		System.out.println("B. Back");
		input = userInput.next();
		if (!input.equals("B")) {
			Manuscript tempManuscript = currentUser.getMyManuscripts().get(Integer.parseInt(input) - 1);
			System.out.println("Enter the path of the updated manuscript");
			String path = userInput.next();

			Manuscript updatedManuscript = new Manuscript(path, currentUser.getMyName(), date,
					tempManuscript.getTitle());
			if (tempAuthor.updateAuthoredManuscript(currentUser, updatedManuscript, theConferenceList)) {
				System.out.println(updatedManuscript.getTitle() + " has been updated.\n");
			} else {
				System.out.println(updatedManuscript.getTitle() + " was not found.\n");
			}

			authorMenu(theUserList, theConferenceList);
		} else {
			authorMenu(theUserList, theConferenceList);
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
	public static void unsubmitManuscriptAuthor(int count, String input, List<User> theUserList,
			List<Conference> theConferenceList, Author tempAuthor) {
		System.out.println("Select a manuscript to unsubmit or command: ");
		for (Manuscript m : currentUser.getMyManuscripts()) {
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
		System.out.println("B. Back");
		input = userInput.next();
		if (!input.equals("B")) {

			Manuscript removedManuscript = currentUser.getMyManuscripts().get(Integer.parseInt(input) - 1);

			if (tempAuthor.unsubmitManuscript(currentUser, removedManuscript, theConferenceList)) {
				System.out.println(removedManuscript.getTitle() + " successfully removed.\n");

			} else {
				System.out.println("Manuscript not found.\n");
			}

			authorMenu(theUserList, theConferenceList);
		} else {
			authorMenu(theUserList, theConferenceList);
		}
	}

	/**
	 * Program Chair UI menu.
	 * 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void programChairMenu(List<User> theUserList, List<Conference> theConferenceList) {
		header();
		System.out.println("Select an option: ");
		System.out.println("1. View all manuscripts");
		System.out.println("2. Reject/Accept Manuscript");
		System.out.println("3. View all assigned Subprogram Chair and manuscripts");
		System.out.println("4. Assign a manuscript to a Subprogram Chair");
		System.out.println("5. Back");
		System.out.println("6. Exit");

		prompt();
		int input = userInput.nextInt();
		int count = 1;
		ProgramChair tempProgramChair = currentUser.findProgramChairRole();

		switch (input) {
		case 1:
			header();
			count = 1;
			for (Manuscript m : currentConference.getManuscripts()) { // view assigned manuscripts
				System.out.println(count + ". " + m.getTitle());
				count++;
			}
			programChairMenu(theUserList, theConferenceList);
			break;
		case 2:
			header();
			acceptOrRejectManuscript(count, theUserList, theConferenceList, tempProgramChair);
			break;
		case 3:
			header();
			viewAssignedSubProgManuscripts(currentConference);
			programChairMenu(theUserList, theConferenceList);
			break;
		case 4:
			// print list of SPCs, pick manuscript
			header();
			viewAllAssignedSubprogramChairAndManuscript(count, theUserList, theConferenceList, tempProgramChair);
			break;
		case 5:
			selectRoleMenu(theUserList, theConferenceList);
			break;
		case 6:
			exit();
			break;
		default:
			System.out.println("Invalid Selection returning to last menu");
			programChairMenu(theUserList, theConferenceList);
			break;
		}
	}

	/**
	 * Prints a list of Subprogram chairs and assigned manuscripts.
	 */
	public static void viewAssignedSubProgManuscripts(Conference theConference) {	
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
	public static void acceptOrRejectManuscript(int count, List<User> theUserList, List<Conference> theConferenceList,
			ProgramChair tempProgramChair) {
		System.out.println("Choose a Manuscript to accept/reject:");
		ArrayList<Manuscript> reccomendedList = new ArrayList<Manuscript>();
		int total = 0;
		for (Manuscript m : currentConference.getManuscripts()) {

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
			programChairMenu(theUserList, theConferenceList);

		}
		prompt();
		int input = userInput.nextInt();
		Manuscript selectedManuscript = reccomendedList.get(input - 1);

		System.out.println("1. Accept");
		System.out.println("2. Reject");
		System.out.println("3. Back");
		System.out.println("4. Exit");
		prompt();
		input = userInput.nextInt();

		switch (input) {
		case 1:
			tempProgramChair.acceptManuscript(selectedManuscript);
			System.out.println(selectedManuscript.getTitle() + " by " + selectedManuscript.getAuthor() + " Accepted.");
			programChairMenu(theUserList, theConferenceList);
			break;
		case 2:
			tempProgramChair.rejectManuscript(selectedManuscript);
			System.out.println(selectedManuscript.getTitle() + " by " + selectedManuscript.getAuthor() + " Rejected.");
			programChairMenu(theUserList, theConferenceList);
			break;
		case 3:
			programChairMenu(theUserList, theConferenceList);
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
	public static void viewAllAssignedSubprogramChairAndManuscript(int count, List<User> theUserList,
			List<Conference> theConferenceList, ProgramChair tempProgramChair) {
		System.out.println("\nSubProgram Chair List");
		count = 1;
		for (User sc : currentConference.getSubProChairList()) {
			System.out.println(count + ". " + sc.getMyName());
			count++;
		}

		prompt();
		int input = userInput.nextInt();
		User selected = currentConference.getSubProChairList().get(input - 1);
		count = 1;
		for (Manuscript m : currentConference.getManuscripts()) { // view list
																	// of
																	// manuscripts
																	// assigned
			System.out.println(count + ". " + m.getTitle());
			count++;
		}
		System.out.println("Select a manuscript to assign to " + selected.getMyName());
		prompt();
		input = userInput.nextInt();
		Manuscript selectedManuscript = currentConference.getManuscripts().get(input - 1);
		ArrayList<Boolean> temp = new ArrayList<Boolean>();

		temp = (ArrayList<Boolean>) tempProgramChair.assignSubProgManuscript(selected, selectedManuscript);

		if (!temp.get(1)) {
			System.out.println("Cannot assign a manuscript to the author");
		} else if (!temp.get(0)) {
			System.out
					.println("Failed to assign manuscript to " + selected.getMyName() + " because of manuscript limit");
		} else {
			System.out.println(selectedManuscript.getTitle() + " assigned to " + selected.getMyName());
			userList.remove(selected);
			userList.add(selected);
		}
		programChairMenu(theUserList, theConferenceList);
	}

	/**
	 * Reviewer UI menu.
	 * 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList  a conference list
	 */
	public static void reviewerMenu(List<User> theUserList, List<Conference> theConferenceList) {
		header();
		System.out.println("Select an option: ");
		System.out.println("1. View assigned manuscripts to review");
		System.out.println("2. Upload a review form");
		System.out.println("3. Back");
		System.out.println("4. Exit");

		Reviewer tempReview = currentUser.findReviewerRole();

		prompt();
		int input = userInput.nextInt();

		switch (input) {
		case 1:
			header();
			viewReviewerManuscripts();
			reviewerMenu(theUserList, theConferenceList);
			break;
		case 2:
			header();
			uploadReviewForm(input, tempReview);
			reviewerMenu(theUserList, theConferenceList);
			break;
		case 3:
			selectRoleMenu(theUserList, theConferenceList);
			break;
		case 4:
			exit();
			break;
		default:
			System.out.println("Invalid selection.");
			reviewerMenu(theUserList, theConferenceList);
			break;
		}
	}

	/**
	 * Upload a review form and attach to manuscript.
	 * 
	 * @param input user input
	 * @param tempReview reviewer
	 */
	public static void uploadReviewForm(int input, Reviewer tempReview) {
		System.out.println("Select a manuscript to upload a review for");
		viewReviewerManuscripts();

		input = userInput.nextInt();
		Manuscript selectedManuscript = currentUser.getMyManuscriptsToReview().get(input - 1);
		System.out.println("Enter the path to the review form");
		userInput.nextLine();
		String path = userInput.nextLine();
		System.out.println("Enter the title of the review form");
		String title = userInput.nextLine();

		List<Boolean> allowed = tempReview.uploadReviewForm(currentUser, currentConference, path,
				currentUser.getMyName(), title, selectedManuscript);

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

	public static void viewReviewerManuscripts() {
		int count = 1;
		if (!currentUser.getMyManuscriptsToReview().isEmpty()) {
			for (Manuscript m : currentUser.getMyManuscriptsToReview()) {
				System.out.println(count + ". " + m.getTitle());
			}
		} else {
			System.out.println("No manuscripts to review");
		}
		System.out.println();
	}

	/**
	 * Subprogram chair UI menu.
	 * 
	 * @param theFinishedFlag login flag
	 * @param theExitFlag exit flag
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 */
	public static void subprogramChairMenu(List<User> theUserList, List<Conference> theConferenceList) {
		header();
		System.out.println("Select an option: ");
		System.out.println("1. Assign a manuscript to a reviewer");
		System.out.println("2. Submit a recommendation for a manuscript");
		System.out.println("3. Back");
		System.out.println("4. Exit");

		SubprogramChair tempSubprogramChair = currentUser.findSubprogramChairRole();
		int count = 1;
		prompt();
		int input = userInput.nextInt();

		switch (input) {

		case 1:
			header();
			assignManuscriptToReviewer(count, input, tempSubprogramChair);
			subprogramChairMenu(theUserList, theConferenceList);
			break;
		case 2:
			header();
			submitRecommendationForManuscript(count, input, tempSubprogramChair);
			subprogramChairMenu(theUserList, theConferenceList);
			break;
		case 3:
			selectRoleMenu(theUserList, theConferenceList);
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
	public static void assignManuscriptToReviewer(int count, int input, SubprogramChair tempSubprogramChair) {
		System.out.println("Select a manuscript to assign to a reviewer");

		if (!currentUser.getSubProgManuscript().isEmpty()) {
			for (Manuscript m : currentUser.getSubProgManuscript()) {
				System.out.println(count + ". " + m.getTitle());
				count++;
			}
			prompt();
			input = userInput.nextInt();
			Manuscript selectedManuscript = currentUser.getSubProgManuscript().get(input - 1);
			ArrayList<User> reviewerList = new ArrayList<User>();

			for (User u : userList) {
				if (hasRole(currentConference, REVIEWER, u)) {
					reviewerList.add(u);
				}
			}
			// System.out.println(reviewerList.size());
			if (reviewerList.isEmpty()) {
				System.out.println("No Reviewers to assign for Conference: " + currentConference.getName());
			} else {
				count = 1;
				System.out.println("Select a reviewer to assign the selected manuscript");

				for (User u : reviewerList) {
					System.out.println(count + ". " + u.getMyName());
					count++;
				}

				prompt();
				input = userInput.nextInt();
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
	public static void submitRecommendationForManuscript(int count, int input, SubprogramChair tempSubprogramChair) {
		System.out.println("Select a manuscript to assign a recommendation");
		for (Manuscript m : currentUser.getSubProgManuscript()) {
			System.out.println(count + ". " + m.getTitle());
			count++;
		}

		if (!currentUser.getSubProgManuscript().isEmpty()) {
			prompt();
			input = userInput.nextInt();
			Manuscript selectedManuscript = currentUser.getSubProgManuscript().get(input - 1);
			System.out.println("Enter the path to the recommendation form");
			userInput.nextLine();
			String path = userInput.nextLine();
			System.out.println("Enter a recommendation score");
			int score = userInput.nextInt();
			System.out.println("Enter a title for the recommendation form");
			userInput.nextLine();
			String title = userInput.nextLine();
			tempSubprogramChair.submitRecomendation(currentUser, selectedManuscript, score, path, title);
			System.out.println("Reccommendation Form for " + selectedManuscript.getTitle() + " submitted.");
		} else {
			System.out.println("No Manuscripts assigned, returning to last menu.");
		}
	}

	/**
	 * This method checks to see if the user has a specified role in a
	 * conference.
	 * 
	 * @param theConference a conference
	 * @param theRole a role
	 * @param theUser a user
	 * @return true or false
	 */
	public static boolean hasRole(Conference theConference, Roles theRole, User theUser) {
		boolean result = false;

		for (Roles r : theUser.getMyRoles()) {
			if (r.getConference().getName().equals(currentConference.getName())
					&& theRole.getClass().getSimpleName().equals(r.getClass().getSimpleName())) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Login menu checks to see if person is registered.
	 * 
	 * @param userList list of system users
	 * @return true or false
	 */
	public static boolean login(List<User> userList) {
		System.out.print("Enter your username: ");
		String input = userInput.next();
		boolean success = false;

		for (User u : userList) {
			if (u.getMyLoginName().equals(input)) {
				currentUser = u;
				success = true;
			}
		}
		if (success) {
			System.out.println("\nSuccessfully logged in as " + currentUser.getMyLoginName());
			return true;
		} else {
			System.out.println("\nNo such user exists, returning to last menu.");
			return false;
		}
	}

	/**
	 * Register menu, returns user
	 * 
	 * @return user
	 */
	public static User register() {
		userInput.nextLine();
		System.out.println("Please enter your full name");
		prompt();
		String input = userInput.nextLine();
		System.out.println("Please enter your desired login name");
		prompt();
		String input2 = userInput.next();
		System.out.println("Please enter your email address");
		prompt();
		String input3 = userInput.next();

		return new User(input, input2, input3);
	}

	public static void header() {
		System.out.println();
		System.out.println("---Scientific Manuscripts Are Reviewed in Terminal---");
		System.out.println("User: " + currentUser.getMyName());
		System.out.println("Conference: " + currentConference.getName());
		System.out.println("_______________________________________________________");
		System.out.println();
	}

	public static void prompt() {
		System.out.print(">> ");
	}

	/**
	 * UI exit menu
	 */
	public static void exit() {
		System.out.println("You selected exit.");

		try {
			FileOutputStream fileOut = new FileOutputStream("userList.ser");
			ObjectOutputStream output = new ObjectOutputStream(fileOut);
			output.writeObject(userList);

			fileOut = new FileOutputStream("conferenceList.ser");
			output = new ObjectOutputStream(fileOut);
			output.writeObject(conferenceList);

			fileOut = new FileOutputStream("initialized.ser");
			DataOutputStream dataOutput = new DataOutputStream(fileOut);
			dataOutput.writeBoolean(initialized);

			output.close();
			fileOut.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}// end of main

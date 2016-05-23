package TCSS360;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalUserInterface implements Serializable {
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -5250437601882775308L;
	
	private transient AuthorMenu myAuthorMenu;
	private transient ReviewerMenu myReviewerMenu;
	private transient SubprogramChairMenu mySubprogramChairMenu;
	private transient ProgramChairMenu myProgramChairMenu;
	private transient Scanner myUserInput;
	private ArrayList<User> myUserList; 
	private ArrayList<Conference> myConferenceList;
	private User myCurrentUser;
	private Conference myCurrentConference;
	private boolean mySetupStatus;
	

	
	public TerminalUserInterface() {
		myUserInput = new Scanner(System.in);
		myAuthorMenu = new AuthorMenu(myUserInput, this);
		myReviewerMenu = new ReviewerMenu(myUserInput, this);
		mySubprogramChairMenu = new SubprogramChairMenu(myUserInput, this);
		myProgramChairMenu = new ProgramChairMenu(myUserInput, this);
		myUserList = new ArrayList<User>();
		myConferenceList = new ArrayList<Conference>();
		mySetupStatus = false;
		
		
	}
	
	public void loginRegisterMenu() {
		
		if (myUserInput == null) {
			myUserInput = new Scanner(System.in);
		}
	
		if (!mySetupStatus) {
			setup();
			mySetupStatus = true;
		}
		
		System.out.println("---Scientific Manuscripts Are Reviewed in Terminal---\n"
				+ "_______________________________________________________\n");
		System.out.println("Welcome to S.M.A.R.T, please select an option: \n1.Login\n2.Register\n3.Exit");
		prompt();

		int input = myUserInput.nextInt();

		switch (input) {
		case 1:
			if (login(myUserList)) {
				selectConferenceMenu(myUserList, myConferenceList);
			} else {
				loginRegisterMenu();
			}
			break;
		case 2:
			myUserList.add(register());
			loginRegisterMenu();
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
	public void selectConferenceMenu(List<User> theUserList, List<Conference> theConferenceList) {
		int count = 0;
		System.out.println("Select a conference or option: ");

		for (Conference c : theConferenceList) {
			count++;
			System.out.println(count + ". " + c.getName());
		}
		System.out.println("B. Back\nE. Exit");

		prompt();
		String input = myUserInput.next();

		switch (input.toUpperCase()) {
		case "E":
			exit();
			break;
		case "B":
			loginRegisterMenu();
			break;
		default:
			if (Integer.parseInt(input) > theConferenceList.size()) {
				System.out.println("Invalid selection returning to last menu");
				selectConferenceMenu(theUserList, theConferenceList);
			} else {
				myCurrentConference = theConferenceList.get(Integer.parseInt(input) - 1);
				System.out.println(myCurrentConference.getName() + " selected.");
				// call next menu
				selectRoleMenu(theUserList, theConferenceList);
			}
			break;
		}
	}

	/**
	 * User input indicator
	 */
	public void prompt() {
		System.out.print(">> ");
	}
	
	/**
	 * Login menu checks to see if person is registered.
	 * 
	 * @param userList list of system users
	 * @return true or false
	 */
	public boolean login(List<User> userList) {
		System.out.print("Enter your username: ");
		String input = myUserInput.next();
		boolean success = false;

		for (User u : userList) {
			if (u.getMyLoginName().equals(input)) {
				myCurrentUser = u;
				success = true;
			}
		}
		if (success) {
			System.out.println("\nSuccessfully logged in as " + myCurrentUser.getMyLoginName());
			return true;
		} else {
			System.out.println("\nNo such user exists, returning to last menu.");
			return false;
		}
	}
	
	/**
	 * UI exit menu
	 */
	public void exit() {
		//System.out.println("You selected exit.");
	}
	
	/**
	 * Register menu, returns user
	 * 
	 * @return user
	 */
	public User register() {
		myUserInput.nextLine();
		System.out.println("Please enter your full name");
		prompt();
		String input = myUserInput.nextLine();
		System.out.println("Please enter your desired login name");
		prompt();
		String input2 = myUserInput.next();
		System.out.println("Please enter your email address");
		prompt();
		String input3 = myUserInput.next();

		return new User(input, input2, input3);
	}
	
	/**
	 * Select a role UI menu.
	 * 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public void selectRoleMenu(List<User> theUserList, List<Conference> theConferenceList) {
		header();
		System.out.println("Select an option:\nM. Submit Manuscript");
		printCurrentRoles();
		System.out.println("B. Back\nE. Exit");

		prompt();
		String roleChoiceInput = myUserInput.next();
		switch (roleChoiceInput.toUpperCase()) {
		case "A":		
			if (myCurrentUser.findAuthorRole() != null) {
				myAuthorMenu.initialAuthorMenu(theUserList, theConferenceList, myCurrentUser, myCurrentConference);
			}
			else {
				System.out.println("Invalid selection, returning to last menu");
				selectRoleMenu(theUserList, theConferenceList);
			}
			
			break;
		case "E":
			exit();
			break;
		case "B":
			selectConferenceMenu(theUserList, theConferenceList);
			break;
		case "S":
			
			if (myCurrentUser.findSubprogramChairRole() != null) {
				mySubprogramChairMenu.initialSubprogramChairMenu(theUserList, theConferenceList, myCurrentUser, myCurrentConference);
			}else {
				System.out.println("Invalid selection, returning to last menu");
				selectRoleMenu(theUserList, theConferenceList);
			}
			break;
		case "M":
			submitManuscript(theUserList, theConferenceList);
			break;
		case "P":			
			if (myCurrentUser.findProgramChairRole() != null) {
				myProgramChairMenu.initialProgramChairMenu(theUserList, theConferenceList, myCurrentUser, myCurrentConference);
			}
			else {
				System.out.println("Invalid selection, returning to last menu");
				selectRoleMenu(theUserList, theConferenceList);
			}		
			break;
		case "R":
			if (myCurrentUser.findReviewerRole() != null) {
				myReviewerMenu.initialReviewerMenu(theUserList, theConferenceList, myCurrentUser, myCurrentConference);
			}
			else {
				System.out.println("Invalid selection, returning to last menu");
				selectRoleMenu(theUserList, theConferenceList);
			}
			break;
		default:
			System.out.println("Invalid selection, returning to last menu");
			selectRoleMenu(theUserList, theConferenceList);
			break;
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
	public boolean hasRole(Conference theConference, Roles theRole, User theUser) {
		boolean result = false;

		for (Roles r : theUser.getMyRoles()) {
			if (r.getConference().getName().equals(myCurrentConference.getName())
					&& theRole.getClass().getSimpleName().equals(r.getClass().getSimpleName())) {
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * Print roles available to the user
	 */
	public void printCurrentRoles() {
		for (Roles r : myCurrentUser.getMyRoles()) {
			if (r.getClass().getSimpleName().equals("Author")
					&& r.getConference().getName().equals(myCurrentConference.getName())) {
				System.out.println("A. Author Options");
			}
			if (r.getClass().getSimpleName().equals("ProgramChair")
					&& r.getConference().getName().equals(myCurrentConference.getName())) {
				System.out.println("P. Program Chair Options");
			}
			if (r.getClass().getSimpleName().equals("Reviewer")
					&& r.getConference().getName().equals(myCurrentConference.getName())) {
				System.out.println("R. Reviewer Options");
			}
			if (r.getClass().getSimpleName().equals("SubprogramChair")
					&& r.getConference().getName().equals(myCurrentConference.getName())) {
				System.out.println("S. Subprogram Chair Options");
			}
		}
	}
	
	public void header() {
		System.out.println();
		System.out.println("---Scientific Manuscripts Are Reviewed in Terminal---");
		System.out.println("User: " + myCurrentUser.getMyName());
		System.out.println("Conference: " + myCurrentConference.getName());
		System.out.println("_______________________________________________________");
		System.out.println();
	}
	
	public void submitManuscript(List<User> theUserList, List<Conference> theConferenceList) {
		System.out.println("Enter the path to the manuscript: ");
		prompt();
		String path = myUserInput.next();
		System.out.println("Enter the title of the manuscript: ");
		prompt();
		String title = myUserInput.next();
		if (myCurrentUser.submitManuscript(path, title, myCurrentUser, myCurrentConference)) {
			System.out.println();
			System.out.println(title + " submitted to Conference " + myCurrentConference.getName());
		} else {
			System.out.println();
			System.out.println("The deadline for manuscript submission has passed.\n");
		}
		selectRoleMenu(theUserList, theConferenceList);
	}
	
	private boolean setup() {
		myUserList = new ArrayList<User>();
		myUserList.add(new User("Adam", "adamlogin", "adam@gmail.com"));
		myUserList.add(new User("Kevin", "kevinlogin", "kevin@gmail.com"));
		myUserList.add(new User("Andrew", "andrewlogin", "andrew@gmail.com"));
		myUserList.add(new User("Bernie", "bernielogin", "bernie@gmail.com"));

		myConferenceList = new ArrayList<Conference>();
		myConferenceList.add(new Conference("Conference on Programming Language Design and Implementation",
				myUserList.get(0), "start date", "end date", "paper deadline", "rev deadline", 60, 30));
		myConferenceList.add(new Conference("International Conference on Automated Software Engineering", myUserList.get(0),
				"start date", "end date", "paper deadline", "rev deadline", 0, 0));
		myConferenceList.add(new Conference("Conference on Computer Aided Verification", myUserList.get(0), "start date",
				"end date", "paper deadline", "rev deadline", 60, 30));

		myUserList.get(0).addMyRole(new SubprogramChair(myConferenceList.get(0))); // adam
																				// login
																				// and
																				// roles
		myUserList.get(0).addMyRole(new Author(myConferenceList.get(0)));
		myUserList.get(0).addMyRole(new ProgramChair(myConferenceList.get(0)));
		myUserList.get(0).addMyRole(new Reviewer(myConferenceList.get(0)));

		myUserList.get(1).addMyRole(new SubprogramChair(myConferenceList.get(0))); // Kevein
																				// login
																				// /
																				// roles

		myUserList.get(2).addMyRole(new Reviewer(myConferenceList.get(0))); // Andrew
																		// login
																		// /
																		// roles

		myUserList.get(3).addMyRole(new Reviewer(myConferenceList.get(0))); // Bernie
																		// login
																		// /roles

		myCurrentUser = myUserList.get(3);
		myCurrentConference = myConferenceList.get(0);
		myCurrentConference.addSubProChairList(myUserList.get(1));

		myCurrentUser.submitManuscript("CoolCS.txt", "How Good is Almost Perfect?", myCurrentUser, myCurrentConference);
		myCurrentUser.submitManuscript("IEEE.txt", "Learning Natural Coding Conventions", myCurrentUser, myCurrentConference);
		myCurrentUser.submitManuscript("PP.txt", "On the Steady-State of Cache Networks", myCurrentUser, myCurrentConference);

		myCurrentUser = myUserList.get(0);
		SubprogramChair initSubprogramChair = myCurrentUser.findSubprogramChairRole();
		// ProgramChair initProgramChair = currentUser.findProgramChairRole();
		// initProgramChair.assignSubProgManuscript(userList.get(1),
		// currentConference.getManuscripts().get(0));
		// initProgramChair.assignSubProgManuscript(userList.get(1),
		// currentConference.getManuscripts().get(1));
		initSubprogramChair.assignReviewerManuscript(myUserList.get(1), myCurrentConference.getManuscripts().get(2));

		myCurrentUser = myUserList.get(1);

		initSubprogramChair.submitRecomendation(myCurrentUser, myCurrentConference, myCurrentConference.getManuscripts().get(0), 1,
				"reccomend1.txt", "rectitle1");
		initSubprogramChair.submitRecomendation(myCurrentUser, myCurrentConference, myCurrentConference.getManuscripts().get(1), 3,
				"reccomend2.txt", "rectitle2");
		initSubprogramChair.submitRecomendation(myCurrentUser, myCurrentConference, myCurrentConference.getManuscripts().get(2), 2,
				"reccomend3.txt", "rectitle3");

		myCurrentConference = myConferenceList.get(1);
		myCurrentUser = myUserList.get(1);
		myCurrentUser.submitManuscript("path1", "Queues Don't Matter When You Can JUMP Them!", myCurrentUser,
				myCurrentConference);
		// initSubprogramChair.assignReviewerManuscript(userList.get(0),
		// currentConference.getManuscripts().get(0));
		// initSubprogramChair.assignReviewerManuscript(userList.get(1),
		// currentConference.getManuscripts().get(0));

		myCurrentUser = myUserList.get(1);
		myCurrentConference.addSubProChairList(myCurrentUser);
		myCurrentUser = null;
		myCurrentConference = null;
		return true;
	}

	
}

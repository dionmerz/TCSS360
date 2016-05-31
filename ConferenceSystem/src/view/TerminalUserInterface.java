package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Conference;
import model.ProgramChair;
import model.Reviewer;
import model.Roles;
import model.SubprogramChair;
import model.User;

/**
 * Control Menu for all Role user Menus.
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 *
 */
public class TerminalUserInterface implements Serializable {

	private static final long serialVersionUID = -5250437601882775308L;
	
	private AuthorMenu myAuthorMenu;
	private ReviewerMenu myReviewerMenu;
	private SubprogramChairMenu mySubprogramChairMenu;
	private ProgramChairMenu myProgramChairMenu;
	private transient Scanner myUserInput;
	private ArrayList<User> myUserList; 
	private ArrayList<Conference> myConferenceList;
	private User myCurrentUser;
	private Conference myCurrentConference;
	private boolean mySetupStatus;
	
	public TerminalUserInterface() {
		myUserInput = new Scanner(System.in);
		myAuthorMenu = new AuthorMenu(myUserInput);
		myReviewerMenu = new ReviewerMenu(myUserInput);
		mySubprogramChairMenu = new SubprogramChairMenu(myUserInput);
		myProgramChairMenu = new ProgramChairMenu(myUserInput);
		myUserList = new ArrayList<User>();
		myConferenceList = new ArrayList<Conference>();
		mySetupStatus = false;	
	}
	
	/**
	 * Menu for logging the user into the system.
	 */
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
	 * A menu for selecting the current conference.
	 * @param theUserList the list of all Users.
	 * @param theConferenceList the list of all conferences.
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
	 * @return true if login successful, false otherwise.
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
		System.out.println("Thank you for using S.M.A.R.T, goodbye");
	}
	
	/**
	 * Menu for registering a new User into the system.
	 * 
	 * @return user the new registered User.
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
	 * A Menu for selecting which role to use.
	 * @param theUserList the list of all Users registered in the System.
	 * @param theConferenceList the list of all Conferences.
	 */
	public void selectRoleMenu(List<User> theUserList, List<Conference> theConferenceList) {
		boolean exited;
		header();
		System.out.println("Select an option:\nA. Author Options");
		printCurrentRoles();
		System.out.println("B. Back\nE. Exit");
		
		prompt();
		String roleChoiceInput = myUserInput.next();
		switch (roleChoiceInput.toUpperCase()) {
		case "A":		
				exited = false;
				exited = myAuthorMenu.initialAuthorMenu(theUserList, theConferenceList, 
						myCurrentUser, myCurrentConference);
				if (!exited) {
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
				exited = false;
				exited = mySubprogramChairMenu.initialSubprogramChairMenu(theUserList, theConferenceList, 
						myCurrentUser, myCurrentConference);

				if (!exited) {
					selectRoleMenu(theUserList, theConferenceList);
				}
			}else {
				System.out.println("Invalid selection, returning to last menu");
				selectRoleMenu(theUserList, theConferenceList);
			}
			break;
			
		case "P":						
			if (myCurrentUser.findProgramChairRole() != null) {
				exited = false;
				exited = myProgramChairMenu.initialProgramChairMenu(theUserList, theConferenceList, 
						myCurrentUser, myCurrentConference);

				if (!exited) {
					selectRoleMenu(theUserList, theConferenceList);
				}
			}else {
				System.out.println("Invalid selection, returning to last menu");
				selectRoleMenu(theUserList, theConferenceList);
			}		
			break;
		case "R":
			if (myCurrentUser.findReviewerRole() != null) {
				exited = false;
				exited = myReviewerMenu.initialReviewerMenu(theUserList, theConferenceList, 
						myCurrentUser, myCurrentConference);

				if (!exited) {
					selectRoleMenu(theUserList, theConferenceList);
				}
				
			}else {
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
	 * @param theConference the current selected Conference
	 * @param theRole the current role the logged in User is using.
	 * @param theUser the current logged in User.
	 * @return true if the User has the specific Role, false otherwise.
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
	 * Print roles available to the logged in User.
	 */
	public void printCurrentRoles() {
		for (Roles r : myCurrentUser.getMyRoles()) {
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
	
	/**
	 * Displays a header with current logged in User info, and selected Conference info.
	 */
	public void header() {
		System.out.println();
		System.out.println("---Scientific Manuscripts Are Reviewed in Terminal---");
		System.out.println("User: " + myCurrentUser.getMyName());
		System.out.println("Conference: " + myCurrentConference.getName());
		System.out.println("_______________________________________________________");
		System.out.println();
	}
	
	/**
	 * Method to initialize the System with Conferences and Program Chairs
	 * 
	 * @return true after System is initialized.
	 */
	private boolean setup() {

		myUserList.add(new User("Adam Marr", "amarr", "adam@gmail.com"));
		myUserList.add(new User("Kevin Li", "kli", "kevin@gmail.com"));
		myUserList.add(new User("Andrew Merz", "amerz", "andrew@gmail.com"));
		myUserList.add(new User("Bernie Guzman", "bguzman", "bernie@gmail.com"));

		
		myConferenceList.add(new Conference("Conference on Programming Language Design and Implementation",
				myUserList.get(0), "8/1/2016", "8/8/2016", "6/20/2016", "7/20/2016", 60, 30));
		myConferenceList.add(new Conference("International Conference on Automated Software Engineering", myUserList.get(0),
				"8/1/2016", "8/8/2016", "6/20/2016", "7/20/2016", 0, 0));
		myConferenceList.add(new Conference("Conference on Computer Aided Verification", myUserList.get(0), "8/1/2016",
				"8/8/2016", "6/20/2016", "7/20/2016", 60, 30));
		myConferenceList.add(new Conference("International Conference on Software Engineering", myUserList.get(0), "8/1/2016",
				"8/8/2016", "6/20/2016", "7/20/2016", 60, 30));

		
		// Adam initialized info
		User adam = myUserList.get(0);
		adam.addMyRole(new ProgramChair(myConferenceList.get(0)));
		
		// Kevin initialized info
		User kevin = myUserList.get(1);
		kevin.addMyRole(new ProgramChair(myConferenceList.get(1)));
		kevin.addMyRole(new Reviewer(myConferenceList.get(0)));
		
		// Andrew initialized info
		User andrew = myUserList.get(2);
		andrew.addMyRole(new ProgramChair(myConferenceList.get(2)));
		andrew.addMyRole(new SubprogramChair(myConferenceList.get(0)));
		myConferenceList.get(0).addSubProChairList(andrew);
		andrew.addMyRole(new Reviewer(myConferenceList.get(0)));
		
		// Bernie initialized info
		User bernie = myUserList.get(3);
		bernie.addMyRole(new ProgramChair(myConferenceList.get(3)));
		bernie.addMyRole(new SubprogramChair(myConferenceList.get(0)));
		myConferenceList.get(0).addSubProChairList(bernie);
		
		// Submit 4 papers to first conference
		andrew.submitManuscript("Acceleration Methods for Numeric CSPs.txt", "Accelerated Methods", andrew, myConferenceList.get(0));
		andrew.submitManuscript("Biometric Security and Privacy in The Big Data Era.txt", "Biometric Security", andrew, myConferenceList.get(0));
		andrew.submitManuscript("Complexity of and Algorithms for Borda Manipulation.txt", "Complexity of Algorithms", andrew, myConferenceList.get(0));
		andrew.submitManuscript("From Non-Negative to General Operator Cost Partitioning.txt", "General Partitioning Cost", andrew, myConferenceList.get(0));
		

		// Assign Bernie (Subprogram Chair) 4 papers to recommend.
		adam.findProgramChairRole().assignSubProgManuscript(bernie, myConferenceList.get(0), andrew.getMyManuscripts().get(0));
		adam.findProgramChairRole().assignSubProgManuscript(bernie, myConferenceList.get(0), andrew.getMyManuscripts().get(1));
		adam.findProgramChairRole().assignSubProgManuscript(bernie, myConferenceList.get(0), andrew.getMyManuscripts().get(2));
		adam.findProgramChairRole().assignSubProgManuscript(bernie, myConferenceList.get(0), andrew.getMyManuscripts().get(3));
		
		// Assign Kevin (reviewer) 4 papers to review.
		bernie.findSubprogramChairRole().assignReviewerManuscript(kevin, bernie.getSubProgManuscript().get(0));
		bernie.findSubprogramChairRole().assignReviewerManuscript(kevin, bernie.getSubProgManuscript().get(1));
		bernie.findSubprogramChairRole().assignReviewerManuscript(kevin, bernie.getSubProgManuscript().get(2));
		bernie.findSubprogramChairRole().assignReviewerManuscript(kevin, bernie.getSubProgManuscript().get(3));
		
		
		return true;
	}

	
}

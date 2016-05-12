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
	
	
	
	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] theargs) {
	boolean finished = false;
	boolean exit = false;

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

		} catch(ClassNotFoundException e) {
			
		} catch (IOException e) {
			
		}

		if(!initialized) {
			initialized = setup();
		}

		//First menu
		registerLoginMenu(finished, exit, userList, conferenceList);

		//display options for current conference based on roles.	


	}

	/**
	 * Initializes the state of the program 
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
		conferenceList.add(new Conference("International Conference on Automated Software Engineering",
				userList.get(0), "start date", "end date", "paper deadline", "rev deadline", 0, 0));
		conferenceList.add(new Conference("Conference on Computer Aided Verification", userList.get(0), "start date", "end date", "paper deadline", "rev deadline", 60, 30));

		userList.get(0).addMyRole(new SubprogramChair(conferenceList.get(0)));   // adam login and roles
		userList.get(0).addMyRole(new Author(conferenceList.get(0)));
		userList.get(0).addMyRole(new ProgramChair(conferenceList.get(0)));
		userList.get(0).addMyRole(new Reviewer(conferenceList.get(0)));

		userList.get(1).addMyRole(new SubprogramChair(conferenceList.get(0)));	// Kevein login / roles

		userList.get(2).addMyRole(new Reviewer(conferenceList.get(0)));			// Andrew login / roles

		userList.get(3).addMyRole(new Reviewer(conferenceList.get(0)));			// Bernie login /roles



		currentUser = userList.get(3);
		currentConference = conferenceList.get(0);

		currentConference.addSubProChairList(userList.get(1));


		currentUser.submitManuscript("CoolCS.txt", "How Good is Almost Perfect?", currentUser, currentConference);
		currentUser.submitManuscript("IEEE.txt", "Learning Natural Coding Conventions", currentUser, currentConference);
		currentUser.submitManuscript("PP.txt", "On the Steady-State of Cache Networks", currentUser, currentConference);

		currentUser = userList.get(0);
		SubprogramChair initSubprogramChair = currentUser.findSubprogramChairRole();
		ProgramChair initProgramChair = currentUser.findProgramChairRole();
		//initProgramChair.assignSubProgManuscript(userList.get(1), currentConference.getManuscripts().get(0));
		//initProgramChair.assignSubProgManuscript(userList.get(1), currentConference.getManuscripts().get(1));
		initSubprogramChair.assignReviewerManuscript(userList.get(1), currentConference.getManuscripts().get(2));

		currentUser = userList.get(1);

		initSubprogramChair.submitRecomendation(currentUser, currentConference.getManuscripts().get(0), 1, "reccomend1.txt", "rectitle1");
		initSubprogramChair.submitRecomendation(currentUser, currentConference.getManuscripts().get(1), 3, "reccomend2.txt", "rectitle2");
		initSubprogramChair.submitRecomendation(currentUser, currentConference.getManuscripts().get(2), 2, "reccomend3.txt", "rectitle3");

		currentConference = conferenceList.get(1);
		currentUser = userList.get(1);
		currentUser.submitManuscript("path1", "Queues Don't Matter When You Can JUMP Them!", currentUser, currentConference);
		//initSubprogramChair.assignReviewerManuscript(userList.get(0), currentConference.getManuscripts().get(0));

		//initSubprogramChair.assignReviewerManuscript(userList.get(1), currentConference.getManuscripts().get(0));

		currentUser = userList.get(1);
		currentConference.addSubProChairList(currentUser);

		currentUser = null;
		currentConference = null;
		return true;
	}

	/**
	 * Register/Login UI menu.
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void registerLoginMenu(boolean theFinishedFlag, boolean theExitFlag, List<User> theUserList, List<Conference> theConferenceList) {
		System.out.println("Select an option: \n1.Login\n2.Register\n3.Exit");
		prompt();
		int input = userInput.nextInt();

		switch(input) {
		case 1:				
			theFinishedFlag = login(theUserList);
			break;
		case 2:
			theUserList.add(register());
			break;
		case 3:
			exit();
			theExitFlag = true;
			break;
		default:
			System.out.println("Invalid selection, returning to last menu");
			break;
		}

		if(!theFinishedFlag && !theExitFlag) {
			registerLoginMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
		} else if (!theExitFlag){
			theFinishedFlag = false;
			selectConferenceMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
		}
	}

	/**
	 * Select a conference UI menu. 
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void selectConferenceMenu(boolean theFinishedFlag, boolean theExitFlag, List<User> theUserList, List<Conference> theConferenceList) {
		int count = 0;
		System.out.println("Select a conference or option: ");
		

		for(Conference c : theConferenceList) {
			count++;
			System.out.println(count + ". " + c.getName());
		}
		System.out.println("B. Back\nE. Exit");

		prompt();
		String input = userInput.next();
		

		switch(input) {
		case "E": 
			exit();
			break;
		case "B":
			registerLoginMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		default: 
			if(Integer.parseInt(input) > theConferenceList.size()) {
				System.out.println("Invalid selection returning to last menu");
				selectConferenceMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
				
			} else {
				currentConference = theConferenceList.get(Integer.parseInt(input) - 1);		
				System.out.println(currentConference.getName() + " selected.");
				//call next menu
				selectRoleMenu(theExitFlag, theFinishedFlag, theUserList, theConferenceList);
			}
			break;
		}
	}

	/**
	 * Select a role UI menu.
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void selectRoleMenu(boolean theFinishedFlag, boolean theExitFlag, List<User> theUserList, List<Conference> theConferenceList) {
		header();
		System.out.println("\nSelect an option:\nM. Submit Manuscript");

		for(Roles r : currentUser.getMyRoles()) {
			if(r.getClass().getSimpleName().equals("Author") && r.getConference().getName().equals(currentConference.getName())) {
				System.out.println("A. Author Options");
			}
			if(r.getClass().getSimpleName().equals("ProgramChair") && r.getConference().getName().equals(currentConference.getName())) {
				System.out.println("P. Program Chair Options");
			}
			if(r.getClass().getSimpleName().equals("Reviewer") && r.getConference().getName().equals(currentConference.getName())) {
				System.out.println("R. Reviewer Options");
			}
			if(r.getClass().getSimpleName().equals("SubprogramChair") && r.getConference().getName().equals(currentConference.getName())) {
				System.out.println("S. Subprogram Chair Options");
			}	
		}

		System.out.println("B. Back\nE. Exit");

		prompt();
		String roleChoiceInput = userInput.next();
		switch(roleChoiceInput) {
		case "A":
			hasRole(currentConference, AUTHOR, currentUser);
			authorMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case "E":
			exit();
			break;
		case "B":
			selectConferenceMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case "S": 
			hasRole(currentConference, SUBPROGRAM_CHAIR, currentUser);
			subprogramChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case "M":
			System.out.println("Enter the path to the manuscript: ");
			prompt();
			String path = userInput.next();
			System.out.println("Enter the title of the manuscript: ");
			prompt();
			String title = userInput.next();				
			currentUser.submitManuscript(path, title, currentUser, currentConference);
			
			selectRoleMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case "P": 
			hasRole(currentConference, PROGRAM_CHAIR, currentUser);
			programChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case "R": 
			hasRole(currentConference, REVIEWER, currentUser);
			reviewerMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		default: 
			System.out.println("Invalid selection, returning to last menu");
			selectRoleMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		}
	}

	/**
	 * Author UI menu.
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void authorMenu(boolean theFinishedFlag, boolean theExitFlag, List<User> theUserList, List<Conference> theConferenceList) {
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

		switch(Integer.parseInt(input)) {
		case 1:
			//Update Manuscript
			header();
			System.out.println("Select a manuscript to update or command: ");
			for(Manuscript m : currentUser.getMyManuscripts()) {
				System.out.println(count + ". " + m.getTitle());
				count++;
			}
			System.out.println("B. Back");
			
			prompt();
			input = userInput.next();
			if(!input.equals("B")) {
				Manuscript tempManuscript = currentUser.getMyManuscripts().get(Integer.parseInt(input) - 1);			
				System.out.println("Enter the path of the updated manuscript");
				String path = userInput.next();

				Manuscript updatedManuscript = new Manuscript(path, currentUser.getMyName(), date, tempManuscript.getTitle());
				tempAuthor.updateAuthoredManuscript(currentUser, updatedManuscript, theConferenceList);
				authorMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			} else {
				authorMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			}
			break;
		case 2:
			//Unsubmit Manuscript
			header();
			System.out.println("Select a manuscript to unsubmit or command: ");
			for(Manuscript m : currentUser.getMyManuscripts()) {
				System.out.println(count + ". " + m.getTitle());
				count++;
			}			
			System.out.println("B. Back");
			
			prompt();
			input = userInput.next();	
			if(!input.equals("B")) {
				Manuscript removedManuscript = currentUser.getMyManuscripts().get(Integer.parseInt(input) - 1);
				tempAuthor.unsubmitManuscript(currentUser, removedManuscript, theConferenceList);	
				authorMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			} else {
				authorMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			}
			break;
		case 3: 
			selectRoleMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 4:
			exit();
			break;
		default: 
			System.out.println("Invalid Selection returning to last menu");
			authorMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		}
	}

	/**
	 * Program Chair UI menu.
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void programChairMenu(boolean theFinishedFlag, boolean theExitFlag, List<User> theUserList, List<Conference> theConferenceList) {
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
		Manuscript selectedManuscript;

		switch(input) {
		case 1:
			header();
			tempProgramChair.viewAllManuscripts(currentConference);
			System.out.println();
			programChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 2:
			header();
			System.out.println("Choose a Manuscript to accept/reject:");
			ArrayList<Manuscript> reccomendedList = new ArrayList<Manuscript>();
			int total = 0;
			for(Manuscript m : currentConference.getManuscripts()) {

				if(m.getStatus() == Status.RECOMMENDED) {
					reccomendedList.add(m);
					System.out.println(count + ". " + m.getTitle());
					System.out.print("\tRecommendations: ");
					for(RecommendationForm rf : m.getRecomFormList()) {
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
				programChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);

			}
			prompt();
			input = userInput.nextInt();
			selectedManuscript = reccomendedList.get(input - 1);


			System.out.println("1. Accept");
			System.out.println("2. Reject");
			System.out.println("3. Back");
			System.out.println("4. Exit");
			prompt();
			input = userInput.nextInt();

			switch(input) {
			case 1: 
				tempProgramChair.acceptManuscript(selectedManuscript);
				programChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
				break;
			case 2: 
				tempProgramChair.rejectManuscript(selectedManuscript);
				programChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
				break;
			case 3:
				programChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
				break;
			case 4:
				exit();
				break;
			}

			break;
		case 3:
			header();
			tempProgramChair.viewAssignedSubProgManuscripts(currentConference);
			programChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 4:
			//print list of SPCs, pick manuscript
			header();
			System.out.println("\nSubProgram Chair List");
			count = 1;
			for(User sc : currentConference.getSubProChairList()) {
				System.out.println(count + ". " + sc.getMyName());
				count++;
			}

			prompt();
			input = userInput.nextInt();
			User selected = currentConference.getSubProChairList().get(input - 1);		
			tempProgramChair.viewAllManuscripts(currentConference);
			System.out.println("Select a manuscript to assign to " + selected.getMyName());
			prompt();
			input = userInput.nextInt();			
			selectedManuscript = currentConference.getManuscripts().get(input - 1);

			tempProgramChair.assignSubProgManuscript(selected, selectedManuscript);
			
			userList.remove(selected);
			userList.add(selected);
			

			programChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 5:
			selectRoleMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 6:
			exit();
			break;
		default:
			System.out.println("Invalid Selection returning to last menu");
			programChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		}
	}

	/**
	 * Reviewer UI menu.
	 * @param theFinishedFlag the login flag
	 * @param theExitFlag the exit flag
	 * @param theUserList a user list
	 * @param theConferenceList a conference list
	 */
	public static void reviewerMenu(boolean theFinishedFlag, boolean theExitFlag, List<User> theUserList, List<Conference> theConferenceList) {
		header();
		System.out.println("Select an option: ");
		System.out.println("1. View assigned manuscripts to review");
		System.out.println("2. Upload a review form");
		System.out.println("3. Back");
		System.out.println("4. Exit");

		Reviewer tempReview = currentUser.findReviewerRole();

		prompt();
		int input = userInput.nextInt();

		switch(input) {
		case 1:
			header();
			tempReview.viewAssignedManuscripts(currentUser);
			reviewerMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 2:
			header();
			System.out.println("Select a manuscript to upload a review for");
			tempReview.viewAssignedManuscripts(currentUser);
			if (!currentUser.getMyManuscriptsToReview().isEmpty()) {
				prompt();
				input = userInput.nextInt();
				Manuscript selectedManuscript = currentUser.getMyManuscriptsToReview().get(input - 1);
				System.out.println("Enter the path to the review form");
				userInput.nextLine();
				String path = userInput.nextLine();
				System.out.println("Enter the title of the review form");
				String title = userInput.nextLine();
				//				tempReview.uploadReviewForm(currentConference, currentUser, path, 
				//						currentUser.getMyName(), title, selectedManuscript);
				tempReview.uploadReviewForm(currentUser, currentConference, path, //  5/8/2015 bernabeg
				currentUser.getMyName(), title, selectedManuscript);
			}
			reviewerMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 3:
			selectRoleMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 4:
			exit();
			break;
		default: 
			System.out.println("Invalid selection.");
			reviewerMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		}
	}

	/**
	 * Subprogram chair UI menu. 
	 * @param theFinishedFlag login flag
	 * @param theExitFlag exit flag
	 * @param theUserList list of users
	 * @param theConferenceList list of conferences
	 */
	public static void subprogramChairMenu(boolean theFinishedFlag, boolean theExitFlag, List<User> theUserList, List<Conference> theConferenceList) {
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
		Manuscript selectedManuscript;

		switch(input) {
		
		case 1:
			header();
			System.out.println("Select a manuscript to assign to a reviewer");
			
			if(!currentUser.getSubProgManuscript().isEmpty()) {
				for(Manuscript m : currentUser.getSubProgManuscript()) {
					System.out.println(count + ". " + m.getTitle());
					count++;
				}
				prompt();
				input = userInput.nextInt();
				selectedManuscript = currentUser.getSubProgManuscript().get(input - 1);
				ArrayList<User> reviewerList = new ArrayList<User>();

				for(User u : userList) {
					if(hasRole(currentConference, REVIEWER, u )) {
						reviewerList.add(u);
					}
				}
				
				//System.out.println(reviewerList.size());
				
				if (reviewerList.isEmpty()) {
					System.out.println("No Reviewers to assign for Conference: " + currentConference.getName());

				}
				
				else {

					count = 1;
					System.out.println("Select a reviewer to assign the selected manuscript");

					for(User u : reviewerList) {
						System.out.println(count + ". " + u.getMyName());
						count++;
					}

					prompt();
					input = userInput.nextInt();
					User selectedReviewer = reviewerList.get(input - 1);

					tempSubprogramChair.assignReviewerManuscript(selectedReviewer, selectedManuscript);
				}
			}
			else 
			{
				System.out.println("\nNo manuscripts currently assigned to subprogram chair");
			}


			subprogramChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 2:
			header();
			System.out.println("Select a manuscript to assign a recommendation");
			for(Manuscript m : currentUser.getSubProgManuscript()) {
				System.out.println(count + ". " + m.getTitle());
				count++;
			}
			
			
			if (!currentUser.getSubProgManuscript().isEmpty()) {
				prompt();
				input = userInput.nextInt();
				selectedManuscript = currentUser.getSubProgManuscript().get(input - 1);
				System.out.println("Enter the path to the recommendation form");
				userInput.nextLine();
				String path = userInput.nextLine();
				System.out.println("Enter a recommendation score");
				int score = userInput.nextInt();
				System.out.println("Enter a title for the recommendation form");
				userInput.nextLine();
				String title = userInput.nextLine();
				tempSubprogramChair.submitRecomendation(currentUser, selectedManuscript, score, path, title);
			}
			else {
				System.out.println("No Manuscripts assigned, returning to last menu.");
			}

			
			
			
			subprogramChairMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 3:
			selectRoleMenu(theFinishedFlag, theExitFlag, theUserList, theConferenceList);
			break;
		case 4:
			exit();
			break;
		}
	}

	/**
	 * This method checks to see if the user has a specified role in a conference. 
	 * @param theConference a conference
	 * @param theRole a role
	 * @param theUser a user
	 * @return true or false
	 */
	public static boolean hasRole(Conference theConference, Roles theRole, User theUser) {
		boolean result = false;

		for (Roles r: theUser.getMyRoles()){
			if(r.getConference().getName().equals(currentConference.getName()) && 
					theRole.getClass().getSimpleName().equals(r.getClass().getSimpleName())) {
				
				result = true;
			}
		}
		return result;
	}

	/**
	 * Login menu checks to see if person is registered. 
	 * @param userList list of system users
	 * @return true or false
	 */
	public static boolean login(List<User> userList) {
		System.out.print("Enter your username: ");
		
		String input = userInput.next();
		boolean success = false;

		for(User u : userList) {
			if(u.getMyLoginName().equals(input)) {
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

		return new User(input,input2,input3);
	}
	
	public static void header() {
		System.out.println();
		System.out.println("---Conference Management Systems---");
		System.out.println("User: " + currentUser.getMyName());
		System.out.println("Conference: " + currentConference.getName());
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

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}// end of main

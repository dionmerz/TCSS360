package TCSS360;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;





/**
 * This class represents a conference object.
 * 
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 * @version 1.0 5/8/2016
 */
public class Main {

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
	private static TerminalUserInterface myUI;

	/**
	 * Runs the SMART Scientific Manuscripts Are Reviewed in Terminal program
	 * 
	 * @param theargs
	 */
	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] theargs) {

		try {
			FileInputStream fileIn = new FileInputStream("UI.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			myUI = (TerminalUserInterface) in.readObject();
			
		} catch (FileNotFoundException e) {
			myUI = new TerminalUserInterface();


		} catch (ClassNotFoundException e) {
			myUI = new TerminalUserInterface();
		} catch (IOException e) {

		}



		// First menu
		//TerminalUserInterface UI = new TerminalUserInterface();
		myUI.loginRegisterMenu();
	
		exit();


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
	/**
	 * UI exit menu
	 */
	public static void exit() {
		System.out.println("You selected exit.");

		try {
			FileOutputStream fileOut = new FileOutputStream("UI.ser");
			ObjectOutputStream output = new ObjectOutputStream(fileOut);
			output.writeObject(myUI);

			output.close();
			fileOut.close();

		} catch (IOException e) {
			
		}
	}
}// end of main

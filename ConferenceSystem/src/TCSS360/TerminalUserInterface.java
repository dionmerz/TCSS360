package TCSS360;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalUserInterface {
	
	private AuthorMenu myAuthorMenu;
	private ReviewerMenu myReviewerMenu;
	private SubprogramChairMenu mySubprogramChairMenu;
	private ProgramChairMenu myProgramChairMenu;
	private ArrayList<User> myUserList; 
	private ArrayList<Conference> myConferenceList;
	private User myCurrentUser;
	private Conference myCurrentConference;
	private boolean mySetupStatus;
	private Scanner myUserInput;
	
	public TerminalUserInterface() {
		myAuthorMenu = new AuthorMenu();
		myReviewerMenu = new ReviewerMenu();
		mySubprogramChairMenu = new SubprogramChairMenu();
		myProgramChairMenu = new ProgramChairMenu();
		myUserList = new ArrayList<User>();
		myConferenceList = new ArrayList<Conference>();
		myUserInput = new Scanner(System.in);
	}

}

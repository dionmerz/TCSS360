package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.Roles;
import model.SubprogramChair;
import model.User;
import model.Manuscript.Status;
import model.ProgramChair;

/**
 * This class tests the SubprogramChair class functionality.
 * @author Bernabe Guzman
 * @version 1.0 5/30/16
 */
public class SubprogramChairTest {

	private List<Conference> conferenceList;
	private List<User> userListMultipleUsers;
	private List<User> emptyUserList;
	private List<Roles> emptyRolesList;
	private Manuscript manuscript1;
	private Manuscript manuscript2;
	private Manuscript manuscript3;
	private Manuscript manuscript4;
	private Manuscript manuscript5;
	private List<Manuscript> listOneManuscript;
	private List<Manuscript> listFourManuscript;
	private User reviewerNoManuscriptsToReview;
	private User reviewerOneManuscriptToReview;
	private User reviewerFourManuscriptsToReview;
	private User reviewerUser;
	private User authorUser;
	private User programChairUser;
	private User subprogramChairUser;
	private Conference testConference;

	/**
	 * Sets up all objects used for testing. This method runs before each individual test. 
	 * @throws Exception if there exist any initialization errors
	 */
	@Before
	public void setUp() throws Exception {
		emptyRolesList = new ArrayList<Roles>();
		emptyUserList = new ArrayList<User>();
		userListMultipleUsers = new ArrayList<User>();
		
		manuscript1 = new Manuscript("test1.txt", "TestAuthor1", "SubmitDate", "TestTitle1");
		manuscript2 = new Manuscript("test2.txt", "TestAuthor2", "SubmitDate", "TestTitle2");
		manuscript3 = new Manuscript("test3.txt", "TestAuthor3", "SubmitDate", "TestTitle3");
		manuscript4 = new Manuscript("test4.txt", "TestAuthor4", "SubmitDate", "TestTitle4");
		manuscript5 = new Manuscript("test5.txt", "TestAuthor5", "SubmitDate", "TestTitle5");
		
		listOneManuscript = new ArrayList<Manuscript>();
		listOneManuscript.add(manuscript1);
		
		listFourManuscript = new ArrayList<Manuscript>();
		listFourManuscript.add(manuscript1);
		listFourManuscript.add(manuscript2);
		listFourManuscript.add(manuscript3);
		listFourManuscript.add(manuscript4);
		
		reviewerNoManuscriptsToReview  = new User("TestReviewer1", "ReviewerLogin1", "reviewer1@email.com");
		reviewerNoManuscriptsToReview.addMyRole(new Reviewer(testConference));
		reviewerOneManuscriptToReview  = new User("TestReviewer2", "ReviewerLogin2", "reviewer2@email.com",
													emptyRolesList, null, listOneManuscript, null, null);
		reviewerOneManuscriptToReview.addMyRole(new Reviewer(testConference));
		reviewerFourManuscriptsToReview  = new User("TestReviewer3", "ReviewerLogin3", "reviewer3@email.com",
													emptyRolesList, null, listFourManuscript, null, null);
		reviewerFourManuscriptsToReview.addMyRole(new Reviewer(testConference));
		reviewerUser = new User("TestReviewer4", "ReviewerLogin4", "reviewer4@email.com");
		reviewerUser.addMyRole(new Reviewer(testConference));
		
		authorUser = new User("TestAuthor", "AuthorLogin", "author@email.com");
		authorUser.addMyRole(new Author(testConference));
		
		programChairUser = new User("TestReviewer4", "ProgramChairLogin4", "programchair@email.com");
		programChairUser.addMyRole(new ProgramChair(testConference));
		
		subprogramChairUser = new User("TestSubprogramChair", "SubprogramLogin", "subprogram@email.com");
		subprogramChairUser.addMyRole(new SubprogramChair(testConference));
		
		testConference = new Conference("Conf1", reviewerNoManuscriptsToReview, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		testConference.addManuscript((Manuscript) manuscript1);
		
		conferenceList = new ArrayList<Conference>();
		conferenceList.add(testConference);
		
		userListMultipleUsers.add(reviewerNoManuscriptsToReview);
		userListMultipleUsers.add(reviewerOneManuscriptToReview);
		userListMultipleUsers.add(reviewerFourManuscriptsToReview);
		userListMultipleUsers.add(subprogramChairUser);
	}
	
	//-------------SubprogramChair class constructor role test partition---------------------------
	/**
	* Tests the Subprogram Chair class constructor. 
    */
	@Test
	public void testSubprogramChairConstructor() {
		SubprogramChair testSubprogramChairRole = new SubprogramChair(testConference);
		assertEquals(testSubprogramChairRole.getConference().getName(), "Conf1");
		assertEquals(testSubprogramChairRole.getClass(), SubprogramChair.class);
	}

	//-----------------assignReviewerManuscript test partitions--------------------------------
	/**
	 * Tests assignReviewerManuscript by assigning a reviewer with no manuscripts a manuscript. 
	 */
	@Test
	public void testAssignReviewerManuscriptReviewerNoManuscriptsToReview() {
		assertTrue(reviewerNoManuscriptsToReview.getMyManuscriptsToReview().isEmpty());
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerNoManuscriptsToReview, manuscript1);
		assertEquals(reviewerNoManuscriptsToReview.getMyManuscriptsToReview().size(), 1);
		assertEquals(reviewerNoManuscriptsToReview.getMyManuscriptsToReview().get(0).getTitle(), "TestTitle1");
	}
	
	/**
	 * Tests assignReviewerManuscript by assigning a reviewer with one manuscripts a manuscript. 
	 */
	@Test
	public void testAssignReviewerManuscriptReviewerOneManuscriptToReview() {
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().size(), 1);
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerOneManuscriptToReview, manuscript2);
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().size(), 2);
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().get(1).getTitle(), "TestTitle2");
	}
	
	/**
	 * Tests assignReviewerManuscript by assigning a reviewer with four manuscripts a manuscript. This 
	 * cover business rule 10 ""
	 */
	@Test
	public void testAssignReviewerManuscriptReviewerWithFourManuscriptsToReview() {
		assertEquals(reviewerFourManuscriptsToReview.getMyManuscriptsToReview().size(), 4);
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerFourManuscriptsToReview, manuscript5);
		assertEquals(reviewerFourManuscriptsToReview.getMyManuscriptsToReview().get(3).getTitle(), "TestTitle4");
		assertEquals(reviewerFourManuscriptsToReview.getMyManuscriptsToReview().size(), 4);
	}
	
	/**
	 * Tests assignReviewerManuscript by assigning a reviewer with self authored manuscript 
	 */
	@Test
	public void testAssignReviewerManuscriptReviewerSelfAuthoredManuscript() {
		reviewerOneManuscriptToReview.setMyName("TestAuthor1");
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().size(), 1);
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerOneManuscriptToReview, manuscript1);
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().get(0).getTitle(), "TestTitle1");
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().size(), 1);
	}
	
	//-----------------appendRecommendationToManuscript test partition--------------------------------
	/**
	 * Tests append recommendation to manuscript by checking the recommendation form list and manuscript status
	 */
	@Test
	public void testAppendRecommendationToManuscript() {
		assertTrue(manuscript1.getRecomFormList().size() == 0);
		assertTrue(manuscript1.getStatus() == Status.SUBMITTED);
		subprogramChairUser.findSubprogramChairRole().appendRecomendationToManuscript(reviewerOneManuscriptToReview, testConference, 
				manuscript1, 5, "recommend.txt", "PaperRecommendation");
		assertTrue(manuscript1.getRecomFormList().size() == 1);
		assertTrue(manuscript1.getStatus() == Status.RECOMMENDED);
	}

	//-----------------getListOfReviewers test partitions--------------------------------
	/**
	 * Test the getListOfReviewers method with an empty list of users.
	 */
	@Test
	public void testGetListOfReviewersWithEmptyList() {
		assertTrue(subprogramChairUser.findSubprogramChairRole().getListOfReviewersFromListOfUsers(emptyUserList).isEmpty());
	}
	
	/**
	 * Test the getListOfReviewers method with a list of multiple users.
	 */
	@Test
	public void testGetListOfReviewersWithUserListMultiplerUsers() {
		List<User> reviewerList = subprogramChairUser.findSubprogramChairRole().getListOfReviewersFromListOfUsers(userListMultipleUsers);
		assertEquals(reviewerList.size(), 3);
		for (User user : reviewerList) {
			assertEquals(user.findReviewerRole().getClass(), Reviewer.class);
		}
	}
	
	//-----------------designateReviewer test partitions--------------------------------
	/**
	 * Test designateReviewer method for a user with reviewer role
	 */
	@Test 
	public void testDesignateUserAsReviewerWithReviewer() {
		subprogramChairUser.findSubprogramChairRole().designateReviewer(reviewerUser);
		assertEquals(reviewerUser.getMyRoles().size(), 1);
		assertEquals(reviewerUser.findReviewerRole().getClass(), Reviewer.class);
	}
	
	/**
	 * Test designateReviewer method for a user with author role
	 */
	@Test 
	public void testDesignateUserAsReviewerWithAuthor() {
		subprogramChairUser.findSubprogramChairRole().designateReviewer(authorUser);
		assertEquals(authorUser.getMyRoles().size(), 2);
		assertEquals(authorUser.findReviewerRole().getClass(), Reviewer.class);
	}
	
	/**
	 * Test designateReviewer method for a user with program chair role
	 */
	@Test 
	public void testDesignateUserAsReviewerWithProgramChair() {
		subprogramChairUser.findSubprogramChairRole().designateReviewer(programChairUser);
		assertEquals(programChairUser.getMyRoles().size(), 2);
		assertEquals(programChairUser.findReviewerRole().getClass(), Reviewer.class);
	}
	
	/**
	 * Test designateReviewer method for a user with subprogram chair role
	 */
	@Test 
	public void testDesignateUserAsReviewerWithSubprogramChair() {
		subprogramChairUser.findSubprogramChairRole().designateReviewer(subprogramChairUser);
		assertEquals(subprogramChairUser.getMyRoles().size(), 2);
		assertEquals(subprogramChairUser.findReviewerRole().getClass(), Reviewer.class);
	}
	
}

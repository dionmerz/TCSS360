package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.Roles;
import model.SubprogramChair;
import model.User;
import model.Manuscript.Status;

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
	private User subprogramChairUser;
	private Conference conference;

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
		reviewerNoManuscriptsToReview.addMyRole(new Reviewer(conference));
		reviewerOneManuscriptToReview  = new User("TestReviewer2", "ReviewerLogin2", "reviewer2@email.com",
													emptyRolesList, null, listOneManuscript, null, null);
		reviewerOneManuscriptToReview.addMyRole(new Reviewer(conference));
		reviewerFourManuscriptsToReview  = new User("TestReviewer3", "ReviewerLogin3", "reviewer3@email.com",
													emptyRolesList, null, listFourManuscript, null, null);
		reviewerFourManuscriptsToReview.addMyRole(new Reviewer(conference));
		
		subprogramChairUser = new User("TestSubprogramChair", "SubprogramLogin", "subprogram@email.com");
		subprogramChairUser.addMyRole(new SubprogramChair(conference));
		
		conference = new Conference("Conf1", reviewerNoManuscriptsToReview, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		conference.addManuscript((Manuscript) manuscript1);
		
		conferenceList = new ArrayList<Conference>();
		conferenceList.add(conference);
		
		userListMultipleUsers.add(reviewerNoManuscriptsToReview);
		userListMultipleUsers.add(reviewerOneManuscriptToReview);
		userListMultipleUsers.add(reviewerFourManuscriptsToReview);
		userListMultipleUsers.add(subprogramChairUser);
	}

//-----------------assignReviewerManuscript test partition--------------------------------
	@Test
	public void testAssignReviewerManuscriptReviewerNoManuscriptsToReview() {
		assertTrue(reviewerNoManuscriptsToReview.getMyManuscriptsToReview().isEmpty());
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerNoManuscriptsToReview, manuscript1);
		assertEquals(reviewerNoManuscriptsToReview.getMyManuscriptsToReview().size(), 1);
		assertEquals(reviewerNoManuscriptsToReview.getMyManuscriptsToReview().get(0).getTitle(), "TestTitle1");
	}
	
	@Test
	public void testAssignReviewerManuscriptReviewerOneManuscriptToReview() {
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().size(), 1);
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerOneManuscriptToReview, manuscript2);
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().size(), 2);
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().get(1).getTitle(), "TestTitle2");
	}
	
	@Test
	public void testAssignReviewerManuscriptReviewerWithFourManuscriptsToReview() {
		assertEquals(reviewerFourManuscriptsToReview.getMyManuscriptsToReview().size(), 4);
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerFourManuscriptsToReview, manuscript5);
		assertEquals(reviewerFourManuscriptsToReview.getMyManuscriptsToReview().get(3).getTitle(), "TestTitle4");
		assertEquals(reviewerFourManuscriptsToReview.getMyManuscriptsToReview().size(), 4);
	}
	
	@Test
	public void testAssignReviewerManuscriptReviewerSelfAuthoredManuscript() {
		reviewerOneManuscriptToReview.setMyName("TestAuthor1");
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().size(), 1);
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerOneManuscriptToReview, manuscript1);
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().get(0).getTitle(), "TestTitle1");
		assertEquals(reviewerOneManuscriptToReview.getMyManuscriptsToReview().size(), 1);
	}
	
	@Test
	public void testAppendRecommendationToManuscript() {
		assertTrue(manuscript1.getRecomFormList().size() == 0);
		assertTrue(manuscript1.getStatus() == Status.SUBMITTED);
		subprogramChairUser.findSubprogramChairRole().appendRecomendationToManuscript(reviewerOneManuscriptToReview, conference, 
				manuscript1, 5, "recommend.txt", "PaperRecommendation");
		assertTrue(manuscript1.getRecomFormList().size() == 1);
		assertTrue(manuscript1.getStatus() == Status.RECOMMENDED);
	}

	@Test
	public void testGetListOfReviewersWithEmptyList() {
		assertTrue(subprogramChairUser.findSubprogramChairRole().getListOfReviewersFromListOfUsers(emptyUserList).isEmpty());
	}
	
	@Test
	public void testGetListOfReviewersWithUserListMultiplerUsers() {
		List<User> reviewerList = subprogramChairUser.findSubprogramChairRole().getListOfReviewersFromListOfUsers(userListMultipleUsers);
		assertEquals(reviewerList.size(), 3);
		for (User user : reviewerList) {
			assertEquals(user.findReviewerRole().getClass(), Reviewer.class);
		}
	}
	
}

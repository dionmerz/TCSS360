package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TCSS360.Conference;
import TCSS360.Manuscript;
import TCSS360.Manuscript.Status;
import TCSS360.Reviewer;
import TCSS360.SubprogramChair;
import TCSS360.User;

public class SubprogramChairTest {

	private List<Conference> conferenceList;
	private Manuscript manuscript1;
	private Manuscript manuscript2;
	private Manuscript manuscript3;
	private Manuscript manuscript4;
	private Manuscript manuscript5;
	private User reviewerNoManuscriptsToReview;
	private User reviewerOneManuscriptToReview;
	private User reviewerFourManuscriptsToReview;
	private User subprogramChairUser;
	private Conference conference;

	@Before
	public void setUp() throws Exception {
		
		manuscript1 = new Manuscript("test1.txt", "TestAuthor1", "SubmitDate", "TestTitle1");
		manuscript2 = new Manuscript("test2.txt", "TestAuthor2", "SubmitDate", "TestTitle2");
		manuscript3 = new Manuscript("test3.txt", "TestAuthor3", "SubmitDate", "TestTitle3");
		manuscript4 = new Manuscript("test4.txt", "TestAuthor4", "SubmitDate", "TestTitle4");
		manuscript5 = new Manuscript("test5.txt", "TestAuthor5", "SubmitDate", "TestTitle5");
		
		reviewerNoManuscriptsToReview  = new User("TestReviewer1", "ReviewerLogin1", "reviewer1@email.com");
		reviewerNoManuscriptsToReview.addMyRole(new Reviewer(conference));
		reviewerOneManuscriptToReview  = new User("TestReviewer2", "ReviewerLogin2", "reviewer2@email.com");
		reviewerOneManuscriptToReview.addMyRole(new Reviewer(conference));
		reviewerFourManuscriptsToReview  = new User("TestReviewer3", "ReviewerLogin3", "reviewer3@email.com");
		reviewerFourManuscriptsToReview.addMyRole(new Reviewer(conference));
		
		subprogramChairUser = new User("TestSubprogramChair", "SubprogramLogin", "subprogram@email.com");
		subprogramChairUser.addMyRole(new SubprogramChair(conference));
		
		conference = new Conference("Conf1", reviewerNoManuscriptsToReview, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		conference.addManuscript((Manuscript) manuscript1);
		
		conferenceList = new ArrayList<Conference>();
		conferenceList.add(conference);
	}

	@Test
	public void testAssignReviewerManuscript_ReviewerNoManuscriptsToReview() {
		assertTrue(reviewerNoManuscriptsToReview.getMyManuscriptsToReview().size() == 0);
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerNoManuscriptsToReview, manuscript1);
		assertTrue(reviewerNoManuscriptsToReview.getMyManuscriptsToReview().size() == 1);
		
		manuscript1 = new Manuscript("test.txt", "TempUser", "SubmitDate", "TestTitle");
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerNoManuscriptsToReview, manuscript1);
		assertFalse(reviewerNoManuscriptsToReview.getMyManuscriptsToReview().size() > 1);
		
		manuscript1 = new Manuscript("test.txt", "OtherUser", "SubmitDate", "TestTitle");
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerNoManuscriptsToReview, manuscript2);
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerNoManuscriptsToReview, manuscript3);
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerNoManuscriptsToReview, manuscript4);
		subprogramChairUser.findSubprogramChairRole().assignReviewerManuscript(reviewerNoManuscriptsToReview, manuscript5);
		
		assertTrue(reviewerNoManuscriptsToReview.getMyManuscriptsToReview().size() == 4);
	}
	
	@Test
	public void testAssignReviewerManuscript_ReviewerOneManuscriptToReview() {
		
	}
	
	@Test
	public void testAssignReviewerManuscript_ReviewerFourManuscriptsToReview() {
	
	}
	
	@Test
	public void testAssignReviewerManuscript_ReviewerSelfAuthoredManuscript() {
		
	}
	
	@Test
	public void testSubmitRecommendation() {
		assertTrue(manuscript2.getRecomFormList().size() == 0);
		subprogramChairUser.findSubprogramChairRole().submitRecomendation(reviewerNoManuscriptsToReview, conference, manuscript2, 5, "recommend.txt", "PaperRecommendation");
		
		assertTrue(manuscript2.getRecomFormList().size() == 1);
		
		assertTrue(manuscript2.getStatus() == Status.RECOMMENDED);
	}

}

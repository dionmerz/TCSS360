package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.Paper;
import model.ProgramChair;
import model.ReviewForm;
import model.Reviewer;
import model.SubprogramChair;
import model.User;
import model.Manuscript.Status;

public class UserTest {
	private Paper script1;
	private Paper script2;
	private Paper script3;
	private Paper script4;
	private User usr;
	private User rev1;
	private User rev2;
	private Reviewer reviewer1;
	private Reviewer reviewer2;
	private Conference conf;
	

	@Before
	public void setUp() throws Exception {
		new ArrayList<Conference>();
		script1 = new Manuscript("test.txt", "TestAuthor", "SubmitDate", "TestTitle1");
		script2 = new Manuscript("test2.txt", "TestAuthor", "SubmitDate", "TestTitle2");
		usr  = new User("TempUser", "UserLogin", "User@email.com");
		conf = new Conference("Conf1", usr, "start", "stop", "PDeadline", "RDeadline", 10, 10);
		conf.addManuscript((Manuscript) script1);
		rev1  = new User("TempRev1", "UserLogin", "User@email.com");
		rev2 = new User("TempRev2", "UserLogin", "User@email.com");
		reviewer1 = new Reviewer(conf);
		reviewer2 = new Reviewer(conf);
		rev1.addMyRole(reviewer1);
		rev2.addMyRole(reviewer2);
		script3 = new ReviewForm("test3.txt", "TestAuthor", "SubmitDate", "TestTitle1", rev1);
		script4 = new ReviewForm("test4.txt", "TestAuthor", "SubmitDate", "TestTitle2", rev2);
		
	}

	@Test
	public void testGetMyName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMyName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyLoginName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMyLoginName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMyEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyManuscripts() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMyManuscript() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveManuscript() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSubProgManuscript() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddSubProgManuscript() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyRoles() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMyRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMyRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyManuscriptsToReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMyManuscriptsToReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMyManuscriptsToReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyReviews() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubmitManuscript() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAuthorRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindProgramChairRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindSubprogramChairRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindReviewerRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasRole() {
		fail("Not yet implemented");
	}

}

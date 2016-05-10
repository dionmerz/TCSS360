package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import TCSS360.Author;
import TCSS360.Conference;
import TCSS360.Manuscript;
import TCSS360.Manuscript.Status;
import TCSS360.Paper;
import TCSS360.ProgramChair;
import TCSS360.ReviewForm;
import TCSS360.Reviewer;
import TCSS360.SubprogramChair;
import TCSS360.User;

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
	public void testName() {
		assertTrue(usr.getMyName().equals("TempUser"));
		usr.setMyName("newUserName");
		assertTrue(usr.getMyName().equals("newUserName"));
	}
	
	@Test
	public void testLoginName() {
		assertTrue(usr.getMyLoginName().equals("UserLogin"));
		usr.setMyLoginName("newLoginName");
		assertTrue(usr.getMyLoginName().equals("newLoginName"));
	}

	@Test
	public void testEmail() {
		assertTrue(usr.getMyEmail().equals("User@email.com"));
		usr.setMyEmail("newEmail");
		assertTrue(usr.getMyEmail().equals("newEmail"));
	}
	
	@Test 
	public void testAuthoredManuscripts() {
		assertEquals(usr.getMyManuscripts().size(),0);
		usr.addMyManuscript((Manuscript)script1);
		usr.addMyManuscript((Manuscript)script2);
		assertEquals(usr.getMyManuscripts().size(), 2);
		assertTrue(usr.getMyManuscripts().get(0).getPath().equals("test.txt"));
		assertTrue(usr.getMyManuscripts().get(1).getPath().equals("test2.txt"));
	}
	
	@Test 
	public void testSubProgManuscripts() {
		assertEquals(usr.getSubProgManuscript().size(),0);
		usr.addSubProgManuscript((Manuscript)script1);
		usr.addSubProgManuscript((Manuscript)script2);
		assertEquals(usr.getSubProgManuscript().size(), 2);
		assertTrue(usr.getSubProgManuscript().get(0).getPath().equals("test.txt"));
		assertTrue(usr.getSubProgManuscript().get(1).getPath().equals("test2.txt"));
	}
	
	@Test 
	public void testReviews() {
		assertEquals(usr.getMyReviews().size(),0);
		usr.addReview((ReviewForm) script3);
		usr.addReview((ReviewForm) script4);
		assertEquals(usr.getMyReviews().size(), 2);
		assertTrue(usr.getMyReviews().get(0).getPath().equals("test3.txt"));
		assertTrue(usr.getMyReviews().get(1).getPath().equals("test4.txt"));
	}
	
	@Test
	public void testSubmitManuscript() {
		usr.addMyManuscript((Manuscript)script1);
		usr.addMyManuscript((Manuscript)script2);
		assertEquals(usr.getMyManuscripts().get(0).getStatus(), Status.SUBMITTED);
		usr.submitManuscript("submitManuscript.txt", "SUBMITMANUSCRITPT", usr, conf);
		assertEquals(usr.getMyManuscripts().size(), 3);
	}
	
	@Test
	public void testFindAuthorRole() {
		User user  = new User("TempAuthor", "UserLogin", "User@email.com");
		Author author = new Author(conf);
		user.addMyRole(author);
		assertEquals(user.findAuthorRole(), author);
	}
	
	@Test
	public void testFindProgramChariRole() {
		User user  = new User("TempAuthor", "UserLogin", "User@email.com");
		ProgramChair programchair = new ProgramChair(conf);
		user.addMyRole(programchair);
		assertEquals(user.findProgramChairRole(), programchair);
	}
	
	@Test
	public void testFindSubProgramChairRole() {
		User user  = new User("TempAuthor", "UserLogin", "User@email.com");
		SubprogramChair subprog = new SubprogramChair(conf);
		user.addMyRole(subprog);
		assertEquals(user.findSubprogramChairRole(), subprog);
	}
	
	@Test
	public void testFindReviewerRole() {
		User user  = new User("TempAuthor", "UserLogin", "User@email.com");
		Reviewer reviewer = new Reviewer(conf);
		user.addMyRole(reviewer);
		assertEquals(user.findReviewerRole(), reviewer);
	}
	
	@Test
	public void testEquals() {
		User temp = new User("TempRev1", "UserLogin", "User@email.com");
		User temp2 = new User("TempRev3", "UserLogin3", "User@email.com3");
		assertTrue(rev1.equals(rev1)); 
		assertTrue(rev1.equals(temp));
		assertFalse(rev1.equals(temp2));
		assertFalse(rev1.equals(null));
	}
	
}

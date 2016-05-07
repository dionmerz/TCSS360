package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TCSS360.Author;
import TCSS360.Conference;
import TCSS360.Manuscript;
import TCSS360.Paper;
import TCSS360.ReviewForm;
import TCSS360.Reviewer;
import TCSS360.SubprogramChair;
import TCSS360.User;

public class ReviewerTest {

	private List<Conference> confList;
	private Manuscript script;
	private Paper script2;
	private Paper form;
	private User usr;
	private Reviewer rev;
	private SubprogramChair spc;
	private Conference conf;

	@Before
	public void setUp() throws Exception {
		confList = new ArrayList<Conference>();
		script = new Manuscript("test.txt", "TestAuthor", "SubmitDate", "TestTitle");
		script2 = new Manuscript("test2.txt", "TestAuthor", "SubmitDate", "TestTitle");
		form = new ReviewForm("review.txt", "TestUser", "Date", "TestTitle", usr);
		usr  = new User("TempUser", "UserLogin", "User@email.com");
		conf = new Conference("Conf1", usr, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		conf.addManuscript((Manuscript) script);
		rev = new Reviewer(conf);
		spc = new SubprogramChair(conf);
		
		confList.add(conf);
	}

	/**
	 * Tests the upload process of a review form
	 * First tests a failed attempt if the Reviewer has not been assigned the paper.
	 * Then tests if the Reviewer has been assigned.
	 */
	@Test
	public void testUploadReviewForm() {
		
		conf = new Conference("Conf1", usr, "start", "stop", "PDeadline", "RDeadline", 0, 0);
		assertTrue(usr.getMyReviews().size() == 0);
		rev.uploadReviewForm(conf, usr, "form.txt", "TestReviewer", "ReviewTitle", (Manuscript) script);
		assertFalse(usr.getMyReviews().size() > 0);
		
		conf = new Conference("Conf1", usr, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		
		rev.uploadReviewForm(conf, usr, "form.txt", "TestReviewer", "ReviewTitle", (Manuscript) script);
		assertFalse(usr.getMyReviews().size() > 0);
		
		
		spc.assignReviewerManuscript(usr, (Manuscript) script);
		
		rev.uploadReviewForm(conf, usr, "form.txt", "TestReviewer", "ReviewTitle", (Manuscript) script);
		assertTrue(usr.getMyReviews().size() == 1);
		assertTrue(script.getReviewList().get(0).getAuthor().equals("TestReviewer"));
		
		
	}

}

package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Conference;
import model.Manuscript;
import model.Reviewer;
import model.SubprogramChair;
import model.User;

public class ReviewerTest {

	private List<Conference> confList;
	private Manuscript script;
	private User usr;
	private Reviewer rev;
	private SubprogramChair spc;
	private Conference conf;

	@Before
	public void setUp() throws Exception {
		confList = new ArrayList<Conference>();
		script = new Manuscript("test.txt", "TestAuthor", "SubmitDate", "TestTitle");
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
		rev.uploadReviewForm(usr, conf, "form.txt", "TestReviewer", "ReviewTitle", (Manuscript) script);
		assertFalse(usr.getMyReviews().size() > 0);
		
		conf = new Conference("Conf1", usr, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		
		rev.uploadReviewForm(usr, conf, "form.txt", "TestReviewer", "ReviewTitle", (Manuscript) script);
		assertFalse(usr.getMyReviews().size() > 0);
		
		
		spc.assignReviewerManuscript(usr, (Manuscript) script);
		
		rev.uploadReviewForm(usr, conf, "form.txt", "TestReviewer", "ReviewTitle", (Manuscript) script);
		assertTrue(usr.getMyReviews().size() == 1);
		assertTrue(script.getReviewList().get(0).getAuthor().equals("TestReviewer"));
		
		
	}

}

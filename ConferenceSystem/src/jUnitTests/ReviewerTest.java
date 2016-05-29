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
	private User testUser;
	private Reviewer testReviewer;
	private SubprogramChair testSubprogramChair;
	private Conference testConference;

	@Before
	public void setUp() throws Exception {
		confList = new ArrayList<Conference>();
		script = new Manuscript("test.txt", "TestAuthor", "SubmitDate", "TestTitle");
		testUser  = new User("TempUser", "UserLogin", "User@email.com");
		testConference = new Conference("Conf1", testUser, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		testConference.addManuscript((Manuscript) script);
		testReviewer = new Reviewer(testConference);
		testSubprogramChair = new SubprogramChair(testConference);
		confList.add(testConference);
	}
	/**
	 * Tests if the Reviewer attempts to upload a review form
	 * without being assigned the manuscript to review.
	 */
	@Test
	public void testUploadReviewFormWithoutBeingAssigned() {
		assertTrue(testUser.getMyReviews().size() == 0);
		testReviewer.uploadReviewForm(testUser, testConference, "form.txt", "TestReviewer", "ReviewTitle", (Manuscript) script);
		assertFalse(testUser.getMyReviews().size() > 0);
	}

	/**
	 * Tests the upload process of a review form.
	 * Assigns the a manuscript to review then
	 * tests to see if the reviewer can upload a review form.
	 * This test also checks if the deadline for uploading a review
	 * form has not passed.
	 */
	@Test
	public void testUploadReviewFormWithBeingAssigned() {			
		testSubprogramChair.assignReviewerManuscript(testUser, (Manuscript) script);
		testReviewer.uploadReviewForm(testUser, testConference, "form.txt", "TestReviewer", "ReviewTitle", (Manuscript) script);
		assertTrue(testUser.getMyReviews().size() == 1);
		assertTrue(script.getReviewList().get(0).getAuthor().equals("TestReviewer"));
			
	}
	/**
	 * Tests the upload process of a review form if
	 * the review deadline date has passed.
	 */
	@Test
	public void testUploadReviewFormAfterReviewDeadline() {
		Conference pastDueConference = new Conference("TestConference", testUser, "start", "stop", "PDeadline", "RDeadline", 0, 30);
		testSubprogramChair.assignReviewerManuscript(testUser, (Manuscript) script);
		assertTrue(testUser.getMyReviews().size() == 0);
		testReviewer.uploadReviewForm(testUser, pastDueConference, "form.txt", "TestReviewer", "ReviewTitle", (Manuscript) script);
		assertTrue(testUser.getMyReviews().size() == 0);
	}

}

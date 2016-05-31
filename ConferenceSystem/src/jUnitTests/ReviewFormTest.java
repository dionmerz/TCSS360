package jUnitTests;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import model.ReviewForm;
import model.User;

/**
 * Test class for review form.
 * 
 * @author Bincheng Li
 * @version 1.0
 */
public class ReviewFormTest {
	private ReviewForm testReviewForm;
	private static final String TEST_PATH = "Test Path";
	private static final String TEST_AUTHOR = "Test Author";
	private static final String TEST_DATE = "05/06/2016";
	private static final String TEST_TITLE = "Test Title";
	private static final String TEST_NAME = "Test name";
	private static final String TEST_LOGIN_NAME = "Login Name";
	private static final String TEST_EMAIL = "Test email";
	private User testReviewer;
	
	
	@Before
	public void setupTests() {
		// reviewer for the test
		testReviewer = new User(TEST_NAME, TEST_LOGIN_NAME, TEST_EMAIL);
		// review form for the test
		testReviewForm = new ReviewForm(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE, testReviewer);
	}
	
	/**
	 * Check the constructor for review form works or not.
	 */
	@Test
	public void testConstructor() {
		String testPath = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\" + TEST_PATH;
		assertTrue(testReviewForm.getPath().equals(testPath));
		assertTrue(testReviewForm.getAuthor().equals(TEST_AUTHOR));
		assertTrue(testReviewForm.getSubmittedDateString().equals(TEST_DATE));
		assertTrue(testReviewForm.getTitle().equals(TEST_TITLE));
	}
	
	/**
	 * Check get reviewer is working properly.
	 */
	@Test
	public void testGetReviewer(){
		
		assertTrue(testReviewForm.getReviewer().equals(testReviewer));
	}
	
	/**
	 * Check set reviewer is working properly.
	 */
	@Test
	public void testSetReviewer(){
		User testReviewer2 = new User (TEST_NAME, "Login Name 2", TEST_EMAIL);
		testReviewForm.setReviewer(testReviewer2);
		assertFalse(testReviewForm.getReviewer().equals(testReviewer));
	}
}


package jUnitTests;

import static org.junit.Assert.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Manuscript;
import model.Manuscript.Status;
import model.RecommendationForm;
import model.ReviewForm;
import model.User;

/**
 * Test class for manuscript.
 * 
 * @author Bincheng Li
 * @version 1.0
 */
public class ManuscriptTest {
	
	private Manuscript testManuscript;
	private Manuscript testManuscript2;
	private Manuscript testManuscript3;
	private Manuscript testManuscript4;
	private static final String TEST_PATH = "Test Path";
	private static final String TEST_AUTHOR = "Test Author";
	private static final String TEST_DATE = "05/06/2016";
	private static final String TEST_TITLE = "Test Title";
	private static final String TEST_NAME = "Test name";
	private static final String TEST_LOGIN_NAME = "Login Name";
	private static final String TEST_EMAIL = "Test email";
	private List<ReviewForm> testReviewFormList;
	private List<User> testReviewerList;
	private List<RecommendationForm> testRecommendationFormList;
	private ReviewForm testReviewForm;
	private RecommendationForm testRecommendationForm;
	private User testReviewer;
	private Status testStatus;
	
	@Before
	public void setupTests() {
		//Set up default test manuscript
		
		testStatus = Status.SUBMITTED;
		testRecommendationFormList = new ArrayList<RecommendationForm>();
		testReviewerList = new ArrayList<User>();
		testReviewFormList = new ArrayList<ReviewForm>();
		testReviewer = new User(TEST_NAME, TEST_LOGIN_NAME, TEST_EMAIL);
		testReviewForm = new ReviewForm(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE, testReviewer);
		testRecommendationForm = new RecommendationForm(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE, 5);
		testRecommendationFormList.add(testRecommendationForm);
		testReviewerList.add(testReviewer);
		testReviewFormList.add(testReviewForm);
		testManuscript = new Manuscript(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE, testStatus, 
										testRecommendationFormList, testReviewerList, testReviewFormList);
	}
	
	/**
	 * Check the constructor for manuscript works or not.
	 */
	@Test
	public void testConstructor() {
		String testPath = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\" + TEST_PATH;
		assertTrue(testManuscript.getPath().toString().equals(testPath));
		assertTrue(testManuscript.getAuthor().equals(TEST_AUTHOR));
		assertTrue(testManuscript.getSubmittedDateString().equals(TEST_DATE));
		assertTrue(testManuscript.getTitle().equals(TEST_TITLE));
	}
	
	/**
	 * Tests add a reviewer for a manuscript works or not. 
	 */
	@Test
	public void testAddReviewerList(){
		int size = testManuscript.getReviewerList().size();
		testManuscript.addReviewer(testReviewer);
		assertTrue(testManuscript.getReviewerList().size() - size == 1);
	}
	
	/**
	 * Test add a review from for a manuscript works or not.
	 */
	@Test
	public void testAddReviewFormList(){
		int size = testManuscript.getReviewList().size();
		testManuscript.addReviewForm(testReviewForm);
		assertTrue(testManuscript.getReviewList().size() - size == 1);
	}
	
	/**
	 * Test add a recommendation form for a manuscript works or not.
	 */
	@Test
	public void testAddRecommendationFormList(){
		int size = testManuscript.getRecomFormList().size();
		testManuscript.getRecomFormList().add(testRecommendationForm);
		assertTrue(testManuscript.getRecomFormList().size() - size == 1);
	}
	
	/**
	 * Test get reviewer List is working properly.
	 */
	@Test
	public void testGetReviewerList(){
		assertTrue(testReviewerList.equals(testManuscript.getReviewerList()));
	}
	
	/**
	 * Test get review List is working properly.
	 */
	@Test
	public void testGetReviewList(){
		assertTrue(testReviewFormList.equals(testManuscript.getReviewList()));
	}
	
	/**
	 * Test get recommendation form List is working properly.
	 */
	@Test
	public void testGetRecommendationFormList(){
		assertTrue(testRecommendationFormList.equals(testManuscript.getRecomFormList()));
	}
	
	/**
	 * Test get status is working properly.
	 */
	@Test
	public void testGetStatus(){
		assertTrue(testStatus == testManuscript.getStatus());
	}
	
	/**
	 * Test set status is working properly.
	 */
	@Test
	public void testSetStatus() {
		testManuscript.setStatus(Status.ACCEPTED);
		assertFalse(testStatus == testManuscript.getStatus());
	}
}

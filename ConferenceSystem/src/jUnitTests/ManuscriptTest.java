package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Manuscript;
import model.RecommendationForm;
import model.ReviewForm;
import model.User;

public class ManuscriptTest {
	
	private Manuscript testManuscript;
	private Manuscript testManuscript2;
	private Manuscript testManuscript3;
	private Manuscript testManuscript4;
	private static final String TEST_PATH = "Test Path";
	private static final String TEST_AUTHOR = "Test Author";
	private static final String TEST_DATE = "05/06/2016";
	private static final String TEST_TITLE = "Test Title";
	private static final String TEST_AUTHOR2 = "Test Author2";
	private static final String TEST_TITLE2 = "Test Title2";
	private static final String TEST_NAME = "Test name";
	private static final String TEST_LOGIN_NAME = "Login Name";
	private static final String TEST_EMAIL = "Test email";
	private List<ReviewForm> testReviewFormList;
	private List<User> testReviewerList;
	private List<RecommendationForm> testRecommendationFormList;
	private ReviewForm testReviewForm;
	private RecommendationForm testRecommendationForm;
	private User testReviewer;
	
	@Before
	public void setupTests() {
		testManuscript = new Manuscript(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE);
		testManuscript2 = new Manuscript(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE);
		testManuscript3 = new Manuscript(TEST_PATH, TEST_AUTHOR2, TEST_DATE, TEST_TITLE);
		testManuscript4 = new Manuscript(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE2);
		testRecommendationFormList = new ArrayList<RecommendationForm>();
		testReviewerList = new ArrayList<User>();
		testReviewFormList = new ArrayList<ReviewForm>();
		testReviewer = new User(TEST_NAME, TEST_LOGIN_NAME, TEST_EMAIL);
		testReviewForm = new ReviewForm(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE, testReviewer);
		testRecommendationForm = new RecommendationForm(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE, 5);
	}
	
	@Test
	public void testConstructor() {
		assertTrue(testManuscript.getPath().equals(TEST_PATH));
		assertTrue(testManuscript.getAuthor().equals(TEST_AUTHOR));
		assertTrue(testManuscript.getSubmittedDateString().equals(TEST_DATE));
		assertTrue(testManuscript.getTitle().equals(TEST_TITLE));
	}
	
	@Test
	public void testAddReviewerList(){
		int size = testReviewerList.size();
		testReviewerList.add(testReviewer);
		assertTrue(testReviewerList.size() - size == 1);
	}
	
	@Test
	public void testAddReviewFormList(){
		int size = testReviewFormList.size();
		testReviewFormList.add(testReviewForm);
		assertTrue(testReviewFormList.size() - size == 1);
	}
	
	@Test
	public void testAddRecommendationFormList(){
		int size = testRecommendationFormList.size();
		testRecommendationFormList.add(testRecommendationForm);
		assertTrue(testRecommendationFormList.size() - size == 1);
	}
	
	@Test
	public void testEquals(){
		assertTrue(testManuscript.equals(testManuscript2));
		assertFalse(testManuscript.equals(testManuscript3));
		assertFalse(testManuscript.equals(testManuscript4));
		assertFalse(testManuscript.equals(null));
	}
}

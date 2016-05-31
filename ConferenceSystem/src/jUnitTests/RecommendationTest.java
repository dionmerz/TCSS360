package jUnitTests;


import static org.junit.Assert.*;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import model.RecommendationForm;

/**
 * Test class for recommendation form.
 * 
 * @author Bincheng Li
 * @version 1.0
 */
public class RecommendationTest {
	private RecommendationForm testRecommendation;
	private static final String TEST_PATH = "Test Path";
	private static final String TEST_AUTHOR = "Test Author";
	private static final String TEST_DATE = "05/06/2016";
	private static final String TEST_TITLE = "Test Title";
	private static final int TEST_SCORE = 5;
	
	@Before
	public void setupTests() {
		// recommedation form for the test.
		testRecommendation = new RecommendationForm(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE, TEST_SCORE);
	}
	
	/**
	 * Check the constructor for recommendation form works or not.
	 */
	@Test
	public void testConstructor() {
		String testPath = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\" + TEST_PATH;
		assertTrue(testRecommendation.getPath().equals(testPath));
		assertTrue(testRecommendation.getAuthor().equals(TEST_AUTHOR));
		assertTrue(testRecommendation.getSubmittedDateString().equals(TEST_DATE));
		assertTrue(testRecommendation.getTitle().equals(TEST_TITLE));
		assertTrue(testRecommendation.getScore() == TEST_SCORE);
	}
	
	/**
	 * Check method get score is working properly.
	 */
	@Test
	public void testGetScore(){
		assertTrue(testRecommendation.getScore() == TEST_SCORE);
	}
	
	/**
	 * Check method set score is working properly.
	 */
	@Test
	public void testSetScore() {
		testRecommendation.setScore(4);
		assertFalse(testRecommendation.getScore() == TEST_SCORE);
	}

}
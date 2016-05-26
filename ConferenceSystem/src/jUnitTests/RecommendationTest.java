package jUnitTests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.RecommendationForm;

public class RecommendationTest {
	private RecommendationForm testRecommendation;
	private static final String TEST_PATH = "Test Path";
	private static final String TEST_AUTHOR = "Test Author";
	private static final String TEST_DATE = "05/06/2016";
	private static final String TEST_TITLE = "Test Title";
	private static final int TEST_SCORE = 5;
	
	@Before
	public void setupTests() {
		testRecommendation = new RecommendationForm(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE, TEST_SCORE);
	}
	
	@Test
	public void testConstructor() {
		assertTrue(testRecommendation.getPath().equals(TEST_PATH));
		assertTrue(testRecommendation.getAuthor().equals(TEST_AUTHOR));
		assertTrue(testRecommendation.getSubmittedDateString().equals(TEST_DATE));
		assertTrue(testRecommendation.getTitle().equals(TEST_TITLE));
		assertTrue(testRecommendation.getScore() == TEST_SCORE);
	}

}

package jUnitTests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Paper;

/**
 * Test class for paper.
 * 
 * @author Bincheng Li
 * @version 1.0
 */
public class PaperTest {
	private Paper testPaper;
	private static final String TEST_PATH = "Test Path";
	private static final String TEST_AUTHOR = "Test Author";
	private static final String TEST_DATE = "05/06/2016";
	private static final String TEST_TITLE = "Test Title";
	
	@Before
	public void setupTests() {
		// Paper for the test.
		testPaper = new Paper(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE);
	}
	
	/**
	 * Check the constructor for paper works or not.
	 */
	@Test
	public void testConstructor() {
		assertTrue(testPaper.getPath().equals(TEST_PATH));
		assertTrue(testPaper.getAuthor().equals(TEST_AUTHOR));
		assertTrue(testPaper.getSubmittedDateString().equals(TEST_DATE));
		assertTrue(testPaper.getTitle().equals(TEST_TITLE));
	}

}

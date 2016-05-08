package jUnitTests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import TCSS360.Paper;

public class PaperTest {
	private Paper testPaper;
	private static final String TEST_PATH = "Test Path";
	private static final String TEST_AUTHOR = "Test Author";
	private static final String TEST_DATE = "05/06/2016";
	private static final String TEST_TITLE = "Test Title";
	
	@Before
	public void setupTests() {
		testPaper = new Paper(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE);
	}
	
	@Test
	public void testConstructor() {
		assertTrue(testPaper.getPath().equals(TEST_PATH));
		assertTrue(testPaper.getAuthor().equals(TEST_AUTHOR));
		assertTrue(testPaper.getSubmittedDateString().equals(TEST_DATE));
		assertTrue(testPaper.getTitle().equals(TEST_TITLE));
	}

}

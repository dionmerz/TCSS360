package jUnitTests;


import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import model.Manuscript;
import model.Paper;

/**
 * Test class for paper.
 * 
 * @author Bincheng Li
 * @version 1.0
 */
public class PaperTest {
	private Paper testPaper;
	private Paper testPaper2;
	private Paper testPaper3;
	private static final String TEST_PATH = "Test Path";
	private static final String TEST_AUTHOR = "Test Author";
	private static final String TEST_DATE = "05/06/2016";
	private static final String TEST_TITLE = "Test Title";
	private static final String TEST_AUTHOR2 = "Test Author2";
	private static final String TEST_TITLE2 = "Test Title2";
	
	@Before
	public void setupTests() {
		// Paper for the test.
		testPaper = new Paper(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE);
		testPaper2 = new Manuscript(TEST_PATH, TEST_AUTHOR2, TEST_DATE, TEST_TITLE);
		testPaper3 = new Manuscript(TEST_PATH, TEST_AUTHOR, TEST_DATE, TEST_TITLE2);
	}
	
	/**
	 * Check method get path is working properly. 
	 */
	@Test
	public void testGetPath() {
		String testPath = Paths.get(".").toAbsolutePath().normalize().toString()+ "\\" + TEST_PATH;
		assertTrue(testPaper.getPath().toString().equals(testPath));
	}
	
	/**
	 * Check method get author is working properly. 
	 */
	@Test
	public void testGetAuthor() {
		assertTrue(testPaper.getAuthor().equals(TEST_AUTHOR));
	}
	
	
	/**
	 * Check method get submitted date is working properly. 
	 */
	@Test
	public void testGetSubmittedDateString() {
		assertTrue(testPaper.getSubmittedDateString().equals(TEST_DATE));
	}
	
	/**
	 * Check method get title is working properly. 
	 */
	@Test
	public void testGetTitle() {
		assertTrue(testPaper.getTitle().equals(TEST_TITLE));
	}
	
	/**
	 * Check method set author is working properly. 
	 */
	@Test
	public void testSetAuthor() {
		testPaper.setAuthor("Test Author 2");
		assertFalse(testPaper.getAuthor().equals(TEST_AUTHOR));
	}
	
	
	/**
	 * Check method set submitted date is working properly. 
	 */
	@Test
	public void testSetSubmittedDateString() {
		testPaper.setSubmittedDateString("Test Date 2");
		assertFalse(testPaper.getSubmittedDateString().equals(TEST_DATE));
	}
	
	/**
	 * Check method set title is working properly. 
	 */
	@Test
	public void testSetTitle() {
		testPaper.setTitle("Test Title 2");
		assertFalse(testPaper.getTitle().equals(TEST_TITLE));
	}
	/**
	 * Test equals method (check are both manuscripts same or not) for 
	 * a manuscript.
	 */
	@Test
	public void testManuscriptEqualsWithItself(){
		assertTrue(testPaper.equals(testPaper));
	}
	
	/**
	 * Test equals method (check are both manuscripts same or not) for 
	 * a manuscript.
	 */
	@Test
	public void testManuscriptEqualsWithNull(){
		assertFalse(testPaper.equals(null));
	}
	
	/**
	 * Test equals method (check are both manuscripts same or not) for 
	 * a manuscript.
	 */
	@Test
	public void testManuscriptEqualsWithDifferentAuthorName(){
		assertFalse(testPaper.equals(testPaper2));
	}
	
	/**
	 * Test equals method (check are both manuscripts same or not) for 
	 * a manuscript.
	 */
	@Test
	public void testManuscriptEqualsWithDifferentTitleName(){
		assertFalse(testPaper.equals(testPaper3));
	}
	
	
}

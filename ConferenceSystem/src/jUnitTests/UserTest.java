package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.Paper;
import model.ProgramChair;
import model.ReviewForm;
import model.Reviewer;
import model.SubprogramChair;
import model.User;
import model.Manuscript.Status;

/**
 * Tests all non constructor methods of the User class.
 * @author Adam Marr
 *
 */
public class UserTest {

	private User testUser;
	private User testUser2;

	private Conference testConference1;
	
	Manuscript testManuscript;
	Manuscript testManuscript2;
	Manuscript testManuscript3;

	@Before
	public void setUp() throws Exception {
		testConference1 = new Conference("Conf1", testUser, "start", "stop", "PDeadline", "RDeadline", 10, 10);
		
		testUser = new User("Ted Testsworth", "BigTed", "TedsAddress@gmail.com");
		testUser2 = new User("Fred Testsworth", "FiredUpFred", "FredsAddress@gmail.com");
		
		testManuscript = new Manuscript("source path 1", testUser.getMyName(), "Entry date", "How to Find Bugs in C# Code for Dummies");
		testManuscript2 = new Manuscript("source path 2", testUser.getMyName(), "Entry date", "How to Find Bugs in C++ Code for Dummies");
		testManuscript3 = new Manuscript("source path 3", testUser.getMyName(), "Entry date", "How to Find Bugs in Java Code for Dummies");
		
		
	}

	/**
	 * Tests the getMyName getter to make sure it returns the expected value.
	 */
	@Test
	public void testGetMyName() {
		assertEquals(testUser.getMyName(), "Ted Testsworth");
		assertEquals(testUser2.getMyName(), "Fred Testsworth");
	}


	/**
	 * Tests the setMyName setter to make sure it changes the name field as expected.
	 */
	@Test
	public void testSetMyName() {
		assertEquals(testUser.getMyName(), "Ted Testsworth");
		testUser.setMyName("Teddie Testsworth");
		assertEquals(testUser.getMyName(), "Teddie Testsworth");
	}

	/**
	 * Tests the getMyLoginName setter to make sure it returns the expected value.
	 */ 
	@Test
	public void testGetMyLoginName() {
		assertEquals(testUser.getMyLoginName(), "BigTed");
		assertEquals(testUser2.getMyLoginName(), "FiredUpFred");
	}

	/**
	 * Tests the setMyLoginName setter to make sure it changes the login name field as expected.
	 */
	@Test
	public void testSetMyLoginName() {
		assertEquals(testUser2.getMyLoginName(), "FiredUpFred");
		testUser2.setMyLoginName("FreeManFred");
		assertEquals(testUser2.getMyLoginName(), "FreeManFred");
	}

	/**
	 * Tests the getMyEmail getter to make sure it returns the expected value.
	 */
	@Test
	public void testGetMyEmail() {
		assertEquals(testUser.getMyEmail(), "TedsAddress@gmail.com");
		assertEquals(testUser2.getMyEmail(), "FredsAddress@gmail.com");
	}

	/**
	 * Tests the setMyEmail setter to make sure it changes the email field as expected.
	 */
	@Test
	public void testSetMyEmail() {
		assertEquals(testUser.getMyEmail(), "TedsAddress@gmail.com");
		testUser.setMyEmail("TedsNewAddress@gmail.com");
		assertEquals(testUser.getMyEmail(), "TedsNewAddress@gmail.com");
	}

	/**
	 * Tests the getMyManuscripts field to make sure it correctly returns a list of all manuscripts authored by the user.
	 * Tests three distinct partitions. A list with no items, a list with one item, and a list with 2 items.
	 */
	@Test
	public void testGetMyManuscripts() {
		assertEquals(testUser.getMyManuscripts().size() , 0);
		
		testUser.addMyManuscript(testManuscript);
		assertEquals(testUser.getMyManuscripts().size() , 1);
		assertEquals(testUser.getMyManuscripts().get(0) , testManuscript);
		
		
		testUser.addMyManuscript(testManuscript2);
		assertEquals(testUser.getMyManuscripts().size() , 2);
		assertEquals(testUser.getMyManuscripts().get(0) , testManuscript);
		assertEquals(testUser.getMyManuscripts().get(1) , testManuscript2);
	}

	@Test
	public void testAddMyManuscript() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveManuscript() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSubProgManuscript() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddSubProgManuscript() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyRoles() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMyRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMyRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyManuscriptsToReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMyManuscriptsToReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMyManuscriptsToReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyReviews() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveReview() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubmitManuscript() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAuthorRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindProgramChairRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindSubprogramChairRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindReviewerRole() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasRole() {
		fail("Not yet implemented");
	}

}

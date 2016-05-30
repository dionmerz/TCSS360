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
	
	private User testUserWithOneAuthoredPaper;
	private User testUserWithTwoAuthoredPaper;
	private User testUserWithNoAuthoredPaper;
	
	private User testUserWithOneSubProgManuscript;
	private User testUserWithTwoSubProgManuscript;
	private User testUserWithNoSubProgManuscript;

	private User testUserWithAuthorRole;
	private User testUserWithReviewerRole;
	private User testUserWithProgramChairRole;
	private User testUserWithSubprogramChairRole;
	private User testUserWithTwoRole;
	private User testUserWithNoRole;
	
	private Conference testConference1;
	
	Manuscript testManuscript;
	Manuscript testManuscript2;
	Manuscript testManuscript3;

	@Before
	public void setUp() throws Exception {
		testConference1 = new Conference("Conf1", testUser, "start", "stop", "PDeadline", "RDeadline", 10, 10);
		
		testUser = new User("Ted Testsworth", "BigTed", "TedsAddress@gmail.com");
		testUser2 = new User("Fred Testsworth", "FiredUpFred", "FredsAddress@gmail.com");
		
		testUserWithOneAuthoredPaper = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithTwoAuthoredPaper = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithNoAuthoredPaper = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		
		testUserWithOneSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithTwoSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithNoSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		
		testUserWithOneSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithTwoSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithNoSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		
		testUserWithAuthorRole = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithReviewerRole = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithProgramChairRole = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithSubprogramChairRole = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithTwoRole = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithNoRole = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		
		
		testUser2 = new User("Fred Testsworth", "FiredUpFred", "FredsAddress@gmail.com");
		testUser2 = new User("Fred Testsworth", "FiredUpFred", "FredsAddress@gmail.com");
		
		testManuscript = new Manuscript("source path 1", testUser.getMyName(), "Entry date", "How to Find Bugs in C# Code for Dummies");
		testManuscript2 = new Manuscript("source path 2", testUser.getMyName(), "Entry date", "How to Find Bugs in C++ Code for Dummies");
		testManuscript3 = new Manuscript("source path 3", testUser.getMyName(), "Entry date", "How to Find Bugs in Java Code for Dummies");
		
		testUserWithOneAuthoredPaper.addMyManuscript(testManuscript);
		testUserWithTwoAuthoredPaper.addMyManuscript(testManuscript);
		testUserWithTwoAuthoredPaper.addMyManuscript(testManuscript2);
		
		testUserWithOneSubProgManuscript.addSubProgManuscript(testManuscript);
		testUserWithTwoSubProgManuscript.addSubProgManuscript(testManuscript);
		testUserWithTwoSubProgManuscript.addSubProgManuscript(testManuscript2);
		
		testUserWithAuthorRole.addMyRole(new Author(testConference1));
		testUserWithReviewerRole.addMyRole(new Reviewer(testConference1));
		testUserWithSubprogramChairRole.addMyRole(new SubprogramChair(testConference1));
		testUserWithProgramChairRole.addMyRole(new ProgramChair(testConference1));
		
		testUserWithTwoRole.addMyRole(new Author(testConference1));
		testUserWithTwoRole.addMyRole(new Reviewer(testConference1));

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
	 * Tests the getMyManuscripts method to make sure it correctly returns a list of all manuscripts authored by the user.
	 */
	@Test
	public void testGetMyManuscripts() {
		assertEquals(testUser.getMyManuscripts().size() , 0);
		testUser.addMyManuscript(testManuscript);
		assertEquals(testUser.getMyManuscripts().size() , 1);
		assertEquals(testUser.getMyManuscripts().get(0) , testManuscript);
	}

	/**
	 * Tests the addMyManuscripts method to make sure it correctly adds a manuscripts to the list of
	 * all manuscripts authored by this user.
	 * Tests three distinct partitions. A list with no items, a list with one item, and a list with 2 items.
	 */
	@Test
	public void testAddMyManuscript() {
		testUserWithNoAuthoredPaper.addMyManuscript(testManuscript);
		assertEquals(testUserWithOneAuthoredPaper.getMyManuscripts().size() , 1);
		assertTrue(testUserWithOneAuthoredPaper.getMyManuscripts().contains(testManuscript));
		
		testUserWithOneAuthoredPaper.addMyManuscript(testManuscript2);
		assertEquals(testUserWithOneAuthoredPaper.getMyManuscripts().size() , 2);
		assertTrue(testUserWithOneAuthoredPaper.getMyManuscripts().contains(testManuscript2));
		
		
		testUserWithTwoAuthoredPaper.addMyManuscript(testManuscript3);
		assertEquals(testUserWithTwoAuthoredPaper.getMyManuscripts().size() , 3);
		assertTrue(testUserWithTwoAuthoredPaper.getMyManuscripts().contains(testManuscript3));
	}

	/**
	 * Tests the removeManuscript method to make sure it removes the given manuscript from the list
	 * of all manuscripts authored by this user.
	 * 
	 * Tests two distinct partitions. A list with one items, a list with two items.
	 */
	@Test
	public void testRemoveManuscript() {
		assertEquals(testUserWithOneAuthoredPaper.getMyManuscripts().size() , 1);
		testUserWithOneAuthoredPaper.removeManuscript(testManuscript);
		assertEquals(testUserWithOneAuthoredPaper.getMyManuscripts().size() , 0);
		
		assertEquals(testUserWithTwoAuthoredPaper.getMyManuscripts().size() , 2);
		testUserWithTwoAuthoredPaper.removeManuscript(testManuscript);
		assertEquals(testUserWithTwoAuthoredPaper.getMyManuscripts().size() , 1);
		assertFalse(testUserWithTwoAuthoredPaper.getMyManuscripts().contains(testManuscript));
	}

	/**
	 * Tests the getMySubProgManuscripts method to make sure it correctly returns a list of all manuscripts 
	 * assigned to this subprogram chair by a program chair.
	 * 
	 * Tests three distinct partitions. A list with no items, a list with one items, a list with two items.
	 */
	@Test
	public void testGetSubProgManuscript() {
		assertTrue(testUserWithNoSubProgManuscript.getSubProgManuscript().isEmpty());
		
		assertEquals(testUserWithOneSubProgManuscript.getSubProgManuscript().size() , 1);
		assertTrue(testUserWithOneSubProgManuscript.getSubProgManuscript().contains(testManuscript));
		
		assertEquals(testUserWithTwoSubProgManuscript.getSubProgManuscript().size() , 2);
		assertTrue(testUserWithTwoSubProgManuscript.getSubProgManuscript().contains(testManuscript));
		assertTrue(testUserWithTwoSubProgManuscript.getSubProgManuscript().contains(testManuscript2));
	}

	/**
	 * Tests the addMySubProgManuscripts method to make sure it correctly adds a manuscript to the list of all manuscripts 
	 * assigned to this subprogram chair by a program chair.
	 * 
	 * Tests three distinct partitions. A list with no items, a list with one items, a list with two items.
	 */
	@Test
	public void testAddSubProgManuscript() {
		testUserWithNoSubProgManuscript.addSubProgManuscript(testManuscript);
		assertEquals(testUserWithNoSubProgManuscript.getSubProgManuscript().size(), 1);
		assertTrue(testUserWithNoSubProgManuscript.getSubProgManuscript().contains(testManuscript));
		
		testUserWithOneSubProgManuscript.addSubProgManuscript(testManuscript2);
		assertEquals(testUserWithOneSubProgManuscript.getSubProgManuscript().size(), 2);
		assertTrue(testUserWithOneSubProgManuscript.getSubProgManuscript().contains(testManuscript));
		assertTrue(testUserWithOneSubProgManuscript.getSubProgManuscript().contains(testManuscript2));
		
		testUserWithTwoSubProgManuscript.addSubProgManuscript(testManuscript3);
		assertEquals(testUserWithTwoSubProgManuscript.getSubProgManuscript().size(), 3);
		assertTrue(testUserWithTwoSubProgManuscript.getSubProgManuscript().contains(testManuscript));
		assertTrue(testUserWithTwoSubProgManuscript.getSubProgManuscript().contains(testManuscript2));
		assertTrue(testUserWithTwoSubProgManuscript.getSubProgManuscript().contains(testManuscript3));
	}

	/**
	 * Tests the getMyRoles method to make sure it correctly returns a list of all roles 
	 * assigned to this user.
	 * 
	 * Tests five distinct partitions. A user that is only an Author, A user that is only a Reviewer, 
	 * A user that is only a Subprogram Chair, A user that is only a Program Chair, and a user with two different roles.
	 */
	@Test
	public void testGetMyRoles() {
		assertTrue(testUserWithNoRole.getMyRoles().isEmpty());
		
		assertEquals(testUserWithAuthorRole.getMyRoles().size() , 1);
		assertEquals(testUserWithAuthorRole.getMyRoles().get(0), new Author(testConference1));
		
		assertEquals(testUserWithReviewerRole.getMyRoles().size() , 1);
		assertEquals(testUserWithReviewerRole.getMyRoles().get(0), new Reviewer(testConference1));
		
		assertEquals(testUserWithSubprogramChairRole.getMyRoles().size() , 1);
		assertEquals(testUserWithSubprogramChairRole.getMyRoles().get(0), new SubprogramChair(testConference1));
		
		assertEquals(testUserWithProgramChairRole.getMyRoles().size() , 1);
		assertEquals(testUserWithProgramChairRole.getMyRoles().get(0), new ProgramChair(testConference1));
		
		assertEquals(testUserWithTwoRole.getMyRoles().size() , 2);
		assertEquals(testUserWithTwoRole.getMyRoles().get(0), new Author(testConference1));
		assertEquals(testUserWithTwoRole.getMyRoles().get(1), new Reviewer(testConference1));
	}

	/**
	 * Tests the addMyRoles method to make sure it correctly adds a Role to the list of all roles 
	 * assigned to this user.
	 * 
	 * Tests three distinct partitions. A list with no items, a list with one items, a list with two items.
	 */
	@Test
	public void testAddMyRole() {
		testUserWithNoRole.addMyRole(new Author(testConference1));
		assertFalse(testUserWithNoRole.getMyRoles().isEmpty());
		assertTrue(testUserWithNoRole.getMyRoles().contains(new Author(testConference1)));
		
		testUserWithAuthorRole.addMyRole(new Reviewer(testConference1));
		assertEquals(testUserWithAuthorRole.getMyRoles().size(), 2);
		assertTrue(testUserWithAuthorRole.getMyRoles().contains(new Author(testConference1)));
		assertTrue(testUserWithAuthorRole.getMyRoles().contains(new Reviewer(testConference1)));
		
		testUserWithTwoRole.addMyRole(new ProgramChair(testConference1));
		assertEquals(testUserWithTwoRole.getMyRoles().size(), 3);
		assertTrue(testUserWithTwoRole.getMyRoles().contains(new Author(testConference1)));
		assertTrue(testUserWithTwoRole.getMyRoles().contains(new Reviewer(testConference1)));
		assertTrue(testUserWithTwoRole.getMyRoles().contains(new ProgramChair(testConference1)));
	}

	/**
	 * Tests the removeMyRoles method to make sure it correctly removes a Role to the list of all roles 
	 * assigned to this user.
	 * 
	 * Tests five distinct partitions. A user that is only an Author, A user that is only a Reviewer, 
	 * A user that is only a Subprogram Chair, A user that is only a Program Chair, and a user with two different roles.
	 */
	@Test
	public void testRemoveMyRole() {
		testUserWithAuthorRole.removeMyRole(new Author(testConference1));
		assertTrue(testUserWithAuthorRole.getMyRoles().isEmpty());
		
		testUserWithReviewerRole.removeMyRole(new Reviewer(testConference1));
		assertTrue(testUserWithReviewerRole.getMyRoles().isEmpty());
		
		testUserWithProgramChairRole.removeMyRole(new ProgramChair(testConference1));
		assertTrue(testUserWithProgramChairRole.getMyRoles().isEmpty());
		
		testUserWithSubprogramChairRole.removeMyRole(new SubprogramChair(testConference1));
		assertTrue(testUserWithSubprogramChairRole.getMyRoles().isEmpty());
		
		testUserWithTwoRole.removeMyRole(new Author(testConference1));
		assertEquals(testUserWithTwoRole.getMyRoles().size(), 1);
		assertTrue(testUserWithTwoRole.getMyRoles().contains(new Reviewer(testConference1)));
		assertFalse(testUserWithTwoRole.getMyRoles().contains(new Author(testConference1)));
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

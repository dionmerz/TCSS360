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
	private User testUserWithFourSubProgManuscript;
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
	Manuscript testManuscript4;

	@Before
	public void setUp() throws Exception {
		testConference1 = new Conference("Conf1", testUser, "start", "stop", "PDeadline", "RDeadline", 10, 10);
		
		testUser = new User("Ted Testsworth", "BigTed", "TedsAddress@gmail.com");
		testUser2 = new User("Fred Testsworth", "FiredUpFred", "FredsAddress@gmail.com");
		
		testUserWithOneAuthoredPaper = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithTwoAuthoredPaper = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithNoAuthoredPaper = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		
		testUserWithOneSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithFourSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithNoSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		
		testUserWithOneSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
		testUserWithFourSubProgManuscript = new User("Ted Testsworth", "BigTedOnePaper", "TedsAddress@gmail.com");
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
		testManuscript4 = new Manuscript("source path 4", testUser.getMyName(), "Entry date", "How to Find Bugs in Python Code for Dummies");
		
		testUserWithOneAuthoredPaper.addMyManuscript(testManuscript);
		testUserWithTwoAuthoredPaper.addMyManuscript(testManuscript);
		testUserWithTwoAuthoredPaper.addMyManuscript(testManuscript2);
		
		testUserWithOneSubProgManuscript.addSubProgManuscript(testManuscript);
		testUserWithFourSubProgManuscript.addSubProgManuscript(testManuscript);
		testUserWithFourSubProgManuscript.addSubProgManuscript(testManuscript2);
		testUserWithFourSubProgManuscript.addSubProgManuscript(testManuscript3);
		testUserWithFourSubProgManuscript.addSubProgManuscript(testManuscript4);
		
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
	 * partition: A list with no items.
	 */
	@Test
	public void testAddMyManuscriptForAuthorWithNoItem() {
		testUserWithNoAuthoredPaper.addMyManuscript(testManuscript);
		assertEquals(testUserWithOneAuthoredPaper.getMyManuscripts().size() , 1);
		assertTrue(testUserWithOneAuthoredPaper.getMyManuscripts().contains(testManuscript));
	}
	
	/**
	 * Tests the addMyManuscripts method to make sure it correctly adds a manuscripts to the list of
	 * all manuscripts authored by this user.
	 * partition: a list with one item.
	 */
	@Test
	public void testAddMyManuscriptForAuthorWithOneItem() {
		testUserWithOneAuthoredPaper.addMyManuscript(testManuscript2);
		assertEquals(testUserWithOneAuthoredPaper.getMyManuscripts().size() , 2);
		assertTrue(testUserWithOneAuthoredPaper.getMyManuscripts().contains(testManuscript2));
	}
	
	/**
	 * Tests the addMyManuscripts method to make sure it correctly adds a manuscripts to the list of
	 * all manuscripts authored by this user.
	 * partition: a list with 2 items.
	 */
	@Test
	public void testAddMyManuscriptForAuthorWithTwoItem() {
		testUserWithTwoAuthoredPaper.addMyManuscript(testManuscript3);
		assertEquals(testUserWithTwoAuthoredPaper.getMyManuscripts().size() , 3);
		assertTrue(testUserWithTwoAuthoredPaper.getMyManuscripts().contains(testManuscript3));
	}


	/**
	 * Tests the removeManuscript method to make sure it removes the given manuscript from the list
	 * of all manuscripts authored by this user.
	 * 
	 * partition: A list with one items.
	 */
	@Test
	public void testRemoveManuscriptForAuthorWithOneItem() {
		assertEquals(testUserWithOneAuthoredPaper.getMyManuscripts().size() , 1);
		testUserWithOneAuthoredPaper.removeManuscript(testManuscript);
		assertEquals(testUserWithOneAuthoredPaper.getMyManuscripts().size() , 0);
	}
	
	/**
	 * Tests the removeManuscript method to make sure it removes the given manuscript from the list
	 * of all manuscripts authored by this user.
	 * 
	 * partition: A list with two items.
	 */
	@Test
	public void testRemoveManuscriptForAuthorWithTwoItem() {	
		assertEquals(testUserWithTwoAuthoredPaper.getMyManuscripts().size() , 2);
		testUserWithTwoAuthoredPaper.removeManuscript(testManuscript);
		assertEquals(testUserWithTwoAuthoredPaper.getMyManuscripts().size() , 1);
		assertFalse(testUserWithTwoAuthoredPaper.getMyManuscripts().contains(testManuscript));
	}
	/**
	 * Tests the getMySubProgManuscripts method to make sure it correctly returns a list of all manuscripts 
	 * assigned to this subprogram chair by a program chair.
	 * 
	 * Tests three distinct partitions: A list with no items, a list with one items, a list with two items.
	 */
	@Test
	public void testGetSubProgManuscript() {
		assertTrue(testUserWithNoSubProgManuscript.getSubProgManuscript().isEmpty());
		
		assertEquals(testUserWithOneSubProgManuscript.getSubProgManuscript().size() , 1);
		assertTrue(testUserWithOneSubProgManuscript.getSubProgManuscript().contains(testManuscript));
		
		assertEquals(testUserWithFourSubProgManuscript.getSubProgManuscript().size() , 4);
		assertTrue(testUserWithFourSubProgManuscript.getSubProgManuscript().contains(testManuscript));
		assertTrue(testUserWithFourSubProgManuscript.getSubProgManuscript().contains(testManuscript2));
	}

	/**
	 * Tests the addMySubProgManuscripts method to make sure it correctly adds a manuscript to the list of all manuscripts 
	 * assigned to this subprogram chair by a program chair.
	 * 
	 * Tests three distinct partitions: A list with no items.
	 */
	@Test
	public void testAddSubProgManuscriptForSubprogramChairWithNoItem() {
		testUserWithNoSubProgManuscript.addSubProgManuscript(testManuscript);
		assertEquals(testUserWithNoSubProgManuscript.getSubProgManuscript().size(), 1);
		assertTrue(testUserWithNoSubProgManuscript.getSubProgManuscript().contains(testManuscript));
	}
	
	/**
	 * Tests the addMySubProgManuscripts method to make sure it correctly adds a manuscript to the list of all manuscripts 
	 * assigned to this subprogram chair by a program chair.
	 * 
	 * Tests three distinct partitions: a list with one items.
	 */
	@Test
	public void testAddSubProgManuscriptForSubprogramChairWithOneItem() {
		testUserWithOneSubProgManuscript.addSubProgManuscript(testManuscript2);
		assertEquals(testUserWithOneSubProgManuscript.getSubProgManuscript().size(), 2);
		assertTrue(testUserWithOneSubProgManuscript.getSubProgManuscript().contains(testManuscript));
		assertTrue(testUserWithOneSubProgManuscript.getSubProgManuscript().contains(testManuscript2));
	}
	/**
	 * Tests the addMySubProgManuscripts method to make sure it correctly adds a manuscript to the list of all manuscripts 
	 * assigned to this subprogram chair by a program chair.
	 * 
	 * Tests three distinct partitions: a list with two items.
	 */
	@Test
	public void testAddSubProgManuscriptForSubprogramChairWithFourItem() {
		testUserWithFourSubProgManuscript.addSubProgManuscript(testManuscript3);
		assertEquals(testUserWithFourSubProgManuscript.getSubProgManuscript().size(), 5);
		assertTrue(testUserWithFourSubProgManuscript.getSubProgManuscript().contains(testManuscript));
		assertTrue(testUserWithFourSubProgManuscript.getSubProgManuscript().contains(testManuscript2));
		assertTrue(testUserWithFourSubProgManuscript.getSubProgManuscript().contains(testManuscript3));
		assertTrue(testUserWithFourSubProgManuscript.getSubProgManuscript().contains(testManuscript4));
	}
	
	/**
	 * Tests the getMyRoles method to make sure it correctly returns a list of all roles 
	 * assigned to this user.
	 * 
	 * partition: A user that is only an Author.
	 */
	@Test
	public void testGetMyRoles() {
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
	 * Tests the getMyRoles method to make sure it correctly returns a list of all roles 
	 * assigned to this user.
	 * 
	 * partition: A user that is only a Reviewer.
	 */
	@Test
	public void testGetMyRolesWithJustAuthorRole() {
		assertEquals(testUserWithAuthorRole.getMyRoles().size() , 1);
		assertEquals(testUserWithAuthorRole.getMyRoles().get(0), new Author(testConference1));
	}
	
	/**
	 * Tests the getMyRoles method to make sure it correctly returns a list of all roles 
	 * assigned to this user.
	 * 
	 * Tests five distinct partitions: A user that is only a Subprogram Chair.
	 */
	@Test
	public void testGetMyRolesWithJustReviewerRole() {
		assertEquals(testUserWithReviewerRole.getMyRoles().size() , 1);
		assertEquals(testUserWithReviewerRole.getMyRoles().get(0), new Reviewer(testConference1));
	}
	
	/**
	 * Tests the getMyRoles method to make sure it correctly returns a list of all roles 
	 * assigned to this user.
	 * 
	 * partition: A user that is only a Program Chair.
	 */
	@Test
	public void testGetMyRolesWithJustSubprogramChairRole() {	
		assertEquals(testUserWithSubprogramChairRole.getMyRoles().size() , 1);
		assertEquals(testUserWithSubprogramChairRole.getMyRoles().get(0), new SubprogramChair(testConference1));
	}
	
	/**
	 * Tests the getMyRoles method to make sure it correctly returns a list of all roles 
	 * assigned to this user.
	 * 
	 * partition: a user with two different roles.
	 */
	@Test
	public void testGetMyRolesWithJustProgramChairRole() {
		assertEquals(testUserWithProgramChairRole.getMyRoles().size() , 1);
		assertEquals(testUserWithProgramChairRole.getMyRoles().get(0), new ProgramChair(testConference1));
	}

	/**
	 * Tests the addMyRoles method to make sure it correctly adds a Role to the list of all roles 
	 * assigned to this user.
	 * 
	 * partition: a list with two items.
	 */
	@Test
	public void testAddMyRoleWithTwoRoles() {
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
	 * partition: A user that is only an Author.
	 */
	@Test
	public void testRemoveMyRoleForUserWithAuthorRole() {
		testUserWithAuthorRole.removeMyRole(new Author(testConference1));
		assertTrue(testUserWithAuthorRole.getMyRoles().isEmpty());
	}
	/**
	 * Tests the removeMyRoles method to make sure it correctly removes a Role to the list of all roles 
	 * assigned to this user.
	 * 
	 * partition: A user that is only a Reviewer.
	 */
	@Test
	public void testRemoveMyRoleForUserWithReviewerRole() {
		testUserWithReviewerRole.removeMyRole(new Reviewer(testConference1));
		assertTrue(testUserWithReviewerRole.getMyRoles().isEmpty());
	}
	
	/**
	 * Tests the removeMyRoles method to make sure it correctly removes a Role to the list of all roles 
	 * assigned to this user.
	 * 
	 * partition: A user that is only a Subprogram Chair.
	 */
	@Test
	public void testRemoveMyRoleForUserWithSubprogramChairRole() {
		testUserWithSubprogramChairRole.removeMyRole(new SubprogramChair(testConference1));
		assertTrue(testUserWithSubprogramChairRole.getMyRoles().isEmpty());
	}
	
	/**
	 * Tests the removeMyRoles method to make sure it correctly removes a Role to the list of all roles 
	 * assigned to this user.
	 * 
	 * partition: A user that is only a Program Chair.
	 */
	@Test
	public void testRemoveMyRoleForUserWithProgramChairRole() {
		testUserWithProgramChairRole.removeMyRole(new ProgramChair(testConference1));
		assertTrue(testUserWithProgramChairRole.getMyRoles().isEmpty());
	}
	
	/**
	 * Tests the removeMyRoles method to make sure it correctly removes a Role to the list of all roles 
	 * assigned to this user.
	 * 
	 * partition: a user with two different roles.
	 */
	@Test
	public void testRemoveMyRoleForUserWithTwoRoles() {	
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

	/**
	 * Tests the findAuthorRole method to ensure it returns a Author object if the user is a Author and null otherwise.
	 * 
	 * Tests two distinct partitions: A user who is a Author, and a user who is not a Author.
	 */
	@Test
	public void testFindAuthorRole() {
		assertFalse(testUserWithAuthorRole.findAuthorRole().equals(null));
		assertTrue(testUserWithNoRole.findAuthorRole() == null);
	}

	/**
	 * Tests the findProgramChairRole method to ensure it returns a ProgramChair object if the user is a Program Chair and null otherwise.
	 * 
	 * Tests two distinct partitions: A user who is a Program Chair, and a user who is not a Program Chair.
	 */
	@Test
	public void testFindProgramChairRole() {
		assertFalse(testUserWithProgramChairRole.findProgramChairRole().equals(null));
		assertTrue(testUserWithNoRole.findProgramChairRole() == null);
	}

	/**
	 * Tests the findSubprogramChairRole method to ensure it returns a SubprogramChair object if the user is a Subprogram Chair and null otherwise.
	 * 
	 * Tests two distinct partitions: A user who is a Subprogram Chair, and a user who is not a Subprogram Chair.
	 */
	@Test
	public void testFindSubprogramChairRole() {
		assertFalse(testUserWithSubprogramChairRole.findSubprogramChairRole().equals(null));
		assertTrue(testUserWithNoRole.findSubprogramChairRole() == null);
	}

	/**
	 * Tests the findReviewerRole method to ensure it returns a reviewer object if the user is a reviewer and null otherwise.
	 * 
	 * Tests two distinct partitions: A user who is a reviewer, and a user who is not a reviewer.
	 */
	@Test
	public void testFindReviewerRole() {
		assertFalse(testUserWithReviewerRole.findReviewerRole().equals(null));
		assertTrue(testUserWithNoRole.findReviewerRole() == null);
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

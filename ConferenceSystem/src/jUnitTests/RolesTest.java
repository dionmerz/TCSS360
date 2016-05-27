package jUnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Conference;
import model.ProgramChair;
import model.User;

/**
 * Tests all of the non constructor methods in the Roles class.
 * @author Adam Marr
 *
 */
public class RolesTest {
	User programChair;
	Conference testConference1;
	Conference testConference2;
	
	@Before
	public void setup() {
		programChair = new User("Tedsworth Roosevelt", "teddy", "ted@cssmanglement.com");
		testConference1 = new Conference("Conference Test 1", programChair, "Starting Date", "Ending Date", "Paper Deadline", "Review Deadline", 30, 50);
		programChair.addMyRole(new ProgramChair(testConference1));
		testConference2 = new Conference("Conference Test 2", programChair, "Starting Date", "Ending Date", "Paper Deadline", "Review Deadline", 30, 50);
		programChair.addMyRole(new ProgramChair(testConference2));
	}

	/**
	 * Tests the getConference getter to make sure it returns the expected values.
	 */
	@Test
	public void testForGetConference() {
		assertEquals(programChair.getMyRoles().get(0).getConference(), testConference1);
		assertEquals(programChair.getMyRoles().get(1).getConference(), testConference2);
	}

}

package jUnitTests;

import static org.junit.Assert.*;

import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;

import model.Conference;
import model.Manuscript;
import model.Paper;
import model.User;

/**
 * Test the Conference class by creating 
 * @author Bernabe Guzman
 *
 */
public class ConferenceTest {
	private Paper manuscript1;
	private Paper manuscript2;
	private User testProgramChairUser;
	private Conference testConference;
	
	@Before
	public void setUp() {
		manuscript1 = new Manuscript("test.txt", "TestAuthor", "SubmitDate", "TestTitle1");
		manuscript2 = new Manuscript("test2.txt", "TestAuthor", "SubmitDate", "TestTitle2");
		testProgramChairUser  = new User("TempUser", "UserLogin", "User@email.com");
		testConference = new Conference("Conf1", testProgramChairUser, "start", "stop", "PDeadline", "RDeadline", 0, 0);
	}
	
	@Test
	public void testAddGetAndRemoveManuscripts() {
		assertTrue(testConference.getManuscripts().isEmpty());
		testConference.addManuscript((Manuscript) manuscript1);
		testConference.addManuscript((Manuscript) manuscript2);
		assertEquals(testConference.getManuscripts().size(), 2);
		testConference.removeManuscript((Manuscript) manuscript1);
		assertEquals(testConference.getManuscripts().size(), 1);
		assertEquals(testConference.getManuscripts().get(0).getTitle(), "TestTitle2");
	}
	
	@Test
	public void testSetAndGetName() {
		assertTrue(testConference.getName().equals("Conf1"));
		testConference.setName("newConf1");
		assertTrue(testConference.getName().equals("newConf1"));
	}
	@Test
	public void testSetAndGetProgramChair() {
		assertTrue(testConference.getProgramChair().getMyName().equals("TempUser"));
		testProgramChairUser.setMyName("newUser");
		assertTrue(testConference.getProgramChair().getMyName().equals("newUser"));
	}
	
	@Test
	public void testSetAndGetStartDate() {
		assertTrue(testConference.getStartingDate().equals("start"));
		testConference.setStartingDate("newStart");
		assertTrue(testConference.getStartingDate().equals("newStart"));
	}
	@Test
	public void testSetAndGetEndingDate() {
		assertTrue(testConference.getEndingDate().equals("stop"));
		testConference.setEndingDate("newStop");
		assertTrue(testConference.getEndingDate().equals("newStop"));
	}

	@Test
	public void testSetAndGetPaperDeadline() {
		assertTrue(testConference.getPaperDeadline().equals("PDeadline"));
		testConference.setPaperDeadline("nPDeadline");
		assertTrue(testConference.getPaperDeadline().equals("nPDeadline"));
	}
	@Test
	public void testSetAndGetReviewDeadline() {
		assertTrue(testConference.getReviewDeadline().equals("RDeadline"));
		testConference.setReviewDeadline("nRDeadline");
		assertTrue(testConference.getReviewDeadline().equals("nRDeadline"));
	}
	@Test
	public void testSetAndGetPaperDeadlineDate() {
		Calendar myReviewDeadline = Calendar.getInstance();
	    myReviewDeadline.add(Calendar.DATE, 0);
	    assertTrue(testConference.getReviewDeadlineDate().equals(myReviewDeadline));
	    Calendar myReviewDeadline2 = Calendar.getInstance();
	    myReviewDeadline.add(Calendar.DATE, 1);
	    testConference.setReviewDeadlineDate(myReviewDeadline2);
	    assertTrue(testConference.getReviewDeadlineDate().equals(myReviewDeadline2));
	}
	@Test
	public void testSetAndGetReviewDeadlineDate() {
		Calendar myPaperDeadline = Calendar.getInstance();
		myPaperDeadline.add(Calendar.DATE, 0);
		assertTrue(testConference.getReviewDeadlineDate().equals(myPaperDeadline));
	    Calendar myPaperDeadline2 = Calendar.getInstance();
	    myPaperDeadline2.add(Calendar.DATE, 1);
	    testConference.setPaperDeadlineDate(myPaperDeadline2);
	    assertTrue(testConference.getPaperDeadlineDate().equals(myPaperDeadline2));
	}
}

package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TCSS360.Author;
import TCSS360.Conference;
import TCSS360.Manuscript;
import TCSS360.Paper;
import TCSS360.User;

/**
 * Test the Conference class by creating 
 * @author bernabeg
 *
 */
public class ConferenceTest {
	private List<Conference> confList;
	private Paper script;
	private Paper script2;
	private User usr;
	private Author auth;
	private Conference conf;
	
	@Before
	public void setUp() throws Exception {
		confList = new ArrayList<Conference>();
		script = new Manuscript("test.txt", "TestAuthor", "SubmitDate", "TestTitle");
		script2 = new Manuscript("test2.txt", "TestAuthor", "SubmitDate", "TestTitle");
		usr  = new User("TempUser", "UserLogin", "User@email.com");
		conf = new Conference("Conf1", usr, "start", "stop", "PDeadline", "RDeadline", 0, 0);
		auth = new Author(conf);	
	}
	
	@Test
	public void test() {
		conf.addManuscript((Manuscript) script);
		conf.addManuscript((Manuscript) script2);
		assertEquals(conf.getManuscripts().size(), 2);
		conf.removeManuscript((Manuscript) script);
		assertEquals(conf.getManuscripts().size(), 1);
	}
	
	@Test
	public void testName() {
		assertTrue(conf.getName().equals("Conf1"));
		conf.setName("newConf1");
		assertTrue(conf.getName().equals("newConf1"));
	}
	@Test
	public void testProgramChair() {
		assertTrue(conf.getProgramChair().getMyName().equals("TempUser"));
		usr.setMyName("newUser");
		assertTrue(conf.getProgramChair().getMyName().equals("newUser"));
	}
	
	@Test
	public void testStartDate() {
		assertTrue(conf.getStartingDate().equals("start"));
		conf.setStartingDate("newStart");
		assertTrue(conf.getStartingDate().equals("newStart"));
	}
	@Test
	public void testEndingDate() {
		assertTrue(conf.getEndingDate().equals("stop"));
		conf.setEndingDate("newStop");
		assertTrue(conf.getEndingDate().equals("newStop"));
	}

	@Test
	public void testPaperDeadline() {
		assertTrue(conf.getPaperDeadline().equals("PDeadline"));
		conf.setPaperDeadline("nPDeadline");
		assertTrue(conf.getPaperDeadline().equals("nPDeadline"));
	}
	@Test
	public void testReviewDeadline() {
		assertTrue(conf.getReviewDeadline().equals("RDeadline"));
		conf.setReviewDeadline("nRDeadline");
		assertTrue(conf.getReviewDeadline().equals("nRDeadline"));
	}
	@Test
	public void testPaperDeadlineDate() {
		Calendar myReviewDeadline = Calendar.getInstance();
	    myReviewDeadline.add(Calendar.DATE, 0);
	    assertTrue(conf.getReviewDeadlineDate().equals(myReviewDeadline));
	    Calendar myReviewDeadline2 = Calendar.getInstance();
	    myReviewDeadline.add(Calendar.DATE, 1);
	    conf.setReviewDeadlineDate(myReviewDeadline2);
	    assertTrue(conf.getReviewDeadlineDate().equals(myReviewDeadline2));
	}
	@Test
	public void testReviewDeadlineDate() {
		Calendar myPaperDeadline = Calendar.getInstance();
		myPaperDeadline.add(Calendar.DATE, 0);
		assertTrue(conf.getReviewDeadlineDate().equals(myPaperDeadline));
	    Calendar myPaperDeadline2 = Calendar.getInstance();
	    myPaperDeadline2.add(Calendar.DATE, 1);
	    conf.setPaperDeadlineDate(myPaperDeadline2);
	    assertTrue(conf.getPaperDeadlineDate().equals(myPaperDeadline2));
	}
}

package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TCSS360.Author;
import TCSS360.Conference;
import TCSS360.Manuscript;
import TCSS360.Paper;
import TCSS360.User;

/**
 * Test for the Author Class.
 * @author Andrew Merz
 *
 */
public class AuthorTest {
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
		conf.addManuscript((Manuscript) script);
		auth = new Author(conf);
		
		confList.add(conf);
	}
	
	@Test
	public void testAuthorConstructor() {
		Author tempAuthor = new Author(conf);
		assertTrue(tempAuthor.getConference().getName().equals(conf.getName()));

	}

	@Test
	public void testUpdateAuthoredManuscript() {
		
		auth.updateAuthoredManuscript(usr, (Manuscript) script2, confList);
		
		assertTrue("test2.txt".equals(confList.get(0).getManuscripts().get(0).getPath()));
		
	}
	
	@Test
	public void testUnsubmitManuscript() {
		
		assertTrue(conf.getManuscripts().size() == 1);
		
		auth.unsubmitManuscript(usr, (Manuscript) script, confList);
		
		assertTrue(conf.getManuscripts().size() == 0);
		
	}

}

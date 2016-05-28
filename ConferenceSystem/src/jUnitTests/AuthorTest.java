package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.Paper;
import model.User;

/**
 * Test for the Author Class.
 * @author Andrew Merz
 *
 */
public class AuthorTest {
	private List<Conference> confList;
	private Paper script;
	private Paper updatedManuscript;
	private User authorUser;
	private Author auth;
	private Conference testConference;

	@Before
	public void setUp() throws Exception {

		authorUser  = new User("TempUser", "UserLogin", "User@email.com");
		confList = new ArrayList<Conference>();
		updatedManuscript = new Manuscript("updatedScript.txt", "AuthorTestFile", "SubmitDate", "TestTitle");
		testConference = new Conference("Conf1", authorUser, "start", "stop", "PDeadline", "RDeadline", 60, 60);
		authorUser.submitManuscript("testFile.txt", "AuthorTestFile", authorUser, testConference);
		script = new Manuscript("testFile.txt", "TempUser", "SubmitDate", "AuthorTestFile");
		confList.add(testConference);
	}
	
	@Test
	public void testAuthorConstructor() {
		Author tempAuthor = new Author(testConference);
		assertTrue(tempAuthor.getConference().getName().equals(testConference.getName()));

	}

	@Test
	public void testUpdateAuthoredManuscript() {
		auth.updateAuthoredManuscript(authorUser, (Manuscript) updatedManuscript, confList);
		System.out.println(confList.get(0).getManuscripts().get(0).getPath());
		assertTrue("updatedScript.txt".equals(confList.get(0).getManuscripts().get(0).getPath()));
		
	}

	
	@Test
	public void testUnsubmitManuscript() {
		
		
		assertTrue(testConference.getManuscripts().size() == 1);
		
		auth.unsubmitManuscript(authorUser, (Manuscript) script, confList);
		
		assertTrue(testConference.getManuscripts().size() == 0);
		
		// Replace the test file.
		authorUser.submitManuscript("testFile.txt", "AuthorTestFile", authorUser, testConference);
		
	}

}

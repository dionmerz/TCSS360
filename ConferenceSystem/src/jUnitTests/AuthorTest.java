package jUnitTests;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.User;

/**
 * Test for the Author Class.
 * @author Andrew Merz
 *
 */
public class AuthorTest {
	private List<Conference> confList;
	private Manuscript updatedManuscript;
	private User authorUser;
	private Conference testConference;

	@Before
	public void setUp() throws Exception {

		authorUser  = new User("TempUser", "UserLogin", "User@email.com");
		confList = new ArrayList<Conference>();
		
		updatedManuscript = new Manuscript("testFile.txt", authorUser.getMyName(), "SubmitDate", "AuthorTestFile");
		
		testConference = new Conference("Conf1", authorUser, "start", "stop", "PDeadline", "RDeadline", 60, 60);
		
		authorUser.submitManuscript("testFile.txt", "AuthorTestFile", authorUser, testConference);
		
		confList.add(testConference);
	}
	
	/**
	 * Test the Construction of the Author Object
	 */
	@Test
	public void testAuthorConstructor() {
		Author tempAuthor = new Author(testConference);
		assertTrue(tempAuthor.getConference().getName().equals(testConference.getName()));

	}

	/**
	 * Tests Updating a submitted manuscript.
	 */
	@Test
	public void testUpdateAuthoredManuscriptWithOneManuscript() {
		Author testAuthor = authorUser.findAuthorRole();
		testAuthor.updateAuthoredManuscript(authorUser, updatedManuscript, confList);
		Path localFile = Paths.get(updatedManuscript.getPath());
		File checkFile = new File(localFile.toString());
		assertTrue(checkFile.exists());
		
		assertTrue(authorUser.getMyManuscripts().contains(updatedManuscript));
	    	
	}
	
	/**
	 * Tests Updating a submitted manuscript.
	 */
	@Test
	public void testUpdateAuthoredManuscriptWithMultipleManuscripts() {
		Author testAuthor = authorUser.findAuthorRole();
		authorUser.submitManuscript("test2.txt", "SecondManuscriptTitle", authorUser, confList.get(0));
		testAuthor.updateAuthoredManuscript(authorUser, updatedManuscript, confList);
		Path localFile = Paths.get(updatedManuscript.getPath());
		File checkFile = new File(localFile.toString());
		assertTrue(checkFile.exists());
		
		assertTrue(authorUser.getMyManuscripts().contains(updatedManuscript));
	    	
	}

	/**
	 * Tests unsubmit of a manuscript where the
	 *  user only has 1 manuscript currently submitted.
	 */
	@Test
	public void testUnsubmitManuscriptWithOneManuscipt() {
		Author testAuthor = authorUser.findAuthorRole();
		
		Manuscript removeManuscript= authorUser.getMyManuscripts().get(0);
		assertTrue(authorUser.getMyManuscripts().size() == 1);
		
		testAuthor.unsubmitManuscript(authorUser, removeManuscript, confList);
		
		assertTrue(authorUser.getMyManuscripts().size() == 0);
		
	}
	/**
	 * Tests unsubmit of a manuscript where the
	 *  user only has multiple manuscript currently submitted.
	 */
	@Test
	public void testUnsubmitManuscriptWithMultipleManuscript() {
		Author testAuthor = authorUser.findAuthorRole();
		authorUser.submitManuscript("test2.txt", "SecondManuscriptTitle", authorUser, confList.get(0));
		Manuscript removeManuscript= authorUser.getMyManuscripts().get(0);
		assertTrue(authorUser.getMyManuscripts().size() > 1);
		int numberOfManuscripts = authorUser.getMyManuscripts().size();
		
		testAuthor.unsubmitManuscript(authorUser, removeManuscript, confList);
		
		assertTrue(authorUser.getMyManuscripts().size() == numberOfManuscripts - 1);
		assertFalse(authorUser.getMyManuscripts().contains(removeManuscript));
		
	}

}

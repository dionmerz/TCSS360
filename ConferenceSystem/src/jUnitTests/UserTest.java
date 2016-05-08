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

public class UserTest {
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
		
	}

	@Test
	public void testName() {
		assertTrue(usr.getMyName().equals("TempUser"));
		usr.setMyName("newUserName");
		assertTrue(usr.getMyName().equals("newUserName"));
	}
	
	@Test
	public void testLoginName() {
		assertTrue(usr.getMyLoginName().equals("UserLogin"));
		usr.setMyLoginName("newLoginName");
		assertTrue(usr.getMyLoginName().equals("newLoginName"));
	}

	@Test
	public void testEmail() {
		assertTrue(usr.getMyEmail().equals("User@email.com"));
		usr.setMyEmail("newEmail");
		assertTrue(usr.getMyEmail().equals("newEmail"));
	}
	
	@Test 
	public void testAuthoredManuscripts() {
		assertEquals(usr.getMyManuscripts().size(),0);
		usr.addMyManuscript((Manuscript)script);
		usr.addMyManuscript((Manuscript)script2);
		assertEquals(usr.getMyManuscripts().size(), 2);
		assertTrue(usr.getMyManuscripts().get(0).getPath().equals("test.txt"));
		assertTrue(usr.getMyManuscripts().get(1).getPath().equals("test2.txt"));
	}
	
}

package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import TCSS360.Author;
import TCSS360.Conference;
import TCSS360.Manuscript;
import TCSS360.Manuscript.Status;
import TCSS360.Paper;
import TCSS360.ProgramChair;
import TCSS360.SubprogramChair;
import TCSS360.User;

public class ProgramChairTest {
	private Paper script;
	private User usr;
	private ProgramChair pc;
	private SubprogramChair sbpc;
	private Conference conf;
	
	@Before
	public void setUp() throws Exception {
		new ArrayList<Conference>();
		script = new Manuscript("test.txt", "TestAuthor", "SubmitDate", "TestTitle");
		new Manuscript("test2.txt", "TestAuthor", "SubmitDate", "TestTitle");
		usr  = new User("TempUser", "UserLogin", "User@email.com");
		conf = new Conference("Conf1", usr, "start", "stop", "PDeadline", "RDeadline", 0, 0);
		pc = new ProgramChair(conf);
		sbpc = new SubprogramChair(conf);
		usr.addMyRole(sbpc);
		new Author(conf);	
		conf.addManuscript((Manuscript) script);
	}
	
	@Test 
	public void testRejectManuscript() {
		assertTrue(((Manuscript) script).getStatus().equals(Status.SUBMITTED));
		pc.rejectManuscript((Manuscript)script);
		assertTrue(((Manuscript) script).getStatus().equals(Status.REJECTED));
	}
	
	@Test 
	public void testAcceptManuscript() {
		assertTrue(((Manuscript) script).getStatus().equals(Status.SUBMITTED));
		pc.acceptManuscript((Manuscript)script);
		assertTrue(((Manuscript) script).getStatus().equals(Status.ACCEPTED));
	}
	
	public void testAssignSubprogramChairManuscript() {
		assertEquals(usr.getSubProgManuscript().size(), 0);
		pc.assignSubProgManuscript(usr, (Manuscript) script);
		assertEquals(usr.getSubProgManuscript().size(), 1);
		assertTrue(usr.getSubProgManuscript().get(0).getPath().equals("test.text"));
	}

}

package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import TCSS360.Conference;
import TCSS360.Manuscript;
import TCSS360.Manuscript.Status;
import TCSS360.Reviewer;
import TCSS360.SubprogramChair;
import TCSS360.User;

public class SubprogramChairTest {

	
	private List<Conference> confList;
	private Manuscript script;
	private Manuscript script2;
	private Manuscript script3;
	private Manuscript script4;
	private Manuscript script5;
	private User usr;
	private User usrSPC;
	private SubprogramChair spc;
	private Conference conf;

	@Before
	public void setUp() throws Exception {
		confList = new ArrayList<Conference>();
		script = new Manuscript("test.txt", "TestAuthor", "SubmitDate", "TestTitle");
		script2 = new Manuscript("test2.txt", "TestAuthor2", "SubmitDate", "TestTitle2");
		script3 = new Manuscript("test3.txt", "TestAuthor3", "SubmitDate", "TestTitle3");
		script4 = new Manuscript("test4.txt", "TestAuthor4", "SubmitDate", "TestTitle4");
		script5 = new Manuscript("test5.txt", "TestAuthor5", "SubmitDate", "TestTitle5");
		usr  = new User("TempUser", "UserLogin", "User@email.com");
		usrSPC = new User("TempUser2", "User2Login", "User2@email.com");
		usrSPC.addMyRole(new SubprogramChair(conf));
		usr.addMyRole(new Reviewer(conf));
		conf = new Conference("Conf1", usr, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		conf.addManuscript((Manuscript) script);
		spc = new SubprogramChair(conf);
		
		confList.add(conf);
	}

	@Test
	public void testAssignReviewerManuscript() {
		assertTrue(usr.getMyManuscriptsToReview().size() == 0);
		spc.assignReviewerManuscript(usr, script);
		assertTrue(usr.getMyManuscriptsToReview().size() == 1);
		
		script = new Manuscript("test.txt", "TempUser", "SubmitDate", "TestTitle");
		spc.assignReviewerManuscript(usr, script);
		assertFalse(usr.getMyManuscriptsToReview().size() > 1);
		
		script = new Manuscript("test.txt", "OtherUser", "SubmitDate", "TestTitle");
		spc.assignReviewerManuscript(usr, script2);
		spc.assignReviewerManuscript(usr, script3);
		spc.assignReviewerManuscript(usr, script4);
		spc.assignReviewerManuscript(usr, script5);
		
		assertTrue(usr.getMyManuscriptsToReview().size() == 4);
		
	}
	
//	@Test
//	public void testSubmitRecommendation() {
//		assertTrue(script2.getRecomFormList().size() == 0);
//		spc.submitRecomendation(usr, script2, 5, "recommend.txt", "PaperRecommendation");
//		
//		assertTrue(script2.getRecomFormList().size() == 1);
//		
//		assertTrue(script2.getStatus() == Status.RECOMMENDED);
//	}

}

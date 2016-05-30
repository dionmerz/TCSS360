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
import model.ProgramChair;
import model.Reviewer;
import model.Roles;
import model.SubprogramChair;
import model.User;
import model.Manuscript.Status;

/**
 * This class tests the ProgramChair class functionality.
 * @author Bernabe Guzman
 * @version 1.0 5/30/16
 */
public class ProgramChairTest {
	
	private List<Conference> conferenceList;
	private List<User> userListMultipleUsers;
	private List<User> emptyUserList;
	private List<Roles> emptyRolesList;
	private Manuscript manuscript1;
	private Manuscript manuscript2;
	private Manuscript manuscript3;
	private Manuscript manuscript4;
	private Manuscript manuscript5;
	private List<Manuscript> listOneManuscript;
	private List<Manuscript> listFourManuscript;
	private User subprogramChairNoManuscriptsToRecommend;
	private User subprogramChairOneManuscriptToRecommend;
	private User subprogramChairFourManuscriptsToRecommend;
	private User programChairUser;
	private Conference conference;
	
	@Before
	public void setUp() throws Exception {
		emptyRolesList = new ArrayList<Roles>();
		emptyUserList = new ArrayList<User>();
		userListMultipleUsers = new ArrayList<User>();
		
		manuscript1 = new Manuscript("test1.txt", "TestAuthor1", "SubmitDate", "TestTitle1");
		manuscript2 = new Manuscript("test2.txt", "TestAuthor2", "SubmitDate", "TestTitle2");
		manuscript3 = new Manuscript("test3.txt", "TestAuthor3", "SubmitDate", "TestTitle3");
		manuscript4 = new Manuscript("test4.txt", "TestAuthor4", "SubmitDate", "TestTitle4");
		manuscript5 = new Manuscript("test5.txt", "TestAuthor5", "SubmitDate", "TestTitle5");
		
		listOneManuscript = new ArrayList<Manuscript>();
		listOneManuscript.add(manuscript1);
		
		listFourManuscript = new ArrayList<Manuscript>();
		listFourManuscript.add(manuscript1);
		listFourManuscript.add(manuscript2);
		listFourManuscript.add(manuscript3);
		listFourManuscript.add(manuscript4);
		
		subprogramChairNoManuscriptsToRecommend  = new User("TestSubprogramChair1", "SubprogramChairLogin1", "SubprogramChair1@email.com");
		subprogramChairNoManuscriptsToRecommend.addMyRole(new SubprogramChair(conference));
		subprogramChairOneManuscriptToRecommend  = new User("TestSubprogramChair2", "SubprogramChairLogin2", "SubprogramChair2@email.com",
													emptyRolesList, null,  null, null, listOneManuscript);
		subprogramChairOneManuscriptToRecommend.addMyRole(new SubprogramChair(conference));
		subprogramChairFourManuscriptsToRecommend  = new User("TestSubprogramChair3", "SubprogramChairLogin3", "SubprogramChair3@email.com",
													emptyRolesList, null,  null, null, listFourManuscript);
		subprogramChairFourManuscriptsToRecommend.addMyRole(new SubprogramChair(conference));
		
		programChairUser = new User("TestSubprogramChair", "SubprogramLogin", "subprogram@email.com");
		programChairUser.addMyRole(new ProgramChair(conference));
		
		conference = new Conference("Conf1", subprogramChairNoManuscriptsToRecommend, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		conference.addManuscript((Manuscript) manuscript1);
		
		conferenceList = new ArrayList<Conference>();
		conferenceList.add(conference);
		
		userListMultipleUsers.add(subprogramChairNoManuscriptsToRecommend);
		userListMultipleUsers.add(subprogramChairOneManuscriptToRecommend);
		userListMultipleUsers.add(subprogramChairFourManuscriptsToRecommend);
		userListMultipleUsers.add(programChairUser);
	}
	
	@Test 
	public void testRejectManuscript() {
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(0).getStatus(),
				Status.SUBMITTED);
		subprogramChairOneManuscriptToRecommend.findSubprogramChairRole().appendRecomendationToManuscript(subprogramChairOneManuscriptToRecommend, 
				conference, manuscript1, 5, "recommend.txt", "PaperRecommendation");
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(0).getStatus(),
				Status.RECOMMENDED);
		programChairUser.findProgramChairRole().rejectManuscript(manuscript1);
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(0).getStatus(),
				Status.REJECTED);
	}
	
//	@Test 
//	public void testAcceptManuscript() {
//		assertTrue(((Manuscript) script).getStatus().equals(Status.SUBMITTED));
//		pc.acceptManuscript((Manuscript)script);
//		assertTrue(((Manuscript) script).getStatus().equals(Status.ACCEPTED));
//	}
//	
//	public void testAssignSubprogramChairManuscript() {
//		assertEquals(usr.getSubProgManuscript().size(), 0);
//		pc.assignSubProgManuscript(usr, (Manuscript) script);
//		assertEquals(usr.getSubProgManuscript().size(), 1);
//		assertTrue(usr.getSubProgManuscript().get(0).getPath().equals("test.text"));
//	}

}

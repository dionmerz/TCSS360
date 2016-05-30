package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Conference;
import model.Manuscript;
import model.ProgramChair;
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
	
	private List<User> userListMultipleUsers;
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
	private User programChairUserForTestConference;
	private User programChairUserForConferenceNoManuscripts;
	private User programChairUserForConferenceOneManuscriptRecommended;
	private User programChairUserForConferenceOneManuscriptNotRecommended;
	private Conference testConference;
	private Conference conferenceNoManuscripts;
	private Conference conferenceOneManuscriptRecommended;
	private Conference conferenceOneManuscriptNotRecommended;
	
	@Before
	public void setUp() throws Exception {
		emptyRolesList = new ArrayList<Roles>();
		userListMultipleUsers = new ArrayList<User>();
		
		manuscript1 = new Manuscript("test1.txt", "TestAuthor1", "SubmitDate", "TestTitle1");
		manuscript2 = new Manuscript("test2.txt", "TestAuthor2", "SubmitDate", "TestTitle2");
		manuscript3 = new Manuscript("test3.txt", "TestAuthor3", "SubmitDate", "TestTitle3");
		manuscript4 = new Manuscript("test4.txt", "TestAuthor4", "SubmitDate", "TestTitle4");
		manuscript5 = new Manuscript("test5.txt", "TestAuthor5", "SubmitDate", "TestTitle5");
		manuscript5.setStatus(Status.RECOMMENDED);
		
		listOneManuscript = new ArrayList<Manuscript>();
		listOneManuscript.add(manuscript1);
		
		listFourManuscript = new ArrayList<Manuscript>();
		listFourManuscript.add(manuscript2);
		listFourManuscript.add(manuscript3);
		listFourManuscript.add(manuscript4);
		listFourManuscript.add(manuscript5);
		
		subprogramChairNoManuscriptsToRecommend  = new User("TestSubprogramChair1", "SubprogramChairLogin1", "SubprogramChair1@email.com");
		subprogramChairNoManuscriptsToRecommend.addMyRole(new SubprogramChair(testConference));
		subprogramChairOneManuscriptToRecommend  = new User("TestSubprogramChair2", "SubprogramChairLogin2", "SubprogramChair2@email.com",
													emptyRolesList, null,  null, null, listOneManuscript);
		subprogramChairOneManuscriptToRecommend.setMyName("TestAuthor1"); // because has manuscript1
		subprogramChairOneManuscriptToRecommend.addMyRole(new SubprogramChair(testConference));
		subprogramChairFourManuscriptsToRecommend  = new User("TestSubprogramChair3", "SubprogramChairLogin3", "SubprogramChair3@email.com",
													emptyRolesList, null,  null, null, listFourManuscript);
		subprogramChairFourManuscriptsToRecommend.addMyRole(new SubprogramChair(testConference));
		
		programChairUserForTestConference = new User("TestSubprogramChair1", "SubprogramLogin", "subprogram@email.com");
		programChairUserForTestConference.addMyRole(new ProgramChair(testConference));
		programChairUserForConferenceNoManuscripts = new User("TestSubprogramChair2", "SubprogramLogin", "subprogram@email.com");
		programChairUserForConferenceNoManuscripts.addMyRole(new ProgramChair(conferenceNoManuscripts));
		programChairUserForConferenceOneManuscriptRecommended = new User("TestSubprogramChair3", "SubprogramLogin", "subprogram@email.com");
		programChairUserForConferenceOneManuscriptRecommended.addMyRole(new ProgramChair(conferenceOneManuscriptRecommended));
		programChairUserForConferenceOneManuscriptNotRecommended = new User("TestSubprogramChair4", "SubprogramLogin", "subprogram@email.com");
		programChairUserForConferenceOneManuscriptNotRecommended.addMyRole(new ProgramChair(conferenceOneManuscriptNotRecommended));
		
		testConference = new Conference("Conf1", programChairUserForTestConference, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		testConference.addManuscript((Manuscript) manuscript1);	
		conferenceNoManuscripts = new Conference("Conf2", programChairUserForConferenceNoManuscripts, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		conferenceOneManuscriptRecommended = new Conference("Conf3", programChairUserForConferenceOneManuscriptRecommended, "start", "stop", "PDeadline", "RDeadline", 30, 60);		
		conferenceOneManuscriptRecommended.addManuscript((Manuscript) manuscript5);
		conferenceOneManuscriptNotRecommended = new Conference("Conf4", programChairUserForConferenceOneManuscriptNotRecommended, "start", "stop", "PDeadline", "RDeadline", 30, 60);
		conferenceOneManuscriptNotRecommended.addManuscript((Manuscript) manuscript1);
		
		userListMultipleUsers.add(subprogramChairNoManuscriptsToRecommend);
		userListMultipleUsers.add(subprogramChairOneManuscriptToRecommend);
		userListMultipleUsers.add(subprogramChairFourManuscriptsToRecommend);
		userListMultipleUsers.add(programChairUserForTestConference);
	}
	
	@Test 
	public void testRejectManuscript() {
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(0).getStatus(),
				Status.SUBMITTED);
		subprogramChairOneManuscriptToRecommend.findSubprogramChairRole().appendRecomendationToManuscript(subprogramChairOneManuscriptToRecommend, 
				testConference, manuscript1, 5, "recommend.txt", "PaperRecommendation");
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(0).getStatus(),
				Status.RECOMMENDED);
		programChairUserForTestConference.findProgramChairRole().rejectManuscript(manuscript1);
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(0).getStatus(),
				Status.REJECTED);
	}
	
	@Test 
	public void testAcceptManuscript() {
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(0).getStatus(),
				Status.SUBMITTED);
		subprogramChairOneManuscriptToRecommend.findSubprogramChairRole().appendRecomendationToManuscript(subprogramChairOneManuscriptToRecommend, 
				testConference, manuscript1, 5, "recommend.txt", "PaperRecommendation");
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(0).getStatus(),
				Status.RECOMMENDED);
		programChairUserForTestConference.findProgramChairRole().acceptManuscript(manuscript1);
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(0).getStatus(),
				Status.ACCEPTED);
	}
	
	@Test
	public void testAssignSubprogramChairManuscriptSubprogramChairNoManuscriptsToRecommend() {
		assertTrue(subprogramChairNoManuscriptsToRecommend.getSubProgManuscript().isEmpty());
		programChairUserForTestConference.findProgramChairRole().assignSubProgManuscript(subprogramChairNoManuscriptsToRecommend, testConference, manuscript2);
		assertEquals(subprogramChairNoManuscriptsToRecommend.getSubProgManuscript().size(), 1);
		assertEquals(subprogramChairNoManuscriptsToRecommend.getSubProgManuscript().get(0).getTitle(), "TestTitle2");
	}
	
	@Test
	public void testAssignSubprogramChairManuscriptSubprogramChairOneManuscriptToRecommend() {
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().size(), 1);
		programChairUserForTestConference.findProgramChairRole().assignSubProgManuscript(subprogramChairOneManuscriptToRecommend, testConference, manuscript2);
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().size(), 2);
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(1).getTitle(), "TestTitle2");
	}
	
	@Test
	public void testAssignSubprogramChairManuscriptSubprogramChairFourManuscriptsToRecommend() {
		assertEquals(subprogramChairFourManuscriptsToRecommend.getSubProgManuscript().size(), 4);
		programChairUserForTestConference.findProgramChairRole().assignSubProgManuscript(subprogramChairFourManuscriptsToRecommend, testConference, manuscript5);
		assertEquals(subprogramChairFourManuscriptsToRecommend.getSubProgManuscript().size(), 4);
		assertEquals(subprogramChairFourManuscriptsToRecommend.getSubProgManuscript().get(3).getTitle(), "TestTitle5");
	}
	
	@Test
	public void testAssignSubprogramChairManuscriptRecommendSelfAuthoredManuscript() {
//		subprogramChairOneManuscriptToRecommend.setMyName("TestAuthor1");
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().size(), 1);
		programChairUserForTestConference.findProgramChairRole().assignSubProgManuscript(subprogramChairOneManuscriptToRecommend, testConference, manuscript1);
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().get(0).getTitle(), "TestTitle1");
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().size(), 1);
	}
	
	@Test
	public void testManuscriptCanOnlyBeAssignedToOneSubprogramChair() {
//		subprogramChairNoManuscriptsToRecommend.setMyName("TestAuthor1");
		assertEquals(subprogramChairNoManuscriptsToRecommend.getSubProgManuscript().size(), 0);
		assertEquals(subprogramChairOneManuscriptToRecommend.getSubProgManuscript().size(), 1);
		programChairUserForConferenceOneManuscriptNotRecommended.findProgramChairRole().assignSubProgManuscript(subprogramChairNoManuscriptsToRecommend, 
				conferenceOneManuscriptNotRecommended, manuscript1);
		assertTrue(subprogramChairNoManuscriptsToRecommend.getSubProgManuscript().isEmpty());
	}
	
	@Test
	public void testGetListOfManuscriptsWithReccomendationConferenceNoManuscripts(){
		assertTrue(conferenceNoManuscripts.getManuscripts().isEmpty());
		assertTrue(programChairUserForTestConference.findProgramChairRole().getListOfManuscriptsWithRecommendations(conferenceNoManuscripts).isEmpty());
	}
	
	@Test
	public void testGetListOfManuscriptsWithReccomendationConferenceOneManuscriptRecommended(){
		List<Manuscript> listRecommendedManuscripts = new ArrayList<Manuscript>();
		assertEquals(conferenceOneManuscriptRecommended.getManuscripts().size(), 1);
		listRecommendedManuscripts = programChairUserForTestConference.findProgramChairRole().getListOfManuscriptsWithRecommendations(conferenceOneManuscriptRecommended);
		assertEquals(listRecommendedManuscripts.size(), 1);
		assertEquals(listRecommendedManuscripts.get(0).getStatus(), Status.RECOMMENDED);
	}
	
	@Test
	public void testGetListOfManuscriptsWithReccomendationConferenceOneManuscriptNotRecommended(){
		List<Manuscript> listRecommendedManuscripts = new ArrayList<Manuscript>();
		assertEquals(conferenceOneManuscriptNotRecommended.getManuscripts().size(), 1);
		listRecommendedManuscripts = programChairUserForTestConference.findProgramChairRole().getListOfManuscriptsWithRecommendations(conferenceOneManuscriptNotRecommended);
		assertTrue(listRecommendedManuscripts.isEmpty());
	}

}

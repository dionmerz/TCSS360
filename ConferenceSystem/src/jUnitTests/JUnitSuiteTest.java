package jUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AuthorTest.class,
	ConferenceTest.class,
	ManuscriptTest.class,
	PaperTest.class,
	ProgramChairTest.class,
	RecommendationTest.class,
	ReviewerTest.class,
	ReviewFormTest.class,
	SubprogramChairTest.class,
	UserTest.class,
	RolesTest.class
})
public class JUnitSuiteTest {

}

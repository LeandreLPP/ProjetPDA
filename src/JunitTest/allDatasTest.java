package JunitTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = {PhotoTest.class,SelectionTest.class,CollectionTest.class,UserTest.class})
public class allDatasTest {
	public static Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}
}
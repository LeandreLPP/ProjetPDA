package JunitTest;

import java.io.File;

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
	
	static public void deleteDirectory(String emplacement) {
		File path = new File(emplacement);
	    if( path.exists() ) {
	      File[] files = path.listFiles();
	      for(int i=0; i<files.length; i++) {
	         if(files[i].isDirectory()) {
	           deleteDirectory(path+"/"+files[i]);
	         }
	         else {
	           files[i].delete();
	         }
	      }
	    }
	    path.delete();
	 }
}
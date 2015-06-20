package JunitTest;
/*package pda.control;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.*;

public class PDAJUnit {
	
	private PdaCtrl lePda;
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void testAjoutNouvelleAppli() {
		lePda = new PdaCtrl();
		IApplication testJUnit = new LaunchCtrl();
		testJUnit.setAppliName ( "TestJUnit" );
		lePda.addNewAppli(testJUnit);
		ArrayList<String> laListe = lePda.getApplicationsName();
		//assertEquals(laListe.get(0), "TestJUnit");
	}
	
	@Test
	public void testStartLauncher() {
		lePda = new PdaCtrl(); 
		lePda.getView().getStartMI().doClick();//simule un clic de souris
		ArrayList<String> liste = lePda.getApplicationsName();
		//assertEquals(liste.get(0), "launcher");
	}
	
	@Test
	public void testCloseCurrentAppli() {
		lePda = new PdaCtrl();
		lePda.getView().getCloseMI().doClick(); //simule un clic de souris
		ArrayList<String> liste = lePda.getApplicationsName();
		//assertEquals(liste.size(), 0);
	}
}*/
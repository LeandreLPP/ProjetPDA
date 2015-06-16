package datas.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datas.*;

public class SelectionTest {
	private Collection collecSource;
	private Selection selection;
	private Photo p1;
	private Photo p2;
	private Photo p3;
	private Photo p4;
	private Photo p5;

	@Before
	public void setUp() throws Exception {
		this.p1 = new Photo("data/chat.jpg","data/testJunit/p1.jpg");
		this.p2 = new Photo("data/chat.jpg","data/testJunit/p2.jpg");
		this.p3 = new Photo("data/chat.jpg","data/testJunit/p3.jpg");
		this.p4 = new Photo("data/chat.jpg","data/testJunit/p4.jpg");
		this.p5 = new Photo("data/chat.jpg","data/testJunit/p5.jpg");
		Photo[] tab = {p1,p2,p3,p4,p5};
		this.collecSource = new Collection("Source",tab);
		this.selection = new Selection(this.collecSource);
	}

	@After
	public void tearDown() throws Exception {
		this.selection = null;
		this.collecSource = null;
		this.p1=null;
		this.p2=null;
		this.p3=null;
		this.p4=null;
		this.p5=null;
		File f = new File("data/testJunit/p1.jpg");
		f.delete();
		f = new File("data/testJunit/p2.jpg");
		f.delete();
		f = new File("data/testJunit/p3.jpg");
		f.delete();
		f = new File("data/testJunit/p4.jpg");
		f.delete();
		f = new File("data/testJunit/p5.jpg");
		f.delete();
	}

	@Test
	public void testSelection() {
		assertNotNull(this.selection);
	}

	@Test
	public void testRechercher() {
		//--- Recherche par auteur ---
		Photo[] rechercheAuteur = {this.p1,this.p2};
		for(Photo p : rechercheAuteur){
			p.setAuteur("A");
		}
		String[] auteur = {"A"};
		this.selection.setAuteur(auteur);
		Collection resultatAuteur = this.selection.rechercher();
		assertArrayEquals(rechercheAuteur,resultatAuteur.getListePhotos().toArray());
		
		//--- Recherche par titre ---
		Photo[] rechercheTitre = {this.p4,this.p3};
		for(Photo p : rechercheTitre){
			p.setTitre("A");
		}
		String[] titre = {"A"};
		auteur = new String[0];
		this.selection.setAuteur(auteur);
		this.selection.setTitre(titre);
		Collection resultatTitre = this.selection.rechercher();
		assertArrayEquals(rechercheTitre,resultatTitre.getListePhotos().toArray());
		
		//--- Recherche par pays ---
		Photo[] recherchePays = {this.p5,this.p2,this.p1,this.p3};
		for(Photo p : recherchePays){
			p.setAuteur("");
			p.setTitre("");
			p.setPays("A");
		}
		String[] pays = {"A"};
		titre = new String[0];
		this.selection.setTitre(titre);
		this.selection.setPays(pays);
		Collection resultatPays = this.selection.rechercher();
		assertArrayEquals(recherchePays,resultatPays.getListePhotos().toArray());
		
		//--- Recherche par date ---
		Photo[] rechercheDate = {this.p5,this.p2,this.p1,this.p3};
		for(Photo p : rechercheDate){
			p.setAuteur("");
			p.setTitre("");
			p.setPays("");
			p.setDate(new java.util.GregorianCalendar(2010,4,10));
		}
		pays = new String[0];
		this.selection.setPays(pays);
		this.selection.setDateFin(new java.util.GregorianCalendar(2013,4,10));
		Collection resultatDate = this.selection.rechercher();
		assertArrayEquals(rechercheDate,resultatDate.getListePhotos().toArray());

		//--- Recherche par date ---
		Photo[] rechercheKey = {this.p5,this.p2,this.p1,this.p3};
		for(Photo p : rechercheKey){
			p.setDate(new java.util.GregorianCalendar());
			String[] key = {"A","b","C"};
			p.setKeyWords(key);
		}
		this.selection.setDateFin(new java.util.GregorianCalendar());
		String[] key = {"A","B"};
		this.selection.setKeyWords(key);
		Collection resultatKey = this.selection.rechercher();
		assertArrayEquals(rechercheKey,resultatKey.getListePhotos().toArray());
	}

	@Test
	public void testGetSetTitre() {
		String[] tab = new String[0];
		this.selection.setTitre(tab);
		assertArrayEquals(tab,this.selection.getTitre());
		tab = new String[2];
		tab[0] = "A";
		tab[1] = "B";
		this.selection.setTitre(tab);
		assertArrayEquals(tab,this.selection.getTitre());
	}

	@Test
	public void testGetSetAuteur() {
		String[] tab = new String[0];
		this.selection.setAuteur(tab);
		assertArrayEquals(tab,this.selection.getAuteur());
		tab = new String[2];
		tab[0] = "A";
		tab[1] = "B";
		this.selection.setAuteur(tab);
		assertArrayEquals(tab,this.selection.getAuteur());
	}

	@Test
	public void testGetSetPays() {
		String[] tab = new String[0];
		this.selection.setPays(tab);
		assertArrayEquals(tab,this.selection.getPays());
		tab = new String[2];
		tab[0] = "A";
		tab[1] = "B";
		this.selection.setPays(tab);
		assertArrayEquals(tab,this.selection.getPays());
	}

	@Test
	public void testGetSetKeyWords() {
		String[] tab = new String[0];
		this.selection.setKeyWords(tab);
		assertArrayEquals(tab,this.selection.getKeyWords());
		tab = new String[2];
		tab[0] = "A";
		tab[1] = "B";
		this.selection.setKeyWords(tab);
		assertArrayEquals(tab,this.selection.getKeyWords());
	}

	@Test
	public void testGetSetDateDebut() {
		java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
		java.util.Date date = new java.util.Date();
		calendar.setTime(date);
		calendar.set(2010, 10, 10);
		this.selection.setDateDebut(calendar);
		assertEquals(calendar,this.selection.getDateDebut());
		java.util.GregorianCalendar calendar2 = new java.util.GregorianCalendar();
		java.util.Date date2 = new java.util.Date();
		calendar2.setTime(date2);
		calendar2.set(2020, 10, 10);
		this.selection.setDateDebut(calendar2);
		assertEquals(calendar,this.selection.getDateDebut());
	}

	@Test
	public void testGetSetDateFin() {
		java.util.GregorianCalendar calendar2 = new java.util.GregorianCalendar();
		java.util.Date date2 = new java.util.Date();
		calendar2.setTime(date2);
		calendar2.set(2020, 10, 10);
		this.selection.setDateFin(calendar2);
		assertEquals(calendar2,this.selection.getDateFin());
		java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();
		java.util.Date date = new java.util.Date();
		calendar.setTime(date);
		calendar.set(2010, 10, 10);
		this.selection.setDateFin(calendar);
		assertEquals(calendar2,this.selection.getDateFin());
	}

	@Test
	public void testGetSetSource() {
		assertEquals(this.collecSource,this.selection.getSource());
		Photo[] tab = {this.p1,this.p3,this.p5};
		Collection source = new Collection("TestSetSource",tab);
		this.selection.setSource(source);
		assertEquals(source,this.selection.getSource());
	}
}

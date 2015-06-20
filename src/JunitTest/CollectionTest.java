package JunitTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datas.*;

public class CollectionTest {

	private Photo pChat;
	private Photo pLapin;
	private Collection collection;

	@Before
	public void setUp(){
		try {
			this.pChat = new Photo("data/chat.jpg","data/testJunit/chat.jpg");
			this.pLapin = new Photo("data/lapin.jpg","data/testJunit/lapin.jpg");
		} catch (IOException e) {
			fail();
		}
		Photo[] tab = {this.pChat,this.pLapin};
		this.collection = new Collection("Test",tab);
	}

	@After()
	public void tearDown(){
		this.pChat = null;
		this.pLapin = null;
		this.collection = null;
		File f = new File("data/testJunit/chat.jpg");
		f.delete();
		f = new File("data/testJunit/lapin.jpg");
		f.delete();
	}

	@Test
	public void testCollectionString() {
		Collection testConstructeur = new Collection("Test");
		assertNotNull(testConstructeur);
	}

	@Test
	public void testCollectionStringPhotoArray() {
		Photo[] tab = {this.pChat,this.pLapin};
		Collection testConstructeur = new Collection("Test",tab);
		assertNotNull(testConstructeur);
	}

	@Test
	public void testAddPhoto() {
		Collection testAddPhoto = new Collection("Test");
		assertEquals(testAddPhoto.getListePhotos().size(),0);
		testAddPhoto.addPhoto(this.pChat);
		assertEquals(testAddPhoto.getListePhotos().size(),1);
		try {
			assertEquals(testAddPhoto.getPhoto(0),this.pChat);
			testAddPhoto.addPhoto(pLapin);
			assertEquals(testAddPhoto.getListePhotos().size(),2);
			assertEquals(testAddPhoto.getPhoto(1),this.pLapin);
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSplit() {
		Collection[] split;

		// -- Tri par ordre alphabetique d'auteur --
		this.pChat.setAuteur("A");
		this.pLapin.setAuteur("B");
		this.collection.setTriAuteurAlpha();
		split = this.collection.split();
		assertEquals(2,split.length);
		assertEquals("A",split[0].getTitre());
		assertEquals("B",split[1].getTitre());
		// -- Tri par ordre anti-alphabetique d'auteur --
		this.collection.setTriAuteurAntiAlpha();
		split = this.collection.split();
		assertEquals(2,split.length);
		assertEquals("B",split[0].getTitre());
		assertEquals("A",split[1].getTitre());

		// -- Tri par ordre alphabetique de titre --
		this.pChat.setTitre("A");
		this.pLapin.setTitre("B");
		this.collection.setTriTitreAlpha();
		split = this.collection.split();
		assertEquals(2,split.length);
		assertEquals("A",split[0].getTitre());
		assertEquals("B",split[1].getTitre());
		// -- Tri par ordre anti-alphabetique de titre --
		this.collection.setTriTitreAntiAlpha();
		split = this.collection.split();
		assertEquals(2,split.length);
		assertEquals("B",split[0].getTitre());
		assertEquals("A",split[1].getTitre());

		// -- Tri par ordre alphabetique de pays --
		this.pChat.setPays("A");
		this.pLapin.setPays("B");
		this.collection.setTriPaysAlpha();
		split = this.collection.split();
		assertEquals(2,split.length);
		assertEquals("A",split[0].getTitre());
		assertEquals("B",split[1].getTitre());
		// -- Tri par ordre anti-alphabetique de pays --
		this.collection.setTriPaysAntiAlpha();
		split = this.collection.split();
		assertEquals(2,split.length);
		assertEquals("B",split[0].getTitre());
		assertEquals("A",split[1].getTitre());

		// -- Tri par ordre chronologique des dates --
		java.util.GregorianCalendar c1 = new java.util.GregorianCalendar();
		java.util.GregorianCalendar c2 = new java.util.GregorianCalendar();
		c1.setTime(new java.util.Date());
		c2.setTime(new java.util.Date());
		c1.set(2012, 10, 12);
		c2.set(2015, 05, 14);
		this.pChat.setDate(c1);
		this.pLapin.setDate(c2);
		this.collection.setTriDateCroissante();
		split = this.collection.split();
		assertEquals(2,split.length);
		assertEquals("Mercredi 12 novembre 2012",split[0].getTitre());
		assertEquals("Dimanche 14 juin 2015",split[1].getTitre());
		// -- Tri par ordre anti-alphabetique de pays --
		this.collection.setTriDateDecroissante();
		split = this.collection.split();
		assertEquals(2,split.length);
		assertEquals("Dimanche 14 juin 2015",split[0].getTitre());
		assertEquals("Mercredi 12 novembre 2012",split[1].getTitre());
	}

	@Test
	public void testDelPhotoPhoto() {
		this.collection.delPhoto(pChat);
		assertEquals(1,this.collection.getListePhotos().size());
		try {
			assertEquals(this.pLapin,this.collection.getPhoto(0));
			this.collection.delPhoto(pChat);
			assertEquals(1,this.collection.getListePhotos().size());
			assertEquals(this.pLapin,this.collection.getPhoto(0));
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
		this.collection.delPhoto(pLapin);
		assertEquals(0,this.collection.getListePhotos().size());
	}

	@Test
	public void testDelPhotoInt() {
		try {
			this.collection.delPhoto(0);
			assertEquals(1,this.collection.getListePhotos().size());
			assertEquals(this.pLapin,this.collection.getPhoto(0));
			this.collection.delPhoto(1);
			assertEquals(0,this.collection.getListePhotos().size());
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelPhotoString() {
		try {
			this.collection.delPhoto("data/testJunit/chat.jpg");
			assertEquals(1,this.collection.getListePhotos().size());
			assertEquals(this.pLapin,this.collection.getPhoto(0));
			this.collection.delPhoto("data/testJunit/lapin.jpg");
			assertEquals(0,this.collection.getListePhotos().size());
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetPhotoInt() {
		try {
			assertEquals(this.pChat, this.collection.getPhoto(0));
			assertEquals(this.pLapin, this.collection.getPhoto(1));
			assertEquals(this.pChat, this.collection.getPhoto(-1));
			assertEquals(this.pLapin, this.collection.getPhoto(3));
			this.pChat.setAuteur("B");
			this.pLapin.setAuteur("A");
			this.collection.setTriAuteurAlpha();
			assertEquals(this.pLapin, this.collection.getPhoto(0));
			assertEquals(this.pChat, this.collection.getPhoto(1));
			assertEquals(this.pLapin, this.collection.getPhoto(-1));
			assertEquals(this.pChat, this.collection.getPhoto(3));
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetPhotoString() {
		try {
			assertEquals(this.pChat, this.collection.getPhoto("data/testJunit/chat.jpg"));
			assertEquals(this.pLapin, this.collection.getPhoto("data/testJunit/lapin.jpg"));
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSetTitre() {
		assertEquals("Test",this.collection.getTitre());
		this.collection.setTitre("Autre titre");
		assertEquals("Autre titre",this.collection.getTitre());
	}

	@Test
	public void testToutesPhotos() {
		java.util.Enumeration<Photo> enumPhoto = this.collection.toutesPhotos();
		java.util.ArrayList<Photo> listePhoto = new java.util.ArrayList<Photo>();
		while(enumPhoto.hasMoreElements()){
			listePhoto.add(enumPhoto.nextElement());
		}
		assertEquals(2,listePhoto.size());
		assertFalse(listePhoto.get(0) == listePhoto.get(1));
		assertTrue(listePhoto.get(0) == this.pChat || listePhoto.get(0) == this.pLapin);
		assertTrue(listePhoto.get(1) == this.pChat || listePhoto.get(1) == this.pLapin);
	}

	@Test
	public void testGetListePhotos() {
		java.util.ArrayList<Photo> listePhoto = this.collection.getListePhotos();
		assertEquals(2,listePhoto.size());
		assertFalse(listePhoto.get(0) == listePhoto.get(1));
		assertTrue(listePhoto.get(0) == this.pChat);
		assertTrue(listePhoto.get(1) == this.pLapin);
	}

	@Test
	public void testGetSetPhotoSelect() {
		assertEquals(0,this.collection.getIndexSelect());
		this.collection.setIndexSelect(1);
		assertEquals(1,this.collection.getIndexSelect());
		this.collection.setIndexSelect(2);
		assertEquals(0,this.collection.getIndexSelect());
		this.collection.setIndexSelect(-1);
		assertEquals(1,this.collection.getIndexSelect());
	}

	@Test
	public void testGetTri() {
		java.util.ArrayList<Object> tabClasses = new java.util.ArrayList<Object>();
		tabClasses.add(utilitaires.TriAuteurAlpha.class);
		tabClasses.add(utilitaires.TriAuteurAntiAlpha.class);
		tabClasses.add(utilitaires.TriTitreAlpha.class);
		tabClasses.add(utilitaires.TriTitreAntiAlpha.class);
		tabClasses.add(utilitaires.TriPaysAlpha.class);
		tabClasses.add(utilitaires.TriPaysAntiAlpha.class);
		tabClasses.add(utilitaires.TriDateCroissante.class);
		tabClasses.add(utilitaires.TriDateDecroissante.class);
		assertTrue(tabClasses.contains(this.collection.getTri().getClass()));
	}
}

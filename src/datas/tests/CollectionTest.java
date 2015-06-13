package datas.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datas.*;

public class CollectionTest {

	private Photo pChat;
	private Photo pLapin;

	@Before
	public void setUp(){
		this.pChat = new Photo("data/chat.jpg","data/testJunit/chat.jpg");
		this.pLapin = new Photo("data/lapin.jpg","data/testJunit/lapin.jpg");
	}
	
	@After()
	public void tearDown(){
		this.pChat = null;
		this.pLapin = null;
		File f = new File("data/testJunit/chat.jpg");
		f.delete();
		f = new File("data/testJunit/lapin.jpg");
		f.delete();
	}

	@Test
	public void testCollectionString() {
		Collection collection = new Collection("Test");
		assertNotNull(collection);
	}

	@Test
	public void testCollectionStringPhotoArray() {
		Photo[] tab = {this.pChat,this.pLapin};
		Collection collection = new Collection("Test",tab);
		assertNotNull(collection);
	}

	@Test
	public void testAddPhoto() {
		Collection collection = new Collection("Test");
		assertEquals(collection.getListePhotos().size(),0);
		collection.addPhoto(this.pChat);
		assertEquals(collection.getListePhotos().size(),1);
		assertEquals(collection.getPhoto(0),this.pChat);
		collection.addPhoto(pLapin);
		assertEquals(collection.getListePhotos().size(),2);
		assertEquals(collection.getPhoto(1),this.pLapin);
	}

	@Test
	public void testSplit() {
		char a = 'a';
		char b = 'b';
		System.out.println(a - b);
		Photo[] tab = {this.pChat,this.pLapin};
		Collection collection = new Collection("Test",tab);
		Collection[] split;
		
		// -- Tri par ordre alphabetique d'auteur --
		this.pChat.setAuteur("A");
		this.pLapin.setAuteur("B");
		collection.setTriAuteurAlpha();
		split = collection.split();
		assertEquals(2,split.length);
		assertEquals("A",split[0].getTitre());
		assertEquals("B",split[1].getTitre());
		// -- Tri par ordre anti-alphabetique d'auteur --
		collection.setTriAuteurAntiAlpha();
		split = collection.split();
		assertEquals(2,split.length);
		assertEquals("B",split[0].getTitre());
		assertEquals("A",split[1].getTitre());
		
		// -- Tri par ordre alphabetique de titre --
		this.pChat.setTitre("A");
		this.pLapin.setTitre("B");
		collection.setTriTitreAlpha();
		split = collection.split();
		assertEquals(2,split.length);
		assertEquals("A",split[0].getTitre());
		assertEquals("B",split[1].getTitre());
		// -- Tri par ordre anti-alphabetique de titre --
		collection.setTriTitreAntiAlpha();
		split = collection.split();
		assertEquals(2,split.length);
		assertEquals("B",split[0].getTitre());
		assertEquals("A",split[1].getTitre());
		
		// -- Tri par ordre alphabetique de pays --
		this.pChat.setPays("A");
		this.pLapin.setPays("B");
		collection.setTriPaysAlpha();
		split = collection.split();
		assertEquals(2,split.length);
		assertEquals("A",split[0].getTitre());
		assertEquals("B",split[1].getTitre());
		// -- Tri par ordre anti-alphabetique de pays --
		collection.setTriPaysAntiAlpha();
		split = collection.split();
		assertEquals(2,split.length);
		assertEquals("B",split[0].getTitre());
		assertEquals("A",split[1].getTitre());
		
		// -- Tri par ordre chronologique des dates --
		java.util.Calendar date1 = Calendar.getInstance();
		date1.set(2012, 12, 12);
		System.out.println(date1.toString());
		java.util.Calendar date2 = Calendar.getInstance();
		date2.set(2015, 1, 1);
		System.out.println(date2.toString());
		this.pChat.setDate(date1);
		this.pLapin.setDate(date2);
		collection.setTriDateCroissante();
		split = collection.split();
		assertEquals(2,split.length);
		//assertEquals("A",split[0].getTitre());
		//assertEquals("B",split[1].getTitre());
		// -- Tri par ordre anti-alphabetique de pays --
		collection.setTriDateDecroissante();
		split = collection.split();
		assertEquals(2,split.length);
		//assertEquals("B",split[0].getTitre());
		//assertEquals("A",split[1].getTitre());
	}

	@Test
	public void testDelPhotoPhoto() {
		 
	}

	@Test
	public void testDelPhotoInt() {
		 
	}

	@Test
	public void testDelPhotoString() {
		 
	}

	@Test
	public void testGetPhotoInt() {
		 
	}

	@Test
	public void testGetPhotoString() {
		 
	}

	@Test
	public void testGetTitre() {
		 
	}

	@Test
	public void testToutesPhotos() {
		 
	}

	@Test
	public void testGetListePhotos() {
		 
	}

	@Test
	public void testGetPhotoSelect() {
		 
	}

	@Test
	public void testGetTri() {
		 
	}

	@Test
	public void testSetTitre() {
		 
	}

	@Test
	public void testSetPhotoSelect() {
		 
	}

}

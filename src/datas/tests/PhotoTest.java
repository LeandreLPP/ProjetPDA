package datas.tests;

import datas.*;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.junit.*;

public class PhotoTest{
	private Photo pChat;
	private Photo pLapin;
	
	public PhotoTest(){
		super();
	}
	
	@Before()
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

	@Test()
	public void testConstructeur() {
		Assert.assertNotNull(pChat);
		Assert.assertNotNull(pLapin);
		BufferedImage img = null;
		File f = new File("data/testJunit/chat.jpg");
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			fail("L'image chat n'a pas ete cree correctement");
		}
		Assert.assertNotNull(img);
		f = new File("data/testJunit/lapin.jpg");
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			fail("L'image lapin n'a pas ete cree correctement");
		}
		Assert.assertNotNull(img);
	}
	
	@Test()
	public void testCheckTag(){
		Assert.assertTrue(this.pChat.checkTag());
		Assert.assertTrue(this.pLapin.checkTag());
	}
	
	@Test()
	public void testCourbeCouleur(){
		int[] courbeChat = this.pChat.courbeCouleur();
		for(int e : courbeChat){
			Assert.assertTrue(e>=0 && e<=100);
		}
	}
	
	@Test()
	public void testDifferenceCouleur(){
		Photo doubleChat = new Photo("data/chat.jpg","data/testJunit/doubleChat.jpg");
		int diffNulle = doubleChat.differenceCouleur(pChat);
		int diffNonNulle = this.pChat.differenceCouleur(pLapin);
		Assert.assertEquals(diffNulle, 0);
		Assert.assertTrue(diffNonNulle>=0 && diffNonNulle<=100);
		File f = new File("data/testJunit/doubleChat.jpg");
		f.delete();
	}
	
	@Test()
	public void testIsIdentique(){
		Photo doubleChat = new Photo("data/chat.jpg","data/testJunit/doubleChat.jpg");
		Assert.assertTrue(doubleChat.isIdentique(this.pChat));
		Assert.assertFalse(doubleChat.isIdentique(this.pLapin));
		File f = new File("data/testJunit/doubleChat.jpg");
		f.delete();
	}

	@Test()
	public void testSetters(){
		
	}
}

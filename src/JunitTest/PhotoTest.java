package JunitTest;

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
		try {
			this.pChat = new Photo("data/chat.jpg","data/testJunit/chat.jpg");
			this.pLapin = new Photo("data/lapin.jpg","data/testJunit/lapin.jpg");
		} catch (IOException e) {
			fail();
		}
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
			Assert.assertTrue(e>=0);
		}
	}
	
	@Test()
	public void testDifferenceCouleur(){
		Photo doubleChat = null;
		Photo chatTag = null;
		Photo noir = null;
		Photo blanc = null;
		try {
			doubleChat = new Photo("data/chat.jpg","data/testJunit/doubleChat.jpg");
			chatTag = new Photo("data/chatTag.jpg","data/testJunit/chatTag.jpg");
			noir = new Photo("data/noir.jpg","data/testJunit/noir.jpg");
			blanc = new Photo("data/blanc.jpg","data/testJunit/blanc.jpg");
		} catch (IOException e) {
			fail();
		}
		double diffNulle = doubleChat.differenceCouleur(pChat);
		double diffFaible = chatTag.differenceCouleur(pChat);
		double diffForte = this.pChat.differenceCouleur(pLapin);
		double diffTotale = noir.differenceCouleur(blanc);
		Assert.assertEquals(0, diffNulle, 0);
		Assert.assertTrue(diffFaible>=0 && diffFaible<=100);
		Assert.assertTrue(diffForte>=0 && diffForte<=100);
		Assert.assertTrue(diffForte>diffFaible);
		Assert.assertEquals(75, diffTotale, 1);
		File f = new File("data/testJunit/doubleChat.jpg");
		f.delete();
	}
	
	@Test()
	public void testDifferenceContour(){
		Photo doubleChat = null;
		Photo chatTag = null;
		Photo noir = null;
		Photo blanc = null;
		try {
			doubleChat = new Photo("data/chat.jpg","data/testJunit/doubleChat.jpg");
			chatTag = new Photo("data/chatTag.jpg","data/testJunit/chatTag.jpg");
			noir = new Photo("data/noir.jpg","data/testJunit/noir.jpg");
			blanc = new Photo("data/blanc.jpg","data/testJunit/blanc.jpg");
		} catch (IOException e) {
			fail();
		}
		double diffNulle = doubleChat.differenceContours(pChat);
		double diffFaible = chatTag.differenceContours(pChat);
		double diffForte = this.pChat.differenceContours(pLapin);
		double diffTotale = noir.differenceContours(blanc);
		Assert.assertEquals(0, diffNulle, 0);
		Assert.assertTrue(diffFaible>=0 && diffFaible<=100);
		Assert.assertTrue(diffForte>=0 && diffForte<=100);
		Assert.assertTrue(diffForte>diffFaible);
		Assert.assertEquals(0, diffTotale, 1);
		File f = new File("data/testJunit/doubleChat.jpg");
		f.delete();
	}
	
	@Test()
	public void testWaterMark(){
		BufferedImage bf;
		try {
			bf = this.pChat.waterMark("Test");
			ImageIO.write(bf, "png", new File("data/testJunit/testWatermark.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test()
	public void testIsIdentique(){
		Photo doubleChat = null;
		try {
			doubleChat = new Photo("data/chat.jpg","data/testJunit/doubleChat.jpg");
		} catch (IOException e) {
			fail();
		}
		Assert.assertTrue(doubleChat.isIdentique(this.pChat));
		Assert.assertFalse(doubleChat.isIdentique(this.pLapin));
		File f = new File("data/testJunit/doubleChat.jpg");
		f.delete();
	}
}

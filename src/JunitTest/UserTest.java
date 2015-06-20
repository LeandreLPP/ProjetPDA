package JunitTest;

import static org.junit.Assert.*;

import java.io.File;

import datas.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private Photo[] listePhotos;
	private User user;

	@Before
	public void setUp() throws Exception {
		this.listePhotos = new Photo[20];
		for(int i = 0; i<this.listePhotos.length; i+=2){
			this.listePhotos[i] = new Photo("data/chat.jpg",("data/testJunit/chat"+i+".jpg"));
			this.listePhotos[i+1] = new Photo("data/lapin.jpg",("data/testJunit/lapin"+(i+1)+".jpg"));
		}
		this.user = new User("User","data/testJunit/saves");
		for(Photo p : this.listePhotos){
			this.user.importerPhoto(p.getImageURL());
		}
	}

	@After
	public void tearDown() throws Exception {
		for(Photo p : this.listePhotos){
			File f = new File(p.getImageURL());
			f.delete();
		}
		this.listePhotos = null;
		this.user = null;
	}

	@Test
	public void testUser() {
		User userTest = new User();
		assertNotNull(userTest);
		assertEquals("default",userTest.getNom());
		assertEquals("saves/default",userTest.getUrlDossier());
		java.io.File dossier = new java.io.File(userTest.getUrlDossier());
		assertTrue(dossier.isDirectory());
		assertEquals("All",userTest.getAllPhotos().getTitre());
		File f = new File("saves");
		f.delete();
	}

	@Test
	public void testUserString() {
		User userTest = new User("test");
		assertNotNull(userTest);
		assertEquals("test",userTest.getNom());
		assertEquals("saves/test",userTest.getUrlDossier());
		java.io.File dossier = new java.io.File(userTest.getUrlDossier());
		assertTrue(dossier.isDirectory());
		assertEquals("All",userTest.getAllPhotos().getTitre());
		File f = new File("saves");
		f.delete();
	}

	@Test
	public void testUserStringString() {
		String url = "data/testJunit";
		User userTest = new User("test",url);
		assertNotNull(userTest);
		assertEquals("test",userTest.getNom());
		assertEquals(url+"/test",userTest.getUrlDossier());
		java.io.File dossier = new java.io.File(userTest.getUrlDossier());
		assertTrue(dossier.isDirectory());
		assertEquals("All",userTest.getAllPhotos().getTitre());
		File f = new File(url+"/test");
		f.delete();
	}

	@Test
	public void testAddCollection() {
		assertNull(this.user.getCollection("TestCollection"));
		this.user.addCollection("TestCollection");
		assertNotNull(this.user.getCollection("TestCollection"));
	}

	@Test
	public void testDelCollection() {
		assertNull(this.user.getCollection("TestCollection"));
		this.user.addCollection("TestCollection");
		assertNotNull(this.user.getCollection("TestCollection"));
		this.user.delCollection("TestCollection");
		assertNull(this.user.getCollection("TestCollection"));
	}

	@Test
	public void testMovePhoto() {
		this.user.addCollection("Chat");
		String key = "data/testJunit/saves/User/chat0.jpg";
		this.user.movePhoto(key, "All", "Chat");
		try {
			assertEquals(this.listePhotos[0].getTitre(),this.user.getCollection("Chat").getPhoto(key).getTitre());
			assertEquals(this.listePhotos[0].getTitre(),this.user.getAllPhotos().getPhoto(key).getTitre());
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
		this.user.movePhoto(key, "Chat", "All");
		try {
			assertEquals(this.listePhotos[0].getTitre(),this.user.getAllPhotos().getPhoto(key).getTitre());
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
		try {
			assertNull(this.user.getCollection("Chat").getPhoto(key));
			fail();
		} catch (NoPhotoFoundException e) {}
	}

	@Test
	public void testDelPhoto() {
		this.user.addCollection("Chat");
		String key = "data/testJunit/saves/User/chat0.jpg";
		this.user.movePhoto(key, "All", "Chat");
		this.user.delPhoto(key);
		try {
			assertNull(this.user.getAllPhotos().getPhoto(key));
			fail();
		} catch (NoPhotoFoundException e) {}
		try {
			assertNull(this.user.getCollection("Chat").getPhoto(key));
			fail();
		} catch (NoPhotoFoundException e) {}
	}

	@Test
	public void testImporterPhoto() {
		this.user.importerPhoto("data/chat.jpg");
		this.user.importerPhoto("data/gif.gif");
		this.user.importerPhoto("data/png.png");
		try {
			assertNotNull(this.user.getAllPhotos().getPhoto("data/testJunit/saves/User/chat.jpg"));
			assertNotNull(this.user.getAllPhotos().getPhoto("data/testJunit/saves/User/gif.gif"));
			assertNotNull(this.user.getAllPhotos().getPhoto("data/testJunit/saves/User/png.png"));
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testToutesCollections() {
		java.util.Enumeration<Collection> enumCollec = this.user.toutesCollections();
		int i = 0;
		while(enumCollec.hasMoreElements()){
			i++;
			enumCollec.nextElement();
		}
		assertEquals(0,i);
		this.user.addCollection("A");
		this.user.addCollection("B");
		enumCollec = this.user.toutesCollections();
		i = 0;
		while(enumCollec.hasMoreElements()){
			i++;
			enumCollec.nextElement();
		}
		assertEquals(2,i);
	}

	@Test
	public void testSauver() {
		this.user.sauver();
		User testSauver = User.charger("data/testJunit/saves/User.out");
		assertNotNull(testSauver);
		try {
			assertNotNull(testSauver.getAllPhotos().getPhoto(0).getImg());
		} catch (NoPhotoFoundException e) {
			fail();
		}
	}

	@Test
	public void testSauverString() {
		String url = "data/testJunit/User.out";
		this.user.sauver(url);
		User testSauver = User.charger(url);
		assertNotNull(testSauver);
		try {
			assertNotNull(testSauver.getAllPhotos().getPhoto(0).getImg());
		} catch (NoPhotoFoundException e) {
			fail();
		}
	}
}

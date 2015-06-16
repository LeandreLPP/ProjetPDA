package datas;

import java.io.*;
import java.util.Hashtable;

public class User implements Serializable {
	private static final long serialVersionUID = 0;
	private String nom;
	private String urlDossier;
	private Collection allPhotos;
	private Hashtable<String,Collection> collections;

	public User(){
		this.nom = "default";
		this.urlDossier = "datas/"+nom;
		this.allPhotos = new Collection("All");
		this.collections = new Hashtable<String,Collection>();
	}

	public User(String nom){
		this.nom = nom;
		this.urlDossier = "datas/"+nom;
		this.allPhotos = new Collection("All");
		this.collections = new Hashtable<String,Collection>();
	}

	public User(String nom, String url){
		this.nom = nom;
		this.urlDossier = url+"/"+nom;
		this.allPhotos = new Collection("All");
		this.collections = new Hashtable<String,Collection>();
	}

	// --- Methodes ---

	public void addCollection(String key){
		if(key != null && !key.equalsIgnoreCase("All") && !this.collections.containsKey(key)){
			Collection c = new Collection(key);
			this.collections.put(key,c);
		}
	}

	public void delCollection(String key){
		if(key != null && !key.equalsIgnoreCase("All")){
			this.collections.remove(key);
		}
	}

	public void movePhoto(String keyOrigine, String keyPhoto, String keyDestination){
		if(keyOrigine != null && keyPhoto != null && keyDestination != null && !keyOrigine.equals(keyDestination)){
			if (this.collections.containsKey(keyOrigine) && this.collections.containsKey(keyDestination)){
				try {
					this.collections.get(keyDestination).addPhoto(this.collections.get(keyOrigine).getPhoto(keyPhoto));
					this.collections.get(keyOrigine).delPhoto(keyPhoto);
				} catch (NoPhotoFoundException e) {
					e.printStackTrace();
				}
			} else if (keyDestination.equalsIgnoreCase("All") && this.collections.containsKey(keyOrigine)){
				try {
					this.collections.get(keyOrigine).delPhoto(keyPhoto);
				} catch (NoPhotoFoundException e) {
					e.printStackTrace();
				}
			} else if (keyOrigine.equalsIgnoreCase("All") && this.collections.containsKey(keyDestination)){
				try {
					this.collections.get(keyDestination).addPhoto(this.allPhotos.getPhoto(keyPhoto));
				} catch (NoPhotoFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void delPhoto(String keyPhoto){
		try {
			this.allPhotos.delPhoto(keyPhoto);
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
		File f = new File(keyPhoto);
		f.delete();
		for(String e : this.collections.keySet()){
			try {
				this.collections.get(e).delPhoto(keyPhoto);
			} catch (NoPhotoFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void importerPhoto(String url){
		String nom = url.split("/")[url.split("/").length-1];
		String newUrl = this.urlDossier+"/"+nom;
		Photo p = new Photo(url,newUrl);
		this.allPhotos.addPhoto(p);
	}

	// --- Sauver et Charger ---
	public void sauver(){
		this.sauver(this.urlDossier+".out");
	}

	public void sauver(String url){
		FileOutputStream file;
		ObjectOutputStream flux;
		try {
			file = new FileOutputStream(url); 
			flux = new ObjectOutputStream(file);
			flux.writeObject(this);
			flux.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static User charger(String url){
		FileInputStream file;
		ObjectInputStream flux;
		User ret = null;
		try {
			file = new FileInputStream(url);
			flux = new ObjectInputStream(file);
			ret = (User) flux.readObject();
			flux.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return ret;
	}
}

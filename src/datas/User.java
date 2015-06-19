package datas;

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class User implements Serializable {
	private static final long serialVersionUID = 0;
	private String nom;
	private String urlDossier;
	private Collection allPhotos;
	private Hashtable<String,Collection> collections;

	public User(){
		this.nom = "default";
		this.urlDossier = "saves/"+this.nom;
		File dossier = new File(this.urlDossier);
		dossier.mkdirs();
		this.allPhotos = new Collection("All");
		this.collections = new Hashtable<String,Collection>();
	}

	public User(String nom){
		this.nom = nom;
		this.urlDossier = "saves/"+nom;
		File dossier = new File(this.urlDossier);
		dossier.mkdirs();
		this.allPhotos = new Collection("All");
		this.collections = new Hashtable<String,Collection>();
	}

	public User(String nom, String url){
		this.nom = nom;
		this.urlDossier = url+"/"+nom;
		File dossier = new File(this.urlDossier);
		dossier.mkdirs();
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

	public void movePhoto(String keyPhoto, String keyOrigine,  String keyDestination){
		String key = this.urlDossier+"/"+keyPhoto;
		if(keyOrigine != null && key != null && keyDestination != null && !keyOrigine.equals(keyDestination)){
			if (this.collections.containsKey(keyOrigine) && this.collections.containsKey(keyDestination)){
				try {
					this.collections.get(keyDestination).addPhoto(this.collections.get(keyOrigine).getPhoto(key));
					this.collections.get(keyOrigine).getPhoto(key).setCollection(this.collections.get(keyDestination).getTitre());
					this.collections.get(keyOrigine).delPhoto(key);
				} catch (NoPhotoFoundException e) {
					e.printStackTrace();
				}
			} else if (keyDestination.equalsIgnoreCase("All") && this.collections.containsKey(keyOrigine)){
				try {
					this.collections.get(keyOrigine).delPhoto(key);
					this.allPhotos.getPhoto(key).setCollection("All");
				} catch (NoPhotoFoundException e) {
					e.printStackTrace();
				}
			} else if (keyOrigine.equalsIgnoreCase("All") && this.collections.containsKey(keyDestination)){
				try {
					this.collections.get(keyDestination).addPhoto(this.allPhotos.getPhoto(key));
					this.allPhotos.getPhoto(key).setCollection(this.collections.get(keyDestination).getTitre());
				} catch (NoPhotoFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void delPhoto(String keyPhoto){
		String key = this.urlDossier+keyPhoto;
		try {
			this.allPhotos.delPhoto(key);
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
		File f = new File(key);
		f.delete();
		for(String e : this.collections.keySet()){
			try {
				this.collections.get(e).delPhoto(key);
			} catch (NoPhotoFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	public boolean importerPhoto(String url){
		boolean ret = true;
		String nom = url.split("/")[url.split("/").length-1];
		String newUrl = this.urlDossier+"/"+nom;
		Photo p;
		try {
			p = new Photo(url,newUrl);
			this.allPhotos.addPhoto(p);
		} catch (IOException e) {
			ret = false;
		}
		return ret;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @return the urlDossier
	 */
	public String getUrlDossier() {
		return this.urlDossier;
	}

	/**
	 * @return the allPhotos
	 */
	public Collection getAllPhotos() {
		return this.allPhotos;
	}
	
	public Collection getCollection(String key){
		return this.collections.get(key);
	}

	/**
	 * @return the collections
	 */
	public Enumeration<Collection> toutesCollections() {
		return this.collections.elements();
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param urlDossier the urlDossier to set
	 */
	public void setUrlDossier(String urlDossier) {
		this.urlDossier = urlDossier;
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

package datas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import utilitaires.*;

public class Collection implements Serializable {
	private static final long serialVersionUID = 0;

	private String titre;
	private Hashtable<String,Photo> mapPhotos;
	private ArrayList<Photo> listePhotos;
	private int photoSelect;
	private Tri tri;

	public Collection(String titre, Photo[] listePhoto){
		this.titre = titre;
		this.mapPhotos = new Hashtable<String,Photo>();
		this.listePhotos = new ArrayList<Photo>();
		for(Photo p : listePhoto){
			this.addPhoto(p);
		}
		this.photoSelect = 0;
		this.tri = new TriTitreAlpha(this.listePhotos);
		this.trier();
	}

	public Collection(String titre){
		this.titre = titre;
		this.mapPhotos = new Hashtable<String,Photo>();
		this.listePhotos = new ArrayList<Photo>();
		this.photoSelect = 0;
		this.tri = new TriTitreAlpha(this.listePhotos);
		this.trier();
	}

	public void trier(){
		this.tri.trier();
	}

	public void addPhoto(Photo p){
		this.listePhotos.add(p);
		this.mapPhotos.put(p.getImageURL(), p);
		this.updateTri();
	}

	private void updateTri(){
		ArrayList<Photo> t = new ArrayList<Photo>();
		try {
			this.tri.getClass().getConstructor(t.getClass()).newInstance(this.listePhotos);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}

	public Collection[] split(){
		Hashtable<Integer, Object[]> tabDeTab = this.tri.split();
		Collection[] ret = new Collection[tabDeTab.size()];
		for(int i : tabDeTab.keySet()){
			Collection t = new Collection((String)tabDeTab.get(i)[0], (Photo[])tabDeTab.get(i)[1]);
			ret[i] = t;
		}
		return ret;
	}
	
	//---Del---
	public void delPhoto(Photo p){
		this.mapPhotos.remove(p.getImageURL());
		this.listePhotos.remove(p);
		this.updateTri();
	}

	public void delPhoto(int index){
		this.delPhoto(this.getPhoto(index));
	}

	public void delPhoto(String key){
		this.delPhoto(this.getPhoto(key));
	}

	//---Get---
	public Photo getPhoto(int index){
		Photo ret = null;
		if(index>= 0 && index < this.listePhotos.size()){
			ret = this.listePhotos.get(index);
		}
		return ret;
	}

	public Photo getPhoto(String key){
		return this.mapPhotos.get(key);
	}

	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @return the mapPhotos
	 */
	public Enumeration<Photo> toutesPhotos() {
		return mapPhotos.elements();
	}

	/**
	 * @return the listePhotos
	 */
	public ArrayList<Photo> getListePhotos() {
		return listePhotos;
	}

	/**
	 * @return the photoSelect
	 */
	public int getPhotoSelect() {
		return photoSelect;
	}

	/**
	 * @return the tri
	 */
	public Tri getTri() {
		return tri;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @param photoSelect the photoSelect to set
	 */
	public void setPhotoSelect(int photoSelect) {
		this.photoSelect = photoSelect;
	}

	public void setTriTitreAlpha(){
		this.tri = new TriTitreAlpha(this.listePhotos);
	}

	public void setTriTitreAntiAlpha(){
		this.tri = new TriTitreAntiAlpha(this.listePhotos);
	}

	public void setTriAuteurAlpha(){
		this.tri = new TriAuteurAlpha(this.listePhotos);
	}

	public void setTriAuteurAntiAlpha(){
		this.tri = new TriAuteurAntiAlpha(this.listePhotos);
	}

	public void setTriDateCroissante(){
		this.tri = new TriDateCroissante(this.listePhotos);
	}

	public void setTriDateDecroissante(){
		this.tri = new TriDateDecroissante(this.listePhotos);
	}

	public void setTriPaysAlpha(){
		this.tri = new TriPaysAlpha(this.listePhotos);
	}

	public void setTriPaysAntiAlpha(){
		this.tri = new TriPaysAntiAlpha(this.listePhotos);
	}

	// --- Sauver et Charger ---
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

	public static Collection charger(String url){
		FileInputStream file;
		ObjectInputStream flux;
		Collection ret = null;
		try {
			file = new FileInputStream(url);
			flux = new ObjectInputStream(file);
			ret = (Collection) flux.readObject();
			flux.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ret;
	}
}

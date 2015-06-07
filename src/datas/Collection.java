package datas;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


public class Collection {
	private String titre;
	private Hashtable<String,Photo> mapPhotos;
	private ArrayList<Photo> listePhotos;
	private int photoSelect;
	
	public Collection(String titre, Photo[] listePhoto){
		
	}
	
	public Collection(String titre){
		
	}
	
	public void addPhoto(Photo p){
		
	}
	
	public void delPhoto(Photo p){
		
	}
	
	public void delPhoto(int index){
		
	}
	
	public void delPhoto(String key){
		
	}
	
	public void getPhoto(Photo p){
		
	}
	
	public void getPhoto(int index){
		
	}
	
	public void getPhoto(String key){
		
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
	
	
}

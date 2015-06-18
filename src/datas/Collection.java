package datas;

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
	private int indexSelect;
	private Tri tri;

	public Collection(String titre){
		this.titre = titre;
		this.mapPhotos = new Hashtable<String,Photo>();
		this.listePhotos = new ArrayList<Photo>();
		this.indexSelect = 0;
		this.tri = new TriTitreAlpha(this.listePhotos);
		this.trier();
	}

	public Collection(String titre, Photo[] listePhoto){
		this.titre = titre;
		this.mapPhotos = new Hashtable<String,Photo>();
		this.listePhotos = new ArrayList<Photo>();
		this.indexSelect = 0;
		this.tri = new TriTitreAlpha(this.listePhotos);
		for(Photo p : listePhoto){
			this.addPhoto(p);
		}
	}

	public void trier(){
		this.tri.trier();
	}

	public void addPhoto(Photo p){
		this.listePhotos.add(p);
		p.setCollection(this.titre);
		this.mapPhotos.put(p.getImageURL(), p);
		this.updateTri();
	}

	private void updateTri(){
		ArrayList<Photo> t = new ArrayList<Photo>();
		if(this.tri.getClass() == TriSimilarite.class){
			this.tri = new TriSimilarite(this);
		} else {
			try {
				this.tri.getClass().getConstructor(t.getClass()).newInstance(this.listePhotos);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				e.printStackTrace();
			}
		}
		this.trier();
	}

	public Collection[] split(){
		Hashtable<Integer, Object[][]> tabDeTab = this.tri.split();
		Collection[] ret = new Collection[tabDeTab.size()];
		for(int i : tabDeTab.keySet()){
			Collection t = new Collection((String)tabDeTab.get(i)[0][0], this.toPhoto(tabDeTab.get(i)[1]));
			ret[i] = t;
		}
		return ret;
	}

	private Photo[] toPhoto(Object[] tab){
		Photo[] rep = new Photo[tab.length];
		int i = 0;
		for(Object o : tab){
			rep[i] = (Photo)o;
			i++;
		}
		return rep;
	}

	//---Del---
	public void delPhoto(Photo p){
		this.mapPhotos.remove(p.getImageURL());
		this.listePhotos.remove(p);
		this.updateTri();
	}

	public void delPhoto(int index) throws NoPhotoFoundException{
		this.delPhoto(this.getPhoto(index));
	}

	public void delPhoto(String key) throws NoPhotoFoundException{
		this.delPhoto(this.getPhoto(key));
	}

	//---Get---
	public Photo getPhoto(int index) throws NoPhotoFoundException{
		Photo ret = null;
		try{
			ret = this.listePhotos.get(index);
		} catch (IndexOutOfBoundsException e) {
			if(this.listePhotos.size()>0){
				if(index<0){
					ret = this.listePhotos.get(0);
				} else {
					ret = this.listePhotos.get(this.listePhotos.size()-1);
				}
			} else throw new NoPhotoFoundException();
		}
		return ret;
	}

	public Photo getPhoto(String key) throws NoPhotoFoundException{
		Photo rep = this.mapPhotos.get(key);
		if (rep == null){
			throw new NoPhotoFoundException();
		}
		return rep;
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
	public int getIndexSelect() {
		return indexSelect;
	}

	/**
	 * @return the tri
	 */
	public Tri getTri() {
		return tri;
	}

	public Photo getPhotoSelect() throws NoPhotoFoundException{
		return this.getPhoto(this.getIndexSelect());
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
	public void setIndexSelect(int photoSelect) {
		if(photoSelect >= this.listePhotos.size()){
			this.indexSelect = 0;
		} else if(photoSelect <0) {
			this.indexSelect = this.listePhotos.size()-1;
		} else {
			this.indexSelect = photoSelect;
		}
	}

	/**
	 * @param photoSelect the photoSelect to set
	 * @throws NoPhotoFoundException 
	 */
	public void setIndexSelect(String key) throws NoPhotoFoundException {
		Photo select = this.getPhoto(key);
		this.indexSelect = this.listePhotos.indexOf(select);
	}

	public void nextPhoto(){
		this.setIndexSelect(this.getIndexSelect()+1);
	}

	public void prevPhoto(){
		this.setIndexSelect(this.getIndexSelect()-1);
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

	public void setTriCollectionAlpha(){
		this.tri = new TriCollectionAlpha(this.listePhotos);
	}

	public void setTriCollectionAntiAlpha(){
		this.tri = new TriCollectionAntiAlpha(this.listePhotos);
	}

	public void setTriSimilarite(int index){
		this.setIndexSelect(index);
		this.tri = new TriSimilarite(this);
	}
}

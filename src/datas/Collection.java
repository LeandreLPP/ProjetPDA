package datas;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import utilitaires.*;

/**
 * Classe implementant les fonctionnalites pour la gestion et la manipulation de groupes de photos dans l'application.
 * @author FRETAY Juliette et LE POLLES--POTIN Leandre - Groupe 1C
 */
public class Collection implements Serializable {
	private static final long serialVersionUID = 0;
	
	/**
	 * Le titre de cette collection.
	 */
	private String titre;
	
	/**
	 * La {@link Hashtable} repertoriant toutes les photos contenues dans cette collection.
	 * La cle est l'attribut de {@link Photo} recupere par {@link Photo#getImageURL()}
	 */
	private Hashtable<String,Photo> mapPhotos;
	
	/**
	 * La liste des {@link Photo} contenues par cette collection, ordonnees selont un tri particulier.
	 */
	private ArrayList<Photo> listePhotos;
	
	/**
	 * L'object {@link Photo} selectionne actuellement.
	 * Cet attribut est utilise lors de l'affichage en mode diaporama de la collection.
	 */
	private int indexSelect;
	
	/**
	 * Le tri selon lequel ordonner la {@link #listePhotos}.
	 */
	private Tri tri;

	/**
	 * Contruit une collection vide.
	 * @param titre Le titre de la collection a creer.
	 */
	public Collection(String titre){
		this.titre = titre;
		this.mapPhotos = new Hashtable<String,Photo>();
		this.listePhotos = new ArrayList<Photo>();
		this.indexSelect = 0;
		this.tri = new TriTitreAlpha(this.listePhotos);
	}

	/**
	 * Construit une collection contenant un certain nombre de photo.
	 * @param titre Le titre de la collection a creer.
	 * @param listePhoto Un tableau contenant tous les objet de type {@link Photo} a ajouter a cette collection.
	 */
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
	
	/**
	 * Tri la {@link #listePhotos} grace a la methode de {@link #tri}.
	 */
	public void trier(){
		this.tri.trier();
	}

	/**
	 * Ajoute une photo a cette collection.
	 * @param p La photo a ajouter.
	 */
	public void addPhoto(Photo p){
		this.listePhotos.add(p);
		this.mapPhotos.put(p.getImageURL(), p);
		this.updateTri();
	}

	/**
	 * Met a jour le tri lors de l'ajout ou la suppression d'une photo de la collection.
	 */
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

	/**
	 * Accesseur permettant de recuperer la {@link #listePhotos} sous la forme d'un tableau de collection.
	 * Chacune de ces collection contient toutes les photos de cette collection possedant le meme critere de tri.
	 * Cela permet d'organiser l'affichage en sous-categorie plus lisible pour l'utilisateur.
	 * @return Le tableau des collections.
	 */
	public Collection[] split(){
		Hashtable<Integer, Object[][]> tabDeTab = this.tri.split();
		Collection[] ret = new Collection[tabDeTab.size()];
		if(this.tri.getClass() != TriSimilarite.class){
			for(int i : tabDeTab.keySet()){
				Collection t = new Collection((String)tabDeTab.get(i)[0][0], this.toPhoto(tabDeTab.get(i)[1]));
				ret[i] = t;
			}
		} else {
			ret[0] = this;
		}
		return ret;
	}

	/**
	 * Methode privee utilisee dans {@link #split()} pour contvertir un tableau d"Object en {@link Photo}.
	 * @param tab Le tableau d'Object a convertir.
	 * @return Le tableau de {@link Photo} converti.
	 */
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
	/**
	 * Supprime de la collection l'objet {@link Photo} passe en parametre
	 * @param p L'objet {@link Photo} a suppriemr de cette collection.
	 */
	public void delPhoto(Photo p){
		this.mapPhotos.remove(p.getImageURL());
		this.listePhotos.remove(p);
		this.updateTri();
	}

	/**
	 * Supprime de la collection l'objet {@link Photo} a l'index passe en parametre.
	 * @param index L'index de l'objet {@link Photo} a supprimer dans la {@link #listePhotos}.
	 * @throws NoPhotoFoundException Si l'index passe en parametre est incorrect.
	 */
	public void delPhoto(int index) throws NoPhotoFoundException{
		this.delPhoto(this.getPhoto(index));
	}

	/**
	 * Supprime de la collection l'objet {@link Photo} lie a la cle passee en parametre
	 * @param key L'url de la photo dont on doit supprimer l'objet associe de la collection.
	 * @throws NoPhotoFoundException Si la cle passe est incorrecte.
	 */
	public void delPhoto(String key) throws NoPhotoFoundException{
		this.delPhoto(this.getPhoto(key));
	}

	//---Get---

	/**
	 * Retourne l'objet {@link Photo} situe a l'index passe en parametre.
	 * @param index L'index de l'objet {@link Photo} a rechercher dans la {@link #listePhotos}.
	 * @throws NoPhotoFoundException Si l'index passe en parametre est incorrect.
	 * @return L'objet {@link Photo} trouve.
	 */
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

	/**
	 * Retourne l'objet {@link Photo} lie a la cle passee en parametre
	 * @param key L'url de la photo que l'on doit retourner.
	 * @throws NoPhotoFoundException Si la cle passe est incorrecte.
	 */
	public Photo getPhoto(String key) throws NoPhotoFoundException{
		Photo rep = this.mapPhotos.get(key);
		if (rep == null){
			throw new NoPhotoFoundException();
		}
		return rep;
	}
	
	/**
	 * Accesseur de l'attribut {@link #titre}.
	 * @return Le {@link String} titre.
	 */
	public String getTitre() {
		return titre;
	}
	
	/**
	 * @return Une {@link Enumeration} contenant toutes les photos de cette collection.
	 */
	public Enumeration<Photo> toutesPhotos() {
		return mapPhotos.elements();
	}
	
	/**
	 * Accesseur de l'attribut {@link #listePhotos}.
	 * @return La {@link ArrayList} listePhotos.
	 */
	public ArrayList<Photo> getListePhotos() {
		return listePhotos;
	}
	
	/**
	 * Accesseur de l'attribut {@link #indexSelect}.
	 * @return L'entier indexSelect.
	 */
	public int getIndexSelect() {
		return indexSelect;
	}
	
	/**
	 * Accesseur de l'attribut {@link #tri}.
	 * @return L'attribut de type {@link Tri}.
	 */
	public Tri getTri() {
		return tri;
	}
	
	/**
	 * @return La photo situe a l'index {@link #indexSelect} dans la {@link #listePhotos}.
	 */
	public Photo getPhotoSelect() throws NoPhotoFoundException{
		return this.getPhoto(this.getIndexSelect());
	}

	/**
	 * Modificateur de l'attibut {@link #titre} 
	 * @param titre Le titre de cette collection.
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Modificateur de l'attibut {@link #indexSelect}.
	 * Si l'index est en dehors de la liste, il est automatiquement regle sur le debut ou la fin de la liste (pour faire une boucle).
	 * @param photoSelect L'index de la photo a selectionner.
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
	 * Modificateur de l'attibut {@link #indexSelect}.
	 * @param key La cle de la photo a selectionner.
	 * @throws NoPhotoFoundException Si la cle passe en parametre ne correspond a aucune photo.
	 */
	public void setIndexSelect(String key) throws NoPhotoFoundException {
		Photo select = this.getPhoto(key);
		this.indexSelect = this.listePhotos.indexOf(select);
	}

	/**
	 * Incremente {@link #indexSelect}.
	 */
	public void nextPhoto(){
		this.setIndexSelect(this.getIndexSelect()+1);
	}

	/**
	 * Decremente {@link #indexSelect}.
	 */
	public void prevPhoto(){
		this.setIndexSelect(this.getIndexSelect()-1);
	}

	/**
	 * Trie les photos par ordre alphabetique de leur titre.
	 */
	public void setTriTitreAlpha(){
		this.tri = new TriTitreAlpha(this.listePhotos);
		this.trier();
	}

	/**
	 * Trie les photos par ordre anti-alphabetique de leur titre.
	 */
	public void setTriTitreAntiAlpha(){
		this.tri = new TriTitreAntiAlpha(this.listePhotos);
		this.trier();
	}

	/**
	 * Trie les photos par ordre alphabetique de leur auteur.
	 */
	public void setTriAuteurAlpha(){
		this.tri = new TriAuteurAlpha(this.listePhotos);
		this.trier();
	}

	/**
	 * Trie les photos par ordre anti-alphabetique de leur auteur.
	 */
	public void setTriAuteurAntiAlpha(){
		this.tri = new TriAuteurAntiAlpha(this.listePhotos);
		this.trier();
	}

	/**
	 * Trie les photos par ordre croissant de leur date de prise de vue.
	 */
	public void setTriDateCroissante(){
		this.tri = new TriDateCroissante(this.listePhotos);
		this.trier();
	}

	/**
	 * Trie les photos par ordre decroissant de leur date de prise de vue.
	 */
	public void setTriDateDecroissante(){
		this.tri = new TriDateDecroissante(this.listePhotos);
		this.trier();
	}

	/**
	 * Trie les photo par ordre alphabetique des noms de pays de prise de vue.
	 */
	public void setTriPaysAlpha(){
		this.tri = new TriPaysAlpha(this.listePhotos);
		this.trier();
	}

	/**
	 * Trie les photo par ordre anti-alphabetique des noms de pays de prise de vue.
	 */
	public void setTriPaysAntiAlpha(){
		this.tri = new TriPaysAntiAlpha(this.listePhotos);
		this.trier();
	}

	/**
	 * Trie les photo par ordre alphabetique des noms de collection auquels elles apartiennent.
	 */
	public void setTriCollectionAlpha(){
		this.tri = new TriCollectionAlpha(this.listePhotos);
		this.trier();
	}

	/**
	 * Trie les photo par ordre anti-alphabetique des noms de collection auquels elles apartiennent.
	 */
	public void setTriCollectionAntiAlpha(){
		this.tri = new TriCollectionAntiAlpha(this.listePhotos);
		this.trier();
	}

	/**
	 * Trie les photos par ordre decrosisant de similarite avec la photo situe a l'index.
	 * @param index L'index de la photo dont on recherche les similaires.
	 */
	public void setTriSimilarite(int index){
		this.setIndexSelect(index);
		this.tri = new TriSimilarite(this);
		this.trier();
	}
}

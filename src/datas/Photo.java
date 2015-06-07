package datas;

import java.util.ArrayList;
import java.util.Date;

public class Photo {
	private String imageURL;
	private String titre;
	private String auteur;
	private String pays;
	private Date date;
	private long gpsLatitude;
	private long gpsLongitude;
	private ArrayList<String> keyWords;
	private String[] tag;
	
	/**
	 * Constructeur sans parametre de la classe photo
	 * Les attributs sont modifies par la suite par les getteurs/setteurs
	 */
	public Photo(){
		
	}
	
	/**
	 * Copie l'etat d'une photo dans un nouvel objet photo, excepte l'url qui est passe en paramtre
	 * @param url L'url de la nouvelle photo
	 * @return Une nouvelle photo, aux attributs identiques a celle-ci.
	 */
	public Photo copier(String url){
		Photo ret = null;
		return ret;
	}
	
	/**
	 * Genere le tag de securite de la photo
	 */
	private void generateTag(){
		
	}
	
	/**
	 * Verifie que le tag de securite correspond bien a la photo de l'url 
	 * @return True si la photo correspond au tag, false sinon.
	 */
	public boolean checkTag(){
		boolean ret = true;
		return ret;
	}
	
	/**
	 * Compare les courbes de couleur des deux photographies.
	 * @param p2 la photo a comparer
	 * @return Le quotient de difference entre 0 et 100. 
	 * 0 signifie que les courbes de couleurs sont identiques, 100 signifie que les courbes de couleurs sont totalement differentes.
	 */
	public int differenceCouleur(Photo p2){
		return 0;
	}
	
	/**
	 * Genere la courbe de couleur sous la forme d'un tableau de 765 entiers entre 0 et 100.
	 * Un entier pour chacune des 255 nuances de rouge, vert et bleu. 
	 * Si l'entier vaut 0, cela veut dire que aucun pixel de l'image ne possede cette nuance de couleur.
	 * Un pixel etant code un code RGB (Red Green Blue), chacun possede trois nuances : une rouge, une verte et unee bleue, codee chacune sur 255 
	 * @return Un tableau de 765 entiers allant de 0 a 100.
	 */
	public int[] courbeCouleur(){
		int[] ret = new int[765];
		return ret;
	}
	
	/**
	 * Compare cette photographie et verifie si elle est identiques (au pixel pres) a celle passee en parametre.
	 * @param p2 La photographie a comparer
	 * @return True si les deux photogrpahies sont strictement identiques (au pixel pres), false sinon.
	 */
	public boolean isIdentique(Photo p2){
		boolean ret = true;
		return ret;
	}
	
	/**
	 * Compare cette photographie et verifie si elle est identiques (au pixel pres) a celle passee en parametre.
	 * Contrairement a {@link #isIdentique(Photo)}, ignore les differences de format.
	 * La photo la plus grande est redimensionne en une de la meme taille que la plus petite
	 * @param p2 La photographie a comparer
	 * @return True si les deux photogrpahies sont strictement identiques (au pixel pres, une fois redimensionne), false sinon.
	 */
	public boolean isIdentiqueIgnoreDimension(Photo p2){
		boolean ret = true;
		return ret;
	}
	
	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return this.imageURL;
	}
	/**
	 * @return the keyWords
	 */
	public ArrayList<String> getKeyWords() {
		return this.keyWords;
	}
	/**
	 * @return the titre
	 */
	public String getTitre() {
		return this.titre;
	}
	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return this.auteur;
	}
	/**
	 * @return the pays
	 */
	public String getPays() {
		return this.pays;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}
	/**
	 * @return the gpsLatitude
	 */
	public long getGpsLatitude() {
		return this.gpsLatitude;
	}

	/**
	 * @return the gpsLongitude
	 */
	public long getGpsLongitude() {
		return this.gpsLongitude;
	}
	
	/**
	 * @param gpsLatitude the gpsLatitude to set
	 */
	public void setGpsLatitude(long gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	/**
	 * @param gpsLongitude the gpsLongitude to set
	 */
	public void setGpsLongitude(long gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @param keyWords the keyWords to set
	 */
	public void setKeyWords(ArrayList<String> keyWords) {
		this.keyWords = keyWords;
	}
	
	
	
}

package datas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

public class Selection {
	private String[] titre;
	private String[] auteur;
	private String[] pays;
	private Calendar dateDebut;
	private Calendar dateFin;
	//private long gpsLatitude;
	//private long gpsLongitude;
	private ArrayList<String> keyWords;
	private Collection source;
	
	/**
	 * Cree une selection vide, les attributs etants modifies par les setteurs.
	 */
	public Selection(){
		this.titre = new String[0];
		this.auteur = new String[0];
		this.pays = new String[0];
		this.dateDebut = Calendar.getInstance();
		this.dateFin = Calendar.getInstance();
		this.keyWords = new ArrayList<String>();
		this.source = new Collection("Tmp");
	}
	
	/**
	 * Genere une collection contenant les photographies correspondant aux criteres.
	 * @return la collection contenant les photographies selectionnees
	 */
	public Collection rechercher(){
		Collection ret = new Collection("Selection");
		Enumeration<Photo> listePhotos = this.source.toutesPhotos();
		Photo p = null;
		while (listePhotos.hasMoreElements()){
			p = listePhotos.nextElement();
			if(this.tabContient(this.auteur, p.getAuteur()) && this.tabContient(this.pays, p.getPays()) && this.tabContient(this.titre, p.getTitre()) 
					&& p.getDate().after(this.dateDebut) && p.getDate().before(this.dateFin)
					&& this.checkKeywords(p.getKeyWords())){
				ret.addPhoto(p);				
			}
		}
		return ret;
	}

	private boolean checkKeywords(ArrayList<String> photo){
		boolean ret = true;
		int i = 0;
		String motACheck;
		while(i<this.keyWords.size() && ret){
			motACheck = this.keyWords.get(i);
			boolean contenu = false;
			int j = 0;
			while(!contenu && j<photo.size()){
				if(motACheck.equalsIgnoreCase(photo.get(j))){
					contenu = true;
				}
				j++;
			}
			i++;
		}
		return ret;
	}
	
	private boolean tabContient(String[] tab, String elem){
		boolean ret = false;
		if(elem != null && !elem.equals("")){
			for(String e : tab){
				if(e.equalsIgnoreCase(elem)){
					ret = true;
				}
			}
		} else {
			ret = true;
		}
		return ret;
	}
	
	/**
	 * @return the titre
	 */
	public String[] getTitre() {
		return titre;
	}

	/**
	 * @return the auteur
	 */
	public String[] getAuteur() {
		return auteur;
	}

	/**
	 * @return the pays
	 */
	public String[] getPays() {
		return pays;
	}

	/**
	 * @return the dateDebut
	 */
	public Calendar getDateDebut() {
		return dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public Calendar getDateFin() {
		return dateFin;
	}

	/*
	 * @return the gpsLatitude
	 
	public long getGpsLatitude() {
		return gpsLatitude;
	}

	/**
	 * @return the gpsLongitude
	 
	public long getGpsLongitude() {
		return gpsLongitude;
	}
	*/

	/**
	 * @return the keyWords
	 */
	public ArrayList<String> getKeyWords() {
		return keyWords;
	}

	/**
	 * @return the source
	 */
	public Collection getSource() {
		return source;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String[] titre) {
		this.titre = titre;
	}

	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String[] auteur) {
		this.auteur = auteur;
	}

	/**
	 * @param pays the pays to set
	 */
	public void setPays(String[] pays) {
		this.pays = pays;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Calendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}

	/*
	 * @param gpsLatitude the gpsLatitude to set
	 
	public void setGpsLatitude(long gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	/**
	 * @param gpsLongitude the gpsLongitude to set
	 
	public void setGpsLongitude(long gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
	*/
	/**
	 * @param keyWords the keyWords to set
	 */
	public void setKeyWords(ArrayList<String> keyWords) {
		this.keyWords = keyWords;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Collection source) {
		this.source = source;
	}
	
}

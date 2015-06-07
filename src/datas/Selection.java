package datas;

import java.util.ArrayList;
import java.util.Date;

public class Selection {
	private String[] titre;
	private String[] auteur;
	private String[] pays;
	private Date dateDebut;
	private Date dateFin;
	private long gpsLatitude;
	private long gpsLongitude;
	private ArrayList<String> keyWords;
	private Collection source;
	
	/**
	 * Cree une selection vide, les attributs etants modifies par les setteurs.
	 */
	public Selection(){
		
	}
	
	/**
	 * Genere une collection contenant les photographies correspondant aux criteres.
	 * @return la collection contenant les photographies selectionnees
	 */
	public Collection rechercher(){
		Collection ret = new Collection("Sélection");
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
	public Date getDateDebut() {
		return dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public Date getDateFin() {
		return dateFin;
	}

	/**
	 * @return the gpsLatitude
	 */
	public long getGpsLatitude() {
		return gpsLatitude;
	}

	/**
	 * @return the gpsLongitude
	 */
	public long getGpsLongitude() {
		return gpsLongitude;
	}

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
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
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

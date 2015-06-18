package datas;

import java.util.GregorianCalendar;
import java.util.Enumeration;

public class Selection {
	private String[] titre;
	private String[] auteur;
	private String[] pays;
	private GregorianCalendar dateDebut;
	private GregorianCalendar dateFin;
	//private long gpsLatitude;
	//private long gpsLongitude;
	private String[] keyWords;
	private Collection source;
	
	/**
	 * Cree une selection vide, les attributs etants modifies par les setteurs.
	 */
	public Selection(Collection source){
		this.titre = new String[0];
		this.auteur = new String[0];
		this.pays = new String[0];
		this.dateDebut = new GregorianCalendar(1700,0,0);
		this.dateFin = new GregorianCalendar(3000,0,0);
		this.keyWords = new String[0];
		this.source = source;
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
			if(this.tabContient(this.auteur, p.getAuteur()) 
					&& this.tabContient(this.pays, p.getPays()) 
					&& this.tabContient(this.titre, p.getTitre()) 
					&& p.getDate().compareTo(this.dateDebut)>=0 
					&& p.getDate().compareTo(this.dateFin)<=0
					&& this.checkKeywords(p.getKeyWords())){
				ret.addPhoto(p);				
			}
		}
		return ret;
	}

	/**
	 * 
	 * @param photo
	 * @return
	 */
	private boolean checkKeywords(String[] photo){
		boolean ret = true;
		int i = 0;
		String motACheck;
		while(i<this.keyWords.length && ret){
			motACheck = this.keyWords[i];
			boolean contenu = false;
			int j = 0;
			while(!contenu && j<photo.length){
				if(motACheck.equalsIgnoreCase(photo[j])){
					contenu = true;
				}
				j++;
			}
			i++;
			ret = contenu;
		}
		return ret;
	}
	
	/**
	 * 
	 * @param tab
	 * @param elem
	 * @return
	 */
	private boolean tabContient(String[] tab, String elem){
		boolean ret = false;
		if(elem != null && !elem.equals("") && tab != null && tab.length>0){
			for(String e : tab){
				if(e.equalsIgnoreCase(elem)){
					ret = true;
				}
			}
		} else if(tab == null || tab.length<=0){
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
	public GregorianCalendar getDateDebut() {
		return dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public GregorianCalendar getDateFin() {
		return dateFin;
	}

	/**
	 * @return the keyWords
	 */
	public String[] getKeyWords() {
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
	public void setDateDebut(GregorianCalendar dateDebut) {
		if(dateDebut == null){
			this.dateDebut = new GregorianCalendar(1700,0,0);
		}else if(this.dateFin.after(dateDebut) || this.dateFin.compareTo(dateDebut)==0){
			this.dateDebut = dateDebut;
		}
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(GregorianCalendar dateFin) {
		if(dateFin == null){
			this.dateFin = new GregorianCalendar(3000,0,0);
		} else if(this.dateDebut.before(dateFin) || this.dateDebut.compareTo(dateFin)==0){
			this.dateFin = dateFin;
		}
	}
	
	/**
	 * @param keyWords the keyWords to set
	 */
	public void setKeyWords(String[] keyWords) {
		this.keyWords = keyWords;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Collection source) {
		this.source = source;
	}
	
}

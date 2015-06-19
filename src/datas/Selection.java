package datas;

import java.util.GregorianCalendar;
import java.util.Enumeration;

/**
 * Classe implementant les methodes de bases pour rechercher de {@link Photo}
 * Les attributs de cette classe sont les criteres de recherche definis par l'utilisateur.
 * Les attributs sont definis par les modificateurs, 
 * puis la methode {@link #rechercher()} est appellee pour obtenir une collection contenant les {@link Photo} correspondant aux criteres.
 * @author FRETAY Juliette et LE POLLES--POTIN Leandre - Groupe 1C
 */
public class Selection {
	/**
	 * Definit l'ensemble des titres recherches par l'utilisateur.
	 */
	private String[] titre;
	
	/**
	 * Definit l'ensemble des auteurs recherches par l'utilisateur.
	 */
	private String[] auteur;
	
	/**
	 * Definit l'ensemble des pays recherches par l'utilisateur.
	 */
	private String[] pays;
	
	/**
	 * Definit l'ensemble des mots cles recherches par l'utilisateur.
	 */
	private String[] keyWords;
	
	/**
	 * Definit la date minimale de prise de vue d'une {@link Photo} pour correspondre a ce critere.
	 */
	private GregorianCalendar dateDebut;
	
	/**
	 * Definit la date maximale de prise de vue d'une {@link Photo} pour correspondre a ce critere.
	 */
	private GregorianCalendar dateFin;
	
	/**
	 * Definit la {@link Collection} dans laquelle cet objet {@link Selection} doit rechercher les {@link Photo} correspondant aux criteres.
	 */
	private Collection source;
	
	/**
	 * Cree un objet {@link Selection} aux criteres vides.
	 * C'est a dire qu'une recherche effectuee a partir d'une {@link Selection} venant d'etre cree renverra toutes 
	 * les {@link Photo} contenue dans la {@link Collection} passee en parametre.
	 * @param source La {@link Collection} dans laquelle cette {@link Selection} recherchera les {@link Photo}.
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
	 * Genere une {@link Collection} contenant les {@link Photo} correspondant aux criteres.
	 * Cette methode appelle les methodes {@link #tabContient(String[], String)} et {@link #checkKeywords(String[])}.
	 * Pour qu'un {@link Photo} corresponde aux criteres il faut que son titre, son auteur et le nom du pays de sa prise de vue
	 * soient respectivement compris dans {@link #titre}, {@link #auteur} et {@link #pays}.
	 * Il faut aussi que sa date de prise de vue soit comprise entre {@link #dateDebut} et {@link #dateFin}.
	 * Il faut enfin que tous les mots-cles contenus dans {@link #keyWords} soient presents dans le tableau de {@link String} "keyWords" de l'objet {@link Photo}.
	 * Si jamais un des tableaux {@link #titre}, {@link #auteur}, {@link #pays} ou {@link #keyWords} est vide ou <code>null</code>, 
	 * le critere correspondant est ignore dans la recherche.
	 * @return La {@link Collection} contenant les {@link Photo} selectionnees.
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
	 * Verifie que le tableau de {@link String} passe en parametre contienne bien tous les mots-cles contenus dans {@link #keyWords}.
	 * @param photo Le tableau de {@link String} correspondant au resultat de {@link Photo#getKeyWords()} sur la {@link Photo} que l'on verifie.
	 * @return <code>true</code> si tous les {@link String} contenus dans {@link #keyWords} sont aussi contenus dans le tableau passe en parametre
	 * Retourne aussi <code>true</code> si {@link #keyWords} est <code>null</code> ou vide. Retourne <code>false</code> dans tous les autres cas.
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
	 * Verifie que le parametre <code>elem</code> est contenu dans le tableau <code>tab</code>.
	 * @param tab Le tableau de {@link String} devant contenir le deuxieme parametre.
	 * @param elem Le {@link String} devant etre contenu dans le tableau passe en parametre.
	 * @return <code>true</code> si <code>elem</code> est contenu dans <code>tab</code> ou si <code>tab</code> est vide ou <code>null</code>.
	 * <code>false</code> dans tous les autres cas.
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
	
	// --- Accesseurs ---
	
	/**
	 * Accesseur de l'attribut {@link #titre}.
	 * @return {@link #titre}
	 */
	public String[] getTitre() {
		return titre;
	}
	
	/**
	 * Accesseur de l'attribut {@link #auteur}.
	 * @return {@link #auteur}
	 */
	public String[] getAuteur() {
		return auteur;
	}
	
	/**
	 * Accesseur de l'attribut {@link #pays}.
	 * @return {@link #pays}
	 */
	public String[] getPays() {
		return pays;
	}

	/**
	 * Accesseur de l'attribut {@link #dateDebut}
	 * @return {@link #dateDebut}
	 */
	public GregorianCalendar getDateDebut() {
		return dateDebut;
	}

	/**
	 * Accesseur de l'attribut {@link #dateFin}
	 * @return {@link #dateFin}
	 */
	public GregorianCalendar getDateFin() {
		return dateFin;
	}

	/**
	 * Accesseur de l'attribut {@link #keyWords}
	 * @return {@link #keyWords}
	 */
	public String[] getKeyWords() {
		return keyWords;
	}

	/**
	 * Accesseur de l'attribut {@link #source}
	 * @return {@link #keyWords}
	 */
	public Collection getSource() {
		return source;
	}

	// --- Modificateurs ---
	
	/**
	 * Modificateur de l'attribut {@link #titre}.
	 * @param titre La valeur a associer a {@link #titre}.
	 */
	public void setTitre(String[] titre) {
		this.titre = titre;
	}

	/**
	 * Modificateur de l'attribut {@link #auteur}.
	 * @param auteur La valeur a associer a {@link #auteur}.
	 */
	public void setAuteur(String[] auteur) {
		this.auteur = auteur;
	}

	/**
	 * Modificateur de l'attribut {@link #pays}.
	 * @param pays La valeur a associer a {@link #pays}.
	 */
	public void setPays(String[] pays) {
		this.pays = pays;
	}

	/**
	 * Modificateur de l'attribut {@link #keyWords}.
	 * @param keyWords La valeur a associer a {@link #keyWords}.
	 */
	public void setKeyWords(String[] keyWords) {
		this.keyWords = keyWords;
	}

	/**
	 * Modificateur de l'attribut {@link #source}.
	 * @param source La valeur a associer a {@link #source}.
	 */
	public void setSource(Collection source) {
		this.source = source;
	}

	/**
	 * Modificateur de l'attribut {@link #dateDebut}.
	 * Gere la validite de l'information. Si le parametre est <code>null</code>, {@link #dateDebut} prends la valeur du premier janvier 1700.
	 * Si le parametre definit une date suivant {@link #dateFin}, l'attribut {@link #dateDebut} reste inchange.
	 * @param dateDebut La valeur a associer a {@link #dateDebut}.
	 */
	public void setDateDebut(GregorianCalendar dateDebut) {
		if(dateDebut == null){
			this.dateDebut = new GregorianCalendar(1700,0,1);
		}else if(this.dateFin.after(dateDebut) || this.dateFin.compareTo(dateDebut)==0){
			this.dateDebut = dateDebut;
		}
	}

	/**
	 * Modificateur de l'attribut {@link #dateFin}.
	 * Gere la validite de l'information. Si le parametre est <code>null</code>, {@link #dateFin} prends la valeur du premier janvier 3000.
	 * Si le parametre definit une date precedant {@link #dateDebut}, l'attribut {@link #dateFin} reste inchange.
	 * @param dateFi  La valeur a associer a {@link #dateFin}.
	 */
	public void setDateFin(GregorianCalendar dateFin) {
		if(dateFin == null){
			this.dateFin = new GregorianCalendar(3000,0,1);
		} else if(this.dateDebut.before(dateFin) || this.dateDebut.compareTo(dateFin)==0){
			this.dateFin = dateFin;
		}
	}
}

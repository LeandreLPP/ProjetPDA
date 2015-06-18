
/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package pda.datas;

import java.awt.Color;
import java.util.GregorianCalendar;

import datas.Collection;
import datas.Photo;
import datas.User;

/**
 *  The simplest application in the PDA.
 *
 *  It can be used to construct other applications.
 *
 *  @author F. Merciol, D. Deveaux MAJ J-F. Kamp
 *                      <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 *  @version $Revision: 2 $
 */
public class AppDatas {

    	/*
     	 * Private implementation -------------------------------------------------
     	 */
    
    	/** the name of the application */
    
	private String galerie;
	private String recherche;
	private String gestion;
	private String options;
	
	private Color galerieColor;
	private Color rechercheColor;
	private Color gestionColor;
	private Color optionsColor;

	private String titreGalerie;
	private String titreRecherche;
	private String titreGestion;
	private String titreOptions;
	private String titreEdition;
	private String titreDiaporama;
	private String titreGestionSelection;
	
	private static Collection[] tabcol;
	private Collection colprinc;
	private static Collection[] tabcolp;
	
	public String getTitreGestionSelection() {
		return titreGestionSelection;
	}

	private User juliette;
	private User[] listeUsers;
	private Collection colecjuliette1;
	private Collection colecprincjuliette;
	
	
	private User utilisateurSelect;	
	
	
	
	/**
	 * Initialise App datas.
	 *
	 */
	public AppDatas() {
		galerie = "Galerie";
		recherche = "Recherche";
		gestion = "Gestion des photos";
		options = "Options";
		
		galerieColor = new Color(127,230,220);
		rechercheColor = new Color(240,143,205);
		gestionColor = new Color(242,195,102);
		optionsColor = new Color(162,242,102);
		
		titreGalerie ="Galerie de photo";
		titreGestion ="Gestion des photos";
		titreDiaporama ="Diaporama de photo";
		titreEdition ="Edition de la photo";
		titreOptions ="Options";
		titreRecherche ="Recherche de Photos";
		titreGestionSelection = "Gestion de la selection";
		
		//-------------PHOTOS-----------------
		Photo photo1 = new Photo("data/bbkim.jpg","data/ChatPenche.jpg");
		photo1.setAuteur("Juliette Fretay");
		photo1.setDate(new GregorianCalendar(1996,05,26));
		photo1.setPays("France");
		Photo photo2 = new Photo("data/bljblm.jpg","data/LapinBulle.jpg");
		photo2.setAuteur("Leandre Le Polles--Potin");
		photo2.setDate(new GregorianCalendar(2015,11,31));
		photo2.setPays("Allemagne");
		Photo photo3 = new Photo("data/p.jpg","data/ChatPitie.jpg");
		photo3.setAuteur("Juliette Fretay");
		photo3.setDate(new GregorianCalendar(2000,00,10));
		photo3.setPays("Portugal");
		Photo photo4 = new Photo("data/viyvip.jpg","data/ChatChelou.jpg");
		photo4.setAuteur("Leandre Le Polles--Potin");
		photo4.setDate(new GregorianCalendar(1996,05,26));
		photo4.setPays("Espagne");
		Photo photo5 = new Photo("data/ar.jpg","data/Chien.jpg");
		photo5.setAuteur("Juliette Fretay");
		photo5.setDate(new GregorianCalendar(1998,07,15));
		photo5.setPays("Suisse");
		Photo photo6 = new Photo("data/lalalala.jpg","data/ChienTriste.jpg");
		photo6.setAuteur("Leandre Le Polles--Potin");
		photo6.setDate(new GregorianCalendar(2002,03,02));
		photo6.setPays("Belgique");
		
		//-------------LISTE PHOTOS-----------------
		Photo[] liste1 = new Photo[3];
		Photo[] liste2 = new Photo[1];
		Photo[] liste3 = new Photo[2];
		Photo[] listeprinc = new Photo[6];
		liste1[0]=photo1;
		liste1[1]=photo3;
		liste2[0]=photo2;
		liste1[2]=photo4;
		liste3[0]=photo5;
		liste3[1]=photo6;
		listeprinc[0]=photo1;
		listeprinc[1]=photo2;
		listeprinc[2]=photo3;
		listeprinc[3]=photo4;
		listeprinc[4]=photo5;
		listeprinc[5]=photo6;
		
		//-------------COLLECTIONS CATEGORIES-----------------
		Collection col1 = new Collection("chat",liste1);
		Collection col2 = new Collection("lapin",liste2);
		Collection col3 = new Collection("chien",liste3);
		tabcol = new Collection[3];
		tabcol[0]=col1;
		tabcol[1]=col2;
		tabcol[2]=col3;
		
		//-------------COLLECTION PRINCIPALE-----------------
		colprinc = new Collection("Principale",listeprinc);
	
		//-------------UTILISATEUR-----------------
		juliette = new User("Juliette");
		juliette.importerPhoto("data/Girl.jpg");
		juliette.importerPhoto("data/Lucy.png");
		juliette.importerPhoto("data/UkyoLove.jpg");
		juliette.importerPhoto("data/Rose.jpg");
		juliette.importerPhoto("data/Fleur.jpg");
		juliette.addCollection("Manga");
		juliette.addCollection("Fille");
		juliette.movePhoto("Lucy.png", "All", "Manga");
		juliette.movePhoto("Girl.jpg", "All", "Manga");
		juliette.movePhoto("UkyoLove.jpg", "All", "Manga");
		juliette.movePhoto("Rose.jpg", "All", "Fille");
		juliette.movePhoto("Fleur.jpg", "All", "Fille");
		colecjuliette1 = juliette.getCollection("Manga");
		colecprincjuliette = juliette.getCollection("All");
		
		//-------------LISTE UTILISATEURS-----------------
		this.listeUsers = new User[1];
		listeUsers[0]=juliette;
	
		utilisateurSelect=null;
	}

	public void setUtilisateurSelect(User utilisateurSelect) {
		this.utilisateurSelect = utilisateurSelect;
	}

	public User getUtilisateurSelect() {
		return utilisateurSelect;
	}

	public Collection getColecjuliette1() {
		return colecjuliette1;
	}

	public Collection getColecprincjuliette() {
		return colecprincjuliette;
	}

	public User getJuliette() {
		return juliette;
	}

	public User[] getListeUsers() {
		return listeUsers;
	}

	public static Collection[] getTabcol() {
		return tabcol;
	}

	public Collection getColprinc() {
		return colprinc;
	}

	public static Collection[] getTabcolp() {
		return tabcolp;
	}

	public static void setTabcolp(Collection[] tabcolp) {
		AppDatas.tabcolp = tabcolp;
	}

	public void setTheme(String theme){
		if(theme.equals("Barbie")){
			galerieColor = new Color(233,26,157);
			rechercheColor = new Color(240,125,187);
			gestionColor = new Color(217,108,203);
			optionsColor = new Color(244,0,65);
		}
		else if(theme.equals("Defaut")){
			galerieColor = new Color(127,230,220);
			rechercheColor = new Color(240,143,205);
			gestionColor = new Color(242,195,102);
			optionsColor = new Color(162,242,102);
		}
		else if(theme.equals("Plage")){
			galerieColor = new Color(102,239,225);
			rechercheColor = new Color(244,244,157);
			gestionColor = new Color(242,183,67);
			optionsColor = new Color(116,179,234);
		}
		else if(theme.equals("Black")){
			galerieColor = new Color(115,115,115);
			rechercheColor = new Color(254,254,254);
			gestionColor = new Color(192,192,192);
			optionsColor = new Color(65,65,65);
		}
		else if(theme.equals("Noel")){
			galerieColor = new Color(15,113,0);
			rechercheColor = new Color(246,220,18);
			gestionColor = new Color(254,254,254);
			optionsColor = new Color(254,0,0);
		}
	}
	
	public String getTitreGalerie() {
		return titreGalerie;
	}

	public String getTitreRecherche() {
		return titreRecherche;
	}

	public String getTitreGestion() {
		return titreGestion;
	}

	public String getTitreOptions() {
		return titreOptions;
	}

	public String getTitreEdition() {
		return titreEdition;
	}

	public String getTitreDiaporama() {
		return titreDiaporama;
	}

	public Color getGalerieColor() {
		return galerieColor;
	}

	public String getGalerie() {
		return galerie;
	}

	public String getRecherche() {
		return recherche;
	}

	public String getGestion() {
		return gestion;
	}

	public String getOptions() {
		return options;
	}
	
    public Color getRechercheColor() {
		return rechercheColor;
	}

	public Color getGestionColor() {
		return gestionColor;
	}

	public Color getOptionsColor() {
		return optionsColor;
	}

	

    
 
} // ---------------------------------------------------------- Class AppDatas

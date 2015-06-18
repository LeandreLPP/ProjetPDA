
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
import datas.NoPhotoFoundException;
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


	public String getTitreGestionSelection() {
		return titreGestionSelection;
	}

	private User juliette;
	private User[] listeUsers;
	private Collection colecjuliette1;
	private Collection colecprincjuliette;


	private User utilisateurSelect;	
	private User defaultUser;

	private Collection collectionSelect;

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
		//-------------UTILISATEUR-----------------
		this.defaultUser = new User("Defaut");
		this.defaultUser.importerPhoto("data/ar.jpg");
		this.defaultUser.importerPhoto("data/bbkim.jpg");
		this.defaultUser.importerPhoto("data/bljblm.jpg");
		this.defaultUser.importerPhoto("data/p.jpg");
		this.defaultUser.importerPhoto("data/lalalala.jpg");
		this.defaultUser.importerPhoto("data/viyvip.jpg");
		try {
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/ar.jpg").setAuteur("Juliette");
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/bbkim.jpg").setAuteur("Juliette");
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/bljblm.jpg").setAuteur("Pierre");
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/p.jpg").setAuteur("Emilie");
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/lalalala.jpg").setAuteur("Emilie");
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/viyvip.jpg").setAuteur("Pierre");
			
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/ar.jpg").setDate(new GregorianCalendar(1996,06,12));
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/bbkim.jpg").setDate(new GregorianCalendar(1996,06,12));
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/bljblm.jpg").setDate(new GregorianCalendar(2015,10,14));
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/p.jpg").setDate(new GregorianCalendar(2003,02,24));
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/lalalala.jpg").setDate(new GregorianCalendar(2010,04,01));
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/viyvip.jpg").setDate(new GregorianCalendar(2010,04,01));
			
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/ar.jpg").setPays("Hollande");
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/bbkim.jpg").setPays("France");
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/bljblm.jpg").setPays("France");
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/p.jpg").setPays("Italie");
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/lalalala.jpg").setPays("France");
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/viyvip.jpg").setPays("Espagne");
			
			String[] keys = {"chien"};
			this.defaultUser.getAllPhotos().getPhoto(this.defaultUser.getUrlDossier()+"/lalalala.jpg").setKeyWords(keys);
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
		this.defaultUser.addCollection("Chat");
		this.defaultUser.addCollection("Chien");
		this.defaultUser.addCollection("Lapin");
		this.defaultUser.movePhoto("ar.jpg", "All", "Chien");
		this.defaultUser.movePhoto("bbkim.jpg", "All", "Chat");
		this.defaultUser.movePhoto("bljblm.jpg", "All", "Lapin");
		this.defaultUser.movePhoto("p.jpg", "All", "Chat");
		this.defaultUser.movePhoto("lalalala.jpg", "All", "Chien");
		this.defaultUser.movePhoto("viyvip.jpg", "All", "Chat");

		//-------------LISTE UTILISATEURS-----------------
		this.listeUsers = new User[2];
		listeUsers[0]=this.defaultUser;
		listeUsers[1]=juliette;

		utilisateurSelect=defaultUser;
		this.collectionSelect=this.utilisateurSelect.getAllPhotos();
	}

	public void setCollectionSelect(Collection collectionSelect) {
		this.collectionSelect = collectionSelect;
	}

	public Collection getCollectionSelect() {
		return collectionSelect;
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

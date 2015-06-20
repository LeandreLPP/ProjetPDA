
/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package datas;

import java.awt.Color;

/**
 *  The simplest application in the PDA.
 *
 *  It can be used to construct other applications.
 *
 *  @author F. Merciol, D. Deveaux MAJ J-F. Kamp
 *                      <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 *  @version $Revision: 2 $
 */
public class PhotoTechDatas {

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

	private User utilisateurSelect;	

	private Collection collectionSelect;
	private String titreEditionModification;
	private String titreNouvelleImage;
	private String titreCheck;
	private String titreNewUser;
	private String titreSuppUser;

	public String getTitreEditionModification() {
		return titreEditionModification;
	}

	/**
	 * Initialise PhotoTech datas.
	 */
	public PhotoTechDatas() {
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
		titreEditionModification = "Modification de la photo";
		titreNouvelleImage = "Ajouter une Photo";
		titreCheck = "Verification authenticite Photos";
		titreNewUser = "Ajouter un utilisateur";
		titreSuppUser = "Supprimer un utilisateur";
	}
	public String getTitreNewUser() {
		return titreNewUser;
	}

	public String getTitreSuppUser() {
		return titreSuppUser;
	}

	public String getTitreCheck() {
		return titreCheck;
	}

	public String getTitreNouvelleImage() {
		return titreNouvelleImage;
	}

	public void setCollectionSelect(Collection collectionSelect) {
		this.collectionSelect = collectionSelect;
	}

	public Collection getCollectionSelect() {
		return collectionSelect;
	}

	public void setUtilisateurSelect(User utilisateurSelect) {
		this.utilisateurSelect = utilisateurSelect;
		this.collectionSelect = this.utilisateurSelect.getAllPhotos();
	}

	public User getUtilisateurSelect() {
		return utilisateurSelect;
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

} // ---------------------------------------------------------- Class PhotoTechDatas

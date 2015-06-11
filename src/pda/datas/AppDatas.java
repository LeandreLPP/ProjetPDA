
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

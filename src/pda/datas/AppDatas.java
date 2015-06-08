
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
	private String labelGalerie;
	
	private Color galerieColor;
	private Color rechercheColor;
	private Color gestionColor;
	private Color optionsColor;

	public Color getRechercheColor() {
		return rechercheColor;
	}

	public Color getGestionColor() {
		return gestionColor;
	}

	public Color getOptionsColor() {
		return optionsColor;
	}

	public String getLabelGalerie() {
		return labelGalerie;
	}

	/**
	 * Initialise App datas.
	 *
	 */
	public AppDatas() {
		galerie = "Galerie";
		recherche = "Recherche";
		gestion = "Gestion des photos";
		options = "Options";
		labelGalerie = "Coucou c'est la galerie !";
		
		galerieColor = new Color(127,230,220);
		rechercheColor = new Color(240,143,205);
		gestionColor = new Color(242,195,102);
		optionsColor = new Color(162,242,102);
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
    

    
 
} // ---------------------------------------------------------- Class AppDatas

/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package datas;

import java.util.Hashtable;

import control.HelloCtrl;
import control.IApplication;
import control.PhotoTechCtrl;

/**
 * The datas of Launch application (that starts all others).
 * 
 * @author F. Merciol, D. Deveaux MAJ J-F. Kamp, I.Borne
 *         <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 * @version $Revision: 1-2013 $
 */
public class LaunchDatas {

	/*
	 * Private implementation -------------------------------------------------
	 */

	/** 
	 * The CONSTANT array of application names.
	 * ADD HERE THE UNIQUE NAME OF A RUNNABLE APPLICATION (!! application name must be unique : it's a key !!).
	 */
	private static final String[] appArray = { "hello" ,"PhotoTech"};


	/** The list of applications classes that can be launched */
	private Hashtable<String, Class> applications;

	/** The list of icons associated to applications */
	private Hashtable<String, String> icons;

	/*
	 * Public ressources -----------------------------------------------------
	 * 
	 * Constructors
	 */

	/**
	 * LaunchDatas constructor.
	 * 
	 * Initialize applications collections.
	 */
	public LaunchDatas() {

		// Dynamic allocation
		applications = new Hashtable<String, Class>();
		icons = new Hashtable<String, String>();

		// AJOUTER ICI "EN DUR" TOUTES LES APPLICATIONS QUE LE LAUNCHER DOIT AFFICHER
		// EN PARCOURANT SIMPLEMENT LE TABLEAU DES NOMS DES APPLICATIONS.
		// appArray[0] contient "hello", "hello" est le nom de l'application, c'est une clé d'identification
		// pour les 2 Hashtable "applications" et "icons"
		applications.put ( appArray[0], HelloCtrl.class ); // !! la classe compilée HelloCtrl.class DOIT EXISTER
		icons.put ( appArray[0], "hello.png" );
		
		applications.put(appArray[1], PhotoTechCtrl.class);
		icons.put(appArray[1], "app.png");

	} // ------------------------------------------------------- LaunchDatas()

	/*
	 * Public methods
	 */
	 
	/** Get the number of applications
	 * @return the number of applications un appArray
	 */
	 public static  int getNbApplications() {
	 	 return appArray.length;
	 }

	/**
	 * Get the application name at index i.
	 * 
	 * @param i index of the application
	 * 
	 * @return the application name
	 */
	public static  String appKeyAt(int i) {
		return appArray[i];
	}

	/**
	 * Get a NEW application instance.
	 * 
	 * @param appName name of the application to create (unique, it's a key)
	 * @return an instance of the required application, null if not ok
	 */
	public IApplication getAppliInstance ( String appName ) {

		IApplication ret = null;

		try {
			// Construction d'une NOUVELLE instance de type = applications.get(appName) (stockée dans une variable de type Class)
			ret = (IApplication) ( (applications.get(appName)).newInstance() );
			// Donner un nom unique à cette application
			ret.setAppliName ( appName );

		} catch (InstantiationException e) {

			System.out.println( e.getMessage() );

		} catch (IllegalAccessException e) {

			System.out.println ( e.getMessage() );
		}

		return ret;
	} // ----------------------------------------------------------- getAppli()

	/**
	 * Get the icon full path information of an application.
	 * 
	 * @param appName name of the application
	 * @return the full name of the icon file
	 */
	public String getIcon(String appName) {

		String ret;
		ret = "data/img/" + icons.get(appName);
		return ret;
	}


} // ----------------------------------------------------------- Class LaunchDatas

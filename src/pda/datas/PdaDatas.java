/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package pda.datas;

import pda.control.IApplication;

import javax.swing.*;
import java.util.*;

/** 
 *  The base class of PDA simulator.
 *
 *  This class displays the PDA emulator and manage all the applications.
 *
 *  @author F. Merciol, D. Deveaux MAJ J-F. Kamp
 *                      <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 *  @version $Revision: 27 $
 */
public class PdaDatas {

    	/*
     	 * Private implementation -------------------------------------------------
     	 */
        
    	/** the vector that stores the running applications */
    	private ArrayList<IApplication> startedApplications;
	
	/**
     	 * Create the collection of running applications.
     	 */
    	public PdaDatas() {

		startedApplications = new ArrayList<IApplication>();
    	} // ------------------------------------------------------------ PdaDatas()
    
    	/*
     	 *  Public methods
     	 */    

    	/** 
     	 * Add a new application in the list of applications running.
	 * @param appli the application to add
     	 */
    	public void addAppli ( IApplication appli ) {

		startedApplications.add ( appli );
    	} // -------------------------------------------------------- addAppli()


    	/**
     	 * Remove the application at index idx from the list of applications running.
	 * @param idx index of the application to remove
     	 */
    	public void closeAppli ( int idx ) {
		IApplication appli = startedApplications.get(idx) ;
		startedApplications.remove (appli);
    	} // --------------------------------------------------------- closeAppli()

	/**
	 * Get a list of all applications names.
	 * @return the list of names
	 */
    	public ArrayList<String> getApplicationsName () {
		ArrayList<String> result = new ArrayList<String> ();

		for ( int i=0; i<startedApplications.size(); i++ ) {
			IApplication appli = startedApplications.get(i);
			result.add (appli.getAppliName ());
		}

		return result;
    	} // --------------------------------------------------------- getApplicationsName ()

	/**
	 * Get the panel in which the application is running.
	 * @param name the name of the application
	 */
    	public JPanel getApplicationPanel ( String name ) {

		JPanel ret = null;
		
		for ( int i=0; i<startedApplications.size(); i++ ) {
			IApplication appli = startedApplications.get(i);
	    		if (name.equals (appli.getAppliName ())) { 
				ret = appli.getAppliPanel();
				break;
			}
	    	}
		
		return ret;		
	} // --------------------------------------------------------- getApplicationsPanel ()

	/**
	 * Get the application corresponding to the given index.
	 * @param index the index in the list
	 * @return the application
	 */
	public IApplication getApplication ( int index ) {
		return this.startedApplications.get(index);
	} // --------------------------------------------------------- getApplication ()

} // ------------------------------------------------------------ Class PdaDatas


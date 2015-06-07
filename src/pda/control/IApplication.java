/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package pda.control;

import javax.swing.*;

/**
 * Common interface for all plugable applications.
 *
 * @author F. Merciol, D. Deveaux MAJ J-F. Kamp
 *                      <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 * @version $Revision: 27 $
 * 
 */
public interface IApplication {
    
	
	/**
	 * Start the application in the specified PDA
	 * @param pda the reference to the PDA root application if necessery
	 */
	public void start(PdaCtrl pda);
	    
	/**
	 * Return the application name as String (unique, it's a key)
	 * @return the name
	 */
	public String getAppliName();
	    
	/**
	 * Return the panel that contains all the application display
	 * @return the panel
	 */
	public JPanel getAppliPanel();
	    
	/**
	 * Close the application ant its display panel
	 * @return true if the method succeeds
	 */
	public boolean close();

	/**
	 * Give a unique name to the application
	 * @param theName the name of the application
	 */
	public void setAppliName ( String theName );
    
} // --------------------------------------------------- Interface IApplication

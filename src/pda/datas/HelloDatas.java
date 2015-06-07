/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package pda.datas;

/**
 *  The simplest application in the PDA.
 *
 *  It can be used to construct other applications.
 *
 *  @author F. Merciol, D. Deveaux MAJ J-F. Kamp
 *                      <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 *  @version $Revision: 2 $
 */
public class HelloDatas {

    	/*
     	 * Private implementation -------------------------------------------------
     	 */
    
    	/** the name of the application */
    	private String label;

	/**
	 * Initialise Hello datas.
	 *
	 */
	public HelloDatas() {
		this.label = new String ( "Bonjour le monde" );
	}
    
    	/**
     	 * Get the text of the application.
     	 *
     	 * @return the text of the application
     	 */
    	public String getLabel() {

        	return label;        
    	} // ------------------------------------------------------------ getLabel()
    
 
} // ---------------------------------------------------------- Class HelloDatas

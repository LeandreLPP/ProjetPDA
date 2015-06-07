/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;

/**
 *  The simplest application in the PDA.
 *
 *  It can be used to construct other applications.
 *
 *  @author F. Merciol, D. Deveaux MAJ J-F. Kamp
 *                      <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 *  @version $Revision: 2 $
 */
public class HelloView {

    	/*
     	 * Private implementation -------------------------------------------------
     	 */
    
    	/** the engine of the application */
    	private HelloDatas engine;
    
    	/** the panel associated to the Hello application (Hello runs in this panel) */
    	private JPanel panel;

    	/*
     	 *  Public ressources -----------------------------------------------------
     	 *
     	 *  Constructors
     	 */

    	/**
     	 * Construction of the Hello IHM.
     	 *
     	 * @param anEngine link to the Hello datas
     	 */
    	public HelloView ( HelloDatas anEngine ) {
        	engine = anEngine;
		// mise en place de l'ihm
		panel = new JPanel();
		panel.add ( new JLabel(engine.getLabel()) );
    	} // ------------------------------------------------------------- HelloView()

    	/*
     	 *  Public methods
     	 */
    
    	/** 
     	 *  Get the main panel
     	 *
     	 * @return the main panel of the application
     	 */
    	public JPanel getPanel() {

		return panel;
    	} // ----------------------------------------------------------- getPanel()

} // ------------------------------------------------------------- Class HelloView

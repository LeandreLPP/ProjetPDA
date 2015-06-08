/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package pda.control;

import pda.view.*;
import pda.datas.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * The "LaunchCtrl" application that starts all others.
 * The LaunchCtrl does NOT start itself (ONLY the OTHERS applications).
 * The LaunchCtrl is created only by the PDACtrl.
 *
 * @author F. Merciol, D. Deveaux MAJ J-F. Kamp
 *                      <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 * @version $Revision: 29 $
 */
public class LaunchCtrl implements IApplication, ActionListener{

 	/*
     	 * Private implementation -------------------------------------------------
     	 */
    
    	/** application name */
    	private String name;
    
    	/** back link to the PDA that supports this launcher */
    	private PdaCtrl pda;

    	/** the view of the application */
    	private LaunchView view;
    
    	/** the datas of the application */
    	private LaunchDatas datas;

    	/*
     	 *  Public ressources -----------------------------------------------------
     	 *
     	 *  Constructors
     	 */
    
    	/** 
	 *  LaunchCtrl constructor.
     	 *
     	 *  Initialize applications datas and launch ihm.
     	 */
    	public LaunchCtrl() {
        	datas = new LaunchDatas();
        	view = new LaunchView ( this, datas );
    	} // ----------------------------------------------------------- LaunchCtrl()

    	/*
     	 * Public methods
     	 */
    
    	/* 
     	 *  see interface documentation
     	 */
    	public void start(PdaCtrl pda) {
		this.pda = pda;
    	} // -------------------------------------------------------------- start()

    	/* 
     	 *  see interface documentation
     	 */
    	public String getAppliName() {
		return this.name;
    	} // ------------------------------------------------------- getAppliName()

    	/* 
     	 *  see interface documentation
     	 */
	public void setAppliName ( String theName ) {
		this.name = theName;
	} // ------------------------------------------------------- setAppliName()

    	/* 
     	 *  see interface documentation
     	 */
    	public JPanel getAppliPanel() {
	 	return view.getPanel();
    	} // ------------------------------------------------------ getAppliPanel()

    	/* 
     	 *  see interface documentation
     	 */
    	public boolean close() {
		return true;
    	} // -------------------------------------------------------------- close()

    	/**
     	 * Callback method, reaction to button pushed.
	 * @param e the event to manage
     	 */
    	public void actionPerformed ( ActionEvent e ) {
		// Récupération du nom de l'application : c'est une clé d'identification importante !
        	String cmd = e.getActionCommand();
		System.out.println ( "New instance of application " + cmd );
		// Construction d'une NOUVELLE instance d'une application correspondant au nom passé en paramètre
        	IApplication appli = datas.getAppliInstance ( cmd );
 		// ICI l'application devient utilisable ET visible dans un NOUVEL ONGLET du PDA
        	pda.addNewAppli ( appli );
    	} // ---------------------------------------------------- actionPerformed()
    
} // ----------------------------------------------------------- Class LaunchCtrl

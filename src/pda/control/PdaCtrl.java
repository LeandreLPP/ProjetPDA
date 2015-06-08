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
import java.util.ArrayList;

/**
 * The base class of PDA simulator.
 * 
 * This class displays the PDA emulator and manages all the applications.
 * In particular it manages the launcher
 * 
 * @author F. Merciol, D. Deveaux MAJ J-F. Kamp
 *         <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 * @version $Revision: 27 $
 */
public class PdaCtrl implements ActionListener {

	/*
	 * Private implementation -------------------------------------------------
	 */

	/** the view of the application */
	private PdaView view;

	/** the engine of the application */
	private PdaDatas datas;

	/*
	 * Constructor -------------------------------------------------
	 */
	/**
	 * Initialize Pda datas and Pda ihm.
	 */
	public PdaCtrl() {
		datas = new PdaDatas();
		view = new PdaView ( this );
	} // ------------------------------------------------------------ PdaCtrl()

	/*
	 * Public methods
	 */

	/**
	 * Starting of the PDA program.
	 */
	public static void main(String[] arg) {
		new PdaCtrl();
	} // --------------------------------------------------------------- main()

	/**
	 * Add a new application : this means that the application becomes visible on a new panel AND that the application is stored in the
	 * list of the applications running (at the SAME index that the new panel stored in the JTabbedPane).
	 * @param appli the new application to add in the PDA
	 */
	public void addNewAppli ( IApplication appli ) {
		// Ajoute la nouvelle appli dans l'ihm grâce à son panneau (appli.getAppliPanel()).
		// Un NOUVEL ONGLET est créé et l'application devient VISIBLE.
		view.addAppli ( appli.getAppliName(), appli.getAppliPanel() );
		// L'application est ajoutée dans le tableau des applications démarrées.
		datas.addAppli ( appli );
		// Démarrage (initialisation) de l'application.
		appli.start(this);
	} // -------------------------------------------------------- addNewAppli()

	/**
	 * Get the array containing the names of the applications.
	 * @return the array of names
	 */
	public ArrayList<String> getApplicationsName() {
		return datas.getApplicationsName();
	} // -------------------------------------------------------- getApplicationsName()

	/**
	 * Callback function, reaction to button pushed.
	 * @param e the action to manage
	 */
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == view.getQuitMI()) this.quit();   
		else if (source == view.getStartMI()) this.startLauncher();
		else if (source == view.getCloseMI()) this.closeCurrentAppli();

	} // ---------------------------------------------------- actionPerformed()

	/*
	 * Private methods
	 */

	/**
	 * Start the launcher application.
	 */
	private void startLauncher() {
		IApplication launcher = new LaunchCtrl();
		launcher.setAppliName ( "launcher" );	// "launcher" = unique name of the launcher application
		this.addNewAppli ( launcher );
	} // ------------------------------------------------------ startLauncher()

	/**
	 * Removes the current application from ihm AND from the vector that stores the running applications.
	 */
	private void closeCurrentAppli() {
		
		IApplication appli = null;
		// récupération de l'index de l'application actuellement visible
		int idx = view.getAppliIndex();
		
		if ( idx >= 0 ) {
			appli = datas.getApplication ( idx );
			// If not null
			if ( appli != null ) {
				// Call close() method if something to do before removing appli from PdaCtrl
				if ( appli.close() ) {
					System.out.println ( "Remove application : " + appli.getAppliName() );
					// Remove appli from ihm
					view.closeAppli ( idx );
					// Remove appli from the vector that stores the running applications
					datas.closeAppli ( idx );
				}
				else System.out.println ( "closeCurrentAppli : appli NOT closed" );
			}
			else System.out.println ( "closeCurrentAppli : null application" );
		}
		else System.out.println ( "closeCurrentAppli : not correct number id" );

	} // --------------------------------------------------------- closeCurrentAppli()

	/**
	 * Quit the PDA program
	 */
	private synchronized void quit() {

		view.quit();
	} // --------------------------------------------------------------- quit()

	public PdaView getView() {
		return view;
	}

} // ------------------------------------------------------------ Class PdaCtrl

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
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/**
 *  The LaunchView application that starts all others.
 *
 *  @author F. Merciol, D. Deveaux MAJ J-F. Kamp, I.Borne
 *                      <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 *  @version $Revision: 1-2013 $
 */
public class LaunchView {

	/*
	 * Private implementation -------------------------------------------------
	 */

	/** the panel associated to the Launch application (Launch runs in this panel) */
	private JPanel panel;

	/** the engine of the application */
	private LaunchDatas engine;

	/*
	 *  Public ressources -----------------------------------------------------
	 *
	 *  Constructors
	 */

	/** 
	 *  LaunchView constructor.
	 *
	 *  Initialize IHM for Launch application (i.e. its panel and the buttons).
	 */
	public LaunchView ( LaunchCtrl ctrl, LaunchDatas theEngine ) {

		// Construction du panneau qui va contenir les boutons de lancement des différentes applis.
		// Le nombre de cases du panneau = le nombre d'applications disponibles.
		this.panel = new JPanel( new GridLayout ( (LaunchDatas.getNbApplications()), 1 ) );
		JButton btn;
		engine = theEngine;

		for(int i = 0; i < LaunchDatas.getNbApplications(); i++) {
			String apn = LaunchDatas.appKeyAt(i);
			btn = new JButton(apn, new ImageIcon(engine.getIcon(apn)));
			panel.add(btn);
			btn.addActionListener(ctrl);
		}		

	} // --------------------------------------------------------- LaunchView()

	/*
	 * Public methods
	 */

	/* 
	 *  see interface documentation
	 */
	public JPanel getPanel() {
		return panel;
	} // ------------------------------------------------------ getAppliPanel()        

} // ----------------------------------------------------------- Class LaunchView

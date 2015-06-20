/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import control.PdaCtrl;


/** 
 *  The base class of PDA simulator.
 *
 *  This class displays the PDA emulator and manage all the applications
 *
 *  @author F. Merciol, D. Deveaux MAJ J-F. Kamp I.Borne
 *                      <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 *  @version $Revision: 1-2013 $
 */
public class PdaView extends JFrame {

	/*
     	 * private  ressources -----------------------------------------------------
     	 *
     	 *  menus (see public accessors)
     	 */

    	private JMenuItem startMI = new JMenuItem("start Launcher");
    
    	/** close menu item */
    	private JMenuItem closeMI = new JMenuItem("close application");
    
    	/** quit menu item */
    	private JMenuItem quitMI = new JMenuItem("quit");




	/*
     	 * private  ressources -----------------------------------------------------
     	 *
     	 *  Constants for the screen size
     	 */
    
    	/** X position of the screen's left corner */
    	private static final int SCREENX = 22;
    
    	/** Y position of the screen's left corner */
    	private static final int SCREENY = 78;
    
    	/** width of the screen */
    	private static final int SCREENW = 322;
    
    	/** height of the screen */
    	private static final int SCREENH = 346;

	/*
     	 * private  ressources -----------------------------------------------------
     	 *
     	 *  attributs
     	 */

    	/** Class is serializable, it must define a serial version */
    	private static final long serialVersionUID = 0L;

    	/** file menu item */
	private JMenu fileMenu = new JMenu("File");
    
    	/** help menu item */
    	private JMenu helpMenu = new JMenu("Help");


     
	/** the skin ot the application */
    	private JLabel skin;

    	/** the tabbed pane : THE MOST IMPORTANT WIDGET (i.e. panneau à onglets) */
    	private JTabbedPane tabbedPane;
    
    	/** the controller of the application */
    	private PdaCtrl ctrl;
    
    	/*
     	 *  Constructors
     	 */
    
    	/**
     	 * Construction of the PDA IHM.
     	 */
    	public PdaView ( PdaCtrl aPda ) {
		super(" PDA Simu ");
        	ctrl = aPda;
		// création du panneau à onglets
		this.tabbedPane = new JTabbedPane();
		// mise en place du décor principal
		this.guiInit();
    	} // ---------------------------------------------------------- PdaView()

    	
    	
    
    
    	/*
     	 *  Public methods
     	 */
     	 
     	 /** Get the start Launcher menu item
     	 * @return a JMenuItem
     	 */
     	 public JMenuItem getStartMI() {
     	 	 return startMI;
     	 }
     	 
     	 /** Get the close application menu item
     	 * @return a JMenuItem
     	 */
     	 public JMenuItem getCloseMI() {
     	 	 return closeMI;
     	 }
     	 
       	 /** Get the start Launcher menu item
     	 * @return a JMenuItem
     	 */
     	 public JMenuItem getQuitMI() {
     	 	 return quitMI;
     	 }
     	 

    	/** 
     	 * Add a new application in the JTabbedPane. This new application becomes VISIBLE.
	 * @param appliName the application name (to add)
	 * @param appliPanel the panel in which the new application runs
     	 */
    	public void addAppli ( String appliName, JPanel appliPanel ) {

        	System.out.println("Add panel for " + appliName);
		// Ajoute un panneau supplémentaire dans l'ihm comme nouvel onglet.
		// C'est dans ce nouveau panneau que la nouvelle application doit venir se loger.
		tabbedPane.addTab(appliName, null, appliPanel, appliName);
		// En sélectionnant le bon index on rend le panneau (et donc l'application) visible.
		tabbedPane.setSelectedIndex (tabbedPane.getTabCount ()-1);

    	} // ----------------------------------------------------------- addAppli()
    
    	/**
     	 * Return the index of the selected application (the one which is visible).
     	 *
     	 * @return the required index
     	 */
    	public int getAppliIndex() {
		// renvoie l'index de l'onglet actuellement visible (et qui contient le panneau dans lequel se trouve l'application)
       		return tabbedPane.getSelectedIndex(); 
    	} // ------------------------------------------------------ getAppliIndex()

	/**
	 * Return the index of the application corresponding to appliPanel.
	 * Overloading of public int getAppliIndex().
	 * 
	 * @param appliPanel the JPanel of the application
	 * @return the required index
	 */
    	public int getAppliIndex ( JPanel appliPanel ) {
		return  tabbedPane.indexOfComponent(appliPanel);
    	} // --------------------------------------------------------- getAppliIndex()

    	/**
     	 * Close the current application but view ONLY.
	 * @param idx the index of the application in the JTabbedPane
     	 */
    	public void closeAppli ( int idx ) {
		tabbedPane.removeTabAt(idx);
    	} // --------------------------------------------------------- closeAppli()

    	/**
     	 *  Quit the PDA program definitively if YES button pressed.
     	 */
    	// Attention pas de synchronized sinon le thread de la machine virtuelle qui gère l'IHM se bloque
    	public void quit() {
		if ( JOptionPane.showConfirmDialog(this, "Do you really want to quit ?", "Closing PDA program", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ) {
			// fin définitive
	    		System.exit(0);
		}
    	} // --------------------------------------------------------------- quit()

	/**
	 * Selection of the JPanel corresponding to appliPanel.
	 * That JPanel becomes VISIBLE.
	 * 
	 * @param appliPanel the JPanel of the application
	 */  	
    	public void selectApplication ( JPanel appliPanel ) {
		// Sélection du panneau correspondant à appliPanel ET rend ce panneau VISIBLE dans un onglet
		if ( appliPanel != null ) tabbedPane.setSelectedComponent (appliPanel);
    	} // --------------------------------------------------------------- selectApplication()

    	/*
     	 * Private implementation -------------------------------------------------
     	 */
    
    	/**
     	 *  Initialization of the entire PDA'S GUI.
     	 */
    	private void guiInit() {

		// NE JAMAIS ECRIRE UNE REACTION COMME CA !
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) { quit(); }
	    	});

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JMenuBar menubar = new JMenuBar();
		menubar.setLayout(new BoxLayout(menubar, BoxLayout.X_AXIS));
		menubar.add(fileMenu);
		menubar.add(Box.createHorizontalGlue());
		menubar.add(helpMenu);
		setJMenuBar(menubar);

		fileMenu.add(startMI);
		fileMenu.add(closeMI);
		fileMenu.add(quitMI);

		// listeners to mouse click
		startMI.addActionListener(ctrl);
		closeMI.addActionListener(ctrl);
		quitMI.addActionListener(ctrl);

		JLayeredPane layeredPane = new JLayeredPane();
		skin = new JLabel ();
		skin.setLocation (0, 0);
		layeredPane.add (skin, new Integer(0));
		layeredPane.add (tabbedPane, new Integer(5));
		tabbedPane.setSize (SCREENW, SCREENH);
		System.getProperties().get("user.dir");
		setSkin (new ImageIcon ("data/img/palm.png"), new Point (SCREENX, SCREENY));

		getContentPane().add(layeredPane, BorderLayout.CENTER);

		pack();
		setVisible(true);
    	} // ------------------------------------------------------------ guiInit()
    
    	/**
	 * Set the skin of the PDA (visible touch !).
	 * 
	 * @param skinIcon background image
	 * @param pos position of the JTabbedPane
	 */
    	private void setSkin ( ImageIcon skinIcon, Point pos ) {

		int width = skinIcon.getIconWidth();
		int height = skinIcon.getIconHeight();
		tabbedPane.setLocation (pos);

		skin.setIcon (skinIcon);
		skin.setSize(width, height);
		skin.getParent ().setPreferredSize(new Dimension(width, height));
    	} // ------------------------------------------------------------ setSkin()
    
} // ------------------------------------------------------------ Class PDAView

/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package pda.view;

import java.awt.*;

import pda.control.*;
import pda.datas.*;

import javax.swing.*;

/**
 * 
 * @author Juliette FRETAY
 * @author Leandre LE POLLES--POTIN
 * 
 * @version 09/06/15
 *
 */
public class AppView {

	/*
	 * Private implementation -------------------------------------------------
	 */

	private AppCtrl ctrl;
	
	/** the engine of the application */
	private AppDatas engine;

	/** the panel associated to the App application (App runs in this panel) */
	private JPanel panel;
	
	
	private JPanel panelCourant;
	private JPanel panelPrecedent;
	
	
	/*
	 *  Public ressources -----------------------------------------------------
	 *
	 *  Constructors
	 */

	/**
	 * Construction of the App IHM.
	 *
	 * @param anEngine link to the App datas
	 */
	public AppView (AppCtrl ctrl, AppDatas anEngine ) {
		engine = anEngine;
		this.ctrl = ctrl;
		// mise en place de l'ihm
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this.afficherMenu();
		
		this.panelCourant = new JPanel();
		this.panelPrecedent = new JPanel();

	} // ------------------------------------------------------------- AppView()

	
	
	
	
	
	/*
	 *  Public methods
	 */
	
	public void afficherMenu(){
		panel.removeAll();
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(2,2,5,5));
		
		JButton boutonGalerie = new JButton("<html><body><center>"+this.engine.getGalerie()+"</center></body></html>");
		boutonGalerie.setBackground(this.engine.getGalerieColor());
		boutonGalerie.addActionListener(this.ctrl);
		boutonGalerie.setActionCommand("Bouton Galerie");
		menu.add(boutonGalerie);
		
		JButton boutonRecherche = new JButton("<html><body><center>"+this.engine.getRecherche()+"</center></body></html>");
		boutonRecherche.setBackground(this.engine.getRechercheColor());
		boutonRecherche.addActionListener(this.ctrl);
		boutonRecherche.setActionCommand("Bouton Recherche");
		menu.add(boutonRecherche);
		
		JButton boutonGestion = new JButton("<html><body><center>"+this.engine.getGestion()+"</center></body></html>");
		boutonGestion.setBackground(this.engine.getGestionColor());
		boutonGestion.addActionListener(this.ctrl);
		boutonGestion.setActionCommand("Bouton Gestion");
		menu.add(boutonGestion);
		
		JButton boutonOptions = new JButton("<html><body><center>"+this.engine.getOptions()+"</center></body></html>");
		boutonOptions.setBackground(this.engine.getOptionsColor());
		boutonOptions.addActionListener(this.ctrl);
		boutonOptions.setActionCommand("Bouton Options");
		menu.add(boutonOptions);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = menu;
		panel.add(menu,BorderLayout.CENTER);
	}
	
	public void afficherGalerie(){
		panel.removeAll();
		JPanel galerie = new JPanel();
		galerie.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreGalerie());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		galerie.add(titre,BorderLayout.NORTH);
		
		JPanel galerieC = new JPanel();
		galerieC.setLayout(new BorderLayout(10,10));
		
		JPanel galerieLigne2 = new JPanel();
		galerieLigne2.setLayout(new BorderLayout(5,5));
		galerieLigne2.add(new JLabel("Trier par"),BorderLayout.WEST);
		galerieLigne2.add(new JComboBox<String>(),BorderLayout.CENTER);
		JButton ok = new JButton("OK");
		ok.addActionListener(ctrl);
		ok.setActionCommand("OK Tri");
		galerieLigne2.add(ok,BorderLayout.EAST);
		galerieC.add(galerieLigne2,BorderLayout.NORTH);
		
		JScrollPane galerieLigne3 = new JScrollPane();
		JPanel panelg = new JPanel();
		panelg.setLayout(new GridLayout(4,1));
		
		JLayeredPane layered1 = new JLayeredPane();
		layered1.setPreferredSize(new Dimension(350,150));
		layered1.setBorder(BorderFactory.createTitledBorder("Lundi 27 Avril 2015"));
		ImageIcon image = new ImageIcon("data/chat.jpg");
		JLabel lab = new JLabel(image);
		lab.setToolTipText("Petit Lapin !");
		lab.setBounds(10, 20, 100, 100);
		layered1.add(lab,JLayeredPane.DEFAULT_LAYER);
		ImageIcon image2 = new ImageIcon("data/lapin.jpg");
		JLabel lab2 = new JLabel(image2);
		lab2.setBounds(120, 20, 100, 100);
		layered1.add(lab2,JLayeredPane.DEFAULT_LAYER);
		
		
		JLayeredPane layered2 = new JLayeredPane();
		layered2.setPreferredSize(new Dimension(350,150));
		layered2.setBorder(BorderFactory.createTitledBorder("Samedi 25 Avril 2015"));
		ImageIcon image3 = new ImageIcon("data/chat.jpg");
		JLabel lab3 = new JLabel(image3);
		lab3.setBounds(10, 20, 100, 100);
		layered2.add(lab3,JLayeredPane.DEFAULT_LAYER);
		
		JLayeredPane layered3 = new JLayeredPane();
		layered3.setPreferredSize(new Dimension(350,150));
		layered3.setBorder(BorderFactory.createTitledBorder("Mardi 21 Avril 2015"));
		
		JLayeredPane layered4 = new JLayeredPane();
		layered4.setPreferredSize(new Dimension(350,150));
		layered4.setBorder(BorderFactory.createTitledBorder("Lundi 20 Avril 2015"));
		
		panelg.add(layered1);
		panelg.add(layered2);
		panelg.add(layered3);
		panelg.add(layered4);
		galerieLigne3.setViewportView(panelg);
		galerieC.add(galerieLigne3,BorderLayout.CENTER);
		
		
		JPanel galerieLigne9 = new JPanel();
		galerieLigne9.setLayout(new GridLayout(1,3,20,20));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		galerieLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		galerieLigne9.add(accueil);
		galerieLigne9.add(new JButton("Ajouter selection"));
		galerie.add(galerieLigne9,BorderLayout.SOUTH);
		galerie.add(galerieC,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = galerie;
		panel.add(galerie, BorderLayout.CENTER);
		
	}
	
	public void afficherGestion(){
		panel.removeAll();
		JPanel gestion = new JPanel();
		gestion.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreGestion());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		gestion.add(titre,BorderLayout.NORTH);
		
		JPanel gestionC = new JPanel();
		gestionC.setLayout(new BorderLayout(10,10));
		
		JPanel gestionLigne2 = new JPanel();
		gestionLigne2.setLayout(new GridLayout(3,1,10,10));
		gestionLigne2.add(new JButton("Ajouter une photo"));
		JPanel gestionLigne2Pan1 = new JPanel();
		gestionLigne2Pan1.setLayout(new GridLayout(1,2,5,5));
		gestionLigne2Pan1.add(new JButton("Ajouter une categorie"));
		gestionLigne2Pan1.add(new JTextField("Nom de la categorie"));
		gestionLigne2.add(gestionLigne2Pan1);
		JPanel gestionLigne2Pan2 = new JPanel();
		gestionLigne2Pan2.setLayout(new GridLayout(1,2,5,5));
		gestionLigne2Pan2.add(new JButton("Supprimer une categorie"));
		gestionLigne2Pan2.add(new JComboBox<String>());
		gestionLigne2.add(gestionLigne2Pan2);
		gestionC.add(gestionLigne2,BorderLayout.NORTH);
		
		JScrollPane gestionLigne3 = new JScrollPane();
		JPanel panelg = new JPanel();
		panelg.setLayout(new GridLayout(4,1));
		
		JLayeredPane layered1 = new JLayeredPane();
		layered1.setPreferredSize(new Dimension(350,150));
		layered1.setBorder(BorderFactory.createTitledBorder("Photo 1"));
		ImageIcon image = new ImageIcon("data/chat.jpg");
		JLabel lab = new JLabel(image);
		lab.setBounds(10, 20, 100, 100);
		layered1.add(lab,JLayeredPane.DEFAULT_LAYER);
		
		JLayeredPane layered2 = new JLayeredPane();
		layered2.setPreferredSize(new Dimension(350,150));
		layered2.setBorder(BorderFactory.createTitledBorder("Photo 2"));
		
		JLayeredPane layered3 = new JLayeredPane();
		layered3.setPreferredSize(new Dimension(350,150));
		layered3.setBorder(BorderFactory.createTitledBorder("Photo 3"));
		
		JLayeredPane layered4 = new JLayeredPane();
		layered4.setPreferredSize(new Dimension(350,150));
		layered4.setBorder(BorderFactory.createTitledBorder("Photo 4"));
		
		panelg.add(layered1);
		panelg.add(layered2);
		panelg.add(layered3);
		panelg.add(layered4);
		gestionLigne3.setViewportView(panelg);
		gestionC.add(gestionLigne3,BorderLayout.CENTER);
		
		JPanel gestionLigne4 = new JPanel();
		gestionLigne4.setLayout(new GridLayout(2,1,10,10));
		JPanel gestionLigne4Pan1 = new JPanel();
		gestionLigne4Pan1.setLayout(new GridLayout(1,2,5,5));
		gestionLigne4Pan1.add(new JButton("Supprimer"));
		gestionLigne4Pan1.add(new JButton("Ajouter une recherche"));
		gestionLigne4.add(gestionLigne4Pan1);
		gestionLigne4.add(new JButton("Editer la selection"));
		gestionC.add(gestionLigne4,BorderLayout.SOUTH);
		
		JPanel gestionLigne9 = new JPanel();
		gestionLigne9.setLayout(new GridLayout(1,3,20,20));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		gestionLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		gestionLigne9.add(accueil);
		gestionLigne9.add(new JButton("Ajouter selection"));
		gestion.add(gestionLigne9,BorderLayout.SOUTH);
		
		gestion.add(gestionC,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = gestion;
		panel.add(gestion, BorderLayout.CENTER);
	}
	public void afficherRecherche(){
		panel.removeAll();
		JPanel recherche = new JPanel();
		recherche.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreRecherche());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		recherche.add(titre,BorderLayout.NORTH);
		
		
		JPanel rechercheC = new JPanel();
		rechercheC.setLayout(new GridLayout(7,1,10,10));
		
		JPanel rechercheLigne2 = new JPanel();
		rechercheLigne2.setLayout(new GridLayout(1,2));
		rechercheLigne2.add(new JLabel("Titre :"));
		rechercheLigne2.add(new JTextField());
		rechercheC.add(rechercheLigne2);
		
		JPanel rechercheLigne3 = new JPanel();
		rechercheLigne3.setLayout(new GridLayout(1,2));
		rechercheLigne3.add(new JLabel("Categorie :"));
		rechercheLigne3.add(new JComboBox<String>());
		rechercheC.add(rechercheLigne3);
		
		JPanel rechercheLigne4 = new JPanel();
		rechercheLigne4.setLayout(new GridLayout(1,2));
		rechercheLigne4.add(new JLabel("Auteur :"));
		rechercheLigne4.add(new JComboBox<String>());
		rechercheC.add(rechercheLigne4);
		
		JPanel rechercheLigne5 = new JPanel();
		rechercheLigne5.setLayout(new GridLayout(1,4,5,5));
		rechercheLigne5.add(new JLabel("Entre le :"));
		rechercheLigne5.add(new JComboBox<String>());
		rechercheLigne5.add(new JComboBox<String>());
		rechercheLigne5.add(new JComboBox<String>());
		rechercheC.add(rechercheLigne5);
		
		JPanel rechercheLigne6 = new JPanel();
		rechercheLigne6.setLayout(new GridLayout(1,4,5,5));
		rechercheLigne6.add(new JLabel("Et le :"));
		rechercheLigne6.add(new JComboBox<String>());
		rechercheLigne6.add(new JComboBox<String>());
		rechercheLigne6.add(new JComboBox<String>());
		rechercheC.add(rechercheLigne6);
		
		JPanel rechercheLigne7 = new JPanel();
		rechercheLigne7.setLayout(new GridLayout(1,2));
		rechercheLigne7.add(new JLabel("Localisation :"));
		rechercheLigne7.add(new JComboBox<String>());
		rechercheC.add(rechercheLigne7);
		
		rechercheC.add(new JTextField("Entrez ici vos mots cles (separes par des ; )."));
		
		JPanel rechercheLigne9 = new JPanel();
		rechercheLigne9.setLayout(new GridLayout(1,3,20,20));
		
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		rechercheLigne9.add(butback);
		
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		rechercheLigne9.add(accueil);
		
		JButton butrecherche = new JButton("Rechercher");
		butrecherche.addActionListener(ctrl);
		butrecherche.setActionCommand("Faire Recherche");
		rechercheLigne9.add(butrecherche);
		recherche.add(rechercheLigne9,BorderLayout.SOUTH);
		
		recherche.add(rechercheC,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = recherche;
		panel.add(recherche, BorderLayout.CENTER);
	}
	public void afficherOptions(){
		panel.removeAll();
		JPanel options = new JPanel();
		options.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreOptions());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		options.add(titre,BorderLayout.NORTH);
		
		JPanel optionsLigne9 = new JPanel();
		optionsLigne9.setLayout(new GridLayout(1,3,20,20));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		optionsLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		optionsLigne9.add(accueil);
		optionsLigne9.add(new JButton("Ajouter selection"));
		options.add(optionsLigne9,BorderLayout.SOUTH);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = options;
		panel.add(options,BorderLayout.CENTER);
	}
	
	public void afficherEdition(){
		panel.removeAll();
		JPanel edition = new JPanel();
		edition.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreEdition());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		edition.add(titre,BorderLayout.NORTH);		
		JPanel editionC = new JPanel();
		editionC.setLayout(new GridLayout(6,1,10,10));
		
		JPanel editionLigne2 = new JPanel();
		editionLigne2.setLayout(new GridLayout(1,3));
		JPanel editionLigne2Pan1 = new JPanel();
		editionLigne2Pan1.setLayout(new GridLayout(2,1));
		editionLigne2Pan1.add(new JLabel("Titre :"));
		editionLigne2Pan1.add(new JLabel("Auteur :"));
		editionLigne2.add(editionLigne2Pan1);
		JPanel editionLigne2Pan2 = new JPanel();
		editionLigne2Pan2.setLayout(new GridLayout(2,1));
		editionLigne2Pan2.add(new JTextField());
		editionLigne2Pan2.add(new JTextField());
		editionLigne2.add(editionLigne2Pan2);
		editionLigne2.add(new JLabel(new ImageIcon("lapin.jpg")));
		editionC.add(editionLigne2);
		
		JPanel editionLigne3 = new JPanel();
		editionLigne3.setLayout(new GridLayout(1,2));
		editionLigne3.add(new JLabel("Categorie :"));
		editionLigne3.add(new JComboBox<String>());
		editionC.add(editionLigne3);
		
		JPanel editionLigne4 = new JPanel();
		editionLigne4.setLayout(new GridLayout(1,2));
		editionLigne4.add(new JLabel("Pays de prise de vue :"));
		editionLigne4.add(new JTextField());
		editionC.add(editionLigne4);
		
		JPanel editionLigne5 = new JPanel();
		editionLigne5.setLayout(new GridLayout(1,4,5,5));
		editionLigne5.add(new JLabel("Prise le :"));
		editionLigne5.add(new JComboBox<String>());
		editionLigne5.add(new JComboBox<String>());
		editionLigne5.add(new JComboBox<String>());
		editionC.add(editionLigne5);
		
		
		editionC.add(new JTextField("Entrez les mots cles de la photo (separes par des ; )."));
		
		JPanel editionLigne7 = new JPanel();
		editionLigne7.setLayout(new GridLayout(1,2,10,10));
		editionLigne7.add(new JButton("Recherche par similarite"));
		editionLigne7.add(new JButton("Supprimer la photo"));
		editionC.add(editionLigne7);
		
		JPanel editionLigne9 = new JPanel();
		editionLigne9.setLayout(new GridLayout(1,3,20,20));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		editionLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		editionLigne9.add(accueil);
		editionLigne9.add(new JPanel());
		edition.add(editionLigne9,BorderLayout.SOUTH);
		
		edition.add(editionC,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = edition;
		panel.add(edition, BorderLayout.CENTER);
	}
	
	public void afficherDiaporama(){
		panel.removeAll();
		JPanel diaporama = new JPanel();
		diaporama.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreDiaporama());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		diaporama.add(titre,BorderLayout.NORTH);		
		JPanel diaporamaC = new JPanel();
		diaporamaC.setLayout(new BorderLayout(10,10));
		
		
		diaporamaC.add(new JLabel("Petit Lapin !"),BorderLayout.NORTH);
		
		
		diaporamaC.add(new JLabel(new ImageIcon("data/chat.jpg")),BorderLayout.CENTER);
		
		JPanel diaporamaCBout = new JPanel();
		diaporamaCBout.setLayout(new GridLayout(1,3,10,10));
		diaporamaCBout.add(new JButton("Previous"));
		JPanel diaporamaCBout2 = new JPanel();
		diaporamaCBout2.setLayout(new GridLayout(2,1,5,5));
		diaporamaCBout2.add(new JButton("Play/Pause"));
		diaporamaCBout2.add(new JButton("Aleatoire"));
		diaporamaCBout.add(diaporamaCBout2);
		diaporamaCBout.add(new JButton("Next"));
		diaporamaC.add(diaporamaCBout,BorderLayout.SOUTH);
		
		JPanel diaporamaLigne9 = new JPanel();
		diaporamaLigne9.setLayout(new GridLayout(1,3,20,20));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		diaporamaLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		diaporamaLigne9.add(accueil);
		JButton boutEdition = new JButton("Edition");
		boutEdition.addActionListener(ctrl);
		boutEdition.setActionCommand("Bouton Edition");
		diaporamaLigne9.add(boutEdition);
		diaporama.add(diaporamaLigne9,BorderLayout.SOUTH);
		
		diaporama.add(diaporamaC,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = diaporama;
		
		panel.add(diaporama,BorderLayout.CENTER);
	}
	
	
	
	public JPanel getPanelPrecedent() {
		return panelPrecedent;
	}






	/** 
	 *  Get the main panel
	 *
	 * @return the main panel of the application
	 */
	public JPanel getPanel() {

		return panel;
	} // ----------------------------------------------------------- getPanel()

} // ------------------------------------------------------------- Class AppView

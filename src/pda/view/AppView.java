/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package pda.view;

/**
 * Importation des librairies java et des packages CONTROL et DATAS
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Enumeration;

import pda.control.*;
import pda.datas.*;

import javax.swing.*;

import datas.*;

/**
 * Classe AppView correspondant a la partie graphique de l'application
 * @author FRETAY Juliette et LE POLLES--POTIN Leandre - Groupe 1C
 * 
 * @version 18/06/15
 *
 */
public class AppView {

	/*
	 * Private implementation -------------------------------------------------
	 */
	
	/** partie control de l'application*/
	private AppCtrl ctrl;
	
	/** the engine of the application */
	private AppDatas engine;

	/** the panel associated to the App application (App runs in this panel) */
	private JPanel panel;
	
	
	
	//Trier
	private JPanel panelCourant;
	private JPanel panelPrecedent;
	
	

	/*
	 * Les composants graphiques de l'application dont nous devons avoir acces pour la partie 
	 */
	
	/* Les JComboBox */
	private JComboBox<String> comboGalerie;
	private JComboBox<String> comboTheme;
	private JComboBox<String> comboCat;
	private JComboBox<String> comboJour;
	private JComboBox<String> comboMois;
	private JComboBox<String> comboAnnee;
	private JComboBox<String> comboJour2;
	private JComboBox<String> comboMois2;
	private JComboBox<String> comboAnnee2;
	private JComboBox<String> comboUsers;
	
	/* Les JTextField */
	private JTextField texteTitre;
	private JTextField texteMots;
	private JTextField texteAuteur;
	private JTextField textePays;
	
	/*Les JButton */
	private JButton playpause;
	
	/*Les boolean */
	private boolean lecture;

	private TestThread threadDiapo;

	
	
	
	/*
	 *  Public ressources -----------------------------------------------------
	 */


	/**
	 * Construction of the App IHM.
	 *
	 * @param anEngine link to the App datas
	 * @param ctrl link to the App control
	 */
	public AppView (AppCtrl ctrl, AppDatas anEngine ) {
		
		this.engine = anEngine;
		this.ctrl = ctrl;
		
		this.panelCourant = new JPanel();
		this.panelPrecedent = new JPanel();
		this.lecture = false;
		
		// mise en place de l'ihm
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		this.afficherMenu();

	} // ------------------------------------------------------------- AppView()

	
	
	
	/*
	 *  Public methods ------------------------------------------------------------------
	 */
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant au menu principal de l'application
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
		
		this.engine.setCollectionSelect(this.engine.getUtilisateurSelect().getAllPhotos());
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = menu;
		panel.add(menu,BorderLayout.CENTER);
	}
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant a une galerie
	 * @param source Collection de photos qui va etre affichee dans la galerie
	 */
	public void afficherGalerie(Collection source){
		panel.removeAll();
		JPanel galerie = new JPanel();
		galerie.setLayout(new BorderLayout(10,10));
		
		Collection[] lesPhotos = source.split();
		
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
		comboGalerie = new JComboBox<String>();
		comboGalerie.addItem("Titre Alphabetique");
		comboGalerie.addItem("Titre Non Alphabetique");
		comboGalerie.addItem("Categorie Alphabetique");
		comboGalerie.addItem("Categorie Non Alphabetique");
		comboGalerie.addItem("Auteur Alphabetique");
		comboGalerie.addItem("Auteur Non Alphabetique");
		comboGalerie.addItem("Date Croissante");
		comboGalerie.addItem("Date Decroissante");
		comboGalerie.addItem("Pays Alphabetique");
		comboGalerie.addItem("Pays Non Alphabetique");
		galerieLigne2.add(comboGalerie,BorderLayout.CENTER);
		JButton ok = new JButton("OK");
		ok.addActionListener(ctrl);
		ok.setActionCommand("OK Tri");
		galerieLigne2.add(ok,BorderLayout.EAST);
		galerieC.add(galerieLigne2,BorderLayout.NORTH);
		JScrollPane galerieLigne3 = new JScrollPane();
		JPanel panelg = new JPanel();
		panelg.setLayout(new GridLayout(4,1));
		
		
		panelg.setLayout(new BoxLayout(panelg,BoxLayout.PAGE_AXIS));
		
		for(int i=0;i<lesPhotos.length;i++){
			JLayeredPane layered = new JLayeredPane();
			layered.setBorder(BorderFactory.createTitledBorder(lesPhotos[i].getTitre()));
			int nbrPhotos = lesPhotos[i].getListePhotos().size();
			int nbrLignes = (int) (nbrPhotos/2);
			if(nbrPhotos%2 == 1){
				nbrLignes++;
			}
			layered.setLayout(new GridLayout(nbrLignes,2,5,5));
			layered.setPreferredSize(new Dimension(280,nbrLignes*160));
			for(int y=0;y<lesPhotos[i].getListePhotos().size();y++){
				int a = lesPhotos[i].getListePhotos().get(y).getImg().getWidth();
				int b = lesPhotos[i].getListePhotos().get(y).getImg().getHeight();
				if(a>b){
					b = b /(a/150);
					a = 150;		
				}
				else{
					a = a/(b/150);
					b=150;
				}
				ImageIcon limage = new ImageIcon(this.getScaledImage(lesPhotos[i].getListePhotos().get(y).getImg(),a,b));
				
				JPanel lepanel = new JPanel();
				lepanel.setLayout(new BorderLayout());
				JButton label = new JButton(limage);
				label.setOpaque(false);
				label.setContentAreaFilled(false);
				label.setBorderPainted(false);
				label.setToolTipText(lesPhotos[i].getListePhotos().get(y).getTitre());
				label.addActionListener(ctrl);
				label.setActionCommand("Image"+lesPhotos[i].getListePhotos().get(y).getImageURL());
				lepanel.add(label,BorderLayout.CENTER);
				layered.add(lepanel);
			}
			panelg.add(layered);
		}
		galerieLigne3.setViewportView(panelg);
		galerieC.add(galerieLigne3,BorderLayout.CENTER);
		
		JPanel galerieLigne9 = new JPanel();
		galerieLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		galerieLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		galerieLigne9.add(accueil);
		JButton diapo = new JButton("Diaporama");
		diapo.addActionListener(ctrl);
		diapo.setActionCommand("Bouton Diaporama");
		galerieLigne9.add(diapo);
		galerie.add(galerieLigne9,BorderLayout.SOUTH);
		galerie.add(galerieC,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = galerie;
		panel.add(galerie, BorderLayout.CENTER);
		
	}
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant a une galerie
	 * @param lesPhotos Tableau de collections qui sera affiche dans la galerie
	 */
	/*public void afficherGalerie(Collection[] lesPhotos){
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
		comboGalerie = new JComboBox<String>();
		comboGalerie.addItem("Titre Alphabetique");
		comboGalerie.addItem("Titre Non Alphabetique");
		comboGalerie.addItem("Categorie Alphabetique");
		comboGalerie.addItem("Categorie Non Alphabetique");
		comboGalerie.addItem("Auteur Alphabetique");
		comboGalerie.addItem("Auteur Non Alphabetique");
		comboGalerie.addItem("Date Croissante");
		comboGalerie.addItem("Date Decroissante");
		comboGalerie.addItem("Pays Alphabetique");
		comboGalerie.addItem("Pays Non Alphabetique");
		galerieLigne2.add(comboGalerie,BorderLayout.CENTER);
		JButton ok = new JButton("OK");
		ok.addActionListener(ctrl);
		ok.setActionCommand("OK Tri");
		galerieLigne2.add(ok,BorderLayout.EAST);
		galerieC.add(galerieLigne2,BorderLayout.NORTH);
		JScrollPane galerieLigne3 = new JScrollPane();
		JPanel panelg = new JPanel();
		panelg.setLayout(new GridLayout(4,1));
		
		
		panelg.setLayout(new BoxLayout(panelg,BoxLayout.PAGE_AXIS));
		
		for(int i=0;i<lesPhotos.length;i++){
			JLayeredPane layered = new JLayeredPane();
			layered.setBorder(BorderFactory.createTitledBorder(lesPhotos[i].getTitre()));
			int nbrPhotos = lesPhotos[i].getListePhotos().size();
			int nbrLignes = (int) (nbrPhotos/2);
			if(nbrPhotos%2 == 1){
				nbrLignes++;
			}
			layered.setLayout(new GridLayout(nbrLignes,2,5,5));
			layered.setPreferredSize(new Dimension(280,nbrLignes*160));
			for(int y=0;y<lesPhotos[i].getListePhotos().size();y++){
				int a = lesPhotos[i].getListePhotos().get(y).getImg().getWidth();
				int b = lesPhotos[i].getListePhotos().get(y).getImg().getHeight();
				if(a>b){
					b = b /(a/150);
					a = 150;		
				}
				else{
					a = a/(b/150);
					b=150;
				}
				ImageIcon limage = new ImageIcon(this.getScaledImage(lesPhotos[i].getListePhotos().get(y).getImg(),a,b));
				
				JPanel lepanel = new JPanel();
				lepanel.setLayout(new BorderLayout());
				JButton label = new JButton(limage);
				label.setOpaque(false);
				label.setContentAreaFilled(false);
				label.setBorderPainted(false);
				label.setToolTipText(lesPhotos[i].getListePhotos().get(y).getTitre());
				label.addActionListener(ctrl);
				label.setActionCommand("Image"+lesPhotos[i].getListePhotos().get(y).getImageURL());
				lepanel.add(label,BorderLayout.CENTER);
				layered.add(lepanel);
			}
			panelg.add(layered);
		}
		galerieLigne3.setViewportView(panelg);
		galerieC.add(galerieLigne3,BorderLayout.CENTER);
		
		JPanel galerieLigne9 = new JPanel();
		galerieLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		galerieLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		galerieLigne9.add(accueil);
		JButton diapo = new JButton("Diaporama");
		diapo.addActionListener(ctrl);
		diapo.setActionCommand("Bouton Diaporama");
		galerieLigne9.add(diapo);
		galerie.add(galerieLigne9,BorderLayout.SOUTH);
		galerie.add(galerieC,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = galerie;
		panel.add(galerie, BorderLayout.CENTER);
		
	}*/
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant a la gestion des photographies
	 */
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
		gestionLigne2.setLayout(new GridLayout(6,1,10,10));
		JButton add = new JButton("Ajouter une photo");
		add.addActionListener(ctrl);
		add.setActionCommand("Ajouter Photo");
		gestionLigne2.add(add);
		gestionLigne2.add(new JSeparator());
		JPanel gestionLigne2Pan1 = new JPanel();
		gestionLigne2Pan1.setLayout(new GridLayout(1,2,5,5));
		JButton addCategorie = new JButton("Ajouter une categorie");
		addCategorie.addActionListener(ctrl);
		addCategorie.setActionCommand("Ajouter Categorie");
		gestionLigne2Pan1.add(addCategorie);
		gestionLigne2Pan1.add(new JTextField("Nom de la categorie"));
		gestionLigne2.add(gestionLigne2Pan1);
		JPanel gestionLigne2Pan2 = new JPanel();
		gestionLigne2Pan2.setLayout(new GridLayout(1,2,5,5));
		JButton suppCat = new JButton("Supprimer une categorie");
		suppCat.addActionListener(ctrl);
		suppCat.setActionCommand("Supprimer Categorie");
		gestionLigne2Pan2.add(suppCat);
		gestionLigne2Pan2.add(new JComboBox<String>());
		gestionLigne2.add(gestionLigne2Pan2);
		gestionLigne2.add(new JSeparator());
		JButton boutselection = new JButton("Gestion de la selection");
		boutselection.addActionListener(ctrl);
		boutselection.setActionCommand("Bouton Gestion Selection");
		gestionLigne2.add(boutselection);
		gestionC.add(gestionLigne2,BorderLayout.CENTER);
		
		
		
		JPanel gestionLigne9 = new JPanel();
		gestionLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		gestionLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		gestionLigne9.add(accueil);
		gestionLigne9.add(new JPanel());
		gestion.add(gestionLigne9,BorderLayout.SOUTH);
		
		gestion.add(gestionC,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = gestion;
		panel.add(gestion, BorderLayout.CENTER);
	}
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant a la gestion des photos selectionnees
	 */
	public void afficherGestionSelection(){
		panel.removeAll();
		JPanel gestion = new JPanel();
		gestion.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreGestionSelection());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		gestion.add(titre,BorderLayout.NORTH);
		
		JPanel gestionC = new JPanel();
		gestionC.setLayout(new BorderLayout(10,10));
		
		JScrollPane gestionLigne3 = new JScrollPane();
		JPanel panelg = new JPanel();
		panelg.setLayout(new GridLayout(4,1));
		
		JLayeredPane layered1 = new JLayeredPane();
		layered1.setPreferredSize(new Dimension(350,150));
		layered1.setBorder(BorderFactory.createTitledBorder("Photo 1"));
		ImageIcon image = new ImageIcon("data/bljblm.jpg");
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
		JButton sup = new JButton("Supprimer");
		sup.addActionListener(ctrl);
		sup.setActionCommand("Supprimer Selection");
		gestionLigne4Pan1.add(sup);
		JButton add = new JButton("Ajouter une recherche");
		add.addActionListener(ctrl);
		add.setActionCommand("Ajouter Recherche");
		gestionLigne4Pan1.add(add);
		gestionLigne4.add(gestionLigne4Pan1);
		JButton edit = new JButton("Editer la selection");
		edit.addActionListener(ctrl);
		edit.setActionCommand("Edit Selection");
		gestionLigne4.add(edit);
		gestionC.add(gestionLigne4,BorderLayout.SOUTH);
		
		JPanel gestionLigne9 = new JPanel();
		gestionLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		gestionLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		gestionLigne9.add(accueil);
		JButton boutAjouter = new JButton("Ajouter selection");
		boutAjouter.addActionListener(ctrl);
		boutAjouter.setActionCommand("Ajouter Selection");
		gestionLigne9.add(boutAjouter);
		gestion.add(gestionLigne9,BorderLayout.SOUTH);
		
		gestion.add(gestionC,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = gestion;
		panel.add(gestion, BorderLayout.CENTER);
	}
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant aux recherches
	 */
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
		texteTitre = new JTextField();
		rechercheLigne2.add(texteTitre);
		rechercheC.add(rechercheLigne2);
		
		JPanel rechercheLigne3 = new JPanel();
		rechercheLigne3.setLayout(new GridLayout(1,2));
		rechercheLigne3.add(new JLabel("Categorie :"));
		comboCat = new JComboBox<String>();
		comboCat.addItem("-");
		Enumeration <Collection> collec = this.engine.getUtilisateurSelect().toutesCollections();
		while(collec.hasMoreElements() ){
			comboCat.addItem(collec.nextElement().getTitre());
		}
		rechercheLigne3.add(comboCat);
		rechercheC.add(rechercheLigne3);
		
		JPanel rechercheLigne4 = new JPanel();
		rechercheLigne4.setLayout(new GridLayout(1,2));
		rechercheLigne4.add(new JLabel("Auteur :"));
		texteAuteur = new JTextField();
		rechercheLigne4.add(texteAuteur);
		rechercheC.add(rechercheLigne4);
		
		JPanel rechercheLigne5 = new JPanel();
		rechercheLigne5.setLayout(new GridLayout(1,4,5,5));
		rechercheLigne5.add(new JLabel("Entre le :"));
		comboJour = new JComboBox<String>();
		comboJour.addItem("-");
		for(int i=1;i<=31;i++){
			comboJour.addItem(""+i+"");
		}
		rechercheLigne5.add(comboJour);
		comboMois = new JComboBox<String>();
		comboMois.addItem("-");	
		comboMois.addItem("janvier");
		comboMois.addItem("fevrier");
		comboMois.addItem("mars");
		comboMois.addItem("avril");
		comboMois.addItem("mai");
		comboMois.addItem("juin");
		comboMois.addItem("juillet");
		comboMois.addItem("aout");
		comboMois.addItem("septembre");
		comboMois.addItem("octobre");
		comboMois.addItem("novembre");
		comboMois.addItem("decembre");
		rechercheLigne5.add(comboMois);
		comboAnnee = new JComboBox<String>();
		comboAnnee.addItem("-");
		for(int i=1990;i<=2015;i++){
			comboAnnee.addItem(""+i+"");
		}
		rechercheLigne5.add(comboAnnee);
		rechercheC.add(rechercheLigne5);
		
		JPanel rechercheLigne6 = new JPanel();
		rechercheLigne6.setLayout(new GridLayout(1,4,5,5));
		rechercheLigne6.add(new JLabel("Et le :"));
		comboJour2 = new JComboBox<String>();
		comboJour2.addItem("-");
		for(int i=1;i<=31;i++){
			comboJour2.addItem(""+i+"");
		}
		rechercheLigne6.add(comboJour2);
		comboMois2 = new JComboBox<String>();
		comboMois2.addItem("-");
		comboMois2.addItem("janvier");
		comboMois2.addItem("fevrier");
		comboMois2.addItem("mars");
		comboMois2.addItem("avril");
		comboMois2.addItem("mai");
		comboMois2.addItem("juin");
		comboMois2.addItem("juillet");
		comboMois2.addItem("aout");
		comboMois2.addItem("septembre");
		comboMois2.addItem("octobre");
		comboMois2.addItem("novembre");
		comboMois2.addItem("decembre");
		rechercheLigne6.add(comboMois2);
		comboAnnee2 = new JComboBox<String>();
		comboAnnee2.addItem("-");
		for(int i=2015;i>=1990;i--){
			comboAnnee2.addItem(""+i+"");
		}
		rechercheLigne6.add(comboAnnee2);
		rechercheC.add(rechercheLigne6);
		
		JPanel rechercheLigne7 = new JPanel();
		rechercheLigne7.setLayout(new GridLayout(1,2));
		rechercheLigne7.add(new JLabel("Localisation :"));
		textePays = new JTextField();
		rechercheLigne7.add(textePays);
		rechercheC.add(rechercheLigne7);
		
		texteMots = new JTextField("Entrez ici vos mots cles (separes par des ; ).");
		rechercheC.add(texteMots);
		
		JPanel rechercheLigne9 = new JPanel();
		rechercheLigne9.setLayout(new GridLayout(1,3,5,5));
		
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
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant aux options de l'application
	 */
	public void afficherOptions(){
		panel.removeAll();
		JPanel options = new JPanel();
		options.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreOptions());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		options.add(titre,BorderLayout.NORTH);
		
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(9,1,5,5));
		pan.add(new JLabel("Se connecter en tant qu'utilisateur :"));
		comboUsers = new JComboBox<String>();
		for(int i=1;i<this.engine.getListeUsers().length;i++){
			comboUsers.addItem(this.engine.getListeUsers()[i].getNom());
		}
		pan.add(comboUsers);
		JPanel pan1 = new JPanel();
		pan1.setLayout(new GridLayout(1,2,5,5));
		JButton co = new JButton("Se connecter");
		co.addActionListener(ctrl);
		co.setActionCommand("Se Connecter");
		pan1.add(co);
		JButton deco = new JButton("Se deconnecter");
		deco.addActionListener(ctrl);
		deco.setActionCommand("Se Deconnecter");		
		pan1.add(deco);
		pan.add(pan1);
		JPanel pan3 = new JPanel();
		pan3.setLayout(new GridLayout(1,2,5,5));
		JButton add = new JButton("Ajouter Utilisateur");
		add.addActionListener(ctrl);
		add.setActionCommand("Ajouter Utilisateur");
		pan3.add(add);
		JButton supp = new JButton("Supprimer l'utilisateur");
		supp.addActionListener(ctrl);
		supp.setActionCommand("Supprimer Utilisateur");		
		pan3.add(supp);
		pan.add(pan3);
		pan.add(new JLabel("Theme :"));
		comboTheme = new JComboBox<String>();
		comboTheme.addItem("Barbie");
		comboTheme.addItem("Plage");
		comboTheme.addItem("Black");
		comboTheme.addItem("Noel");
		pan.add(comboTheme);
		JPanel pan2 = new JPanel();
		pan2.setLayout(new GridLayout(1,2,5,5));
		JButton boutTheme = new JButton("Choisir ce theme");
		boutTheme.addActionListener(ctrl);
		boutTheme.setActionCommand("Changer Theme");
		pan2.add(boutTheme);
		JButton boutThemeD = new JButton("Theme par Defaut");
		boutThemeD.addActionListener(ctrl);
		boutThemeD.setActionCommand("Defaut Theme");
		pan2.add(boutThemeD);
		pan.add(pan2);
		JButton apropos = new JButton("A propos");
		apropos.addActionListener(ctrl);
		apropos.setActionCommand("A Propos");
		pan.add(apropos);
		JButton help = new JButton("Aide");
		help.addActionListener(ctrl);
		help.setActionCommand("Aide");
		pan.add(help);
		options.add(pan, BorderLayout.CENTER);
		
		JPanel optionsLigne9 = new JPanel();
		optionsLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		optionsLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		optionsLigne9.add(accueil);
		optionsLigne9.add(new JPanel());
		options.add(optionsLigne9,BorderLayout.SOUTH);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = options;
		panel.add(options,BorderLayout.CENTER);
	}
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant a l'edition d'une photo electionnee
	 */
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
		editionLigne2.setLayout(new GridLayout(1,2));
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
		editionLigne9.setLayout(new GridLayout(1,3,10,10));
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

	/**
	 * Methode publique permettant d'afficher le panel correspondant au diaporama de la galerie courante
	 * @param laPhoto Photo a afficher en premier dans le diaporama
	 */
	public void afficherDiaporama(Photo laPhoto){
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
		
		JLabel photo = new JLabel(laPhoto.getTitre());
		photo.setHorizontalAlignment(SwingConstants.CENTER);
		diaporamaC.add(photo,BorderLayout.NORTH);
		int a = laPhoto.getImg().getWidth();
		int b = laPhoto.getImg().getHeight();
		if(b>150){
			a = a/(b/150);
			b = 150;
			if(a>280){
				b=b/(a/280);
				a =280;
			}
		}
		else if(a>280){
			b=b/(a/280);
			a =280;
		}
		ImageIcon image = new ImageIcon(this.getScaledImage(laPhoto.getImg(),a,b));
		
		JLabel label = new JLabel(image);
		JPanel pan = new JPanel();
		pan.add(label);
		diaporamaC.add(pan,BorderLayout.CENTER);
		
		JPanel diaporamaCBout = new JPanel();
		diaporamaCBout.setLayout(new GridLayout(1,3,10,10));
		JButton previous = new JButton("Previous");
		previous.addActionListener(ctrl);
		previous.setActionCommand("Bouton Previous");
		diaporamaCBout.add(previous);
		JPanel diaporamaCBout2 = new JPanel();
		diaporamaCBout2.setLayout(new GridLayout(2,1,5,5));
		if(this.lecture == true){
			playpause = new JButton("Play");
		}
		else{
			playpause= new JButton("Pause");
		}
		playpause.addActionListener(ctrl);
		playpause.setActionCommand("Bouton Play Pause");
		diaporamaCBout2.add(playpause);
		diaporamaCBout2.add(new JButton("Aleatoire"));
		diaporamaCBout.add(diaporamaCBout2);
		JButton next = new JButton("Next");
		next.addActionListener(ctrl);
		next.setActionCommand("Bouton Next");
		diaporamaCBout.add(next);
		diaporamaC.add(diaporamaCBout,BorderLayout.SOUTH);
		
		JPanel diaporamaLigne9 = new JPanel();
		diaporamaLigne9.setLayout(new GridLayout(1,3,10,10));
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
	
	/**
	 * Methode publique permettant de faire tourner un diaporama
	 */
	public void lireDiaporama(){
		this.lecture = true;
		System.out.println("4");
			System.out.println("5");
			try {
				System.out.println("6");
				this.threadDiapo = new TestThread(this);
				
				this.afficherDiaporama(this.engine.getUtilisateurSelect().getAllPhotos().getPhotoSelect());
				System.out.println("7");
				
				System.out.println("8");
			} catch (NoPhotoFoundException e) {
				e.printStackTrace();
				System.out.println("erreur");
			}
		this.lecture = false;
	}
	
	public void nextPhoto(){
		this.engine.getUtilisateurSelect().getAllPhotos().nextPhoto();
		this.lireDiaporama();
	}
	
	public void pauseDiapo(){
		this.threadDiapo = null;
		this.getPlaypause().setText("Pause");
	}
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant a l'aide de l'application
	 */
	public void afficherAide(){
		panel.removeAll();
		JPanel aide = new JPanel();
		aide.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel("Aide");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		aide.add(titre,BorderLayout.NORTH);
		
		JScrollPane aide2 = new JScrollPane();
		JLabel aideC = new JLabel("<html>=============================================<br>Galerie<br>=============================================<br><br>blabla<br><br>=============================================<br>Recherche<br>=============================================<br><br>blabla<br><br>=============================================<br>Diaporama<br>=============================================<br><br>blabla<br><br>=============================================<br>Edition<br>=============================================<br><br>blabla<br><br>=============================================<br>Gestion<br>=============================================<br><br>blabla<br><br>=============================================<br>Options<br>=============================================<br><br>blabla<br><br>");
		aide2.setViewportView(aideC);
		
		
		
		JPanel aideLigne9 = new JPanel();
		aideLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		aideLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		aideLigne9.add(accueil);
		aideLigne9.add(new JPanel());
		aide.add(aideLigne9,BorderLayout.SOUTH);
		
		aide.add(aide2,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = aide;
		panel.add(aide, BorderLayout.CENTER);
	}
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant au a propos de l'application
	 */
	public void afficherAPropos(){
		panel.removeAll();
		JPanel aPropos = new JPanel();
		aPropos.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel("A Propos");
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		aPropos.add(titre,BorderLayout.NORTH);
		
		JLabel aProposC = new JLabel("<html>============================================= <br>  PhotoTech  <br> ============================================= <br><br> Auteurs : <br> Leandre Le Polles--Potin & Juliette Fretay <br> <br> Version : <br> Projet Programmation INFO IUT Vannes - Juin 2015 <br> <br> Notes : <br> Projet sous la tutelle de M Lefevre et M Le Lain dans le cadre de la premiere annee du DUT Informatique de l'IUT de Vannes </html>");
		
		
		JPanel aProposLigne9 = new JPanel();
		aProposLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back");
		aProposLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		aProposLigne9.add(accueil);
		aProposLigne9.add(new JPanel());
		aPropos.add(aProposLigne9,BorderLayout.SOUTH);
		
		aPropos.add(aProposC,BorderLayout.CENTER);
		
		this.panelPrecedent = this.panelCourant;
		this.panelCourant = aPropos;
		panel.add(aPropos, BorderLayout.CENTER);
	}
	
	/* =========Les accesseurs permettant de recuperer les elements graphiques utiles a la classe control de l'application=========*/
	
	
	/**
	 * Accesseur renvoyant le JComboBox des themes
	 * @return Le JComboBox des themes
	 */
	public JComboBox<String> getComboTheme() {
		return comboTheme;
	}
	
	/**
	 * Accesseur renvoyant le JComboBox de galerie
	 * @return Le JComboBox de galerie
	 */
	public JComboBox<String> getComboGalerie() {
		return comboGalerie;
	}
	
	/**
	 * Accesseur renvoyant le JComboBox des categories
	 * @return Le JComboBox des categories
	 */
	public JComboBox<String> getComboCat() {
		return comboCat;
	}
	
	/**
	 * Accesseur renvoyant le JComboBox des jours1
	 * @return Le JComboBox des jours1
	 */
	public JComboBox<String> getComboJour() {
		return comboJour;
	}

	/**
	 * Accesseur renvoyant le JComboBox des mois1
	 * @return Le JComboBox des mois1
	 */
	public JComboBox<String> getComboMois() {
		return comboMois;
	}

	/**
	 * Accesseur renvoyant le JComboBox des annees1
	 * @return Le JComboBox des annees1
	 */
	public JComboBox<String> getComboAnnee() {
		return comboAnnee;
	}

	/**
	 * Accesseur renvoyant le JComboBox des jours2
	 * @return Le JComboBox des jours2
	 */
	public JComboBox<String> getComboJour2() {
		return comboJour2;
	}

	/**
	 * Accesseur renvoyant le JComboBox des mois2
	 * @return Le JComboBox des mois2
	 */
	public JComboBox<String> getComboMois2() {
		return comboMois2;
	}
	
	/**
	 * Accesseur renvoyant le JComboBox des annees2
	 * @return Le JComboBox des annees2
	 */
	public JComboBox<String> getComboAnnee2() {
		return comboAnnee2;
	}
	
	/**
	 * Accesseur renvoyant le JComboBox des utilisateurs
	 * @return Le JComboBox des utilisateurs
	 */
	public JComboBox<String> getComboUsers() {
		return comboUsers;
	}

	/**
	 * Accesseur renvoyant le panel affiche precedemment 
	 * @return Le panel affiche precedemment 
	 */
	public JPanel getPanelPrecedent() {
		return panelPrecedent;
	}
	
	/**
	 * Accesseur renvoyant le panel courant 
	 * @return Le panel courant
	 */
	public JPanel getPanelCourant() {
		return panelCourant;
	}
	
	/**
	 * Accesseur renvoyant le Bouton Play/Pause
	 * @return Le bouton Play/Pause
	 */
	public JButton getPlaypause() {
		return playpause;
	}

	/**
	 * Accesseur renvoyant le JTextField Titre
	 * @return Le JTextField Titre
	 */
	public JTextField getTexteTitre() {
		return texteTitre;
	}

	/**
	 * Accesseur renvoyant le JTextField Auteur
	 * @return Le JTextField Auteur
	 */
	public JTextField getTexteAuteur() {
		return texteAuteur;
	}

	/**
	 * Accesseur renvoyant le JTextField Pays
	 * @return Le JTextField Pays
	 */
	public JTextField getTextePays() {
		return textePays;
	}

	/**
	 * Accesseur renvoyant le JTextField Mots Cles
	 * @return Le JTextField Mots Cles
	 */
	public JTextField getTexteMots() {
		return texteMots;
	}
	
	/** 
	 *  Get the main panel
	 * @return the main panel of the application
	 */
	public JPanel getPanel() {

		return panel;
	} // ----------------------------------------------------------- getPanel()

	
	
	/*
	 *  Private methods ------------------------------------------------------------------
	 */
	
	/**
	 * Methode privee permettant de redimensionner une image
	 * @param srcImg Image a redimensionner 
	 * @param w Nouvelle largeur
	 * @param h Nouvelle hauteur
	 * @return L'image redimensionnee
	 */
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
} // ------------------------------------------------------------- Class AppView

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
import javax.swing.text.JTextComponent;

import datas.*;

/**
 * Classe PhotoTechView correspondant a la partie graphique de l'application
 * @author FRETAY Juliette et LE POLLES--POTIN Leandre - Groupe 1C
 * 
 * @version 18/06/15
 *
 */
public class PhotoTechView {

	/*
	 * Private implementation -------------------------------------------------
	 */
	
	/** partie control de l'application*/
	private PhotoTechCtrl ctrl;
	
	/** the engine of the application */
	private PhotoTechDatas engine;

	/** the panel associated to the PhotoTech application (PhotoTech runs in this panel) */
	private JPanel panel;
	
	
	
	
	

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
	
	private JComboBox<String> comboMoisModif;

	private JComboBox<String> comboJourModif;

	private JComboBox<String> comboAnneeModif;

	private JTextField user;

	private JTextField titreEd;

	private JTextField auteurEd;

	private JComboBox<String> comboCatModif;

	private JTextField paysEd;

	private JTextField motsEd;

	private JTextField textURL;

	private JButton boutonURL;

	private JTextField titrenew;

	private JTextComponent auteurnew;

	private JTextField motsnew;

	private JComboBox<String> comboMoisnew;

	private JComboBox<String> comboJournew;

	private JComboBox<String> comboAnneenew;

	private JTextField paysnew;

	private JComboBox<String> comboCatnew;

	private JTextField textCatEd;

	private JComboBox<String> catG;

	private JLabel textCheck;

	private JTextField textnewU;
	

	
	
	
	/*
	 *  Public ressources -----------------------------------------------------
	 */


	/**
	 * Construction of the PhotoTech IHM.
	 *
	 * @param anEngine link to the PhotoTech datas
	 * @param ctrl link to the PhotoTech control
	 */
	public PhotoTechView (PhotoTechCtrl ctrl, PhotoTechDatas anEngine ) {
		
		this.engine = anEngine;
		this.ctrl = ctrl;
		
		this.lecture = false;
		
		// mise en place de l'ihm
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		this.afficherConnection();

	} // ------------------------------------------------------------- PhotoTechView()

	
	
	
	/*
	 *  Public methods ------------------------------------------------------------------
	 */
	
	/**
	 * Methode publique permettant d'afficher le panel correspondant au menu principal de l'application
	 */
	public void afficherConnection(){
		panel.removeAll();
		JPanel connection = new JPanel();
		connection.setLayout(new GridLayout(6,1,5,5));
		
		JLabel label = new JLabel("PhotoTech");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Courrier",5,20));
		label.setForeground(Color.orange);
		connection.add(label);
		
		connection.add(new JPanel());
		
		user = new JTextField("Entrez votre nom");
		connection.add(user);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,2,5,5));
		JButton connectionG = new JButton("Se connecter");
		connectionG.addActionListener(ctrl);
		connectionG.setActionCommand("Connection");
		panel1.add(connectionG);
		JButton sansUser = new JButton("Par defaut");
		sansUser.addActionListener(ctrl);
		sansUser.setActionCommand("Sans User");
		panel1.add(sansUser);
		connection.add(panel1);
		
		
		connection.add(new JPanel());
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,1,5,5));
		JButton add = new JButton("Creer un utilisateur");
		add.addActionListener(ctrl);
		add.setActionCommand("Ajouter Utilisateur");
		panel2.add(add);
		connection.add(panel2);
		
		panel.add(connection,BorderLayout.CENTER);
	}
	
	public JTextField getUser() {
		return user;
	}




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
		
		this.engine.getUtilisateurSelect().getAllPhotos().setTriTitreAlpha();
		this.engine.setCollectionSelect(this.engine.getUtilisateurSelect().getAllPhotos());
		
		
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
				label.addActionListener(this.ctrl);
				label.setActionCommand("Image"+lesPhotos[i].getListePhotos().get(y).getNomFichier());
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
		butback.setActionCommand("Back de Galerie");
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
				label.setActionCommand("Image"+lesPhotos[i].getListePhotos().get(y).getNomFichier());
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
		textCatEd = new JTextField("Nom de la categorie");
		gestionLigne2Pan1.add(textCatEd);
		gestionLigne2.add(gestionLigne2Pan1);
		JPanel gestionLigne2Pan2 = new JPanel();
		gestionLigne2Pan2.setLayout(new GridLayout(1,2,5,5));
		JButton suppCat = new JButton("Supprimer une categorie");
		suppCat.addActionListener(ctrl);
		suppCat.setActionCommand("Supprimer Categorie");
		gestionLigne2Pan2.add(suppCat);
		catG = new JComboBox<String>();
		Enumeration<Collection> enumeration = this.engine.getUtilisateurSelect().toutesCollections();
		while(enumeration.hasMoreElements()){
			Collection p = enumeration.nextElement();
			catG.addItem(p.getTitre());
		}
		gestionLigne2Pan2.add(catG);
		gestionLigne2.add(gestionLigne2Pan2);
		gestionLigne2.add(new JSeparator());
		JButton boutselection = new JButton("Verification de l'authenticite des photos");
		boutselection.addActionListener(ctrl);
		boutselection.setActionCommand("Bouton Verification");
		gestionLigne2.add(boutselection);
		gestionC.add(gestionLigne2,BorderLayout.CENTER);
		
		
		
		JPanel gestionLigne9 = new JPanel();
		gestionLigne9.setLayout(new GridLayout(1,3,10,10));
		gestionLigne9.add(new JPanel());
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		gestionLigne9.add(accueil);
		gestionLigne9.add(new JPanel());
		gestion.add(gestionLigne9,BorderLayout.SOUTH);
		
		gestion.add(gestionC,BorderLayout.CENTER);
		
		
		panel.add(gestion, BorderLayout.CENTER);
	}
	
	public void afficherCheck(){
		panel.removeAll();
		JPanel gestion = new JPanel();
		gestion.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreCheck());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		gestion.add(titre,BorderLayout.NORTH);
		
		JPanel gestionC = new JPanel();
		textCheck = new JLabel("Authenticite ...");
		textCheck.setHorizontalAlignment(SwingConstants.CENTER);
		gestionC.add(textCheck);
		
		
		JPanel gestionLigne9 = new JPanel();
		gestionLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back de Gestion");
		gestionLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		gestionLigne9.add(accueil);
		gestionLigne9.add(new JPanel());
		gestion.add(gestionLigne9,BorderLayout.SOUTH);
		
		gestion.add(gestionC,BorderLayout.CENTER);
		
		
		panel.add(gestion, BorderLayout.CENTER);
	}
	
	public void setTextCheck(String textCheck) {
		this.textCheck.setText(textCheck);
	}





	public void afficherNouveauUtilisateur(){
		panel.removeAll();
		JPanel gestion = new JPanel();
		gestion.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreNewUser());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		gestion.add(titre,BorderLayout.NORTH);
		
		JPanel panC = new JPanel();
		panC.setLayout(new GridLayout(5,1));
		panC.add(new JPanel());
		panC.add(new JLabel("Entrez votre nom :"));
		textnewU = new JTextField();
		panC.add(textnewU);
		panC.add(new JPanel());
		panC.add(new JPanel());
		gestion.add(panC,BorderLayout.CENTER);
		
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(1,3));
		JButton accueil = new JButton("Back");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Back Connection");
		pan.add(accueil);
		pan.add(new JPanel());
		JButton diapo = new JButton("Valider");
		diapo.addActionListener(ctrl);
		diapo.setActionCommand("Bouton Valider Creation User");
		pan.add(diapo);
		gestion.add(pan,BorderLayout.SOUTH);
		
		
		panel.add(gestion, BorderLayout.CENTER);
	}


	public JTextField getTextnewU() {
		return textnewU;
	}




	public JLabel getTextCheck() {
		return textCheck;
	}




	public JComboBox<String> getCatG() {
		return catG;
	}




	public JTextField getTextCatEd() {
		return textCatEd;
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
		
		
		rechercheLigne9.add(new JPanel());
		
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
		pan.setLayout(new GridLayout(7,1,5,5));
		JButton sauver = new JButton("Sauver la phototheque");
		sauver.addActionListener(ctrl);
		sauver.setActionCommand("Bouton Sauver");
		pan.add(sauver);
		pan.add(new JLabel("Changer d'utilisateur :"));
		JButton home = new JButton("Retour sur la page de connection");
		home.addActionListener(ctrl);
		home.setActionCommand("Retour Connection");
		pan.add(home);
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
		
		JPanel panaide = new JPanel();
		panaide.setLayout(new GridLayout(1,2,5,5));
		JButton apropos = new JButton("A propos");
		apropos.addActionListener(ctrl);
		apropos.setActionCommand("A Propos");
		panaide.add(apropos);
		JButton help = new JButton("Aide");
		help.addActionListener(ctrl);
		help.setActionCommand("Aide");
		panaide.add(help);
		pan.add(panaide);
		
		options.add(pan, BorderLayout.CENTER);
		
		JPanel optionsLigne9 = new JPanel();
		optionsLigne9.setLayout(new GridLayout(1,3,10,10));
		
		optionsLigne9.add(new JPanel());
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		optionsLigne9.add(accueil);
		optionsLigne9.add(new JPanel());
		options.add(optionsLigne9,BorderLayout.SOUTH);
		
		
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
		
		String titreS;
		try {
			titreS = this.engine.getCollectionSelect().getPhotoSelect().getTitre();
		} catch (NoPhotoFoundException e) {
			titreS = "Pas de titre";
			e.printStackTrace();
		}
		JLabel titreEd = new JLabel(titreS);
		editionLigne2Pan2.add(titreEd);
		String auteurS;
		try {
			auteurS = this.engine.getCollectionSelect().getPhotoSelect().getAuteur();
		} catch (NoPhotoFoundException e) {
			auteurS = "Pas d'auteur";
			e.printStackTrace();
		}
		JLabel auteurEd = new JLabel(auteurS);
		editionLigne2Pan2.add(auteurEd);
		editionLigne2.add(editionLigne2Pan2);
		editionC.add(editionLigne2);
		
		JPanel editionLigne3 = new JPanel();
		editionLigne3.setLayout(new GridLayout(1,2));
		editionLigne3.add(new JLabel("Categorie :"));
		String cat;
		try {
			cat = this.engine.getCollectionSelect().getPhotoSelect().getCollection();
		} catch (NoPhotoFoundException e1) {
			cat = "Pas de categorie";
			e1.printStackTrace();
		}
		editionLigne3.add(new JLabel(cat));
		editionC.add(editionLigne3);
		
		JPanel editionLigne4 = new JPanel();
		editionLigne4.setLayout(new GridLayout(1,2));
		editionLigne4.add(new JLabel("Pays de prise de vue :"));
		String paysS;
		try {
			paysS = this.engine.getCollectionSelect().getPhotoSelect().getPays();
		} catch (NoPhotoFoundException e) {
			paysS = "Pas de Pays";
			e.printStackTrace();
		}
		JLabel paysEd = new JLabel(paysS);
		editionLigne4.add(paysEd);
		editionC.add(editionLigne4);
		
		JPanel editionLigne5 = new JPanel();
		editionLigne5.setLayout(new GridLayout(1,2,5,5));
		editionLigne5.add(new JLabel("Prise le :"));
		String date;
		try {
			date = this.dateToString(this.engine.getCollectionSelect().getPhotoSelect());
		} catch (NoPhotoFoundException e) {
			date = "Pas de date";
			e.printStackTrace();
		}
		editionLigne5.add(new JLabel(date));
		editionC.add(editionLigne5);
		
		String mots="Mots Cles : ";
		Photo laPhoto;
		try {
			laPhoto = this.engine.getCollectionSelect().getPhotoSelect();
		} catch (NoPhotoFoundException e) {
			laPhoto = null;
			e.printStackTrace();
		}
		for(int i=0;i<laPhoto.getKeyWords().length;i++){
			mots += laPhoto.getKeyWords()[i]+"; ";
		}
		if(laPhoto.getKeyWords() == null || laPhoto.getKeyWords().length == 0){
			mots+="Pas de mot cle entre.";
		}
		editionC.add(new JLabel(mots));
		
		JPanel editionLigne7 = new JPanel();
		editionLigne7.setLayout(new GridLayout(1,2,10,10));
		JButton simil = new JButton("Recherche par similarite");
		simil.addActionListener(ctrl);
		simil.setActionCommand("Similarite");
		editionLigne7.add(simil);
		JButton supp = new JButton("Supprimer la photo");
		supp.addActionListener(ctrl);
		supp.setActionCommand("Supprimer Photo");
		editionLigne7.add(supp);
		editionC.add(editionLigne7);
		
		JPanel editionLigne9 = new JPanel();
		editionLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back de Diapo");
		editionLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		editionLigne9.add(accueil);
		JButton modif = new JButton("Modifier");
		modif.addActionListener(ctrl);
		modif.setActionCommand("Modifier");
		editionLigne9.add(modif);
		edition.add(editionLigne9,BorderLayout.SOUTH);
		
		edition.add(editionC,BorderLayout.CENTER);
		
		
		panel.add(edition, BorderLayout.CENTER);
	}
	/**
	 * Methode publique permettant d'afficher le panel correspondant a la modification d'une photo electionnee
	 */
	public void afficherEditionModification(){
		panel.removeAll();
		JPanel edition = new JPanel();
		edition.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreEditionModification());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		edition.add(titre,BorderLayout.NORTH);		
		JPanel editionC = new JPanel();
		editionC.setLayout(new GridLayout(5,1,10,10));
		
		JPanel editionLigne2 = new JPanel();
		editionLigne2.setLayout(new GridLayout(1,2));
		JPanel editionLigne2Pan1 = new JPanel();
		editionLigne2Pan1.setLayout(new GridLayout(2,1));
		editionLigne2Pan1.add(new JLabel("Titre :"));
		editionLigne2Pan1.add(new JLabel("Auteur :"));
		editionLigne2.add(editionLigne2Pan1);
		JPanel editionLigne2Pan2 = new JPanel();
		editionLigne2Pan2.setLayout(new GridLayout(2,1));
		titreEd = new JTextField();
		String titreS = "Entrez un nouveau titre";
		titreEd.setText(titreS);
		editionLigne2Pan2.add(titreEd);
		auteurEd = new JTextField();
		String auteurS = "Entrez un nouvel auteur";
		auteurEd.setText(auteurS);
		editionLigne2Pan2.add(auteurEd);
		editionLigne2.add(editionLigne2Pan2);
		editionC.add(editionLigne2);
		
		JPanel editionLigne3 = new JPanel();
		editionLigne3.setLayout(new GridLayout(1,2));
		editionLigne3.add(new JLabel("Categorie :"));
		comboCatModif = new JComboBox<String>();
		comboCatModif.addItem("-");
		Enumeration<Collection> enumeration = this.engine.getUtilisateurSelect().toutesCollections();
		while(enumeration.hasMoreElements()){
			Collection p = enumeration.nextElement();
			comboCatModif.addItem(p.getTitre());
		}
		editionLigne3.add(comboCatModif);
		editionC.add(editionLigne3);
		
		JPanel editionLigne4 = new JPanel();
		editionLigne4.setLayout(new GridLayout(1,2));
		editionLigne4.add(new JLabel("Pays de prise de vue :"));
		paysEd = new JTextField();
		String paysS = "Entrez un nouveau Pays";
		paysEd.setText(paysS);
		editionLigne4.add(paysEd);
		editionC.add(editionLigne4);
		
		JPanel editionLigne5 = new JPanel();
		editionLigne5.setLayout(new GridLayout(1,4,5,5));
		editionLigne5.add(new JLabel("Nouvelle date :"));
		comboJourModif = new JComboBox<String>();
		comboJourModif.addItem("-");
		for(int i=1;i<=31;i++){
			comboJourModif.addItem(""+i+"");
		}
		editionLigne5.add(comboJourModif);
		comboMoisModif = new JComboBox<String>();
		comboMoisModif.addItem("-");
		comboMoisModif.addItem("janvier");
		comboMoisModif.addItem("fevrier");
		comboMoisModif.addItem("mars");
		comboMoisModif.addItem("avril");
		comboMoisModif.addItem("mai");
		comboMoisModif.addItem("juin");
		comboMoisModif.addItem("juillet");
		comboMoisModif.addItem("aout");
		comboMoisModif.addItem("septembre");
		comboMoisModif.addItem("octobre");
		comboMoisModif.addItem("novembre");
		comboMoisModif.addItem("decembre");
		editionLigne5.add(comboMoisModif);
		comboAnneeModif = new JComboBox<String>();
		comboAnneeModif.addItem("-");
		for(int i=1990;i<=2015;i++){
			comboAnneeModif.addItem(""+i+"");
		}
		editionLigne5.add(comboAnneeModif);
		editionC.add(editionLigne5);
		
		motsEd = new JTextField("Nouveaux mots cles de la photo (separes par des ; ).");
		editionC.add(motsEd);
		
		
		JPanel editionLigne9 = new JPanel();
		editionLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back de Modification");
		editionLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		editionLigne9.add(accueil);
		JButton valmodif = new JButton("Valider");
		valmodif.addActionListener(ctrl);
		valmodif.setActionCommand("Valider Modification");
		editionLigne9.add(valmodif);
		edition.add(editionLigne9,BorderLayout.SOUTH);
		
		edition.add(editionC,BorderLayout.CENTER);
		
		
		panel.add(edition, BorderLayout.CENTER);
	}
	
	
	public void afficherNouvelleImage(){
		panel.removeAll();
		JPanel edition = new JPanel();
		edition.setLayout(new BorderLayout(10,10));
		
		JLabel titre = new JLabel(this.engine.getTitreNouvelleImage());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setFont(new Font("Courrier",5,20));
		titre.setForeground(Color.ORANGE);
		edition.add(titre,BorderLayout.NORTH);		
		JPanel editionC = new JPanel();
		editionC.setLayout(new GridLayout(6,1,10,10));
		JPanel panfile = new JPanel();
		panfile.setLayout(new GridLayout(1,2));
		textURL = new JTextField("URL de votre Photo");
		boutonURL = new JButton("Chercher une Photo");
		boutonURL.addActionListener(ctrl);
		boutonURL.setActionCommand("Chercher Photo");
		panfile.add(textURL);
		panfile.add(boutonURL);
		editionC.add(panfile);
		JPanel editionLigne2 = new JPanel();
		editionLigne2.setLayout(new GridLayout(1,2));
		JPanel editionLigne2Pan1 = new JPanel();
		editionLigne2Pan1.setLayout(new GridLayout(2,1));
		editionLigne2Pan1.add(new JLabel("Titre :"));
		editionLigne2Pan1.add(new JLabel("Auteur :"));
		editionLigne2.add(editionLigne2Pan1);
		JPanel editionLigne2Pan2 = new JPanel();
		editionLigne2Pan2.setLayout(new GridLayout(2,1));
		titrenew = new JTextField();
		String titreS = "Entrez un titre";
		titrenew.setText(titreS);
		editionLigne2Pan2.add(titrenew);
		auteurnew = new JTextField();
		String auteurS = "Entrez un auteur";
		auteurnew.setText(auteurS);
		editionLigne2Pan2.add(auteurnew);
		editionLigne2.add(editionLigne2Pan2);
		editionC.add(editionLigne2);
		
		JPanel editionLigne3 = new JPanel();
		editionLigne3.setLayout(new GridLayout(1,2));
		editionLigne3.add(new JLabel("Categorie :"));
		comboCatnew = new JComboBox<String>();
		Enumeration<Collection> enumeration = this.engine.getUtilisateurSelect().toutesCollections();
		while(enumeration.hasMoreElements()){
			Collection p = enumeration.nextElement();
			comboCatnew.addItem(p.getTitre());
		}
		editionLigne3.add(comboCatnew);
		editionC.add(editionLigne3);
		
		JPanel editionLigne4 = new JPanel();
		editionLigne4.setLayout(new GridLayout(1,2));
		editionLigne4.add(new JLabel("Pays de prise de vue :"));
		paysnew = new JTextField();
		String paysS = "Entrez un nouveau Pays";
		paysnew.setText(paysS);
		editionLigne4.add(paysnew);
		editionC.add(editionLigne4);
		
		JPanel editionLigne5 = new JPanel();
		editionLigne5.setLayout(new GridLayout(1,4,5,5));
		editionLigne5.add(new JLabel("Selectionnez une date :"));
		comboJournew = new JComboBox<String>();
		for(int i=1;i<=31;i++){
			comboJournew.addItem(""+i+"");
		}
		editionLigne5.add(comboJournew);
		comboMoisnew = new JComboBox<String>();
		comboMoisnew.addItem("janvier");
		comboMoisnew.addItem("fevrier");
		comboMoisnew.addItem("mars");
		comboMoisnew.addItem("avril");
		comboMoisnew.addItem("mai");
		comboMoisnew.addItem("juin");
		comboMoisnew.addItem("juillet");
		comboMoisnew.addItem("aout");
		comboMoisnew.addItem("septembre");
		comboMoisnew.addItem("octobre");
		comboMoisnew.addItem("novembre");
		comboMoisnew.addItem("decembre");
		editionLigne5.add(comboMoisnew);
		comboAnneenew = new JComboBox<String>();
		for(int i=1990;i<=2015;i++){
			comboAnneenew.addItem(""+i+"");
		}
		editionLigne5.add(comboAnneenew);
		editionC.add(editionLigne5);
		
		motsnew = new JTextField("Les mots cles de la photo (separes par des ; ).");
		editionC.add(motsnew);
		
		
		JPanel editionLigne9 = new JPanel();
		editionLigne9.setLayout(new GridLayout(1,3,10,10));
		JButton butback = new JButton("Back");
		butback.addActionListener(ctrl);
		butback.setActionCommand("Back de Gestion");
		editionLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		editionLigne9.add(accueil);
		JButton valmodif = new JButton("Valider");
		valmodif.addActionListener(ctrl);
		valmodif.setActionCommand("Valider Ajout");
		editionLigne9.add(valmodif);
		edition.add(editionLigne9,BorderLayout.SOUTH);
		
		edition.add(editionC,BorderLayout.CENTER);
		
		
		panel.add(edition, BorderLayout.CENTER);
	}
	
	
	
	
	
	public JComboBox<String> getComboCatnew() {
		return comboCatnew;
	}




	public JTextField getTitrenew() {
		return titrenew;
	}




	public JTextComponent getAuteurnew() {
		return auteurnew;
	}




	public JTextField getMotsnew() {
		return motsnew;
	}




	public JComboBox<String> getComboMoisnew() {
		return comboMoisnew;
	}




	public JComboBox<String> getComboJournew() {
		return comboJournew;
	}




	public JComboBox<String> getComboAnneenew() {
		return comboAnneenew;
	}




	public JTextField getPaysnew() {
		return paysnew;
	}




	public JTextField getTextURL() {
		return textURL;
	}




	public JButton getBoutonURL() {
		return boutonURL;
	}




	public JComboBox<String> getComboMoisModif() {
		return comboMoisModif;
	}




	public JComboBox<String> getComboJourModif() {
		return comboJourModif;
	}




	public JComboBox<String> getComboAnneeModif() {
		return comboAnneeModif;
	}




	public JTextField getTitreEd() {
		return titreEd;
	}




	public JTextField getAuteurEd() {
		return auteurEd;
	}




	public JComboBox<String> getComboCatModif() {
		return comboCatModif;
	}




	public JTextField getPaysEd() {
		return paysEd;
	}




	public JTextField getMotsEd() {
		return motsEd;
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
		butback.setActionCommand("Back de Edition");
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
		
		
		
		panel.add(diaporama,BorderLayout.CENTER);
	}
	
	/**
	 * Methode publique permettant de faire tourner un diaporama
	 */
	public void lireDiaporama(){
		this.lecture = true;
		System.out.println("4");
		while(this.playpause.getText().equals("Play")){
			System.out.println("5");
			try {
				System.out.println("6");
				Thread.sleep(3000);
				this.engine.getUtilisateurSelect().getAllPhotos().nextPhoto();
				
				this.afficherDiaporama(this.engine.getUtilisateurSelect().getAllPhotos().getPhotoSelect());
				System.out.println("7");
				
				System.out.println("8");
			} catch (NoPhotoFoundException | InterruptedException e) {
				e.printStackTrace();
				System.out.println("erreur");
			}
		}
		this.lecture = false;
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
		butback.setActionCommand("Back de Options");
		aideLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		aideLigne9.add(accueil);
		aideLigne9.add(new JPanel());
		aide.add(aideLigne9,BorderLayout.SOUTH);
		
		aide.add(aide2,BorderLayout.CENTER);
		
		
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
		butback.setActionCommand("Back de Options");
		aProposLigne9.add(butback);
		JButton accueil = new JButton("Accueil");
		accueil.addActionListener(ctrl);
		accueil.setActionCommand("Bouton Accueil");
		aProposLigne9.add(accueil);
		aProposLigne9.add(new JPanel());
		aPropos.add(aProposLigne9,BorderLayout.SOUTH);
		
		aPropos.add(aProposC,BorderLayout.CENTER);
		
		
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
	
	private String dateToString(Photo p){
		String rep = "";
		java.util.GregorianCalendar d = p.getDate();
		java.util.Date date = d.getTime();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("EEEEEEEE dd MMMMMMMM YYYY");
		rep = format.format(date);
		rep = Character.toUpperCase(rep.charAt(0))+rep.substring(1);
		return rep;
	}
} // ------------------------------------------------------------- Class PhotoTechView

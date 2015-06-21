/* 
 *  PDA project -- UBS/IUT de Vannes -- Dept Informatique
 *
 *  $Id$
 *
 *  Copyright 2007-08 © IUT de Vannes
 *  Ce logiciel pédagogique est diffusé sous licence GPL
 */
package control;

import view.PhotoTechView;

import javax.imageio.ImageIO;
import javax.swing.*;

import datas.Collection;
import datas.NoPhotoFoundException;
import datas.Photo;
import datas.PhotoTechDatas;
import datas.Selection;
import datas.User;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.GregorianCalendar;

/**
 *  The simplest application in the PDA.
 *
 *  It can be used to construct other applications.
 *
 *  @author F. Merciol, D. Deveaux MAJ J-F. Kamp
 *                      <{francois.merciol|daniel.deveaux}@univ-ubs.fr>
 *  @version $Revision: 27 $
 */
public class PhotoTechCtrl implements IApplication, ActionListener {

	/*
	 * Private implementation -------------------------------------------------
	 */

	/** the name of the application */
	private String name;

	/** the view of the application */
	private PhotoTechView view;

	/** the engine of the application */
	private PhotoTechDatas engine;

	/*
	 *  Public ressources -----------------------------------------------------
	 *
	 *  Constructors
	 */

	/**
	 * Initialize the datas and ihm of PhotoTech application.
	 */
	public PhotoTechCtrl () {

		engine = new PhotoTechDatas();
		view = new PhotoTechView (this, engine );
	} // --------------------------------------------------------- PhotoTechCtrl()

	/*
	 *  Public methods
	 */

	/*
	 * See documentation of interface
	 */
	public void start ( PdaCtrl pda ) {
		System.out.println ( "Start of PhotoTech application" );
	} // -------------------------------------------------------------- start()

	/*
	 * See documentation of interface
	 */
	public String getAppliName() {
		return name;
	} // ---------------------------------------------------------- getAppliName()

	/*
	 * See documentation of interface
	 */
	public JPanel getAppliPanel() {
		return view.getPanel();
	} // ---------------------------------------------------------- getAppliPanel()

	/*
	 * See documentation of interface
	 */
	public boolean close() {
		return true;
	} // ---------------------------------------------------------- close()

	/*
	 * See documentation of interface
	 */
	public void setAppliName ( String theName ) {
		this.name = theName;
	} // ---------------------------------------------------------- setAppliName()

	/**
	 * Callback method, reaction to button pushed
	 * @param e the captured event
	 */
	public void actionPerformed ( ActionEvent e ) {

		// Events generated by PhotoTech application


		JButton source =new JButton();
		if(e.getSource().getClass() == source.getClass()){
			source = (JButton)e.getSource();
		}

		if(source.getActionCommand() == "Bouton Galerie"){
			this.view.afficherGalerie(this.engine.getUtilisateurSelect().getAllPhotos());
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Recherche"){
			this.view.afficherRecherche();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Options"){
			this.view.afficherOptions();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Gestion"){
			this.view.afficherGestion();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Edition"){
			this.view.afficherEdition();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Diaporama"){
			try {
				this.view.afficherDiaporama(this.engine.getCollectionSelect().getPhotoSelect());
			} catch (NoPhotoFoundException e1) {}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Accueil"){
			this.view.afficherMenu();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Faire Recherche"){
			// -- Titre --
			String[] titre;
			if(this.view.getTexteTitre().getText().trim().equals("")==false && this.view.getTexteTitre() != null){
				titre = this.view.getTexteTitre().getText().split(";");
			} else titre = null;
			
			// -- Categorie --
			String categorie;
			if(this.view.getComboCat().getSelectedItem().equals("-")){
				categorie =null;
			} else categorie = this.view.getComboCat().getItemAt(this.view.getComboCat().getSelectedIndex());
			
			// -- Auteur --
			String[] auteur;
			if(this.view.getTexteAuteur().getText().trim().equals("") == false && this.view.getTexteAuteur() != null ){
				auteur = this.view.getTexteAuteur().getText().split(";");
			} else auteur = null;
			
			// -- Dates --
			int jour1 = -1;
			if(this.view.getComboJour().getItemAt(this.view.getComboJour().getSelectedIndex()).equals("-") == false){
				jour1 = Integer.parseInt(this.view.getComboJour().getItemAt(this.view.getComboJour().getSelectedIndex()));
			}
			int jour2 = -1;
			if(this.view.getComboJour2().getItemAt(this.view.getComboJour2().getSelectedIndex()).equals("-") == false){
				jour2 = Integer.parseInt(this.view.getComboJour2().getItemAt(this.view.getComboJour2().getSelectedIndex()));
			}
			int mois1 = this.view.getComboMois().getSelectedIndex() - 1;
			int mois2 = this.view.getComboMois2().getSelectedIndex() -1;
			int annee1 = -1;
			if(this.view.getComboAnnee().getItemAt(this.view.getComboAnnee().getSelectedIndex()).equals("-") == false){
				annee1 = Integer.parseInt(this.view.getComboAnnee().getItemAt(this.view.getComboAnnee().getSelectedIndex()));
			}
			int annee2 = -1;
			if(this.view.getComboAnnee2().getItemAt(this.view.getComboAnnee2().getSelectedIndex()).equals("-") == false){
				annee2 = Integer.parseInt(this.view.getComboAnnee2().getItemAt(this.view.getComboAnnee2().getSelectedIndex()));
			}
			
			// -- Pays --
			String[] localisation;
			if(this.view.getTextePays().getText().trim().equals("")==false && this.view.getTextePays() != null){
				localisation = this.view.getTextePays().getText().split(";");
			}
			else{
				localisation = null;
			}
			
			// -- KeyWords -- 
			String[] motsCles;
			if(this.view.getTexteMots().getText().trim().equals("Entrez ici vos mots cles (separes par des ; ).")== false && this.view.getTexteMots() != null
					&& !this.view.getTexteMots().getText().trim().equals("")){
				motsCles = this.view.getTexteMots().getText().split(";");
			} else motsCles = new String[0];
			
			
			Selection laSelection;

			if(categorie != null){
				laSelection = new Selection(this.engine.getUtilisateurSelect().getCollection(categorie));
			}
			else{
				laSelection = new Selection(this.engine.getUtilisateurSelect().getAllPhotos());
			}

			laSelection.setAuteur(auteur);
			laSelection.setKeyWords(motsCles);
			laSelection.setPays(localisation);
			laSelection.setTitre(titre);
			if(annee1 !=-1 && jour1 !=-1 && mois1 !=-1){
				laSelection.setDateDebut(new GregorianCalendar(annee1,mois1,jour1));
				
			}
			else{
				laSelection.setDateDebut(null);
				
			}
			if(annee1 !=-1 && jour1 !=-1 && mois1 !=-1){
				laSelection.setDateFin(new GregorianCalendar(annee2,mois2,jour2));
				
			}
			else{
				laSelection.setDateFin(null);
				
			}
			Collection resultat = laSelection.rechercher();
			if(resultat != null){
				this.engine.setCollectionSelect(resultat);
				this.view.afficherGalerie(resultat);
			} else {
				System.out.println("rien trouve !");
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Back de Galerie"){
			if(this.engine.getCollectionSelect() != this.engine.getUtilisateurSelect().getAllPhotos()){
				this.view.afficherRecherche();
			}
			else{
				this.view.afficherMenu();
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Back de Gestion"){
			this.view.afficherGestion();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Back de Edition"){
			this.view.afficherGalerie(this.engine.getCollectionSelect());
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Back de Modification"){
			this.view.afficherEdition();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Back de Options"){
			this.view.afficherOptions();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Back de Diapo"){
			Photo laPhoto;
			try {
				laPhoto = this.engine.getCollectionSelect().getPhotoSelect();
			} catch (NoPhotoFoundException e1) {
				laPhoto = null;
				e1.printStackTrace();
			}
			this.view.afficherDiaporama(laPhoto);
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "OK Tri"){
			String tri = this.view.getComboGalerie().getItemAt(this.view.getComboGalerie().getSelectedIndex());

			if (tri.equals("Categorie Alphabetique")){
				this.engine.getCollectionSelect().setTriCollectionAlpha();
				this.view.afficherGalerie(this.engine.getCollectionSelect());
			}
			else if(tri.equals("Categorie Non Alphabetique")){
				this.engine.getCollectionSelect().setTriCollectionAntiAlpha();
				this.view.afficherGalerie(this.engine.getCollectionSelect());
			}
			else if(tri.equals("Auteur Alphabetique")){

				this.engine.getCollectionSelect().setTriAuteurAlpha();
				this.view.afficherGalerie(this.engine.getCollectionSelect());


			}
			else if(tri.equals("Auteur Non Alphabetique")){

				this.engine.getCollectionSelect().setTriAuteurAntiAlpha();
				this.view.afficherGalerie(this.engine.getCollectionSelect());


			}
			else if(tri.equals("Date Croissante")){

				this.engine.getCollectionSelect().setTriDateCroissante();
				this.view.afficherGalerie(this.engine.getCollectionSelect());


			}
			else if(tri.equals("Date Decroissante")){

				this.engine.getCollectionSelect().setTriDateDecroissante();
				this.view.afficherGalerie(this.engine.getCollectionSelect());


			}
			else if(tri.equals("Pays Alphabetique")){

				this.engine.getCollectionSelect().setTriPaysAlpha();
				this.view.afficherGalerie(this.engine.getCollectionSelect());


			}
			else if(tri.equals("Pays Non Alphabetique")){

				this.engine.getCollectionSelect().setTriPaysAntiAlpha();
				this.view.afficherGalerie(this.engine.getCollectionSelect());


			}
			else if(tri.equals("Titre Alphabetique")){

				this.engine.getCollectionSelect().setTriTitreAlpha();
				this.view.afficherGalerie(this.engine.getCollectionSelect());


			}
			else if(tri.equals("Titre Non Alphabetique")){

				this.engine.getCollectionSelect().setTriTitreAntiAlpha();
				this.view.afficherGalerie(this.engine.getCollectionSelect());


			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Changer Theme"){
			this.engine.setTheme(this.view.getComboTheme().getItemAt(this.view.getComboTheme().getSelectedIndex()));
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Defaut Theme"){
			this.engine.setTheme("Defaut");
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Aide"){
			this.view.afficherAide();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "A Propos"){
			this.view.afficherAPropos();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Ajouter Photo"){
			this.view.afficherNouvelleImage();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Ajouter Categorie"){
			if(!this.view.getTextCatEd().getText().equals("Nom de la categorie") && this.view.getTextCatEd() != null && !this.view.getTextCatEd().getText().equals("Entrez un nom svp !")){
				this.engine.getUtilisateurSelect().addCollection(this.view.getTextCatEd().getText());
				this.view.getTextCatEd().setText("");
				this.view.afficherGestion();
			}
			else{
				this.view.getTextCatEd().setText("Entrez un nom svp !");
			}

		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Supprimer Categorie"){
			this.engine.getUtilisateurSelect().delCollection(this.view.getCatG().getSelectedItem().toString());
			this.view.afficherGestion();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand().substring(0, 5).equals("Image")){
			try {
				this.engine.getCollectionSelect().setIndexSelect(source.getActionCommand().substring(5));
			} catch (NoPhotoFoundException e2) {
				e2.printStackTrace();
			}
			try {

				this.view.afficherDiaporama(this.engine.getCollectionSelect().getPhotoSelect());


			} catch (NoPhotoFoundException e1) {
				e1.printStackTrace();
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Previous"){
			try {

				this.engine.getCollectionSelect().prevPhoto();
				this.view.afficherDiaporama(this.engine.getCollectionSelect().getPhotoSelect());


			} catch (NoPhotoFoundException e1) {
				e1.printStackTrace();
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Next"){
			try {

				this.engine.getCollectionSelect().nextPhoto();
				this.view.afficherDiaporama(this.engine.getCollectionSelect().getPhotoSelect());

			} catch (NoPhotoFoundException e1) {
				e1.printStackTrace();
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Play Pause"){
			if(this.view.getPlaypause().getText().equals("Pause")){
				this.view.getPlaypause().setText("Play");
				this.view.lireDiaporama();
			}
			else{
				this.view.getPlaypause().setText("Pause");
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Ajouter Utilisateur"){
			this.view.afficherNouveauUtilisateur();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Supprimer Utilisateur"){
			String nomUser = this.view.getUser().getText();
			try{
				String pathFile = "saves/"+nomUser+".out";
				this.engine.setUtilisateurSelect(User.charger(pathFile));
				if(this.view.getMdp().getText().equals(this.engine.getUtilisateurSelect().getPassword())){
					this.engine.getUtilisateurSelect().delUser();
					this.view.getLabelC().setText("Utilisateur supprime !");
				}
				else{
					this.view.getLabelC().setText("Mot de passe incorrect");
					this.view.getMdp().setText("");
				}
				
			}
			catch(java.lang.Exception p){
				this.view.getLabelC().setText("Cet utilisateur n'existe pas");
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Similarite"){
			this.engine.getCollectionSelect().setTriSimilarite(this.engine.getCollectionSelect().getIndexSelect());
			this.view.afficherGalerie(this.engine.getCollectionSelect());
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Modifier"){
			this.view.afficherEditionModification();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Nouvelle Categorie"){
			System.out.println("NEW CATEGORIE");
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Valider Modification"){
			Photo laPhoto;
			try {
				laPhoto = this.engine.getCollectionSelect().getPhotoSelect();
			} catch (NoPhotoFoundException e1) {
				laPhoto = null;
				e1.printStackTrace();
			}
			if(this.view.getTitreEd().getText() != null && this.view.getTitreEd().getText() != "" && this.view.getTitreEd().getText().equals("Entrez un nouveau titre")==false){
				laPhoto.setTitre(this.view.getTitreEd().getText());
			}
			if(this.view.getAuteurEd().getText() != null && this.view.getAuteurEd().getText() != "" && !this.view.getAuteurEd().getText().equals("Entrez un nouvel auteur")){
				laPhoto.setAuteur(this.view.getAuteurEd().getText());
			}
			
			if(this.view.getComboCatModif().getSelectedItem() != "-"){
				laPhoto.setCollection((String)this.view.getComboCatModif().getSelectedItem());
			}
			if(this.view.getPaysEd().getText() != null && this.view.getPaysEd().getText() != "" && !this.view.getPaysEd().getText().equals("Entrez un nouveau Pays")){
				laPhoto.setPays(this.view.getPaysEd().getText());
			}
			if((this.view.getComboAnneeModif().getSelectedItem().equals("-")) == false  && (this.view.getComboMoisModif().getSelectedItem().equals("-")) == false  && (this.view.getComboJourModif().getSelectedItem().equals("-")) == false  ){
				int annee = this.view.getComboAnneeModif().getSelectedIndex()+1989;
				int mois = this.view.getComboMoisModif().getSelectedIndex()-1;
				int jour = this.view.getComboJourModif().getSelectedIndex();
				laPhoto.setDate(new GregorianCalendar(annee,mois,jour));
			}
			if(this.view.getMotsEd().getText() != null && this.view.getMotsEd().getText() != "" && !this.view.getMotsEd().getText().equals("Nouveaux mots cles de la photo (separes par des ; ).")){
				laPhoto.setKeyWords(this.view.getMotsEd().getText().split(";"));
			}
			this.engine.getUtilisateurSelect().sauver();
			this.view.afficherEdition();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Sans User"){
			
			try {
				this.engine.setUtilisateurSelect(User.charger("saves/Defaut.out"));
				this.view.afficherMenu();
			} catch (ClassNotFoundException | IOException e1) {
				this.view.getLabelC().setText("Fichier corrompu, creez un nouvel utilisateur");
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Connexion"){
			String nomUser = this.view.getUser().getText();
			try{
				String pathFile = "saves/"+nomUser+".out";
				this.engine.setUtilisateurSelect(User.charger(pathFile));
				if(this.view.getMdp().getText().equals(this.engine.getUtilisateurSelect().getPassword())){
					this.view.afficherMenu();
				}
				else{
					this.view.getLabelC().setText("Mot de passe incorrect");
					this.view.getMdp().setText("");
				}
				
			}
			catch(java.lang.Exception p){
				this.view.getLabelC().setText("Cet utilisateur n'existe pas");
			}

		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Retour Connexion"){
			this.engine.getUtilisateurSelect().sauver();
			this.view.getUser().setText("Entrez votre nom");
			this.view.afficherConnexion();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Sauver"){
			this.engine.getUtilisateurSelect().sauver();
			this.view.afficherMenu();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Supprimer Photo"){
			Photo laPhoto;
			try {
				laPhoto = this.engine.getCollectionSelect().getPhotoSelect();
			} catch (NoPhotoFoundException e1) {
				laPhoto = null;
				e1.printStackTrace();
			}
			this.engine.getUtilisateurSelect().delPhoto(laPhoto.getNomFichier());
			this.engine.getUtilisateurSelect().sauver();
			this.view.afficherGalerie(this.engine.getCollectionSelect());
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Chercher Photo"){
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Selectionnez une photo");
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				this.view.getTextURL().setText(chooser.getSelectedFile().getAbsolutePath().toString());
			}


		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Valider Ajout"){
			String titre = this.view.getTitrenew().getText();
			if(titre.equals("Entrez un titre")) titre = "";
			String auteur = this.view.getAuteurnew().getText();
			if(auteur.equals("Entrez un auteur")) auteur="";
			String collection = "All";
			if(this.view.getComboCatnew().getSelectedItem() != null){
				collection = this.view.getComboCatnew().getSelectedItem().toString();
			}
			String pays = this.view.getPaysnew().getText();
			if(pays.equals("Entrez un nouveau Pays")) pays="";
			int annee = this.view.getComboAnneenew().getSelectedIndex()+1990;
			int mois = this.view.getComboMoisnew().getSelectedIndex();
			int jour = this.view.getComboMoisnew().getSelectedIndex()+3;
			String[] keyWords = this.view.getMotsnew().getText().split(";");
			if(keyWords[0].equals("Les mots cles de la photo (separes par des ")) keyWords=new String[0];
			String nom = this.replaceAllString(view.getTextURL().getText(), "\\", "/");
			if(titre!=null && auteur!=null && pays!=null && keyWords!=null && nom!=null && !nom.equals("")){
				String nomFinal = this.engine.getUtilisateurSelect().importerPhoto(nom);
				try {
					Photo laPhoto = this.engine.getUtilisateurSelect().getAllPhotos().getPhoto(nomFinal);
					laPhoto.setTitre(titre);
					laPhoto.setAuteur(auteur);
					laPhoto.setCollection(collection);
					laPhoto.setPays(pays);
					laPhoto.setDate(new GregorianCalendar(annee,mois,jour));
					laPhoto.setKeyWords(keyWords);
				} catch (NoPhotoFoundException e1) {
					e1.printStackTrace();
				}
				this.engine.getUtilisateurSelect().sauver();
				this.view.afficherGestion();
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Verification"){
			boolean check = true;
			Enumeration<Photo> lesphotos = this.engine.getUtilisateurSelect().getAllPhotos().toutesPhotos();
			this.view.afficherCheck();
			this.view.setTextCheck("");
			while(lesphotos.hasMoreElements()){
				Photo laPhoto = lesphotos.nextElement();
				if(laPhoto.checkTag() == false){
					check = false;
					String tmp = this.view.getTextCheck().getText();
					this.view.setTextCheck("<html><body>"+tmp+"La photo "+laPhoto.getTitre()+" est corrompue ! <br></body></html>");
				}
			}
			if(check == true){
				this.view.getTextCheck().setText("Toutes les photos sont authentiques !");
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Back Connexion"){
			this.view.afficherConnexion();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Valider Creation User"){
			if(!this.view.getTextnewU().getText().equals("") && this.view.getTextnewU().getText() != null ){
				if(this.view.getTextnewMdp().getText() != null && !this.view.getTextnewMdp().getText().equals("")){
					User nouveau = new User(this.view.getTextnewU().getText());
				nouveau.setPassword(this.view.getTextnewMdp().getText());
				nouveau.sauver();
				this.view.afficherConnexion();
				}
				else{
					this.view.getLabelM().setText("Entrez un mot de passe svp");
				}
			}
			else{
				this.view.getLabelM().setText("Entrez un nom svp !");
			}

		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Changer de Mot de Passe"){
			this.view.afficherNouveauMDP();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Valider MDP"){
			String mdp = new String(this.view.getUser2().getPassword());
			if(mdp.equals(this.engine.getUtilisateurSelect().getPassword())){
				if(mdp != null && !mdp.equals("Entrez votre nouveau mot de passe") && !mdp.equals("")){
					this.engine.getUtilisateurSelect().setPassword(mdp);
					this.engine.getUtilisateurSelect().sauver();
					this.view.afficherOptions();
				}
				else{
					this.view.getLabelMDP().setText("Entrez un mot de passe svp !");
				}

			}
			else{
				this.view.getLabelMDP().setText("Mot de passe incorrect");
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Bouton Watermark"){
			this.view.afficherWatermark();
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Valider Water"){
			String url = this.view.getUrlWater().getText();
			File fUrl = new File(url);
			String textWater = this.view.getWater().getText();
			if(url == null || url.equals("")){
				this.view.getTextWater().setText("Selectionnez un fichier");
			} else if (textWater == null || textWater.equals("")){
				this.view.getTextWater().setText("Entrez une watermark");
			} else if (fUrl.exists() && !fUrl.canWrite()){
				this.view.getTextWater().setText("Fichier non accessible");
			} else {
				try {
					Photo p = this.engine.getCollectionSelect().getPhotoSelect();
					ImageIO.write(p.waterMark(textWater), p.getExtension(), fUrl);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (NoPhotoFoundException e1) {
					e1.printStackTrace();
				}
				this.view.afficherEdition();
			}
		}
		//-------------------------------------------------------------
		else if(source.getActionCommand() == "Parcourir"){
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Selectionnez un dossier");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setApproveButtonText("Valider");
		    File f = null;
			try {
				f = new File(new File(this.engine.getCollectionSelect().getPhotoSelect().getNomFichier()).getCanonicalPath());
			} catch (IOException | NoPhotoFoundException e1) {
				e1.printStackTrace();
			}
		    chooser.setSelectedFile(f);
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				this.view.getUrlWater().setText(chooser.getSelectedFile().getAbsolutePath().toString());
			}
		}

	} // ---------------------------------------------------------- actionPerformed()

	public String replaceAllString(String strOrig, String strFind, String strReplace) {
		if(strOrig == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer(strOrig);
		String toReplace = "";

		if (strReplace == null) toReplace = "";
		else toReplace = strReplace;

		int pos = strOrig.length();

		while (pos > -1) {
			pos = strOrig.lastIndexOf(strFind, pos);
			if (pos > -1) sb.replace(pos, pos+strFind.length(), toReplace);
			pos = pos - strFind.length();
		}

		return sb.toString();
	}


} // ---------------------------------------------------------- Class PhotoTechCtrl

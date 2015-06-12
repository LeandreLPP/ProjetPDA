package datas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

public class Photo implements Serializable {
	private static final long serialVersionUID = 0;
	private String imageURL;
	private String titre;
	private String auteur;
	private String pays;
	private Calendar date;
	private ArrayList<String> keyWords;
	private int[] tag;
	private BufferedImage img;
	/* On ignore ces variables pour le moment
	private long gpsLatitude;
	private long gpsLongitude;*/

	/**
	 * Constructeur sans parametre de la classe photo
	 * Les attributs sont modifies par la suite par les getteurs/setteurs
	 */
	public Photo(String urlOrigine, String urlDestination){
		this.imageURL = urlDestination;
		this.titre = this.imageURL.split("/")[this.imageURL.split("/").length-1];
		this.CopierFichier(urlOrigine, urlDestination);
		this.auteur = null;
		this.pays = null;
		this.date = Calendar.getInstance();
		this.keyWords = new ArrayList<String>();
		try {
			this.img = ImageIO.read(new File(this.imageURL));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.tagInvisible();
		this.generateTag();
	}
	
	private boolean CopierFichier(String source, String destination){
		boolean resultat=false;
		FileInputStream filesource=null;
		FileOutputStream fileDestination=null;
		File creation = new File(destination);
		try{
			creation.createNewFile();
		} catch(IOException e) {
			System.out.println("Impossible de creer le fichier");
			e.printStackTrace();
		}
		try{
			filesource=new FileInputStream(source);
			fileDestination=new FileOutputStream(creation);
			byte buffer[]=new byte[512*1024];
			int nblecture;
			while((nblecture=filesource.read(buffer))!=-1){
				fileDestination.write(buffer,0,nblecture);
			}
			resultat=true;
		}catch(FileNotFoundException nf){
			nf.printStackTrace();
		}catch(IOException io){
			io.printStackTrace();
		}finally{
			try{
				filesource.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				fileDestination.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		} 
		return resultat;
	}

	/**
	 * Genere le tag de securite de la photo
	 */
	private void generateTag(){
		int width = this.img.getWidth();
		int height = this.img.getHeight();
		int p1 = this.img.getRGB(0, 0);
		int p2 = this.img.getRGB(width, height);
		this.tag = new int[]{width,height,p1,p2};
	}


	/**
	 * Verifie que le tag de securite correspond bien a la photo de l'url 
	 * @return True si la photo correspond au tag, false sinon.
	 * @throws IOException 
	 */
	public boolean checkTag(){
		boolean ret = true;
		BufferedImage imgTest;
		try {
			imgTest = ImageIO.read(new File(this.imageURL));
			int width = imgTest.getWidth();
			int height = imgTest.getHeight();
			int p1 = imgTest.getRGB(0, 0);
			int p2 = imgTest.getRGB(width, height);
			int[] tagTest = new int[]{width,height,p1,p2};
			for(int i = 0; i<4;i++){
				if(this.tag[i] != tagTest[i]){
					ret = false;
				}
			}
		} catch (IOException e) {
			ret = false;
		}
		return ret;
	}

	/*
	 * Copie l'etat d'une photo dans un nouvel objet photo, excepte l'url qui est passe en parametre
	 * @param url L'url de la nouvelle photo
	 * @return Une nouvelle photo, aux attributs identiques a celle-ci.
	 
	public Photo copier(String url){
		//TODO
		Photo ret = null;
		return ret;
	}*/

	/**
	 * Genere la courbe de couleur sous la forme d'un tableau de 765 entiers entre 0 et 100.
	 * Un entier pour chacune des 255 nuances de rouge, vert et bleu. 
	 * Si l'entier vaut 0, cela veut dire que aucun pixel de l'image ne possede cette nuance de couleur.
	 * Si l'entier vaut 100, cela veut dire que tous les pixels de l'image possedent cette nuance de couleur.
	 * Un pixel etant code un code RGB (Red Green Blue), chacun possede trois nuances : une rouge, une verte et unee bleue, codee chacune sur 255 
	 * @return Un tableau de 765 entiers allant de 0 a 100.
	 */
	public int[] courbeCouleur(){
		int[] ret = new int[765];
		int width = this.img.getWidth();
		int height = this.img.getHeight();
		int nbPixels = width*height;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Color rgb = new Color(this.img.getRGB(row, col));
				int r = rgb.getRed();
				int g = rgb.getGreen() + 255;
				int b = rgb.getBlue() + 510;
				ret[r]++;
				ret[g]++;
				ret[b]++;
			}
		}
		for (int e : ret){
			e = ((int)e/nbPixels)*100;
		}
		return ret;
	}

	/**
	 * Compare les courbes de couleur des deux photographies.
	 * @param p2 la photo a comparer
	 * @return Le quotient de difference entre 0 et 100. 
	 * 0 signifie que les courbes de couleurs sont identiques, 100 signifie que les courbes de couleurs sont totalement differentes.
	 */
	public int differenceCouleur(Photo p2){
		int[] tab1 = this.courbeCouleur();
		int[] tab2 = p2.courbeCouleur();
		int ret = 0;
		for(int i = 0; i<tab1.length; i++){
			ret += Math.abs(tab1[i]-tab2[i]);			
		}
		return ret;
	}
	
	/**
	 * 
	 */
	private void tagInvisible(){
		int alpha = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		int width = this.img.getWidth();
		int height = this.img.getHeight();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Color rgb = new Color(this.img.getRGB(col, row));
				alpha += rgb.getAlpha();
				r += rgb.getRed();
				g += rgb.getGreen();
				b += rgb.getBlue();
			}
		}
		alpha %= 255;
		r %= 255;
		g %= 255;
		b %= 255;
		Color tag = new Color(alpha,r,g,b);
		this.img.setRGB(width-1, height-1, tag.getRGB());
		String extension = this.getExtension();
		try {
			ImageIO.write(this.img, extension, ImageIO.createImageOutputStream(this.imageURL));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Compare cette photographie et verifie si elle est identiques (au pixel pres) a celle passee en parametre.
	 * @param p2 La photographie a comparer
	 * @return True si les deux photogrpahies sont strictement identiques (au pixel pres), false sinon.
	 */
	public boolean isIdentique(Photo p2){
		boolean ret = false;
		if(this.img.getHeight()==p2.img.getHeight() && this.img.getWidth()==p2.img.getWidth()){
			ret = true;
			int row = 0;
			int col = 0;
			while(ret && row<this.img.getHeight()){
				col = 0;
				while(ret && col<this.img.getWidth()){
					if(this.img.getRGB(row, col)!=p2.img.getRGB(row,col)) ret = false;
					col++;
				}
				row++;
			}
		}
		return ret;
	}

	/*
	 * Compare cette photographie et verifie si elle est identiques (au pixel pres) a celle passee en parametre.
	 * Contrairement a {@link #isIdentique(Photo)}, ignore les differences de format.
	 * La photo la plus grande est redimensionne en une de la meme taille que la plus petite
	 * @param p2 La photographie a comparer
	 * @return True si les deux photogrpahies sont strictement identiques (au pixel pres, une fois redimensionne), false sinon.
	 * @throws IOException 
	public boolean isIdentiqueIgnoreDimension(Photo p2) throws IOException{
		BufferedImage test = new BufferedImage();
		boolean ret = true;
		return ret;
	}
	 */

	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return this.imageURL;
	}
	/**
	 * @return the keyWords
	 */
	public ArrayList<String> getKeyWords() {
		return this.keyWords;
	}
	/**
	 * @return the titre
	 */
	public String getTitre() {
		return this.titre;
	}
	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return this.auteur;
	}
	/**
	 * @return the pays
	 */
	public String getPays() {
		return this.pays;
	}
	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return this.date;
	}
	
	/**
	 * @return the img
	 */
	public BufferedImage getImg(){
		return this.img;
	}
	
	/**
	 * @return the type of the image
	 */
	public String getExtension(){
		String ext = "";
		int i = this.imageURL.length()-1;
		char c = this.imageURL.charAt(i);
		while(c != '.'){
			ext = c + ext;
			i--;
		}
		return ext;
	}

	/*
	public long getGpsLatitude() {
		return this.gpsLatitude;
	}
	public long getGpsLongitude() {
		return this.gpsLongitude;
	}
	public void setGpsLatitude(long gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
	public void setGpsLongitude(long gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
	 */

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}
	/**
	 * @param keyWords the keyWords to set
	 */
	public void setKeyWords(ArrayList<String> keyWords) {
		this.keyWords = keyWords;
	}
	
	// --- Sauver et Charger ---
	public void sauver(){
		this.sauver(this.imageURL.split(".")[this.imageURL.split(".").length-2]+".out");
	}
	
	public void sauver(String url){
		FileOutputStream file;
		ObjectOutputStream flux;
		try {
			file = new FileOutputStream(url); 
			flux = new ObjectOutputStream(file);
			flux.writeObject(this);
			flux.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Photo charger(String url){
		FileInputStream file;
		ObjectInputStream flux;
		Photo ret = null;
		try {
			file = new FileInputStream(url);
			flux = new ObjectInputStream(file);
			ret = (Photo) flux.readObject();
			flux.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ret;
	}
}

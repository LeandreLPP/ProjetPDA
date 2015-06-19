package datas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

/**
 * Classe implementant les fonctionnalites pour la gestion et la manipulation des photograpies dans l'application.
 * Chaque instance de cette classe est liee a un fichier de type image (jpg, png ou gif) stocke sur le disque.
 * @author FRETAY Juliette et LE POLLES--POTIN Leandre - Groupe 1C
 */
public class Photo implements Serializable {
	private static final long serialVersionUID = 0;
	
	/**
	 * Le chemin d'enregistrement de la photographie a laquelle est lie cet objet {@link Photo}
	 * Defini l'emplacement de sauvegarde de {@link #img}
	 */
	private String imageURL;
	
	/**
	 * Le titre de cet objet photographie, initialise par defaut lors de la creation de l'objet au nom du fichier de la photographie sans son extension
	 * Par exemple la photographie enregistree sous le nom "Chat.jpg" sera nommee par defaut "Chat".
	 */
	private String titre;
	
	/**
	 * L'auteur de cette photographie, initialise par defaut a @null.
	 */
	private String auteur;
	
	/**
	 * Le pays de prise de vue de cette photographie, initialise par defaut a @null.
	 */
	private String pays;
	
	/**
	 * Le nom de la collection a laquelle appartient cette photographie.
	 */
	private String collection;
	
	/**
	 * La date de prise de vue de cette photographie.
	 */
	private GregorianCalendar date;
	
	/**
	 * Les mots cles associes a cette photographie.
	 */
	private String[] keyWords;
	
	/**
	 * Le tag permettant de verifier l'integrite de la photographie stockee en memoire.
	 * Ce tag est genere a l'instanciation de l'objet par la methode {@link #generateTag()}.
	 */
	private final int[] tag;
	
	/**
	 * L'objet de type BufferedImage utilise pour les manipulation de photographie.
	 * Cet objet "img" est sauvegarde a l'emplacement defini par {@link #imageURL}
	 */
	private transient BufferedImage img;

	/**
	 * Construit un objet de type Photo
	 * @param urlOrigine Chemin de l'image de type jpeg, png ou gif utilise pour creer l'objet photo
	 * @param urlDestination Chemin de sauvegarde de l'image importee
	 * @throws IOException En cas de probleme avec les fichier dont les chemins sont passes en parametre.
	 */
	public Photo(String urlOrigine, String urlDestination) throws IOException{
		this.imageURL = urlDestination;
		this.titre = this.imageURL.split("/")[this.imageURL.split("/").length-1];
		this.titre = this.titre.substring(0,this.titre.length()-(this.getExtension().length()+1));
		this.auteur = null;
		this.pays = null;
		this.collection = "All";
		this.date = new GregorianCalendar();
		this.keyWords = new String[0];
		this.CopierFichier(urlOrigine);
		this.tagInvisible();
		this.tag = this.generateTag();
	}

	/**
	 * Copie l'image dont le chemin est passe en parametre dans l'emplacement du disque situe a l'adresse {@link #imageURL}.
	 * Appelle la methode {@link #enregisterImg()}.
	 * @param source Le chemin du fichier a copier a l'adresse stockee par {@link #imageURL}.
	 * @throws IOException En cas de probleme de lecture ou d'ecriture de la photographie. Par exemple si le chemin passe en parametre est incorrect.
	 */
	private void CopierFichier(String source) throws IOException{
		this.img = ImageIO.read(new File(source));
		this.enregisterImg();
		this.img = ImageIO.read(new File(this.imageURL));
	}

	/**
	 * Enregitre l'image stockee dans {@link #img} a l'emplacement du disque definit par {@link #imageURL}.
	 * @throws IOException En cas de probleme d'ecriture de l'image, par exemple si le chemin stocke dans {@link #imageURL} est incorrecte.
	 */
	private void enregisterImg() throws IOException{
		String extension = this.getExtension();
		File f = new File(this.imageURL);
		f.createNewFile();
		if(extension.equals("jpg") || extension.equals("jpeg")){
			ImageIO.write(this.img, "png", f);
		} else {
			ImageIO.write(this.img, extension, f);
		}
	}

	/**
	 * Genere le tag invisible sur l'image associee a cette photo.
	 * @throws IOException En cas de probleme d'ecriture de l'image, par exemple si le chemin stocke dans {@link #imageURL} est incorrecte.
	 */
	private void tagInvisible() throws IOException{
		int alpha = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		int width = this.img.getWidth();
		int height = this.img.getHeight();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if(col != width-1 || row != height-1){
					Color rgb = new Color(this.img.getRGB(col, row));
					alpha += rgb.getAlpha();
					r += rgb.getRed();
					g += rgb.getGreen();
					b += rgb.getBlue();
				}
			}
		}
		alpha %= 255;
		r %= 255;
		g %= 255;
		b %= 255;
		Color tag = new Color(alpha,r,g,b);
		this.img.setRGB(width-1, height-1, tag.getRGB());
		this.enregisterImg();
	}

	/**
	 * Genere le tag de securite de la photo. Utilise le tag invisible calcule par la methode {@link #tagInvisible()}.
	 */
	private int[] generateTag(){
		int width = this.img.getWidth();
		int height = this.img.getHeight();
		int p1 = this.img.getRGB(0, 0);
		int p2 = this.img.getRGB(width-1, height-1);
		return new int[]{width,height,p1,p2};
	}


	/**
	 * Verifie que le tag de securite correspond bien a la photographiede l'url 
	 * @return True si la photographiecorrespond au tag, false sinon.
	 * @throws IOException En cas de probleme d'ecriture de l'image, par exemple si le chemin stocke dans {@link #imageURL} est incorrecte.
	 */
	public boolean checkTag(){
		boolean ret = true;
		BufferedImage imgTest;
		try {
			imgTest = ImageIO.read(new File(this.imageURL));
			int width = imgTest.getWidth();
			int height = imgTest.getHeight();
			int p1 = imgTest.getRGB(0, 0);
			int p2 = imgTest.getRGB(width-1, height-1);
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

	// --- Comparaison d'images ---
	
	/**
	 * Redimensionne un {@link BufferedImage} au format demande.
	 * @param srcImg La {@link BufferedImage} a redimensionner.
	 * @param w Le nombre de pixels de largeur de l'image finale.
	 * @param h Le nombre de pixels de hauteur de l'image finale.
	 * @return Un objet de type {@link BufferedImage} redimensionne au format passe en parametre
	 */
	private BufferedImage redimensionner( BufferedImage srcImg, int w, int h ){ 
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB); 
		Graphics2D g2 = resizedImg.createGraphics(); 
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
		g2.drawImage(srcImg, 0, 0, w, h, null); 
		g2.dispose();
		return resizedImg; 
	} 

	/**
	 * Calcule les contours visibles dans la {@link BufferedImage} stockee dans l'attribut {@link #img}.
	 * @return Un objet de type {@link BufferedImage} du meme format que {@link #img}. Seuls sont traces les pixels des contours.
	 */
	public BufferedImage contours(){
		BufferedImage rep = new BufferedImage(this.img.getWidth(),this.img.getHeight(),this.img.getType());
		for(int col = 1; col<this.img.getWidth()-1; col++){
			for(int row = 1; row<this.img.getHeight()-1; row++){
				int[] px = new int[8];
				Color base = new Color(this.img.getRGB(col, row+1));
				px[0] = this.img.getRGB(col-1, row+1);
				px[1] = this.img.getRGB(col, row+1);
				px[2] = this.img.getRGB(col+1, row+1);
				px[3] = this.img.getRGB(col-1, row);
				px[4] = this.img.getRGB(col+1, row);
				px[5] = this.img.getRGB(col-1, row+1);
				px[6] = this.img.getRGB(col, row+1);
				px[7] = this.img.getRGB(col+1, row+1);
				double max = 0;
				for(int i : px){
					Color c = new Color(i);
					double diff = Math.abs(c.getAlpha()-base.getAlpha())
							+ Math.abs(c.getRed()-base.getRed())
							+ Math.abs(c.getGreen()-base.getGreen())
							+ Math.abs(c.getBlue()-base.getBlue());
					diff = (diff/(4*256))*100;
					max = Math.max(max, diff);
				}
				if(max>7.5){
					rep.setRGB(col, row, Color.BLACK.getRGB());
				} else {
					rep.setRGB(col, row, Color.WHITE.getRGB());
				}
			}
		}
		try {
			ImageIO.write(rep, "png", new File("data/test/"+this.titre+"."+this.getExtension()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rep;
	}

	/**
	 * Calcule et retourne le pourcentage de difference entre les contours de deux images : celle associee a cet objet photo,
	 * et celle associee a l'objet poto passe en parametre.
	 * @param p2 L'objet Photo avec lequel la comparaison est effectuee.
	 * @return Un double entre 0 et 100. 100 signifie qu'aucune similarite entre les contours n'a ete trouvee.
	 */
	public double differenceContours(Photo p2){
		double rep = 0;
		BufferedImage contourSelf = this.contours();
		BufferedImage contourP2 = p2.contours();
		int colMin = Math.min(contourSelf.getWidth(), contourP2.getWidth());
		int rowMin = Math.min(contourSelf.getHeight(), contourP2.getHeight());
		int nbPixels = colMin*rowMin;
		contourSelf = this.redimensionner(contourSelf,colMin,rowMin);
		contourP2 = this.redimensionner(contourP2,colMin,rowMin);
		for(int col = 0; col<colMin; col++){
			for(int row = 0; row<rowMin; row++){
				if(contourSelf.getRGB(col, row) != contourP2.getRGB(col, row)){
					rep++;
				}
			}
		}
		rep = (rep/nbPixels)*100;
		return rep;
	}

	/**
	 * Genere la courbe de couleur sous la forme d'un tableau de 1024 entiers superieurs a 0.
	 * Un entier pour chacune des 256 nuances de luminosite, de rouge, de vert et de bleu. 
	 * Chaque entier correspond au nombre de pixels de cette nuance de couleur
	 * Un pixel etant code un code RGB (Red Green Blue), chacun possede quatres nuances : 
	 * la luminosite, le rouge, le vert et le bleu, codee chacun sur 256 
	 * @return Un tableau de 1024 entiers positifs.
	 */
	public int[] courbeCouleur(){
		int[] ret = new int[1024];
		int width = this.img.getWidth();
		int height = this.img.getHeight();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Color rgb = new Color(this.img.getRGB(col, row));
				int a = rgb.getAlpha();
				int r = rgb.getRed() + 256;
				int g = rgb.getGreen() + 512;
				int b = rgb.getBlue() + 768;
				ret[a]++;
				ret[r]++;
				ret[g]++;
				ret[b]++;
			}
		}
		return ret;
	}

	/**
	 * Compare les courbes de couleur generee par la methode {@link #courbeCouleur()} des deux photographies et en sort un index de difference.
	 * 
	 * @see La methode {@link #courbeCouleur()}.
	 * @param p2 L'objet Photo avec lequel la comparaison est effectuee.
	 * @return Un double entre 0 et 100. 100 signifie que toutes les valeurs RGB de l'image (y compris les valeurs de luminosite) sont totalement opposées.
	 */
	public double differenceCouleur(Photo p2){
		int[] tab1 = this.courbeCouleur();
		int[] tab2 = p2.courbeCouleur();
		int nbPixelsSelf = this.img.getHeight()*this.img.getWidth();
		int nbPixelsP2 = p2.getImg().getHeight()*p2.getImg().getWidth();
		int min = Math.min(nbPixelsSelf, nbPixelsP2);
		double dbMax = (double)Math.max(nbPixelsSelf, nbPixelsP2);
		double dbMin = (double)min;
		if(min == nbPixelsP2){
			for(int i : tab1){
				i = (int)((double)((double)i/dbMax)*dbMin);
			}
		} else {
			for(int i : tab2){
				i = (int)((double)((double)i/dbMax)*dbMin);
			}
		}
		double ret = 0;
		for(int i = 0; i<tab1.length; i++){
			ret += Math.abs(tab1[i]-tab2[i]);
		}
		ret = (ret/(8*min))*100;
		return ret;
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
					if(this.img.getRGB(col,row)!=p2.img.getRGB(col,row)) ret = false;
					col++;
				}
				row++;
			}
		}
		return ret;
	}

	/**
	 * Utilise les methodes {@link #isIdentique(Photo)}, {@link #differenceContours(Photo)} et {@link #differenceCouleur(Photo)}
	 * pour calculer la difference entre deux images.
	 * @param p2 L'objet Photo contenant l'image a comparer avec {@link #img}.
	 * @return Un double entre 0 et 100. Plus grand est ce double, plus les images sont similaires. 
	 * Un resultat de 100 signifie que les images sont identiques au pixel pres.
	 */
	public double similarite(Photo p2) {
		double rep = 0;
		if(this.isIdentique(p2)){
			rep = 100;
		} else {
			rep = 100-((this.differenceCouleur(p2)+this.differenceContours(p2))/2);
		}
		return rep;
	}

	// --- Getteurs and Setters ---
	/**
	 * Accesseur de l'attribut {@link #imageURL}.
	 * @return Le {@link String} imageURL.
	 */
	public String getImageURL() {
		return this.imageURL;
	}
	
	/**
	 * Accesseur de l'attribut {@link #keyWords}.
	 * @return Le tableau de {@link String} keyWords.
	 */
	public String[] getKeyWords() {
		return this.keyWords;
	}
	
	/**
	 * Accesseur de l'attribut {@link #titre}.
	 * @return Le {@link String} titre.
	 */
	public String getTitre() {
		return this.titre;
	}
	
	/**
	 * Accesseur de l'attribut {@link #auteur}.
	 * @return Le {@link String} auteur.
	 */
	public String getAuteur() {
		return this.auteur;
	}
	
	/**
	 * Accesseur de l'attribut {@link #pays}.
	 * @return Le {@link String} pays.
	 */
	public String getPays() {
		return this.pays;
	}
	
	/**
	 * Accesseur de l'attribut {@link #date}.
	 * @return Le {@link GregorianCalendar} date.
	 */
	public GregorianCalendar getDate() {
		return this.date;
	}
	
	/**
	 * Accesseur de l'attribut {@link #img}.
	 * @return Le {@link BufferedImage} img.
	 */
	public BufferedImage getImg(){
		return this.img;
	}

	/**
	 * Accesseur de l'extension de l'image.
	 * Par exemple "jpg", "png", gif".
	 * @return Le {@link String} calcule a partir de {@link #imageURL}.
	 */
	public String getExtension(){
		String ext = "";
		int i = this.imageURL.length()-1;
		char c = this.imageURL.charAt(i);
		do {
			ext = c + ext;
			i--;
			c = this.imageURL.charAt(i);
		} while(c != '.');
		return ext;
	}
	
	public String getNomFichier(){
		return this.imageURL.split("/")[this.imageURL.split("/").length-1];
	}
	/**
	 * Accesseur de l'attribut {@link #collection}.
	 * @return Le {@link String} collection.
	 */
	public String getCollection() {
		return this.collection;
	}

	/**
	 * Modificateur de l'attibut {@link #collection} 
	 * @param collection La collection a laquelle appartient cette photo.
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	/**
	 * Modificateur de l'attibut {@link #titre} 
	 * @param titre Le titre de cette photo.
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Modificateur de l'attibut {@link #auteur} 
	 * @param auteur L'auteur de cette photo.
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/**
	 * Modificateur de l'attibut {@link #pays} 
	 * @param pays Le pays de prise de vue de cette photo.
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}

	/**
	 * Modificateur de l'attibut {@link #date} 
	 * @param date La date de prise de vue de cette photo.
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	/**
	 * Modificateur de l'attibut {@link #keyWords} 
	 * @param keyWords Les mots cles associes a cette photo.
	 */
	public void setKeyWords(String[] keyWords) {
		this.keyWords = keyWords;
	}

	// --- IO methods ---
	/**
	 * La methode d'ecriture de cet objet photo.
	 * La plupart des attribut de cet objet sont enregistres normalement.
	 * Pour l'attribut {@link #img}, on utilise les methode de {@link ImageIO}.
	 * @param out Le fichier d'ecriture de cet objet.
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out)throws IOException{
		out.defaultWriteObject();
		this.enregisterImg();
	}

	/**
	 * La methode de lecture de cet objet photo.
	 * La plupart des attribut de cet objet sont lus normalement.
	 * Pour l'attribut {@link #img}, on utilise les methode de {@link ImageIO}.
	 * @param in Le fichier de lecture de cet objet.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		this.img = ImageIO.read(new File(this.imageURL));
	}
}

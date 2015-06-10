package datas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 0;
	private String urlDossier;
	private String[] urlCollections;
	
	// --- Sauver et Charger ---
		public void sauver(){
			this.sauver(this.urlDossier);
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
		
		public static User charger(String url){
			FileInputStream file;
			ObjectInputStream flux;
			User ret = null;
			try {
				file = new FileInputStream(url);
				flux = new ObjectInputStream(file);
				ret = (User) flux.readObject();
				flux.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return ret;
		}
	
}

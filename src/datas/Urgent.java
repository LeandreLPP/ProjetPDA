package datas;

import java.util.Enumeration;

public class Urgent {
	public static void main(String[] args){
		User defaut = User.charger("C:/Users/Youkool/CloudStation/workspace/ProjetPDA/saves/Juliette.out");
		Enumeration<Photo> t = defaut.getAllPhotos().toutesPhotos();
		while(t.hasMoreElements()){
			Photo p = t.nextElement();
			if(p != null){
				defaut.getAllPhotos().delPhoto(p);
				defaut.getAllPhotos().addPhoto(p);
			}
		}
		Enumeration<Collection> c = defaut.toutesCollections();
		while(c.hasMoreElements()){
			Collection e = c.nextElement();
			t = e.toutesPhotos();
			while(t.hasMoreElements()){
				Photo p = t.nextElement();
				if(p != null){
					e.delPhoto(p);
					e.addPhoto(p);
				}
			}
		}
		defaut.sauver("C:/Users/Youkool/CloudStation/workspace/ProjetPDA/saves/Juliette.out");
	}

}

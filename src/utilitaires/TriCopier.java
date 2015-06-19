package utilitaires;

import java.util.ArrayList;
import java.util.Hashtable;

import datas.*;

public class TriCopier extends Tri {
	private static final long serialVersionUID = 0;
	Hashtable<Photo, Integer> index;

	public TriCopier(ArrayList<Photo> liste, Collection model){
		super(liste);
		this.index = new Hashtable<Photo, Integer>();
		ArrayList<Photo> listeModel = model.getListePhotos();
		for(Photo p : this.liste){
			int i = listeModel.indexOf(p);
			if(i == -1)i=99999;
			this.index.put(p, i);
		}
	}

	@Override
	protected boolean compare(int i1, int i2) {
		Photo p1 = this.liste.get(i1);
		Photo p2 = this.liste.get(i2);
		return this.index.get(p1)>this.index.get(p2);
	}

	@Override
	public Hashtable<Integer, Object[][]> split() {
		Hashtable<Integer, Object[][]> rep = new Hashtable<Integer, Object[][]>();
		Object[][] resultat = {{"Pas sence etre split"},this.liste.toArray()};
		rep.put(0, resultat);
		return rep;
	}

}

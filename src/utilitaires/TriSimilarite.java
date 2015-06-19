package utilitaires;

import java.util.ArrayList;
import java.util.Hashtable;

import datas.Collection;
import datas.NoPhotoFoundException;
import datas.Photo;

public class TriSimilarite extends Tri {
	private static final long serialVersionUID = 0;
	private Photo base;
	private Hashtable<Photo,Double> similarite;

	public TriSimilarite(Collection source){
		super(source.getListePhotos());
		this.similarite = new Hashtable<Photo,Double>();
		try {
			this.base = source.getPhoto(source.getIndexSelect());
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
		for(Photo p : this.liste){
			this.similarite.put(p, p.similarite(base));
		}
	}

	@Override
	protected boolean compare(int i1, int i2) {
		boolean rep = false;
		Photo p1 = this.liste.get(i1);
		Photo p2 = this.liste.get(i2);
		double p1similarite = this.similarite.get(p1);
		double p2similarite = this.similarite.get(p2);
		if(p1similarite<p2similarite){
			rep = true;
		}
		return rep;
	}

	@Override
	public Hashtable<Integer, Object[][]> split() {
		Hashtable<Integer, Object[][]> rep = new Hashtable<Integer, Object[][]>();
		ArrayList<Photo> identique = new ArrayList<Photo>();
		ArrayList<Photo> forte = new ArrayList<Photo>();
		ArrayList<Photo> faible = new ArrayList<Photo>();
		for(Photo p : this.liste){
			double simil = this.similarite.get(p);
			if(simil == (double)100){
				identique.add(p);
			} else if(simil>=50) {
				forte.add(p);
			} else {
				faible.add(p);
			}
		}
		Object[][] resultatIdentique = {{"Photo identiques"},identique.toArray()};
		rep.put(0, resultatIdentique);
		Object[][] resultatFort = {{"Photo a forte ressemblance"},forte.toArray()};
		rep.put(1, resultatFort);
		Object[][] resultatFaible = {{"Photo a faible ressemblance"},faible.toArray()};
		rep.put(2, resultatFaible);
		return rep;
	}
}

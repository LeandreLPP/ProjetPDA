package utilitaires;

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
		Object[][] resultat = {{"Resultat de la comparaison"},this.liste.toArray()};
		rep.put(0, resultat);
		return rep;
	}
}

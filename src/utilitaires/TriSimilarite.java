package utilitaires;

import java.util.Hashtable;

import datas.Collection;
import datas.NoPhotoFoundException;
import datas.Photo;

public class TriSimilarite extends Tri {
	private static final long serialVersionUID = 0;
	private Photo base;

	public TriSimilarite(Collection source) {
		super(source.getListePhotos());
		try {
			this.base = source.getPhoto(source.getIndexSelect());
		} catch (NoPhotoFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean compare(int i1, int i2) {
		boolean rep = false;
		Photo p1 = this.liste.get(i1);
		Photo p2 = this.liste.get(i2);
		if(p1.similarite(this.base)<p2.similarite(this.base)){
			rep = true;
		}
		return rep;
	}

	@Override
	public Hashtable<Integer, Object[][]> split() {
		Hashtable<Integer, Object[][]> rep = new Hashtable<Integer, Object[][]>();
		Object[][] resultat = {{"Resultat de la comaraison"},this.liste.toArray()};
		rep.put(0, resultat);
		return null;
	}
}

package utilitaires;

import java.util.ArrayList;
import java.util.Hashtable;

import datas.Photo;

public class TriCollectionAntiAlpha extends Tri {
	private static final long serialVersionUID = 0;

	public TriCollectionAntiAlpha(ArrayList<Photo> liste) {
		super(liste);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean compare(int i1, int i2) {
		boolean rep = false;
		Photo p1 = this.liste.get(i1);
		Photo p2 = this.liste.get(i2);
		String s1 = p1.getCollection();
		String s2 = p2.getCollection();
		if(s1 == null){
			s1="All";
			p1.setCollection("All");
		}
		if(s2 == null){
			s2="All";
			p2.setCollection("All");
		}
		if(s1.compareToIgnoreCase(s2)<0){
			rep = true;
		}
		return rep;
	}

	@Override
	public Hashtable<Integer, Object[][]> split() {
		Hashtable<Integer, Object[][]> rep = new Hashtable<Integer, Object[][]>();
		if(this.liste != null && this.liste.size()>0){
			int i = 0;
			String titre = this.liste.get(i).getCollection();
			ArrayList<Photo> liste = new ArrayList<Photo>();
			for(Photo p : this.liste){
				if(!p.getCollection().equals(titre)){
					Object[][] couple = {{titre},liste.toArray()};
					rep.put(i, couple);
					i++;
					titre = p.getCollection();
					liste = new ArrayList<Photo>();
				}
				liste.add(p);
			}
			Object[][] couple = {{titre},liste.toArray()};
			rep.put(i, couple);
		} else {
			Object[][] coupleVide = {{"Aucune photo n'a ete trouvee"},this.liste.toArray()};
			rep.put(0, coupleVide);
		}
		return rep;
	}

}

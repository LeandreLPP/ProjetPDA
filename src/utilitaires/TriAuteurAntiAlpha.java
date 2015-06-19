package utilitaires;

import datas.*;

import java.util.ArrayList;
import java.util.Hashtable;

public class TriAuteurAntiAlpha extends Tri {
	private static final long serialVersionUID = 0;

	public TriAuteurAntiAlpha(ArrayList<Photo> liste){
		super(liste);
	}

	protected boolean compare(int i1, int i2) {
		boolean rep = false;
		Photo p1 = this.liste.get(i1);
		Photo p2 = this.liste.get(i2);
		String s1 = p1.getAuteur();
		String s2 = p2.getAuteur();
		if(s1 == null){
			s1="";
			p1.setAuteur("");
		}
		if(s2 == null){
			s2="";
			p2.setAuteur("");
		}
		if(s1.compareToIgnoreCase(s2)<0){
			rep = true;
		}
		return rep;
	}

	public Hashtable<Integer, Object[][]> split() {
		Hashtable<Integer, Object[][]> rep = new Hashtable<Integer, Object[][]>();
		if(this.liste != null && this.liste.size()>0){
			int i = 0;
			String titre = this.liste.get(i).getAuteur();
			ArrayList<Photo> liste = new ArrayList<Photo>();
			for(Photo p : this.liste){
				if(!p.getAuteur().equals(titre)){
					Object[][] couple = {{titre},liste.toArray()};
					rep.put(i, couple);
					i++;
					titre = p.getAuteur();
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

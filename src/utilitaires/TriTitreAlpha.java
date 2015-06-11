package utilitaires;

import datas.*;

import java.util.ArrayList;
import java.util.Hashtable;

public class TriTitreAlpha extends Tri{
	private static final long serialVersionUID = 0;

	public TriTitreAlpha(ArrayList<Photo> liste){
		super(liste);
	}

	
	protected boolean compare(int i1, int i2) {
		boolean rep = false;
		Photo p1 = this.liste.get(i1);
		Photo p2 = this.liste.get(i2);
		if(p1.getTitre().compareToIgnoreCase(p2.getTitre())>0){
			rep = true;
		}
		return rep;
	}

	public Hashtable<Integer, Object[]> split() {
		Hashtable<Integer, Object[]> rep = new Hashtable<Integer, Object[]>();
		int i = 0;
		String titre = this.liste.get(i).getTitre();
		ArrayList<Photo> liste = new ArrayList<Photo>();
		for(Photo p : this.liste){
			if(!p.getTitre().equals(titre)){
				Object[] couple = {titre,liste.toArray()};
				rep.put(i, couple);
				i++;
				titre = p.getTitre();
			}
			liste.add(p);
		}
		return rep;
	}
}

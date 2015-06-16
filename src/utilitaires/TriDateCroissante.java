package utilitaires;

import datas.*;

import java.util.ArrayList;
import java.util.Hashtable;

public class TriDateCroissante extends Tri {
	private static final long serialVersionUID = 0;

	public TriDateCroissante(ArrayList<Photo> liste){
		super(liste);
	}
	
	protected boolean compare(int i1, int i2) {
		boolean rep = false;
		Photo p1 = this.liste.get(i1);
		Photo p2 = this.liste.get(i2);
		if(p1.getDate().after(p2.getDate())){
			rep = true;
		}
		return rep;
	}

	public Hashtable<Integer, Object[][]> split() {
		Hashtable<Integer, Object[][]> rep = new Hashtable<Integer, Object[][]>();
		int i = 0;
		String titre = this.dateToString(this.liste.get(i));
		ArrayList<Photo> liste = new ArrayList<Photo>();
		for(Photo p : this.liste){
			if(!this.dateToString(p).equals(titre)){
				Object[][] couple = {{titre},liste.toArray()};
				rep.put(i, couple);
				i++;
				titre = this.dateToString(p);
				liste = new ArrayList<Photo>();
			}
			liste.add(p);
		}
		Object[][] couple = {{titre},liste.toArray()};
		rep.put(i, couple);
		return rep;
	}
	
	private String dateToString(Photo p){
		String rep = "";
		java.util.GregorianCalendar d = p.getDate();
		java.util.Date date = d.getTime();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("EEEEEEEE dd MMMMMMMM YYYY");
		rep = format.format(date);
		rep = Character.toUpperCase(rep.charAt(0))+rep.substring(1);
		return rep;
	}
}

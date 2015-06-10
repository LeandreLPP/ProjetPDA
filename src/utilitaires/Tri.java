package utilitaires;

import datas.*;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Tri implements Serializable {
	private static final long serialVersionUID = 0;
	protected ArrayList<Photo> liste;
	
	public Tri (ArrayList<Photo> liste){
		this.liste = liste;
	}
	
	public void trier(){
		for(int i = 0; i < this.liste.size()-1; i++){
			for(int j = i+1; j<this.liste.size(); j++){
				if(this.compare(i, j)){
					this.swap(i, j);
				}
			}
		}
	}
	
	private void swap(int i1, int i2){
		Photo tmp = this.liste.get(i1);
		this.liste.set(i1, this.liste.get(i2));
		this.liste.set(i2, tmp);
	}
	
	abstract boolean compare(int i1, int i2);
}

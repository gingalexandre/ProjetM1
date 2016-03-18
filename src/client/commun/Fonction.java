package client.commun;

public class Fonction {

	public static int getIndexMinValue(Double[] tab){
		if(tab == null || tab.length == 0) {return -1;} 
		int res = 0;
		for(int i=1;i<tab.length;i++){
			if(tab[i]<tab[res]){
				res = i;
			}
		}
		return res;
	}
	
	public static int getIndexMaxValue(Double[] tab){
		if (tab == null || tab.length == 0) { return -1;}
		int res = 0;
		for(int i=1;i<tab.length;i++){
			if(tab[i]>tab[res]){
				res = i;
			}
		}
		return res;
	}
}

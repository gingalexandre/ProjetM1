/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.modele;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Arthur
 */
public class Ressource implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Code pour faciliter l'identification des différentes ressources
    public final static int BOIS = 1;
    public final static int BLE = 2;
    public final static int LAINE = 3;
    public final static int ARGILE = 4;
    public final static int MINERAIE = 5;
    
    //Les différents stocks
    private static HashMap <Integer, Integer> stockBois = new HashMap <>();
    private static HashMap <Integer, Integer> stockBle = new HashMap <>();
    private static HashMap <Integer, Integer> stockLaine = new HashMap <>();
    private static HashMap <Integer, Integer> stockArgile = new HashMap <>();
    private static HashMap <Integer, Integer> stockMineraie = new HashMap <>();
    
    public Ressource(){
        //Initialement 19 éléments par ressources
        stockBois.put(BOIS, 19);
        stockBois.put(BLE, 19);
        stockBois.put(LAINE, 19);
        stockBois.put(ARGILE, 19);
        stockBois.put(MINERAIE, 19);
    }
    
    /**
     * @return the stockBois
     */
    public static HashMap <Integer, Integer> getStockBois() {
        return stockBois;
    }

    /**
     * @param aStockBois the stockBois to set
     */
    public static void setStockBois(HashMap <Integer, Integer> aStockBois) {
        stockBois = aStockBois;
    }

    /**
     * @return the stockBle
     */
    public static HashMap <Integer, Integer> getStockBle() {
        return stockBle;
    }

    /**
     * @param aStockBle the stockBle to set
     */
    public static void setStockBle(HashMap <Integer, Integer> aStockBle) {
        stockBle = aStockBle;
    }

    /**
     * @return the stockLaine
     */
    public static HashMap <Integer, Integer> getStockLaine() {
        return stockLaine;
    }

    /**
     * @param aStockLaine the stockLaine to set
     */
    public static void setStockLaine(HashMap <Integer, Integer> aStockLaine) {
        stockLaine = aStockLaine;
    }

    /**
     * @return the stockArgile
     */
    public static HashMap <Integer, Integer> getStockArgile() {
        return stockArgile;
    }

    /**
     * @param aStockArgile the stockArgile to set
     */
    public static void setStockArgile(HashMap <Integer, Integer> aStockArgile) {
        stockArgile = aStockArgile;
    }

    /**
     * @return the stockMineraie
     */
    public static HashMap <Integer, Integer> getStockMineraie() {
        return stockMineraie;
    }

    /**
     * @param aStockMineraie the stockMineraie to set
     */
    public static void setStockMineraie(HashMap <Integer, Integer> aStockMineraie) {
        stockMineraie = aStockMineraie;
    }
}

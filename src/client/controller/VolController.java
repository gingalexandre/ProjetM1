package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import serveur.modele.Ressource;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

public class VolController implements Initializable {
	
	@FXML
	private Label message;
	
	@FXML
	private TextField resteBois, resteBle, resteArgile, resteMineraie, resteLaine;
	
	@FXML
	private ImageView imgBois, imgBle, imgArgile, imgMineraie, imgLaine;
	
	@FXML
	private Button boutonVol;
	
	/**
	 * Nombre max de ressources
	 */
	private int maxRessource;
	
	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;
	
	/**
	 * Proxy de jeu
	 */
	private Proxy proxy;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		serveur = ConnexionManager.getStaticServeur();
		
		//Initialisation du proxy
		proxy = ConnexionManager.getStaticProxy();
		try {
			proxy.setVolController(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			setImages();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Set les valeurs des textField
	 * @param maxRessources
	 * @throws RemoteException
	 */
	public void setValeursText(int maxRessources) throws RemoteException{
		this.maxRessource = maxRessources;
		this.message.setText("Vous ne pouvez garder que "+maxRessources+" ressources...");
		this.resteArgile.setText(""+proxy.getJoueur().getStockRessource().get(Ressource.ARGILE));
		this.resteBois.setText(""+proxy.getJoueur().getStockRessource().get(Ressource.BOIS));
		this.resteBle.setText(""+proxy.getJoueur().getStockRessource().get(Ressource.BLE));
		this.resteLaine.setText(""+proxy.getJoueur().getStockRessource().get(Ressource.LAINE));
		this.resteMineraie.setText(""+proxy.getJoueur().getStockRessource().get(Ressource.MINERAIE));
	}
	
	/**
	 * Set les images
	 * @throws RemoteException
	 */
	public void setImages() throws RemoteException {
		imgLaine.setImage(new Image("file:Ressources/ressources/ressource_laine.png"));
		imgBois.setImage(new Image("file:Ressources/ressources/ressource_Bois.png"));
		imgMineraie.setImage(new Image("file:Ressources/ressources/ressource_Mineraie.png"));
		imgArgile.setImage(new Image("file:Ressources/ressources/ressource_Argile.png"));
		imgBle.setImage(new Image("file:Ressources/ressources/ressource_Ble.png"));
	}
	
	/**
	 * Permet d'effectue le vol
	 * @throws RemoteException
	 */
	public void effectuerVol() throws RemoteException{
		if(volValide()){
			HashMap<Integer, Integer> nouveauStock = new HashMap<Integer, Integer>();
			nouveauStock.put(Ressource.ARGILE, Integer.parseInt(this.resteArgile.getText()));
			nouveauStock.put(Ressource.MINERAIE, Integer.parseInt(this.resteMineraie.getText()));
			nouveauStock.put(Ressource.LAINE, Integer.parseInt(this.resteLaine.getText()));
			nouveauStock.put(Ressource.BOIS, Integer.parseInt(this.resteBois.getText()));
			nouveauStock.put(Ressource.BLE, Integer.parseInt(this.resteBle.getText()));
			
			proxy.getJoueur().setStockRessource(nouveauStock);
			
			//Actualisation de l'affichage
			this.proxy.getJoueursController().majRessource();
			serveur.getGestionnaireUI().diffuserGainRessource();
			serveur.getGestionnaireUI().diffuserGainCarteRessource();
			
			//Fermeture de la fenêtre
			MenuController.fenetreVol.close();
		}
		else{
			this.message.setText("Saisie invalide ou nombre de ressource différent de "+this.maxRessource);
		}
	}
	
	/**
	 * Indique si le vol est valide
	 * @return true si le vol est valide, false sinon
	 * @throws RemoteException
	 */
	public boolean volValide() throws RemoteException{
		boolean isValide = true;
		
		try{
			//Vérification de typage en int
			int argile = Integer.parseInt(this.resteArgile.getText());
			int bois = Integer.parseInt(this.resteBois.getText());
			int ble = Integer.parseInt(this.resteBle.getText());
			int mineraie = Integer.parseInt(this.resteMineraie.getText());
			int laine = Integer.parseInt(this.resteLaine.getText());
			
			//Vérification d'entiers positifs
			if((argile<0)||(bois<0)||(mineraie<0)||(laine<0)){
				isValide = false;
			}
			
			//Vérification pour que le joueur garde la moitier de ces ressources (maxRessource)
			if((argile+bois+ble+mineraie+laine)!=maxRessource){
				isValide = false;
			}
			
		}
		catch(Exception e){
			isValide = false;
			e.printStackTrace();
		}
		return isValide;
	}
}

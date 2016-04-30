package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
	
	private Proxy proxy;
	
	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Initialisation du proxy
		proxy = ConnexionManager.getStaticProxy();
		try {
			proxy.setVolController(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		serveur = ConnexionManager.getStaticServeur();
		
		try {
			setImages();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setValeursText(int maxRessources) throws RemoteException{
		this.message.setText("Vous ne pouvez garder que "+maxRessources+" ressources...");
	}
	
	public void setImages() throws RemoteException {
		
	}

}

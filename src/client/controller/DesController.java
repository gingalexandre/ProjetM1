package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import serveur.modele.Des;
import serveur.modele.Message;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.reseau.Serveur;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class DesController implements Initializable {

	private static final String numeroUn = "file:Ressources/des/dice1.png";
	private static final String numeroDeux = "file:Ressources/des/dice2.png";
	private static final String numeroTrois = "file:Ressources/des/dice3.png";
	private static final String numeroQuatre = "file:Ressources/des/dice4.png";
	private static final String numeroCinq = "file:Ressources/des/dice5.png";
	private static final String numeroSix = "file:Ressources/des/dice6.png";
	
	@FXML
	private ImageView de1, de2;
	
	@FXML
	private Button boutonDes;
	
	/**
	 * Proxy client
	 */
	private Proxy proxy;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		de1.setImage(new Image(numeroSix));
		de2.setImage(new Image(numeroSix));
		proxy = ConnexionManager.getStaticProxy();
		proxy.setDesController(this);
		
	}

	public void lancerDes() {
		Des des = new Des();
		Integer[] resultats = des.lancerDes();
		
		animateDes();
		
		// Modification des images
		de1.setImage(new Image(distribuerDes(resultats[0])));
		de2.setImage(new Image(distribuerDes(resultats[1])));
		
		notifierLancerDes(resultats);
	}
	
	public void animateDes() {
		RotateTransition rt1 = new RotateTransition(Duration.millis(1000), de1);
	    RotateTransition rt2 = new RotateTransition(Duration.millis(1000), de2);
	    rt1.setByAngle(180*6);
	    rt2.setByAngle(180*6);
	    rt1.play();
	    rt2.play();
	}

	private String distribuerDes(Integer de) {
		switch (de) {
		case 1:
			return numeroUn;
		case 2:
			return numeroDeux;
		case 3:
			return numeroTrois;
		case 4:
			return numeroQuatre;
		case 5:
			return numeroCinq;
		case 6:
			return numeroSix;
		default:
			return null;
		}
	}
	
	private void notifierLancerDes(Integer[] resultats){
		try{
			// Récupération du serveur en passant par le singleton ConnexionManager
			Serveur serveur = ConnexionManager.getStaticServeur();
			serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+" a lancé les dés : "+resultats[0]+" | "+resultats[1]));
		}
		catch (RemoteException e){
			e.printStackTrace();
		}
	}
}

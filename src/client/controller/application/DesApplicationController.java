package client.controller.application;

import java.net.URL;
import java.util.ResourceBundle;

import client.modele.Des;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DesApplicationController implements Initializable {

	private static final String numeroUn = "file:Ressources/des/dice1.png";
	private static final String numeroDeux = "file:Ressources/des/dice2.png";
	private static final String numeroTrois = "file:Ressources/des/dice3.png";
	private static final String numeroQuatre = "file:Ressources/des/dice4.png";
	private static final String numeroCinq = "file:Ressources/des/dice5.png";
	private static final String numeroSix = "file:Ressources/des/dice6.png";
	
	private static final String enAttente = "file:Ressources/des/dices.gif";
	
	private static final String initGif = "file:Ressources/autres/stack.gif";

	@FXML
	private ImageView des1, des2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		des1.setImage(new Image(initGif));
		des2.setImage(new Image(initGif));		
	}

	public void actionLancerDes() {
		Des des = new Des();
		Integer[] resultats = des.lancerDes();

		des1.setImage(new Image(enAttente));
		des2.setImage(new Image(enAttente));
		
		try {
			// Attente volontaire pour simuler le lancé de dés
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Affectation de l'image selon le lancé
		Image imageDe1 = new Image(distribuerDes(resultats[0]));
		Image imageDe2 = new Image(distribuerDes(resultats[1]));
		
		
		// Modification des images
		des1.setImage(imageDe1);
		des1.setImage(imageDe2);

		// Action des dès
		des.actionDes(resultats[0] + resultats[1]);

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
}

package client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class JoueurActuelController implements Initializable {

	@FXML
	private ImageView imageBle, imageArgile, imageCaillou, imageLaine, imageBois;
	
	@FXML
	private Label nbBle, nbArgile, nbCaillou, nbLaine, nbBois, nomJoueur;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nbBle.setText("0");
		nbArgile.setText("0");
		nbCaillou.setText("0");
		nbLaine.setText("0");
		nbBois.setText("0");
		nomJoueur.setText(ConnexionController.nomJoueur);
		
	}

	/**
	 * Méthode permettant de modifier la valeur de la ressource passé en paramètre
	 * @param nomRessource : String nom de la ressource à modifier
	 * @param nombre : int nombre à modifier
	 */
	public void modifierRessource(String nomRessource, int nombre) {

		Label ressource = distribuerLabelRessource(nomRessource);
		int nbInitial = Integer.parseInt(ressource.getText());
		ressource.setText(Integer.toString(nbInitial + nombre));
		
	}

	/**
	 * Méthode renvoyant le Label correspondant au nom de la ressource
	 * @param nomRessource : String : nom de la ressource
	 * @return Label : label correspondant
	 */
	public Label distribuerLabelRessource(String nomRessource) {
		switch (nomRessource) {
		case "Ble":
			return nbBle;
		case "Argile":
			return nbArgile;
		case "Caillou":
			return nbCaillou;
		case "Laine":
			return nbLaine;
		case "Bois":
			return nbBois;
		default:
			return null;
		}
	}

}

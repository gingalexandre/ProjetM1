package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import serveur.modele.Ressource;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.reseau.Serveur;

public class JoueurActuelController implements Initializable {

	@FXML
	private Label nbBle, nbArgile, nbCaillou, nbLaine, nbBois, nomJoueur;

	@FXML
	private GridPane couleurJoueur;
	
	private Proxy proxy;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nbBle.setText("0");
		nbArgile.setText("0");
		nbCaillou.setText("0");
		nbLaine.setText("0");
		nbBois.setText("0");
		nomJoueur.setText(ConnexionController.nomJoueur);
		couleurJoueur.setStyle("-fx-background-color: BLACK;");
		proxy = ConnexionManager.getStaticProxy();
		proxy.setJoueurActuelController(this);
	}
	
	/**
	 * Mise � jour de l'affichage des ressources du joueur actuel
	 * @throws RemoteException
	 */
	
	public void majRessource() throws RemoteException{
		Serveur serveur = ConnexionManager.getStaticServeur();
		HashMap<Integer, Integer> stockJoueur = proxy.getJoueur().getStockRessource();
		this.nbArgile.setText(""+stockJoueur.get(Ressource.ARGILE));
		this.nbBle.setText(""+stockJoueur.get(Ressource.BLE));
		this.nbBois.setText(""+stockJoueur.get(Ressource.BOIS));
		this.nbCaillou.setText(""+stockJoueur.get(Ressource.MINERAIE));
		this.nbLaine.setText(""+stockJoueur.get(Ressource.LAINE));
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

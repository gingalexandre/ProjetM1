package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import client.commun.Fonction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import serveur.modele.Joueur;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;

public class JoueurActuelController implements Initializable {

	@FXML
	private Label nbBle, nbArgile, nbCaillou, nbLaine, nbBois, nomJoueur, nbVictoire;

	@FXML
	private GridPane couleurJoueur;
	
	/**
	 * Proxy client, c'est avec �a qu'on peut acc�der au joueur 
	 */
	private Proxy proxy;
	
	/**
	 * Joueur actuel (correspond au model)
	 */
	private Joueur joueur;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// R�cup�ration du proxy via le singleton ConnexionManager
		proxy = ConnexionManager.getStaticProxy();
		// Indique au proxy que le JoueurActuelController du joueur est cette classe.
		// Permet au proxy d'appeler des m�thodes de cette classe
		proxy.setJoueurActuelController(this);
		
		try {
			// R�cup�ration du joueur pour pouvoir obtenir ses informations
			joueur = proxy.getJoueur();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		nbBle.setText("0");
		nbArgile.setText("0");
		nbCaillou.setText("0");
		nbLaine.setText("0");
		nbBois.setText("0");
		nomJoueur.setText(joueur.getNomUtilisateur());
		nbVictoire.setText("2");
		
		// Appel de la m�thode permettant de transformer la couleur de fran�ais � anglais pour pouvoir changer le style
		String couleurAnglais = Fonction.couleurEnAnglais(joueur.getCouleur());
		couleurJoueur.setStyle("-fx-background-color: "+couleurAnglais+";");
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

package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;

import client.commun.Fonction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import serveur.modele.Joueur;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.modele.Ressource;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.reseau.Serveur;

public class JoueurActuelController implements Initializable {

	@FXML
	private Label nbBle, nbArgile, nbCaillou, nbLaine, nbBois, nomJoueur, nbVictoire;

	@FXML
	private GridPane couleurJoueur;
	
	/**
	 * Proxy client, c'est avec ça qu'on peut accéder au joueur 
	 */
	private Proxy proxy;
	
	/**
	 * Joueur actuel (correspond au model)
	 */
	private Joueur joueur;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Récupération du proxy via le singleton ConnexionManager
		proxy = ConnexionManager.getStaticProxy();
		// Indique au proxy que le JoueurActuelController du joueur est cette classe.
		// Permet au proxy d'appeler des méthodes de cette classe
		proxy.setJoueurActuelController(this);
		
		try {
			// Récupération du joueur pour pouvoir obtenir ses informations
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
		
		// Appel de la méthode permettant de transformer la couleur de français à anglais pour pouvoir changer le style
		String couleurAnglais = Fonction.couleurEnAnglais(joueur.getCouleur());
		couleurJoueur.setStyle("-fx-background-color: "+couleurAnglais+";");
		proxy = ConnexionManager.getStaticProxy();
		proxy.setJoueurActuelController(this);
		
	}
	
	/**
	 * Mise à jour de l'affichage des ressources du joueur actuel
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
	 * MÃ©thode permettant de modifier la valeur de la ressource passÃ© en paramÃ¨tre
	 * @param nomRessource : String nom de la ressource Ã  modifier
	 * @param nombre : int nombre Ã  modifier
	 */
	public void modifierRessource(String nomRessource, int nombre) {
		Label ressource = distribuerLabelRessource(nomRessource);
		int nbInitial = Integer.parseInt(ressource.getText());
		ressource.setText(Integer.toString(nbInitial + nombre));
	}

	/**
	 * MÃ©thode renvoyant le Label correspondant au nom de la ressource
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

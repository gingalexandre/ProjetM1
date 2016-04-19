package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.ResourceBundle;
import client.commun.Fonction;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.modele.Ressource;
import serveur.modele.service.JoueurInterface;
import serveur.reseau.communicationClients.service.GestionnairePartieInterface;
import serveur.reseau.Serveur;

public class JoueursController implements Initializable {

	@FXML
	private Label nbBle, nbArgile, nbCaillou, nbLaine, nbBois, nomJoueur, nbVictoire;

	@FXML
	private GridPane couleurJoueur;

	@FXML
	private GridPane autreUn, autreDeux, autreTrois;

	@FXML
	private Label autreUnName, autreDeuxName, autreTroisName;
	
	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;
	
	/**
	 * Proxy client, c'est avec ça qu'on peut accéder au joueur 
	 */
	private Proxy proxy;
	
	/**
	 * Joueur actuel (correspond au model)
	 */
	private JoueurInterface joueur;
	
	/**
	 * Liste des autres joueurs connectés au serveur
	 */
	private ArrayList<JoueurInterface> autresJoueurs;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		autresJoueurs = new ArrayList<JoueurInterface>();
		
		// Récupération du serveur via le singleton pour facilement le manipuler dans la classe
		serveur = ConnexionManager.getStaticServeur();
		// Récupération du proxy via le singleton ConnexionManager
		proxy = ConnexionManager.getStaticProxy();
		// Indique au proxy que le JoueursController du joueur est cette classe.
		// Permet au proxy d'appeler des méthodes de cette classe
		proxy.setJoueursController(this);

		try {
			// Envoi à CHAQUE joueur la liste de tous les joueurs, sauf lui-même. Permet de réaliser correctement l'affichage
			// des autres joueurs sur l'interface
			((GestionnairePartieInterface)serveur.getGestionnairePartie()).envoyerAutresJoueurs();
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
		try {
			nomJoueur.setText(joueur.getNomUtilisateur());
			nbVictoire.setText("2");
			// Appel de la méthode permettant de transformer la couleur de français à anglais pour pouvoir changer le style
			String couleurAnglais = Fonction.couleurEnAnglais(joueur.getCouleur());
			couleurJoueur.setStyle("-fx-background-color: "+couleurAnglais+";");
			proxy = ConnexionManager.getStaticProxy();
			proxy.setJoueursController(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Mise à jour de l'affichage des ressources du joueur actuel
	 * @throws RemoteException
	 */
	
	public void majRessource() throws RemoteException{
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
	
	/**
	 * Reçoit la liste des autres joueurs connectés au serveur
	 * @param autresJoueurs
	 */
	public void recevoirAutresJoueurs(ArrayList<JoueurInterface> autresJoueurs) throws RemoteException{
		this.autresJoueurs.clear();
		this.autresJoueurs = autresJoueurs;
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				try {
					for(int i=0;i<autresJoueurs.size();i++) {
						if (i == 0) {
							JoueurInterface p = autresJoueurs.get(0);
							autreUnName.setText(p.getNomUtilisateur());
							autreUn.setVisible(true);
							String couleurAnglais = Fonction.couleurEnAnglais(p.getCouleur());
							autreUn.setStyle("-fx-background-color: " + couleurAnglais + ";");
						}
						if (i == 1) {
							JoueurInterface p = autresJoueurs.get(1);
							autreDeuxName.setText(p.getNomUtilisateur());
							autreDeux.setVisible(true);
							String couleurAnglais = Fonction.couleurEnAnglais(p.getCouleur());
							autreDeux.setStyle("-fx-background-color: " + couleurAnglais + ";");
						}
						if (i == 2) {
							JoueurInterface p = autresJoueurs.get(2);
							autreTroisName.setText(p.getNomUtilisateur());
							autreTrois.setVisible(true);
							String couleurAnglais = Fonction.couleurEnAnglais(p.getCouleur());
							autreTrois.setStyle("-fx-background-color: " + couleurAnglais + ";");
						}
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		/*
		for(int i=0;i<this.autresJoueurs.size();i++){
			if(i==0){
				Joueur p = autresJoueurs.get(0);
				autreUnName.setText(p.getNomUtilisateur());
				String couleurAnglais = Fonction.couleurEnAnglais(p.getCouleur());
				autreUn.setStyle("-fx-background-color: "+couleurAnglais+";");
			}
			if(i==1){
				Joueur p = autresJoueurs.get(1);
				autreDeuxName.setText(p.getNomUtilisateur());
				String couleurAnglais = Fonction.couleurEnAnglais(p.getCouleur());
				autreDeux.setStyle("-fx-background-color: "+couleurAnglais+";");
			}
			if(i==2){
				Joueur p = autresJoueurs.get(2);
				autreTroisName.setText(p.getNomUtilisateur());
				String couleurAnglais = Fonction.couleurEnAnglais(p.getCouleur());
				autreTrois.setStyle("-fx-background-color: "+couleurAnglais+";");
			}
		}*/
	}
}

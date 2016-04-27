package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import client.commun.Fonction;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.GridPane;
import serveur.modele.Ressource;
import serveur.modele.service.JoueurInterface;
import serveur.reseau.communicationClients.service.GestionnairePartieInterface;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

public class JoueursController implements Initializable {

	@FXML
	private Label nbBle, nbArgile, nbCaillou, nbLaine, nbBois, nomJoueur, nbVictoire;

	@FXML
	private GridPane couleurJoueur;

	@FXML
	private GridPane autreUn, autreDeux, autreTrois;

	@FXML
	private Label autreUnName, autreDeuxName, autreTroisName;
	
	@FXML
	private Label nbCarteJoueur1, nbCarteJoueur2, nbCarteJoueur3;

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

		// Récupération du serveur via le singleton pour facilement le manipuler
		// dans la classe
		serveur = ConnexionManager.getStaticServeur();
		// Récupération du proxy via le singleton ConnexionManager
		proxy = ConnexionManager.getStaticProxy();
		// Indique au proxy que le JoueursController du joueur est cette classe.
		// Permet au proxy d'appeler des méthodes de cette classe
		try {
			proxy.setJoueursController(this);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// Envoi à CHAQUE joueur la liste de tous les joueurs, sauf
			// lui-même. Permet de réaliser correctement l'affichage
			// des autres joueurs sur l'interface
			((GestionnairePartieInterface) serveur.getGestionnairePartie()).envoyerAutresJoueurs();
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
			// Appel de la méthode permettant de transformer la couleur de
			// français à anglais pour pouvoir changer le style
			String couleurAnglais = Fonction.couleurEnRGB(joueur.getCouleur());
			couleurJoueur.setStyle("-fx-background-color: " + couleurAnglais + ";");
			proxy = ConnexionManager.getStaticProxy();
			proxy.setJoueursController(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Mise à jour de l'affichage des ressources du joueur actuel
	 * 
	 * @throws RemoteException
	 */

	public void majRessource() throws RemoteException {
		HashMap<Integer, Integer> stockJoueur = proxy.getJoueur().getStockRessource();
		Platform.runLater(() -> this.nbArgile.setText("" + stockJoueur.get(Ressource.ARGILE)));
		Platform.runLater(() -> this.nbBle.setText("" + stockJoueur.get(Ressource.BLE)));
		Platform.runLater(() -> this.nbBois.setText("" + stockJoueur.get(Ressource.BOIS)));
		Platform.runLater(() -> this.nbCaillou.setText("" + stockJoueur.get(Ressource.MINERAIE)));
		Platform.runLater(() -> this.nbLaine.setText("" + stockJoueur.get(Ressource.LAINE)));
	}
	
	
	public void majNbCarte() throws RemoteException{
		String nomJoueur = "";
		for(int i = 0; i<serveur.getGestionnairePartie().getPartie().getNombreJoueurs()-1; i++){
				switch (i){
				case 0:
					nomJoueur = this.autreUnName.getText();
					break;
				case 1:
					nomJoueur = this.autreDeuxName.getText();
					break;
				case 2:
					nomJoueur = this.autreTroisName.getText();
					break;
				default :
					break;
				}
				int nbCarte = 0;
				nbCarte += serveur.getGestionnairePartie().getPartie().getJoueurByName(nomJoueur).getStockRessource().get(Ressource.ARGILE);
				nbCarte += serveur.getGestionnairePartie().getPartie().getJoueurByName(nomJoueur).getStockRessource().get(Ressource.BLE);
				nbCarte += serveur.getGestionnairePartie().getPartie().getJoueurByName(nomJoueur).getStockRessource().get(Ressource.BOIS);
				nbCarte += serveur.getGestionnairePartie().getPartie().getJoueurByName(nomJoueur).getStockRessource().get(Ressource.MINERAIE);
				nbCarte += serveur.getGestionnairePartie().getPartie().getJoueurByName(nomJoueur).getStockRessource().get(Ressource.LAINE);
				int n = nbCarte;
				switch (i){
				case 0:
					Platform.runLater(() -> this.nbCarteJoueur1.setText("" + n));
					break;
				case 1:
					Platform.runLater(() -> this.nbCarteJoueur2.setText("" + n));
					break;
				case 2:
					Platform.runLater(() -> this.nbCarteJoueur3.setText("" + n));
					break;
				default :
					break;
			}
		}
	}

	/**
	 * Méthode permettant de modifier la valeur de la ressource passé en
	 * paramètre
	 * 
	 * @param nomRessource
	 *            : String nom de la ressource à modifier
	 * @param nombre
	 *            : int nombre à modifier
	 */
	public void modifierRessource(String nomRessource, int nombre) {
		Label ressource = distribuerLabelRessource(nomRessource);
		int nbInitial = Integer.parseInt(ressource.getText());
		ressource.setText(Integer.toString(nbInitial + nombre));
	}

	/**
	 * Méthode renvoyant le Label correspondant au nom de la ressource
	 * 
	 * @param nomRessource
	 *            : String : nom de la ressource
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
	 * 
	 * @param autresJoueurs
	 */
	public void recevoirAutresJoueurs(ArrayList<JoueurInterface> autresJoueurs) throws RemoteException {
		this.autresJoueurs.clear();
		this.autresJoueurs = autresJoueurs;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < autresJoueurs.size(); i++) {
						if (i == 0) {
							JoueurInterface p = autresJoueurs.get(0);
							autreUnName.setText(p.getNomUtilisateur());
							autreUn.setVisible(true);
							String couleurAnglais = Fonction.couleurEnRGB(p.getCouleur());
							autreUn.setStyle("-fx-background-color: " + couleurAnglais + ";");
						}
						if (i == 1) {
							JoueurInterface p = autresJoueurs.get(1);
							autreDeuxName.setText(p.getNomUtilisateur());
							autreDeux.setVisible(true);
							String couleurAnglais = Fonction.couleurEnRGB(p.getCouleur());
							autreDeux.setStyle("-fx-background-color: " + couleurAnglais + ";");
						}
						if (i == 2) {
							JoueurInterface p = autresJoueurs.get(2);
							autreTroisName.setText(p.getNomUtilisateur());
							autreTrois.setVisible(true);
							String couleurAnglais = Fonction.couleurEnRGB(p.getCouleur());
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
		 * for(int i=0;i<this.autresJoueurs.size();i++){ if(i==0){ Joueur p =
		 * autresJoueurs.get(0); autreUnName.setText(p.getNomUtilisateur());
		 * String couleurAnglais = Fonction.couleurEnAnglais(p.getCouleur());
		 * autreUn.setStyle("-fx-background-color: "+couleurAnglais+";"); }
		 * if(i==1){ Joueur p = autresJoueurs.get(1);
		 * autreDeuxName.setText(p.getNomUtilisateur()); String couleurAnglais =
		 * Fonction.couleurEnAnglais(p.getCouleur()); autreDeux.setStyle(
		 * "-fx-background-color: "+couleurAnglais+";"); } if(i==2){ Joueur p =
		 * autresJoueurs.get(2); autreTroisName.setText(p.getNomUtilisateur());
		 * String couleurAnglais = Fonction.couleurEnAnglais(p.getCouleur());
		 * autreTrois.setStyle("-fx-background-color: "+couleurAnglais+";"); } }
		 */
	}

	/**
	 * Permet de griser un GridPane
	 * 
	 * @param gridPaneAGriser
	 *            GridPane à griser
	 */
	public void griserGridPane(GridPane gridPaneAGriser) {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setSaturation(-1);
		gridPaneAGriser.setEffect(colorAdjust);
	}
	
	public void supprimerGridPane(GridPane gridPaneAGriser) {
		gridPaneAGriser = new GridPane();
	}

	/**
	 * Permet de trouver le joueur qu'il faut supprimer du menu
	 * 
	 * @param nomJoueurASupprimer
	 *            String nom du Joueur
	 */
	public void suppressionJoueur(String nomJoueurASupprimer) {
		if (nomJoueurASupprimer.equals(autreUnName.getText())) {
			this.griserGridPane(autreUn);
		} else if (nomJoueurASupprimer.equals(autreDeuxName.getText())) {
			this.griserGridPane(autreDeux);
		} else {
			this.griserGridPane(autreTrois);
		}
	}

	public void suppressionDepartJoueur(String nomUtilisateur) {
		if (nomUtilisateur.equals(autreUnName.getText())) {
			Platform.runLater(() -> autreUnName.setText(""));
			this.supprimerGridPane(autreUn);
		} else if (nomUtilisateur.equals(autreDeuxName.getText())) {
			Platform.runLater(() -> autreDeuxName.setText(""));
			this.supprimerGridPane(autreDeux);
		} else {
			Platform.runLater(() -> autreTroisName.setText(""));
			this.supprimerGridPane(autreTrois);
		}
	}
}

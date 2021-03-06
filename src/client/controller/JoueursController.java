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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
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
	private AnchorPane routelongue, armeeforte;

	@FXML
	private GridPane couleurJoueur;

	@FXML
	private GridPane autreUn, autreDeux, autreTrois;

	@FXML
	private Label autreUnName, autreDeuxName, autreTroisName;
	
	@FXML
	private Label nbCarteJoueur1, nbCarteJoueur2, nbCarteJoueur3;

	@FXML
	private Label nbVictoireJoueur1, nbVictoireJoueur2, nbVictoireJoueur3;

	@FXML
	private AnchorPane routelongueJoueur1, routelongueJoueur2, routelongueJoueur3;

	@FXML
	private AnchorPane armeeforteJoueur1, armeeforteJoueur2, armeeforteJoueur3;

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
			// Envoi à CHAQUE joueur la liste de tous les joueurs, sauf
			// lui-même. Permet de réaliser correctement l'affichage
			// des autres joueurs sur l'interface
			((GestionnairePartieInterface) serveur.getGestionnairePartie()).envoyerAutresJoueurs();
			// Récupération du joueur pour pouvoir obtenir ses informations
			joueur = proxy.getJoueur();
			
			nomJoueur.setText(joueur.getNomUtilisateur());
			
			// Appel de la méthode permettant de transformer la couleur de
			// français à anglais pour pouvoir changer le style
			String couleurAnglais = Fonction.couleurEnRGB(joueur.getCouleur());
			couleurJoueur.setStyle("-fx-background-color: " + couleurAnglais + ";");
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			nomJoueur.setText(joueur.getNomUtilisateur());
			this.majRessource();
			if(joueur.isArmeeLaPlusPuissante()) armeeforte.setStyle(null);
			if(joueur.isRouteLaPlusLongue()) routelongue.setStyle(null);
			nbVictoire.setText(Integer.toString(joueur.getPointVictoire()));
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
	 * @throws RemoteException
	 */

	public void majRessource() throws RemoteException {
		HashMap<Integer, Integer> stockJoueur = serveur.getGestionnairePartie().getPartie().getJoueurByName(proxy.getJoueur().getNomUtilisateur()).getStockRessource();
		Platform.runLater(() -> {
			this.nbArgile.setText("" + stockJoueur.get(Ressource.ARGILE));
			this.nbBle.setText("" + stockJoueur.get(Ressource.BLE));
			this.nbBois.setText("" + stockJoueur.get(Ressource.BOIS));
			this.nbCaillou.setText("" + stockJoueur.get(Ressource.MINERAIE));
			this.nbLaine.setText("" + stockJoueur.get(Ressource.LAINE));
		});
	}
	
	/**
	 * Méthode mettant à jour le nombre de cartes
	 * @throws RemoteException
	 */
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
	 * Méthode permettant de modifier la valeur de la ressource passé en paramètre
	 * @param nomRessource - nom de la ressource à modifier
	 * @param nombre - nombre à modifier
	 */
	public void modifierRessource(String nomRessource, int nombre) {
		Label ressource = distribuerLabelRessource(nomRessource);
		int nbInitial = Integer.parseInt(ressource.getText());
		ressource.setText(Integer.toString(nbInitial + nombre));
	}

	/**
	 * Méthode renvoyant le Label correspondant au nom de la ressource
	 * @param nomRessource - nom de la ressource
	 * @return le label correspondant
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
							if(p.isArmeeLaPlusPuissante()) armeeforteJoueur1.setStyle(null);
							if(p.isRouteLaPlusLongue()) routelongueJoueur1.setStyle(null);
						}
						if (i == 1) {
							JoueurInterface p = autresJoueurs.get(1);
							autreDeuxName.setText(p.getNomUtilisateur());
							autreDeux.setVisible(true);
							String couleurAnglais = Fonction.couleurEnRGB(p.getCouleur());
							autreDeux.setStyle("-fx-background-color: " + couleurAnglais + ";");
							if(p.isArmeeLaPlusPuissante()) armeeforteJoueur2.setStyle(null);
							if(p.isRouteLaPlusLongue()) routelongueJoueur2.setStyle(null);
						}
						if (i == 2) {
							JoueurInterface p = autresJoueurs.get(2);
							autreTroisName.setText(p.getNomUtilisateur());
							autreTrois.setVisible(true);
							String couleurAnglais = Fonction.couleurEnRGB(p.getCouleur());
							autreTrois.setStyle("-fx-background-color: " + couleurAnglais + ";");
							if(p.isArmeeLaPlusPuissante()) armeeforteJoueur3.setStyle(null);
							if(p.isRouteLaPlusLongue()) routelongueJoueur3.setStyle(null);
						}
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Permet de griser un GridPane
	 * @param gridPaneAGriser - GridPane à griser
	 */
	public void griserGridPane(GridPane gridPaneAGriser) {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setSaturation(-1);
		gridPaneAGriser.setEffect(colorAdjust);
	}
	
	/**
	 * Permet de supprimer un GridPane
	 * @param gridPaneASupprimer
	 */
	public void supprimerGridPane(GridPane gridPaneASupprimer) {
		gridPaneASupprimer = new GridPane();
	}

	/**
	 * Permet de trouver le joueur qu'il faut supprimer du menu
	 * @param nomJoueurASupprimer - nom du joueur à supprimer
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

	/**
	 * Update l'UI en haut quand un joueur se deco
	 * @param nomUtilisateur
	 */
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

	/**
	 * Mise a jour des points de victoire.
	 */
	public void majPointVictoire() throws RemoteException {
		this.updatePointVictoire(joueur,nbVictoire);
		String nomUtilisateur = "";
		for (JoueurInterface ji:autresJoueurs) {
			nomUtilisateur = ji.getNomUtilisateur();
			if (nomUtilisateur.equals(autreUnName.getText())) {
                this.updatePointVictoire(ji,nbVictoireJoueur1);
			} else if (nomUtilisateur.equals(autreDeuxName.getText())) {
                this.updatePointVictoire(ji,nbVictoireJoueur2);
			} else {
                this.updatePointVictoire(ji,nbVictoireJoueur3);
			}
		}
	}

    /**
     * Subméthode pour éviter duplication de code.
     * @param j
     * @param label
     */
    private void updatePointVictoire(JoueurInterface j, Label label){
        Platform.runLater(() -> {
            try {
                label.setText(Integer.toString(j.getPointVictoire()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

	/**
	 * Mise a jour des points de de l'armée puissante.
	 */
	public void updateArmeePuissante() throws RemoteException {
        this.updateArmeePuissante(joueur, armeeforte);
		String nomUtilisateur = "";
		for (JoueurInterface ji:autresJoueurs) {
			nomUtilisateur = ji.getNomUtilisateur();
			if (nomUtilisateur.equals(autreUnName.getText())) {
                this.updateArmeePuissante(ji, armeeforteJoueur1);
			} else if (nomUtilisateur.equals(autreDeuxName.getText())) {
                this.updateArmeePuissante(ji, armeeforteJoueur2);
			} else {
                this.updateArmeePuissante(ji, armeeforteJoueur3);
			}
		}
	}

    /**
     * Subméthode pour éviter duplication de code.
     * @param j
     * @param node
     */
    private void updateArmeePuissante(JoueurInterface j, Node node){
        Platform.runLater(() -> {
            try {
                if(j.isArmeeLaPlusPuissante()) node.setStyle(null);
                if(!j.isArmeeLaPlusPuissante()) node.setStyle("-fx-opacity: 0.25");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }


	/**
	 * Mise a jour des points de de l'armée puissante.
	 */
	public void updateRouteLongue() throws RemoteException {
		this.updateRouteLongue(joueur, routelongue);
		String nomUtilisateur = "";
		for (JoueurInterface ji:autresJoueurs) {
			nomUtilisateur = ji.getNomUtilisateur();
			if (nomUtilisateur.equals(autreUnName.getText())) {
                this.updateRouteLongue(ji, routelongueJoueur1);

			} else if (nomUtilisateur.equals(autreDeuxName.getText())) {
                this.updateRouteLongue(ji, routelongueJoueur2);
			} else {
                this.updateRouteLongue(ji, routelongueJoueur3);
			}
		}
	}

    /**
     * Subméthode pour éviter duplication de code.
     * @param j
     * @param node
     */
    private void updateRouteLongue(JoueurInterface j, Node node){
        Platform.runLater(() -> {
            try {
                if(j.isRouteLaPlusLongue()) node.setStyle(null);
                if(!j.isRouteLaPlusLongue()) node.setStyle("-fx-opacity: 0.25");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

}

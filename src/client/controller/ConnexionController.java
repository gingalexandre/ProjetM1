package client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.commun.Fonction;
import client.view.VuePrincipale;
import exception.TooMuchPlayerException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import serveur.modele.Message;
import serveur.reseau.proxy.JoueurServeur;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

public class ConnexionController implements Initializable {

	@FXML
	private TextField nomUtilisateur;

	@FXML
	private PasswordField mdp;

	@FXML
	private Button connexion;

	@FXML
	private Label utilisateurErreur;

	private Pane page = null;

	private Serveur serveur;

	private Proxy proxy;

	public static Stage inscriptionFenetre, gameFenetre;

	public Date dateNaissance;

	public static String nomJoueur;

	@FXML
	private Button boutonChargerPartie;

	private Pane pageChargementPartie = null;
	private Stage fenetreChargementPartie;

	@FXML
	private ComboBox<Integer> listePartie;

	public void initialize(URL location, ResourceBundle resources) {
		serveur = ConnexionManager.getStaticServeur();
		proxy = ConnexionManager.getStaticProxy();
	}

	public boolean joueurPresent(ArrayList<JoueurServeur> liste) throws RemoteException {
		for (JoueurServeur js : liste) {
			if (js.getJoueur().getNomUtilisateur().equals(nomUtilisateur.getText())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode vérifiant la connexion. Si elle fonctionne, alors la m�thode
	 * lance le jeu
	 * 
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@FXML
	public void connexion() throws RemoteException, InterruptedException, TooMuchPlayerException {
		ArrayList<JoueurServeur> listeJoueurs = new ArrayList<JoueurServeur>();
		boolean connexionOk = false;
		try {
			serveur = ConnexionManager.getStaticServeur();
			listeJoueurs = serveur.getGestionnairePartie().recupererTousLesJoueurs();
			connexionOk = serveur.getGestionnaireBDD().verificationConnexion(nomUtilisateur.getText(),
					Fonction.crypte(mdp.getText()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Si le joueur n'est pas déjà présent sur le serveur
		if (this.joueurPresent(listeJoueurs)) {
			utilisateurErreur.setText("Joueur déjà présent sur la partie.");
		} else {
			// Si le joueur existe
			if (connexionOk) {
				nomJoueur = nomUtilisateur.getText();
				dateNaissance = serveur.getGestionnaireBDD().getDateNaissanceUtilisateur(nomJoueur);
				enregistrerJoueur(nomJoueur, dateNaissance);

				lancerJeu();
			} else {
				utilisateurErreur.setText("Erreur, utilisateur inconnu, inscrivez-vous.");
			}
		}

	}

	/**
	 * Méthode permettant de lancer le jeu une fois connecté
	 */
	public void lancerJeu() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/Game.fxml"));
		try {
			page = (AnchorPane) loader.load();
			gameFenetre = new Stage();
			gameFenetre.setTitle("Les Colons de Catanes");
			Scene scene = new Scene(page, 0, 0);
			gameFenetre.setScene(scene);
			VuePrincipale.stagePrincipal.close();
			gameFenetre.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					Serveur serveur = ConnexionManager.getStaticServeur();
					try {
						serveur.getGestionnaireUI().diffuserMessage(
								new Message(proxy.getJoueur().getNomUtilisateur() + " s'est déconnecté de la partie"));
						serveur.getGestionnaireUI().diffuserDepartJoueur(proxy.getJoueur());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			});
			gameFenetre.showAndWait();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Appel la méthode du serveur pour enregistrer le joueur
	 * 
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	public void enregistrerJoueur(String nomJoueur, Date date) throws RemoteException, TooMuchPlayerException {
		// Enregistrement du joueur sur le serveur
		serveur.enregistrerJoueur(this.proxy, nomJoueur, date);
		// Set le nom du joueur. Pour recuperer le joueur n'importe a (et donc
		// ses attributs), passer par proxy.getJoueur()
		this.proxy.getJoueur().setNomUtilisateur(nomJoueur);
		this.proxy.getJoueur().setDateDeNaissance(date);
	}

	/**
	 * Se lance quand l'utilisateur appuie sur entrée lorsqu'il se trouve dans
	 * le PasswordField
	 * 
	 * @throws RemoteException
	 * @throws InterruptedException
	 * @throws TooMuchPlayerException
	 */
	public void onEnter() throws RemoteException, InterruptedException, TooMuchPlayerException {
		connexion();
	}

	/**
	 * Permet l'ouverture de la fenêtre permettant de choisir une partie à
	 * charger
	 * 
	 * @throws InterruptedException
	 */
	public void ouvrirFenetreChargerPartie() throws InterruptedException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ChargementPartie.fxml"));
		try {
			boolean connexionOk = serveur.getGestionnaireBDD().verificationConnexion(nomUtilisateur.getText(),
					Fonction.crypte(mdp.getText()));
			if (connexionOk) {
				pageChargementPartie = (Pane) loader.load();
				
				ArrayList<Integer> listeIdPartieSauvegarde = serveur.getGestionnaireBDD()
						.recupererPartieByName(nomUtilisateur.getText());
				if (listeIdPartieSauvegarde != null && listeIdPartieSauvegarde.size() > 0) {
					listePartie = new ComboBox<Integer>();
					listePartie.getItems().addAll(listeIdPartieSauvegarde);
					fenetreChargementPartie = new Stage();
					fenetreChargementPartie.setTitle("Les Colons de Catanes");
					Scene scene = new Scene(pageChargementPartie, 430, 500);
					fenetreChargementPartie.setScene(scene);
					fenetreChargementPartie.showAndWait();
				}
				else{
					utilisateurErreur.setText("Erreur, aucune partie sauvegardée.");
				}
			} else {
				utilisateurErreur.setText("Erreur, utilisateur inconnu, inscrivez-vous.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Charger une partie
	 */
	public void chargerPartie() {
		// TODO
	}
}

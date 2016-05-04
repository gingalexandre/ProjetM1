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
	private Button connexion, boutonChargementPartie, boutonParamètresPartie;

	@FXML
	private Label utilisateurErreur;

	private Pane page = null;
	
	/**
	 * Fenêtre de chargement de la partie
	 */
	private Pane pageChargementPartie = null;
	
	/**
	 * Fenêtre de chargement de la partie
	 */
	private Pane pageParametresPartie = null;

	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;

	/**
	 * Proxy du joueur
	 */
	private Proxy proxy;

	/**
	 * Diverses fenêtres
	 */
	public static Stage inscriptionFenetre, gameFenetre, fenetreChargementPartie, fenetreParametres;

	/**
	 * Date de naissance du joueur
	 */
	public Date dateNaissance;

	/**
	 * Nom du joueur
	 */
	public static String nomJoueur;
	
	/**
	 * Indique si le joueur est le premier joueur sur le serveur
	 */
	private boolean premierJoueur = true;

	/** 
	 * Méthodes d'initialisation
	 */
	public void initialize(URL location, ResourceBundle resources) {
		serveur = ConnexionManager.getStaticServeur();
		proxy = ConnexionManager.getStaticProxy();
		try {
			if(serveur.getGestionnairePartie() != null){
				if(serveur.getGestionnairePartie().getPartie().getNombreJoueurs() >= 1){
					boutonChargementPartie.setVisible(false);
					boutonParamètresPartie.setVisible(false);
					premierJoueur = false;
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de savoir si un joueur est déjà présent sur la partie
	 * @param liste de tous les joueurs connectés dans la partie
	 * @return vrai si le joueur est présent, faux sinon
	 * @throws RemoteException
	 */
	public boolean joueurPresent(ArrayList<JoueurServeur> liste) throws RemoteException {
		for (JoueurServeur js : liste) {
			if (js.getJoueur().getNomUtilisateur().equals(nomUtilisateur.getText())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode vérifiant la connexion. Si elle fonctionne, alors la méthode lance le jeu
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	@FXML
	public void connexion() throws RemoteException, InterruptedException, TooMuchPlayerException {
		boolean gestionnairePartieNull = false;
		if(serveur.getGestionnairePartie() == null){
			gestionnairePartieNull = true;
		}
		if(serveur.getGestionnairePartie() == null || !serveur.getGestionnairePartie().getPartie().isPartieCommence()){ // La partie a pas commencé, le joueur peut se connecter tranquille
			ArrayList<JoueurServeur> listeJoueurs = new ArrayList<JoueurServeur>();
			boolean connexionOk = false;
			try {
				if(!gestionnairePartieNull){
					listeJoueurs = serveur.getGestionnairePartie().recupererTousLesJoueurs();
				}
				connexionOk = serveur.getGestionnaireBDD().verificationConnexion(nomUtilisateur.getText(), Fonction.crypte(mdp.getText()));
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
					if(!premierJoueur){ // C'est pas le premier joueur
						enregistrerJoueur(nomJoueur, dateNaissance);
					}
					else{ // C'est le premier joueur
						enregistrerJoueur(nomJoueur, dateNaissance, ParametresController.nbJoueurs, ParametresController.difficulte);
					}
					lancerJeu();
				} else {
					utilisateurErreur.setText("Erreur, utilisateur inconnu, inscrivez-vous.");
				}
			}
		}else{
			utilisateurErreur.setText("Désolé ! La partie a déjà commencé.");
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
			gameFenetre.setMaximized(true);
			VuePrincipale.stagePrincipal.close();
			gameFenetre.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					try {
						serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur() + " s'est déconnecté de la partie"));
						serveur.getGestionnaireUI().diffuserDepartJoueur(proxy.getJoueur());
						System.exit(0);
					} catch (Exception e) {
						System.exit(0);
					}
				}
			});
			gameFenetre.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Appel la méthode du serveur pour enregistrer le joueur
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	public void enregistrerJoueur(String nomJoueur, Date date) throws RemoteException, TooMuchPlayerException {
		if(!serveur.enregistrerJoueur(this.proxy, nomJoueur, date)){
			utilisateurErreur.setText("Vous ne faîtes pas partie de la partie chargée qui est en cours.");
		}
		else{
			// Set le nom du joueur. Pour recuperer le joueur n'importe où (et donc ses attributs), passer par proxy.getJoueur()
			this.proxy.getJoueur().setNomUtilisateur(nomJoueur);
			this.proxy.getJoueur().setDateDeNaissance(date);
		}
	}
	
	/**
	 * Méthode appelée que lors de la connexion du PREMIER joueur de la partie
	 * @param nomJoueur
	 * @param date
	 * @param nbJoueurs
	 * @param difficulte
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	public void enregistrerJoueur(String nomJoueur, Date date, int nbJoueurs, String difficulte) throws RemoteException, TooMuchPlayerException {
		if(!serveur.enregistrerJoueur(this.proxy, nomJoueur, date, nbJoueurs, difficulte)){
			utilisateurErreur.setText("Vous ne faîtes pas partie de la partie chargée qui est en cours.");
		}
		else{
			// Set le nom du joueur. Pour recuperer le joueur n'importe où (et donc ses attributs), passer par proxy.getJoueur()
			this.proxy.getJoueur().setNomUtilisateur(nomJoueur);
			this.proxy.getJoueur().setDateDeNaissance(date);
		}
	}
	
	/**
	 * Se lance quand l'utilisateur appuie sur entrée lorsqu'il se trouve dans le PasswordField
	 * @throws RemoteException
	 * @throws InterruptedException
	 * @throws TooMuchPlayerException
	 */
	public void onEnter() throws RemoteException, InterruptedException, TooMuchPlayerException {
		connexion();
	}

	/**
	 * Permet l'ouverture de la fenêtre permettant de choisir une partie à charger
	 * @throws InterruptedException
	 * @throws RemoteException 
	 */
	public void ouvrirFenetreChargerPartie() throws InterruptedException, RemoteException {
		if(serveur.getGestionnairePartie() == null || !serveur.getGestionnairePartie().getPartie().isPartieCommence()){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ChargementPartie.fxml"));
			try {
				boolean connexionOk = serveur.getGestionnaireBDD().verificationConnexion(nomUtilisateur.getText(),
						Fonction.crypte(mdp.getText()));
				if (connexionOk) {
					nomJoueur = nomUtilisateur.getText();
					pageChargementPartie = (Pane) loader.load();
	
					ArrayList<Integer> listeIdPartieSauvegarde = serveur.getGestionnaireBDD()
							.recupererPartieByName(nomUtilisateur.getText());
					if (listeIdPartieSauvegarde != null && listeIdPartieSauvegarde.size() > 0) {
						boutonParamètresPartie.setVisible(false);
						serveur.creerGestionnaireUIetPartie();
						
						fenetreChargementPartie = new Stage();
						fenetreChargementPartie.setTitle("Les Colons de Catanes");
						Scene scene = new Scene(pageChargementPartie, 370, 200);
						fenetreChargementPartie.setScene(scene);
						fenetreChargementPartie.showAndWait();
					} else {
						utilisateurErreur.setText("Erreur, aucune partie sauvegardée.");
					}
				} else {
					utilisateurErreur.setText("Erreur, utilisateur inconnu, inscrivez-vous.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			utilisateurErreur.setText("Une partie chargée est déjà sur le serveur, vous ne pouvez pas en charger une autre.");
		}
	}
	
	@FXML
	public void ouvrirFenetreParametresPartie() throws IOException{
		boutonChargementPartie.setVisible(false);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/ParametresPartie.fxml"));
		pageParametresPartie = (Pane) loader.load();
		
		fenetreParametres = new Stage();
		fenetreParametres.setTitle("Paramètres de la partie");
		Scene scene = new Scene(pageParametresPartie, 300, 200);
		fenetreParametres.setScene(scene);
		fenetreParametres.showAndWait();
	}
}

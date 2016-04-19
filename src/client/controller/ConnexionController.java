package client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ResourceBundle;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import serveur.modele.Message;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.reseau.Serveur;

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
	
	private FXMLLoader inscriptionChargement;
	public static Stage inscriptionFenetre, gameFenetre;
	
	public Date dateNaissance;
	
	public static String nomJoueur;
	
	public void initialize(URL location, ResourceBundle resources) {
		serveur = ConnexionManager.getStaticServeur();
		proxy = ConnexionManager.getStaticProxy();
	}
	
	/**
	 * Méthode vérifiant la connexion. Si elle fonctionne, alors la m�thode lance le jeu
	 * @throws RemoteException 
	 * @throws TooMuchPlayerException 
	 */
	@FXML
	public void connexion() throws RemoteException, InterruptedException, TooMuchPlayerException {
		boolean connexionOk = false;
		try {
			serveur = ConnexionManager.getStaticServeur();
			connexionOk = serveur.getGestionnaireBDD().verificationConnexion(nomUtilisateur.getText(), mdp.getText());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Si le joueur existe
		if(connexionOk){
			nomJoueur = nomUtilisateur.getText();
			dateNaissance = serveur.getGestionnaireBDD().getDateNaissanceUtilisateur(nomJoueur);
			enregistrerJoueur(nomJoueur, dateNaissance);
			
			lancerJeu();
		}
		else{
			utilisateurErreur.setText("Erreur, utilisateur inconnu, inscrivez-vous.");
		}
	}
	
	/**
	 * Méthode permettant de lancer le jeu une fois connect�
	 */
	public void lancerJeu(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/Game.fxml"));
		try {
			page = (AnchorPane) loader.load();
			gameFenetre = new Stage();
			gameFenetre.setTitle("Les Colons de Catanes");
		    Scene scene = new Scene(page,0,0);
		    gameFenetre.setScene(scene);
		    VuePrincipale.stagePrincipal.close();
		    gameFenetre.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            public void handle(WindowEvent we) {
	            	Serveur serveur = ConnexionManager.getStaticServeur();
	            	try {
						serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+" s'est déconnecté de la partie"));
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
	 * Méthode permettant d'afficher la fen�tre d'inscription
	 */
	@FXML
	public void inscription() {
		inscriptionChargement = new FXMLLoader(getClass().getResource("/fxml/Inscription.fxml"));
		try {
			page = (Pane) inscriptionChargement.load();
			inscriptionFenetre = new Stage();
			inscriptionFenetre.setTitle("Fen�tre d'inscription");
			inscriptionFenetre.initModality(Modality.WINDOW_MODAL);
		    Scene miniScene = new Scene(page);
		    inscriptionFenetre.setScene(miniScene);
		    gameFenetre.setFullScreen(true);
		    inscriptionFenetre.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * Appel la méthode du serveur pour enregistrer le joueur 
	 * @throws RemoteException
	 * @throws TooMuchPlayerException
	 */
	public void enregistrerJoueur(String nomJoueur, Date date) throws RemoteException, TooMuchPlayerException{
		// Enregistrement du joueur sur le serveur
		serveur.enregistrerJoueur(this.proxy, nomJoueur, date);
		// Set le nom du joueur. Pour recuperer le joueur n'importe a (et donc ses attributs), passer par proxy.getJoueur()
		this.proxy.getJoueur().setNomUtilisateur(nomJoueur);
		this.proxy.getJoueur().setDateDeNaissance(date);
	}
	
	/**
	 * Se lance quand l'utilisateur appuie sur entrée lorsqu'il se trouve dans le PasswordField
	 * @throws RemoteException
	 * @throws InterruptedException
	 * @throws TooMuchPlayerException
	 */
	public void onEnter() throws RemoteException, InterruptedException, TooMuchPlayerException{
		connexion();
	}
}

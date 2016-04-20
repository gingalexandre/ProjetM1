package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import client.commun.Fonction;
import serveur.modele.Message;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Controller du chat
 * @author jerome
 */
public class ChatController implements Initializable{

	@FXML
	private ScrollPane scrollPanePrincipal, scrollPaneSysteme, scrollPaneJoueurs;
	
	@FXML
	private TextFlow textFlowPrincipal, textFlowSysteme, textFlowJoueurs;

	@FXML
	private TextField saisie;
	
	/**
	 * Taille maximale d'un message
	 */
	private static final int TAILLE_MAX_MESSAGE = 150;
	
	/**
	 * Proxy client
	 */
	private Proxy proxy;
	
	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		enregistrerController();
		listenerVues();
		proprietesComposants();
		try{
			serveur = ConnexionManager.getStaticServeur();
			serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+" vient de se connecter"));
			serveur.envoyerNombreJoueursConnectes();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Indique au serveur le controller chat distant
	 */
	private void enregistrerController() {
		proxy = ConnexionManager.getStaticProxy();
		// Indique au proxy que le ChatController du joueur est cette classe
		proxy.setChatController(this);
	}
	
	/**
	 * Appelle les méthodes gérant les listener des composants de la vue
	 */
	private void listenerVues() {
		nombreCharMaxTextField();
	}
	
	/**
	 * Mets des propriétés dans certains composants
	 */
	private void proprietesComposants() {
		saisie.setPromptText("Max. 150 caractères");
	}

	/**
	 * Limite la taille maximale d'un message
	 */
	private void nombreCharMaxTextField() {
		saisie.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() > oldValue.intValue()) {
	                if (saisie.getText().length() >= TAILLE_MAX_MESSAGE) {
	                    	saisie.setText(saisie.getText().substring(0, TAILLE_MAX_MESSAGE));
	                    }
	                }
	            }
	        }
		);
	}
	
	/**
	 * Affiche le message dans les TextArea correspondantes
	 * @param message - Message à afficher
	 */
	public void afficherMessage(Message message){
		if(message.isSystem()){
			Platform.runLater(() -> textFlowPrincipal.getChildren().add(creerStyleTexteMessage(message)));
			Platform.runLater(() -> textFlowSysteme.getChildren().add(creerStyleTexteMessage(message)));
		}
		else{
			Platform.runLater(() -> textFlowPrincipal.getChildren().add(creerStyleTexteAuteur(message)));
			Platform.runLater(() -> textFlowPrincipal.getChildren().add(creerStyleTexteMessage(message)));
			
			Platform.runLater(() -> textFlowJoueurs.getChildren().add(creerStyleTexteAuteur(message)));
			Platform.runLater(() -> textFlowJoueurs.getChildren().add(creerStyleTexteMessage(message)));
		}
		
		// Scroll du chat
		setScrollValue(scrollPanePrincipal, scrollPanePrincipal.getHmax());
		setScrollValue(scrollPaneJoueurs, scrollPaneJoueurs.getHmax());
		setScrollValue(scrollPaneSysteme, scrollPaneSysteme.getHmax());
	}
	
	/**
	 * Renvoie un Text avec le style adéquat pour l'auteur du message
	 * @param message - message à transformer
	 * @return le Text avec le style adéquat
	 */
	public Text creerStyleTexteAuteur(Message message){
		Text auteur = new Text(message.getAuteur() + " : ");
		auteur.setFont(Font.font(auteur.getFont().getFamily(), FontWeight.BOLD, auteur.getFont().getSize())); // C'est pas propre mais j'arrive pas à faire autrement
		auteur.setFill(Fonction.getCouleurFromString(message.getCouleur()));
		return auteur;
	}
	
	/**
	 * Renvoie un Text avec le style adéquat pour le contenu du message
	 * @param message - message à transformer
	 * @return le Text avec le style adéquat
	 */
	public Text creerStyleTexteMessage(Message message){
		Text contenu = new Text(message.getMessage() + "\n");
		return contenu;
	}
	
	/**
	 * Se déclenche quand l'utilisateur appuie sur la touche "Entrée" lorsqu'il se trouve dans le TextField
	 * @throws RemoteException 
	 */
	@FXML
	public void onEnter() throws RemoteException{
		String messageUtilisateur = saisie.getText();
		if(!messageUtilisateur.equals("")){
			Message message = new Message(proxy.getJoueur().getNomUtilisateur(), messageUtilisateur, proxy.getJoueur().getCouleur());
			try{
				// Appel de la méthode distante diffuserMessage du serveur pour envoyer le message � tous les controllers des joueurs
				serveur.getGestionnaireUI().diffuserMessage(message);
			}
			catch (RemoteException e){
				e.printStackTrace();
			}
			saisie.setText("");
		}
	}
	
	/**
	 * Place la barre de scroll à la valeur donnée
	 * @param scrollPane
	 * @param value
	 */
	public void setScrollValue(ScrollPane scrollPane, double value){
		Platform.runLater(() -> scrollPane.setVvalue(value));
	}
}

package client.controller.application;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import client.commun.Fonction;
import client.controller.rmi.JoueurServeur;
import client.modele.Message;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import serveur.ConnexionManager;
import serveur.Serveur;

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
	 * JoueurServeur client
	 */
	private JoueurServeur joueur;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		enregistrerController();
		listenerVues();
		
		joueur = ConnexionManager.getStaticProxy();
	}
	
	/**
	 * Taille maximale d'un message
	 */
	private static final int TAILLE_MAX_MESSAGE = 150;
	
	/**
	 * Indique au serveur le controller chat distant
	 */
	private void enregistrerController() {
		JoueurServeur joueurServeur = ConnexionManager.getStaticProxy();
		joueurServeur.setChatController(this);
	}
	
	/**
	 * Appelle les méthodes gérant les listener des composants de la vue
	 */
	private void listenerVues() {
		nombreCharMaxTextField();
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
		Platform.runLater(() -> textFlowPrincipal.getChildren().add(creerStyleTexteAuteur(message)));
		Platform.runLater(() -> textFlowPrincipal.getChildren().add(creerStyleTexteMessage(message)));
		
		Platform.runLater(() -> textFlowJoueurs.getChildren().add(creerStyleTexteAuteur(message)));
		Platform.runLater(() -> textFlowJoueurs.getChildren().add(creerStyleTexteMessage(message)));
	}
	
	/**
	 * Renvoie un Text avec le style adéquat pour l'auteur du message
	 * @param message
	 * @return
	 */
	public Text creerStyleTexteAuteur(Message message){
		Text auteur = new Text(message.getAuteur() + " : ");
		auteur.setFont(Font.font("Verdana, FontWeight.BOLD, 20"));
		auteur.setFill(Fonction.getCouleurFromString(message.getCouleur()));
		return auteur;
	}
	
	/**
	 * Renvoie un Text avec le style adéquat pour le contenu du message
	 * @param message
	 * @return
	 */
	public Text creerStyleTexteMessage(Message message){
		Text contenu = new Text(message.getMessage() + "\n");
		contenu.setFont(Font.font("Verdana, 20"));
		return contenu;
	}
	
	/**
	 * Se déclenche quand l'utilisateur appuie sur la touche "Entrée" lorsqu'il se trouve dans le TextField
	 */
	@FXML
	public void onEnter(){
		String messageUtilisateur = saisie.getText();
		if(!messageUtilisateur.equals("")){
			Message message = new Message("Pierre", messageUtilisateur, joueur.getCouleur());
			try{
				Serveur serveur = ConnexionManager.getStaticServeur();
				serveur.diffuserMessage(message);
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
		scrollPane.setVvalue(value);
	}
}

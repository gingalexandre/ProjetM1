package client.controller.application;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import client.controller.rmi.ChatRMIController;
import client.modeles.Message;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import serveur.ConnexionManager;
import serveur.Serveur;

/**
 * Controller du chat
 * @author jerome
 */
public class ChatApplicationController implements Initializable{
	
	private static final int TAILLE_MAX_MESSAGE = 150;

	@FXML
	private TextArea principal, systeme, joueurs;

	@FXML
	private TextField saisie;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		enregistrerController();
		listenerVues();
	}
	
	/**
	 * Indique au serveur le controller chat distant
	 */
	private void enregistrerController() {
		ChatRMIController chatRMIController;
		try {
			chatRMIController = new ChatRMIController();
			chatRMIController.setChatController(this);
			Serveur serveur = ConnexionManager.getStaticServeur();
			serveur.enregistrerCommunication(chatRMIController);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
		principal.appendText(message.getAuteur() + " : "+message.getMessage() + "\n");
		joueurs.appendText(message.getAuteur() + " : "+message.getMessage() + "\n");
	}
	
	/**
	 * Se déclenche quand l'utilisateur appuie sur la touche "Entrée" lorsqu'il se trouve dans le TextField
	 */
	@FXML
	public void onEnter(){
		String messageUtilisateur = saisie.getText();
		if(!messageUtilisateur.equals("")){
			Message message = new Message("Pierre", messageUtilisateur);
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
}

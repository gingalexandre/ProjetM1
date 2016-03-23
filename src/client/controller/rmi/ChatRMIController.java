package client.controller.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.controller.application.ChatApplicationController;
import client.modeles.Message;
import javafx.application.Platform;
import service.Communication;

/**
 * Fais le lien entre le controller de chat de l'application et le serveur rmi
 * @author jerome
 */
public class ChatRMIController extends UnicastRemoteObject implements Communication{

	private static final long serialVersionUID = 1L;
	
	private ChatApplicationController chatController;
	
	public ChatRMIController() throws RemoteException {
		super();
	}
	
	public void setChatController(ChatApplicationController chatController){
		this.chatController = chatController;
	}
	
	@Override
	public void recevoir(Message message) {
		Platform.runLater(() -> chatController.afficherMessage(message));
	}
}

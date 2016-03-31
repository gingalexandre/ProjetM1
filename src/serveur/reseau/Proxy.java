package serveur.reseau;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.controller.ChatController;
import client.controller.PlateauController;
import serveur.modele.Joueur;
import serveur.modele.Message;
import serveur.modele.Plateau;

public class Proxy extends UnicastRemoteObject implements serveur.reseau.JoueurServeur{

	private static final long serialVersionUID = 1L;

	/**
	 * Couleur de jeu du joueur
	 */
	private String couleur;
	
	/**
	 * 	Controller du chat
	 */
	private ChatController chatController;
	
	/**
	 * Controller du plateau
	 */
	private PlateauController plateauController;

	/**
	 * Nom de l'utilisateur dans la base de donn�es
	 */
	private String nomUtilisateur;
	
	public Proxy() throws RemoteException{
		
	}
	
	/**
	 * Recup�re la couleur de jeu du joueur
	 * @param color
	 */
	public String getCouleur(){
		return this.couleur;
	}
	
	/**
	 * @param chatController
	 */
	public void setChatController(ChatController chatController){
		this.chatController = chatController;
	}
	
	/**
	 * @param plateauController
	 */
	public void setPlateauController(PlateauController plateauController){
		this.plateauController = plateauController;
	}
	
	/**
	 * Re�oit le message transmit par le serveur et l'envoie au joueur et l'envoie au controller du chat
	 * @param message
	 * @throws RemoteException
	 */
	@Override
	public void recevoirMessage(Message message) throws RemoteException {
		this.chatController.afficherMessage(message);
	}

	/**
	 * Re�oit le plateau envoy� par le serveur et l'envoie au controller du plateau
	 * @param plateau
	 * @throws RemoteException
	 */
	@Override
	public void envoyerPlateau(Plateau plateau) throws RemoteException {
		this.plateauController.setPlateau(plateau);
	}
	
	/**
	 * Indique la couleur de jeu du joueur
	 * @param color
	 */
	@Override
	public void setCouleur(String couleur) throws RemoteException{
		this.couleur = couleur;
	}
	
	/**
	 * Permet d'obtenir le nom de l'utilisateur
	 * @return le nom de l'utilisateur
	 */
	public String getNomUtilisateur(){
		return this.nomUtilisateur;
	}
	
	/**
	 * Permet de donner le nom de login de l'utilisateur
	 * @param nom - nom de l'utilisateur
	 */
	public void setNomUtilisateur(String nomUtilisateur){
		this.nomUtilisateur = nomUtilisateur;
	}
}
package serveur.reseau.communicationClients.gestionnaire;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import serveur.modele.Message;
import serveur.modele.Plateau;
import serveur.modele.service.PlateauInterface;
import serveur.reseau.communicationClients.service.GestionnaireUIInterface;
import serveur.reseau.proxy.JoueurServeur;

/**
 * Classe qui s'occupe des echanges concernant l'interface entre les clients et le serveur
 * @author jerome
 */
public class GestionnaireUI extends UnicastRemoteObject implements GestionnaireUIInterface{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Plateau de jeu
	 */
	private PlateauInterface plateau;
	
	/**
	 * Contient la liste des joueurs connect�s au serveur
	 */
	private ArrayList<JoueurServeur> joueurServeurs = new ArrayList<JoueurServeur>();
	
	/**
	 * Constructeur de la classe GestionnaireUI
	 */
	public GestionnaireUI() throws RemoteException{
		this.plateau = Plateau.getInstance();
	}

	/**
	 * @return le plateau de jeu
	 */
	public PlateauInterface getPlateau() throws RemoteException{
		return this.plateau;
	}
	
	/**
	 * Enregistre un nouveau joueur dans la liste des joueurs
<<<<<<< HEAD:src/serveur/reseau/communicationClients/GestionnaireUI.java
	 * @param nouveauJoueurServeur - joueur � enregistrer
=======
	 * @param nouveauJoueurServeur - joueur a enregistrer
>>>>>>> master:src/serveur/reseau/communicationClients/gestionnaire/GestionnaireUI.java
	 */
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur) throws RemoteException{
		joueurServeurs.add(nouveauJoueurServeur);
	}
	
	/**
<<<<<<< HEAD:src/serveur/reseau/communicationClients/GestionnaireUI.java
	 * Envoie le plateau de jeu au joueur pass� en param�tre
=======
	 * Envoie le plateau de jeu au joueur pass� en parametre
>>>>>>> master:src/serveur/reseau/communicationClients/gestionnaire/GestionnaireUI.java
	 * @param proxy
	 * @throws RemoteException 
	 */
	public void envoyerPlateau(JoueurServeur proxy) throws RemoteException {
		proxy.envoyerPlateau(this.plateau);
	}

	/**
	 * Diffuse les modifications du voleur aux joueurs
	 * @param depart case de départ
	 * @param arrive case d'arrivée
	 * @throws RemoteException
	 */
	public void diffuserVoleur(int depart, int arrive) throws RemoteException {
		plateau.getVoleur().setVOLEUR(false);
		plateau.getHexagones().get(arrive).setVOLEUR(true);
		// deux lignes du dessus ne marche pas car plateau semble différent pour chaque joueur...
		for(JoueurServeur joueurServeur : joueurServeurs){
			joueurServeur.envoyerPositionVoleur(depart,arrive);
		}
	}

	/**
<<<<<<< HEAD:src/serveur/reseau/communicationClients/GestionnaireUI.java
	 * Diffuse les messages du chat aux joueurs
=======
	 * Diffuse un message envoyé par un joueur a tous les autre joueurServeurs
>>>>>>> master:src/serveur/reseau/communicationClients/gestionnaire/GestionnaireUI.java
	 * @param message
	 * @throws RemoteException
	 */
	public void diffuserMessage(Message message) throws RemoteException {
		for(JoueurServeur joueurServeur : joueurServeurs){
			joueurServeur.recevoirMessage(message);
		}
	}
}
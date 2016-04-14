package serveur.reseau.communicationClients;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Message;
import serveur.modele.Plateau;
import serveur.reseau.JoueurServeur;

/**
 * Classe qui s'occupe des echanges concernant l'interface entre les clients et le serveur
 * @author jerome
 */
public class GestionnaireUI implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Plateau de jeu
	 */
	private Plateau plateau;
	
	/**
	 * Contient la liste des joueurs connect�s au serveur
	 */
	private ArrayList<JoueurServeur> joueurServeurs = new ArrayList<JoueurServeur>();
	
	/**
	 * Constructeur de la classe GestionnaireUI
	 */
	public GestionnaireUI(){
		this.plateau = Plateau.getInstance();
	}

	public Plateau getPlateau() {
		return this.plateau;
	}
	
	/**
	 * Enregistre un nouveau joueur dans la liste des joueurs
	 * @param nouveauJoueurServeur - joueur a enregistrer
	 */
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur){
		joueurServeurs.add(nouveauJoueurServeur);
	}
	
	/**
	 * Envoie le plateau de jeu au joueur pass� en parametre
	 * @param proxy
	 * @throws RemoteException 
	 */
	public void envoyerPlateau(JoueurServeur proxy) throws RemoteException {
		proxy.envoyerPlateau(this.plateau);
	}
	
	/**
	 * Diffuse un message envoy� par un joueur a tous les autre joueurServeurs
	 * @param message
	 * @throws RemoteException
	 */
	public void diffuserMessage(Message message) throws RemoteException {
		for(JoueurServeur joueurServeur : joueurServeurs){
			joueurServeur.recevoirMessage(message);
		}
	}
}

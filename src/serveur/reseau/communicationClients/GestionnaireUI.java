package serveur.reseau.communicationClients;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Message;
import serveur.modele.Plateau;
import serveur.reseau.JoueurServeur;

/**
 * Classe qui s'occupe des �changes concernant l'interface entre les clients et le serveur
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
	 * @param nouveauJoueurServeur - joueur � enregistrer
	 */
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur){
		joueurServeurs.add(nouveauJoueurServeur);
	}
	
	/**
	 * Envoie le plateau de jeu au joueur pass� en param�tre
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
	 * Diffuse les messages du chat aux joueurs
	 * @param message
	 * @throws RemoteException
	 */
	public void diffuserMessage(Message message) throws RemoteException {
		for(JoueurServeur joueurServeur : joueurServeurs){
			joueurServeur.recevoirMessage(message);
		}
	}
}

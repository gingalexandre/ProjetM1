package serveur.reseau.communicationClients.gestionnaire;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import serveur.modele.Message;
import serveur.modele.Plateau;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PlateauInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;
import serveur.reseau.communicationClients.service.GestionnaireUIInterface;
import serveur.reseau.proxy.JoueurServeur;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

/**
 * Classe qui s'occupe des echanges concernant l'interface entre les clients et le serveur
 * @author jerome
 */
public class GestionnaireUI extends UnicastRemoteObject implements GestionnaireUIInterface {

	private static final long serialVersionUID = 1L;

	/**
	 * Plateau de jeu
	 */
	private PlateauInterface plateau;

	/**
	 * Contient la liste des joueurs connectés au serveur
	 */
	private ArrayList<JoueurServeur> joueurServeurs = new ArrayList<JoueurServeur>();

	/**
	 * Constructeur de la classe GestionnaireUI
	 */
	public GestionnaireUI(String difficulte) throws RemoteException {
		Plateau.setDifficulte(difficulte);
		this.plateau = Plateau.getInstance();
	}

	/**
	 * @return le plateau de jeu
	 */
	public PlateauInterface getPlateau() throws RemoteException {
		return this.plateau;
	}

	/**
	 * Permet d'indiquer un nouveau plateau
	 * @param plateau - nouveau plateau
	 */
	public void setPlateau(PlateauInterface plateau) throws RemoteException{
		this.plateau = plateau;
	}
	
	/**
	 * Enregistre un nouveau joueur dans la liste des joueurs
	 * @param nouveauJoueurServeur - joueur a enregistrer
	 */
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur) throws RemoteException {
		joueurServeurs.add(nouveauJoueurServeur);
	}

	/**
	 * Envoie le plateau de jeu au joueur passé en parametre
	 * @param proxy
	 * @throws RemoteException
	 */
	public void envoyerPlateau(JoueurServeur proxy) throws RemoteException {
		proxy.envoyerPlateau(this.plateau);
	}
	
	/**
	 * Permet d'envoyer le plateau à tous les joueurs
	 * @throws RemoteException
	 */
	public void envoyerPlateauATousLesJoueurs() throws RemoteException {
		for(JoueurServeur j : joueurServeurs){
			j.envoyerPlateau(this.plateau);
		}
	}

	/**
	 * Diffuse les modifications du voleur aux joueurs
	 * @param depart - case de départ
	 * @param arrive - case d'arrivée
	 * @throws RemoteException
	 */
	public void diffuserVoleur(int depart, int arrive) throws RemoteException {
		plateau.getVoleur().setVOLEUR(false);
		plateau.getHexagones().get(arrive).setVOLEUR(true);
		// deux lignes du dessus ne marche pas car plateau semble différent pour
		// chaque joueur...
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.envoyerPositionVoleur(depart, arrive);
		}
	}

	/**
	 * Diffuse les messages du chat aux joueurs pour la prise d'un message
	 * @param message
	 * @throws RemoteException
	 */
	public void diffuserMessage(Message message) throws RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.recevoirMessage(message);
		}
	}

	/**
	 * Diffuse les messages du chat aux joueurs pour la prise d'une route
	 * @param message
	 * @throws RemoteException
	 */
	public void diffuserPriseDeRoute(RouteInterface r, JoueurInterface j) throws RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.recevoirPriseDeRoute(r, j);
		}
	}

	/**
	 * Diffuse les messages du chat aux joueurs pour la prise d'une route
	 * @param message
	 * @throws RemoteException
	 */
	public void diffuserPriseDeVille(VilleInterface v, JoueurInterface joueurCourrant) throws RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.recevoirPriseDeVille(v, joueurCourrant);
		}
	}

	/** 
	 * Diffuse le gain d'une ressource
	 * @throws RemoteException
	 */
	public void diffuserGainRessource() throws RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.recevoirGainRessource();
		}
	}

	/**
	 * Permet de diffuser le fait que le joueur x est parti
	 * @throws RemoteException
	 */
	public void diffuserDepartJoueur(JoueurInterface joueurSupprime) throws RemoteException {
		Serveur serveur = ConnexionManager.getInstance().getServeur();
		String nomJoueurSupprime = joueurSupprime.getNomUtilisateur();
		// Dans le cas où la partie n'a pas commencée
		if (!serveur.getGestionnairePartie().getPartie().isPartieCommence()) {
			serveur.getGestionnairePartie().getPartie().affecterNullJoueur(joueurSupprime);
			serveur.supprimerJoueur(joueurSupprime);
			for (JoueurServeur js : joueurServeurs) {
				js.suppressionDepartJoueur(nomJoueurSupprime);
			}
			serveur.envoyerNombreJoueursConnectes();
		} else {
			// Si il reste plus de 1 joueur sur le serveur
			if(serveur.getGestionnairePartie().getPartie().getNombreJoueurs() > 1){
				// Si c'était le tour du joueur qui part, on le termine et le jeu continu
				if (serveur.getGestionnairePartie().getPartie().getJoueurTour().getNomUtilisateur().equals(joueurSupprime.getNomUtilisateur())) {
					serveur.getGestionnairePartie().finirTour(nomJoueurSupprime);
				}
				// Suppression du joueur
				joueurServeurs.remove(joueurSupprime);
				for (JoueurServeur js : joueurServeurs) {
					js.suppressionJoueur(nomJoueurSupprime);
				}
				serveur.getGestionnairePartie().getPartie().setTour(serveur.getGestionnairePartie().getPartie().getTour() - 1);
			}
		}
		// Suppression complète du serveur
		serveur.getGestionnairePartie().supprimerJoueur(joueurSupprime);
		serveur.getGestionnairePartie().getPartie().supprimerJoueur(joueurSupprime);
	}

	/**
	 * Diffuse une proposition
	 * @param j - joueur à qui on diffuse
	 * @param offreDemande - HashMap contenant les ressources
	 * @param nomExpediteur - nom de l'expediteur
	 * @throws RemoteException
	 */
	public void diffuserProposition(JoueurServeur j, HashMap<String, Integer> offreDemande, String nomExpediteur) throws RemoteException {
		j.envoyerProposition(offreDemande, nomExpediteur);
	}

	/**
	 * Permet de diffuser le gain d'une carte ressource
	 * @throws RemoteException
	 */
	public void diffuserGainCarteRessource() throws RemoteException {
		for (JoueurServeur js : joueurServeurs) {
			js.envoyerNbCarte();
		}		
	}

	/**
	 * Permet de supprimer un joueur
	 * @param joueur - joueur à supprimer
	 * @throws RemoteException
	 */
	public void supprimerJoueur(JoueurServeur joueur) throws RemoteException{
		this.joueurServeurs.remove(joueur);
	}

	/**
	 * Permet d'envoyer un vol
	 * @param ressourcesMax
	 * @param j
	 * @throws RemoteException
	 */
	public void envoyerVol(int ressourcesMax, JoueurServeur j) throws RemoteException {
		j.envoyerVol(ressourcesMax);
	}

	/**
	 * Permet la mise a jour des points d'un joueur.
	 * @throws RemoteException
	 */
	public void updatePointVictoire() throws  RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.updatePointVictoire();
		}
	}

	/**
	 * Permet la mise a jour de la carte armée puissante.
	 * @throws RemoteException
	 */
	public void updateArmeePuissante() throws  RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.updateArmeePuissante();
		}
	}

	/**
	 * Permet la mise a jour de la carte route longue.
	 * @throws RemoteException
	 */
	public void updateRouteLongue() throws  RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.updateRouteLongue();
		}
	}

	/**
	 * Permet de récupérer toutes les ressources d'un type pour monopoliser
	 * @param ressource_visee
	 * @throws RemoteException
	 */
	public int monopole(int ressource_visee) throws  RemoteException {
		int value = 0;
		for (JoueurServeur joueurServeur : joueurServeurs) {
			value += joueurServeur.monopole(ressource_visee);
		}
		return value;
	}

	
}

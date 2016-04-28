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
 * Classe qui s'occupe des echanges concernant l'interface entre les clients et
 * le serveur
 * 
 * @author jerome
 */
public class GestionnaireUI extends UnicastRemoteObject implements GestionnaireUIInterface {

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
	public GestionnaireUI() throws RemoteException {
		this.plateau = Plateau.getInstance();
	}

	/**
	 * @return le plateau de jeu
	 */
	public PlateauInterface getPlateau() throws RemoteException {
		return this.plateau;
	}

	/**
	 * Enregistre un nouveau joueur dans la liste des joueurs
	 * 
	 * @param nouveauJoueurServeur
	 *            - joueur a enregistrer
	 */
	public void enregistrerJoueur(JoueurServeur nouveauJoueurServeur) throws RemoteException {
		joueurServeurs.add(nouveauJoueurServeur);
	}

	/**
	 * Envoie le plateau de jeu au joueur pass� en parametre
	 * 
	 * @param proxy
	 * @throws RemoteException
	 */
	public void envoyerPlateau(JoueurServeur proxy) throws RemoteException {
		proxy.envoyerPlateau(this.plateau);
	}

	/**
	 * Diffuse les modifications du voleur aux joueurs
	 * 
	 * @param depart
	 *            case de départ
	 * @param arrive
	 *            case d'arrivée
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
	 * Diffuse les messages du chat aux joueurs
	 * 
	 * @param message
	 * @throws RemoteException
	 */
	public void diffuserMessage(Message message) throws RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.recevoirMessage(message);
		}
	}

	public void diffuserPriseDeRoute(RouteInterface r, JoueurInterface j) throws RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.recevoirPriseDeRoute(r, j);
		}
	}

	@Override
	public void diffuserPriseDeVille(VilleInterface v, JoueurInterface joueurCourrant) throws RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.recevoirPriseDeVille(v, joueurCourrant);
		}
	}

	@Override
	public void diffuserGainRessource() throws RemoteException {
		for (JoueurServeur joueurServeur : joueurServeurs) {
			joueurServeur.recevoirGainRessource();
		}
	}

	/**
	 * Permet de diffuser le fait que le joueur x est parti
	 */
	public void diffuserDepartJoueur(JoueurInterface joueurSupprime) throws RemoteException {
		Serveur serveur = ConnexionManager.getInstance().getServeur();
		// Dans le cas où la partie n'a pas commencée
		if (!serveur.getGestionnairePartie().getPartie().isPartieCommence()) {
			serveur.getGestionnairePartie().getPartie().affecterNullJoueur(joueurSupprime);
			serveur.supprimerJoueur(joueurSupprime);
			for (JoueurServeur js : joueurServeurs) {
				js.suppressionDepartJoueur(joueurSupprime.getNomUtilisateur());
			}
			serveur.envoyerNombreJoueursConnectes();
		} else {
			// Si c'était le tour du joueur qui part, on le termine et le jeu
			// continu
			if (serveur.getGestionnairePartie().getPartie().getJoueurTour().getNomUtilisateur().equals(joueurSupprime.getNomUtilisateur())) {
				serveur.getGestionnairePartie().finirTour();
			}
			// Suppression du joueur
			joueurServeurs.remove(joueurSupprime);
			for (JoueurServeur js : joueurServeurs) {
				js.suppressionJoueur(joueurSupprime.getNomUtilisateur());
			}
			serveur.getGestionnairePartie().getPartie().setTour(serveur.getGestionnairePartie().getPartie().getTour() - 1);
		}
		// Suppression complète du serveur
		serveur.getGestionnairePartie().supprimerJoueur(joueurSupprime);
		serveur.getGestionnairePartie().getPartie().supprimerJoueur(joueurSupprime);
	}

	@Override
	public void diffuserProposition(JoueurServeur j, HashMap<String, Integer> offreDemande, String nomExpediteur) throws RemoteException {
		j.envoyerProposition(offreDemande, nomExpediteur);
	}

	@Override
	public void diffuserGainCarteRessource() throws RemoteException {
		for (JoueurServeur js : joueurServeurs) {
			js.envoyerNbCarte();
		}		
	}
}

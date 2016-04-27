package serveur.bdd.modeleSauvegarde;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import serveur.commun.Fonctions;
import serveur.modele.Plateau;
import serveur.modele.Ressource;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PartieInterface;
import serveur.reseau.proxy.JoueurServeur;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

/**
 * Classe servant a convertir une PartieInterface en PartieSauvegarde pour la
 * sauvegarde de l'objet
 * 
 * @author Alexandre
 *
 */
public class PartieSauvegarde implements Serializable {
	/**
	 * Variable pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Booléen pour savoir si la partie a commencé
	 */

	/**
	 * PlateauSauvegarde stockant le plateau
	 */
	private PlateauSauvegarde plateauCourant;
	/**
	 * Id de la Partie
	 */
	private int idPartie;
	/**
	 * Liste des JoueurSauvegarde correspondant aux Joueurs de la Partie
	 */
	private ArrayList<JoueurSauvegarde> joueurs = new ArrayList<JoueurSauvegarde>();
	/**
	 * Joueur a qui c'est le tour
	 */
	private JoueurSauvegarde joueurActuel;
	/**
	 * Booléen pour savoir si la partie a commencé
	 */
	private boolean isPartieCommence;
	/**
	 * Ressource
	 */
	private Ressource ressources;
	/**
	 * Tour
	 */
	private int tour;
	/**
	 * Tour total
	 */
	private int tourGlobal;

	/**
	 * Constructeur
	 * 
	 * @throws RemoteException
	 */
	public PartieSauvegarde(boolean t) throws RemoteException {
		this.plateauCourant = new PlateauSauvegarde(recupererPlateau());
		Serveur serveur = ConnexionManager.getStaticServeur();
		this.ressources = serveur.getGestionnairePartie().getPartie().getRessources();
		this.isPartieCommence = serveur.getGestionnairePartie().getPartie().isPartieCommence();
		this.tour = serveur.getGestionnairePartie().getPartie().getTour();
		this.tourGlobal = serveur.getGestionnairePartie().getPartie().getCompteurTourGlobal();
		ArrayList<JoueurServeur> joueursServeur = new ArrayList<JoueurServeur>();
		try {
			// Récupération de tous les joueurs
			joueursServeur = serveur.getGestionnairePartie().recupererTousLesJoueurs();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		this.joueurs = Fonctions.transformArrayJoueurSauvegarde(joueursServeur);
		JoueurInterface joueurInterfaceActuel;
		PartieInterface partie = null;
		try {
			// Récupération du Joueur a qui c'est le tour
			partie = serveur.getGestionnairePartie().getPartie();
			joueurInterfaceActuel = partie.getJoueurTour();
			this.joueurActuel = new JoueurSauvegarde(joueurInterfaceActuel);
			this.idPartie = partie.getId();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Constructeur vide pour la désérialisation
	 */
	public PartieSauvegarde() throws RemoteException {
	}

	/**
	 * Méthode permettant de récupérer le plateau de la Partie
	 * 
	 * @return Plateau
	 * @throws RemoteException
	 */
	private Plateau recupererPlateau() throws RemoteException {
		return Plateau.getInstance();
	}

	/**
	 * Getter de Serialversionuid
	 * 
	 * @return long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Setter du PlateauSauvegarde de la Partie
	 * 
	 * @param PlateauSauvegarde
	 *            plateauCourant
	 */
	public void setPlateauCourant(PlateauSauvegarde plateauCourant) {
		this.plateauCourant = plateauCourant;
	}

	/**
	 * Setter de l'ArrayList de JoueurSauvegarde de la Partie
	 * 
	 * @param ArrayList<JoueurSauvegarde>
	 *            joueurs
	 */
	public void setJoueurs(ArrayList<JoueurSauvegarde> joueurs) {
		this.joueurs = joueurs;
	}

	/**
	 * Setter du Joueur Actuel (celui à qui c'est le tour)
	 * 
	 * @param JoueurSauvegarde
	 *            joueurActuel
	 */
	public void setJoueurActuel(JoueurSauvegarde joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

	/**
	 * Getter du PlateauSauvegarde correspondant à la Partie
	 * 
	 * @return PlateauSauvegarde
	 */
	public PlateauSauvegarde getPlateauCourant() {
		return plateauCourant;
	}

	/**
	 * Getter de l'ArrayList de JoueurSauvegarde
	 * 
	 * @return ArrayList<JoueurSauvegarde>
	 */
	public ArrayList<JoueurSauvegarde> getJoueurs() {
		return joueurs;
	}

	/**
	 * Getter du JoueurSauvegarde correspondant au Joueur Actuel
	 * 
	 * @return JoueurSauvegarde
	 */
	public JoueurSauvegarde getJoueurActuel() {
		return joueurActuel;
	}

	/**
	 * Getter de l'Id de la Partie
	 * 
	 * @return Integer
	 */
	public int getIdPartie() {
		return idPartie;
	}

	/**
	 * Setter de l'Id de la Partie
	 * 
	 * @param Integer
	 *            idPartie
	 */
	public void setIdPartie(int idPartie) {
		this.idPartie = idPartie;
	}

	/**
	 * Getter du boolean pour savoir si la partie à commencé
	 * 
	 * @return booléen
	 */
	public boolean isPartieCommence() {
		return isPartieCommence;
	}

	/**
	 * Setter du boolean pour savoir si la partie à commencé
	 * 
	 * @param partieCommence
	 *            booléen
	 */
	public void setPartieCommence(boolean partieCommence) {
		this.isPartieCommence = partieCommence;
	}

	/**
	 * Méthode pour déserialiser
	 * 
	 * @param json
	 *            : String json en entrée
	 * @return l'Objet Partie Sauvegarde
	 */
	public static PartieSauvegarde deserialiser(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(json, PartieSauvegarde.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Getter de Ressource
	 * 
	 * @return Ressource
	 */
	public Ressource getRessources() {
		return ressources;
	}

	/**
	 * Setter de Ressource
	 * 
	 * @param Ressource
	 */
	public void setRessources(Ressource ressources) {
		this.ressources = ressources;
	}

	/**
	 * Getter de Tour
	 * 
	 * @return int
	 */
	public int getTour() {
		return tour;
	}

	/**
	 * Setter de Tour
	 * 
	 * @param int
	 */
	public void setTour(int tour) {
		this.tour = tour;
	}

	/**
	 * Getter de TourGlobale
	 * 
	 * @return int
	 */
	public int getTourGlobal() {
		return tourGlobal;
	}

	/**
	 * Setter de TourGlobale
	 * 
	 * @param int
	 */
	public void setTourGlobal(int tourGlobal) {
		this.tourGlobal = tourGlobal;
	}

}

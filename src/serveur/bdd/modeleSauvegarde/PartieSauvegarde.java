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
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PartieInterface;
import serveur.reseau.proxy.JoueurServeur;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

/**
 * Classe servant a convertir une partieInterface en partieSauvegarde pour la
 * sauvegarde de l'objet
 * 
 * @author Alexandre
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
	 * Id de la partie
	 */
	private int idPartie;

	/**
	 * Liste des JoueurSauvegarde correspondant aux Joueurs de la partie
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
	 * Tour
	 */
	private int tour;

	/**
	 * Tour total
	 */
	private int tourGlobal;

	/**
	 * Ressource
	 */
	private RessourceSauvegarde ressource;
	
	private PaquetSauvegarde deck;

	/**
	 * Constructeur
	 * 
	 * @throws RemoteException
	 */
	public PartieSauvegarde(boolean t) throws RemoteException {
		this.plateauCourant = new PlateauSauvegarde(recupererPlateau());
		Serveur serveur = ConnexionManager.getStaticServeur();
		this.deck = new PaquetSauvegarde(serveur.getGestionnairePartie().getPartie().getDeck());
		
		this.ressource = new RessourceSauvegarde(serveur.getGestionnairePartie().getPartie().getRessources());
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
	 * Méthode permettant de récupérer le plateau de la partie
	 * 
	 * @return Plateau
	 * @throws RemoteException
	 */
	private Plateau recupererPlateau() throws RemoteException {
		return Plateau.getInstance();
	}

	/**
	 * Getter de tour
	 * 
	 * @return int
	 */
	public int getTour() {
		return tour;
	}

	/**
	 * Setter de tour
	 * 
	 * @param int
	 */
	public void setTour(int tour) {
		this.tour = tour;
	}

	/**
	 * Getter de tourGlobale
	 * 
	 * @return int
	 */
	public int getTourGlobal() {
		return tourGlobal;
	}

	/**
	 * Setter de tourGlobale
	 * 
	 * @param int
	 */
	public void setTourGlobal(int tourGlobal) {
		this.tourGlobal = tourGlobal;
	}

	/**
	 * Setter du PlateauSauvegarde de la partie
	 * 
	 * @param PlateauSauvegarde
	 *            - plateauCourant
	 */
	public void setPlateauCourant(PlateauSauvegarde plateauCourant) {
		this.plateauCourant = plateauCourant;
	}

	/**
	 * Setter de l'ArrayList de JoueurSauvegarde de la partie
	 * 
	 * @param joueurs
	 */
	public void setJoueurs(ArrayList<JoueurSauvegarde> joueurs) {
		this.joueurs = joueurs;
	}

	/**
	 * Setter du joueur actuel (celui à qui c'est le tour)
	 * 
	 * @param JoueurSauvegarde
	 *            - joueurActuel
	 */
	public void setJoueurActuel(JoueurSauvegarde joueurActuel) {
		this.joueurActuel = joueurActuel;
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
	 * Getter du PlateauSauvegarde correspondant à la partie
	 * 
	 * @return PlateauSauvegarde
	 */
	public PlateauSauvegarde getPlateauCourant() {
		return plateauCourant;
	}

	/**
	 * Getter de l'ArrayList de JoueurSauvegarde
	 * 
	 * @return la liste des joueurs
	 */
	public ArrayList<JoueurSauvegarde> getJoueurs() {
		return joueurs;
	}

	/**
	 * Getter du JoueurSauvegarde correspondant au joueur actuel
	 * 
	 * @return JoueurSauvegarde
	 */
	public JoueurSauvegarde getJoueurActuel() {
		return joueurActuel;
	}

	/**
	 * Getter de l'id de la partie
	 * 
	 * @return Integer
	 */
	public int getIdPartie() {
		return idPartie;
	}

	/**
	 * Setter de l'Id de la partie
	 * 
	 * @param Integer
	 *            - idPartie
	 */
	public void setIdPartie(int idPartie) {
		this.idPartie = idPartie;
	}

	/**
	 * Getter du boolean pour savoir si la partie à commencé
	 * 
	 * @return booléen
	 */
	public boolean getIsPartieCommence() {
		return isPartieCommence;
	}

	/**
	 * Setter du boolean pour savoir si la partie à commencé
	 * 
	 * @param partieCommence
	 *            - booléen
	 */
	public void setPartieCommence(boolean partieCommence) {
		this.isPartieCommence = partieCommence;
	}

	/**
	 * Méthode pour déserialiser
	 * 
	 * @param json
	 *            - json en entrée
	 * @return l'objet Partie Sauvegarde
	 */
	public static PartieSauvegarde deserialiser(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			PartieSauvegarde test = objectMapper.readValue(json, PartieSauvegarde.class);
			
			return test;
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
	 * @return RessourceSauvegarde
	 */
	public RessourceSauvegarde getRessource() {
		return ressource;
	}

	/**
	 * Setter de Ressource
	 * 
	 * @param ressource
	 *            RessourceSauvegarde
	 */
	public void setRessource(RessourceSauvegarde ressource) {
		this.ressource = ressource;
	}

	public PaquetSauvegarde getDeck() {
		return deck;
	}

	public void setDeck(PaquetSauvegarde deck) {
		this.deck = deck;
	}
	
	

}

package serveur.bdd.modeleSauvegarde;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Plateau;
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
	 * Constructeur
	 * 
	 * @throws RemoteException
	 */
	public PartieSauvegarde() throws RemoteException {
		this.plateauCourant = new PlateauSauvegarde(recupererPlateau());
		Serveur serveur = ConnexionManager.getStaticServeur();
		ArrayList<JoueurServeur> joueursServeur = new ArrayList<JoueurServeur>();
		try {
			// Récupération de tous les joueurs
			joueursServeur = serveur.getGestionnairePartie().recupererTousLesJoueurs();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for (JoueurServeur js : joueursServeur) {

			try {
				// Conversion de chaque JoueurInterface en JoueurSauvegarde
				JoueurSauvegarde joueur = new JoueurSauvegarde(js.getJoueur());
				this.joueurs.add(joueur);
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		}
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

}

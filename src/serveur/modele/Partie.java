/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.modele;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import serveur.bdd.modeleSauvegarde.PartieSauvegarde;
import serveur.commun.Fonctions;
import serveur.modele.carte.Paquet;
import serveur.modele.service.CarteInterface;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PaquetInterface;
import serveur.modele.service.PartieInterface;
import serveur.modele.service.PlateauInterface;

/**
 *
 * @author Arthur
 */

public class Partie extends UnicastRemoteObject implements Serializable, PartieInterface {

	private static final long serialVersionUID = 1L;

	/**
	 * Joueurs de la partie. Le JoueurInterface 4 peut etre null.
	 */
	private JoueurInterface joueur1, joueur2, joueur3, joueur4;

	/**
	 * Id de la partie
	 */
	private int id;
	
	/**
	 * Ressources de la carte
	 */
	private Ressource ressources;

	/**
	 * Indique si la partie a commencé ou non
	 */
	private boolean partieCommence;
	
	/**
	 * Indique si la partie a été chargée ou non
	 */
	private boolean chargee;

	/**
	 * Ordre de jeu de la partie
	 */
	private ArrayList<JoueurInterface> ordreJeu;

	/**
	 * De 1 à 3 si 3 joueurs De 1 à 4 si 4 joueurs
	 */
	private int tour;

	/**
	 * Compte tous les tours
	 */
	private int compteurTourGlobal;

	/**
	 * Plateau de la partie.
	 */
	private PlateauInterface plateau;

	/**
	 * Deck de carte de la partie;
	 */
	private PaquetInterface deck;

	/**
	 * Constructeur de la classe Partie
	 * @param p - plateau de la partie
	 */
	public Partie(PlateauInterface p) throws RemoteException {
		this.ordreJeu = new ArrayList<>();
		this.ressources = new Ressource();
		this.tour = 1;
		this.compteurTourGlobal = 0;
		this.plateau = p;
		this.partieCommence = false;
		this.deck = new Paquet();
	}
	
	/**
	 * Constructeur de la sauvegarde
	 * @param p
	 * @throws RemoteException
	 */
	public Partie(PartieSauvegarde p) throws RemoteException{
		this.joueur1 = new Joueur(p.getJoueurs().get(0));
		this.joueur2 = new Joueur(p.getJoueurs().get(1));
		this.joueur3 = new Joueur(p.getJoueurs().get(2));
		if(p.getJoueurs().size() > 3){
			this.joueur4 = new Joueur(p.getJoueurs().get(3));
		}
		
		this.ordreJeu = Fonctions.transformArrayJoueur(p.getJoueurs());
		this.tour = p.getTour();
		this.compteurTourGlobal = p.getTourGlobal();
		Plateau.getInstance().creerNouveauPlateau(p.getPlateauCourant());
		this.plateau = Plateau.getInstance();
		this.partieCommence = true;
		this.ressources = p.getRessource();
	}
	
	public Partie() throws RemoteException{};
	
	/**
	 * @return la liste de tous les joueur
	 */
	public ArrayList<JoueurInterface> getTousLesJoueurs() throws RemoteException{
		ArrayList<JoueurInterface> aRetourner = new ArrayList<JoueurInterface>();
		if(this.joueur1 != null){
			aRetourner.add(joueur1);
		}
		if(this.joueur2 != null){
			aRetourner.add(joueur2);
		}
		if(this.joueur3 != null){
			aRetourner.add(joueur3);
		}
		if(this.joueur4 != null){
			aRetourner.add(joueur4);
		}
		return aRetourner;
	}
	
	/**
	 * Permet de récupérer un joueur participant à la partie
	 * @return le joueur 1
	 * @throws RemoteException
	 */
	public JoueurInterface getJoueur1() throws RemoteException {
		return joueur1;
	}

	/**
	 * Défini d'un joueur
	 * @param joueur1
	 * @throws RemoteException
	 */
	public void setJoueur1(JoueurInterface joueur1) throws RemoteException {
		this.joueur1 = joueur1;
		this.ordreJeu.add(this.joueur1);
	}

	/**
	 * Permet de récupérer un joueur participant à la partie
	 * @return le joueur 2
	 * @throws RemoteException
	 */
	public JoueurInterface getJoueur2() throws RemoteException {
		return joueur2;
	}

	/**
	 * Défini d'un joueur
	 * @param joueur2
	 * @throws RemoteException
	 */
	public void setJoueur2(JoueurInterface joueur2) throws RemoteException {
		this.joueur2 = joueur2;
		this.ordreJeu.add(this.joueur2);
	}

	/**
	 * Permet de récupérer un joueur participant à la partie
	 * @return le joueur 3
	 * @throws RemoteException
	 */
	public JoueurInterface getJoueur3() throws RemoteException {
		return joueur3;
	}

	/**
	 * Défini d'un joueur
	 * @param joueur3
	 * @throws RemoteException
	 */
	public void setJoueur3(JoueurInterface joueur3) throws RemoteException {
		this.joueur3 = joueur3;
		this.ordreJeu.add(this.joueur3);
	}

	/**
	 * Permet de récupérer un joueur participant à la partie
	 * @return le joueur 4
	 * @throws RemoteException
	 */
	public JoueurInterface getJoueur4() throws RemoteException {
		return joueur4;
	}

	/**
	 * Défini d'un joueur
	 * @param joueur4
	 * @throws RemoteException
	 */
	public void setJoueur4(JoueurInterface joueur4) throws RemoteException {
		this.joueur4 = joueur4;
		this.ordreJeu.add(this.joueur4);
	}

	/**
	 * Permet de savoir si la partie a été chargée ou non
	 * @return true si la partie a été chargée, false sinon
	 */
	public boolean isChargee() throws RemoteException{
		return chargee;
	}

	/**
	 * Permet d'indiquer si la partie a été chargée ou non
	 * @param chargee 
	 */
	public void setChargee(boolean chargee) throws RemoteException{
		this.chargee = chargee;
	}
	
	/**
	 * Getter du boolean pour savoir si la partie a commencé
	 * @return booléen
	 */
	public boolean isPartieCommence() throws RemoteException{
		return partieCommence;
	}

	/**
	 * Setter du boolean pour savoir si la partie as commencé
	 * @param partieCommence - nouveau partieCommence
	 */
	public void setPartieCommence(boolean partieCommence) throws RemoteException{
		this.partieCommence = partieCommence;
	}
	
	/**
	 * Permet de récupérer les ressources de la partie
	 * @return les ressources
	 * @throws RemoteException
	 */
	public Ressource getRessources() throws RemoteException {
		return ressources;
	}

	/**
	 * Définition des ressources
	 * @param ressources
	 * @throws RemoteException
	 */
	public void setRessources(Ressource ressources) throws RemoteException {
		this.ressources = ressources;
	}
	
	/**
	 * @return l'id du joueur
	 * @throws RemoteException
	 */
	public int getId() {
		return id;
	}

	/**
	 * Permet d'indiquer l'id du joueur
	 * @param id - nouvel id
	 * @throws RemoteException
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Permet d'obtenir le tour de la partie
	 * @return
	 * @throws RemoteException
	 */
	public int getTour() throws RemoteException {
		return this.tour;
	}
	
	/**
	 * Permet d'indiquer l'attribut tour de la partie
	 * @param tour - nouveau tour
	 * @throws RemoteException
	 */
	public void setTour(int tour) {
		this.tour = tour;
	}
	
	/**
	 * Récupération de la liste triée des joueurs
	 * @return liste de clients
	 * @throws RemoteException
	 */
	public ArrayList<JoueurInterface> getOrdreJeu() throws RemoteException {
		return ordreJeu;
	}

	/**
	 * Définition d'un nouvel ordre de jeu
	 * @param ordreJeu
	 * @throws RemoteException
	 */
	public void setOrdreJeu(ArrayList<JoueurInterface> ordreJeu) throws RemoteException {
		this.ordreJeu = ordreJeu;
	}
	
	/**
	 * Permet de récupérer le nombre de joueurs de la partie
	 * @return le nombre de joueurs
	 * @throws RemoteException
	 */
	public int getNombreJoueurs() throws RemoteException {
		int compteur = 0;
		if (joueur1 != null) {
			compteur++;
		}
		if (joueur2 != null) {
			compteur++;
		}
		if (joueur3 != null) {
			compteur++;
		}
		if (joueur4 != null) {
			compteur++;
		}
		return compteur;
	}
	
	/**
	 * Permet de récupérer le nombre de joueurs de la partie
	 * @return le nombre de joueurs
	 * @throws RemoteException
	 */
	public JoueurInterface getJoueurTour() throws RemoteException {
		if (tour != 0) {
			return ordreJeu.get(tour - 1);
		} else {
			return ordreJeu.get(tour);
		}
	}
	
	/**
	 * Récupération d'un joueur en fonction de sa couleur
	 * @param couleur
	 * @return le joueur grâce à sa couleur
	 * @throws RemoteException
	 */
	public JoueurInterface getJoueurByCouleur(String couleur) throws RemoteException {
		if (this.joueur1.getCouleur() == couleur) {
			return this.joueur1;
		} else if (this.joueur2.getCouleur() == couleur) {
			return this.joueur2;
		} else if (this.joueur3.getCouleur() == couleur) {
			return this.joueur3;
		} else if (this.joueur4.getCouleur() == couleur) {
			return this.joueur4;
		}
		return null;
	}
	
	/**
	 * Récupération d'un joueur en fonction de son nom
	 * @param nom
	 * @return le joueur grâce à son nom
	 * @throws RemoteException
	 */
	public JoueurInterface getJoueurByName(String nom) throws RemoteException {
		if (this.joueur1.getNomUtilisateur().equals(nom)) {
			return this.joueur1;
		} else if (this.joueur2.getNomUtilisateur().equals(nom)) {
			return this.joueur2;
		} else if (this.joueur3.getNomUtilisateur().equals(nom)) {
			return this.joueur3;
		} else if (this.joueur4.getNomUtilisateur().equals(nom)) {
			return this.joueur4;
		}
		return null;
	}
	
	/**
	 * Récupération du joueur le plus âgé
	 * @return le joueur le plus vieux
	 * @throws RemoteException
	 */
	public JoueurInterface getJoueurLePlusVieux() throws RemoteException {
		return ordreJeu.get(0);
	}
	
	/**
	 * @return le plateau de la partie
	 * @throws RemoteException
	 */
	public PlateauInterface getPlateau() {
		return this.plateau;
	}
	
	/**
	 * Permet de récupérer le nombre de tour qu'il y a eu dans la partie
	 * @return le nombre de tour qu'il y a eu dans la partie
	 */
	public int getCompteurTourGlobal() throws RemoteException {
		return this.compteurTourGlobal;
	}

	/**
	 * Permet de voler la moitier des ressources d'un joueur qui a plus de 7 cartes
	 * @throws RemoteException
	 */
	public HashMap<String, Integer> getNomJoueursVoles() throws RemoteException{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (this.joueur1.getNbCarte()>7) {
			map.put(this.joueur1.getNomUtilisateur(), this.joueur1.getNbCarte()/2);
		}
		if (this.joueur2.getNbCarte()>7) {
			map.put(this.joueur2.getNomUtilisateur(), this.joueur2.getNbCarte()/2);
		}
		if (this.joueur3.getNbCarte()>7) {
			map.put(this.joueur3.getNomUtilisateur(), this.joueur3.getNbCarte()/2);
		}
		if(this.joueur4 != null){
			if (this.joueur4.getNbCarte()>7) {
				map.put(this.joueur4.getNomUtilisateur(), this.joueur4.getNbCarte()/2);
			}
		}
		return map;
	}
	
	/**
	 * Incrémente de 1 le nombre de tour ou le remet à 0 si un tour complet à été effectué
	 * @throws RemoteException
	 */
	public void incrementeTour() throws RemoteException {
		tour = (tour + 1) % (getNombreJoueurs() + 1);
		if (tour == 0)
			tour++;
		compteurTourGlobal++;
	}

	/**
	 * Définition de l'ordre de jeu en fonction de l'âge des participants
	 * @throws RemoteException
	 */
	public void arrangerOrdreTour() throws RemoteException {
		Comparator<JoueurInterface> c = new Comparator<JoueurInterface>() {
			@Override
			public int compare(JoueurInterface j1, JoueurInterface j2) {
				try {
					return j1.compareTo(j2);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			}
		};
		ordreJeu.sort(c);
	}

	/**
	 * Méthode permettant de supprimer un Joueur
	 * @param joueurSupprime - joueur à supprimer
	 * @throws RemoteException
	 */
	public void supprimerJoueur(JoueurInterface joueurSupprime) throws RemoteException {
		for (JoueurInterface js : ordreJeu) {
			if (js.getNomUtilisateur().equals(joueurSupprime.getNomUtilisateur())) {
				ordreJeu.remove(js);
				break;
			}
		}

		this.affecterNullJoueur(joueurSupprime);

	}

	/**
	 * Méthode permettant d'affecter null à un joueur
	 * @param joueurSupprime - joueur à supprimer
	 * @throws RemoteException
	 */
	public void affecterNullJoueur(JoueurInterface joueurSupprime) throws RemoteException {
		if (joueur1 != null && joueur1.getNomUtilisateur().equals(joueurSupprime.getNomUtilisateur())) {
			joueur1 = null;
		} else if (joueur2 != null && joueur2.getNomUtilisateur().equals(joueurSupprime.getNomUtilisateur())) {
			joueur2 = null;
		} else if (joueur3 != null && joueur3.getNomUtilisateur().equals(joueurSupprime.getNomUtilisateur())) {
			joueur3 = null;
		} else {
			joueur4 = null;
		}
	}

	/**
	 * Renvoi la première carte du deck de la partie concernée
	 * @return la première carte du deck de la partie concernée
	 * @throws RemoteException
     */
	public CarteInterface piocheDeck() throws RemoteException{
		return this.deck.pioche();
	}
}

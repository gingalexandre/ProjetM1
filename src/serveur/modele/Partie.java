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
import serveur.modele.service.*;

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

	private int id;
	private Ressource ressources;

	private boolean partieCommence;

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
	 * @param p
	 *            - plateau de la partie
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
	
	public Partie(PartieSauvegarde p) throws RemoteException{
		this.ordreJeu = Fonctions.transformArrayJoueur(p.getJoueurs());
		this.tour = p.getTour();
		this.compteurTourGlobal = p.getTourGlobal();
		this.plateau = new Plateau(p.getPlateauCourant());
		this.partieCommence = true;
	}

	/**
	 * Permet de récupérer le nombre de tour qu'il y a eu dans la partie
	 * 
	 * @return le nombre de tour qu'il y a eu dans la partie
	 */
	public int getCompteurTourGlobal() throws RemoteException {
		return this.compteurTourGlobal;
	}

	/**
	 * @return le nombre de joueurs de la partie
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

	public JoueurInterface getJoueurTour() throws RemoteException {
		if (tour != 0) {
			return ordreJeu.get(tour - 1);
		} else {
			return ordreJeu.get(tour);
		}
	}

	public ArrayList<JoueurInterface> getOrdreJeu() throws RemoteException {
		return ordreJeu;
	}

	public void setOrdreJeu(ArrayList<JoueurInterface> ordreJeu) throws RemoteException {
		this.ordreJeu = ordreJeu;
	}

	public void incrementeTour() throws RemoteException {
		tour = (tour + 1) % (getNombreJoueurs() + 1);
		if (tour == 0)
			tour++;
		compteurTourGlobal++;
	}

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

	public JoueurInterface getJoueur1() throws RemoteException {
		return joueur1;
	}

	public void setJoueur1(JoueurInterface joueur1) throws RemoteException {
		this.joueur1 = joueur1;
		this.ordreJeu.add(this.joueur1);
	}

	public JoueurInterface getJoueur2() throws RemoteException {
		return joueur2;
	}

	public void setJoueur2(JoueurInterface joueur2) throws RemoteException {
		this.joueur2 = joueur2;
		this.ordreJeu.add(this.joueur2);
	}

	public JoueurInterface getJoueur3() throws RemoteException {
		return joueur3;
	}

	public void setJoueur3(JoueurInterface joueur3) throws RemoteException {
		this.joueur3 = joueur3;
		this.ordreJeu.add(this.joueur3);
	}

	public JoueurInterface getJoueur4() throws RemoteException {
		return joueur4;
	}

	public void setJoueur4(JoueurInterface joueur4) throws RemoteException {
		this.joueur4 = joueur4;
		this.ordreJeu.add(this.joueur4);
	}

	public Ressource getRessources() throws RemoteException {
		return ressources;
	}

	public void setRessources(Ressource ressources) throws RemoteException {
		this.ressources = ressources;
	}

	/**
	 * Arrange la liste ordreJeu par ordre d'âge des joueurs
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
	 * @return le JoueurInterface le plus vieux de la partie
	 */
	public JoueurInterface getJoueurLePlusVieux() throws RemoteException {
		return ordreJeu.get(0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public PlateauInterface getPlateau() {
		// TODO Auto-generated method stub
		return this.plateau;
	}

	/**
	 * Méthode permettant de supprimer un Joueur
	 * 
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
	 * Setter de tour
	 */
	public void setTour(int tour) {
		this.tour = tour;
	}

	/**
	 * Getter de tour
	 */
	public int getTour() throws RemoteException {
		return this.tour;
	}

	public boolean isPartieCommence() throws RemoteException{
		return partieCommence;
	}

	public void setPartieCommence(boolean partieCommence) throws RemoteException{
		this.partieCommence = partieCommence;
	}
	
	
	@Override
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
	
	
	public Partie() throws RemoteException{}

	/**
	 * Renvoi la première carte du paquet.
	 * @return
	 * @throws RemoteException
     */
	public CarteInterface piocheDeck() throws RemoteException{
		return this.deck.pioche();
	}

	@Override
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

}

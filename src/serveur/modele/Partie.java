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

import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PartieInterface;

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

	private ArrayList<JoueurInterface> ordreJeu;

	/**
	 * De 1 à 3 si 3 joueurs De 1 à 4 si 4 joueurs
	 */
	private int tour;

	/**
	 * @param plateau
	 *            - plateau de la partie
	 */
	public Partie() throws RemoteException {
		this.ordreJeu = new ArrayList<>();
		this.ressources = new Ressource();
		this.tour = 1;
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
		return ordreJeu.get(tour - 1);
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

}

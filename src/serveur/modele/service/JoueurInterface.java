package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import serveur.modele.Joueur;
import serveur.modele.carte.Carte;

public interface JoueurInterface extends Remote{
	
	HashMap<Integer, Integer> getStockRessource() throws RemoteException;
	void setStockRessource(HashMap<Integer, Integer> stockRessource) throws RemoteException;
	int getId() throws RemoteException;
	boolean isPret() throws RemoteException;
	void setPret(boolean pret) throws RemoteException;
	void construireRoute() throws RemoteException;
	void construireColonie() throws RemoteException;
	void construireVille() throws RemoteException;
	void ajoutRessource(int typeRessource, int value) throws RemoteException;
	void supprimerRessource(int typeRessource, int value) throws RemoteException;
	void echangerRessource(int typeRessourceDonnee, int quantiteDonnee, Joueur partenaireEchange,
			int typeRessourceRecup, int quantiteRecup)  throws RemoteException;
	void jouerCarteDevelopement(Carte carte)  throws RemoteException;
	void joueurCarteSpeciale(Carte carte)  throws RemoteException;
	void ajouterPointVictoire()  throws RemoteException;
	String getNomUtilisateur()  throws RemoteException;
	void setNomUtilisateur(String nomUtilisateur)  throws RemoteException;
	Date getDateDeNaissance()  throws RemoteException;
	void setDateDeNaissance(Date dateDeNaissance)  throws RemoteException;
	String getCouleur()  throws RemoteException;
	void setCouleur(String couleur)  throws RemoteException;
	int getNbColonie()  throws RemoteException;
	void setNbColonie(int nbColonie)  throws RemoteException;
	int getNbVille()  throws RemoteException;
	void setNbVille(int nbVille)  throws RemoteException;
	boolean encoreAssezVille()  throws RemoteException;
	boolean encoreAssezColonie()  throws RemoteException;
	boolean encoreAssezRoute()  throws RemoteException;
	int getPointVictoire()  throws RemoteException;
	void setPointVictoire(int pointVictoire)  throws RemoteException;
	ArrayList<Carte> getCartes()  throws RemoteException;
	void addCartes(Carte carte)  throws RemoteException;
	int getNbRoute()  throws RemoteException;
	void setNbRoute(int nbRoute)  throws RemoteException;	
	int compareTo(JoueurInterface j) throws RemoteException;
}

package serveur.modele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import serveur.modele.Joueur;

/**
 * 
 * @author jerome
 */
public interface JoueurInterface extends Remote {

	/**
	 * Permet d'obtenir le stock de ressources du joueur
	 * @return le stock de ressources du joueur
	 * @throws RemoteException
	 */
	HashMap<Integer, Integer> getStockRessource() throws RemoteException;
	
	/**
	 * Permet de spécifier le stock de ressources du joueur
	 * @param stockRessource - stock de ressources du joueur
	 * @throws RemoteException
	 */
	void setStockRessource(HashMap<Integer, Integer> stockRessource) throws RemoteException;
	
	/**
	 * Permet d'obtenir l'id du joueur
	 * @return l'id du joueur
	 * @throws RemoteException
	 */
	int getId() throws RemoteException;
	
	/**
	 * Permet de spécifier l'id du joueur
	 * @param i - id du joueur
	 */
	void setId(int i) throws RemoteException;

	/**
	 * Permet d'obtenir le nombre de colonies du joueur
	 * @return le nombre de colonies du joueur
	 * @throws RemoteException
	 */
	int getNbColonie() throws RemoteException;
	
	/**
	 * Permet de spécifier le nombre de colonies du joueur
	 * @param nbColonie -  nombre de colonies du joueur
	 * @throws RemoteException
	 */
	void setNbColonie(int nbColonie) throws RemoteException;
	
	/**
	 * Permet d'obtenir le nombre de carte du joueur
	 * @return le nombre de carte du joueur
	 * @throws RemoteException
	 */
	int getNbCarte() throws RemoteException;
	
	/**
	 * Permet d'obtenir le nombre de villes du joueur
	 * @return le nombre de colonies du joueur
	 * @throws RemoteException
	 */
	int getNbVille() throws RemoteException;
	
	/**
	 * Permet de spécifier le nombre de villes du joueur
	 * @param nbVille - nombre de villes du joueur
	 * @throws RemoteException
	 */
	void setNbVille(int nbVille) throws RemoteException;
	
	/**
	 * Permet de savoir si un joueur est prêt
	 * @return si le joueur est prêt ou non
	 * @throws RemoteException
	 */
	boolean getPret() throws RemoteException;

	/**
	 * Permet de spécifier si le joueur est prêt ou non
	 * @param pret - true si le joueur est pret, false sinon
	 * @throws RemoteException
	 */
	void setPret(boolean pret) throws RemoteException;

	/**
	 * Permet d'obtenir le nom d'utilisateur du joueur
	 * @return le nom d'utilisateur du joueur
	 * @throws RemoteException
	 */
	String getNomUtilisateur() throws RemoteException;
	
	/**
	 * Permet de spécifier le nom d'utilisateur du joueur
	 * @param nomUtilisateur - nom d'utilisateur
	 * @throws RemoteException
	 */
	void setNomUtilisateur(String nomUtilisateur) throws RemoteException;
	
	/**
	 * Permet d'obtenir la date de naissance du joueur
	 * @return la date de naissance du joueur
	 * @throws RemoteException
	 */
	Date getDateDeNaissance() throws RemoteException;
	
	/**
	 * Permet de spécifier la date de naissance du joueur
	 * @param dateDeNaissance - date de naissance 
	 * @throws RemoteException
	 */
	void setDateDeNaissance(Date dateDeNaissance) throws RemoteException;
	
	/**
	 * Permet d'obtenir la couleur du joueur
	 * @return la couleur du joueur
	 * @throws RemoteException
	 */
	String getCouleur() throws RemoteException;
	
	/**
	 * Permet de spécifier la couleur du joueur
	 * @param couleur - couleur du joueur
	 * @throws RemoteException
	 */
	void setCouleur(String couleur) throws RemoteException;
	
	/**
	 * Permet d'obtenir le nombre de points de victoire du joueur
	 * @return le nombre de points de victoire du joueur
	 * @throws RemoteException
	 */
	int getPointVictoire() throws RemoteException;
	
	/**
	 * Permet de spécifier le nombre de point de victoire du joueur
	 * @param pointVictoire - point de victoire du joueur
	 * @throws RemoteException
	 */
	void setPointVictoire(int pointVictoire) throws RemoteException;
	
	/**
	 * Permet d'obtenir la liste des cartes du joueur
	 * @return la liste des cartes du joueur
	 * @throws RemoteException
	 */
	ArrayList<CarteInterface> getCartes() throws RemoteException;
	
	/**
	 * Permet d'ajouter une carte à la liste des cartes du joueur
	 * @param carte - carte à ajouter
	 * @throws RemoteException
	 */
	void addCarte(CarteInterface carte) throws RemoteException;

	/**
	 * Permet de récupérer une carte.
	 * @param index index de la carte a prendre
	 * @return Une carte
	 * @throws RemoteException
     */
	public CarteInterface getCarte(int index)  throws RemoteException;

	/**
	 * Permet de supprimer une carte.
	 * @param index index de la carte a supprimer
	 * @throws RemoteException
	 */
	public void removeCarte(int index)  throws RemoteException;
	
	/**
	 * Permet d'obtenir le nombre de routes du joueur
	 * @return le nombre de routes du joueur
	 * @throws RemoteException
	 */
	int getNbRoute() throws RemoteException;
	
	/**
	 * Permet de spécifier le nombre de routes du joueur
	 * @param nbRoute - nombre de routes du joueur
	 * @throws RemoteException
	 */
	void setNbRoute(int nbRoute) throws RemoteException;	
	
	/**
	 * Permet au joueur de construire une route
	 * @throws RemoteException
	 */
	void construireRoute() throws RemoteException;
	
	/**
	 * Permet au joueur de construire une colonie
	 * @throws RemoteException
	 */
	void construireColonie() throws RemoteException;
	
	/**
	 * Permet au joueur de construire une ville
	 * @throws RemoteException
	 */
	void construireVille() throws RemoteException;
	
	/**
	 * Permet au joueur d'ajouter une ressources
	 * @param typeRessource - type de la ressource
	 * @param value - quantite de la ressource à ajouter
	 * @throws RemoteException
	 */
	void ajoutRessource(int typeRessource, int value) throws RemoteException;
	
	/**
	 * Permet au joueur de supprimer une ressources
	 * @param typeRessource - type de la ressource
	 * @param value - quantite de la ressource à supprimer
	 * @throws RemoteException
	 */
	void supprimerRessource(int typeRessource, int value) throws RemoteException;
	
	/**
	 * Permet au joueur d'échanger une ressource
	 * @param typeRessourceDonnee - type de la ressource donnée
	 * @param quantiteDonnee - quantité donnée 
	 * @param partenaireEchange - joueur avec qui échanger
	 * @param typeRessourceRecup - type de la ressource récupérée
	 * @param quantiteRecup - quantité de la ressource récupérée
	 * @throws RemoteException
	 */
	void echangerRessource(int typeRessourceDonnee, int quantiteDonnee, Joueur partenaireEchange, int typeRessourceRecup, int quantiteRecup)  throws RemoteException;
	
	/**
	 * Permet au joueur de jouer une carte développement 
	 * @param carte - carte à jouer
	 * @throws RemoteException
	 */
	void jouerCarteDevelopement(CarteInterface carte) throws RemoteException;
	
	/**
	 * Permet au joueur de jouer une carte spéciale 
	 * @param carte - carte à jouer
	 * @throws RemoteException
	 */
	void joueurCarteSpeciale(CarteInterface carte) throws RemoteException;
	
	/**
	 * Permet au joueur d'ajouter des points de victoire
	 * @throws RemoteException
	 */
	void ajouterPointVictoire() throws RemoteException;
	
	/**
	 * Permet de suppri;er un point de victoire qu joueur
	 * @throws RemoteException
	 */
	void supprimerPointVictoire() throws RemoteException;
	
	/**
	 * Permet de savoir si le joueur a encore assez de villes
	 * @return true si le joueur a encore assez de villes, false sinon
	 * @throws RemoteException
	 */
	boolean encoreAssezVille() throws RemoteException;
	
	/**
	 * Permet de savoir si le joueur a encore assez de colonies
	 * @return true si le joueur a encore assez de colonies, false sinon
	 * @throws RemoteException
	 */
	boolean encoreAssezColonie() throws RemoteException;
	
	/**
	 * Permet de savoir si le joueur a encore assez de routes
	 * @return true si le joueur a encore assez de routes, false sinon
	 * @throws RemoteException
	 */
	boolean encoreAssezRoute() throws RemoteException;
	
	/**
	 * Permet de comparer deux joueurs en fonction de leur date de naissance
	 * @param j - Joueur à comparer
	 * @return 1, 0 ou -1
	 * @throws RemoteException
	 */
	int compareTo(JoueurInterface j) throws RemoteException;

	/**
	 * Permet d'incrémenter le nombre de chevalier jouer par un joueur.
	 * @throws RemoteException
     */

	public void incrementeGuerrier() throws RemoteException;

	/**
	 * Permet de savoir si le joueur a l'armée la plus puissante
	 * @return
	 * @throws RemoteException
     */
	public boolean isArmeeLaPlusPuissante() throws RemoteException ;

	/**
	 * Permet de mettre à jour le paramètre qui indique que le joueur possède la carte de la plus puissante armée
	 * @param armeeLaPlusPuissante
     */
	public void setArmeeLaPlusPuissante(boolean armeeLaPlusPuissante) throws RemoteException ;

	/**
	 * Permet de savoir si le joueur a la plus longue route
	 * @return
	 * @throws RemoteException
     */
	public boolean isRouteLaPlusLongue() throws RemoteException;

	/**
	 * Permet de mettre à jour le paramètre qui indique que le joueur possède la carte de la plus longue route
	 * @param routeLaPlusLongue
	 * @throws RemoteException
     */
	public void setRouteLaPlusLongue(boolean routeLaPlusLongue) throws RemoteException;

	/**
	 * Retourne le nombre de guerrier jouer par un joueur.
	 * @return
	 * @throws RemoteException
	 */
	public int nbGuerrier() throws RemoteException ;

	/**
	 * Permet d'utiliserles cartes a partir de ce tour.
	 */
	void updateCarteJouable() throws RemoteException;


	/**
	 * Permet de récupérer une liste de ports sous forme d'entiers représentant leur type d'échange
	 * @return liste de ports
	 * @throws RemoteException
	 */
	ArrayList<Integer> getPorts() throws RemoteException;

	/**
	 * Getter tailleroutemax pour savoir quelle est la longueur de la plus grande route du joueur
	 * @return taille route max
	 */
	public int getTailleroutemax() throws RemoteException;

	/**
	 * Setter tailleroutemax pour mettre à jour la taille de la plus longue route.
	 * @param value
	 * @throws RemoteException
	 */
	public void setTailleroutemax(int value) throws RemoteException;

	boolean checkAchat(String string) throws RemoteException;

	void faireAchat(String string) throws RemoteException;
}

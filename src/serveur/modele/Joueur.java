/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.modele;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import serveur.bdd.modeleSauvegarde.JoueurSauvegarde;
import serveur.commun.Fonctions;
import serveur.modele.service.CarteInterface;
import serveur.modele.service.JoueurInterface;

/**
 *
 * @author Arthur
 */
public class Joueur extends UnicastRemoteObject implements JoueurInterface, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Id du joueur
	 */
	private int id;
	private static int compteurDeJoueur = 0;

	/**
	 * Pseudo du joueur dans la base de donnees
	 */
	private String nomUtilisateur;

	/**
	 * Date de naissance du joueur dans la base de donnees
	 */
	private Date dateDeNaissance;

	/**
	 * Couleur de jeu du joueur
	 */
	private String couleur;

	/**
	 * Si le joueur est pret a jouer ou non
	 */
	private boolean pret;

	/**
	 * Point de victoires du joueur
	 */
	private int pointVictoire;

	/**
	 * Nombre de cartes en main
	 */
	private int nbCarte;

	/**
	 * Nombre de colonies du joueur
	 */
	private int nbColonie = 5;

	/**
	 * Nombre de villes du joueur
	 */
	private int nbVille = 4;

	/**
	 * Nombre de routes du joueur
	 */
	private int nbRoute = 15;

	/**
	 * Nombre de carte chavlier joué du joueur
	 */
	private int guerrier = 0;

	/**
	 * Nombre de carte chavlier joué du joueur
	 */
	private int tailleroutemax = 0;

	/**
	 * Stock de ressources du joueur
	 */
	private HashMap<Integer, Integer> stockRessource;

	/**
	 * Cartes du joueur
	 */
	private ArrayList<CarteInterface> cartes = new ArrayList<CarteInterface>();

	/**
	 * Points de victoires supplémentaires
	 */
	boolean routeLaPlusLongue, armeeLaPlusPuissante = false;

	private int nbRouteGratuite;

	private boolean estVole;

	/**
	 * Constructeur de joueur Est appele lors de l'ajout d'un proxy sur le
	 * serveur
	 */
	public Joueur() throws RemoteException {
		initialisationAttributs();
	}

	/**
	 * Constructeur de la classe Joueur
	 * 
	 * @param id
	 * @param nomUtilisateur
	 * @param dateDeNaissance
	 * @param couleur
	 * @param pret
	 * @param pointVictoire
	 * @param nbColonie
	 * @param nbVille
	 * @param nbRoute
	 * @param stockRessource
	 * @param cartes
	 * @throws RemoteException
	 */
	public Joueur(int id, String nomUtilisateur, Date dateDeNaissance, String couleur, boolean pret, int pointVictoire,
			int nbColonie, int nbVille, int nbRoute, HashMap<Integer, Integer> stockRessource,
			ArrayList<CarteInterface> cartes) throws RemoteException {
		super();
		this.id = id;
		this.nomUtilisateur = nomUtilisateur;
		this.dateDeNaissance = dateDeNaissance;
		this.couleur = couleur;
		this.pret = pret;
		this.pointVictoire = pointVictoire;
		this.nbColonie = nbColonie;
		this.nbVille = nbVille;
		this.nbRoute = nbRoute;
		this.stockRessource = stockRessource;
		this.cartes = cartes;
	}

	/**
	 * Constructeur de joueur
	 * 
	 * @param nom
	 *            - nom du joueur
	 * @param dateNaissance
	 *            - date de naissance
	 */
	public Joueur(String nom, Date dateNaissance) throws RemoteException {
		this.setNomUtilisateur(nom);
		this.setDateDeNaissance(dateNaissance);
		initialisationAttributs();
	}

	/**
	 * Constructeur pour la sauvegarde
	 * 
	 * @param joueur
	 * @throws RemoteException
	 */
	public Joueur(JoueurSauvegarde joueur) throws RemoteException {
		this.id = joueur.getId();
		this.nomUtilisateur = joueur.getNomUtilisateur();
		this.dateDeNaissance = joueur.getDateDeNaissance();
		this.couleur = joueur.getCouleur();
		this.pret = joueur.isPret();
		this.pointVictoire = joueur.getPointVictoire();
		this.nbColonie = joueur.getNbColonie();
		this.nbVille = joueur.getNbVille();
		this.nbRoute = joueur.getNbRoute();
		this.stockRessource = joueur.getStockRessource();
		this.cartes = Fonctions.transformArrayCarte(joueur.getCartes());
		this.guerrier = joueur.nbGuerrier();
		this.tailleroutemax = joueur.getTailleroutemax();		
		this.estVole = joueur.getEstVole();
	}

	/**
	 * Permet d'initialiser les divers attributs d'un joueur
	 * 
	 * @throws RemoteException
	 */
	private void initialisationAttributs() throws RemoteException {
		compteurDeJoueur++;
		this.id = compteurDeJoueur;
		this.setPointVictoire(0);
		this.stockRessource = new HashMap<Integer, Integer>();
		this.stockRessource.put(Ressource.BOIS, 0);
		this.stockRessource.put(Ressource.BLE, 0);
		this.stockRessource.put(Ressource.ARGILE, 0);
		this.stockRessource.put(Ressource.MINERAIE, 0);
		this.stockRessource.put(Ressource.LAINE, 0);
		this.nbColonie = 5;
		this.nbRoute = 15;
		this.nbVille = 4;
		this.nbRouteGratuite = 0;
	}

	/**
	 * @see serveur.modele.service.JoueurInterface#getNbCarte()
	 */
	public int getNbCarte() {
		int nbCarte = 0;
		if (this.stockRessource.get(Ressource.ARGILE) != null) {
			nbCarte += this.stockRessource.get(Ressource.ARGILE);
		}
		if (this.stockRessource.get(Ressource.BOIS) != null) {
			nbCarte += this.stockRessource.get(Ressource.BOIS);
		}
		if (this.stockRessource.get(Ressource.BLE) != null) {
			nbCarte += this.stockRessource.get(Ressource.BLE);
		}
		if (this.stockRessource.get(Ressource.MINERAIE) != null) {
			nbCarte += this.stockRessource.get(Ressource.MINERAIE);
		}
		if (this.stockRessource.get(Ressource.LAINE) != null) {
			nbCarte += this.stockRessource.get(Ressource.LAINE);
		}
		return nbCarte;
	}

	/**
	 * @return true si le joueur à la route la plus longue, false sinon
	 */
	public boolean isRouteLaPlusLongue() {
		return routeLaPlusLongue;
	}

	/**
	 * Place la valeur de l'attribut routeLaPlusLongue
	 * 
	 * @param routeLaPlusLongue
	 */
	public void setRouteLaPlusLongue(boolean routeLaPlusLongue) {
		this.routeLaPlusLongue = routeLaPlusLongue;
	}

	/**
	 * @return true si le joueur à la route la plus longue, false sinon
	 */
	public boolean isArmeeLaPlusPuissante() {
		return armeeLaPlusPuissante;
	}

	/**
	 * Place la valeur de l'attribut armeeLaPlusPuissante
	 * 
	 * @param armeeLaPlusPuissante
	 */
	public void setArmeeLaPlusPuissante(boolean armeeLaPlusPuissante) {
		this.armeeLaPlusPuissante = armeeLaPlusPuissante;
	}

	/**
	 * Constructeur de joueur
	 * 
	 * @param nom
	 *            - nom du joueur
	 */
	public Joueur(String nom) throws RemoteException {
		this.setNomUtilisateur(nom);
		initialisationAttributs();
	}

	/**
	 * Permet d'obtenir le stock de ressources du joueur
	 * 
	 * @return le stock de ressources du joueur
	 */
	public HashMap<Integer, Integer> getStockRessource() throws RemoteException {
		return this.stockRessource;
	}

	/**
	 * Permet de spécifier le stock de ressources du joueur
	 * 
	 * @param stockRessource
	 *            - stock de ressources du joueur
	 * @throws RemoteException
	 */
	public void setStockRessource(HashMap<Integer, Integer> stockRessource) throws RemoteException {
		this.stockRessource = stockRessource;
	}

	/**
	 * Permet d'obtenir l'id du joueur
	 * 
	 * @return l'id du joueur
	 * @throws RemoteException
	 */
	public int getId() throws RemoteException {
		return this.id;
	}

	/**
	 * Permet de spécifier l'id du joueur
	 * 
	 * @param id
	 *            - id du joueur
	 */
	public void setId(int id) throws RemoteException {
		this.id = id;
	}

	/**
	 * @return si le joueur est pret a jouer ou non
	 */
	public boolean getPret() throws RemoteException {
		return pret;
	}

	/**
	 * Permet de recuperer la valeur de l'attribut pret
	 * 
	 * @param pret
	 */
	public void setPret(boolean pret) throws RemoteException {
		this.pret = pret;
	}

	/**
	 * Permet d'obtenir le nom d'utilisateur du joueur
	 * 
	 * @return le nom d'utilisateur du joueur
	 * @throws RemoteException
	 */
	public String getNomUtilisateur() throws RemoteException {
		return nomUtilisateur;
	}

	/**
	 * Permet de spécifier le nom d'utilisateur du joueur
	 * 
	 * @param nomUtilisateur
	 *            - nom d'utilisateur
	 * @throws RemoteException
	 */
	public void setNomUtilisateur(String nomUtilisateur) throws RemoteException {
		this.nomUtilisateur = nomUtilisateur;
	}

	/**
	 * Permet d'obtenir la date de naissance du joueur
	 * 
	 * @return la date de naissance du joueur
	 * @throws RemoteException
	 */
	public Date getDateDeNaissance() throws RemoteException {
		return dateDeNaissance;
	}

	/**
	 * Permet de spécifier la date de naissance du joueur
	 * 
	 * @param dateDeNaissance
	 *            - date de naissance
	 * @throws RemoteException
	 */
	public void setDateDeNaissance(Date dateDeNaissance) throws RemoteException {
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * Permet d'obtenir la couleur du joueur
	 * 
	 * @return la couleur du joueur
	 * @throws RemoteException
	 */
	public String getCouleur() throws RemoteException {
		return this.couleur;
	}

	/**
	 * Permet de spécifier la couleur du joueur
	 * 
	 * @param couleur
	 *            - couleur du joueur
	 * @throws RemoteException
	 */
	public void setCouleur(String couleur) throws RemoteException {
		this.couleur = couleur;
	}

	/**
	 * Permet d'obtenir le nombre de colonies du joueur
	 * 
	 * @return le nombre de colonies du joueur
	 * @throws RemoteException
	 */
	public int getNbColonie() throws RemoteException {
		return nbColonie;
	}

	/**
	 * Permet de spécifier le nombre de colonies du joueur
	 * 
	 * @param nbColonie
	 *            - nombre de colonies du joueur
	 * @throws RemoteException
	 */
	public void setNbColonie(int nbColonie) throws RemoteException {
		this.nbColonie = nbColonie;
	}

	/**
	 * Permet d'obtenir le nombre de villes du joueur
	 * 
	 * @return le nombre de colonies du joueur
	 * @throws RemoteException
	 */
	public int getNbVille() throws RemoteException {
		return nbVille;
	}

	/**
	 * Permet de spécifier le nombre de villes du joueur
	 * 
	 * @param nbVille
	 *            - nombre de villes du joueur
	 * @throws RemoteException
	 */
	public void setNbVille(int nbVille) throws RemoteException {
		this.nbVille = nbVille;
	}

	/**
	 * Permet d'obtenir le nombre de points de victoire du joueur
	 * 
	 * @return le nombre de points de victoire du joueur
	 * @throws RemoteException
	 */
	public int getPointVictoire() throws RemoteException {
		return pointVictoire;
	}

	/**
	 * Permet de spécifier le nombre de point de victoire du joueur
	 * 
	 * @param pointVictoire
	 *            - point de victoire du joueur
	 * @throws RemoteException
	 */
	public void setPointVictoire(int pointVictoire) throws RemoteException {
		this.pointVictoire = pointVictoire;
	}

	/**
	 * Permet d'obtenir le nombre de routes du joueur
	 * 
	 * @return le nombre de routes du joueur
	 * @throws RemoteException
	 */
	public int getNbRoute() throws RemoteException {
		return nbRoute;
	}

	/**
	 * Permet de spécifier le nombre de routes du joueur
	 * 
	 * @param nbRoute
	 *            - nombre de routes du joueur
	 * @throws RemoteException
	 */
	public void setNbRoute(int nbRoute) throws RemoteException {
		this.nbRoute = nbRoute;
	}

	/**
	 * Permet au joueur d'ajouter une ressources
	 * 
	 * @param typeRessource
	 *            - type de la ressource
	 * @param value
	 *            - quantite de la ressource à ajouter
	 * @throws RemoteException
	 */
	public void ajoutRessource(int typeRessource, int value) throws RemoteException {

		this.stockRessource.put(typeRessource, this.stockRessource.get(typeRessource) + value);

	}

	/**
	 * Permet au joueur de supprimer une ressources
	 * 
	 * @param typeRessource
	 *            - type de la ressource
	 * @param value
	 *            - quantite de la ressource à supprimer
	 * @throws RemoteException
	 */
	public void supprimerRessource(int typeRessource, int value) throws RemoteException {
		this.stockRessource.put(typeRessource, this.stockRessource.get(typeRessource) - value);
	}

	/**
	 * Permet au joueur d'échanger une ressource
	 * 
	 * @param typeRessourceDonnee
	 *            - type de la ressource donnée
	 * @param quantiteDonnee
	 *            - quantité donnée
	 * @param partenaireEchange
	 *            - joueur avec qui échanger
	 * @param typeRessourceRecup
	 *            - type de la ressource récupérée
	 * @param quantiteRecup
	 *            - quantité de la ressource récupérée
	 * @throws RemoteException
	 */
	public void echangerRessource(int typeRessourceDonnee, int quantiteDonnee, Joueur partenaireEchange,
			int typeRessourceRecup, int quantiteRecup) throws RemoteException {

	}

	/**
	 * Permet au joueur d'ajouter des points de victoire
	 * 
	 * @throws RemoteException
	 */
	public void ajouterPointVictoire() throws RemoteException {
		this.pointVictoire++;
	}

	/**
	 * Permet de supprimer un point de victoire qu joueur
	 * 
	 * @throws RemoteException
	 */
	public void supprimerPointVictoire() throws RemoteException {
		this.pointVictoire--;
	}

	/**
	 * Permet au joueur de jouer une carte développement
	 * 
	 * @param carte
	 *            - carte à jouer
	 * @throws RemoteException
	 */
	public void jouerCarteDevelopement(CarteInterface carte) throws RemoteException {

	}

	/**
	 * Permet au joueur de jouer une carte spéciale
	 * 
	 * @param carte
	 *            - carte à jouer
	 * @throws RemoteException
	 */
	public void joueurCarteSpeciale(CarteInterface carte) throws RemoteException {

	}

	/**
	 * Permet de savoir si le joueur a encore assez de villes
	 * 
	 * @return true si le joueur a encore assez de villes, false sinon
	 * @throws RemoteException
	 */
	public boolean encoreAssezVille() throws RemoteException {
		return (this.nbVille > 0);
	}

	/**
	 * Permet de savoir si le joueur a encore assez de colonies
	 * 
	 * @return true si le joueur a encore assez de colonies, false sinon
	 * @throws RemoteException
	 */
	public boolean encoreAssezColonie() throws RemoteException {
		return (this.nbVille > 0);
	}

	/**
	 * Permet de savoir si le joueur a encore assez de routes
	 * 
	 * @return true si le joueur a encore assez de routes, false sinon
	 * @throws RemoteException
	 */
	public boolean encoreAssezRoute() throws RemoteException {
		return (this.nbRoute > 0);
	}

	/**
	 * Permet d'obtenir la liste des cartes du joueur
	 * 
	 * @return la liste des cartes du joueur
	 * @throws RemoteException
	 */
	public ArrayList<CarteInterface> getCartes() throws RemoteException {
		return cartes;
	}

	/**
	 * Permet d'ajouter une carte à la liste des cartes du joueur
	 * 
	 * @param carte
	 *            - carte à ajouter
	 * @throws RemoteException
	 */
	public void addCarte(CarteInterface carte) throws RemoteException {
		this.cartes.add(carte);
	}

	/**
	 * Permet de récupérer une carte.
	 * 
	 * @param index
	 *            index de la carte a prendre
	 * @return Une carte
	 * @throws RemoteException
	 */
	public CarteInterface getCarte(int index) throws RemoteException {
		return this.cartes.get(index);
	}

	/**
	 * Permet de supprimer une carte.
	 * 
	 * @param index
	 *            index de la carte a supprimer
	 * @throws RemoteException
	 */
	public void removeCarte(int index) throws RemoteException {
		this.cartes.remove(index);
	}

	/**
	 * Incrémente le nombre qui permet de savoir combien de carte chevalier le
	 * joueur à jouer. Nécessaire au calcul pour la plus grande armée.
	 * 
	 * @throws RemoteException
	 */
	public void incrementeGuerrier() throws RemoteException {
		this.guerrier++;
	}

	/**
	 * Retourne le nombre de guerrier jouer par un joueur.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public int nbGuerrier() throws RemoteException {
		return this.guerrier;
	}

	@Override
	public int compareTo(JoueurInterface j) throws RemoteException {
		if (this.dateDeNaissance.after(j.getDateDeNaissance())) {
			return 1;
		} else if (this.dateDeNaissance.before(j.getDateDeNaissance())) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * Méthode equals
	 */
	public boolean equals(Object o) {
		return o instanceof Joueur && ((Joueur) o).id == this.id;
	}

	/**
	 * Decremente le nombre de Routes restantes du joueur
	 * 
	 * @throws RemoteException
	 */
	public void construireRoute() throws RemoteException {
		boolean isInitPhase = this.nbRoute > 13;
		this.nbRoute--;
		if (!isInitPhase && nbRouteGratuite == 0) {
			faireAchat("Route");
		} else {
			if (nbRouteGratuite > 0)
				nbRouteGratuite--;
		}
	}

	/**
	 * Decremente le nombre de Colonies restantes du joueur
	 * 
	 * @throws RemoteException
	 */
	public void construireColonie() throws RemoteException {
		boolean isInitPhase = this.nbRoute > 13;
		this.nbColonie--;
		if (!isInitPhase)
			faireAchat("Colonie");
	}

	/**
	 * Decremente le nombre de Villes restantes du joueur et incremente le
	 * nombre de Colonie
	 * 
	 * @throws RemoteException
	 */
	public void construireVille() throws RemoteException {
		this.nbColonie++;
		this.nbVille--;
		faireAchat("Ville");
	}

	/**
	 * Méthode permettant de decrementer les ressources pour un achat donner en
	 * parametre
	 * 
	 * @param str
	 *            Objet acheter etant dans l'ensemble suivant {"Route" | "Ville"
	 *            | "Colonie" | "Developpement"}
	 */
	public void faireAchat(String str) throws RemoteException {
		switch (str) {
		case "Route":
			// Bois + argile
			this.supprimerRessource(Ressource.ARGILE, 1);
			this.supprimerRessource(Ressource.BOIS, 1);
			break;
		case "Ville":
			// 2 blé 3 pierre
			this.supprimerRessource(Ressource.BLE, 2);
			this.supprimerRessource(Ressource.MINERAIE, 3);
			break;
		case "Colonie":
			// bois argile blé laine
			this.supprimerRessource(Ressource.BLE, 1);
			this.supprimerRessource(Ressource.ARGILE, 1);
			this.supprimerRessource(Ressource.BOIS, 1);
			this.supprimerRessource(Ressource.LAINE, 1);
			break;
		case "Developpement":
			// blé caillou laine
			this.supprimerRessource(Ressource.LAINE, 1);
			this.supprimerRessource(Ressource.BLE, 1);
			this.supprimerRessource(Ressource.MINERAIE, 1);
		}
	}

	/**
	 * Permet d'utiliserles cartes a partir de ce tour.
	 */
	public void updateCarteJouable() throws RemoteException {
		for (CarteInterface ci : this.getCartes()) {
			ci.setUtilisable(true);
		}
	}

	/**
	 * Permet de récupérer une liste de ports sous forme d'entiers représentant
	 * leur type d'échange
	 * 
	 * @return liste de ports
	 * @throws RemoteException
	 */
	@Override
	public ArrayList<Integer> getPorts() throws RemoteException {
		return Plateau.getPortsJoueur(this.nomUtilisateur);
	}

	/**
	 * Getter tailleroutemax pour savoir quelle est la longueur de la plus
	 * grande route du joueur
	 * 
	 * @return taille route max
	 */
	public int getTailleroutemax() throws RemoteException {
		return tailleroutemax;
	}

	/**
	 * Setter tailleroutemax pour mettre à jour la taille de la plus longue
	 * route.
	 * 
	 * @param value
	 * @throws RemoteException
	 */
	public void setTailleroutemax(int value) throws RemoteException {
		tailleroutemax = value;
	}

	/**
	 * Méthode permettant de verifier les ressources du joueur pour effectuer un
	 * achat
	 * 
	 * @param string
	 *            Objet que l'on veut acheter dans l'ensemble suivant {"Route" |
	 *            "Ville" | "Colonie" | "Developpement"}
	 */
	public boolean checkAchat(String string) throws RemoteException {
		switch (string) {
		case "Route":
			// Bois + argile
			return (getNbRoute() > 0 && stockRessource.get(Ressource.ARGILE) > 0
					&& stockRessource.get(Ressource.BOIS) > 0 || (nbRouteGratuite > 0));
		case "Ville":
			// 2 blé 3 pierre
			return getNbVille() > 0 && stockRessource.get(Ressource.BLE) > 1
					&& stockRessource.get(Ressource.MINERAIE) > 2;
		case "Colonie":
			return getNbColonie() > 0 && stockRessource.get(Ressource.BOIS) > 0 && stockRessource.get(Ressource.BLE) > 0
					&& stockRessource.get(Ressource.ARGILE) > 0 && stockRessource.get(Ressource.LAINE) > 0;
		// bois argile blé laine
		case "Developpement":
			// blé caillou laine
			return stockRessource.get(Ressource.BLE) > 0 && stockRessource.get(Ressource.MINERAIE) > 0
					&& stockRessource.get(Ressource.LAINE) > 0;
		}
		return false;
	}

	@Override
	public void setNbRouteGratuite(int i) throws RemoteException {
		nbRouteGratuite=i;
	}

	public static int getCompteurDeJoueur() throws RemoteException {
		return compteurDeJoueur;
	}

	public static void setCompteurDeJoueur(int compteurDeJoueur) throws RemoteException {
		Joueur.compteurDeJoueur = compteurDeJoueur;
	}

	public int getGuerrier() throws RemoteException {
		return guerrier;
	}

	public void setGuerrier(int guerrier) throws RemoteException {
		this.guerrier = guerrier;
	}

	public int getNbRouteGratuite() throws RemoteException {
		return nbRouteGratuite;
	}

	public void setNbCarte(int nbCarte) throws RemoteException {
		this.nbCarte = nbCarte;
	}

	public void setCartes(ArrayList<CarteInterface> cartes) throws RemoteException {
		this.cartes = cartes;
	}

	public boolean getEstVole() throws RemoteException {
		return this.estVole;
	}

	public void setEstVole(boolean estVole) throws RemoteException {
		this.estVole = estVole;
	}

}

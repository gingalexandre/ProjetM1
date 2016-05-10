package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import serveur.modele.Message;
import serveur.modele.Ressource;
import serveur.reseau.proxy.JoueurServeur;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

/**
*
* @author Arthur
*/
public class EchangeController implements Initializable {

	@FXML
	private Button boutonPropositionOffre;
	
	@FXML
	private ComboBox<String> choixJoueur;
	
	@FXML
	private ImageView imgBois, imgLaine, imgMineraie, imgArgile, imgBle;
	
	@FXML
	private TextField demandeBois, offreBois, demandeBle, offreBle, demandeMineraie, offreMineraie, demandeArgile, offreArgile, demandeLaine, offreLaine;
	
	@FXML
	private Label message;
	
	/**
	 * HashMap pour les échanges
	 */
	private HashMap<String,Integer> offreDemande;
	
	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;
	
	/**
	 * Proxy du joueur
	 */
	private Proxy proxy;
	
	/**
	 * Méthode d'initialisation
	 */
	public void initialize(URL location, ResourceBundle resources) {
		//Initialisation du proxy et du serveur
		serveur = ConnexionManager.getStaticServeur();
		proxy = ConnexionManager.getStaticProxy();
		
		try {
			proxy.setEchangeController(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		imgLaine.setImage(new Image(getClass().getResource("/cases/ressourceLaine.png").toExternalForm()));
		imgBois.setImage(new Image(getClass().getResource("/cases/ressourceBois.png").toExternalForm()));
		imgMineraie.setImage(new Image(getClass().getResource("/cases/ressourceMineraie.png").toExternalForm()));
		imgArgile.setImage(new Image(getClass().getResource("/cases/ressourceArgile.png").toExternalForm()));
		imgBle.setImage(new Image(getClass().getResource("/cases/ressourceBle.png").toExternalForm()));
		
		demandeArgile.setText("0");
		demandeBle.setText("0");
		demandeBois.setText("0");
		demandeLaine.setText("0");
		demandeMineraie.setText("0");
		offreArgile.setText("0");
		offreBle.setText("0");
		offreLaine.setText("0");
		offreMineraie.setText("0");
		offreBois.setText("0");
		
		try {
			initComboBox(serveur.getGestionnairePartie().getPartie().getNombreJoueurs());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialisation de la comboBox pour le choix du joueur avec qui échanger
	 * @param nbJoueur
	 * @throws RemoteException
	 */
	@FXML
	private void initComboBox(int nbJoueur) throws RemoteException{
		String nomUtilisateur = proxy.getJoueur().getNomUtilisateur();
		String nomJ1 = serveur.getGestionnairePartie().getPartie().getJoueur1().getNomUtilisateur();
		String nomJ2 = serveur.getGestionnairePartie().getPartie().getJoueur2().getNomUtilisateur();
		String nomJ3 = serveur.getGestionnairePartie().getPartie().getJoueur3().getNomUtilisateur();
		String nomJ4 = "";
		if(nbJoueur>3){
			nomJ4 = serveur.getGestionnairePartie().getPartie().getJoueur1().getNomUtilisateur();
		}
		
		//Ajout des lignes pour les joueurs
		if(!nomJ1.equals(nomUtilisateur)){
			choixJoueur.getItems().add(nomJ1);
		}
		if(!nomJ2.equals(nomUtilisateur)){
			choixJoueur.getItems().add(nomJ2);
		}
		if(!nomJ3.equals(nomUtilisateur)){
			choixJoueur.getItems().add(nomJ3);
		}
		if((!nomJ4.equals(nomUtilisateur))&&(nomJ4!="")){
			choixJoueur.getItems().add(nomJ4);
		}
			
		//Ajout des lignes pour les ports	
		for(int ressource : proxy.getJoueur().getPorts()){
			switch (ressource) {
			case 0 :
				choixJoueur.getItems().add("Port (3 ressources contre 1 ressource)");
				break;
			case Ressource.ARGILE :
				choixJoueur.getItems().add("Port (2 argiles contre 1 ressource)");
				break;
			case Ressource.BOIS :
				choixJoueur.getItems().add("Port (2 bois contre 1 ressource)");
				break;
			case Ressource.MINERAIE :
				choixJoueur.getItems().add("Port (2 minerais contre 1 ressource)");
				break;
			case Ressource.BLE :
				choixJoueur.getItems().add("Port (2 blés contre 1 ressource)");
				break;
			case Ressource.LAINE :
				choixJoueur.getItems().add("Port (2 laines contre 1 ressource)");
				break;
			}
		}
		//Ajout de la ligne pour la banque
		choixJoueur.getItems().add("Banque");
	}
	
	/**
	 * Récupération des données de la matrice d'échange
	 * @throws RemoteException
	 */
	public void recuperationDonneesMatrice() throws RemoteException{
		this.offreDemande = new HashMap<String, Integer>();
		try{
			this.offreDemande.put("dBois",Integer.parseInt(demandeBois.getText()));
			this.offreDemande.put("dBle",Integer.parseInt(demandeBle.getText()));
			this.offreDemande.put("dMineraie",Integer.parseInt(demandeMineraie.getText()));
			this.offreDemande.put("dArgile",Integer.parseInt(demandeArgile.getText()));
			this.offreDemande.put("dLaine",Integer.parseInt(demandeLaine.getText()));
			
			this.offreDemande.put("oBois",Integer.parseInt(offreBois.getText()));
			this.offreDemande.put("oBle",Integer.parseInt(offreBle.getText()));
			this.offreDemande.put("oMineraie",Integer.parseInt(offreMineraie.getText()));
			this.offreDemande.put("oArgile",Integer.parseInt(offreArgile.getText()));
			this.offreDemande.put("oLaine",Integer.parseInt(offreLaine.getText()));
		}
		catch(Exception e){
			message.setText("Invalide !");
		}
	}
	
	/**
	 * Méthode de création et d'envois d'une offre
	 * @throws RemoteException
	 */
	@FXML
	private void proposerOffre() throws RemoteException{
		
		recuperationDonneesMatrice();
		
		String nomJ1 = serveur.getGestionnairePartie().getPartie().getJoueur1().getNomUtilisateur();
		String nomJ2 = serveur.getGestionnairePartie().getPartie().getJoueur2().getNomUtilisateur();
		String nomJ3 = serveur.getGestionnairePartie().getPartie().getJoueur3().getNomUtilisateur();
		String nomJ4 = "";
		int nbJoueur = serveur.getGestionnairePartie().getPartie().getNombreJoueurs();
		if(nbJoueur>3){
			nomJ4 = serveur.getGestionnairePartie().getPartie().getJoueur4().getNomUtilisateur();
		}
		
		if(offreValide(offreDemande)){
			if(choixJoueur.getValue().equals(nomJ1)){
				envoyerPropositionJoueur(serveur.getJoueur(nomJ1));
			}
			if(choixJoueur.getValue().equals(nomJ2)){
				envoyerPropositionJoueur(serveur.getJoueur(nomJ2));
			}
			if(choixJoueur.getValue().equals(nomJ3)){
				envoyerPropositionJoueur(serveur.getJoueur(nomJ3));
			}
			if(nbJoueur>3){
				if(choixJoueur.getValue().equals(nomJ4)){
					envoyerPropositionJoueur(serveur.getJoueur(nomJ4));
				}
			}
			
			if(choixJoueur.getValue().equals(("Port (3 ressources contre 1 ressource)"))){
				echangeAvecPortUniversel();
			}
			if(choixJoueur.getValue().equals(("Port (2 argiles contre 1 ressource)"))){
				echangeAvecPortSpecifique(Ressource.ARGILE);
			}
			if(choixJoueur.getValue().equals(("Port (2 minerais contre 1 ressource)"))){
				echangeAvecPortSpecifique(Ressource.MINERAIE);
			}
			if(choixJoueur.getValue().equals(("Port (2 bois contre 1 ressource)"))){
				echangeAvecPortSpecifique(Ressource.BOIS);
			}
			if(choixJoueur.getValue().equals(("Port (2 blés contre 1 ressource)"))){
				echangeAvecPortSpecifique(Ressource.BLE);
			}
			if(choixJoueur.getValue().equals(("Port (2 laines contre 1 ressource)"))){
				echangeAvecPortSpecifique(Ressource.LAINE);
			}
		
			if(choixJoueur.getValue().equals("Banque")){
				echangeAvecBanque();
			}
			if(choixJoueur.getValue().equals("Choisir un joueur")){
				message.setText("Choisir un joueur");
			}
			MenuController.fenetreEchange.close();
		}
		else{
			message.setText("Invalide !");
		}
	}
	
	/**
	 * Vérifie si l'offre est valide (que le joueur a suffisamment de ressources)
	 * @param offreDemande
	 * @return
	 * @throws RemoteException
	 */
	private boolean offreValide(HashMap<String, Integer> offreDemande) throws RemoteException{
		boolean isValide = true;
		//L'utilisateur n'a pas assez de ressources pour proposer l'offre
		if(((offreDemande.get("oBois") > proxy.getJoueur().getStockRessource().get(Ressource.BOIS))
				|| (offreDemande.get("oBle") > proxy.getJoueur().getStockRessource().get(Ressource.BLE))
				|| (offreDemande.get("oMineraie") > proxy.getJoueur().getStockRessource().get(Ressource.MINERAIE))
				|| (offreDemande.get("oArgile") > proxy.getJoueur().getStockRessource().get(Ressource.ARGILE))
				|| (offreDemande.get("oLaine") > proxy.getJoueur().getStockRessource().get(Ressource.LAINE)))){
			isValide = false;
		}
		//Les valeurs sont positives
		else if(((offreDemande.get("oBois") < 0)	|| (offreDemande.get("oBle") < 0) || (offreDemande.get("oMineraie") < 0) || (offreDemande.get("oArgile") < 0) || (offreDemande.get("oLaine") < 0)
				|| (offreDemande.get("dBois") < 0)	|| (offreDemande.get("dBle") < 0) || (offreDemande.get("dMineraie") < 0) || (offreDemande.get("dArgile") < 0) || (offreDemande.get("dLaine") < 0))){
			isValide = false;			
		}
		return isValide;
	}
	
	/**
	 * Procede à l'échange avec la banque de 4 ressources contre 1 spécifique
	 * @throws RemoteException
	 */
	private void echangeAvecBanque() throws RemoteException{
		int nbRessourcesEchangeables = 0;
		String [] listeOffres = {"oBois", "oBle", "oArgile", "oMineraie", "oLaine"};
		
		for(String typeOffre : listeOffres){
			if(offreDemande.get(typeOffre)>=4){
				int nbRessEchangeableCourrant = offreDemande.get(typeOffre)/4;
				nbRessourcesEchangeables += nbRessEchangeableCourrant;
				int ressource = getTypeRessource(typeOffre);
				proxy.getJoueur().supprimerRessource(ressource, 4*nbRessEchangeableCourrant);
			}
		}
		
		String message = echangeOuArnaque(nbRessourcesEchangeables, "la banque");
		
		recuperationGainEchange(nbRessourcesEchangeables);
		
		//Actualisation de l'affichage
		this.proxy.getJoueursController().majRessource();
		serveur.getGestionnaireUI().diffuserGainRessource();
		serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+message));
	}
	
	private void echangeAvecPortUniversel() throws RemoteException{
		int nbRessourcesEchangeables = 0;
		String [] listeOffres = {"oBois", "oBle", "oArgile", "oMineraie", "oLaine"};
		
		for(String typeOffre : listeOffres){
			if(offreDemande.get(typeOffre)>=3){
				int nbRessEchangeableCourrant = offreDemande.get(typeOffre)/3;
				nbRessourcesEchangeables += nbRessEchangeableCourrant;
				int ressource = getTypeRessource(typeOffre);
				proxy.getJoueur().supprimerRessource(ressource, 3*nbRessEchangeableCourrant);
			}
		}
		
		String message = echangeOuArnaque(nbRessourcesEchangeables, "un port");
		
		recuperationGainEchange(nbRessourcesEchangeables);
		
		//Actualisation de l'affichage
		this.proxy.getJoueursController().majRessource();
		serveur.getGestionnaireUI().diffuserGainRessource();
		serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+message));
	}
	
	/**
	 * Procede à l'échange avec un port spécifique
	 * @throws RemoteException
	 */
	private void echangeAvecPortSpecifique(int typePort) throws RemoteException{
		int nbRessourceNecessaires = 0;
		if(typePort==0){
			nbRessourceNecessaires = 3;
		}
		else{
			nbRessourceNecessaires = 2;
		}
		
		String typeOffre = "";
		switch (typePort) {
		case Ressource.ARGILE :
			typeOffre = "oArgile";
			break;
		case Ressource.BOIS :
			typeOffre = "oBois";
			break;
		case Ressource.MINERAIE :
			typeOffre = "oMineraie";
			break;
		case Ressource.BLE :
			typeOffre = "oBle";
			break;
		case Ressource.LAINE :
			typeOffre = "oLaine";
			break;
		}
		
		int nbRessourcesEchangeables = 0;
		if(offreDemande.get(typeOffre)>=nbRessourceNecessaires){
			int nbRessEchangeable = offreDemande.get(typeOffre)/nbRessourceNecessaires;
			nbRessourcesEchangeables = nbRessEchangeable;
			int ressource = getTypeRessource(typeOffre);
			proxy.getJoueur().supprimerRessource(ressource, nbRessourceNecessaires*nbRessEchangeable);
		}
		
		String message = echangeOuArnaque(nbRessourcesEchangeables, "un port");
		
		recuperationGainEchange(nbRessourcesEchangeables);
		
		//Actualisation de l'affichage
		this.proxy.getJoueursController().majRessource();
		serveur.getGestionnaireUI().diffuserGainRessource();
		serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+message));
	}
	
	private void recuperationGainEchange(int nbRessourcesEchangeables) throws RemoteException{
		String [] listeDemandes = {"dBois", "dBle", "dArgile", "dMineraie", "dLaine"};
		
		while(nbRessourcesEchangeables>0){
			for(String typeDemande : listeDemandes){
				while(offreDemande.get(typeDemande)>0){
					int ressource = getTypeRessource(typeDemande);
					proxy.getJoueur().ajoutRessource(ressource, 1);
					nbRessourcesEchangeables--;
					offreDemande.put(typeDemande,offreDemande.get(typeDemande)-1);
				}
			}
		}
	}
	
	private String echangeOuArnaque(int nbRessourcesEchangeables, String avecQui) throws RemoteException{
		if(nbRessourcesEchangeables>0){
			return " vient de faire un échange avec "+avecQui;
		}
		else{
			return " a tenté d'arnarquer "+avecQui;
		}
	}
	
	/**
	 * Permet de savoir de quelle ressource il s'agit
	 * @param offreOuDemande
	 * @return la ressource
	 * @throws RemoteException
	 */
	public int getTypeRessource(String offreOuDemande) throws RemoteException{
		if((offreOuDemande.equals("oBois"))||(offreOuDemande.equals("dBois"))){
			return Ressource.BOIS;
		}
		else if((offreOuDemande.equals("oBle"))||(offreOuDemande.equals("dBle"))){
			return Ressource.BLE;
		}
		else if((offreOuDemande.equals("oMineraie"))||(offreOuDemande.equals("dMineraie"))){
			return Ressource.MINERAIE;
		}
		else if((offreOuDemande.equals("oArgile"))||(offreOuDemande.equals("dArgile"))){
			return Ressource.ARGILE;
		}
		else if((offreOuDemande.equals("oLaine"))||(offreOuDemande.equals("dLaine"))){
			return Ressource.LAINE;
		}
		return 0;
	}
	
	/**
	 * Envois la demande au joueur concerné
	 * @param j
	 * @throws RemoteException
	 */
	private void envoyerPropositionJoueur(JoueurServeur j) throws RemoteException{
		serveur.getGestionnaireUI().diffuserProposition(j, offreDemande, proxy.getJoueur().getNomUtilisateur());
		serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+" a proposé une offre à "+j.getJoueur().getNomUtilisateur()));
	}
}

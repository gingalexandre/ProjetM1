package client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import serveur.modele.Joueur;
import serveur.modele.Message;
import serveur.modele.Ressource;
import serveur.modele.service.JoueurInterface;
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
	
	private Proxy proxy;
	
	private HashMap<String,Integer> offreDemande;
	
	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;
	
	/**
	 * Méthode d'initialisation
	 */
	public void initialize(URL location, ResourceBundle resources) {
		
		//Initialisation du proxy
		proxy = ConnexionManager.getStaticProxy();
		proxy.setEchangeController(this);
		
		imgLaine.setImage(new Image("file:Ressources/ressources/ressource_laine.png"));
		imgBois.setImage(new Image("file:Ressources/ressources/ressource_Bois.png"));
		imgMineraie.setImage(new Image("file:Ressources/ressources/ressource_Mineraie.png"));
		imgArgile.setImage(new Image("file:Ressources/ressources/ressource_Argile.png"));
		imgBle.setImage(new Image("file:Ressources/ressources/ressource_Ble.png"));
		
		serveur = ConnexionManager.getStaticServeur();
		
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
	
	@FXML
	private void initComboBox(int nbJoueur) throws RemoteException{
		
		if(serveur.getGestionnairePartie().getPartie().getJoueur1().getNomUtilisateur()!=proxy.getJoueur().getNomUtilisateur())
		choixJoueur.getItems().add(serveur.getGestionnairePartie().getPartie().getJoueur1().getNomUtilisateur());
		
		if(serveur.getGestionnairePartie().getPartie().getJoueur2().getNomUtilisateur()!=proxy.getJoueur().getNomUtilisateur())
		choixJoueur.getItems().add(serveur.getGestionnairePartie().getPartie().getJoueur2().getNomUtilisateur());
		
		if(serveur.getGestionnairePartie().getPartie().getJoueur3().getNomUtilisateur()!=proxy.getJoueur().getNomUtilisateur())
		choixJoueur.getItems().add(serveur.getGestionnairePartie().getPartie().getJoueur3().getNomUtilisateur());
		
		choixJoueur.getItems().add("Banque");
		
		if(nbJoueur>3){
			if(serveur.getGestionnairePartie().getPartie().getJoueur4().getNomUtilisateur()!=proxy.getJoueur().getNomUtilisateur())
			choixJoueur.getItems().add(serveur.getGestionnairePartie().getPartie().getJoueur4().getNomUtilisateur());
		}
	}
	
	/**
	 * Méthode de création et d'envois d'une offre
	 */
	@FXML
	private void proposerOffre() throws RemoteException{
		
		offreDemande = new HashMap<String, Integer>();
		try{
			offreDemande.put("dBois",Integer.parseInt(demandeBois.getText()));
			offreDemande.put("dBle",Integer.parseInt(demandeBle.getText()));
			offreDemande.put("dMineraie",Integer.parseInt(demandeMineraie.getText()));
			offreDemande.put("dArgile",Integer.parseInt(demandeArgile.getText()));
			offreDemande.put("dLaine",Integer.parseInt(demandeLaine.getText()));
			
			offreDemande.put("oBois",Integer.parseInt(offreBois.getText()));
			offreDemande.put("oBle",Integer.parseInt(offreBle.getText()));
			offreDemande.put("oMineraie",Integer.parseInt(offreMineraie.getText()));
			offreDemande.put("oArgile",Integer.parseInt(offreArgile.getText()));
			offreDemande.put("oLaine",Integer.parseInt(offreLaine.getText()));
			
		}
		catch(Exception e){
			message.setText("N'entrez que des entiers");
		}
		
		//if(offreValide(offreDemande)){
			
			if(choixJoueur.getValue().equals(serveur.getGestionnairePartie().getPartie().getJoueur1().getNomUtilisateur())){
				envoyerPropositionJoueur(serveur.getJoueur(serveur.getGestionnairePartie().getPartie().getJoueur1().getNomUtilisateur()));
			}
			if(choixJoueur.getValue().equals(serveur.getGestionnairePartie().getPartie().getJoueur2().getNomUtilisateur())){
				envoyerPropositionJoueur(serveur.getJoueur(serveur.getGestionnairePartie().getPartie().getJoueur2().getNomUtilisateur()));
			}
			if(choixJoueur.getValue().equals(serveur.getGestionnairePartie().getPartie().getJoueur3().getNomUtilisateur())){
				envoyerPropositionJoueur(serveur.getJoueur(serveur.getGestionnairePartie().getPartie().getJoueur3().getNomUtilisateur()));
			}
			if(serveur.getGestionnairePartie().getPartie().getNombreJoueurs()>3){
				if(choixJoueur.getValue().equals(serveur.getGestionnairePartie().getPartie().getJoueur4().getNomUtilisateur())){
					envoyerPropositionJoueur(serveur.getJoueur(serveur.getGestionnairePartie().getPartie().getJoueur4().getNomUtilisateur()));
				}
			}
			if(choixJoueur.getValue().equals("Banque")){
				echangeAvecBanque();
			}
			if(choixJoueur.getValue().equals("Choisir un joueur")){
				message.setText("Choisir un joueur");
			}
			MenuController.fenetreEchange.close();
		/*}
		else{
			message.setText("Ressources insuffisantes");
		}*/
	}
	
	private boolean offreValide(HashMap<String, Integer> offreDemande) throws RemoteException{
		if(((offreDemande.get("oBois") > proxy.getJoueur().getStockRessource().get(Ressource.BOIS))
				|| (offreDemande.get("oBle") > proxy.getJoueur().getStockRessource().get(Ressource.BLE))
				|| (offreDemande.get("oMineraie") > proxy.getJoueur().getStockRessource().get(Ressource.MINERAIE))
				|| (offreDemande.get("oArgile") > proxy.getJoueur().getStockRessource().get(Ressource.ARGILE))
				|| (offreDemande.get("oLaine") > proxy.getJoueur().getStockRessource().get(Ressource.LAINE)))){
					return false;			
		}
		return true;
	}
	
	private void echangeAvecBanque() throws RemoteException{
		int nbRessourcesEchangeables = 0;
		if(offreDemande.get("oBois")>=4){
			nbRessourcesEchangeables += offreDemande.get("oBois")/4;
		}
		if(offreDemande.get("oBle")>=4){
			nbRessourcesEchangeables += offreDemande.get("oBle")/4;
		}
		if(offreDemande.get("oMineraie")>=4){
			nbRessourcesEchangeables += offreDemande.get("oMineraie")/4;
		}
		if(offreDemande.get("oArgile")>=4){
			nbRessourcesEchangeables += offreDemande.get("oArgile")/4;
		}
		if(offreDemande.get("oLaine")>=4){
			nbRessourcesEchangeables += offreDemande.get("oLaine")/4;
		}
		
		while(nbRessourcesEchangeables>=0){
			while(offreDemande.get("dBois")>=0){
				proxy.getJoueur().ajoutRessource(Ressource.BOIS, 1);
				nbRessourcesEchangeables--;
				offreDemande.put("dBois",offreDemande.get("dBois")-1);
			}
			while(offreDemande.get("dBle")>=0){
				proxy.getJoueur().ajoutRessource(Ressource.BLE, 1);
				nbRessourcesEchangeables--;
				offreDemande.put("dBle",offreDemande.get("dBle")-1);
			}
			while(offreDemande.get("dMineraie")>=0){
				proxy.getJoueur().ajoutRessource(Ressource.MINERAIE, 1);
				nbRessourcesEchangeables--;
				offreDemande.put("dMineraie",offreDemande.get("dMineraie")-1);
			}
			while(offreDemande.get("dArgile")>=0){
				proxy.getJoueur().ajoutRessource(Ressource.ARGILE, 1);
				nbRessourcesEchangeables--;
				offreDemande.put("dArgile",offreDemande.get("dArgile")-1);
			}
			while(offreDemande.get("dLaine")>=0){
				proxy.getJoueur().ajoutRessource(Ressource.LAINE, 1);
				nbRessourcesEchangeables--;
				offreDemande.put("dLaine",offreDemande.get("dLaine")-1);
			}
		}
		
		//Actualisation de l'affichage
		this.proxy.getJoueursController().majRessource();
		serveur.getGestionnaireUI().diffuserGainRessource();
		serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+" vient de faire un échange avec la banque"));
	}
	
	private void envoyerPropositionJoueur(JoueurServeur j) throws RemoteException{
		serveur.getGestionnaireUI().diffuserProposition(j, offreDemande, proxy.getJoueur().getNomUtilisateur());
	}
}

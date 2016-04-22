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
import serveur.modele.Ressource;
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
		
		try {
			initComboBox(serveur.getGestionnairePartie().getPartie().getNombreJoueurs());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Méthode pour fermer la fenêtre des échanges
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
		
		if(offreValide(offreDemande)){
			
			if(choixJoueur.getValue()==serveur.getGestionnairePartie().getPartie().getJoueur1().getNomUtilisateur()){
				//Envoyer demande à ce joueur
			}
			else if(choixJoueur.getValue()==serveur.getGestionnairePartie().getPartie().getJoueur2().getNomUtilisateur()){
				//Envoyer demande à ce joueur
			}
			else if(choixJoueur.getValue()==serveur.getGestionnairePartie().getPartie().getJoueur3().getNomUtilisateur()){
				//Envoyer demande à ce joueur
			}
			else if(choixJoueur.getValue()==serveur.getGestionnairePartie().getPartie().getJoueur4().getNomUtilisateur()){
				//Envoyer demande à ce joueur
			}
			else if(choixJoueur.getValue()=="Banque"){
				echangeAvecBanque();
			}
			else{
				message.setText("Choisir un joueur");
			}
			MenuController.fenetreEchange.close();
		}
		else{
			message.setText("Ressources insuffisantes");
		}
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
	
	private void echangeAvecBanque(){
		//TODO
	}
	
	
}

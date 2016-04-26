package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import serveur.modele.Message;
import serveur.modele.Ressource;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

public class PropositionController implements Initializable {
	
	@FXML
	private Label proposition, valeurs;
	
	@FXML
	private Button accepter, refuser;
	
	private HashMap<String, Integer> offreDemande;
	
	private String expediteur;
	
	private Proxy proxy;
	
	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//Initialisation du proxy
		proxy = ConnexionManager.getStaticProxy();
		proxy.setPropositionController(this);
		
		serveur = ConnexionManager.getStaticServeur();
	}

	public void setPropostion(String proposition) {
		this.expediteur = proposition;
		this.proposition.setText(proposition+" vous propose l'échange suivant : ");
	}
	
	public void accepterOffre() throws RemoteException{
		effectuerEchange();
		serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+" a accepté l'offre"));
		
	}
	
	public void refuserOffre() throws RemoteException{
		serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+" a refusé l'offre"));
	}
	
	public void setValeursText(HashMap<String, Integer>offreDemande){
		this.offreDemande = offreDemande;
		String v = "";
		if((offreDemande.get("dBois")>0)||(offreDemande.get("dBle")>0)||(offreDemande.get("dArgile")>0)||(offreDemande.get("dMineraie")>0)||(offreDemande.get("dLaine")>0)){
			v += "DEMANDE : ";
			if(offreDemande.get("dBois")>0){
				v += offreDemande.get("dBois")+" Bois ";
			}
			if(offreDemande.get("dBle")>0){
				v += offreDemande.get("dBle")+" Ble ";
			}
			if(offreDemande.get("dArgile")>0){
				v += offreDemande.get("dArgile")+" Argile ";
			}
			if(offreDemande.get("dMineraie")>0){
				v += offreDemande.get("dMineraie")+" Minerai ";
			}
			if(offreDemande.get("dLaine")>0){
				v += offreDemande.get("dLaine")+" Laine ";
			}
		}
		if((offreDemande.get("oBois")>0)||(offreDemande.get("oBle")>0)||(offreDemande.get("oArgile")>0)||(offreDemande.get("oMineraie")>0)||(offreDemande.get("oLaine")>0)){
			v += "/ CONTRE : ";
			if(offreDemande.get("oBois")>0){
				v += offreDemande.get("oBois")+" Bois ";
			}
			if(offreDemande.get("oBle")>0){
				v += offreDemande.get("oBle")+" Ble ";
			}
			if(offreDemande.get("oArgile")>0){
				v += offreDemande.get("oArgile")+" Argile ";
			}
			if(offreDemande.get("oMineraie")>0){
				v += offreDemande.get("oMineraie")+" Minerai ";
			}
			if(offreDemande.get("oLaine")>0){
				v += offreDemande.get("oLaine")+" Laine ";
			}
		}
		this.valeurs.setText(v);
	}
	
	private void effectuerEchange() throws RemoteException{
		if((this.offreDemande.get("dBois")>0)||(this.offreDemande.get("dBle")>0)||(this.offreDemande.get("dArgile")>0)||(this.offreDemande.get("dMineraie")>0)||(this.offreDemande.get("dLaine")>0)){
			if(this.offreDemande.get("dBois")>0){
				serveur.getGestionnairePartie().getPartie().getJoueurByName(expediteur).ajoutRessource(Ressource.BOIS,this.offreDemande.get("dBois"));
				proxy.getJoueur().supprimerRessource(Ressource.BOIS,this.offreDemande.get("dBois"));
			}
			if(this.offreDemande.get("dBle")>0){
				serveur.getGestionnairePartie().getPartie().getJoueurByName(expediteur).ajoutRessource(Ressource.BLE,this.offreDemande.get("dBle"));
				proxy.getJoueur().supprimerRessource(Ressource.BLE,this.offreDemande.get("dBle"));
			}
			if(this.offreDemande.get("dArgile")>0){
				serveur.getGestionnairePartie().getPartie().getJoueurByName(expediteur).ajoutRessource(Ressource.ARGILE,this.offreDemande.get("dArgile"));
				proxy.getJoueur().supprimerRessource(Ressource.ARGILE,this.offreDemande.get("dArgile"));
			}
			if(this.offreDemande.get("dMineraie")>0){
				serveur.getGestionnairePartie().getPartie().getJoueurByName(expediteur).ajoutRessource(Ressource.MINERAIE,this.offreDemande.get("dMineraie"));
				proxy.getJoueur().supprimerRessource(Ressource.MINERAIE,this.offreDemande.get("dMineraie"));
			}
			if(this.offreDemande.get("dLaine")>0){
				serveur.getGestionnairePartie().getPartie().getJoueurByName(expediteur).ajoutRessource(Ressource.LAINE,this.offreDemande.get("dLaine"));
				proxy.getJoueur().supprimerRessource(Ressource.LAINE,this.offreDemande.get("dLaine"));	
			}
		}
		
		if((this.offreDemande.get("oBois")>0)||(this.offreDemande.get("oBle")>0)||(this.offreDemande.get("oArgile")>0)||(this.offreDemande.get("oMineraie")>0)||(this.offreDemande.get("oLaine")>0)){
			if(this.offreDemande.get("oBois")>0){
				serveur.getGestionnairePartie().getPartie().getJoueurByName(expediteur).supprimerRessource(Ressource.BOIS,this.offreDemande.get("oBois"));
				proxy.getJoueur().ajoutRessource(Ressource.BOIS,this.offreDemande.get("oBois"));
			}
			if(this.offreDemande.get("oBle")>0){
				serveur.getGestionnairePartie().getPartie().getJoueurByName(expediteur).supprimerRessource(Ressource.BLE,this.offreDemande.get("oBle"));
				proxy.getJoueur().ajoutRessource(Ressource.BLE,this.offreDemande.get("dBle"));
			}
			if(this.offreDemande.get("oArgile")>0){
				serveur.getGestionnairePartie().getPartie().getJoueurByName(expediteur).supprimerRessource(Ressource.ARGILE,this.offreDemande.get("oArgile"));
				proxy.getJoueur().ajoutRessource(Ressource.ARGILE,this.offreDemande.get("oArgile"));
			}
			if(this.offreDemande.get("oMineraie")>0){
				serveur.getGestionnairePartie().getPartie().getJoueurByName(expediteur).supprimerRessource(Ressource.MINERAIE,this.offreDemande.get("oMineraie"));
				proxy.getJoueur().ajoutRessource(Ressource.MINERAIE,this.offreDemande.get("oMineraie"));
			}
			if(this.offreDemande.get("oLaine")>0){
				serveur.getGestionnairePartie().getPartie().getJoueurByName(expediteur).supprimerRessource(Ressource.LAINE,this.offreDemande.get("oLaine"));
				proxy.getJoueur().ajoutRessource(Ressource.LAINE,this.offreDemande.get("oLaine"));	
			}
		}
		//Actualisation de l'affichage
		this.proxy.getJoueursController().majRessource();
		serveur.getGestionnaireUI().diffuserGainRessource();		
	}

}

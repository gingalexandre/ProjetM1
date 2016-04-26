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
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

public class PropositionController implements Initializable {
	
	@FXML
	private Label proposition, valeurs;
	
	@FXML
	private Button accepter, refuser;
	
	public Stage fenetreProposition;
	
	private HashMap<String, Integer> offreDemande;
	
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

	public void setPropostion(String propostion) {
		this.proposition.setText(propostion+" vous propose l'échange suivant : ");
	}
	
	public void accepterOffre() throws RemoteException{
		System.out.println("yolo");
		/*effectuerEchange();
		serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+" a accepté l'offre"));
		this.fenetreProposition.close();*/
	}
	
	public void refuserOffre() throws RemoteException{
		/*serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur()+" a refusé l'offre"));
		this.fenetreProposition.close();*/
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
				v += offreDemande.get("dBle")+" Ble ";
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
	
	private void effectuerEchange(){
		//TODO
	}

}

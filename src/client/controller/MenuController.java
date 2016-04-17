package client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import serveur.modele.Des;
import serveur.modele.Message;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.reseau.Serveur;

public class MenuController implements Initializable {
	
	/**
	 * Attributs concernants les dés
	 */
	private static final String numeroUn = "file:Ressources/des/dice1.png";
	private static final String numeroDeux = "file:Ressources/des/dice2.png";
	private static final String numeroTrois = "file:Ressources/des/dice3.png";
	private static final String numeroQuatre = "file:Ressources/des/dice4.png";
	private static final String numeroCinq = "file:Ressources/des/dice5.png";
	private static final String numeroSix = "file:Ressources/des/dice6.png";
	
	@FXML
	private ImageView de1, de2;
	
	@FXML
	private Button boutonDes;
	
	/**
	 * Pour les échanges
	 */
	@FXML
	private Button boutonEchange;
	
	private Pane page = null;
	public static Stage fenetreEchange;
	
	/**
	 * Proxy client
	 */
	private Proxy proxy;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Initialisation des dés
		de1.setImage(new Image(numeroSix));
		de2.setImage(new Image(numeroSix));
		
		//Initialisation du proxy
		proxy = ConnexionManager.getStaticProxy();
		proxy.setMenuController(this);
		
	}

	/**
	 * Méthodes pour les dés
	 * 
	 * lancerDes()
	 * animationDes()
	 * distributionDes()
	 * notifierLancerDes()
	 */
	public void lancerDes() throws RemoteException {
		boutonDes.setDisable(true);
		Des des = new Des();
		Integer[] resultats = des.lancerDes();
		
		animateDes();
		// Modification des images
		de1.setImage(new Image(distribuerDes(resultats[0])));
		de2.setImage(new Image(distribuerDes(resultats[1])));
		
		notifierLancerDes(resultats);
		extractionRessources(resultats);
		
	}
	
	public void animateDes() {
		RotateTransition rt1 = new RotateTransition(Duration.millis(1000), de1);
	    RotateTransition rt2 = new RotateTransition(Duration.millis(1000), de2);
	    rt1.setByAngle(180*6);
	    rt2.setByAngle(180*6);
	    rt1.play();
	    rt2.play();
	}

	private String distribuerDes(Integer de) {
		switch (de) {
		case 1:
			return numeroUn;
		case 2:
			return numeroDeux;
		case 3:
			return numeroTrois;
		case 4:
			return numeroQuatre;
		case 5:
			return numeroCinq;
		case 6:
			return numeroSix;
		default:
			return null;
		}
	}
	
	/**
	 * Affiche le resultat des dés dans le chat sous forme de message Système
	 * @param Integer[] resultats (résultats des dés)
	 */
	private void notifierLancerDes(Integer[] resultats){
		try{
			// Récupération du serveur en passant par le singleton ConnexionManager
			Serveur serveur = ConnexionManager.getStaticServeur();
			String nomJoueur = proxy.getJoueur().getNomUtilisateur();
			serveur.getGestionnaireUI().diffuserMessage(new Message(nomJoueur+" a lancé les dés : "+resultats[0]+" | "+resultats[1]));
		}
		catch (RemoteException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Distribue à chaque joueurs les ressources associées à la case du numéro tombé
	 * @param Integer[] resultats (résultats des dés)
	 * @throws RemoteException 
	 */
	private void extractionRessources(Integer[] resultats) throws RemoteException{
		Integer caseConsernee = resultats[0]+resultats[1];
		
		//Méthode (retournant le type de ressource) à implémenter
		//int ressource = Plateau.getRessourceCase(caseConcernee);
		
		//Méthode (retournant la liste des noms de joueurs) à implémenter
		//String[] listNom = Plateau.getJoueursCase(caseConcernee);
		
		// Récupération du serveur en passant par le singleton ConnexionManager
		Serveur serveur = ConnexionManager.getStaticServeur();
		
		//Ajout des ressources aux joueurs de la liste
		proxy.getJoueur().ajoutRessource(1, 1);
		
		//Actualisation de l'affichage
		this.proxy.getJoueursController().majRessource();
	}
	
	/**
	 * Méthode pour permettre le lancement de la popup d'échange et laisser EchangeController prendre le relais pour les méthodes 
	 * 
	 */
	@FXML
	public void ouvrirEchange(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/Echange.fxml"));
		try {
			page = (Pane) loader.load();
			fenetreEchange = new Stage();
			fenetreEchange.setTitle("Les Colons de Catanes");
		    Scene scene = new Scene(page,430,500);
		    fenetreEchange.setScene(scene);
		    fenetreEchange.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
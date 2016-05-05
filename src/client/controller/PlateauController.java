package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import client.view.VuePrincipale;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import serveur.modele.Jeton;
import serveur.modele.Message;
import serveur.modele.Route;
import serveur.modele.Ville;
import serveur.modele.service.HexagoneInterface;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PlateauInterface;
import serveur.modele.service.VilleInterface;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;
import serveur.view.VueHexagone;

/**
 * Controller du plateau
 * @author jerome
 */
public class PlateauController implements Initializable{

	@FXML
	public Pane mainPane;

	/**
	 * Group rassemblant les hexagones dans le mainPane
	 */
	private Group hexagones;

	/**
	 * Group rassemblant les villes dans le mainPane
	 */
	private Group villes;

	/**
	 * Group rassemblant les routes dans le mainPane
	 */
	private Group routes;

	/**
	 * Group rassemblant les routes dans le mainPane
	 */
	private Group jetons;

	/**
	 * Plateau de jeu
	 */
	private PlateauInterface plateau;
	
	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;
	
	/**
	 * Proxy avec le serveur
	 */
	private Proxy proxy;

	/**
	 * MenuController associé
	 */
	public MenuController menuController;

	/**
	 * Méthode d'initialisation
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			recupererAttributs();
			enregistrerController();
			VuePrincipale.paneUsed = mainPane;
			recupererPlateau();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		Platform.runLater(() -> dessinerPlateau());
	}

	/**
	 * Récupère les attributs de ConnexionManager
	 */
	private void recupererAttributs() {
		this.serveur = ConnexionManager.getStaticServeur();
		this.proxy = ConnexionManager.getStaticProxy();
	}

	/**
	 * Indique au proxy que cette classe est le controller du plateau
	 * @throws RemoteException 
	 */
	private void enregistrerController() throws RemoteException {
		Proxy proxy = ConnexionManager.getStaticProxy();
		proxy.setPlateauController(this);
	}

	/**
	 * Demande le plateau au serveur
	 * @throws RemoteException 
	 */
	private void recupererPlateau() throws RemoteException {
		serveur.getGestionnaireUI().envoyerPlateau(proxy);
	}

	/**
	 * Permet de set l'attribut plateau
	 * @param plateau - Plateau envoy� par le proxy RMI 
	 */
	public void setPlateau(PlateauInterface plateau){
		this.plateau = plateau;
	}

	/**
	 * Méthode qui set le menu controller.
	 */
	public void setMenuController(MenuController mc){ this.menuController = mc; }
	
	/**
	 * Dessine le plateau
	 * @throws RemoteException 
	 */
	public void dessinerPlateau() {
		//Dessin du fond
		Image img = new Image("file:Ressources/cases/mer.png");
		ImageView imgView = new ImageView(img);
		imgView.setFitHeight(725);
		imgView.setFitWidth(650);
		imgView.setLayoutX(50);
		mainPane.getChildren().add(imgView);
		
		try {
			// Ajout des hexagones
			hexagones = new Group();
			hexagones.getChildren().addAll(VueHexagone.transformVueHexagone(plateau.getHexagones()));
	
			//Ajout des routes
			routes = new Group();
	        routes.getChildren().addAll(Route.transformRouteVueRoute(plateau.getRoutes()));
	        
			//Ajout des villes
			villes = new Group();
	        Circle[] t = Ville.transformVilleVueVille(plateau.getVilles());
			villes = new Group();
			villes.getChildren().addAll(t);
	
			//Ajout des jetons
			jetons = new Group();
			Circle[] jetonsDessine = Jeton.transformVueJeton(plateau.getJetons());
			jetons.getChildren().addAll(jetonsDessine);
	
			// Construction du Pane principal
			mainPane.getChildren().add(hexagones);
			mainPane.getChildren().add(routes);
			mainPane.getChildren().add(villes);
			mainPane.getChildren().add(jetons);
	        mainPane.setStyle("-fx-background-color: #4e6c91");
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}

	/**
	 * Update des hexagones concerné par le changement du voleur
	 * @param depart hexagone de départ
	 * @param arrive hexagone d'arrivé
	 */
	public void deplaceVoleur(int depart, int arrive) throws RemoteException{
		plateau.getVoleur().setVOLEUR(false);
		plateau.getHexagones().get(arrive).setVOLEUR(true);
		ColorAdjust colorAdjust = new ColorAdjust();
		Platform.runLater(() -> hexagones.getChildren().get(depart).setEffect(null));
		colorAdjust.setSaturation(-1);
		Platform.runLater(() -> hexagones.getChildren().get(arrive).setEffect(colorAdjust));
	}

	/**
	 * Permet l'action du voleur.
	 */
	public void doActionVoleur() throws RemoteException{
		serveur.getGestionnaireUI().diffuserMessage(new Message ("Choisir la case de destination du Voleur."));
		menuController.boutonFinTour.setDisable(true);
		mainPane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event){
				int depart;
				try {
					depart = plateau.getHexagones().indexOf(plateau.getVoleur());
					Point2D point = new Point2D(event.getX(),event.getY());
					int i = 0;
					for (HexagoneInterface hex: plateau.getHexagones()) {
						Polygon polygon = new Polygon();
						polygon.getPoints().addAll(hex.getPoints());
						if(polygon.contains(point)){
							if (defausseVoleur(hex)){
								try {
									serveur.getGestionnaireUI().diffuserVoleur(depart,i);
									serveur.getGestionnaireUI().diffuserMessage(new Message ("Déplacement du voleur de la case : "+(depart+1)+" à la case "+(i+1)+"."));
									menuController.setButtonsAfterLancerDes();
									mainPane.removeEventHandler(MouseEvent.MOUSE_PRESSED, this);
								} catch (RemoteException e) {
									e.printStackTrace();
								}								
							}else{
								event.consume();
							}
							break;
						}else{
							i++;
						}
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Defausse du volueur lorsqu'il arrive sur un hexagone.
	 * @param hex
	 * @throws RemoteException
     */
	private boolean defausseVoleur(HexagoneInterface hex) throws RemoteException{
        JoueurInterface voleur = proxy.getJoueur();
		ArrayList<VilleInterface> ville_adj = hex.getVilleAdj();
		List<String> choices = new ArrayList<>();
		for (VilleInterface vi : ville_adj) {
			JoueurInterface ji = vi.getOqp();
			if(ji != null && !ji.getNomUtilisateur().equals(voleur.getNomUtilisateur())){
				if(!choices.contains(ji.getNomUtilisateur()))choices.add(ji.getNomUtilisateur());
			}
		}
		if(choices.size()==0) return true;
        String nameVoler = popUpChoix(choices);
        if(nameVoler==null) return false;
        ArrayList<JoueurInterface> opposants = serveur.getGestionnairePartie().recupererAutresJoueurs(voleur);
        JoueurInterface voler = null;
        for (JoueurInterface ji : opposants) {
            if(ji.getNomUtilisateur().equals(nameVoler)){
                voler=ji;
            }
        }
        if(voler==null) return false;
        if (voler.getNbCarte()<1) return true;
        HashMap<Integer,Integer> stock = voler.getStockRessource();
        List l = new LinkedList<Integer>();
        for (Integer i : stock.keySet()){
            for (int nb =0 ; nb<stock.get(i) ; nb++) l.add(i);
        }
        Collections.shuffle(l);
        int ressource = (int) l.get((int)Math.random()*l.size());
        voler.supprimerRessource(ressource,1);
        voleur.ajoutRessource(ressource,1);
        serveur.getGestionnaireUI().diffuserGainRessource();
        serveur.getGestionnaireUI().diffuserGainCarteRessource();
        return true;
	}

    /**
     * Pop associée.
     * @param choices
     * @return
     */
    private String popUpChoix(List<String> choices){
        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0),choices);
        dialog.setTitle("Choix du joueur qui subit le vol");
        dialog.setContentText("Choississez le joueur ciblé : ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        }
        return null;
    }
}

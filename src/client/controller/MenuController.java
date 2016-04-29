package client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

import client.commun.Fonction;
import client.view.VuePrincipale;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import serveur.modele.Des;
import serveur.modele.Message;
import serveur.modele.Plateau;
import serveur.modele.Point;
import serveur.modele.service.*;
import serveur.modele.service.CarteInterface;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;


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
	private GridPane menuGridPane;
	
	@FXML
	private GridPane pretGridPane;
	
	@FXML
	private ImageView de1, de2;
	
	@FXML
	private Button boutonDes;
	
	/**
	 * Pour les échanges
	 */
	@FXML
	private Button boutonEchange;

    /**
     * Bouton pour lancer l'action de piocher
     */
	@FXML
	private Button boutonPioche;

    /**
     * Bonton pour lancer l'action de la carte séléctionner en choice box.
     */
    @FXML
    private Button boutonCarte;
	
	private Pane pageEchange = null;
	public static Stage fenetreEchange;
	public static Stage fenetreProposition;
	
	/**
	 * Pour les cartes
	 */
	@FXML
	private ChoiceBox<String> listeCarte;
	
	/**
	 * Pour la construction
	 */
	@FXML
	private Button boutonConstruireRoute, boutonConstruireColonie, boutonConstruireVille;
	
	/**
	 * Pour finir le tour
	 */
	@FXML
	private Button boutonFinTour;
	
	/**
	 * Proxy client
	 */
	private Proxy proxy;
	
	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;

	private Pane paneEchange;
	private Pane paneProposition;
    /**
     * PlateauController qui reporte les actions affectant le platea.
     */
	private PlateauController pc;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Initialisation des dés
		de1.setImage(new Image(numeroSix));
		de2.setImage(new Image(numeroSix));
		
		//Initialisation de la liste de cartes
		listeCarte.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        });

		//Initialisation du proxy
		proxy = ConnexionManager.getStaticProxy();
		try {
			proxy.setMenuController(this);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		serveur = ConnexionManager.getStaticServeur();
	}
	
	/**
	 * Méthode de lancement de la partie
	 * @throws RemoteException 
	 */
	public void joueurPret() throws RemoteException{
		pretGridPane.setVisible(false);
		menuGridPane.setVisible(true);
		
		String nomJoueur = proxy.getJoueur().getNomUtilisateur();
		serveur.getGestionnaireUI().diffuserMessage(new Message(nomJoueur+" est prêt !"));
		
		JoueurInterface joueur = proxy.getJoueur();
		serveur.getGestionnairePartie().joueurPret(joueur);
	}
	
	boolean isInitTurn() throws RemoteException{
		return serveur.getGestionnairePartie().isPremierePhasePartie();
	}
	
	/**
	 * Méthode de désactivation/réactivation des boutons
	 */
	public void setButtons(boolean... boo) throws RemoteException{
		if (boo.length==1){
			Platform.runLater(() -> boutonDes.setDisable(boo[0]));
			Platform.runLater(() -> boutonEchange.setDisable(boo[0]));
			Platform.runLater(() -> boutonFinTour.setDisable(boo[0]));
			Platform.runLater(() -> boutonConstruireRoute.setDisable(boo[0]));
			Platform.runLater(() -> boutonConstruireColonie.setDisable(boo[0]));
			Platform.runLater(() -> boutonConstruireVille.setDisable(boo[0]));
			Platform.runLater(() -> listeCarte.setDisable(boo[0]));
			Platform.runLater(() -> boutonPioche.setDisable(boo[0]));
            Platform.runLater(() -> boutonCarte.setDisable(boo[0]));
		}
		else {
			Platform.runLater(() -> boutonDes.setDisable(boo[0]));
			Platform.runLater(() -> boutonEchange.setDisable(boo[1]));
			Platform.runLater(() -> boutonFinTour.setDisable(boo[2]));
			Platform.runLater(() -> boutonConstruireRoute.setDisable(boo[0]));
			Platform.runLater(() -> boutonConstruireColonie.setDisable(boo[0]));
			Platform.runLater(() -> boutonConstruireVille.setDisable(boo[0]));
			Platform.runLater(() -> listeCarte.setDisable(boo[0]));
			Platform.runLater(() -> boutonPioche.setDisable(boo[0]));
            Platform.runLater(() -> boutonCarte.setDisable(boo[0]));
		}
		
		if(isInitTurn()){
			proxy.setButtonsSauvegarde(true);
		}
		else{
			proxy.setButtonsSauvegarde(false);
		}
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

        int des_val = 0;
		for(int i = 0; i<resultats.length;i++){
			des_val += resultats[i];
		}
		if(des_val != 7){
			extractionRessources(resultats);
		}else{
            serveur.getGestionnaireUI().diffuserMessage(new Message ("Choisir la case de destination du Voleur"));
			pc.doActionVoleur();
		}
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
	 * @param resultats (résultats des dés)
	 * @throws RemoteException 
	 */
	private void notifierLancerDes(Integer[] resultats) throws RemoteException{
		String nomJoueur = proxy.getJoueur().getNomUtilisateur();
		serveur.getGestionnaireUI().diffuserMessage(new Message(nomJoueur+" a lancé les dés : "+resultats[0]+" | "+resultats[1]));
	}
	
	/**
	 * Distribue à chaque joueurs les ressources associées à la case du numéro tombé
	 * @param resultats (résultats des dés)
	 * @throws RemoteException 
	 */
	private void extractionRessources(Integer[] resultats) throws RemoteException{
		Integer caseConsernee = resultats[0]+resultats[1];
		
		PlateauInterface p = serveur.getGestionnairePartie().getPartie().getPlateau();
		for (HexagoneInterface h : p.getHexagones()){
			// Recuperation des Hexagones concernés
			if (h.getNumero()==caseConsernee && !h.getVOLEUR()){
				int ressource = h.getRessource();
				// Don de ressources a chacune des Villes/Colonies adjacentes de cet Hexagone
				for(VilleInterface v : h.getVilleAdj()){
					if(v!=null && v.getOqp()!=null){
						//Si c'est une ville
						if(v.isVille()){
							v.getOqp().ajoutRessource(ressource, 2);
						}
						//Si c'est une colonie
						else{
							v.getOqp().ajoutRessource(ressource, 1);
						}
					}
				}
			}
		}
		
		//Actualisation de l'affichage
		this.proxy.getJoueursController().majRessource();
		serveur.getGestionnaireUI().diffuserGainRessource();
		serveur.getGestionnaireUI().diffuserGainCarteRessource();
	}
	
	/**
	 * Actualisation de la liste de cartes
	 */
	public void majListeCarte() throws RemoteException{
		for(CarteInterface carte : proxy.getJoueur().getCartes()){
			this.listeCarte.getItems().add(carte.getNom());
		}
	}
	
	/**
	 * Méthode pour permettre le lancement de la popup d'échange et laisser EchangeController prendre le relais pour les méthodes 
	 * 
	 */
	@FXML
	public void ouvrirEchange(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/Echange.fxml"));
		try {
			pageEchange = (Pane) loader.load();
			fenetreEchange = new Stage();
			fenetreEchange.setTitle("Les Colons de Catanes");
		    Scene scene = new Scene(pageEchange,430,500);
		    fenetreEchange.setScene(scene);
		    fenetreEchange.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode pour permettre le lancement de la popup d'échange et laisser EchangeController prendre le relais pour les méthodes 
	 * 
	 */
	public void ouvrirProposition(String nomExpediteur, HashMap<String,Integer> valeurs){
		Platform.runLater(() -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/Proposition.fxml"));
			try {
				pageEchange = (Pane) loader.load();
				
				PropositionController controller = loader.getController();
				controller.setPropositionText(nomExpediteur);
				controller.setValeursText(valeurs);
				fenetreProposition = new Stage();
				fenetreProposition.setTitle("Les Colons de Catanes");
			    Scene scene = new Scene(pageEchange,430,500);
			    fenetreProposition.setScene(scene);
			    fenetreProposition.showAndWait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * Méthode de fin de tour
	 * @throws RemoteException 
	 */
	public void finirLeTour() throws RemoteException{
		String nomJoueur = proxy.getJoueur().getNomUtilisateur();
		serveur.getGestionnaireUI().diffuserMessage(new Message(nomJoueur+" a fini son tour"));
		this.setButtons(true);
		
		//Lancement du tour du joueur suivant
		JoueurInterface joueurTour = serveur.getGestionnairePartie().finirTour();
		
		serveur.getGestionnaireUI().diffuserMessage(new Message("C'est à "+joueurTour.getNomUtilisateur()+" de jouer"));
		serveur.getGestionnairePartie().lancerProchainTour(joueurTour);
	}
	
	public void demanderRoute(boolean initPhase,VilleInterface villeIgnored){
		try{
			setButtons(true);
			Serveur serveur = ConnexionManager.getStaticServeur();
			PlateauInterface p = serveur.getGestionnairePartie().getPartie().getPlateau();
			// INITIALISATION
			// Etape 1 : Création d'une map avec chaque point qui associe la ville de cet emplacement
			HashMap<Point,VilleInterface> villes = new HashMap();
			for(VilleInterface v : p.getVilles()){
				villes.put(v.getEmplacement(), v);
			}
			// Etape 2 : Récupération des points des extremités des points des Routes du joueur qui veut construire dans un set
			JoueurInterface joueurCourrant = proxy.getJoueur();
			HashSet<Point> pointsDeRoutes = new HashSet();
			if(!initPhase){
				for(RouteInterface r: p.getRoutes()){
					if((r.getOqp()!= null) && r.getOqp().equals(joueurCourrant)){
						pointsDeRoutes.add(r.getDepart());
						pointsDeRoutes.add(r.getArrive());
					}
				}
			}
			//System.out.println(b+" "+pointsDeRoutes.size());
			//System.out.println("!");
			// RECHERCHES DES ROUTES CONSTRUCTIBLES
			HashMap<Polygon, RouteInterface> routesConstructibles = new HashMap();
			Group grp = new Group();
			for(RouteInterface r: p.getRoutes()){
				if(r.estConstructible(villes, joueurCourrant, pointsDeRoutes,villeIgnored)){
					// Récupération des points pour une écriture plus simple du code
					double x1 = r.getDepart().getX();
					double y1 = r.getDepart().getY();
					double x2 = r.getArrive().getX();
					double y2 = r.getArrive().getY();
					// Création des points du rectangle a dessiner
					Point2D p1 = null;
					Point2D p2 = null;
					Point2D p3 = null;
					Point2D p4 = null;
					// Definition d'une largeur de rectangle par rapport à la taille des Hexagones
					int minitaille = 10*Plateau.SIZE/100/2;
					// Identification du cas
					// Cas 1 : de haut gauche vers bas droit 
					if ((x1<x2 && y1>y2) || (x1>x2 && y1<y2)){
						// Identification si c'est haut gauche vers bas droit ou l'inverse
						// Le but etant depart (x1,y1) (haut gauche) et arrivée (x2,y2)
						// Si y1<y2 il faut inverser
						if (y1<y2){
							double temp = y1;
							y1 = y2;
							y2 = temp;
							
							temp = x1;
							x1 = x2;
							x2 = temp;
						}
						p1 = new Point2D(x1+(Math.sqrt(3)/2)*minitaille,y1+minitaille/2);
						p2 = new Point2D(x1-(Math.sqrt(3)/2)*minitaille,y1-minitaille/2);
						p3 = new Point2D(x2-(Math.sqrt(3)/2)*minitaille,y2-minitaille/2); 
						p4 = new Point2D(x2+(Math.sqrt(3)/2)*minitaille,y2+minitaille/2);
					}
					// Cas 2 : de bas gauche vers haut droit 
					if ((x1<x2 && y1<y2) || (x1>x2 && y1>y2)){
						if (y1<y2){
							double temp = y1;
							y1 = y2;
							y2 = temp;
							
							temp = x1;
							x1 = x2;
							x2 = temp;
						}
						p1 = new Point2D(x1-((Math.sqrt(3)/2) * minitaille),y1+minitaille/2);
						p2 = new Point2D(x1+((Math.sqrt(3)/2) * minitaille), y1-minitaille/2);
						p3 = new Point2D(x2+((Math.sqrt(3)/2) * minitaille), y2-minitaille/2);
						p4 = new Point2D(x2-((Math.sqrt(3)/2) * minitaille), y2+minitaille/2);
					}
					// Cas 3 : route vertical
					if (x1==x2) {
						if (y1<y2){
							double temp = y1;
							y1 = y2;
							y2 = temp;
							
							temp = x1;
							x1 = x2;
							x2 = temp;
						}
						p1 = new Point2D(x1+minitaille,y1);
						p2 = new Point2D(x1-minitaille,y1);
						p3 = new Point2D(x2-minitaille,y2);
						p4 = new Point2D(x2+minitaille,y2);
					}	
					Double[] points = {p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY(),p4.getX(),p4.getY()};
					Polygon rectangle = new Polygon();
					rectangle.getPoints().addAll(points);
					rectangle.setFill(Color.WHITE);
					Platform.runLater(() -> grp.getChildren().add(rectangle));
					routesConstructibles.put(rectangle, r);
					rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent me){
							try {
								rectangle.setFill(Fonction.getCouleurFromString(joueurCourrant.getCouleur()));
								((Group)VuePrincipale.paneUsed.getChildren().get(3)).getChildren().add(rectangle);
								routesConstructibles.get(rectangle).setOQP(joueurCourrant);
								VuePrincipale.paneUsed.getChildren().remove(VuePrincipale.paneUsed.getChildren().size()-1);
								serveur.getGestionnaireUI().diffuserPriseDeRoute(r, joueurCourrant);
								if(isInitTurn()){
									setButtons(true,true,false);
								}else{
									setButtons(false);
								}
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
						}
					});
				}
			}
			Platform.runLater(() -> VuePrincipale.paneUsed.getChildren().add(grp));
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public void dessinerRoute(RouteInterface r, JoueurInterface j) throws RemoteException {
		double x1 = r.getDepart().getX();
		double y1 = r.getDepart().getY();
		double x2 = r.getArrive().getX();
		double y2 = r.getArrive().getY();
		// Création des points du rectangle a dessiner
		Point2D p1 = null;
		Point2D p2 = null;
		Point2D p3 = null;
		Point2D p4 = null;
		// Definition d'une largeur de rectangle par rapport à la taille des Hexagones
		int minitaille = 10*Plateau.SIZE/100/2;
		// Identification du cas
		// Cas 1 : de haut gauche vers bas droit 
		if ((x1<x2 && y1>y2) || (x1>x2 && y1<y2)){
			// Identification si c'est haut gauche vers bas droit ou l'inverse
			// Le but etant depart (x1,y1) (haut gauche) et arrivée (x2,y2)
			// Si y1<y2 il faut inverser
			if (y1<y2){
				double temp = y1;
				y1 = y2;
				y2 = temp;
				
				temp = x1;
				x1 = x2;
				x2 = temp;
			}
			p1 = new Point2D(x1+(Math.sqrt(3)/2)*minitaille,y1+minitaille/2);
			p2 = new Point2D(x1-(Math.sqrt(3)/2)*minitaille,y1-minitaille/2);
			p3 = new Point2D(x2-(Math.sqrt(3)/2)*minitaille,y2-minitaille/2); 
			p4 = new Point2D(x2+(Math.sqrt(3)/2)*minitaille,y2+minitaille/2);
		}
		// Cas 2 : de bas gauche vers haut droit 
		if ((x1<x2 && y1<y2) || (x1>x2 && y1>y2)){
			if (y1<y2){
				double temp = y1;
				y1 = y2;
				y2 = temp;
				
				temp = x1;
				x1 = x2;
				x2 = temp;
			}
			p1 = new Point2D(x1-((Math.sqrt(3)/2) * minitaille),y1+minitaille/2);
			p2 = new Point2D(x1+((Math.sqrt(3)/2) * minitaille), y1-minitaille/2);
			p3 = new Point2D(x2+((Math.sqrt(3)/2) * minitaille), y2-minitaille/2);
			p4 = new Point2D(x2-((Math.sqrt(3)/2) * minitaille), y2+minitaille/2);
		}
		// Cas 3 : route vertical
		if (x1==x2) {
			if (y1<y2){
				double temp = y1;
				y1 = y2;
				y2 = temp;
				
				temp = x1;
				x1 = x2;
				x2 = temp;
			}
			p1 = new Point2D(x1+minitaille,y1);
			p2 = new Point2D(x1-minitaille,y1);
			p3 = new Point2D(x2-minitaille,y2);
			p4 = new Point2D(x2+minitaille,y2);
		}	
		Double[] points = {p1.getX(),p1.getY(),p2.getX(),p2.getY(),p3.getX(),p3.getY(),p4.getX(),p4.getY()};
		Polygon rectangle = new Polygon();
		rectangle.getPoints().addAll(points);
		rectangle.setFill(Fonction.getCouleurFromString(j.getCouleur()));
		Platform.runLater(() -> ((Group)VuePrincipale.paneUsed.getChildren().get(2)).getChildren().add(rectangle));
	}
	
	/**
	 * Méthodes permettant à l'utilisateur de choisir où placer sa colonie
	 * @param depart booleen indiquant si c'est le depart auquel cas on pose où on ne tiens pas compte des route
	 */
	public void demanderColonie(boolean depart){	
		try {
			setButtons(true);
			Serveur serveur = ConnexionManager.getStaticServeur();
			PlateauInterface p = serveur.getGestionnairePartie().getPartie().getPlateau();
			JoueurInterface joueurCourrant = proxy.getJoueur();
			// Recuperation de la liste des villes
			HashMap<Point,VilleInterface> toutesLesVilles = new HashMap<Point,VilleInterface>();
			for(VilleInterface v : p.getVilles()){
				toutesLesVilles.put(v.getEmplacement(), v);
			}
			//Création du groupe pour ajouter les villes potentiel
			Group g = new Group();
			if (depart) {
				for (VilleInterface v : p.getVilles()){
					if (v.estLibre(null, p.getVilles())){
						// Si pas ressorti, possibles exception (infixable)
						double x = v.getEmplacement().getX();
						double y = v.getEmplacement().getY();
						Circle c = new Circle(x,y,Plateau.SIZE/5,Color.WHITE);
						c.setOnMousePressed(new EventHandler<MouseEvent>(){

							@Override
							public void handle(MouseEvent event) {
								// TODO Auto-generated method stub
								try {
									v.setOQP(joueurCourrant);
									c.setFill(Fonction.getCouleurFromString(joueurCourrant.getCouleur()));
									VuePrincipale.paneUsed.getChildren().remove(VuePrincipale.paneUsed.getChildren().size()-1);
									serveur.getGestionnaireUI().diffuserPriseDeVille(v, joueurCourrant);
									setButtons(false);
									if(isInitTurn()){
										setButtons(true,true,false);
										Point maColo = new Point(c.getCenterX(),c.getCenterY());
										VilleInterface maFirstColo = null;
										int i = 0;
										for (VilleInterface v : p.getVilles()){
											if (v.getOqp()!=null && v.getOqp().equals(joueurCourrant) && !v.getEmplacement().equals(maColo)){
												//v.setOQP(null);
												maFirstColo = v;
												i++;
												System.out.println(maFirstColo);
											}
										}
										//System.out.println();
										if (i == 0) maFirstColo=null;
										demanderRoute(true,maFirstColo);
										//if (maFirstColo != null) maFirstColo.setOQP(joueurCourrant);
									}
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
							}
							
						});
						Platform.runLater( () -> g.getChildren().add(c));
					}
				}
			}
			Platform.runLater(() -> VuePrincipale.paneUsed.getChildren().add(g));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void dessinerVille(VilleInterface v, JoueurInterface joueurCourrant) {
		// TODO Auto-generated method stub
		try {
			double x = v.getEmplacement().getX();
			double y = v.getEmplacement().getY();
			Circle c = new Circle(x,y,Plateau.SIZE/5,Fonction.getCouleurFromString(joueurCourrant.getCouleur()));
			Platform.runLater(() -> ((Group)VuePrincipale.paneUsed.getChildren().get(3)).getChildren().add(c));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

    /**
     * Initialise le controller.
     * @param pc PlateauContoller.
     */
    public void setPlateauController(PlateauController pc){
        this.pc = pc;
    }

	/**
	 * Pioche de la carte.
     */
	public void piocheCarte() throws RemoteException {
		//TODO tester si ressources sont suffisantes et les décrémentés.
		CarteInterface carte = serveur.getGestionnairePartie().getPartie().piocheDeck();
		if (carte != null) {
			CarteInterface card = carte;
			Platform.runLater(() -> {
                try {
                    listeCarte.getItems().add(card.getNom());
                    proxy.getJoueur().addCartes(carte);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
			serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur() + " a acheté une carte développement."));
		}else{
            serveur.getGestionnaireUI().diffuserMessage(new Message("Le deck de carte développement est vide."));
        }
	}

    public void jouerCarte() throws RemoteException {
        int index = listeCarte.getSelectionModel().getSelectedIndex();

        System.out.println(listeCarte.getSelectionModel().getSelectedItem());
        System.out.println("Index -->" +index);
        CarteInterface carte = proxy.getJoueur().getCartes(index);
        System.out.println("Carte -->" +carte.getNom());
        if(carte != null){
            serveur.getGestionnaireUI().diffuserMessage(new Message("Playing card : "+carte.getNom()));
            listeCarte.getItems().remove(index);
            proxy.getJoueur().removeCartes(index);
        }
    }
}

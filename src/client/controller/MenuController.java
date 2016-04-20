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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;
import serveur.modele.Des;
import serveur.modele.Message;
import serveur.modele.Plateau;
import serveur.modele.Point;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PlateauInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;
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
	
	private Pane page = null;
	public static Stage fenetreEchange;
	
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
	
	private Pane pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Initialisation des dés
		de1.setImage(new Image(numeroSix));
		de2.setImage(new Image(numeroSix));
		
		//Initialisation du proxy
		proxy = ConnexionManager.getStaticProxy();
		proxy.setMenuController(this);
		
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
	
	/**
	 * Méthode de réactivation des boutons
	 */
	public void setButtons(boolean boo){
		Platform.runLater(() -> boutonDes.setDisable(boo));
		Platform.runLater(() -> boutonEchange.setDisable(boo));
		Platform.runLater(() -> boutonFinTour.setDisable(boo));
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
	 * @throws RemoteException 
	 */
	private void notifierLancerDes(Integer[] resultats) throws RemoteException{
		String nomJoueur = proxy.getJoueur().getNomUtilisateur();
		serveur.getGestionnaireUI().diffuserMessage(new Message(nomJoueur+" a lancé les dés : "+resultats[0]+" | "+resultats[1]));
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
	
	public void demanderRoute(){
		try{
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
			for(RouteInterface r: p.getRoutes()){
				if((r.getOqp()!= null) && r.getOqp().equals(joueurCourrant)){
					pointsDeRoutes.add(r.getDepart());
					pointsDeRoutes.add(r.getArrive());
				}
			}
			// RECHERCHES DES ROUTES CONSTRUCTIBLES
			HashMap<Polygon, RouteInterface> routesConstructibles = new HashMap();
			Group grp = new Group();
			for(RouteInterface r: p.getRoutes()){
				if(r.estConstructible(villes, joueurCourrant, pointsDeRoutes)){
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
								/*System.out.println(VuePrincipale.paneUsed.getChildren());
								System.out.println(((Group)VuePrincipale.paneUsed.getChildren().get(1)).getChildren());
								System.out.println(((Group)VuePrincipale.paneUsed.getChildren().get(2)).getChildren());
								System.out.println(((Group)VuePrincipale.paneUsed.getChildren().get(3)).getChildren());
								System.out.println(((Group)VuePrincipale.paneUsed.getChildren().get(4)).getChildren());
								System.out.println(((Group)VuePrincipale.paneUsed.getChildren().get(5)).getChildren());*/
								((Group)VuePrincipale.paneUsed.getChildren().get(3)).getChildren().add(rectangle);
								routesConstructibles.get(rectangle).setOQP(joueurCourrant);
								VuePrincipale.paneUsed.getChildren().remove(VuePrincipale.paneUsed.getChildren().size()-1);
								serveur.getGestionnaireUI().diffuserPriseDeRoute(r, joueurCourrant);
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
		// TODO Auto-generated method stub
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
		Platform.runLater(() -> ((Group)VuePrincipale.paneUsed.getChildren().get(3)).getChildren().add(rectangle));
	}
	
	/**
	 * Méthodes permettant à l'utilisateur de choisir où placer sa colonie
	 * @param depart booleen indiquant si c'est le depart auquel cas on pose où on ne tiens pas compte des routes
	 */
	public void demanderColonie(boolean depart){		
		try {
			Serveur serveur = ConnexionManager.getStaticServeur();
			PlateauInterface p = serveur.getGestionnairePartie().getPartie().getPlateau();
			HashMap<Point,VilleInterface> villes = new HashMap<Point,VilleInterface>();
			// On identifie les deux cas: cas du depart ou on a aucune route, cas ou on a des routes 
			if (depart) {
				for (VilleInterface v : p.getVilles()){
					if (v.estLibre(null, p.getVilles())){
						
					}
				}
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

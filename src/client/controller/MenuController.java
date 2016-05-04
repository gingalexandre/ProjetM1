package client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import serveur.modele.Des;
import serveur.modele.Message;
import serveur.modele.Plateau;
import serveur.modele.Point;
import serveur.modele.Ressource;
import serveur.modele.carte.LongueRoute;
import serveur.modele.carte.Victoire;
import serveur.modele.service.CarteInterface;
import serveur.modele.service.HexagoneInterface;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PlateauInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;


/**
 * @author jerome
 *
 */
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
	 * Pour finir le tour
	 */
	@FXML
	public Button boutonFinTour;

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

	/**
	 * Pour la construction
	 */
	@FXML
	private Button boutonConstruireRoute, boutonConstruireColonie, boutonConstruireVille, boutonQuitter;

	/**
	 * Pane popup
	 */
	private Pane pagePopup = null;

	/**
	 * Diverses fenêtres
	 */
	public static Stage fenetreEchange, fenetreProposition, fenetreVol;


	/**
	 * CarteController qui gère els actions des cartes
	 */
	private CarteController carteController;

	@FXML
	private ChoiceBox<String> listeCarte;

	/**
	 * PlateauController qui reporte les actions affectant le platea.
	 */
	private PlateauController pc;

	/**
	 * Serveur de jeu
	 */
	private Serveur serveur;

	/**
	 * Proxy client
	 */
	private Proxy proxy;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		serveur = ConnexionManager.getStaticServeur();

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
			e.printStackTrace();
		}
		
		/*
		 * Dessin des routes / villes si la partie est chargée
		 */
		PlateauInterface p;
		try {
			p = serveur.getGestionnairePartie().getPartie().getPlateau();
			for(RouteInterface r : p.getRoutes()){
				if(r.getOqp() != null){
					dessinerRoute(r, r.getOqp());
				}
			}
			for(VilleInterface v : p.getVilles()){
				if(v.getOqp() != null){
					dessinerVille(v, v.getOqp());
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode pour permettre le lancement de la popup d'échange et laisser EchangeController prendre le relais pour les méthodes 
	 */
	@FXML
	public void ouvrirEchange(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/Echange.fxml"));
		try {
			pagePopup = (Pane) loader.load();
			fenetreEchange = new Stage();
			fenetreEchange.setTitle("Les Colons de Catanes");
			Scene scene = new Scene(pagePopup,430,500);
			fenetreEchange.initModality(Modality.WINDOW_MODAL);
			fenetreEchange.initOwner(ConnexionController.gameFenetre.getScene().getWindow());
			fenetreEchange.setScene(scene);
			fenetreEchange.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permet de quitter la partie. Se délenche quand la partie est terminée et que l'utilisateur appuie sur le bouton pour quitter la partie.
	 * @throws RemoteException
	 */
	@FXML
	public void quitterPartie() throws RemoteException{
		try{
			serveur.quitterPartie(proxy.getJoueur());
			System.exit(0);
		}
		catch(UnmarshalException e){ // Le serveur n'existe plus
			System.exit(0);
		}
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

	public boolean isInitTurn() throws RemoteException{
		return serveur.getGestionnairePartie().isPremierePhasePartie();
	}

	public void setButtonsAfterLancerDes() throws RemoteException{
		Platform.runLater(() -> {
			boutonEchange.setDisable(false);
			boutonFinTour.setDisable(false);
			try {
				disableBoutonConstruction(false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listeCarte.setDisable(false);
			boutonPioche.setDisable(false);
			boutonCarte.setDisable(false);
			this.boutonDes.setDisable(true);
		});
	}
	/**
	 * Méthode de désactivation/réactivation des boutons
	 */
	public void setButtons(boolean... boo) throws RemoteException{
		if (boo.length==1){
			Platform.runLater(() -> {
				boutonDes.setDisable(boo[0]);
				boutonEchange.setDisable(boo[0]);
				boutonFinTour.setDisable(boo[0]);
				try {
					disableBoutonConstruction(boo[0]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listeCarte.setDisable(boo[0]);
				boutonPioche.setDisable(boo[0]);
				boutonCarte.setDisable(boo[0]);
			});
		}
		else {
			Platform.runLater(() -> {
				boutonDes.setDisable(boo[0]);
				boutonEchange.setDisable(boo[1]);
				boutonFinTour.setDisable(boo[2]);
				try {
					disableBoutonConstruction(boo[0]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listeCarte.setDisable(boo[0]);
				boutonPioche.setDisable(boo[0]);
				boutonCarte.setDisable(boo[0]);
			});
		}

		if(isInitTurn()){
			proxy.setButtonsSauvegarde(true);
		}
		else{
			proxy.setButtonsSauvegarde(false);
		}
	}

	public void disableBoutonConstruction(boolean boo) throws RemoteException{
		//vérification possibilité construction
		if(peutConstruireRoute()){
			boutonConstruireRoute.setDisable(boo);
		}
		if(peutConstruireColonie()){
			boutonConstruireColonie.setDisable(boo);
		}
		if(peutConstruireVille()){
			boutonConstruireVille.setDisable(boo);
		}
	}

	public boolean peutConstruireRoute() throws RemoteException {
		if((proxy.getJoueur().getStockRessource().get(Ressource.ARGILE)>=1)&&(proxy.getJoueur().getStockRessource().get(Ressource.BOIS)>=1)){
			return true;
		}
		return false;
	}

	public boolean peutConstruireColonie() throws RemoteException {
		if((proxy.getJoueur().getStockRessource().get(Ressource.ARGILE)>=1)&&(proxy.getJoueur().getStockRessource().get(Ressource.BOIS)>=1)&&(proxy.getJoueur().getStockRessource().get(Ressource.BLE)>=1)&&(proxy.getJoueur().getStockRessource().get(Ressource.LAINE)>=1)){
			return true;
		}
		return false;
	}

	public boolean peutConstruireVille() throws RemoteException {
		if((proxy.getJoueur().getStockRessource().get(Ressource.BLE)>=2)&&(proxy.getJoueur().getStockRessource().get(Ressource.MINERAIE)>=3)){
			return true;
		}
		return false;
	}

	/**
	 * Fait apparaitre le bouton pour quitter la partie
	 */
	public void activerQuitterPartie(){
		Platform.runLater(() -> {
			menuGridPane.setVisible(false);
			pretGridPane.setVisible(true);
			boutonQuitter.setVisible(true);
		});
	}

	/**
	 * Méthodes pour les dés
	 * lancerDes()
	 * animationDes()
	 * distributionDes()
	 * notifierLancerDes()
	 */
	public void lancerDes() throws RemoteException {
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
			pc.doActionVoleur();
			HashMap<String, Integer> listeJoueursVoles = serveur.getGestionnairePartie().getPartie().getNomJoueursVoles();

			Set<String> cles = listeJoueursVoles.keySet();
			Iterator<String> it = cles.iterator();
			while (it.hasNext()){
				String nom = (String) it.next();
				Integer moitierRessource = listeJoueursVoles.get(nom);
				serveur.getGestionnaireUI().envoyerVol(moitierRessource, serveur.getJoueur(nom));
			}
		}
		setButtonsAfterLancerDes();
	}

	/**
	 * Permet d'animer les dès
	 */
	public void animateDes() {
		RotateTransition rt1 = new RotateTransition(Duration.millis(1000), de1);
		RotateTransition rt2 = new RotateTransition(Duration.millis(1000), de2);
		rt1.setByAngle(180*6);
		rt2.setByAngle(180*6);
		rt1.play();
		rt2.play();
	}

	/**
	 * Permet d'avoir le string correspondant au score du dès
	 * @param de
	 * @return un string correspondant au score du dès
	 */
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
						if(v.isColonie()){
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
	public void ouvrirProposition(String nomExpediteur, HashMap<String,Integer> valeurs){
		Platform.runLater(() -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/Proposition.fxml"));
			try {
				pagePopup = (Pane) loader.load();

				PropositionController controller = loader.getController();
				controller.setPropositionText(nomExpediteur);
				controller.setValeursText(valeurs);
				fenetreProposition = new Stage();
				fenetreProposition.setTitle("Les Colons de Catanes");

				Scene scene = new Scene(pagePopup,430,500);
				fenetreProposition.initModality(Modality.WINDOW_MODAL);
				fenetreProposition.initOwner(ConnexionController.gameFenetre.getScene().getWindow());
				fenetreProposition.setScene(scene);
				fenetreProposition.showAndWait();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	/**
	 * Méthode pour permettre le lancement de la popup de vol et laisser VolController prendre le relais pour les méthodes 
	 *
	 */
	public void ouvrirVol(int maxRessource) {
		Platform.runLater(() -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/view/fxml/Vol.fxml"));
			try {
				pagePopup = (Pane) loader.load();

				VolController controller = loader.getController();
				controller.setValeursText(maxRessource);
				fenetreVol = new Stage();
				fenetreVol.setTitle("Les Colons de Catanes");
				Scene scene = new Scene(pagePopup,430,500);
				fenetreVol.setScene(scene);
				fenetreVol.initStyle(StageStyle.UNDECORATED);
				fenetreVol.initModality(Modality.WINDOW_MODAL);
				fenetreVol.initOwner(ConnexionController.gameFenetre.getScene().getWindow());
				fenetreVol.showAndWait();
				serveur.getGestionnaireUI().diffuserDisableBoutonEchange(true);

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
		String nomJoueurActuel = proxy.getJoueur().getNomUtilisateur();
		this.setButtons(true);

		//Lancement du tour du joueur suivant
		serveur.getGestionnairePartie().finirTour(nomJoueurActuel);
	}

	/**
	 * Demande une route
	 * @param initPhase
	 * @param villeIgnored
	 */
	public void demanderRoute(boolean initPhase, VilleInterface villeIgnored){
		try{
			setButtons(true);
			PlateauInterface p = serveur.getGestionnairePartie().getPartie().getPlateau();
			// INITIALISATION
			// Etape 1 : Création d'une map avec chaque point qui associe la ville de cet emplacement
			HashMap<Point,VilleInterface> villes = new HashMap<Point, VilleInterface>();
			for(VilleInterface v : p.getVilles()){
				villes.put(v.getEmplacement(), v);
			}
			// Etape 2 : Récupération des points des extremités des points des Routes du joueur qui veut construire dans un set
			JoueurInterface joueurCourrant = proxy.getJoueur();
			HashSet<Point> pointsDeRoutes = new HashSet<Point>();
			if(!initPhase){
				for(RouteInterface r: p.getRoutes()){
					if((r.getOqp()!= null) && r.getOqp().equals(joueurCourrant)){
						pointsDeRoutes.add(r.getDepart());
						pointsDeRoutes.add(r.getArrive());
					}
				}
			}
			// RECHERCHES DES ROUTES CONSTRUCTIBLES
			HashMap<Polygon, RouteInterface> routesConstructibles = new HashMap<Polygon, RouteInterface>();
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
								int maRouteLaPlusLongue = p.calculerRouteLaPlusLongue(joueurCourrant);
								joueurCourrant.construireRoute();
								proxy.getJoueursController().majRessource();
								serveur.getGestionnaireUI().diffuserGainRessource(); // A voir si on peut supprimer
								serveur.getGestionnaireUI().diffuserGainCarteRessource();
								serveur.getGestionnaireUI().diffuserMessage(new Message("La route la plus longue de "+joueurCourrant.getNomUtilisateur()+" est de "+maRouteLaPlusLongue+"."));
								if(maRouteLaPlusLongue>= LongueRoute.NB_ROUTE_MINIMAL){
									joueurCourrant.setTailleroutemax(maRouteLaPlusLongue);
									serveur.getGestionnairePartie().verificationRouteLongue(joueurCourrant);
									serveur.getGestionnaireUI().updatePointVictoire();
									serveur.getGestionnaireUI().updateRouteLongue();
								}
								if(isInitTurn()){
									setButtons(true,true,false);
								}
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			}
			if (!initPhase && grp.getChildren().size()==0) setButtons(false);
			Platform.runLater(() -> VuePrincipale.paneUsed.getChildren().add(grp));
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Permet de dessiner une route
	 * @param r
	 * @param j
	 * @throws RemoteException
	 */
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
			if(!this.serveur.getGestionnairePartie().getPartie().isChargee()){
				setButtons(true);
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
										joueurCourrant.construireColonie();
										proxy.getJoueursController().majRessource();
										serveur.getGestionnaireUI().diffuserGainRessource(); // A voir si on peut supprimer
										serveur.getGestionnaireUI().diffuserGainCarteRessource();
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
												}
											}
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
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Permet de dessiner une ville
	 * @param v
	 * @param joueurCourrant
	 */
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
	 * Initialise le controller.
	 * @param cc PlateauContoller.
	 */
	public void setCarteController(CarteController cc){
		this.carteController = cc;
	}

	/**
	 * Pioche de la carte.
	 */
	public void piocheCarte() throws RemoteException {
		//TODO tester si ressources sont suffisantes et les décrémentés.
		JoueurInterface j = proxy.getJoueur();
		CarteInterface carte;
		if (j.checkAchat("Developpement")){
			carte = serveur.getGestionnairePartie().getPartie().piocheDeck();
			j.faireAchat("Developpement");
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erreur détectée");
			alert.setHeaderText("Attention vous avez essayé une action impossible.");
			alert.setContentText("Vous ne pouvez pas piocher de carte. Vous n'avez pas les ressources");
			alert.showAndWait();
			return;
		}

		if (carte != null) {
			CarteInterface card = carte;
			Platform.runLater(() -> {
				try {
					listeCarte.getItems().add(card.getNom());
					proxy.getJoueur().addCarte(carte);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			});
			serveur.getGestionnaireUI().diffuserMessage(new Message(proxy.getJoueur().getNomUtilisateur() + " a acheté une carte développement."));
		}else{
			serveur.getGestionnaireUI().diffuserMessage(new Message("Le deck de carte développement est vide."));
		}
	}

	/**
	 * Méthode qui permet de jouer une carte.
	 * @throws RemoteException
	 */
	public void jouerCarte() throws RemoteException {
		int index = listeCarte.getSelectionModel().getSelectedIndex();
		JoueurInterface joueur = proxy.getJoueur();
		if(index != -1){
			CarteInterface carte = joueur.getCarte(index);
			if (carte != null) {
				if (carte.getUtilisable()) {
					serveur.getGestionnaireUI().diffuserMessage(new Message(joueur.getNomUtilisateur() + " joue la carte de développement: " + carte.getNom() + "."));
					boolean action = carteController.doActionCarte(carte);
					if (action == true) {
						listeCarte.getItems().remove(index);
						joueur.removeCarte(index);
					} else {
						popErreur("Action annulée ou non valide.");
					}
				} else {
					popErreur("Vous ne pouvez pas cette carte puisque vous venez de la pioché et que ce n'est pas une carte victoire.");
				}
			} else {
				popErreur("Veuillez séléctionner une carte dans le menu déroulant avant d'essayer de la jouer.");
			}
		}else{
			popErreur("Aucune carte séléctionnée.");
		}
		disableBoutonConstruction(false);
	}


	public void popErreur(String m){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erreur détectée");
		alert.setHeaderText("Attention vous avez essayé une action impossible.");
		alert.setContentText(m);
		alert.showAndWait();
	}


	@FXML
	public void construireRoute() throws RemoteException{
		JoueurInterface j = proxy.getJoueur();
		// Verification préalable
		if (j.checkAchat("Route")){
			demanderRoute(false, null);
			this.proxy.getJoueursController().majRessource();
			serveur.getGestionnaireUI().diffuserGainRessource();
			serveur.getGestionnaireUI().diffuserGainCarteRessource();
		}else {
			popErreur("Vous ne pouvez pas contruire de routes. Soit vous avez atteint la limite, soit vous n'avez pas les ressources");
			Alert alert = new Alert(Alert.AlertType.ERROR);
		}
	}

	@FXML
	public void construireColonie() throws RemoteException{
		JoueurInterface j = proxy.getJoueur();
		//Vérification prealable
		if (j.checkAchat("Colonie")){
			demanderColonie(false);
		}
		else {
			popErreur("Vous ne pouvez pas contruire de colonie. Soit vous avez atteint la limite, soit vous n'avez pas les ressoruces");
		}
	}

	public void disableBoutonEchange(boolean b) {
		this.boutonEchange.setDisable(b);
	}

	public void setBoutonLancerDes(boolean b) {
		this.boutonDes.setDisable(b);
	}

}

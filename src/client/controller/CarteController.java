package client.controller;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import serveur.modele.Message;
import serveur.modele.Ressource;
import serveur.modele.carte.*;
import serveur.modele.service.CarteInterface;
import serveur.modele.service.JoueurInterface;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;
import java.rmi.RemoteException;
import java.util.Optional;

/**
 * Created by Yohann Hugo on 30/04/2016.
 */
public class CarteController {

    /**
     * PlateauController pour répercuter les changements sur le plateau.
     */
    public PlateauController plateauController;

    /**
     * MenuController pour déclencher la constructon des routes.
     */
    public MenuController menuController;

    /**
     * Proxy client
     */
    private Proxy proxy;

    /**
     * Serveur de jeu
     */
    private Serveur serveur;

    /**
     * Constructeur
     */
    public CarteController(PlateauController pc, MenuController mc){
        plateauController=pc;
        menuController=mc;
        proxy = ConnexionManager.getStaticProxy();
        try {
            proxy.setCarteController(this);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        serveur = ConnexionManager.getStaticServeur();
    }

    public boolean doActionCarte(CarteInterface ci) throws RemoteException {
        boolean action = false;
        JoueurInterface player = proxy.getJoueur();
        if(ci.getNom().equals((new Chevalier()).getNom())){
            plateauController.doActionVoleur();
            player.incrementeGuerrier();
            int nbguerrier = player.nbGuerrier();
            if(nbguerrier>=ArmeePuissante.NB_CHEVALIER_MINIMAL){
                serveur.getGestionnairePartie().verificationArmeeForte(player);
                serveur.getGestionnaireUI().updatePointVictoire();
                serveur.getGestionnaireUI().updateArmeePuissante();
            }
            action = true;
        }
        if(ci.getNom().equals((new Victoire()).getNom())){
            action = true;
            player.setPointVictoire(player.getPointVictoire()+2);
            serveur.getGestionnaireUI().updatePointVictoire();
            serveur.getGestionnaireUI().diffuserMessage(new Message(player.getNomUtilisateur()+" gagne 2 points de victoire suite à l'usage de sa carte développement."));
        }
        if(ci.getNom().equals((new Invention()).getNom())){
            int ressource_cible = popChoixRessource("Carte Invention","Les cartes de développement de type Invention permettent de gagner +2 dans une ressource.");
            if(ressource_cible != -1){
                action = true;
                player.ajoutRessource(ressource_cible,2);
                proxy.getJoueursController().majRessource();
                serveur.getGestionnaireUI().diffuserMessage(new Message(player.getNomUtilisateur()+" gagne 2 de "+nameRessource(ressource_cible)+" suite à l'usage de sa carte développement."));
            }
        }
        if(ci.getNom().equals((new Monopole()).getNom())){
            int ressource_cible = popChoixRessource("Carte Monopole","Les cartes de développement de type Monopole permettent d'obtenir le monopole d'une ressource en volant les réserves de celle-ci aux autres joueurs.");
            if(ressource_cible != -1){
                action = true;
                int total = serveur.getGestionnaireUI().monopole(ressource_cible);
                player.ajoutRessource(ressource_cible,total);
                serveur.getGestionnaireUI().diffuserGainRessource();
                serveur.getGestionnaireUI().diffuserMessage(new Message(player.getNomUtilisateur()+" gagne "+total+" de "+nameRessource(ressource_cible)+" suite à l'usage de sa carte développement."));
            }
        }
        if(ci.getNom().equals((new Route()).getNom())){
            serveur.getGestionnaireUI().diffuserMessage(new Message(player.getNomUtilisateur()+" gagne 2 routes à construire suite à l'usage de sa carte développement de type Route."));

        }
        return action;
    }

    private int popChoixRessource(String messagehead,String messagetext){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(messagehead);
        alert.setHeaderText(messagetext);
        alert.setContentText("Veuillez choisir la ressource qui sera visée par votre action : ");

        ButtonType buttonBois = new ButtonType("Bois");
        ButtonType buttonBle = new ButtonType("Blé");
        ButtonType buttonLaine = new ButtonType("Laine");
        ButtonType buttonArgile = new ButtonType("Argile");
        ButtonType buttonMineraie = new ButtonType("Mineraie");

        alert.getButtonTypes().setAll(buttonBois, buttonBle, buttonLaine, buttonArgile,buttonMineraie);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonBois){
            return Ressource.BOIS;
        } else if (result.get() == buttonBle) {
            return Ressource.BLE;
        } else if (result.get() == buttonLaine) {
            return Ressource.LAINE;
        } else if (result.get() == buttonArgile) {
            return Ressource.ARGILE;
        } else if (result.get() == buttonMineraie) {
            return Ressource.MINERAIE;
        }

        return -1;
    }

    public String nameRessource(int i){
        switch (i){
            case 1 :
                return "bois";
            case 2 :
                return "blé";
            case 3 :
                return "laine";
            case 4 :
                return "argile";
            case 5 :
                return "mineraie";
            default :
                return "inconnue";
        }
    }

    public boolean carteArmeePuissante(){

        return false;
    }


}

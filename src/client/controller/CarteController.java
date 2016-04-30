package client.controller;


import serveur.modele.carte.*;
import serveur.modele.service.CarteInterface;
import serveur.reseau.proxy.Proxy;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;
import java.rmi.RemoteException;

/**
 * Created by Yohann Hugo on 30/04/2016.
 */
public class CarteController {

    /**
     * PlateauController pour répercuter les changements desus.
     */
    public PlateauController plateauController;

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
    public CarteController(PlateauController pc){
        plateauController=pc;
        proxy = ConnexionManager.getStaticProxy();
        try {
            proxy.setCarteController(this);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        serveur = ConnexionManager.getStaticServeur();
    }

    public void doActionCarte(CarteInterface ci) throws RemoteException {
        if(ci.getNom().equals((new Chevalier()).getNom())){
            plateauController.doActionVoleur();
            proxy.getJoueur().incrementeGuerrier();
        }
        if(ci.getNom().equals((new Victoire()).getNom())){

        }
        if(ci.getNom().equals((new Invention()).getNom())){

        }
        if(ci.getNom().equals((new Monopole()).getNom())){

        }
        if(ci.getNom().equals((new Route()).getNom())){

        }
    }


}

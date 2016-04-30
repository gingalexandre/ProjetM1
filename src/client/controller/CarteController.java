package client.controller;


import serveur.modele.service.CarteInterface;

/**
 * Created by Yohann Hugo on 30/04/2016.
 */
public class CarteController {

    public PlateauController plateauController;

    /**
     * Constructeur
     */
    public CarteController(PlateauController pc){
        plateauController=pc;
    }

    public void doActionCarte(CarteInterface ci){
        System.out.println("Une carte a été jouée.");
    }


}

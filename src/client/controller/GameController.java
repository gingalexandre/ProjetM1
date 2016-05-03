package client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * Created by Yohann Hugo on 22/04/2016.
 */
public class GameController implements Initializable{

    /**
     * Element inclus plateau.
     */
    @FXML private Parent plateau;
    
    /**
     * Element inclus menu.
     */
    @FXML private Parent menu;

    @FXML MenuController menuController;
    
    @FXML PlateauController plateauController;

    public CarteController carteController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carteController = new CarteController(plateauController,menuController);
        menuController.setPlateauController(plateauController);
        menuController.setCarteController(carteController);
    }
}

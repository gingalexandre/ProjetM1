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

    //  Include des éléments
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuController.setPlateauController(plateauController);
    }
}

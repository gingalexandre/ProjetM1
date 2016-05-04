package client.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ParametresController implements Initializable {
	
	@FXML
	public ComboBox<Integer> comboBoxJoueurs;
	
	@FXML
	public ComboBox<String> comboBoxDifficulte;
	
	@FXML
	public Button boutonValider;
	
	public static int nbJoueurs = 3;
	
	public static String difficulte = "Débutant";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboBoxJoueurs.getItems().add(3);
		comboBoxJoueurs.getItems().add(4);
		comboBoxJoueurs.getSelectionModel().select(0);
		
		comboBoxDifficulte.getItems().add("Débutant");
		comboBoxDifficulte.getItems().add("Expert");
		comboBoxDifficulte.getSelectionModel().select(0);
	}
	
	@FXML
	public void valider(){
		Stage stage = (Stage) boutonValider.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void changeJoueurs(){
		nbJoueurs = comboBoxJoueurs.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	public void changeDifficulte(){
		difficulte = comboBoxDifficulte.getSelectionModel().getSelectedItem();
	}
}

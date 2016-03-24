package client.controller.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class JoueurActuelApplicationController implements Initializable {

	@FXML
	private ImageView imageBle, imageArgile, imageCaillou, imageLaine, imageBois;

	private TextField nbBle, nbArgile, nbCaillou, nbLaine, nbBois;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nbBle.setText("0");
		nbArgile.setText("0");
		nbCaillou.setText("0");
		nbLaine.setText("0");
		nbBois.setText("0");
		

	}

}

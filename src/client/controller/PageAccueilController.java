package client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import client.view.VuePageAccueil;
import client.view.VuePrincipale;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PageAccueilController implements Initializable {

	@FXML
	public Pane panePageAccueil;

	@FXML
	public Button boutonJouer, boutonRegles, boutonQuitter;

	@FXML
	private TextField ipServeur;

	@FXML
	public ImageView imageCatan;

	private static final String cheminImage = "/autres/logo.png";

	public static String ipServeurConnexion;
	
	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		panePageAccueil.getStylesheets().clear();
		panePageAccueil.getStylesheets()
				.add(getClass().getResource("/client/view/fxml/stylesheet.css").toExternalForm());

		imageCatan.setImage(new Image(getClass().getResourceAsStream(cheminImage)));
		Platform.setImplicitExit(false);
	}

	/**
	 * Se déclenche lors d'un clic sur le boutton boutonJouer
	 */
	@FXML
	public void jouer() {
		 Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(this.ipServeur.getText());
		if (this.ipServeur.getText() == null || !matcher.matches()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erreur détectée");
			alert.setHeaderText("Attention vous avez essayé une action impossible.");
			alert.setContentText("Veuillez rentrer une adresse IP");
			alert.showAndWait();
		} else {
			ipServeurConnexion = this.ipServeur.getText();
			Platform.runLater(() -> {
				try {
					VuePageAccueil.stagePrincipal.close();
					new VuePrincipale().start(new Stage());
				} catch (Exception e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Erreur!");
					alert.setHeaderText("Attention : ");
					alert.setContentText("Le serveur de jeu est indisponible.");
					alert.showAndWait();
					VuePageAccueil.stagePrincipal.show();
					e.printStackTrace();
				}
			});
		}
	}

	/**
	 * Se déclenche lors d'un clic sur le boutton boutonRegles
	 */
	@FXML
	public void regles() {
		VuePrincipale v = new VuePrincipale();
		v.getHostServices().showDocument(getClass().getResource("/rules.pdf").toExternalForm());
	}

	/**
	 * Se déclenche lors d'un clic sur le boutton boutonQuitter
	 */
	@FXML
	public void quitter() {
		System.exit(0);
	}
}

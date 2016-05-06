package client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VuePageAccueil extends Application{

	public static Scene scene;
	public static Stage stagePrincipal;
	public static Pane paneUsed;
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader preloader = new FXMLLoader(getClass().getResource("fxml/PageAccueil.fxml"));
		Parent root = preloader.load();
        scene = new Scene(root,925,715);
        scene.getStylesheets().clear();
		scene.getStylesheets()
				.add(getClass().getResource("/client/view/fxml/stylesheet.css").toExternalForm());

        stage.setTitle("Les Colons de Catanes");
        stage.setScene(scene);
        stagePrincipal = stage;
        stagePrincipal.setResizable(false);
        stage.show();
	}
}

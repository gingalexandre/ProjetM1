package client.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

/**
 * 
 * @author jerome
 */
public class VuePrincipale extends Application{
	
	public static Scene scene;
	public static Stage stagePrincipal;
	public static Pane paneUsed;

	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader preloader = new FXMLLoader(getClass().getResource("fxml/Accueil.fxml"));
		
		Parent root = preloader.load();
		
        scene = new Scene(root,0,0);

        stage.setTitle("Les Colons de Catanes");
        stage.setScene(scene);   
        stage.setMaximized(true);
        stagePrincipal = stage;
        
        Serveur serveur = ConnexionManager.getStaticServeur();
        stagePrincipal.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				try {
					serveur.decrementeNbConnexions();
					System.exit(0);
				} catch (Exception e) {
					System.exit(0);
				}
			}
		});
        stage.show();
	}	
}

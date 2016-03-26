package client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author jerome
 */
public class VuePrincipale extends Application{
	
	
	public static Scene scene;
	public static Stage stagePrincipal;

	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader preloader = new FXMLLoader(getClass().getResource("/fxml/Accueil.fxml"));
		
		
		Parent root = preloader.load();

		
        scene = new Scene(root,0,0);
    
        
        stage.setTitle("Les Colons de Catanes");
        stage.setScene(scene);   
        stage.setMaximized(true);
        stagePrincipal = stage;
        stage.show();	
	}
}

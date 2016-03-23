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
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
		Parent root = loader.load();
		
        Scene scene = new Scene(root,0,0);
    
        stage.setTitle("Les Colons de Catanes");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();	
	}
}

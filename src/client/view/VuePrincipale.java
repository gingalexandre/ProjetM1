package client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class VuePrincipale extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainView.fxml"));
		Parent root = loader.load();
	    
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		
        Scene scene = new Scene(root, primaryScreenBounds.getMaxX(), primaryScreenBounds.getMaxY());
    
        stage.setTitle("Les Colons de Catanes");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();	
	}
}

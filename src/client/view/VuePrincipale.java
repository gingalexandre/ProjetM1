package client.view;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import serveur.modele.Message;
import serveur.reseau.ConnexionManager;
import serveur.reseau.Proxy;
import serveur.reseau.Serveur;

/**
 * 
 * @author jerome
 */
public class VuePrincipale extends Application{
	
	
	public static Scene scene;
	public static Stage stagePrincipal;
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader preloader = new FXMLLoader(getClass().getResource("fxml/Accueil.fxml"));
		
		Parent root = preloader.load();
		
        scene = new Scene(root,0,0);
    
        stage.setTitle("Les Colons de Catanes");
        stage.setScene(scene);   
        stage.setMaximized(true);
        stagePrincipal = stage;
        stage.show();
	}

	
}

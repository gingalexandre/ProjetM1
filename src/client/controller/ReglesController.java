package client.controller;

import client.view.VuePrincipale;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ReglesController {
	
	@FXML
	private Button boutonRegles;
	
	public void afficherRegles(){
				VuePrincipale v= new VuePrincipale();
				String path = new java.io.File("" ).getAbsolutePath(); 
				v.getHostServices().showDocument("file://"+ path + "/rules.pdf");
	}

}

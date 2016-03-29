package client.controller.application;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import serveur.ConnexionManager;
import serveur.Serveur;
import serveur.bdd.Utilisateur;

public class InscriptionController implements Initializable {

	private static final int MAXSIZE = 10;
	private static final int MINSIZE = 2;

	@FXML
	private TextField nomUtilisateur;

	@FXML
	private PasswordField mdp, mdpVerif;

	@FXML
	private Button validationInscription;

	@FXML
	private Label mdpErreur, utilisateurErreur;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	/**
	 * Méthode permettant de valider une inscription, si c'est bon elle ferme la fenêtre inscription
	 * @throws InterruptedException
	 * @throws RemoteException 
	 */
	@FXML
	public void validationInscription() throws InterruptedException, RemoteException {
		if (mdp.getText().equals(mdpVerif.getText()) && mdp.getText().length() < MAXSIZE
				&& mdp.getText().length() > MINSIZE && nomUtilisateur.getText().length() < MAXSIZE
				&& nomUtilisateur.getText().length() > MINSIZE) {
			Serveur serveur = ConnexionManager.getStaticServeur();
			int erreur = serveur.inscriptionBDD(nomUtilisateur.getText(), mdp.getText());
			switch (erreur) {
			case 0:
				utilisateurErreur.setText("Erreur d'accès à la base de données. Veuillez recommencer plus tard.");

			case 1:
				utilisateurErreur.setText("Nom d'utilisateur déjà  existant, veuillez recommencer.");
			case 2 :
				ConnexionController.inscriptionFenetre.close();
				
			default:
				utilisateurErreur.setText("Erreur d'accès à la base de données. Veuillez recommencer plus tard.");
			}
		} else {
			mdpErreur.setText("Mot de passe non identique ou pas assez long ou trop court. Vérifiez également la taille du nom d'utilisateur.");
		}
	}
}

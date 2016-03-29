package client.controller.application;

import java.net.URL;
import java.util.ResourceBundle;

import client.view.VuePrincipale;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import serveur.bdd.Utilisateur;

public class InscriptionController implements Initializable {

	private static final int MAXSIZE = 10;
	private static final int MINSIZE = 2;

	@FXML
	private TextArea utilisateur;

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
	 */
	@FXML
	public void validationInscription() throws InterruptedException {
		if (mdp.getText().equals(mdpVerif.getText()) && mdp.getText().length() < MAXSIZE
				&& mdp.getText().length() > MINSIZE && utilisateur.getText().length() < MAXSIZE
				&& utilisateur.getText().length() > MINSIZE) {
			Utilisateur user = new Utilisateur(utilisateur.getText(), mdp.getText());
			int erreur = user.inscription();
			switch (erreur) {
			case 0:
				utilisateurErreur.setText("Erreur d'accès à la base de données. Veuillez recommencer plus tard.");
				break;
			case 1:
				utilisateurErreur.setText("Nom d'utilisateur déjà existant, veuillez recommencer.");
				break;
			case 2 :
				AccueilController.inscriptionFenetre.close();
				break;
			default:
				utilisateurErreur.setText("Erreur d'accès à la base de données. Veuillez recommencer plus tard.");
			}
		} else {
			mdpErreur.setText("Mot de passe non identique ou pas assez long ou trop court. Vérifiez également la taille du nom d'utilisateur.");
		}
	}

}

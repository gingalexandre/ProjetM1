package client.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import serveur.reseau.serveur.ConnexionManager;
import serveur.reseau.serveur.Serveur;

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
	
	@FXML
	private DatePicker dateNaissance;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	/**
	 * M�thode permettant de valider une inscription, si c'est bon elle ferme la fen�tre inscription
	 * @throws InterruptedException
	 * @throws RemoteException 
	 */
	@FXML
	public void validationInscription() throws InterruptedException, RemoteException {
		if (mdp.getText().equals(mdpVerif.getText()) && mdp.getText().length() < MAXSIZE
				&& mdp.getText().length() > MINSIZE && nomUtilisateur.getText().length() < MAXSIZE
				&& nomUtilisateur.getText().length() > MINSIZE) {
			Serveur serveur = ConnexionManager.getStaticServeur();
			String erreur = serveur.getGestionnaireBDD().inscriptionBDD(nomUtilisateur.getText(), mdp.getText(), dateNaissance.getValue());
			utilisateurErreur.setText(erreur);
		} else {
			mdpErreur.setText("Mot de passe non identique ou pas assez long ou trop court. Vérifiez également la taille du nom d'utilisateur.");
		}
	}
}

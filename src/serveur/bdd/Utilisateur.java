package serveur.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilisateur {

	private String nomUtilisateur;

	private String mdp;
/**
 * Constructeur
 * @param nomUtilisateur : String : pseudo de l'utilisateur
 * @param mdp : String : mot de passe de l'utilisateur
 */
	public Utilisateur(String nomUtilisateur, String mdp) {
		super();
		this.nomUtilisateur = nomUtilisateur;
		this.mdp = mdp;
	}

	/**
	 * Méthode permettant la vérification de la connexion
	 * 
	 * @return boolean : Vrai si la connexion est effective, faux sinon
	 * @throws InterruptedException
	 */
	public boolean verificationConnexion() throws InterruptedException {
		try {
			Connection connection = Base.connexion();
			PreparedStatement prestmt = null;
			String query = "SELECT * FROM Joueur WHERE pseudo=? AND mdp=?;";
			prestmt = connection.prepareStatement(query);
			prestmt.setString(1, nomUtilisateur);
			prestmt.setString(2, mdp);
			ResultSet rs = prestmt.executeQuery();
			connection.close();
			// On test si la taille est égale à 1, si c'est le cas c'est qu'on a
			// bien l'utilisateur d'inscrit

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * MÃ©thode permettant l'inscription d'un utilisateur
	 * 
	 * @return int : 1 = nom déjà  existant ; 0 = problème avec la Base ; 2 =
	 *         inscription Ok
	 * @throws InterruptedException
	 */
	public boolean inscription() throws InterruptedException {
		if (this.verificationConnexion()) {
			return true;
		} else {
			Connection connection = Base.connexion();
			String query = "INSERT INTO Joueur(idJoueur, pseudo, mdp, nombrePartieGagnee, nombrePartieJouee) VALUES (NULL,?,?,0,0)";
			PreparedStatement prestmt;
			try {
				prestmt = connection.prepareStatement(query);
				prestmt.setString(1, nomUtilisateur);
				prestmt.setString(2, mdp);
				prestmt.executeUpdate();
				connection.commit();
				connection.close();
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

}

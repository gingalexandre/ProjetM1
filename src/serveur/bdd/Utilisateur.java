package serveur.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilisateur {

	private String nomUtilisateur;

	private String mdp;

	public Utilisateur(String nomUtilisateur, String mdp) {
		super();
		this.nomUtilisateur = nomUtilisateur;
		this.mdp = mdp;
	}

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
	
	
	public int inscription() throws InterruptedException{
		Connection connection = Base.connexion();
		PreparedStatement prestmt = null;
		String query = "SELECT * FROM Joueur WHERE pseudo=?;";
		
		try {
			prestmt = connection.prepareStatement(query);
			prestmt.setString(1, nomUtilisateur);
			ResultSet rs = prestmt.executeQuery();
			if(rs.next()){
				return 1;
			}
			else{
				query = "INSERT INTO Joueur(idJoueur, pseudo, mdp, nombrePartieGagnee, nombrePartieJouee) VALUES (NULL,?,?,0,0)";
				prestmt = connection.prepareStatement(query);
				prestmt.setString(1, nomUtilisateur);
				prestmt.setString(2, mdp);
				prestmt.executeUpdate();
				connection.commit();
				connection.close();
				return 2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}

}

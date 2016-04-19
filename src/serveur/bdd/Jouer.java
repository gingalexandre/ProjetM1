package serveur.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jouer {
	
	private int idJoueur;
	
	private int idPartie;

	public Jouer(int idJoueur, int idPartie) {
		super();
		this.idJoueur = idJoueur;
		this.idPartie = idPartie;
	}
	
	public void insererJouer() throws InterruptedException{
		Connection connection = Base.connexion();
		String query = "INSERT INTO Jouer(idJoueur, idPartie) VALUES (?,?)";
		PreparedStatement prestmt;
		try {
			prestmt = connection.prepareStatement(query);
			prestmt.setInt(1, idJoueur);
			prestmt.setInt(2, idPartie);
			prestmt.executeUpdate();
			connection.commit();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package serveur.bdd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import serveur.modele.Joueur;

public class Partie {

	private int idPartie;

	private String path;

	private String checksum;

	public Partie(int idPartie, String path, String contenu) {
		this.idPartie = idPartie;
		this.path = path;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.checksum = new String(md.digest(contenu.getBytes()));
	}

	public static Partie getById(int id) throws InterruptedException {
		Connection connection = Base.connexion();
		PreparedStatement prestmt = null;
		String query = "SELECT * FROM Partie WHERE idPartie=? ;";
		try {
			prestmt = connection.prepareStatement(query);
			prestmt.setInt(1, id);
			ResultSet rs = prestmt.executeQuery();
			if (rs.first() == true) {
				Partie partie = new Partie(rs.getInt("idPartie"), rs.getString("path"), rs.getString("checksum"));
				connection.close();
				return partie;
			} else {
				connection.close();
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static Partie getIdByChecksum(String path) throws InterruptedException {
		Connection connection = Base.connexion();
		PreparedStatement prestmt = null;
		String query = "SELECT * FROM Partie WHERE path=? ;";
		try {
			prestmt = connection.prepareStatement(query);
			prestmt.setString(1, path);
			ResultSet rs = prestmt.executeQuery();
			Partie partie = new Partie(rs.getInt("idPartie"), rs.getString("path"), rs.getString("checksum"));
			connection.close();
			return partie;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void insererPartie(String[] nomJoueurs) throws InterruptedException {
		Connection connection = Base.connexion();
		String query = "INSERT INTO Partie(idPartie, path, checksum) VALUES (NULL,?,?)";
		PreparedStatement prestmt;
		try {
			prestmt = connection.prepareStatement(query);
			prestmt.setString(1, path);
			prestmt.setString(2, checksum);
			prestmt.executeUpdate();
			connection.commit();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String joueur : nomJoueurs) {
			Utilisateur user = Utilisateur.getJoueurByName(joueur);
			Partie partie = Partie.getIdByChecksum(path);
			Jouer jouer = new Jouer(partie.getIdPartie(), user.getId());
			jouer.insererJouer();
		}
	}

	public int getIdPartie() {
		return idPartie;
	}

	public String getPath() {
		return path;
	}

	public String getChecksum() {
		return checksum;
	}

}

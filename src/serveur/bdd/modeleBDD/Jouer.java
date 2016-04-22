package serveur.bdd.modeleBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import serveur.bdd.Base;

/**
 * Classe représentant la table Jouer de la Base de Données
 * 
 * @author Alexandre
 *
 */
public class Jouer {
	/**
	 * Id du Joueur
	 */
	private int idJoueur;
	/**
	 * Id de la Partie
	 */
	private int idPartie;

	/**
	 * Constructeur
	 * 
	 * @param idJoueur
	 *            : id du Joueur
	 * @param idPartie
	 *            : id de la Partie
	 */
	public Jouer(int idJoueur, int idPartie) {
		super();
		this.idJoueur = idJoueur;
		this.idPartie = idPartie;
	}

	/**
	 * Méthode permettant l'insertion d'une ligne dans la table Jouer
	 * 
	 * @throws InterruptedException
	 */
	public void insererJouer() throws InterruptedException {
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
			e.printStackTrace();
		}
	}

}

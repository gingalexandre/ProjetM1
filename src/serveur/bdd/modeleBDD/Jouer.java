package serveur.bdd.modeleBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	 * @param idJoueur - id du Joueur
	 * @param idPartie - id de la Partie
	 */
	public Jouer(int idJoueur, int idPartie) {
		super();
		this.idJoueur = idJoueur;
		this.idPartie = idPartie;
	}

	/**
	 * Méthode permettant l'insertion d'une ligne dans la table Jouer
	 * @throws InterruptedException
	 */
	public void insererJouer() throws InterruptedException {
		Connection connection = Base.connexion();
		System.out.println(idJoueur);
		String query = "INSERT INTO Jouer(idJoueur, idPartie) VALUES (?,?);";
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
	
	/**
	 * Recupère la liste des parties avec l'id d'un joueur
	 * @param id
	 * @return a liste des parties avec l'id d'un joueur
	 * @throws InterruptedException
	 */
	public static ArrayList<Integer> recupererIdPartieByIdJoueur(int id) throws InterruptedException{
		Connection connection = Base.connexion();
		String query = "SELECT idPartie FROM Jouer WHERE idJoueur=? ;";
		PreparedStatement prestmt;
		try {
			prestmt = connection.prepareStatement(query);
			prestmt.setInt(1, id);
			ResultSet res = prestmt.executeQuery();
			ArrayList<Integer> listeId = new ArrayList<Integer>();
			while(res.next()){
				listeId.add(res.getInt("idPartie"));
			}
			connection.close();
			return listeId;

		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Integer>();
		}
	}
}

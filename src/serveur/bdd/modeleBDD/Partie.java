package serveur.bdd.modeleBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import serveur.bdd.Base;
import serveur.bdd.modeleSauvegarde.JoueurSauvegarde;
import serveur.bdd.modeleSauvegarde.PartieSauvegarde;
import serveur.commun.Fonctions;

/**
 * Classe permettant de représenter la Table partie de la Base de Données
 * 
 * @author Alexandre
 *
 */
public class Partie {
	/**
	 * id de la Partie
	 */
	private int idPartie;
	/**
	 * Chemin où se trouve le fichier de Sauvegarde
	 */
	private String path;
	/**
	 * Checksum pour s'assurer de l'intégrité du fichier
	 */
	private String checksum;

	/**
	 * Constructeur
	 * 
	 * @param idPartie
	 *            : int : Id de la Partie
	 * @param path
	 *            : String : chemin où se trouve le fichier de Sauvegarde
	 * @param contenu
	 *            : String : contenu du fichier pour faire le checksum
	 */
	public Partie(int idPartie, String path, String contenu) {
		this.idPartie = idPartie;
		this.path = path;
		// Création du checksum
		this.checksum = Fonctions.crypte(contenu);
	}


	/**
	 * Méthode permettant de retrouver une Partie par son id
	 * 
	 * @param id
	 *            : id de la partie à rechercher
	 * @return : Partie
	 * @throws InterruptedException
	 */
	public static Partie getById(int id) throws InterruptedException {
		Connection connection = Base.connexion();
		PreparedStatement prestmt = null;
		String query = "SELECT * FROM Partie WHERE idPartie=? ;";
		try {
			prestmt = connection.prepareStatement(query);
			prestmt.setInt(1, id);
			ResultSet rs = prestmt.executeQuery();
			if (rs.next() == true) {
				Partie partie = new Partie(rs.getInt("idPartie"), rs.getString("path"), rs.getString("checksum"));
				connection.close();
				return partie;
			} else {
				connection.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Méthode permettant de retrouver une Partie par son Path
	 * 
	 * @param path
	 *            : String : chemin de la partie recherchée
	 * @return Partie
	 * @throws InterruptedException
	 */
	public static Partie getPartieByPath(String path) throws InterruptedException {
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
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Méthode permettant l'insertion d'une Partie
	 * 
	 * @param joueurs
	 *            : Joueur jouant la partie (pour la liaison avec la table
	 *            Jouer)
	 * @throws InterruptedException
	 */
	public void insererPartie(ArrayList<JoueurSauvegarde> joueurs) throws InterruptedException {
		// Insertion d'une ligne Partie dans la Base de Données
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
			e.printStackTrace();
		}
		// Liaison de cette ligne avec la Table Jouer permettant la liaison avec
		// la Table Joueur
		for (JoueurSauvegarde joueur : joueurs) {
			Utilisateur user;
			user = Utilisateur.getJoueurByName(joueur.getNomUtilisateur());
			Partie partie = Partie.getPartieByPath(path);
			Jouer jouer = new Jouer(partie.getIdPartie(), user.getId());
			jouer.insererJouer();

		}
	}

	/**
	 * Getter de l'Id de la Partie
	 * 
	 * @return Integer
	 */
	public int getIdPartie() {
		return idPartie;
	}

	/**
	 * Getter de l'Id de la Partie
	 * 
	 * @return String
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Getter du Checksum de la Partie
	 * 
	 * @return String
	 */
	public String getChecksum() {
		return checksum;
	}

}

package serveur.bdd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Base {
	/**
	 * Fonction permettant la connexion à la base de données
	 * @param config 
	 * 
	 * @return l'objet de connexion à la base
	 * @throws InterruptedException
	 */
	public static Connection connexion() throws InterruptedException {
		Connection c = null;
		try {
			BufferedReader In = new BufferedReader(new FileReader("database.db"));
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:database.db");
				c.setAutoCommit(false);
			} catch (SQLException sql) {
				System.out.println(
						"Base de données indisponible, le serveur va tenter de se reconnecter d'ici 10 secondes. Merci de patienter.");
				Thread.sleep(10000);
				return Base.connexion();
			} catch (Exception e) {

				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			return c;
			// Dans le cas où le fichier n'existe pas
		} catch (FileNotFoundException fnfe) {
			try {
				c = DriverManager.getConnection("jdbc:sqlite:database.db");
				Base.creerBase();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Base.connexion();
		}

	}

	public static void creerBase() throws InterruptedException {
		Connection connection = Base.connexion();
		String query = "CREATE TABLE Joueur(idJoueur INTEGER PRIMARY KEY , pseudo TEXT NOT NULL , mdp TEXT NOT NULL , nombrePartieGagnee  INTEGER NOT NULL , nombrePartieJouee   INTEGER NOT NULL , dateNaissance Datetime NOT NULL);";
		PreparedStatement prestmt;
		try {
			prestmt = connection.prepareStatement(query);
			prestmt.executeUpdate();
			connection.commit();
			query = "CREATE TABLE Partie(idPartie  INTEGER PRIMARY KEY NOT NULL , path  TEXT NOT NULL , checksum  TEXT);";
			prestmt = connection.prepareStatement(query);
			prestmt.executeUpdate();
			connection.commit();
			query = "CREATE TABLE Jouer(idJoueur  INTEGER NOT NULL , idPartie  INTEGER NOT NULL , PRIMARY KEY (idJoueur,idPartie) , FOREIGN KEY (idJoueur) REFERENCES Joueur(idJoueur), FOREIGN KEY (idPartie) REFERENCES Partie(idPartie));";
			prestmt = connection.prepareStatement(query);
			prestmt.executeUpdate();
			connection.commit();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

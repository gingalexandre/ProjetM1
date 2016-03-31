package serveur.bdd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Base {
	/**
	 * Fonction permettant la connexion à la base de données
	 * 
	 * @return Connection : Objet de Connexion à la Base
	 * @throws InterruptedException 
	 */
	public static Connection connexion() throws InterruptedException {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:database.db");
			c.setAutoCommit(false);
		}catch (SQLException sql){
			System.out.println("Base de données indisponible, le serveur va tenter de se reconnecter d'ici 10 secondes. Merci de patienter.");
			Thread.sleep(10000);
			return Base.connexion();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return c;
	}
}

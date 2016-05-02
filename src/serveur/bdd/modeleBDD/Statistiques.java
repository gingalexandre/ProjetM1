package serveur.bdd.modeleBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import serveur.bdd.Base;

public class Statistiques {
	
	/**
	 * Méthode permettant de récupérer les Statistiques d'un Joueur à partir de son pseudo
	 * @param nomUtilisateur - pseudo du Joueur
	 * @return un tableau de int [nombrePartieJouee, nombrePartieGagnee]
	 */
	public static Integer[] getStatistiques(String nomUtilisateur){
		Integer[] res = new Integer[2];
		Connection connection;
		try {
			connection = Base.connexion();
			PreparedStatement prestmt = null;
			String query = "SELECT nombrePartieJouee, nombrePartieGagnee FROM Joueur WHERE pseudo=?;";
			prestmt = connection.prepareStatement(query);
			prestmt.setString(1, nomUtilisateur);
			ResultSet rs = prestmt.executeQuery();
			res[0] = rs.getInt(1);
			res[1] = rs.getInt(2);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Méthode permettant de mettre à jour les stats d'un Joueur à la fin de la partie
	 * @param victoire : int 0 si perdu, 1 si victoire
	 * @param nomUtilisateur : String : pseudo du Joueur
	 */
	public static void addStatistique(int victoire, String nomUtilisateur){
		Connection connection;
		Integer[] res = new Integer[2];
		try {
			// Récupération des données existantes
			res = Statistiques.getStatistiques(nomUtilisateur);
			// Mise à jour des données
			res[0] += 1;
			res[1] += victoire;
			connection = Base.connexion();
			PreparedStatement prestmt = null;
			String query = "UPDATE Joueur SET nombrePartieJouee=?, nombrePartieGagnee=?  WHERE pseudo=?;";
			prestmt = connection.prepareStatement(query);
			prestmt.setInt(1, res[0]);
			prestmt.setInt(2, res[1]);
			prestmt.setString(3, nomUtilisateur);
			prestmt.executeUpdate();
			connection.commit();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

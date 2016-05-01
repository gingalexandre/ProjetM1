package serveur.bdd.modeleBDD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import serveur.bdd.Base;

/**
 * Classe représentant la Table Utilisateur dans la Base de Données
 * 
 * @author Alexandre
 *
 */
public class Utilisateur {
	
	/**
	 * nom de l'Utilisateur
	 */
	private String nomUtilisateur;
	
	/**
	 * Mot de passe de l'Utilsateur
	 */
	private String mdp;
	
	/**
	 * Id de l'utilisateurs
	 */
	private int id;
	
	/**
	 * Date de Naissance de l'Utilisateur
	 */
	private LocalDate dateNaissance;

	/**
	 * Constructeur
	 * @param nomUtilisateur - pseudo de l'utilisateur
	 * @param mdp - mot de passe de l'utilisateur
	 */
	public Utilisateur(String nomUtilisateur, String mdp, LocalDate dateNaissance) {
		super();
		this.nomUtilisateur = nomUtilisateur;
		this.mdp = mdp;
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Constructeur
	 * @param id - id de l'utilisateur
	 * @param nomUtilisateur - nom de l'utilisateur
	 * @param mdp - mot de passe de l'utilisateur
	 * @param dateNaissance - date de naissance de l'utilisateur
	 */
	public Utilisateur(int id, String nomUtilisateur, String mdp, LocalDate dateNaissance) {
		super();
		this.id = id;
		this.nomUtilisateur = nomUtilisateur;
		this.mdp = mdp;
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Méthode permettant la vérification de la connexion
	 * @return vrai si la connexion est effective, faux sinon
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

			// On test si la taille est égale à 1, si c'est le cas c'est qu'on a
			// bien l'utilisateur d'inscrit

			if (rs.next()) {
				connection.close();
				return true;
			} else {
				connection.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	/**
	 * Méthode permettant l'inscription d'un utilisateur
	 * @return String : message à afficher (erreur ou non)
	 * @throws InterruptedException
	 */
	public String inscription() throws InterruptedException {
		if (this.verificationConnexion()) {
			return "Nom d'utilisateur déjà existant, veuillez recommencer.";
		} else {
			Connection connection = Base.connexion();
			String query = "INSERT INTO Joueur(idJoueur, pseudo, mdp, nombrePartieGagnee, nombrePartieJouee, dateNaissance) VALUES (NULL,?,?,0,0,?)";
			PreparedStatement prestmt;
			try {
				prestmt = connection.prepareStatement(query);
				prestmt.setString(1, nomUtilisateur);
				prestmt.setString(2, mdp);
				prestmt.setDate(3, Date.valueOf(dateNaissance));
				prestmt.executeUpdate();
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "Inscription réussie";
		}
	}

	/**
	 * Méthode permettant de récupérer la date de Naissance d'un Joueur à partir de son pseudo
	 * @param nom - nom du joueur dont on cherche la date de naissance
	 * @return la date de naissance du joueur cherché, null si n'existe pas
	 * @throws InterruptedException
	 */
	public static Date getDateNaissance(String nom) throws InterruptedException {
		try {
			Connection connection = Base.connexion();
			PreparedStatement prestmt = null;
			String query = "SELECT * FROM Joueur WHERE pseudo=? ;";
			prestmt = connection.prepareStatement(query);
			prestmt.setString(1, nom);
			ResultSet rs = prestmt.executeQuery();
			Date dateNaissance = rs.getDate("dateNaissance");
			connection.close();
			return dateNaissance;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Permet de récupérer un utilisateur grâce à son nom
	 * @param nom - nom de l'utilisateur
	 * @return l'utilisateur qui a le nom, null si il y a pas d'utilisateur avec ce nom
	 * @throws InterruptedException
	 */
	public static Utilisateur getJoueurByName(String nom) throws InterruptedException {
		Connection connection = Base.connexion();
		PreparedStatement prestmt = null;
		String query = "SELECT * FROM Joueur WHERE pseudo=? ;";
		try {
			prestmt = connection.prepareStatement(query);
			prestmt.setString(1, nom);
			ResultSet rs = prestmt.executeQuery();
			Instant instant = Instant.ofEpochMilli(rs.getDate("dateNaissance").getTime());
			LocalDateTime dateNaissance = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			Utilisateur utilisateur = new Utilisateur(rs.getInt("idJoueur"), rs.getString("pseudo"),
					rs.getString("mdp"), dateNaissance.toLocalDate());
			connection.close();
			return utilisateur;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Getter du nom de l'utilisateur
	 * @return String
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	/**
	 * Setter du nom de l'utilisateur
	 * @param nomUtilisateur - nouveau nom
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	/**
	 * Getter du mot de Passe de l'utilisateur
	 * @return String
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * Setter du mot de passe de l'utilisateur
	 * @param mdp
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	/**
	 * Getter de la date de naissance de l'utilisateur
	 * @return LocalDate
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * Setter de la date de Naissance de l'utilisateur
	 * @param dateNaissance - nouvelle date de naissance
	 */
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Getter de l'id de l'utilisateur
	 * @return Integer
	 */
	public int getId() {
		return id;
	}
}

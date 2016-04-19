package serveur.bdd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Utilisateur {

	private String nomUtilisateur;

	private String mdp;
	
	private int id;

	private LocalDate dateNaissance;

	/**
	 * Constructeur
	 * 
	 * @param nomUtilisateur
	 *            : String : pseudo de l'utilisateur
	 * @param mdp
	 *            : String : mot de passe de l'utilisateur
	 */
	public Utilisateur(String nomUtilisateur, String mdp, LocalDate dateNaissance) {
		super();
		this.nomUtilisateur = nomUtilisateur;
		this.mdp = mdp;
		this.dateNaissance = dateNaissance;
	}
	
	public Utilisateur(int id, String nomUtilisateur, String mdp, LocalDate dateNaissance) {
		super();
		this.id = id;
		this.nomUtilisateur = nomUtilisateur;
		this.mdp = mdp;
		this.dateNaissance = dateNaissance;
	}

	/**
	 * M�thode permettant la v�rification de la connexion
	 * 
	 * @return boolean : Vrai si la connexion est effective, faux sinon
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

			// On test si la taille est �gale � 1, si c'est le cas c'est qu'on a
			// bien l'utilisateur d'inscrit

			if (rs.next()) {
				connection.close();
				return true;
			} else {
				connection.close();
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * M�thode permettant l'inscription d'un utilisateur
	 * 
	 * @return String : message � afficher (erreur ou non)
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Inscription réussie";
		}
	}

	/**
	 * Méthode permettant de récupérer la date de Naissance d'un Joueur à partir
	 * de son pseudo
	 * 
	 * @param nom
	 *            : String : nom du joueur dont on cherche la date de naissance
	 * @return Date : date de naissance du joueur cherché, null si n'existe pas
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

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
			Utilisateur utilisateur = new Utilisateur(rs.getInt("idJoueur"), rs.getString("pseudo"), rs.getString("mdp"), dateNaissance.toLocalDate());
			connection.close();
			return utilisateur;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public int getId() {
		return id;
	}
	
	

	
}

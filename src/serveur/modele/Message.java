package serveur.modele;

import java.io.Serializable;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String auteur;
	private String message;
	private String couleur;
	private boolean system;
	
	public Message(String auteur, String message, String couleur) {
		super();
		this.auteur = auteur;
		this.message = message;
		this.couleur = couleur;
		this.system = false;
	}
	
	//Lorsqu'il s'agit d'un message Systeme
	public Message(String message){
		this.message = message;
		this.couleur = "Noir";
		this.system = true;
	}
	
	public boolean isSystem() {
		return system;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}

	public String getAuteur() {
		return auteur;
	}
	
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
}

package serveur.bdd;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import serveur.modele.Plateau;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.PartieInterface;
import serveur.reseau.ConnexionManager;
import serveur.reseau.JoueurServeur;
import serveur.reseau.Serveur;

public class PartieSauvegarde implements Serializable {

	private static final long serialVersionUID = 1L;

	private Plateau plateauCourant;
	
	private int idPartie;

	private ArrayList<JoueurSauvegarde> joueurs = new ArrayList<JoueurSauvegarde>();

	private JoueurSauvegarde joueurActuel;

	public PartieSauvegarde() {
		this.plateauCourant = Plateau.getInstance();
		Serveur serveur = ConnexionManager.getStaticServeur();
		ArrayList<JoueurServeur> joueursServeur = new ArrayList<JoueurServeur>();
		try {
			joueursServeur = serveur.getGestionnairePartie().recupererTousLesJoueurs();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (JoueurServeur js : joueursServeur) {

			try {

				JoueurSauvegarde joueur = new JoueurSauvegarde(js.getJoueur().getId(),
						js.getJoueur().getNomUtilisateur(), js.getJoueur().getDateDeNaissance(),
						js.getJoueur().getCouleur(), js.getJoueur().getPret(), js.getJoueur().getPointVictoire(),
						js.getJoueur().getNbColonie(), js.getJoueur().getNbVille(), js.getJoueur().getNbVille(),
						js.getJoueur().getStockRessource(), js.getJoueur().getCartes());
				this.joueurs.add(joueur);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		JoueurInterface joueurInterfaceActuel;
		try {
			PartieInterface partie =  ((PartieInterface) serveur.getGestionnairePartie().getPartie());
			joueurInterfaceActuel = partie.getJoueurTour();
			this.joueurActuel = new JoueurSauvegarde(joueurInterfaceActuel.getId(),
					joueurInterfaceActuel.getNomUtilisateur(), joueurInterfaceActuel.getDateDeNaissance(),
					joueurInterfaceActuel.getCouleur(), joueurInterfaceActuel.getPret(),
					joueurInterfaceActuel.getPointVictoire(), joueurInterfaceActuel.getNbColonie(),
					joueurInterfaceActuel.getNbVille(), joueurInterfaceActuel.getNbRoute(),
					joueurInterfaceActuel.getStockRessource(), joueurInterfaceActuel.getCartes());
			this.idPartie = partie.getId();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setPlateauCourant(Plateau plateauCourant) {
		this.plateauCourant = plateauCourant;
	}

	public void setJoueurs(ArrayList<JoueurSauvegarde> joueurs) {
		this.joueurs = joueurs;
	}

	public void setJoueurActuel(JoueurSauvegarde joueurActuel) {
		this.joueurActuel = joueurActuel;
	}

	public Plateau getPlateauCourant() {
		return plateauCourant;
	}

	public ArrayList<JoueurSauvegarde> getJoueurs() {
		return joueurs;
	}

	public JoueurSauvegarde getJoueurActuel() {
		return joueurActuel;
	}

	public int getIdPartie() {
		return idPartie;
	}

	public void setIdPartie(int idPartie) {
		this.idPartie = idPartie;
	}

	
}

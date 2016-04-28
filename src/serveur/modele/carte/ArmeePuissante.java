package serveur.modele.carte;

/**
 * Classe représente la Carte de l'armee la plus puissante.
 * @author Yohann Hugo
 */
public class ArmeePuissante implements Carte {
	
	private String nomCarte = "Armée la plus puissante";
	
	private static final long serialVersionUID = 1L;

	/**
     * Attribut : condition minimal requise.
     */
    private static int NB_CHEVALIER_MINIMAL = 3;

    /**
     * Attribut chemin vers la ressources image.
     */
    private static String CHEMIN = "/Ressources/cartes/armee_puissante.png";

    /**
     * Constructeur
     */
    public ArmeePuissante(){
    }

    /**
     * Action provoquer lorsqu'un joueur reçoit la carte d'armée la plus puissant.
     */
    @Override
    public void doAction(){
    /*
        currentPlayer.setPointVictoire(currentPlayer.getPointVictoire()+2);
        f(Player!=null) Player.setPointVictoire(Player.getPointVictoire()-2);*/
    }

    /**
     * Méthode de l'interface Carte qui permet de récupérer le chemin de la ressources image d'une carte.
     *
     * @return chemin de la ressource de l'image.
     */
    @Override
    public String getCheminImage() {
        return CHEMIN;
    }

	@Override
	public String getNom() {
		return this.nomCarte;
	}
}

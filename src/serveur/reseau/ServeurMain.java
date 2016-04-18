package serveur.reseau;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class ServeurMain {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(42000);
		System.setProperty("java.rmi.server.hostname", "127.0.0.1");
		if(argumentCorrect(args)){
			Naming.rebind("rmi://127.0.0.1:42000/serveur", new ServeurImpl(Integer.parseInt(args[0])));
			System.out.println("Serveur lancé correctement");
		}
	}
	
	/**
	 * Vérifie que args a la bonne valeur
	 * @param args
	 * @return
	 */
	public static boolean argumentCorrect(String[] args){
		if(args.length > 0 && args.length < 2){
			if(isNumeric(args[0])){
				if(Integer.parseInt(args[0]) <= 4 && Integer.parseInt(args[0]) > 2){
					return true;
				}
				else{
					System.out.println("L'argument doit etre un chiffre egal a 3 ou 4.\nRed�marrez le serveur et r�essayer.");
					return false;
				}
			}
			else{
				System.out.println("L'argument doit �tre un chiffre egal à 3 ou 4.\nRed�marrez le serveur et r�essayer.");
				return false;
			}
		}
		else{
			System.out.println("Veuillez rentrer un SEUL argument (un chiffre correspondant au nombre maximum de joueurs) lors du lancement du serveur.\nRed�marrez le serveur et r�essayer.");
			return false;
		}
	}
	
	/**
	 * Vérifie que le string est un nombre
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}
}

package test.serveur.modele;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import serveur.modele.Joueur;
import serveur.modele.Point;
import serveur.modele.Route;
import serveur.modele.Ville;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;

public class TestRoute {
	
	static Route r1;
	static Route r2;
	static Route r3;
	static Route r4;
	static Route r5;
	static Route r6;
	static Route r7;
	static Route r8;
	static Route r9;
	static Route r10;
	
	static Ville v1;
	static Ville v2;
	static Ville v3;
	static Ville v4;
	static Ville v5;
	static Ville v6;
	static Ville v7;
	static Ville v8;
	

	@BeforeClass
	public static void init() throws RemoteException{
		// On crée un rectangle qui est en fait 3 carré l'un a coté de l'autre
		r1 = new Route(new Point(1.0,1.0) ,new Point(2.0,1.0));
		r2 = new Route(new Point(2.0,1.0) ,new Point(3.0,1.0));
		r3 = new Route(new Point(3.0,1.0) ,new Point(4.0,1.0));
		r4 = new Route(new Point(1.0,1.0) ,new Point(1.0,2.0));
		r5 = new Route(new Point(2.0,1.0) ,new Point(2.0,2.0));
		r6 = new Route(new Point(3.0,1.0) ,new Point(3.0,2.0));
		r7 = new Route(new Point(4.0,1.0) ,new Point(4.0,2.0));
		r8 = new Route(new Point(1.0,2.0) ,new Point(2.0,2.0));
		r9 = new Route(new Point(2.0,2.0) ,new Point(3.0,2.0));
		r10 = new Route(new Point(3.0,2.0) ,new Point(4.0,2.0));
		
		v1 = new Ville(new Point(1.0,1.0));
		v2 = new Ville(new Point(2.0,1.0));
		v3 = new Ville(new Point(3.0,1.0));
		v4 = new Ville(new Point(4.0,1.0));
		v5 = new Ville(new Point(1.0,2.0));
		v6 = new Ville(new Point(2.0,2.0));
		v7 = new Ville(new Point(3.0,2.0));
		v8 = new Ville(new Point(4.0,2.0));
		v1.ajouterRoute(r1);
		v1.ajouterRoute(r4);
		
		v2.ajouterRoute(r1);
		v2.ajouterRoute(r5);
		v2.ajouterRoute(r2);
		
		v3.ajouterRoute(r2);
		v3.ajouterRoute(r6);
		v3.ajouterRoute(r3);
		
		v4.ajouterRoute(r3);
		v4.ajouterRoute(r7);
		
		v5.ajouterRoute(r4);
		v5.ajouterRoute(r8);
		
		v6.ajouterRoute(r8);
		v6.ajouterRoute(r9);
		v6.ajouterRoute(r5);
		
		v7.ajouterRoute(r9);
		v7.ajouterRoute(r6);
		v7.ajouterRoute(r10);
		
		v8.ajouterRoute(r10);
		v8.ajouterRoute(r7);
	}
	
	@Before
	public void reinit() throws RemoteException{
		r1.setOQP(null);
		r2.setOQP(null);
		r3.setOQP(null);
		r4.setOQP(null);
		r5.setOQP(null);
		r6.setOQP(null);
		r7.setOQP(null);
		r8.setOQP(null);
		r9.setOQP(null);
		r10.setOQP(null);
		
		v1.setOQP(null);
		v2.setOQP(null);
		v3.setOQP(null);
		v4.setOQP(null);
		v5.setOQP(null);
		v6.setOQP(null);
		v7.setOQP(null);
		v8.setOQP(null);
		
	}
	
	/**
	 * Test de la méthode permettant de verifier l'égalité de deux routes
	 * @throws RemoteException 
	 */
	@Test
	public void testEquals() throws RemoteException {

		Route r = new Route(new Point(1.0, 1.0), new Point(2.0, 2.0));
		Route r2 = new Route(new Point(3.0, 3.0), new Point(4.0, 4.0));
		
		assertFalse(r.equals(r2));
		assertFalse(r.equals(null));
		assertFalse(r.equals(new Object()));
		assertFalse(r.equals(new Route(null,null)));
		assertTrue(r.equals(new Route(new Point(1.0,1.0),new Point(2.0,2.0))));
	}
	
	@Test
	public void testIsExtremite() throws RemoteException{
		Joueur j = new Joueur("Toto");
		ArrayList<VilleInterface> listville = new ArrayList<>();
		listville.add(v1);
		listville.add(v2);
		listville.add(v3);
		listville.add(v4);
		listville.add(v5);
		listville.add(v6);
		listville.add(v7);
		listville.add(v8);
		HashMap<Point, VilleInterface> villes = new HashMap<>();
		for (VilleInterface v : listville) villes.put(v.getEmplacement(),v);
		r1.setOQP(j);
		r2.setOQP(j);
		assertTrue(r1.isExtremite(villes)==1);
		assertTrue(r2.isExtremite(villes)==-1);
		r3.setOQP(j);
		assertTrue(r3.isExtremite(villes)==-1);
		assertTrue(r2.isExtremite(villes)==0);
		assertTrue(r1.isExtremite(villes)==1);
	}
	
	@Test
	public void testGetSuccesseur() throws RemoteException{
		Joueur j = new Joueur("toto");
		r1.setOQP(j);
		ArrayList<VilleInterface> listville = new ArrayList<>();
		listville.add(v1);
		listville.add(v2);
		listville.add(v3);
		listville.add(v4);
		listville.add(v5);
		listville.add(v6);
		listville.add(v7);
		listville.add(v8);
		ArrayList<RouteInterface> routes = new ArrayList<>();
		routes.add(r1);
		routes.add(r2);
		routes.add(r3);
		routes.add(r4);
		routes.add(r5);
		routes.add(r6);
		routes.add(r7);
		routes.add(r8);
		routes.add(r9);
		routes.add(r10);
		HashMap<Point, VilleInterface> villes = new HashMap<>();
		for (VilleInterface v : listville) villes.put(v.getEmplacement(),v);
		ArrayList<RouteInterface> r = r1.getSuccesseurs(r1.getDepart(), j, villes, new HashSet<RouteInterface>());
		assertTrue(r.size()==0);
		r2.setOQP(j);
		r = r1.getSuccesseurs(r1.getArrive(), j, villes, new HashSet<RouteInterface>());
		assertTrue(r.size()==1 && r.get(0).equals(r2));
		r = r1.getSuccesseurs(r1.getDepart(), j, villes, new HashSet<RouteInterface>());
		assertTrue(r.size()==0);
		HashSet<RouteInterface> hs = new HashSet<RouteInterface>();
		hs.add(r2);
		r = r1.getSuccesseurs(r1.getArrive(), j, villes,hs);
		assertTrue(r.size()==0);
		r = r2.getSuccesseurs(r2.getDepart(), j, villes, new HashSet<RouteInterface>());
		assertTrue(r.size()==1 && r.get(0).equals(r1));
		r6.setOQP(j);
		r3.setOQP(j);
		r = r2.getSuccesseurs(r2.getArrive(), j, villes, new HashSet<RouteInterface>());
		assertTrue(r.size()==2 && r.contains(r6) && r.contains(r3));
	}
	
	@Test
	public void testRoutesLaPlusLongue() throws RemoteException {
		Joueur j = new Joueur("toto");
		ArrayList<VilleInterface> listville = new ArrayList<>();
		listville.add(v1);
		listville.add(v2);
		listville.add(v3);
		listville.add(v4);
		listville.add(v5);
		listville.add(v6);
		listville.add(v7);
		listville.add(v8);
		ArrayList<RouteInterface> routes = new ArrayList<>();
		routes.add(r1);
		routes.add(r2);
		routes.add(r3);
		routes.add(r4);
		routes.add(r5);
		routes.add(r6);
		routes.add(r7);
		routes.add(r8);
		routes.add(r9);
		routes.add(r10);
		
		/* TEST SUR UNE ROUTE DE 1 */
		System.out.println("=========   TEST SUR 1 ROUTE DE TAILLE 1  =========");
		r1.setOQP(j);
		int size = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(size==1);
		
		/* TEST SUR UNE ROUTE DE 2 */
		System.out.println("=========   TEST SUR 1 ROUTE DE DEUX  =========");
		r2.setOQP(j);
		size = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(size==2);
		
		/* TEST SUR UNE ROUTE DE 3 */
		System.out.println("=========   TEST SUR 1 ROUTE DE TROIS  =========");
		r6.setOQP(j);
		size = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(size==3);
		
		/* TEST SUR 2 ROUTE DE 2 */
		System.out.println("=========   TEST SUR 2 ROUTES DE DEUX  =========");
		r6.setOQP(null);
		r9.setOQP(j);
		r10.setOQP(j);
		size = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(size==2);
		
		reinit();
		/* TESTE SUR UNE ROUTE QUI BOUCLE BOUCLE */ 
		System.out.println("=========   TEST SUR UNE ROUTE QUI BOUCLE  =========");
		r1.setOQP(j);
		r2.setOQP(j);
		r3.setOQP(j);
		r7.setOQP(j);
		r10.setOQP(j);
		r9.setOQP(j);
		r8.setOQP(j);
		r4.setOQP(j);
		size = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(size==8);
		
		reinit();
		/* TEST SUR UNE ROUTE QUI FINI PAR UNE BOUCLE */
		System.out.println("=========   TEST SUR UNE ROUTE QUI BOUCLE  =========");
		r1.setOQP(j);
		r2.setOQP(j);
		r6.setOQP(j);
		r10.setOQP(j);
		r7.setOQP(j);
		r3.setOQP(j);
		size = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(size == 6);
		
		reinit();
		/* TEST SUR DEUX ROUTES DE TAILLE 2 ET 3 */
		System.out.println("=========   TEST SUR 2 ROUTES DE TAILLE 2 ET 3  =========");
		r1.setOQP(j);
		r2.setOQP(j);
		r8.setOQP(j);
		r9.setOQP(j);
		r10.setOQP(j);
		size = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(size==3);
		
		reinit();
		/* TEST SUR UNE ROUTE DE TAILLE 5 AVEC UNE DE MES COLONIE AU MILLIEU */
		r1.setOQP(j);
		r5.setOQP(j);
		r9.setOQP(j);
		r6.setOQP(j);
		r3.setOQP(j);
		size = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(size==5);
		v6.setOQP(j);
		size = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(size==5);
		
		/* TEST AVEC TEST SUR UNE ROUTE DE TAILLE 5 AVEC UNE COLONIE ADVERSE AU MILIEU */
		v6.setOQP(new Joueur("Bob"));
		size = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(size == 3);
		
	}
	
	/* On recopie du code de Plateau pour tester la route la plus longue */
	public int calculerRouteLaPlusLongue(JoueurInterface j , ArrayList<VilleInterface> villes, ArrayList<RouteInterface> routes) throws RemoteException{
		HashMap<Point,VilleInterface> ville = new HashMap<Point,VilleInterface>();
		for (VilleInterface v : villes){
			ville.put(v.getEmplacement(),v);
		}
		ArrayList<RouteInterface> extremites = new ArrayList<RouteInterface>();
		ArrayList<RouteInterface> routesDuJoueur = new ArrayList<RouteInterface>();
		for (RouteInterface r : routes){
			if (r.getOqp()!= null && r.getOqp().equals(j)){
				routesDuJoueur.add(r);
				if (r.isExtremite(ville) != 0){
					extremites.add(r);
				}
			}
		}
		// Cas ou on a une boucle on prend une route au hasard pour debuter
		ArrayList<Integer> resultats = new ArrayList<Integer>();
		if (extremites.size()>0){
			for(RouteInterface r : extremites){
				Point extremite = (r.isExtremite(ville)>0) ? r.getArrive() : r.getDepart(); 
				chercherToutesLesRoutes(ville, extremite,r,new HashSet<RouteInterface>(), 0,resultats,j);
			}
		}
		else {
			Point extremite = (routesDuJoueur.get(0).isExtremite(ville)>0) ? routesDuJoueur.get(0).getArrive() : routesDuJoueur.get(0).getDepart(); 
			chercherToutesLesRoutes(ville, extremite,routesDuJoueur.get(0),new HashSet<RouteInterface>(), 0,resultats,j);
		}
		Collections.sort(resultats);
		return resultats.get(resultats.size()-1);
	} 
	
	private void chercherToutesLesRoutes(HashMap<Point,VilleInterface> villes, Point extremite,RouteInterface current,Set<RouteInterface> visites, int size, ArrayList<Integer> res,JoueurInterface j) throws RemoteException {
		size++;
		visites.add(current);
		int isExtremite = current.isExtremite(villes);
		if (isExtremite!=0){
			if (size==1){
				Point prochaineExtremite = (extremite.equals(current.getDepart()))? current.getDepart():current.getArrive();
				ArrayList<RouteInterface> successeur =current.getSuccesseurs(prochaineExtremite, j, villes, visites);
				if (successeur.size()!=0){
					for (RouteInterface r : successeur){
						chercherToutesLesRoutes(villes, prochaineExtremite, r, visites, size, res, j);
						size--;
					}
				}else {
					res.add(size);
					size--;
				}
			}else{
				res.add(size);
				size--;
			}
		}
		else {
			Point prochaineExtremite = (extremite.equals(current.getDepart()))?current.getArrive():current.getDepart();
			ArrayList<RouteInterface> successeur = current.getSuccesseurs(prochaineExtremite,j,villes,visites);
			if (successeur.size()!=0){
				for (RouteInterface r : successeur){
					chercherToutesLesRoutes(villes, prochaineExtremite, r, visites, size, res, j);
					size--;
				}
			}else {
				res.add(size);
				size--;
			}
		}
	}

}

package test.serveur.modele;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import serveur.modele.Joueur;
import serveur.modele.Point;
import serveur.modele.Route;
import serveur.modele.Ville;
import serveur.modele.service.JoueurInterface;
import serveur.modele.service.RouteInterface;
import serveur.modele.service.VilleInterface;

public class TestRoute {

	/**
	 * Test de la méthode permettant de voir si deux Routes sont identiques
	 * @throws RemoteException 
	 */
	@Test
	public void testEqualsRoute() throws RemoteException {

		Route r = new Route(new Point(1.0, 1.0), new Point(2.0, 2.0));
		Route r2 = new Route(new Point(3.0, 3.0), new Point(4.0, 4.0));
		
		assertTrue(r.equals(r));
		assertFalse(r.equals(r2));
	}
	
	/**
	 * Test de la méthode permettant de comparer deux Routes
	 * @throws RemoteException 
	 */
	@Test
	public void testCompareRoute() throws RemoteException {

		Route r = new Route(new Point(1.0, 1.0), new Point(2.0, 2.0));
		Route r2 = new Route(new Point(3.0, 3.0), new Point(4.0, 4.0));
		
		/*assertEquals(r.compareTo(r),0);
		assertEquals(r.compareTo(r2),10);
		assertEquals(r2.compareTo(r),-10);
		
		Route r3 = new Route(new Point(1.0, 1.0), new Point(2.0, 3.0));
		assertEquals(r.compareTo(r3),10);
		assertEquals(r3.compareTo(r),-10);*/
	}
	
	@Test
	public void testRoutesLaPlusLongue() throws RemoteException {
		Route r1 = new Route(new Point(1.0,1.0) ,new Point(2.0,1.0));
		Route r2 = new Route(new Point(2.0,1.0) ,new Point(3.0,1.0));
		Route r3 = new Route(new Point(3.0,1.0) ,new Point(4.0,1.0));
		Route r4 = new Route(new Point(1.0,1.0) ,new Point(1.0,2.0));
		Route r5 = new Route(new Point(2.0,1.0) ,new Point(2.0,2.0));
		Route r6 = new Route(new Point(3.0,1.0) ,new Point(3.0,2.0));
		Route r7 = new Route(new Point(4.0,1.0) ,new Point(4.0,2.0));
		Route r8 = new Route(new Point(1.0,2.0) ,new Point(2.0,2.0));
		Route r9 = new Route(new Point(2.0,2.0) ,new Point(3.0,2.0));
		Route r10 = new Route(new Point(3.0,2.0) ,new Point(4.0,2.0));
		
		Ville v1 = new Ville(new Point(1.0,1.0));
		Ville v2 = new Ville(new Point(2.0,1.0));
		Ville v3 = new Ville(new Point(3.0,1.0));
		Ville v4 = new Ville(new Point(4.0,1.0));
		Ville v5 = new Ville(new Point(1.0,2.0));
		Ville v6 = new Ville(new Point(2.0,2.0));
		Ville v7 = new Ville(new Point(3.0,2.0));
		Ville v8 = new Ville(new Point(4.0,2.0));
		
		Joueur j = new Joueur();
		
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
		
		r1.setOQP(j);
		r2.setOQP(j);
		r6.setOQP(j);
		r9.setOQP(j);
		r8.setOQP(j);
		r4.setOQP(j);
		v1.setOQP(j);
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
		assertTrue(r1.isExtremite(villes)==1);
		HashSet<RouteInterface> visite = new HashSet<>();
		visite.add(r1);
		int nbsr1 = r1.getSuccesseurs(r1.getArrive(), j, villes, visite).size();
		assertTrue(nbsr1==1);
		assertTrue(r1.getSuccesseurs(r1.getArrive(), j, villes, visite).get(0).equals(r2));
		RouteInterface route = r2.getSuccesseurs(r2.getArrive(), j, villes, visite).get(0);
		assertEquals(route,r6);
		// Tentative avec une boucle, l
		int i = calculerRouteLaPlusLongue(j, listville, routes);
		assertTrue(i==6);
	}
	
	/* On recopie du code des Plateau pour tester la route la plus longue */
	public int calculerRouteLaPlusLongue(JoueurInterface j , ArrayList<VilleInterface> villes, ArrayList<RouteInterface> routes) throws RemoteException{
		HashMap<Point,VilleInterface> ville = new HashMap<Point,VilleInterface>();
		for (VilleInterface v : villes){
			ville.put(v.getEmplacement(),v);
		}
		ArrayList<RouteInterface> extremites = new ArrayList<RouteInterface>();
		for (RouteInterface r : routes){
			if (r.getOqp()!= null && r.getOqp().equals(j)){
				if (r.isExtremite(ville) != 0){
					extremites.add(r);
				}
			}
		}
		ArrayList<Integer> resultats = new ArrayList<Integer>();
		for(RouteInterface r : extremites){
			Point extremite = (r.isExtremite(ville)>0) ? r.getArrive() : r.getDepart(); 
			chercherToutesLesRoutes(ville, extremite,r,new HashSet<RouteInterface>(), 0,resultats,j);
		}	
		Collections.sort(resultats);
		System.out.println(resultats);
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
						//size--;
					}
				}else {
					res.add(size);
					//size--;
				}
			}else{
				res.add(size);
				//size--;
			}
		}
		else {
			Point prochaineExtremite = (extremite.equals(current.getDepart()))?current.getArrive():current.getDepart();
			ArrayList<RouteInterface> successeur = current.getSuccesseurs(prochaineExtremite,j,villes,visites);
			if (successeur.size()!=0){
				for (RouteInterface r : successeur){
					chercherToutesLesRoutes(villes, prochaineExtremite, r, visites, size, res, j);
					//size--;
				}
			}else {
				res.add(size);
				//size--;
			}
		}
	}

}

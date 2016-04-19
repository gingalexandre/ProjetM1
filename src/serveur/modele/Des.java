/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur.modele;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import serveur.modele.service.DesInterface;

/**
 *
 * @author Arthur
 */
public class Des extends UnicastRemoteObject implements DesInterface {
    
	private static final long serialVersionUID = 1L;
	
	private int de1;
    private int de2;
    
    public Des() throws RemoteException{};
    
    public Integer[] lancerDes(){
        this.de1 = (int) (Math.random() * (7-1) + 1);
        this.de2 = (int) (Math.random() * (7-1) + 1);
        Integer[] res = new Integer[2];
        res[0] = de1;
        res[1] = de2;
        return res;
    }
    
    public void actionDes(int scoreDe){
        if(scoreDe == 7){
            //voleur
        }
        else{
            //generationRessourceParCase(scoreDe);
        }
    }
}

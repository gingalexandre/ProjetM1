/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.modeles;

/**
 *
 * @author Arthur
 */
public class Des {
    
    private int de1;
    private int de2;
    
    public void lancerDes(){
        this.de1 = (int) (Math.random() * (6-1) + 1);
        this.de2 = (int) (Math.random() * (6-1) + 1);
        actionDes(de1+de2);
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

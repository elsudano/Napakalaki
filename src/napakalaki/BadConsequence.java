/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package napakalaki;


import java.util.ArrayList;



/**
 *
 * @author 
 * 
 */
public class BadConsequence {
    private String text="";
    private int levels =0;
    private int nVisibleTreasures =0;
    private int nHiddenTreasures =0;
    private boolean death = false;
    private ArrayList<TreasureKind> specificHiddenTreasures=new ArrayList();
    private ArrayList<TreasureKind> specificVisibleTreasures=new ArrayList();
    
    
    public int getLevels() {
        return levels;
    }
    
    public int getNVisibleTreasures() {
        return nVisibleTreasures;
    }

    public int getNHiddenTreasures() {
        return nHiddenTreasures;
    }
    
    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return specificHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return specificVisibleTreasures;
    }
    
    public BadConsequence(String t, int l, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden){
        levels = l;
        text = t;
        specificHiddenTreasures=tHidden;
        specificVisibleTreasures=tVisible;
    }

    
    @Override
    public String toString(){
        return "Texto: "+text +", "+
               "Niveles: "+Integer.toString(levels)+", "+
               "Numero de Tesoros Visibles: "+Integer.toString(nVisibleTreasures)+", "+
               "Numero de Tesoros Ocultos: "+Integer.toString(nHiddenTreasures)+", "+
               "Muerte: "+Boolean.toString(death)+", "+
               "Tesoros Ocultos: "+specificHiddenTreasures.toString()+", "+
               "Tesoros Visibles: "+specificVisibleTreasures.toString();
    }
    
}



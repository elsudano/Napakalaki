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
    
    public BadConsequence(String text, int levels, int nVisible, int nHidden){
        this.text = text;
        this.levels = levels;
        this.nVisibleTreasures=nVisible;
        this.nHiddenTreasures=nHidden;
    }
    
    public BadConsequence(String text, int levels, ArrayList<TreasureKind> tVisible,ArrayList<TreasureKind> tHidden){
        this.text = text;
        this.levels = levels;
        this.specificVisibleTreasures = tVisible;
        this.specificHiddenTreasures = tHidden;
    }

    public BadConsequence(String text, boolean death){
        this.text = text;
        this.death = death;
    }
    
    public String getText() {
        return this.text;
    }
    
    public int getLevels() {
        return this.levels;
    }
    
    public int getNVisibleTreasures() {
        return this.nVisibleTreasures;
    }

    public int getNHiddenTreasures() {
        return this.nHiddenTreasures;
    }
    
    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return this.specificHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return this.specificVisibleTreasures;
    }
    
    @Override
    public String toString(){
        return "Texto: "+this.text +", "+
               "Niveles: "+Integer.toString(this.levels)+", "+
               "Numero de Tesoros Visibles: "+Integer.toString(this.nVisibleTreasures)+", "+
               "Numero de Tesoros Ocultos: "+Integer.toString(this.nHiddenTreasures)+", "+
               "Muerte: "+Boolean.toString(this.death)+", "+
               "Tesoros Ocultos: "+this.specificHiddenTreasures.toString()+", "+
               "Tesoros Visibles: "+this.specificVisibleTreasures.toString();
    }
    
}
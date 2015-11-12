
package napakalaki;

import java.util.ArrayList;
import napakalaki.Napakalaki;
import napakalaki.Treasure;
import napakalaki.TreasureKind;

/**
 * Clase de Mal rollo se encarga de incluir las ociones
 * negativas del juego así cuando se pierde un combate. 
 * @authors:
 * Carlos de la Torre 75145459C
 * Farouk
 */
public class BadConsequence {
    static final int MAXTREASURES = 10;
    private String text; //Texto
    private int levels; //Niveles perdidos
    private int nVisibleTreasures; //Visibles perdidos
    private int nHiddenTreasures; //Ocultos perdidos
    private boolean death; //muerte
    
    private final ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList(); //tipo oculto perdidos
    private final ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList(); //tipo visible perdidos
    
    /**
     * Constructor de Clase, inicializa los valores del objeto
     * con parámetros de entrada:
     */
    public BadConsequence(){
        this.text = "";
        this.levels = this.nVisibleTreasures = this.nHiddenTreasures = 0;
        death = false;
    }
    
    /**
     * Constructor con parámetros, inicializa los valores del objeto
     * con parámetros de entrada:
     * @param text numero entero que indica cantidad de tesoros
     * @param levels numero que indica la cantidad de niveles
     * @param nHiddenTreasures numero de tesoros ocultos
     * @param nVisibleTreasures numero de tesoros visibles
     * @param death verdadero o falso si hay muerte
     * @param tVisible array con los tesoros visibles
     * @param tHidden array con los tesoros ocultos
     */
    public BadConsequence(String text, int levels, int nHiddenTreasures, int nVisibleTreasures, boolean death, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden){
        this.text = text;
        this.levels = levels;
        this.nHiddenTreasures = nHiddenTreasures;
        this.nVisibleTreasures = nVisibleTreasures;
        this.death = death;
        this.specificVisibleTreasures.addAll(tVisible);
        this.specificHiddenTreasures.addAll(tHidden);
    }
    
    /**
     * Constructor con parámetros, inicializa los valores del objeto
     * con parámetros de entrada:
     * @param text numero entero que indica cantidad de tesoros
     * @param levels numero que indica la cantidad de niveles
     * @param nHiddenTreasures numero de tesoros ocultos
     * @param nVisibleTreasures numero de tesoros visibles
     */
    public BadConsequence (String text, int levels, int nVisibleTreasures, int nHiddenTreasures) {
        this.text = text;
        this.levels = levels;
        this.nVisibleTreasures = nVisibleTreasures;
        this.nHiddenTreasures = nHiddenTreasures;
    }
    
    /**
     * Constructor con parámetros, inicializa los valores del objeto
     * con parámetros de entrada:
     * @param text numero entero que indica cantidad de tesoros
     * @param levels numero que indica la cantidad de niveles
     * @param tVisible array con los tesoros visibles
     * @param tHidden array con los tesoros ocultos
     */
    public BadConsequence (String text, int levels, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden) {
        this.text = text;
        this.levels = levels;
        this.specificVisibleTreasures.addAll(tVisible);
        this.specificHiddenTreasures.addAll(tHidden);
    }
    
    /**
     * Constructor con parámetros, inicializa los valores del objeto
     * con parámetros de entrada:
     * @param text numero entero que indica cantidad de tesoros
     * @param death verdadero o falso si hay muerte
     */
    public BadConsequence (String text, boolean death) {
        this.text = text;
        this.death = death;
    }
    
    /**
     * Podemos preguntar si el mal rollo esta lleno o esta vacio
     * osea si los tesoros visibles y los ocultos estan vacios.
     * @return true si no hay tesoros almacenados en el mal rollo.
     */
    public boolean isEmpty () {
        return (nHiddenTreasures == 0 && nVisibleTreasures == 0 && specificHiddenTreasures.isEmpty() && specificVisibleTreasures.isEmpty());
    }
    
    /**
     * Devuelve el un numero entero con el nivel del mostruo
     * @return nuemro entero con la cantidad de niveles que se pierde
     */
    public int getLevels () {
        return levels;
    }
    
    /**
     * Devuelve el numero entero con la cantidad de tesoros visibles
     * que perdemos cuando tenemos este mal rollo
     * @return numero entero con la cantidad de tesoros visibles
     */
    public int getNVisibleTreasures () {
        return nVisibleTreasures;
    }
    
    /**
     * Devuelve el numero entero con la cantidad de tesoros ocultos
     * que perdemos cuando tenemos este mal rollo
     * @return numero entero con la cantidad de tesoros ocultos
     */
    public int getNHiddenTreasures () {
        return nHiddenTreasures;
    }
    
    /**
     * Devuelve un array con todos los tesoros ocultos que
     * contiene el mal rollo
     * @return Array con todos los tesoros ocultos
     */
    public ArrayList<TreasureKind> getSpecificHiddenTreasures () {
        return specificHiddenTreasures;    
    }
    
    /**
     * Devuelve un array con todos los tesoros visibles que
     * contiene el mal rollo
     * @return Array con todos los tesoros visibles
     */
    public ArrayList<TreasureKind> getSpecificVisibleTreasures () {
        return specificVisibleTreasures;
    }
    
    /**
     * Quitamos un tesoro visible 
     * @param treasure es el tesoro que queremos quitar
     */
    public void substractVisibleTreasure (Treasure treasure) {
        Napakalaki.getInstance().getDealer().giveTreasureBack(treasure);
    }
    
    /**
     * Quitamos un tesoro oculto 
     * @param treasure es el tesoro que queremos quitar
     */
    public void substractHiddenTreasure (Treasure treasure) {
        Napakalaki.getInstance().getDealer().giveTreasureBack(treasure);
    }
    
    /**
     * Metodo para actualizar los datos del mal rollo 
     * una vez que se ha aplicado las reglas del juego.
     * @param tVisible array con los tesoros visibles
     * @param tHidden array con los tesoros ocultos
     * @return devolvemos el mismo mal rollo pero con
     * los datos actualizados
     */
    public BadConsequence adjustToFitTreasureList (ArrayList<Treasure> tVisible, ArrayList<Treasure> tHidden){
        //Visible
        ArrayList<TreasureKind> aux = new ArrayList();
        for(Treasure t:tVisible){
           for(TreasureKind t2:this.specificVisibleTreasures){
               if(t.getType() == t2){
                   aux.add(t2);
               }
           }
        }
        this.specificVisibleTreasures.clear();
        this.specificVisibleTreasures.addAll(aux);
        
        //Hidden
        aux.clear(); //Limpio aux para reutilizarlo
        for(Treasure t:tHidden){
          for(TreasureKind t2:this.specificHiddenTreasures){
              if(t.getType() == t2){
                   aux.add(t2);
              }
          }
        }
        this.specificHiddenTreasures.clear();
        this.specificHiddenTreasures.addAll(aux);
        return this; //Se retorna el mismo
    }
    
    /**
     * Comprobamos si mi mal rollo tiene muerte y por
     * lo tanto el final de la partida
     * @return verdadero o falso según sea muerte o no.
     */
    public boolean myBadConsequenceIsDeath(){
        return this.text.contains("muerto");
    }
    
    /**
     * Devuelve el parámetro de muerte
     * @return verdadero o falso si esta muerto o no
     */
    public boolean getDeath(){
        return this.death;
    }
    
    /**
     * Devuelve el motivo del mal rollo
     * @return Cadena de caracteres con el nombre del mal rollo
     */
    public String getText(){
        return this.text;
    }
    
    /**
     * Devuelve una cadena de caracteres con todos los valores
     * que contiene el mal rollo, lo imprime por pantalla formateado
     * @return Cadena de caracteres con los valores del mal rollo formateado
     */
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


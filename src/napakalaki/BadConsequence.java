package napakalaki;

import java.util.ArrayList;
import napakalaki.Napakalaki;
import napakalaki.Treasure;
import napakalaki.TreasureKind;

/**
 * Clase de Mal rollo se encarga de incluir las ociones negativas del juego así
 * cuando se pierde un combate.
 *
 * @authors: Carlos de la Torre 75145459C Farouk
 */
public class BadConsequence {

    /**
     * Cantidad máxima de tesoros del mal rollo
     */
    static final int MAXTREASURES = 10;
    /**
     * Texto que indica cual es el mal rollo
     */
    private String text;
    /**
     * Niveles del Mal rollo
     */
    private int levels;
    /**
     * Numero de tesoros visibles que se perderan con este mal rollo
     */
    private int nVisibleTreasures;
    /**
     * Numero de tesoros visibles que se perderan con este mal rollo
     */
    private int nHiddenTreasures;
    /**
     * Si el mal rollo al aplicarlo al jugador hace que este muera
     */
    private boolean death;
    /**
     * Tesoros ocultos que se pierden si se aplica el mal rollo
     */
    private final ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    /**
     * Tesoros visibles que se pierden si se aplica el mal rollo
     */
    private final ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();

    /**
     * Constructor de Clase, inicializa los valores del objeto con parámetros de
     * entrada:
     */
    public BadConsequence() {
        this.text = "";
        this.levels = this.nVisibleTreasures = this.nHiddenTreasures = 0;
        death = false;
    }

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param text numero entero que indica cantidad de tesoros
     * @param levels numero que indica la cantidad de niveles
     * @param nHiddenTreasures numero de tesoros ocultos
     * @param nVisibleTreasures numero de tesoros visibles
     * @param death verdadero o falso si hay muerte
     * @param tVisible array con los tesoros visibles
     * @param tHidden array con los tesoros ocultos
     */
    public BadConsequence(String text, int levels, int nVisibleTreasures, int nHiddenTreasures, boolean death, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden) {
        this.text = text;
        this.levels = levels;
        this.nHiddenTreasures = nHiddenTreasures;
        this.nVisibleTreasures = nVisibleTreasures;
        this.death = death;
        this.specificVisibleTreasures.addAll(tVisible);
        this.specificHiddenTreasures.addAll(tHidden);
    }

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param text numero entero que indica cantidad de tesoros
     * @param levels numero que indica la cantidad de niveles
     * @param nHiddenTreasures numero de tesoros ocultos
     * @param nVisibleTreasures numero de tesoros visibles
     */
    public BadConsequence(String text, int levels, int nVisibleTreasures, int nHiddenTreasures) {
        this.text = text;
        this.levels = levels;
        this.nVisibleTreasures = nVisibleTreasures;
        this.nHiddenTreasures = nHiddenTreasures;
    }

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param text numero entero que indica cantidad de tesoros
     * @param levels numero que indica la cantidad de niveles
     * @param tVisible array con los tesoros visibles
     * @param tHidden array con los tesoros ocultos
     */
    public BadConsequence(String text, int levels, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden) {
        this.text = text;
        this.levels = levels;
        this.specificVisibleTreasures.addAll(tVisible);
        this.specificHiddenTreasures.addAll(tHidden);
    }

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param text numero entero que indica cantidad de tesoros
     * @param death verdadero o falso si hay muerte
     */
    public BadConsequence(String text, boolean death) {
        this.text = text;
        this.death = death;
        this.levels = Player.NIVEL_MAXIMO;
        this.nVisibleTreasures = this.MAXTREASURES;
        this.nHiddenTreasures = this.MAXTREASURES;
    }

    /**
     * Podemos preguntar si el mal rollo esta lleno o esta vacio osea si los
     * tesoros visibles y los ocultos estan vacios.
     *
     * @return true si no hay tesoros almacenados en el mal rollo.
     */
    public boolean isEmpty() {
        return (this.nHiddenTreasures == 0 && this.nVisibleTreasures == 0 && this.specificHiddenTreasures.isEmpty() && this.specificVisibleTreasures.isEmpty());
    }

    /**
     * Devuelve el un numero entero con el nivel del mostruo
     *
     * @return nuemro entero con la cantidad de niveles que se pierde
     */
    public int getLevels() {
        return this.levels;
    }

    /**
     * Devuelve el numero entero con la cantidad de tesoros visibles que
     * perdemos cuando tenemos este mal rollo
     *
     * @return numero entero con la cantidad de tesoros visibles
     */
    public int getNVisibleTreasures() {
        return this.nVisibleTreasures;
    }

    /**
     * Devuelve el numero entero con la cantidad de tesoros ocultos que perdemos
     * cuando tenemos este mal rollo
     *
     * @return numero entero con la cantidad de tesoros ocultos
     */
    public int getNHiddenTreasures() {
        return this.nHiddenTreasures;
    }

    /**
     * Devuelve un array con todos los tesoros ocultos que contiene el mal rollo
     *
     * @return Array con todos los tesoros ocultos
     */
    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return this.specificHiddenTreasures;
    }

    /**
     * Devuelve un array con todos los tesoros visibles que contiene el mal
     * rollo
     *
     * @return Array con todos los tesoros visibles
     */
    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return this.specificVisibleTreasures;
    }

    /**
     * Quitamos un tesoro visible
     *
     * @param treasure es el tesoro que queremos quitar
     */
    public void substractVisibleTreasure(Treasure treasure) {
        this.specificVisibleTreasures.remove(treasure);
        if (this.nVisibleTreasures > 0)
            this.nVisibleTreasures--;
    }

    /**
     * Quitamos un tesoro oculto
     *
     * @param treasure es el tesoro que queremos quitar
     */
    public void substractHiddenTreasure(Treasure treasure) {
        this.specificHiddenTreasures.remove(treasure);
        if (this.nHiddenTreasures > 0)
            this.nHiddenTreasures--;
    }

    /**
     * Metodo para actualizar los datos del mal rollo una vez que se ha aplicado
     * las reglas del juego.
     *
     * @param tVisible array con los tesoros visibles
     * @param tHidden array con los tesoros ocultos
     * @return devolvemos el mismo mal rollo pero con los datos actualizados
     */
    public BadConsequence adjustToFitTreasureList(ArrayList<Treasure> tVisible, ArrayList<Treasure> tHidden) {
        /**
         * @TODO Tienes que hacer que este método compruebe si los datos que
         * vienen en los dos array se pueden quitar todos ellos de los datos que
         * tiene el jugador. Osea que si tenemos una mano, un zapato. Podamos
         * quitarselo al jugador. No podemos modificar el this por que
         * modificamos la carta original del mazo. Aparte de eso tenemos que
         * crear un if para saber si los array vienen vacios pues tenemos que
         * quitar el numero de tesoros que se especifican en las variables
         * nVisibleTreasures y nHiddenTreasures del objeto This
         */
        BadConsequence bs;
        ArrayList<TreasureKind> auxv = new ArrayList();
        ArrayList<TreasureKind> auxh = new ArrayList();
        if (!tVisible.isEmpty() && !tHidden.isEmpty()) {
            //Visible
            for (Treasure tparam : tVisible)
                for (TreasureKind tlocal : this.specificVisibleTreasures)
                    if (tparam.getType() == tlocal)
                        auxv.add(tlocal);
            //Hidden
            for (Treasure t : tHidden)
                for (TreasureKind t2 : this.specificHiddenTreasures)
                    if (t.getType() == t2)
                        auxh.add(t2);
            bs = new BadConsequence(this.text, this.levels, this.nVisibleTreasures, this.nHiddenTreasures, this.death, auxv, auxh);
        } else {
            /**
             * Si el jugador no tiene tesoros en sus arrays no se puede quitar
             * ninguno de los tesoros por lo tanto se devuelve un mal rollo
             * que solo provoca muerte o quita los niveles del mal rollo
             */
            bs = new BadConsequence(this.text, this.levels, 0, 0, this.death, auxv, auxh);
        }

        return bs;
    }

    /**
     * Comprobamos si mi mal rollo tiene muerte y por lo tanto el final de la
     * partida
     *
     * @return verdadero o falso según sea muerte o no.
     */
    public boolean myBadConsequenceIsDeath() {
        return this.text.contains("muerto");
    }

    /**
     * Devuelve el parámetro de muerte
     *
     * @return verdadero o falso si esta muerto o no
     */
    public boolean getDeath() {
        return this.death;
    }

    /**
     * Devuelve el motivo del mal rollo
     *
     * @return Cadena de caracteres con el nombre del mal rollo
     */
    public String getText() {
        return this.text;
    }

    /**
     * Devuelve una cadena de caracteres con todos los valores que contiene el
     * mal rollo, lo imprime por pantalla formateado
     *
     * @return Cadena de caracteres con los valores del mal rollo formateado
     */
    @Override
    public String toString() {
        return "Texto: " + this.text + ", "
                + "\n\tNiveles: " + Integer.toString(this.levels) + ", "
                + "\n\tNumero de Tesoros Visibles: " + Integer.toString(this.nVisibleTreasures) + ", "
                + "\n\tNumero de Tesoros Ocultos: " + Integer.toString(this.nHiddenTreasures) + ", "
                + "\n\tMuerte: " + Boolean.toString(this.death) + ", "
                + "\n\tTesoros Ocultos: " + this.specificHiddenTreasures.toString() + ", "
                + "\n\tTesoros Visibles: " + this.specificVisibleTreasures.toString();
    }
}

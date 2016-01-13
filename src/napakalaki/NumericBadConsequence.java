package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class NumericBadConsequence extends BadConsequence {

    /**
     * Numero de tesoros visibles que se perderan con este mal rollo
     */
    private int nVisibleTreasures;
    /**
     * Numero de tesoros visibles que se perderan con este mal rollo
     */
    private int nHiddenTreasures;

    /**
     * Constructor de Clase, inicializa los valores del objeto con parámetros de
     * entrada:
     */
    public NumericBadConsequence() {
        super();
        this.nVisibleTreasures = this.nHiddenTreasures = 0;
    }

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param text numero entero que indica cantidad de tesoros
     * @param levels numero que indica la cantidad de niveles
     * @param death indica si el mal rollo conlleva muerte.
     * @param nHiddenTreasures numero de tesoros ocultos
     * @param nVisibleTreasures numero de tesoros visibles
     */
    public NumericBadConsequence(String text, int levels, boolean death, int nVisibleTreasures, int nHiddenTreasures) {
        super(text, levels, death);
        this.nVisibleTreasures = nVisibleTreasures;
        this.nHiddenTreasures = nHiddenTreasures;
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
     * Metodo para actualizar los datos del mal rollo una vez que se ha aplicado
     * las reglas del juego.
     *
     * @param tVisible array con los tesoros visibles
     * @param tHidden array con los tesoros ocultos
     * @return devolvemos el mismo mal rollo pero con los datos actualizados
     */
    @Override
    public NumericBadConsequence adjustToFitTreasureList(ArrayList<Treasure> tVisible, ArrayList<Treasure> tHidden) {
        NumericBadConsequence bs;
        int numvisibles, numocultos;
        if (!tVisible.isEmpty() || !tHidden.isEmpty()) {
            if (this.nVisibleTreasures > tVisible.size()) {
                numvisibles = tVisible.size();
            } else {
                numvisibles = this.nVisibleTreasures;
            }
            if (this.nHiddenTreasures > tHidden.size()) {
                numocultos = tHidden.size();
            } else {
                numocultos = this.nHiddenTreasures;
            }
            bs = new NumericBadConsequence(this.getText(), this.getLevels(), this.getDeath(), numvisibles, numocultos);
        } else {
            bs = new NumericBadConsequence(this.getText(), this.getLevels(), this.getDeath(), 0, 0);
        }
        return bs;
    }

    /**
     * Quitamos un tesoro visible
     *
     * @param treasure es el tesoro que queremos quitar
     */
    @Override
    public void substractVisibleTreasure(Treasure treasure) {
        if (this.nVisibleTreasures > 0) {
            this.nVisibleTreasures--;
        }
    }

    /**
     * Quitamos un tesoro oculto
     *
     * @param treasure es el tesoro que queremos quitar
     */
    @Override
    public void substractHiddenTreasure(Treasure treasure) {
        if (this.nHiddenTreasures > 0) {
            this.nHiddenTreasures--;
        }
    }

    /**
     * Podemos preguntar si el mal rollo esta lleno o esta vacio osea si los
     * tesoros visibles y los ocultos estan vacios.
     *
     * @return true si no hay tesoros almacenados en el mal rollo.
     */
    @Override
    public boolean isEmpty() {
        return (this.nHiddenTreasures == 0 && this.nVisibleTreasures == 0);
    }

    /**
     * Devuelve una cadena de caracteres con todos los valores que contiene el
     * mal rollo, lo imprime por pantalla formateado
     *
     * @return Cadena de caracteres con los valores del mal rollo formateado
     */
    @Override
    public String toString() {
        return super.toString()
                + "\n\tNumero de Tesoros Visibles: " + Integer.toString(this.nVisibleTreasures)
                + "\n\tNumero de Tesoros Ocultos: " + Integer.toString(this.nHiddenTreasures);
    }
}

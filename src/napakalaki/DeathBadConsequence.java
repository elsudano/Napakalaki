package napakalaki;

/**
 * @Creado el 07-ene-2016
 * @Autor Carlos de la Torre
 * @Blog https://www.sudano.net
 */
public class DeathBadConsequence extends NumericBadConsequence {

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param text numero entero que indica cantidad de tesoros
     * @param death indica si el mal rollo conlleva muerte.
     */
    public DeathBadConsequence(String text, boolean death) {
        super(text, Player.NIVEL_MAXIMO, death, MAXTREASURES, MAXTREASURES);
    }

    /**
     * Devuelve una cadena de caracteres con todos los valores que contiene el
     * mal rollo, lo imprime por pantalla formateado
     *
     * @return Cadena de caracteres con los valores del mal rollo formateado
     */
    @Override
    public String toString() {
        return super.toString() + "";
    }

}

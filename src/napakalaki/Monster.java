package napakalaki;

/**
 * Clase de Monstruo que contiene todos los datos para poder crear un monstruo
 * del juego
 *
 * @authors: Carlos de la Torre 75145459C Farouk Arroub
 */
public class Monster implements Card {

    /**
     * Variable de tipo String que indica el nombre del Monstruo.
     */
    private String name;
    /**
     * Variable de tipo entero que indica el nivel de combate del Monstruo.
     */
    private int combatLevel;
    /**
     * Variable de tipo Prize que almacena el Precio del Monstruo.
     */
    private Prize prize;
    /**
     * Variable de tipo BadConsequence que almacena el Mal Rollo del Monstruo.
     */
    private BadConsequence badConsequence;
    /**
     * Variable de tipo entero que almacena el nivel que tiene el monstruo cuando se enfrenta a un jugador Sectario.
     */
    private int levelChangeAgainstCultistPlayers = 0;

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param n nombre del monstuo
     * @param l entero nivel de combate
     * @param b mal rollo del monstruo
     * @param p precio del monstruo
     */
    public Monster(String n, int l, BadConsequence b, Prize p) {
        this.name = n;
        this.combatLevel = l;
        this.badConsequence = b;
        this.prize = p;
    }

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param n nombre del monstuo
     * @param l entero nivel de combate
     * @param b mal rollo del monstruo
     * @param p precio del monstruo
     * @param l2 entero que indica el jugador sectario al que cambiamos
     */
    public Monster(String n, int l, BadConsequence b, Prize p, int l2) {
        this.name = n;
        this.combatLevel = l;
        this.badConsequence = b;
        this.prize = p;
        this.levelChangeAgainstCultistPlayers = l2;
    }

    /**
     * Consultor de el nivel de combate del mostruo
     *
     * @return entero que indica el nivel de combate
     */
    public int getCombatLevel() {
        return this.combatLevel;
    }

    /**
     * Consultor del mal rollo del monstruo
     *
     * @return devuelve el objeto mal rollo del monstruo
     */
    public BadConsequence getBadConsequence() {
        return this.badConsequence;
    }

    /**
     * Consultor que devuelve el precio que tiene el monstruo
     *
     * @return objeto price que contiene el monstruo
     */
    public Prize getPrize() {
        return this.prize;
    }

    /**
     * Consultor de la cantidad de niveles que gana el monstruo
     *
     * @return entero con la cantidad de niveles que ganamos
     */
    public int getLevelsGained() {
        return this.prize.getLevels();
    }

    /**
     * Consultor de la cantidad de tesoros que gana el jugador si gana al
     * monstruo
     *
     * @return entero con la cantidad de niveles a ganar
     */
    public int getTreasuresGained() {
        return this.prize.getTreasures();
    }

    /**
     * Consultor para saber si el mal rollo del monstruo realiza una muerte
     * directa del jugador
     *
     * @return verdad o mentira que el monstruo contiene la muerte
     */
    public boolean kills() {
        return this.badConsequence.myBadConsequenceIsDeath();
    }

    /**
     * Consultor de dato basico
     *
     * @return numero entero con el nivel
     */
    @Override
    public int getBasicValue() {
        return this.getCombatLevel();
    }

    /**
     * Muestra el nombre del monstruo
     *
     * @return Cadena con el nombre del monstruo
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Muestra un valor especial
     *
     * @return numero entero con un valor especial
     */
    @Override
    public int getSpecialValue() {
        return getBasicValue() + levelChangeAgainstCultistPlayers;
    }

    /**
     * Devuelve una cadena de caracteres con todos los valores que contiene el
     * monstruo, lo imprime por pantalla formateado
     *
     * @return Cadena de caracteres con los valores del monstruo formateado
     */
    @Override
    public String toString() {
        String formateado = 
        "Nombre del Monstruo: " + this.getName()
        +"\nNivel de Combate: " + this.getCombatLevel()
        +"\nContenido del Precio:\n\t" + this.getPrize().toString()
        +"\nContenido de Mal Rollo:\n\t" + this.getBadConsequence().toString()
        + "\n";
        return formateado;
    }
}
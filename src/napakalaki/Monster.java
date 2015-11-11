
package napakalaki;

/**
 * Clase de Monstruo que contiene todos los datos
 * para poder crear un monstruo del juego
 * @authors:
 * Carlos de la Torre 75145459C
 * Farouk
 */
public class Monster implements Card{
    private String name;
    private int combatLevel;
    private Prize prize;
    private BadConsequence badConsequence;
    private int levelChangeAgainstCultistPlayers=0;
    
    /**
     * Constructor con parámetros, inicializa los valores del objeto
     * con parámetros de entrada:
     * @param name nombre del monstuo
     * @param combatLevel entero nivel de combate
     * @param bc mal rollo del monstruo
     * @param price precio del monstruo
     */
    public Monster (String name, int combatLevel, BadConsequence bc, Prize price) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.badConsequence = bc;
        this.prize = price;
    }
    
    /**
     * Constructor con parámetros, inicializa los valores del objeto
     * con parámetros de entrada:
     * @param name nombre del monstuo
     * @param combatLevel entero nivel de combate
     * @param bc mal rollo del monstruo
     * @param price precio del monstruo
     * @param levelChangeAgainstCultistPlayers entero que indica el jugador sectario al que cambiamos
     */
    public Monster(String name, int combatLevel, BadConsequence bc, Prize price, int levelChangeAgainstCultistPlayers) {
        this.name = name;
        this.combatLevel = combatLevel;
        this.badConsequence = bc;
        this.prize = price;
        this.levelChangeAgainstCultistPlayers = levelChangeAgainstCultistPlayers;
    }
    
    /**
     * Consultor de el nivel de combate del mostruo
     * @return entero que indica el nivel de combate
     */
    public int getCombatLevel(){
        return this.combatLevel;
    }
    
    /**
     * Consultor del mal rollo del monstruo
     * @return devuelve el objeto mal rollo del monstruo
     */
    public BadConsequence getBadConsequence () {
        return this.badConsequence;
    }
    
    /**
     * Consultor que devuelve el precio que tiene el monstruo
     * @return objeto price que contiene el monstruo
     */
    public Prize getPrize() {
        return this.prize;
    }
    
    /**
     * Consultor de la cantidad de niveles que gana el monstruo
     * @return entero con la cantidad de niveles que ganamos
     */
    public int getLevelsGained () {
        return this.prize.getLevels();
    }
    
    /**
     * Consultor de la cantidad de tesoros que gana
     * el jugador si gana al monstruo
     * @return entero con la cantidad de niveles a ganar
     */
    public int getTreasuresGained () {
        return this.prize.getTreasures();
    }
    
    /**
     * Consultor para saber si el mal rollo del monstruo 
     * realiza una muerte directa del jugador
     * @return verdad o mentira que el monstruo contiene la muerte
     */
    public boolean kills(){
        return this.badConsequence.myBadConsequenceIsDeath();
    }
    
    /**
     * Consultor de dato basico
     * @return numero entero con el nivel
     */
    @Override
    public int getBasicValue(){
        return this.getCombatLevel();
    }
    
    /**
     * Muestra el nombre del monstruo
     * @return Cadena con el nombre del monstruo
     */
    @Override
    public String getName(){
        return name;
    }
    
    /**
     * Muestra un valor especial
     * @return numero entero con un valor especial
     */
    @Override
    public int getSpecialValue(){
        return getBasicValue() + levelChangeAgainstCultistPlayers;
    }
    
    /**
     * Devuelve una cadena de caracteres con todos los valores
     * que contiene el monstruo, lo imprime por pantalla formateado
     * @return Cadena de caracteres con los valores del monstruo formateado
     */
    @Override
    public String toString(){
        return "Nombre: "+this.name+", "+ 
               "Nivel de Combate: "+Integer.toString(this.combatLevel)+", "+
               "Buen Rollo: "+"{"+this.prize.toString()+"}"+", "+
               "Mal Rollo: "+"{"+this.badConsequence.toString()+"}";
    }
}


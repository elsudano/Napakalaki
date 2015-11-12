package napakalaki;

import gui.NapakalakiView;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase principal del juego, contiene las instancias principales para el
 * desarrollo de una partida.
 *
 * @authors: Carlos de la Torre 75145459C Farouk
 */
public class Napakalaki {

    /**
     * Instance es la unica instancia del juego que hay en toda la aplicacion de
     * esta manera no se pueden repetir parametros, esto sirve para las clases
     * singleton.
     */
    private static final Napakalaki INSTANCE = new Napakalaki();
    /**
     * Lista de jugadores que hay en el Juego.
     */
    private ArrayList<Player> players;
    /**
     * El jugador que actualmente tiene el turno.
     */
    private Player currentPlayer;
    /**
     * El monstruo que actualmente está siendo usado.
     */
    private Monster currentMonster;
    /**
     * Este es la instancia que controla el repartidor de cartas.
     */
    private CardDealer dealer;
    /**
     * Esta es la instancia que controla el dado.
     */
    private Dice dice;

    /**
     * Constructor de Clase, inicializa los valores del objeto.
     */
    private Napakalaki() {
        this.players = new ArrayList();
        this.currentMonster = null;
        this.dealer = CardDealer.getInstance();
        this.currentPlayer = new Player("nulo");
    }

    /**
     * Consultor que se encarga de devolver el repartidor de cartas.
     *
     * @return objeto carddealer para gestión de cartas
     */
    public CardDealer getDealer() {
        return this.dealer;
    }

    /**
     * Consultor que se encarga de devolver el dado
     *
     * @return objeto dado para realizar tiradas de dado
     */
    public Dice getDice() {
        return this.dice;
    }

    /**
     * Metodo auxiliar que sirve para inicializar los jugadores basicamente
     * añade los nombres a un array
     *
     * @param names array de string con los nombres de los jugadores
     */
    private void initPlayers(ArrayList<String> names) {
        this.players = new ArrayList(names.size());
        for (String s : names) {
            Player aux = new Player(s);
            players.add(aux);
        }

    }

    /**
     * Metodo auxiliar que devuelve el siguiente jugador que le toca participar
     * en el juego
     *
     * @return objeto tipo jugador que es el siguiente que le toca interactuar
     */
    private Player nextPlayer() {
        int aux = 0;
        aux = players.indexOf(currentPlayer);
        aux++;
        if (aux == players.size()) {
            aux = 0;
        }

        return players.get(aux);
    }

    /**
     * Metodo auxiliar que sirve para decir si un jugador tiene el turno de
     * tirada de dado permitida
     *
     * @return verdadero o falso según le toque o no al jugador tirar el dado
     */
    private boolean nextTurnIsAllowed() {
        return currentPlayer.validState();
        //return true;
    }

    /**
     * Metodo auxiliar que sirve para añadir los enemigos al juego
     */
    private void setEnemies() {
        //@@TODO;
    }

    /**
     * Con esto pedimos una instancia unica del juego para poder interactuar con
     * la parte de la interfaz gráfica.
     *
     * @return objeto de tipo Napakalaki principal del juego
     */
    public static Napakalaki getInstance() {
        return INSTANCE;
    }

    /**
     * Metodo que se encarga de realizar los combates entre juegadores y
     * monstruos
     *
     * @return devuelve un objeto de tipo CombarResult con el resultado del
     * combate
     */
    public CombatResult developCombat() {
        CombatResult cr = this.currentPlayer.combat(currentMonster);
        this.dealer.giveMonsterBack(currentMonster);
//        if(cr.equals(CombatResult.LOSEANDCONVERT)){
//            Cultist c = dealer.nextCultists();
//            CultistPlayer cp = new CultistPlayer(this.currentPlayer,c);
//            for(Player p:players){
//                if(p.equals(currentPlayer)){
//                    p = cp;
//                }
//            }
//            this.currentPlayer = cp;
//        }
        return cr;
    }

    /**
     * Metodo publico que sirve para descartar todo los tesoros visibles que se
     * pasan por parametros en el array
     *
     * @param treasures array de tipo tesoro que serán los que se descartan
     */
    public void discardVisibleTreasures(ArrayList<Treasure> treasures) {
        for (Treasure t : treasures) {
            this.currentPlayer.discardVisibleTreasure(t);
            this.dealer.giveTreasureBack(t);
        }
    }

    /**
     * Metodo publico que sirve para descartar todo los tesoros ocultos que se
     * pasan por parametros en el array
     *
     * @param treasures array de tipo tesoro que serán los que se descartan
     */
    public void discardHiddenTreasures(ArrayList<Treasure> treasures) {
        for (Treasure t : treasures) {
            this.currentPlayer.discardHiddenTreasure(t);
            this.dealer.giveTreasureBack(t);
        }
    }

    /**
     * Metodo que se encarga de cambiar los tesoros de la lista de los tesoros
     * ocultos a tesoros visibles
     *
     * @param treasures lista de tesoros validos que se puedan poner como
     * visibles
     */
    public void makeTreasuresVisible(ArrayList<Treasure> treasures) {
        for (Treasure t : treasures) {
            this.currentPlayer.makeTreasuresVisible(t);
        }
    }

    /**
     * Metodo que se encarga de inicializar todas las variables necesarias para
     * el juego
     *
     * @param names array con los nombres de los jugadores
     */
    public void initGame(ArrayList<String> names) {
        this.initPlayers(names);
        this.dealer.initCards();
        this.nextTurn();
    }

    /**
     * Consultor del jugador que esta actualmente en uso
     *
     * @return objeto tipo player con el que esta actualmente en uso.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Consultor del monstruo que esta actualmente en uso
     *
     * @return objeto tipo monstruo con el que esta actualmente en uso.
     */
    public Monster getCurrentMonster() {
        return currentMonster;
    }

    /**
     * Consultor del tipo de circunstancia que se lleva a cabo al pasar al
     * siguiente turno
     *
     * @return objeto tipo Circunstancia que indica si se pasa, o se sigue
     * jugando
     */
    public Circunstancia nextTurn() {
        Circunstancia respuesta = Circunstancia.NADA;
        boolean stateOK = this.nextTurnIsAllowed();
        if (stateOK) {
            this.currentMonster = dealer.nextMonster();
            this.currentPlayer = this.nextPlayer();
            boolean dead = this.currentPlayer.isDead();
            if (dead) {
                this.currentPlayer.initTreasures();
            }
        }

        if (this.currentPlayer.isCultistPlayer()) {
            respuesta = Circunstancia.PASA;
        } else if (this.getDice().getInstance().nextNumber() == 1) {
            respuesta = Circunstancia.PIERDE_TURNO;
        } else if (!stateOK) {
            respuesta = Circunstancia.NOPASA;
        }
        return respuesta;
    }

    /**
     * Consultor para saber si el juego ha llegado a su final
     *
     * @param result será el resultado de un combate para poder compararlo.
     * @return devuelve verdadero si el juego ha llegado a su fin falso en su
     * caso contrario.
     */
    public boolean endOfGame(CombatResult result) {
        return result.equals(CombatResult.WINGAME);

    }

    public ArrayList<Player> getNames() {
        return this.players;
    }

//    private NapakalakiView vista;
//    
//    public NapakalakiView getVista(){
//        return this.vista;
//    }
//    
//    public void setVista(NapakalakiView vista){
//        this.vista = vista;
//    }
    public boolean buyLevels(ArrayList<Treasure> visible, ArrayList<Treasure> hidden) {
        boolean canI = this.currentPlayer.buyLevels(visible, hidden);
        return canI;
    }
}

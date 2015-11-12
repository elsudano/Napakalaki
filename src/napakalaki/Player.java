package napakalaki;

import java.util.ArrayList;

/**
 * Clase de Jugador que controla todo lo relacionado con el tesoros que posee,
 * tanto ocultos como visibles nombre, o si ha terminado la partida para el.
 *
 * @authors: Carlos de la Torre 75145459C Farouk Arroub
 */
public class Player {

    /**
     * Indica si el jugador esta Muerto
     */
    private boolean dead;
    /**
     * Este es el nombre del Jugador
     */
    private String name;
    /**
     * Nivel de combate del Jugador
     */
    private int level;
    /**
     * Tesoros ocultos que tiene el Jugador
     */
    private ArrayList<Treasure> hiddenTreasures;
    /**
     * Tesoros visibles que tiene el Jugador
     */
    private ArrayList<Treasure> visibleTreasures;
    /**
     * Mal rollo mendiente de cumplir por el jugador
     */
    private BadConsequence pendingBadStuff;
    /**
     * Constante que indica el nivel minimo de un Jugador
     */
    private static final int NIVEL_MINIMO = 1;
    /**
     * Constante que indica el nivel máximo de un Jugador
     */
    private static final int NIVEL_MAXIMO = 10;
    /**
     * Constante que indica el numero máximo de tesoros ocultos de un Jugador
     */
    private static final int TESOROS_OCULTOS_MAXIMO = 4;
    /**
     * Indica si el Jugador es un Sectario
     */
    private boolean isCultistPlayer;

    /**
     * Constructor de Clase, inicializa los valores del objeto con parámetros de
     * entrada:
     *
     * @param name este es el nombre del jugador
     */
    public Player(String name) {
        this.name = name;
        this.dead = true;
        this.hiddenTreasures = new ArrayList();
        this.visibleTreasures = new ArrayList();
        this.pendingBadStuff = new BadConsequence();
        this.level = 1;
        this.isCultistPlayer = false;
    }

    /**
     * Metodo auxiliar que pone el parametro de muerte a falso para que el
     * jugador vuelva a la vida.
     */
    private void bringToLife() {
        this.dead = false;
    }

    /**
     * Consultor que indica cual es el nivel de combate de un jugador, este
     * metodo se suele utilizar mucho para compararlo con el nivel de combate de
     * un monstruo, para llevar a cabo los enfrentamientos.
     *
     * @return devuelve un entero con el nivel de combate del Jugador
     */
    public int getCombatLevel() {
        int cont = 0;
        boolean collar = false;
        for (Treasure t : visibleTreasures) {
            if (t.getType().equals(TreasureKind.NECKLACE)) {
                collar = true;
            }
        }
        if (collar) {
            for (Treasure t1 : visibleTreasures) {
                cont += t1.getSpecialValue();
            }
        } else {
            for (Treasure t2 : visibleTreasures) {
                cont += t2.getBasicValue();
            }
        }
        cont += this.level;
        return cont;
    }

    /**
     * Metodo Auxiliar que añade una cantidad de nivel al usuario segun el
     * parámetro de entrada
     *
     * @param i cantidad que se quiere añadir al nivel de combate del jugador
     */
    private void incrementLevels(int i) {
        this.level += i;
    }

    /**
     * Metodo Auxiliar que quita una cantidad de nivel al usuario segun el
     * parámetro de entrada
     *
     * @param i cantidad que se quiere quitar al nivel de combate del jugador
     */
    private void decrementLevels(int i) {
        this.level -= i;
        if (this.level <= 0) {
            this.level = 1;
        }
    }

    private void setPendingBadStuff(BadConsequence b) {
        this.pendingBadStuff = b;

    }

    private void dieIfNoTreasures() {
        if (this.hiddenTreasures.isEmpty() && this.visibleTreasures.isEmpty()) {
            this.dead = true;
        }
    }

    public void dicardNecklaceIfVisible() {
        for (Treasure t : visibleTreasures) {
            if (t.getType().equals(TreasureKind.NECKLACE)) {
                Napakalaki.getInstance().getDealer().giveTreasureBack(t);
            }
        }

    }

    /**
     * Método que asigna que Nivel queremos ponerle al Jugador.
     *
     * @param n numero de nivel que vamos a asignar al jugador
     */
    public void setLevel(int n) {
        this.level = n;
    }

    public void die() {
        this.setLevel(1);

        for (Treasure treasure : visibleTreasures) {
            Napakalaki.getInstance().getDealer().giveTreasureBack(treasure);
        }
        visibleTreasures.clear();
        for (Treasure treasure : hiddenTreasures) {
            Napakalaki.getInstance().getDealer().giveTreasureBack(treasure);
        }
        hiddenTreasures.clear();
        this.dieIfNoTreasures();
    }

    private int computeGoldCoinsValue(ArrayList<Treasure> treasures) {
        int cont = 0;
        for (Treasure t : treasures) {
            cont += t.getGoldCoins();
        }
        // es posible comprar un nivel con 1.000 piezas de oro.
        cont /= 1000; // Redondeado hacia abajo 8.9 = 8
        return cont;
    }

    private boolean canIBuyLevels(int I) {
        return ((this.level + I) < 10);
    }

    public void applyPrize(Monster currentMonster) {
        int nlevels = currentMonster.getLevelsGained();
        this.incrementLevels(nlevels);
        int nTreasures = currentMonster.getTreasuresGained();
        if (nTreasures > 0) {

            for (int i = 0; i < nTreasures; i++) {
                Treasure treasure = Napakalaki.getInstance().getDealer().nextTreasure();
                hiddenTreasures.add(treasure);
            }
        }

    }

    public void applyBadStuff(BadConsequence bad) {
        int nlevels = bad.getLevels();
        this.decrementLevels(nlevels);
        pendingBadStuff = bad.adjustToFitTreasureList(visibleTreasures, hiddenTreasures);
        this.setPendingBadStuff(pendingBadStuff);
    }

    private boolean canMakeTreasureVisible(Treasure t) {
        boolean manodoble = false, puede = false;
        for (Treasure trea : visibleTreasures) {
            if (trea.getType().equals(TreasureKind.BOTHHANDS)) {
                manodoble = true;
            }
        }

        if (manodoble) {
            if (this.visibleTreasures.size() < 4) {
                puede = true;
            }
        } else if (this.visibleTreasures.size() < 5) {
            puede = true;
        }
        for (Treasure trea : visibleTreasures) {
            if (trea.getType().equals(t.getType())) {
                puede = false;
            }
        }
        return puede;
    }

    private int howManyVisibleTreasures(TreasureKind tKind) {
        int cont = 0;
        for (int i = 0; i < visibleTreasures.size(); i++) {
            if (visibleTreasures.get(i).getType().equals(tKind)) {
                cont++;
            }
        }
        return cont;
    }

    public boolean isDead() {
        return dead;
    }

    public ArrayList<Treasure> getHiddenTreasures() {
        return this.hiddenTreasures;
    }

    public ArrayList<Treasure> getVisibleTreasures() {
        return this.visibleTreasures;
    }

    public CombatResult combat(Monster m) {
        CombatResult cr;
        Dice dado = Dice.getInstance();
        int myLevel = this.getLevels();
        int monsterLevel = this.getOponentLevel(m);
        if (myLevel > monsterLevel) {
            this.applyPrize(m);
            if (this.getLevels() >= 10) {
                cr = CombatResult.WINGAME;
            } else {
                cr = CombatResult.WIN;
            }
        } else {

            int escape = dado.nextNumber();
            if (escape < 5) {
                boolean amIDead = m.kills();
                if (amIDead) {
                    this.die();
                    cr = CombatResult.LOSEANDDIE;
                } else {
                    BadConsequence bad = m.getBadConsequence();
                    cr = CombatResult.LOSE;
                    this.applyBadStuff(bad);
                }
            } else {
                cr = CombatResult.LOSEANDESCAPE;
            }
        }
        if (!this.isDead()) {
            if (this.shouldConvert()) {
                cr = CombatResult.LOSEANDCONVERT;
            }
        }
        this.dicardNecklaceIfVisible();
        return cr;
    }

    public void makeTreasuresVisible(Treasure t) {
        boolean canI = this.canMakeTreasureVisible(t);
        if (canI) {
            this.visibleTreasures.add(t);
            this.hiddenTreasures.remove(t);
        }
    }

    public void discardVisibleTreasure(Treasure t) {
        this.visibleTreasures.remove(t);
        if ((pendingBadStuff != null) && (!pendingBadStuff.isEmpty())) {
            this.pendingBadStuff.substractVisibleTreasure(t);
        }
        this.dieIfNoTreasures();
    }

    public void discardHiddenTreasure(Treasure t) {
        this.hiddenTreasures.remove(t);
        if ((pendingBadStuff != null) && (!pendingBadStuff.isEmpty())) {
            this.pendingBadStuff.substractHiddenTreasure(t);
        }
        this.dieIfNoTreasures();
    }

    public boolean buyLevels(ArrayList<Treasure> visible, ArrayList<Treasure> hidden) {
        int levelsMayBought = this.computeGoldCoinsValue(visible);
        levelsMayBought += this.computeGoldCoinsValue(hidden);
        boolean canI = this.canIBuyLevels(levelsMayBought);
        if (canI) {
            this.incrementLevels(levelsMayBought);
        }
        visibleTreasures.removeAll(visible);
        hiddenTreasures.removeAll(hidden);

        for (Treasure t : visible) {
            Napakalaki.getInstance().getDealer().giveTreasureBack(t);
        }
        for (Treasure t : hidden) {
            Napakalaki.getInstance().getDealer().giveTreasureBack(t);
        }
        return canI;
    }

    public boolean validState() {
        return ((pendingBadStuff.isEmpty()) && (hiddenTreasures.size() <= 4));
    }

    public void initTreasures() {
        this.bringToLife();
        Treasure treasure = Napakalaki.getInstance().getDealer().nextTreasure();
        this.hiddenTreasures.add(treasure);
        int number = Dice.getInstance().nextNumber();
        if (number > 1) {
            treasure = Napakalaki.getInstance().getDealer().nextTreasure();
            hiddenTreasures.add(treasure);
        }
        if (number == 6) {
            treasure = Napakalaki.getInstance().getDealer().nextTreasure();
            hiddenTreasures.add(treasure);
        }

    }

    public boolean hasVisibleTreasures() {
        return (this.visibleTreasures.size() > 0);

    }

    public int getLevels() {
        return this.level;
    }

    public String getName() {
        return this.name;
    }

    public Player(Player p) {
        this.dead = p.dead;
        this.hiddenTreasures = p.hiddenTreasures;
        this.level = p.level;
        this.name = p.name;
        this.pendingBadStuff = p.pendingBadStuff;
        this.visibleTreasures = p.visibleTreasures;
    }

    public int getOponentLevel(Monster m) {
        return m.getBasicValue();
    }

    public boolean shouldConvert() {
        Dice dado = Dice.getInstance();
        return (dado.nextNumber() == 6);
    }

    public BadConsequence getPendingBadStuff() {
        return this.pendingBadStuff;
    }

    public boolean isCultistPlayer() {
        return isCultistPlayer;
    }
}

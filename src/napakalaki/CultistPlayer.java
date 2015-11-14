package napakalaki;

import java.util.ArrayList;

/**
 * Clase de Jugador que controla todo lo relacionado con el tesoros que posee,
 * tanto ocultos como visibles nombre, o si ha terminado la partida para el.
 *
 * @authors: Carlos de la Torre 75145459C Farouk Arroub
 */
public class CultistPlayer extends Player {

    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;
    private boolean isCultistPlayer=false;
    
    public CultistPlayer(Player player, Cultist card) {
        super(player);
        myCultistCard = new Cultist(card.getName(), card.getBasicValue());
        incrementCultists();
        this.isCultistPlayer = true;
    }
    private void incrementCultists(){
        totalCultistPlayers++;
    }
    

    public int getCombatLevel() {
        return super.getCombatLevel() + myCultistCard.getSpecialValue();
    }
    
    @Override
    public boolean shouldConvert() {
        return false;
    }
   
    @Override
    public int getOponentLevel(Monster m) {
        return m.getSpecialValue();
    }

    public int computeGoldCoinsValue(ArrayList<Treasure> treasures) {
         int goldCoins = 0;
                
        for(Treasure t: treasures)
            goldCoins += 2*t.getGoldCoins();
                           
        return goldCoins / 1000;
    }
    
    public static int getTotalCultistPlayers() {
        return totalCultistPlayers;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tJugador sectario: "
                + "\n\t\tBonus basico: " + myCultistCard.getBasicValue()
                + " | Bonus especial: " + myCultistCard.getSpecialValue();
    }
    public Cultist getMyCultistCard(){
        return this.myCultistCard;
    }
    
    public boolean isCultistPlayer() {
        return isCultistPlayer;
    }
}

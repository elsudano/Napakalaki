//  PDOO GRUPO A
//  JESUS ANGEL GONZALEZ NOVEZ   76440070F
//  CARLOS DE LA TORRE			 75145459C
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author Jesús Ángel González Novez
 */
public class CultistPlayer extends Player {

    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;
    // EXAMEN
    private boolean isCultistPlayer=false;
    // FIN EXAMEN
    
    public CultistPlayer(Player player, Cultist card) {
        super(player);
        myCultistCard = new Cultist(card.getName(), card.getBasicValue());
        incrementCultists();
        // EXAMEN
        this.isCultistPlayer = true;
        // FIN EXAMEN
    }
    private void incrementCultists(){
        totalCultistPlayers++;
    }
    
    @Override
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
    
    // EXAMEN
    public boolean isCultistPlayer() {
        return isCultistPlayer;
    }
    // FIN EXAMEN
}

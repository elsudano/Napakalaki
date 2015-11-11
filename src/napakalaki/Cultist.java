//  PDOO GRUPO A
//  JESUS ANGEL GONZALEZ NOVEZ   76440070F
//  CARLOS DE LA TORRE			 75145459C
package napakalaki;

/**
 *
 * @author Jesús Ángel González Novez
 */
public class Cultist implements Card {
    private String name;
    private int gainedLevels;
    public Cultist(String name, int gainedLevels){
        this.name = name;
        this.gainedLevels = gainedLevels;
    }
     @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBasicValue() {
        return gainedLevels;
    }

    @Override
    public int getSpecialValue() {
        return getBasicValue()*CultistPlayer.getTotalCultistPlayers();
    }
}

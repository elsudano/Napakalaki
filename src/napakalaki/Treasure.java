//  PDOO GRUPO A
//  JESUS ANGEL GONZALEZ NOVEZ   76440070F
//  CARLOS DE LA TORRE			 75145459C
package napakalaki;
public class Treasure implements Card{
    private String name;
    private int goldCoins;
    private int minBonus;
    private int maxBonus;
    private TreasureKind type;
    public Treasure ( String n,int g, int min, int max,  TreasureKind t) {
        this.name = n;
        this.goldCoins = g;
        this.minBonus = min;
        this.maxBonus = max;
        this.type = t;
    }
    public int getGoldCoins () {
        return this.goldCoins;
    }
 
    public TreasureKind getType () {
        return this.type;
    }
    public int getMinBonus(){
        return this.minBonus;
    }
    public int getMaxBonus(){
        return this.maxBonus;
    }
    @Override
    public String getName () {
        return this.name;
    }
    @Override
    public int getBasicValue(){
        return this.getMinBonus();
    }
    @Override 
    public int getSpecialValue(){
        return this.getMaxBonus();
    }
    @Override
    public String toString() {
        return this.name + " (" + this.type.toString() + ") " + "\nBonus mínimo = " + this.minBonus + "\nBonus máximo = " + this.maxBonus + "\nPiezas de Oro = " + this.goldCoins;
    }
}


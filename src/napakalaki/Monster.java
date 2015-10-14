package napakalaki;



/**
 *
 * @author 
 */
public class Monster  {

    
    private String name;
    private int combatLevel;
    private Prize prize;
    private BadConsequence badConsequence;
    
    public Monster(String name, int level, BadConsequence bc, Prize price){
        this.name = name;
        this.combatLevel = level;
        this.badConsequence = bc;
        this.prize = price;
       
    }
    
    public String getName() {
        return this.name;
    }

    public int getCombatLevel() {
        return this.combatLevel;
    }
    
    public BadConsequence getBadConsequence() {
        return this.badConsequence;
    }
    
    public Prize getPrice(){
        return this.prize;
    }
    
    public int getLevelsGained(){
        return this.prize.getLevel();
    }
    
    public int getTreasuresGained(){
        return this.prize.getTreasures();
    }
    
    
    @Override
    public String toString(){
        return "Nombre: "+this.name+", "+ 
               "Nivel de Combate: "+Integer.toString(this.combatLevel)+", "+
               "Buen Rollo: "+"{"+this.prize.toString()+"}"+", "+
               "Mal Rollo: "+"{"+this.badConsequence.toString()+"}";
    }
    
}
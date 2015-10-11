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


          
    public Monster(String n, int l, BadConsequence b,Prize p){
        name = n;
        combatLevel = l;
        prize = p;
        badConsequence=b;
       
    }
    
    public String getName() {
        return name;
    }

    public int getCombatLevel() {
        return combatLevel;
    }
    
    public BadConsequence getBadConsequence() {
        return badConsequence;
    }
    
    public Prize getPrice(){
        return prize;
    }
    
    public int getLevelsGained(){
        return prize.getLevel();
    }
    
    public int getTreasuresGained(){
        return prize.getTreasures();
    }
    
    
    @Override
    public String toString(){
        return "Nombre: "+name+", "+ 
               "Nivel de Combate: "+Integer.toString(combatLevel)+", "+
               "Buen Rollo: "+"{"+prize.toString()+"}"+", "+
               "Mal Rollo: "+"{"+badConsequence.toString()+"}";
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package napakalaki;

/**
 *
 * @author 
 */
public class Prize {

    
    private int treasures;
    private int level;
    
    
    
    Prize(int treasures,int level){
        this.level=level;
        this.treasures=treasures;
    }
    
    public int getTreasures() {
        return treasures;
    }

    public int getLevel() {
        return level;
    }
    
    @Override
    public String toString(){
        return "Tesoros= "+Integer.toString(treasures)+", "+
               "Niveles= "+Integer.toString(level);
    }
    
}

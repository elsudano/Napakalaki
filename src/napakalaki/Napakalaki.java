/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;
import java.util.ArrayList;
/**
 *
 * @author pc
 */
public class Napakalaki {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<TreasureKind> tvp = new ArrayList();
        ArrayList<TreasureKind> top = new ArrayList();
        tvp.add(TreasureKind.armor);
        top.add(TreasureKind.armor);
        BadConsequence bc = new BadConsequence("Pierdes tu armadura visible y otra oculta", 0, tvp, top);
        Prize prize = new Prize(2,1);
        Monster monster = new Monster("3 Byakhees de bonanza", 8, bc, prize);
        System.out.println(monster.toString());
        
    }
}


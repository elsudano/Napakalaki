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
public enum TreasureKind {
    armor,oneHand,bothHand,helmet,shoe,necklace;
    
    @Override
    public String toString() {

        switch (this) {

            case armor:
                return "Armadura";

            case oneHand:
                return "1 Mano";

            case bothHand:
                return "2 Manos";

            case helmet:
                return "Casco";

            case shoe:
                return "Calzado";

            case necklace:
                return "Collar";

            default:
                return "Error";
        }
    }
    
 } 


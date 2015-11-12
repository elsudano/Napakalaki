//  PDOO GRUPO A
//  JESUS ANGEL GONZALEZ NOVEZ   76440070F
//  CARLOS DE LA TORRE			 75145459C
package napakalaki;

import gui.*;
import java.util.ArrayList;

/**
 *
 * @author Jesús Ángel González Novez
 */
public class PruebaNapakalaki {
    private static final Napakalaki mNapakalaki = Napakalaki.getInstance();
    private static final NapakalakiView mNapakalakiView = new NapakalakiView();
    
    public static void main(String args[]) {
        Dice.createInstance(mNapakalakiView);
        mNapakalakiView.setModel(mNapakalaki);
        PlayersNamesCapture namesCaptures = new PlayersNamesCapture(mNapakalakiView,true);
        ArrayList<String> names = namesCaptures.getNames();
        mNapakalaki.initGame(names);
        mNapakalakiView.Show(names);
    }
}

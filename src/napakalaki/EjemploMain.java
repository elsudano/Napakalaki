package napakalaki;

import Test.GameTester;
import gui.NapakalakiView;

public class EjemploMain {

    public static void main(String[] args) {
        Napakalaki game = Napakalaki.getInstance();
        NapakalakiView vista = new NapakalakiView();
        Dice.createInstance(vista);
        GameTester test = GameTester.getInstance();
        
        // Poner el numero de jugadores con el que se quiera probar
        test.play(game, vista, 2);
        game = null;
        vista = null;
    }
}

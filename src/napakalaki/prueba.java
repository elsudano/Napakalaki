package napakalaki;

import gui.NapakalakiView;
import java.util.ArrayList;

/**
 *
 * @author Carlos de la Torre
 */
public class prueba {

    public static void main(String[] args) {
        NapakalakiView vista = new NapakalakiView();
        Napakalaki controlador = Napakalaki.getInstance();
        controlador.setVista(vista);

        ArrayList<String> jugadores = new ArrayList<>();
        ArrayList<Treasure> tesoros;
        CombatResult resultado = CombatResult.LOSE;
        
        jugadores.add("Carlos");
        jugadores.add("Sara");
        controlador.initGame(jugadores);
        while (resultado != CombatResult.WIN) {
            System.out.println(controlador.getCurrentMonster());
            System.out.println(controlador.getCurrentPlayer());
            tesoros = new ArrayList<>(controlador.getCurrentPlayer().getHiddenTreasures());
            controlador.discardHiddenTreasures(tesoros);
            resultado = controlador.developCombat();
            System.out.println(resultado);
            controlador.nextTurn();
        }
    }
}

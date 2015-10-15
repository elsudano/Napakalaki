package napakalaki;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Carlos de la Torre
 */
public class Herramientas {
    // Esta variable no cambia en ningun momento y se puede llamar desde
    // cualquier sitio con el metodo getHerramientas()
    private static final Herramientas misHerramientas = new Herramientas();
    // Definición de la variable in que nos va a permitir leer String desde teclado.
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	 
    /**
    * @return una instancia unica de las Herramientas 
    */
    public static Herramientas getInstance() {
        return misHerramientas;
    }
}

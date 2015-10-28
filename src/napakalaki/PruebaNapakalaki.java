/**
 * Clase Principal del Proyecto que sería como si fuera la clase controlador del
 * proyecto seria la que soporta todas las llamadas que se realizan por parte
 * del usuario y la que realiza las llamadas hacia las otras clase para que se
 * ejecute el programa.
 */
package napakalaki;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Carlos de la Torre
 *
 */
public class PruebaNapakalaki {

    // Definición de la variable in que nos va a permitir leer String desde teclado.
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    /**
     * cogemos la instancia para importar la base de datos
    * */
    private static final importarTXT myBD = importarTXT.getInstancia();
    /**
     * el resto de variables
    * */
    private static final ArrayList<Monster> BDMonstruos = myBD.getMonsterBD();
    private static Monster monstruo = null;
    private static BadConsequence MalRolloMonstruo;
    private static Prize precioMonstruo;

    /**
     * @param args
     */
    public static void main(String[] args) {
        int opcion = 0; //variables que nos controlan las opciónes de menú.
        String eleccion;

        do { //do-while que controlara todo el menú
            try {// tratamiento de las excepciones
                //mostrar menú(usando System.out.println(...)) con las siguientes opciones
                System.out.println(
                        "1. Crear Objeto 'Prize',asignar valores, mostrar valores\n"
                        + "2. Crear el Objeto Mal Rollo y llenar sus datos\n"
                        + "3. Crear el Objeto Monstruo y llenar sus datos\n"
                        + "4. Mostrar Monstruos pero...\n"
                        + "5. Mostrar todos los Monstruos"
                        + "\n"
                        + "0. Terminar Aplicación");
                eleccion = in.readLine();
                if (eleccion.equals("")) {
                    throw new Exception("Introduzca un numero del 1 al 5 para continuar");
                }
                opcion = Integer.parseInt(eleccion); // lectura de un int.
                if (opcion < 0 || opcion > 5) {
                    throw new Exception("La opción elegida no es valida");
                }
                // Montar un switch con todas las opciones de menú.
                switch (opcion) {
                    case 1:
                        System.out.println("Creando objeto 'Prize'\n");
                        System.out.println("Introduzca el numero de Tesoros\n"
                                + "pulse enter y luego el numero para el\n"
                                + "nivel pulse enter para terminar\n");
                        precioMonstruo = new Prize(Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()));
                        System.out.println("Objeto Creado\n");
                        System.out.println("Valor de los tesoros del objeto creado: " + precioMonstruo.getTreasures() + "\n");
                        System.out.println("Valor del nivel del objeto creado: " + precioMonstruo.getLevel() + "\n");
                        System.out.println("probando metodo toString:\n" + precioMonstruo.toString());
                        break;
                    case 2:
                        System.out.println("Creando objeto 'MalRollo'\n");
                        System.out.println("Introduzca el concepto del Mal Rollo\n"
                                + "pulse enter y luego el numero para los niveles\n"
                                + "pulse enter y luego el numero para los Tesoros Visibles\n"
                                + "pulse enter y luego el numero para los Tesoros Ocultos\n"
                                + "pulse enter para terminar\n");
                        MalRolloMonstruo = new BadConsequence(in.readLine(), Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()));
                        System.out.println("Objeto 1 Creado\n");
                        System.out.println("Valor del Mal Rollo del objeto creado: " + MalRolloMonstruo.getText() + "\n");
                        System.out.println("Valor de los niveles del objeto creado: " + MalRolloMonstruo.getLevels() + "\n");
                        System.out.println("probando metodo toString:\n" + MalRolloMonstruo.toString());
                        break;
                    case 3:
                        System.out.println("Creando objeto 'Monstruo'\n");
                        System.out.println("Introduzca el numero de Tesoros\n"
                                + "pulse enter y luego el numero para el nivel\n"
                                + "pulse enter y introduzca el concepto del Mal Rollo\n"
                                + "pulse enter y luego el numero para los niveles\n"
                                + "pulse enter y luego el numero para los Tesoros Visibles\n"
                                + "pulse enter y luego el numero para los Tesoros Ocultos\n"
                                + "pulse enter y el nombre del monstruo\n"
                                + "pulse enter y y por ultimo el valor del nivel del monstruo\n"
                                + "pulse enter para terminar\n");
                        precioMonstruo = new Prize(Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()));
                        MalRolloMonstruo = new BadConsequence(in.readLine(), Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()));
                        monstruo = new Monster(in.readLine(), Integer.parseInt(in.readLine()), MalRolloMonstruo, precioMonstruo);
                        BDMonstruos.add(monstruo);
                        System.out.println("Objeto monstruo Creado\n");
                        System.out.println("Valor del nivel de conbate del mostruo creado: " + monstruo.getCombatLevel() + "\n");
                        System.out.println("Valor del nombre del monstruo creado: " + monstruo.getName() + "\n");
                        System.out.println("probando metodo toString:\n" + monstruo.toString());
                        break;
                    case 4:
                        String eleccion2; int contador = 0;
                        try {
                            System.out.println("1. Solamente los que tengan un nivel de Combate mayor que 10\n"
                                    + "2. Que en el Mal Rollo se pierdan niveles\n"
                                    + "3. Que en el Buen Rollo se ganen mas de 1 nivel\n"
                                    + "4. Que en el Mal Rollo pierdan un determinado tesoro\n");
                            eleccion2 = in.readLine();
                            if (eleccion2.equals("")) {
                                throw new Exception("Introduzca un numero del 1 al 5 para continuar");
                            }
                            int opcion2 = Integer.parseInt(eleccion2); // lectura de un int.
                            switch (opcion2) {
                                case 1:
                                    while (contador < BDMonstruos.size()) {
                                        monstruo = BDMonstruos.get(contador);
                                        if (monstruo.getCombatLevel() > 10) {
                                            myBD.mostrarMonstruoFormateado(monstruo);
                                        }
                                        contador++;
                                    }
                                case 2:
                                    while (contador < BDMonstruos.size()) {
                                        monstruo = BDMonstruos.get(contador);
                                        if (monstruo.getBadConsequence().getLevels() != 0) {
                                            myBD.mostrarMonstruoFormateado(monstruo);
                                        }
                                        contador++;
                                    }
                                case 3:
                                    while (contador < BDMonstruos.size()) {
                                        monstruo = BDMonstruos.get(contador);
                                        if (monstruo.getPrice().getLevel() > 1) {
                                            myBD.mostrarMonstruoFormateado(monstruo);
                                        }
                                        contador++;
                                    }
                                case 4:
                                    while (contador < BDMonstruos.size()) {
                                        monstruo = BDMonstruos.get(contador);
                                        if (!monstruo.getBadConsequence().getSpecificHiddenTreasures().isEmpty() && !monstruo.getBadConsequence().getSpecificVisibleTreasures().isEmpty()) {
                                            myBD.mostrarMonstruoFormateado(monstruo);
                                        }
                                        contador++;
                                    }
                            }
                        } catch (Exception ex) { // captura de la excepción
                            System.err.println(ex);
                        }
                        break;
                    case 5:
                        int c = 0;
                        while (c < BDMonstruos.size()) {
                            monstruo = BDMonstruos.get(c);
                            myBD.mostrarMonstruoFormateado(monstruo);
                            c++;
                        }
                        break;
                }
            } catch (Exception ex) { // captura de la excepción
                System.err.println(ex);
            }
        } while (opcion != 0);
        System.out.println("Saliendo de la Aplicación");
    } // de main(...)
}
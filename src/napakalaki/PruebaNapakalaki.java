/**
 * Clase Principal del Proyecto
 * que sería como si fuera la clase controlador del proyecto
 * seria la que soporta todas las llamadas que se realizan
 * por parte del usuario y la que realiza las llamadas hacia
 * las otras clase para que se ejecute el programa.
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
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	   
    /*
     * Ejemplos de lectura desde teclado con in.
     * String s = in.readLine();//Lectura de un String
     * double d= Double.parseDouble(in.readLine());// lectura de un duoble
     * int i = Integer.parseInt(in.readLine());// lectura de un int
     * Estado estado = Estado.valueOf(in.readLine()); // lectura de un enum
     */
    private static ArrayList<Monster> BDMonstruos = new ArrayList();
    //private static ArrayList<Treasure> BDTesoros = new ArrayList();

    /**
     * Con esta función podemos escribir por pantalla
     * el objeto monstruo que le pasamos por 
     * parametros pero con las cabeceras de los 
     * campos.
     * @param miMonstruo es el objeto monstruo que queremos imprimir por pantalla 
     */
    private static void mostrarMonstruoFormateado(Monster miMonstruo){
        System.out.println("Nombre del Monstruo: " + miMonstruo.getName());
        System.out.println("Nivel de Combate: " + miMonstruo.getCombatLevel());
        Prize precioMonstruo = miMonstruo.getPrice();
        System.out.println("Tesoros que podemos ganar: " + precioMonstruo.getTreasures());
        System.out.println("Cantidad de niveles que conseguimos por ganar: " + precioMonstruo.getLevel());
        BadConsequence MalRolloMonstruo = miMonstruo.getBadConsequence();
        System.out.println("Texto MR: " + MalRolloMonstruo.getText());
        System.out.println("Niveles de MR que perdemos: " + MalRolloMonstruo.getLevels());
        System.out.println("String: " + MalRolloMonstruo.toString() + "\n");
    }

    /**
     * Con esta función podemos escribir por pantalla
     * el objeto tesoro que le pasamos por 
     * parametros pero con las cabeceras de los 
     * campos.
     * @param miTesoro es el objeto tesoro que queremos imprimir por pantalla 
     */
//    private static void mostrarTesoroFormateado(Treasure miTesoro){
//            System.out.println("Nombre del Tesoro: " + miTesoro.getName());
//            System.out.println("Cantidad de Monedas de Oro: " + miTesoro.getGoldCoins());
//            System.out.println("Cantidad Minima de Bonus: " + miTesoro.getMinBonus());
//            System.out.println("Cantidad Maxima de Bonus: " + miTesoro.getMaxBonus());
//            System.out.println("Tipo de Tesoro que conseguimos: " + miTesoro.mType + "\n");
//    }

    /**
     * Esta es una funcion auxiliar que nos sirve
     * para mostrar un submenu en la consola
     */
    private static void submenu(){
        int opcion2=0, contador=0;
        Monster monstruo = null;
        String eleccion2 = "";
        try {
            System.out.println("1. Solamente los que tengan un nivel de Combate mayor que 10\n"+
                            "2. Que en el Mal Rollo se pierdan niveles\n"+
                            "3. Que en el Buen Rollo se ganen mas de 1 nivel\n"+
                            "4. Que en el Mal Rollo pierdan un determinado tesoro\n");
            eleccion2 = in.readLine();
            if (eleccion2=="") throw new Exception("Introduzca un numero del 1 al 4 para continuar");
            opcion2 = Integer.parseInt(eleccion2); // lectura de un int.
            switch(opcion2){
                case 1:
                    while (contador < BDMonstruos.size()){
                            monstruo = BDMonstruos.get(contador);
                            if (monstruo.getCombatLevel()>10){
                                    mostrarMonstruoFormateado(monstruo);
                            }
                            contador++;
                    }
                case 2:
                    while (contador < BDMonstruos.size()){
                            monstruo = BDMonstruos.get(contador);
                            if (monstruo.getBadConsequence().getLevels()!=0){
                                    mostrarMonstruoFormateado(monstruo);
                            }
                            contador++;
                    }
                case 3:
                    while (contador < BDMonstruos.size()){
                            monstruo = BDMonstruos.get(contador);
                            if (monstruo.getPrice().getLevel()>1){
                                    mostrarMonstruoFormateado(monstruo);
                            }
                            contador++;
                    }
                case 4:
                    while (contador < BDMonstruos.size()){
                            monstruo = BDMonstruos.get(contador);
                            if (!monstruo.getBadConsequence().getSpecificHiddenTreasures().isEmpty() && !monstruo.getBadConsequence().getSpecificVisibleTreasures().isEmpty()){
                                    mostrarMonstruoFormateado(monstruo);
                            }
                            contador++;
                    }
                }
            }catch(Exception ex){ // captura de la excepción
                System.err.println(ex);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        //mCardDealer.initCards();
        int opcion=0; //variables que nos controlan las opciónes de menú.
        String eleccion="";
        Monster monstruo = null;
        //Treasure tesoro = null;

        do{ //do-while que controlara todo el menú
                try{// tratamiento de las excepciones
                        //mostrar menú(usando System.out.println(...)) con las siguientes opciones
                        System.out.println("1. Crear Objeto 'Prize',asignar valores, mostrar valores\n"+
                                        "2. Crear el Objeto Mal Rollo y llenar sus datos\n"+
                                        "3. Crear el Objeto Monstruo y llenar sus datos\n"+
                                        "4. Mostrar Monstruos pero...\n"+
                                        "5. Mostrar todos los Monstruos\n"+
                                        "6. Mostrar todos los Tesoros"+
                                        "\n"+
                                        "0. Terminar Aplicación");
                        eleccion = in.readLine(); 
                        if (eleccion=="") throw new Exception("Introduzca un numero del 0 al 6 para continuar");
                        opcion = Integer.parseInt(eleccion); // lectura de un int.
                        if (opcion<0 || opcion>6) throw new Exception("La opción elegida no es valida");
                        // Montar un switch con todas las opciones de menú.
                        switch(opcion){
                            case 1:
                                System.out.println("Creando objeto 'Prize'\n");
                                System.out.println("Introduzca el numero de Tesoros\n"
                                                + "pulse enter y luego el numero para el\n"
                                                + "nivel pulse enter para terminar\n");
                                Prize precioMonstruo = new Prize(Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()));
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
                                BadConsequence mRollo = new BadConsequence(in.readLine(),Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()));
                                System.out.println("Objeto 1 Creado\n");
                                System.out.println("Valor del Mal Rollo del objeto creado: " + mRollo.getText() + "\n");
                                System.out.println("Valor de los niveles del objeto creado: " + mRollo.getLevels() + "\n");
                                System.out.println("probando metodo toString:\n" + mRollo.toString());
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
                                Prize precioMonstruo1 = new Prize(Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()));
                                BadConsequence MalRolloMonstruo = new BadConsequence(in.readLine(),Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()));
                                monstruo = new Monster(in.readLine(),Integer.parseInt(in.readLine()),MalRolloMonstruo,precioMonstruo1);
                                BDMonstruos.add(monstruo);
                                System.out.println("Objeto monstruo Creado\n");
                                System.out.println("Valor del nivel de conbate del mostruo creado: " + monstruo.getCombatLevel() + "\n");
                                System.out.println("Valor del nombre del monstruo creado: " + monstruo.getName() + "\n");
                                System.out.println("probando metodo toString:\n" + monstruo.toString());
                                break;
                            case 4:
                                submenu();
                                break;
                            case 5:
                                int c=0;
                                while (c < BDMonstruos.size()){
                                        monstruo = BDMonstruos.get(c);
                                        mostrarMonstruoFormateado(monstruo);
                                        c++;
                                }
                                break;
//                            case 6:
//                                c=0;
//                                while (c < BDTesoros.size()){
//                                        Treasure tesoro = BDTesoros.get(c);
//                                        mostrarTesoroFormateado(tesoro);
//                                        c++;
//                                }
//                                break;
//                            default:
//                                System.out.println("Error de Selección");
//                                break;
                        }
                }catch(Exception ex){ // captura de la excepción
                        System.err.println(ex);
        } 
        }while(opcion !=0);    
        System.out.println("Saliendo de la Aplicación");
    } // de main(...)
}

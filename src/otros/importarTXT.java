package otros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import napakalaki.BadConsequence;
import napakalaki.Monster;
import napakalaki.Prize;
import napakalaki.TreasureKind;

public class importarTXT {

    private static final String FICHERO = "/resources/base_datos_monstruos.txt";
    private static final importarTXT myInstanciaImportar = new importarTXT(FICHERO);
    private ArrayList<Monster> BDMonstruos = new ArrayList();
    //private ArrayList<TreasureKind> BDTreasure = new ArrayList();
    private BadConsequence MalRolloMonstruo;
    private Prize precioMonstruo;
    private FileReader fr = null;
    private BufferedReader br = null;
    /*
     * Ejemplos de lectura desde teclado con in.
     * String s = in.readLine();//Lectura de un String
     * double d= Double.parseDouble(in.readLine());// lectura de un duoble
     * int i = Integer.parseInt(in.readLine());// lectura de un int
     * Estado estado = Estado.valueOf(in.readLine()); // lectura de un enum
     */
    private String[] columnas = null;
    private String fila = null;

    //	CONSTRUCTOR
    /**
     * Lo unico que necesitamos es el nombre del fichero donde estan los datos
     *
     * @param nombreFichero esta es la dirección donde se encuentra el fichero
     * BD.txt con un formato especifico
     */
    private importarTXT(String nombreFichero) {
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            this.fr = new FileReader(getClass().getResource(nombreFichero).getPath());
            this.br = new BufferedReader(fr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cargarBD();
    }

    //	METODOS
    /**
     * Con este metodo conseguimos que sea la misma instancia del objeto
     * importar, y asi por lo tanto conseguimos que se habrá solo una vez el
     * fichero de base de datos
     *
     * @return devuelve un objeto importarTXT
     */
    public static importarTXT getInstancia() {
        return myInstanciaImportar;
    }

    /**
     * Con este metodo devolvemos el array que contiene la base de datos de
     * Monstruos
     *
     * @return se devuelve un Array con lo monstruos ya metidos
     */
    public ArrayList<Monster> getMonsterBD() {
        return this.BDMonstruos;
    }

    /**
     * Con esto mostramos por pantalla los datos del monstruo que pasamos por
     * parametros
     *
     * @param miMonstruo objeto monstruo que queremos monstrar
     */
    public void mostrarMonstruoFormateado(Monster miMonstruo) {
        System.out.println(
        "Nombre del Monstruo: " + miMonstruo.getName()
        +"Nivel de Combate: " + miMonstruo.getCombatLevel()
        +"Tesoros que podemos ganar: " + miMonstruo.getPrize().getTreasures()
        +"Cantidad de niveles que conseguimos por ganar: " + miMonstruo.getPrize().getLevels()
        +"Texto MR: " + miMonstruo.getBadConsequence().getText()
        +"Niveles de MR que perdemos: " + miMonstruo.getBadConsequence().getLevels()
        +"String: " + miMonstruo.getBadConsequence().toString()
        + "\n");
    }

    /**
     * Con este metodo cargamos el fichero de texto que debe de estar
     * devidamente formateado en un arrayList de Monster.
     */
    private void cargarBD() {
        BadConsequence malrollo;
        try {
            while ((fila = this.br.readLine()) != null) {
                //System.out.println("La ultima linea es : " + fila);
                columnas = fila.split(",");
                if (columnas[10].equals("merte")) {
//                    malrollo = new BadConsequence(columnas[4], 0, true);
                } else if (!columnas[8].isEmpty() || !columnas[9].isEmpty()) {
                    ArrayList<TreasureKind> THidden = leeTesoros(columnas[8]);
                    ArrayList<TreasureKind> TVisible = leeTesoros(columnas[9]);
//                    malrollo = new BadConsequence(columnas[4], Integer.parseInt(columnas[5]), THidden, TVisible);
                } else {
//                    malrollo = new BadConsequence(columnas[4], Integer.parseInt(columnas[5]), Integer.parseInt(columnas[6]), Integer.parseInt(columnas[7]));
                }
                Prize precio = new Prize(Integer.parseInt(columnas[2]), Integer.parseInt(columnas[3]));
//                this.BDMonstruos.add(new Monster(columnas[0], Integer.parseInt(columnas[1]), malrollo, precio));
            }
            this.fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Con este metodo lo que hacemos es convertir una cadena de texto separada
     * por "," en un ArrayList<TreasureKind> de esa manera podemos tener los
     * objetos que realmente necesitamos en el ArrayList devuelto.
     *
     * @param es una cadena de texto separada por comas con los objetos
     * @return Es un ArrayList<TreasureKind> que contiene los objetos
     */
    private ArrayList<TreasureKind> leeTesoros(String datos) {
        ArrayList<TreasureKind> Tesoros = new ArrayList();
        String[] objTesoro = datos.split("-");
        int c = 0;
        while (objTesoro.length > 0 && c < objTesoro.length) {
            switch (objTesoro[c]) {
                case "armor":
                    Tesoros.add(TreasureKind.ARMOR);
                    break;
                case "oneHand":
                    Tesoros.add(TreasureKind.ONEHAND);
                    break;
                case "bothHand":
                    Tesoros.add(TreasureKind.BOTHHANDS);
                    break;
                case "shoe":
                    Tesoros.add(TreasureKind.SHOES);
                    break;
                case "necklace":
                    Tesoros.add(TreasureKind.NECKLACE);
                    break;
            }
            c++;
        }
        return Tesoros;
    }

    public void muestraFila() {
        for (int c = 0; c <= 10; c++) {
            System.out.println("La Columna: " + c + " contiene esto: " + columnas[c] + "\n");
        }
    }
}

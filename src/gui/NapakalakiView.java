//  PDOO GRUPO A
//  JESUS ANGEL GONZALEZ NOVEZ   76440070F
//  CARLOS DE LA TORRE			 75145459C
package gui;

import napakalaki.CombatResult;
import napakalaki.TreasureKind;
import napakalaki.Monster;
import napakalaki.CultistPlayer;
import napakalaki.Player;
import napakalaki.Circunstancia;
import napakalaki.Napakalaki;
import napakalaki.Dice;
import napakalaki.Treasure;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class NapakalakiView extends javax.swing.JFrame implements Vista {

    private static final long serialVersionUID = 1L;
    private Napakalaki napakalaki;
    private Dice dice;
    private ArrayList<String> names = new ArrayList<String>();

    private Monster currentMonster;
    private MonsterView monsterImage;
    private Player currentPlayer;
    private ArrayList<TreasureView> tesorosVisiblesAlimpiar = new ArrayList<TreasureView>();
    private ArrayList<Treasure> tesorosVisiblesSeleccionados = new ArrayList<Treasure>();
    private ArrayList<TreasureView> tesorosOcultosAlimpiar = new ArrayList<TreasureView>();
    private ArrayList<Treasure> tesorosOcultosSeleccionados = new ArrayList<Treasure>();
    private ArrayList<Boolean> jugadoresVivos = new ArrayList<Boolean>();

    private class TreasureView extends JPanel {

        private static final long serialVersionUID = 1L;
        protected Treasure treasure; // asociación con el treasure que representa
        private JTextArea jL_name = new JTextArea();
        private JLabel jL_bonus = new JLabel();
        private JLabel jL_piezasOro = new JLabel();
        private JLabel jL_tipo = new JLabel();

        /* Imagen correspondiente al treasure */
        private Image imagenTesoro;

        TreasureView(Treasure unTesoro) {
            treasure = unTesoro;
            try {
                imagenTesoro = (new ImageIcon(getClass().getClassLoader()
                        .getResource(
                                "resources/Tesoros/" + treasure.getName()
                                + ".png"))).getImage();
            } catch (Exception e) {
                JOptionPane
                        .showMessageDialog(
                                this,
                                "La imagen resources/Tesoros/"
                                + treasure.getName()
                                + ".png no está disponible.\n"
                                + "Contactar con los programadores para subsanar el fallo: "
                                + e.getMessage(), "Error!!!",
                                JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            this.setPreferredSize(new java.awt.Dimension(120, 150));
            this.setLayout(new java.awt.GridLayout());
            this.setBorder(javax.swing.BorderFactory
                    .createLineBorder(Color.BLACK));

            jL_name.setFocusable(false);
            jL_name.setEditable(false);
            jL_name.setBackground(Color.WHITE);
            jL_name.setColumns(15);
            jL_name.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
            jL_name.setOpaque(false);
            jL_name.setLineWrap(true);
            jL_name.setRows(3);
            jL_name.setText(treasure.getName());
            jL_name.setToolTipText("");
            jL_name.setWrapStyleWord(true);
            jL_name.setAutoscrolls(false);

            jL_bonus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jL_bonus.setText("+" + treasure.getBasicValue() + "/+"
                    + treasure.getSpecialValue());
            jL_piezasOro
                    .setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            jL_piezasOro.setText(treasure.getGoldCoins() + " oro");
            jL_tipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            jL_tipo.setText("" + treasure.getType());

            this.setOpaque(false);
            setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            this.add(jL_name, new java.awt.GridLayout(5, 5, 110, -1));
            this.add(jL_tipo, new java.awt.GridLayout(5, 120, 90, -1));
            this.add(jL_piezasOro, new java.awt.GridLayout(5, 132, 90, -1));
            this.add(jL_bonus, new java.awt.GridLayout(20, 132, 90, -1));

        }

        /*
         * Sobreescritura del método paint del JPanel para añadir la imagen del
         * treasure
         */
        @Override
        public void paint(Graphics g) {
            if (imagenTesoro != null) {
                g.drawImage(imagenTesoro, 0, 0, null);
            }
            super.paint(g);
        }
    }

    private class VisibleTreasureView extends TreasureView {

        private static final long serialVersionUID = 1L;

        VisibleTreasureView(Treasure unTesoro) {
            super(unTesoro);
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (tesorosVisiblesSeleccionados
                            .contains(VisibleTreasureView.this.treasure)) {
                        tesorosVisiblesSeleccionados
                                .remove(VisibleTreasureView.this.treasure);
                        VisibleTreasureView.this.setBorder(BorderFactory
                                .createLineBorder(Color.black));
                        VisibleTreasureView.this.setEnabled(true);
                    } else {
                        tesorosVisiblesSeleccionados
                                .add(VisibleTreasureView.this.treasure);
                        VisibleTreasureView.this.setBorder(BorderFactory
                                .createMatteBorder(5, 5, 5, 5, Color.red));
                        VisibleTreasureView.this.setEnabled(false);
                    }
                }
            });
        }
    }

    private class HiddenTreasureView extends TreasureView {

        private static final long serialVersionUID = 1L;

        HiddenTreasureView(Treasure unTesoro) {
            super(unTesoro);
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (tesorosOcultosSeleccionados
                            .contains(HiddenTreasureView.this.treasure)) {
                        tesorosOcultosSeleccionados
                                .remove(HiddenTreasureView.this.treasure);
                        HiddenTreasureView.this.setBorder(BorderFactory
                                .createLineBorder(Color.black));
                        HiddenTreasureView.this.setEnabled(true);
                    } else {
                        tesorosOcultosSeleccionados
                                .add(HiddenTreasureView.this.treasure);
                        HiddenTreasureView.this.setBorder(BorderFactory
                                .createMatteBorder(5, 5, 5, 5, Color.red));
                        HiddenTreasureView.this.setEnabled(false);
                    }
                }
            });
        }
    }

    private class MonsterView extends JPanel {

        private static final long serialVersionUID = 1L;
        private Monster monstruo;
        private Image monsterImage;

        MonsterView(Monster monstruo) {
            this.monstruo = monstruo;
            try {
                monsterImage = (new ImageIcon(getClass().getClassLoader()
                        .getResource(
                                "resources/Monstruos/"
                                + this.monstruo.getName() + ".png")))
                        .getImage();
            } catch (Exception e) {
                JOptionPane
                        .showMessageDialog(
                                this,
                                "La imagen resources/Monstruos/"
                                + this.monstruo.getName()
                                + ".png no está disponible.\n"
                                + "Contactar con los programadores para subsanar el fallo: "
                                + e.getMessage(), "Error!!!",
                                JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            this.setPreferredSize(new java.awt.Dimension(120, 150));
            this.setBorder(javax.swing.BorderFactory
                    .createLineBorder(Color.BLACK));
            this.setOpaque(false);
        }

        /*
         * Sobreescritura del método paint de JPanel para dibujar la imagen del
         * monstruo
         */
        @Override
        public void paint(Graphics g) {
            if (monsterImage != null) {
                g.drawImage(monsterImage, 0, 0, null);
            }
            super.paint(g);
        }

    }

    public void setModel(Napakalaki model) {
        this.napakalaki = model;
    }

    public NapakalakiView() {
        initComponents();
    }

    private void initComponents() {

        jP_monstruos = new javax.swing.JPanel();
        jL_nombreMonstruo = new javax.swing.JLabel();
        jL_nivelesGanados = new javax.swing.JLabel();
        jL_tesorosGanados = new javax.swing.JLabel();
        jL_nivel = new javax.swing.JLabel();
        jL_nivelContraSectarios = new javax.swing.JLabel();
        jL_nivelesPerdidos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jL_malRollo = new javax.swing.JTextArea();
        jP_imgMonstruo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jL_resultadoCombate = new javax.swing.JLabel();
        jP_jugadores = new javax.swing.JPanel();
        jL_nombreJugador = new javax.swing.JLabel();
        jP_tesorosVisibles = new javax.swing.JPanel();
        jP_tesorosOcultos = new javax.swing.JPanel();
        jL_esSectario = new javax.swing.JLabel();
        jL_nivelCombate = new javax.swing.JLabel();
        jL_bonusSectario = new javax.swing.JLabel();
        jL_malRolloPendiente = new javax.swing.JLabel();
        jP_malRolloPendiente = new javax.swing.JPanel();
        jL_ocultosPerdidos = new javax.swing.JLabel();
        jL_tipoOcultosPerdidos = new javax.swing.JLabel();
        jL_visiblesPerdidos = new javax.swing.JLabel();
        jL_tipoVisiblesPerdidos = new javax.swing.JLabel();
        jL_nivelBasico = new javax.swing.JLabel();
        jL_excesoCartas = new javax.swing.JLabel();
        jB_equiparse = new javax.swing.JButton();
        jB_comprarNivel = new javax.swing.JButton();
        jB_descartarseTesoros = new javax.swing.JButton();
        jB_combatir = new javax.swing.JButton();
        jB_siguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Napakalaki");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(true);

        jP_monstruos.setBorder(javax.swing.BorderFactory.createTitledBorder("Territorio del Terrible Monstruo"));
        //jP_monstruos.setPreferredSize(new java.awt.Dimension(904, 180));

        jL_nombreMonstruo.setText("Nombre Monstruo");

        jL_nivelesGanados.setText("Niveles Ganados");

        jL_tesorosGanados.setText("Tesoros Ganados");

        jL_nivel.setText("Nivel");

        jL_nivelContraSectarios.setText("Nivel contra Sectarios");

        jL_nivelesPerdidos.setText("Niveles Perdidos");

        jL_malRollo.setEditable(false);
        jL_malRollo.setBackground(new java.awt.Color(238, 238, 238));
        jL_malRollo.setColumns(20);
        jL_malRollo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jL_malRollo.setLineWrap(true);
        jL_malRollo.setRows(4);
        jL_malRollo.setText("Mal Rollo del Monstruo");
        jL_malRollo.setToolTipText("");
        jL_malRollo.setWrapStyleWord(true);
        jL_malRollo.setAutoscrolls(false);
        jScrollPane1.setViewportView(jL_malRollo);

        jP_imgMonstruo.setMaximumSize(new java.awt.Dimension(120, 155));
        jP_imgMonstruo.setMinimumSize(new java.awt.Dimension(120, 150));
        jP_imgMonstruo.setPreferredSize(new java.awt.Dimension(120, 155));

        jPanel1.setBorder(javax.swing.BorderFactory
                .createTitledBorder("Resultado del Combate"));

        jL_resultadoCombate.setText("Resultado del Combate");
        jL_resultadoCombate
                .setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
                jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        jPanel1Layout
                        .createSequentialGroup()
                        .addContainerGap(160, Short.MAX_VALUE)
                        .addComponent(jL_resultadoCombate,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 189,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
                javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        jPanel1Layout
                        .createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(jL_resultadoCombate).addContainerGap()));

        javax.swing.GroupLayout jP_monstruosLayout = new javax.swing.GroupLayout(
                jP_monstruos);
        jP_monstruosLayout.setHorizontalGroup(
                jP_monstruosLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(jP_monstruosLayout.createSequentialGroup()
                        .addGroup(jP_monstruosLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(jP_monstruosLayout.createSequentialGroup()
                                        .addGap(31)
                                        .addGroup(jP_monstruosLayout.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(jL_nombreMonstruo, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                .addComponent(jL_nivelesGanados, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jL_tesorosGanados, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(75)
                                        .addGroup(jP_monstruosLayout.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(jL_nivel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jL_nivelContraSectarios, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                                .addComponent(jL_nivelesPerdidos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(44)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
                                .addGroup(jP_monstruosLayout.createSequentialGroup()
                                        .addGap(152)
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(18)
                        .addComponent(jP_imgMonstruo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(58, Short.MAX_VALUE))
        );
        jP_monstruosLayout.setVerticalGroup(
                jP_monstruosLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(jP_monstruosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jP_monstruosLayout.createParallelGroup(Alignment.TRAILING)
                                .addGroup(jP_monstruosLayout.createSequentialGroup()
                                        .addGroup(jP_monstruosLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(jL_nombreMonstruo)
                                                .addComponent(jL_nivel))
                                        .addGap(7)
                                        .addGroup(jP_monstruosLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(jL_nivelesGanados)
                                                .addComponent(jL_nivelContraSectarios))
                                        .addPreferredGap(ComponentPlacement.RELATED)
                                        .addGroup(jP_monstruosLayout.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(jL_nivelesPerdidos)
                                                .addComponent(jL_tesorosGanados)))
                                .addGroup(jP_monstruosLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(jP_imgMonstruo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(18)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
        jP_monstruos.setLayout(jP_monstruosLayout);

        jP_jugadores.setBorder(javax.swing.BorderFactory
                .createTitledBorder("Parcelita de los débiles jugadores"));

        jL_nombreJugador.setText("Nombre Jugador");

        jP_tesorosVisibles.setBorder(javax.swing.BorderFactory
                .createTitledBorder("Equipo"));

        jP_tesorosOcultos.setBorder(javax.swing.BorderFactory
                .createTitledBorder("Cartas Ocultas"));

        jL_esSectario.setText("Humano");

        jL_nivelCombate.setText("Nivel de Combate");

        jL_bonusSectario.setText("Bonus Sectario");

        jP_malRolloPendiente.setBorder(javax.swing.BorderFactory
                .createTitledBorder("MalRollo Pendiente"));

        jL_ocultosPerdidos.setText("Nº Ocultos a perder:");

        jL_tipoOcultosPerdidos.setText("Tipo:");

        jL_visiblesPerdidos.setText("Nº Visibles a perder");

        jL_tipoVisiblesPerdidos.setText("Tipo:");

        javax.swing.GroupLayout jP_malRolloPendienteLayout = new javax.swing.GroupLayout(
                jP_malRolloPendiente);
        jP_malRolloPendiente.setLayout(jP_malRolloPendienteLayout);
        jP_malRolloPendienteLayout
                .setHorizontalGroup(jP_malRolloPendienteLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                jP_malRolloPendienteLayout
                                .createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        jP_malRolloPendienteLayout
                                        .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(
                                                jL_ocultosPerdidos,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                236,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(
                                                jP_malRolloPendienteLayout
                                                .createParallelGroup(
                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                        false)
                                                .addComponent(
                                                        jL_tipoOcultosPerdidos,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                .addComponent(
                                                        jL_visiblesPerdidos,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        224,
                                                        Short.MAX_VALUE)
                                                .addComponent(
                                                        jL_tipoVisiblesPerdidos,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)))
                                .addContainerGap(
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)));
        jP_malRolloPendienteLayout
                .setVerticalGroup(jP_malRolloPendienteLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                jP_malRolloPendienteLayout
                                .createSequentialGroup()
                                .addContainerGap(
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(jL_ocultosPerdidos)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jL_tipoOcultosPerdidos)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jL_visiblesPerdidos)
                                .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jL_tipoVisiblesPerdidos)
                                .addContainerGap()));

        jL_nivelBasico.setText("Nivel Básico");

        jL_excesoCartas.setText("");

        javax.swing.GroupLayout jP_jugadoresLayout = new javax.swing.GroupLayout(
                jP_jugadores);
        jP_jugadores.setLayout(jP_jugadoresLayout);
        jP_jugadoresLayout
                .setHorizontalGroup(jP_jugadoresLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                jP_jugadoresLayout
                                .createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        jP_jugadoresLayout
                                        .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                false)
                                        .addComponent(
                                                jP_tesorosVisibles,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                649,
                                                Short.MAX_VALUE)
                                        .addComponent(
                                                jP_tesorosOcultos,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                649,
                                                Short.MAX_VALUE))
                                .addGroup(
                                        jP_jugadoresLayout
                                        .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                jP_jugadoresLayout
                                                .createSequentialGroup()
                                                .addGroup(
                                                        jP_jugadoresLayout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jP_jugadoresLayout
                                                                .createSequentialGroup()
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(
                                                                        jL_malRolloPendiente))
                                                        .addGroup(
                                                                jP_jugadoresLayout
                                                                .createSequentialGroup()
                                                                .addGap(21,
                                                                        21,
                                                                        21)
                                                                .addGroup(
                                                                        jP_jugadoresLayout
                                                                        .createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(
                                                                                jL_nivelCombate,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                150,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(
                                                                                jL_esSectario,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                150,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(
                                                                                jL_nivelBasico,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                150,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(
                                                                                jL_nombreJugador,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                150,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(
                                                                                jL_excesoCartas,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                138,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(
                                                                                jL_bonusSectario,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                255,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0,
                                                                        12,
                                                                        Short.MAX_VALUE)))
                                                .addContainerGap())
                                        .addGroup(
                                                jP_jugadoresLayout
                                                .createSequentialGroup()
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(
                                                        jP_malRolloPendiente,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(
                                                        24,
                                                        Short.MAX_VALUE)))));
        jP_jugadoresLayout
                .setVerticalGroup(jP_jugadoresLayout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                jP_jugadoresLayout
                                .createSequentialGroup()
                                .addGroup(
                                        jP_jugadoresLayout
                                        .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                jP_jugadoresLayout
                                                .createSequentialGroup()
                                                .addGap(27,
                                                        27,
                                                        27)
                                                .addComponent(
                                                        jL_nombreJugador)
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(
                                                        jL_nivelBasico)
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(
                                                        jL_nivelCombate)
                                                .addGap(12,
                                                        12,
                                                        12)
                                                .addComponent(
                                                        jL_malRolloPendiente)
                                                .addGap(18,
                                                        18,
                                                        18)
                                                .addComponent(
                                                        jL_esSectario)
                                                .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(
                                                        jL_bonusSectario)
                                                .addGap(18,
                                                        18,
                                                        18)
                                                .addComponent(
                                                        jL_excesoCartas))
                                        .addGroup(
                                                jP_jugadoresLayout
                                                .createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(
                                                        jP_tesorosVisibles,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        193,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(14, 14, 14)
                                .addGroup(
                                        jP_jugadoresLayout
                                        .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                false)
                                        .addComponent(
                                                jP_tesorosOcultos,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                195,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(
                                                jP_malRolloPendiente,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE))
                                .addContainerGap()));

        jB_equiparse.setText("Equiparse");
        jB_equiparse.setEnabled(false);
        jB_equiparse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_equiparseActionPerformed(evt);
            }
        });

        jB_comprarNivel.setText("Comprar Nivel");
        jB_comprarNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_comprarNivelActionPerformed(evt);
            }
        });

        jB_descartarseTesoros.setText("Descartarse Tesoros");
        jB_descartarseTesoros.setEnabled(false);
        jB_descartarseTesoros
                .addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jB_descartarseTesorosActionPerformed(evt);
                    }
                });

        jB_combatir.setText("¡COMBATIR!");
        jB_combatir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_combatirActionPerformed(evt);
            }
        });

        jB_siguiente.setText("Siguiente");
        jB_siguiente.setEnabled(false);
        jB_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_siguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(
                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                        layout.createSequentialGroup()
                                        .addComponent(
                                                jB_equiparse)
                                        .addGap(18, 18,
                                                18)
                                        .addComponent(
                                                jB_comprarNivel)
                                        .addGap(18, 18,
                                                18)
                                        .addComponent(
                                                jB_descartarseTesoros)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(
                                                jB_combatir)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addComponent(
                                                jB_siguiente))
                                .addGroup(
                                        layout.createSequentialGroup()
                                        .addComponent(
                                                jP_jugadores,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0,
                                                0,
                                                Short.MAX_VALUE))
                                .addComponent(
                                        jP_monstruos,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        975, Short.MAX_VALUE))
                        .addContainerGap()));
        layout.setVerticalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jP_monstruos,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                184,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jP_jugadores,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(
                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                                layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jB_siguiente)
                                .addComponent(jB_combatir)
                                .addComponent(
                                        jB_descartarseTesoros)
                                .addComponent(jB_comprarNivel)
                                .addComponent(jB_equiparse))
                        .addContainerGap(13, Short.MAX_VALUE)));

        pack();
    }

    private void jB_equiparseActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jB_equiparseActionPerformed
        napakalaki.makeTreasuresVisible(tesorosOcultosSeleccionados);
        refreshPlayer();
    }

    private void jB_descartarseTesorosActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jB_descartarseTesorosActionPerformed
        napakalaki.discardHiddenTreasures(tesorosOcultosSeleccionados);
        napakalaki.discardVisibleTreasures(tesorosVisiblesSeleccionados);
        refreshPlayer();

        if (currentPlayer.getPendingBadStuff().isEmpty()) {
            jB_equiparse.setEnabled(true);
        }
    }

    private void jB_comprarNivelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jB_comprarNivelActionPerformed
        napakalaki.buyLevels(tesorosVisiblesSeleccionados,
                tesorosOcultosSeleccionados);
        refreshPlayer();
    }

    private void jB_combatirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jB_combatirActionPerformed
        refreshMonster();
        CombatResult resultado = napakalaki.developCombat();
        jL_resultadoCombate.setText("" + resultado);

        if (resultado == CombatResult.WINGAME) {
            JOptionPane.showMessageDialog(this,
                    "El player " + currentPlayer.getName()
                    + " ha ganado la partida!!!", "Ganador!!!",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else if (resultado == CombatResult.LOSEANDDIE) {
            currentPlayer.die();
        }

        boolean cumplioMalRollo = currentPlayer.getPendingBadStuff().isEmpty();
        if (cumplioMalRollo == true) {
            jB_comprarNivel.setEnabled(true);
            jB_equiparse.setEnabled(true);
        }

        jB_siguiente.setEnabled(true);
        jB_descartarseTesoros.setEnabled(true);
        jB_comprarNivel.setEnabled(false);
        jB_combatir.setEnabled(false);
        refreshPlayer();
    }

    int veces = 0;

    private void jB_siguienteActionPerformed(java.awt.event.ActionEvent evt) {
        Circunstancia fin = napakalaki.nextTurn();
        veces++;
        refreshPlayer();

        if (veces >= 3 && names.size() == 3) {
            jB_equiparse.setEnabled(true);
        } else if (veces >= 4 && names.size() == 4) {
            jB_equiparse.setEnabled(true);
        } else {
            jB_equiparse.setEnabled(false);
        }

        jB_comprarNivel.setEnabled(true);
        jB_descartarseTesoros.setEnabled(false);
        jB_siguiente.setEnabled(false);
        jB_combatir.setEnabled(true);
        jL_malRolloPendiente.setText("");
        jL_excesoCartas.setText("");

        limpiaMonstruo();
        if (fin == Circunstancia.PIERDE_TURNO) {
            jL_excesoCartas.setText("FIN ES FALSE");
        }

    }

    private javax.swing.JButton jB_combatir;
    private javax.swing.JButton jB_comprarNivel;
    private javax.swing.JButton jB_descartarseTesoros;
    private javax.swing.JButton jB_equiparse;
    private javax.swing.JButton jB_siguiente;
    private javax.swing.JLabel jL_bonusSectario;
    private javax.swing.JLabel jL_esSectario;
    private javax.swing.JLabel jL_excesoCartas;
    private javax.swing.JTextArea jL_malRollo;
    private javax.swing.JLabel jL_malRolloPendiente;
    private javax.swing.JLabel jL_nivel;
    private javax.swing.JLabel jL_nivelBasico;
    private javax.swing.JLabel jL_nivelCombate;
    private javax.swing.JLabel jL_nivelContraSectarios;
    private javax.swing.JLabel jL_nivelesGanados;
    private javax.swing.JLabel jL_nivelesPerdidos;
    private javax.swing.JLabel jL_nombreJugador;
    private javax.swing.JLabel jL_nombreMonstruo;
    private javax.swing.JLabel jL_ocultosPerdidos;
    private javax.swing.JLabel jL_resultadoCombate;
    private javax.swing.JLabel jL_tesorosGanados;
    private javax.swing.JLabel jL_tipoOcultosPerdidos;
    private javax.swing.JLabel jL_tipoVisiblesPerdidos;
    private javax.swing.JLabel jL_visiblesPerdidos;
    private javax.swing.JPanel jP_imgMonstruo;
    private javax.swing.JPanel jP_jugadores;
    private javax.swing.JPanel jP_malRolloPendiente;
    private javax.swing.JPanel jP_monstruos;
    private javax.swing.JPanel jP_tesorosOcultos;
    private javax.swing.JPanel jP_tesorosVisibles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;

    @Override
    public void Show(ArrayList<String> names) {
        for (int i = 0; i < 3; i++) {
            jugadoresVivos.add(false);
        }
        this.setVisible(true);
        refreshPlayer();
        limpiaMonstruo();

    }

    @Override
    public int getDice(String s1, String s2) {
        return dice.nextNumber(s1, s2);
    }

    private void refreshMonster() {
        if (monsterImage != null) {
            jP_imgMonstruo.remove(monsterImage);
        }
        currentMonster = napakalaki.getCurrentMonster();
        monsterImage = new MonsterView(currentMonster);
        jL_nombreMonstruo.setText(currentMonster.getName());
        jL_nivelesGanados.setText("Niveles Ganados: "
                + currentMonster.getPrize().getLevels());
        jL_tesorosGanados.setText("Tesoros Ganados: "
                + currentMonster.getPrize().getTreasures());
        jL_nivel.setText("Nivel: " + currentMonster.getBasicValue());
        jL_nivelContraSectarios.setText("Nivel contra Sectarios: "
                + currentMonster.getSpecialValue());
        jL_nivelesPerdidos.setText("Niveles perdidos: "
                + currentMonster.getBadConsequence().getLevels());
        jL_resultadoCombate.setText("");
        jL_malRollo.setText(currentMonster.getBadConsequence().getText());

        jP_imgMonstruo.add(monsterImage);

        pack();
        repaint();

    }

    private void refreshPlayer() {
        TreasureView unTesoroGrafico;
        currentPlayer = napakalaki.getCurrentPlayer();

        jL_nombreJugador.setText("" + currentPlayer.getName());
        jL_nivelBasico.setText("Nivel Básico: " + currentPlayer.getLevels());
        jL_nivelCombate.setText("Nivel de combate: "
                + currentPlayer.getCombatLevel());

        if (currentPlayer instanceof CultistPlayer) {
            jL_esSectario.setText("Sectario");
            jL_bonusSectario.setText("+"
                    + ((CultistPlayer) currentPlayer).getMyCultistCard()
                    .getBasicValue()
                    + " por cada Sectario en napakalaki -> "
                    + CultistPlayer.getTotalCultistPlayers());
        } else {
            jL_esSectario.setText("Humano");
            jL_bonusSectario.setText("");
        }

        for (TreasureView tg : tesorosVisiblesAlimpiar) {
            jP_tesorosVisibles.remove(tg);
        }
        tesorosVisiblesAlimpiar.clear();

        for (Treasure t : currentPlayer.getVisibleTreasures()) {
            unTesoroGrafico = new VisibleTreasureView(t);
            jP_tesorosVisibles.add(unTesoroGrafico);
            tesorosVisiblesAlimpiar.add(unTesoroGrafico);
        }
        for (TreasureView tg : tesorosOcultosAlimpiar) {
            jP_tesorosOcultos.remove(tg);
        }
        tesorosOcultosAlimpiar.clear();

        for (Treasure t : currentPlayer.getHiddenTreasures()) {
            unTesoroGrafico = new HiddenTreasureView(t);
            jP_tesorosOcultos.add(unTesoroGrafico);
            tesorosOcultosAlimpiar.add(unTesoroGrafico);
        }

        tesorosVisiblesSeleccionados.clear();
        tesorosOcultosSeleccionados.clear();

        // Actualizamos malRolloPendiente
        if (currentPlayer.getPendingBadStuff().getNHiddenTreasures() == 0
                && currentPlayer.getPendingBadStuff()
                .getSpecificHiddenTreasures().isEmpty()) {
            jL_ocultosPerdidos.setText("");
            jL_tipoOcultosPerdidos.setText("");
        } else {
            jL_ocultosPerdidos.setText("Nº ocultos perdidos: "
                    + currentPlayer.getPendingBadStuff().getNHiddenTreasures());

            String f = new String();
            f += "Tipos: ";
            for (TreasureKind t : currentPlayer.getPendingBadStuff()
                    .getSpecificHiddenTreasures()) {
                f += t + " | ";
            }

            jL_tipoOcultosPerdidos.setText(f);
        }

        if (currentPlayer.getPendingBadStuff().getNVisibleTreasures() == 0
                && currentPlayer.getPendingBadStuff()
                .getSpecificVisibleTreasures().isEmpty()) {
            jL_visiblesPerdidos.setText("");
            jL_tipoVisiblesPerdidos.setText("");

        } else {
            jL_visiblesPerdidos
                    .setText("Nº visibles perdidos: "
                            + currentPlayer.getPendingBadStuff()
                            .getNVisibleTreasures());

            String f = "Tipos: ";
            for (TreasureKind t : currentPlayer.getPendingBadStuff()
                    .getSpecificVisibleTreasures()) {
                f += t + " | ";
            }

            jL_tipoVisiblesPerdidos.setText(f);
        }

        repaint();
        pack();
    }

    private void limpiaMonstruo() {
        // liampiamos info del monstruo
        if (monsterImage != null) {
            jP_imgMonstruo.remove(monsterImage);
        }
        jL_nombreMonstruo.setText("");
        jL_nivelesGanados.setText("");
        jL_tesorosGanados.setText("");
        jL_nivel.setText("");
        jL_nivelContraSectarios.setText("");
        jL_nivelesPerdidos.setText("");
        jL_resultadoCombate.setText("");
        jL_malRollo.setText("");
    }

    @SuppressWarnings("unused")
    private int findPlayer(String name) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }
}

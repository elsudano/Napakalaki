//  PDOO GRUPO A
//  JESUS ANGEL GONZALEZ NOVEZ   76440070F
//  CARLOS DE LA TORRE			 75145459C
package gui;

import java.util.ArrayList;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;

public class PlayersNamesCapture extends javax.swing.JDialog {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> nombres;
    /**
     * Creates new form PlayersNamesCapture
     */
    public PlayersNamesCapture(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

    }

    private void initComponents() {
        jL_jugador0 = new javax.swing.JLabel();
        jL_jugador1 = new javax.swing.JLabel();
        jL_jugador2 = new javax.swing.JLabel();
        jT_nombreJugador0 = new javax.swing.JTextField();
        jT_nombreJugador1 = new javax.swing.JTextField();
        jT_nombreJugador2 = new javax.swing.JTextField();
        cancelar = new javax.swing.JButton();
        comenzar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jL_jugador0.setText("Jugador 1");

        jL_jugador1.setText("Jugador 2");

        jL_jugador2.setText("Jugador 3");

        jT_nombreJugador0.setText("...");
        jT_nombreJugador0.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_nombreJugador0.setText("");
            }
        });

        jT_nombreJugador1.setText("...");
        jT_nombreJugador1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
            	jT_nombreJugador1.setText("");
            }
        });

        jT_nombreJugador2.setText("...");
        jT_nombreJugador2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
            	jT_nombreJugador2.setText("");
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });

        comenzar.setText("Comenzar");
        comenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombres= new ArrayList<String>();
                nombres.add(jT_nombreJugador0.getText());
                nombres.add(jT_nombreJugador1.getText());
                nombres.add(jT_nombreJugador2.getText());
                dispose();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(56)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jL_jugador0)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(jT_nombreJugador0, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        							.addGroup(layout.createSequentialGroup()
        								.addComponent(jL_jugador2)
        								.addPreferredGap(ComponentPlacement.UNRELATED)
        								.addComponent(jT_nombreJugador2))
        							.addGroup(layout.createSequentialGroup()
        								.addComponent(jL_jugador1)
        								.addPreferredGap(ComponentPlacement.UNRELATED)
        								.addComponent(jT_nombreJugador1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(35)
        					.addComponent(cancelar)
        					.addGap(51)
        					.addComponent(comenzar)))
        			.addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(33)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jL_jugador0)
        				.addComponent(jT_nombreJugador0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jL_jugador1)
        				.addComponent(jT_nombreJugador1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jL_jugador2)
        				.addComponent(jT_nombreJugador2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(37)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(cancelar)
        				.addComponent(comenzar))
        			.addContainerGap(50, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }

    /**
     * @param args the command line arguments
     */
    public ArrayList<String> getNames(){
       this.setVisible(true);
        return nombres;
    }

    private javax.swing.JButton cancelar;
    private javax.swing.JButton comenzar;
    private javax.swing.JLabel jL_jugador0;
    private javax.swing.JLabel jL_jugador1;
    private javax.swing.JLabel jL_jugador2;
    private javax.swing.JTextField jT_nombreJugador0;
    private javax.swing.JTextField jT_nombreJugador1;
    private javax.swing.JTextField jT_nombreJugador2;
}

//  PDOO GRUPO A
//  JESUS ANGEL GONZALEZ NOVEZ   76440070F
//  CARLOS DE LA TORRE			 75145459C
package napakalaki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

/**
 * 
 * @author Profesor
 */
public class Dice extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Dice instance = null;
	private Random generator = new Random();
	private Timer timerDice;
	private int value;

	private Dice(java.awt.Frame parent) {
		super(parent, true);

		initComponents();
		timerDice = new Timer(50, diceAction);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void createInstance(java.awt.Frame parent) {
		if (instance == null)
			instance = new Dice(parent);
	}

	public static Dice getInstance() {
		return instance;
	}

	private int privateNextNumber() {
		return (generator.nextInt(6) + 1);
	}

	private ActionListener diceAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ev) {
			value = privateNextNumber();
			jL_dice.setText(Integer.toString(value));
			pack();
		}
	};

	public int nextNumber() {
		return nextNumber("Este es el Numero","");

	}

	public int nextNumber(String message1, String message2) {
		jB_OK.setVisible(false);
		jL_message1.setText(message1);
		jL_message2.setText(message2);
		pack();
		timerDice.start();
		this.setVisible(true);
		return value;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jL_dice = new javax.swing.JLabel();
        jL_message1 = new javax.swing.JLabel();
        jB_OK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jL_message2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dado");
        setMinimumSize(new java.awt.Dimension(400, 280));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jL_dice.setBackground(new java.awt.Color(255, 255, 255));
        jL_dice.setFont(new java.awt.Font("Trebuchet MS", 2, 48)); // NOI18N
        jL_dice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_dice.setText("1");
        jL_dice.setOpaque(true);
        jL_dice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jL_diceMouseClicked(evt);
            }
        });
        getContentPane().add(jL_dice, new java.awt.GridBagConstraints(/**160, 110, 80, 80*/));

        jL_message1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_message1.setText("jLabel1");
        getContentPane().add(jL_message1, new java.awt.GridBagConstraints(/**20, 10, 350, 30*/));

        jB_OK.setText("OK");
        jB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_OKActionPerformed(evt);
            }
        });
        getContentPane().add(jB_OK, new java.awt.GridBagConstraints(/**170, 200, -1, -1*/));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("pincha sobre el dado para detenerlo");
        getContentPane().add(jLabel1, new java.awt.GridBagConstraints(/**70, 80, -1, -1*/));

        jL_message2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_message2.setText("jLabel1");
        getContentPane().add(jL_message2, new java.awt.GridBagConstraints(/**20, 40, 350, 30*/));

        setSize(new java.awt.Dimension(400, 284));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

	private void jL_diceMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jL_diceMouseClicked
		timerDice.stop();
		jB_OK.setVisible(true);
		pack();
	}// GEN-LAST:event_jL_diceMouseClicked

	private void jB_OKActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jB_OKActionPerformed
		this.dispose();
	}// GEN-LAST:event_jB_OKActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_OK;
    private javax.swing.JLabel jL_dice;
    private javax.swing.JLabel jL_message1;
    private javax.swing.JLabel jL_message2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
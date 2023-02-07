
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*; 

public class remote {

 private char value;
 private int veloc = 0;

 JFrame frame = new JFrame("remote");
 final JPanel panel = new JPanel();
 final JLabel label = new JLabel();
 final JLabel label2 = new JLabel("Avanti: W, Indietro: S, Sinistra: A, Destra: D, Freno: J, Spegni: P");

 public void gestisciremote() {

 frame.setSize(600,600);
 
 panel.add(label);
 panel.add(label2);
 
 label.setHorizontalAlignment(JLabel.RIGHT);
 label.setVerticalAlignment(JLabel.BOTTOM);
 label2.setHorizontalAlignment(JLabel.CENTER);
 label2.setVerticalAlignment(JLabel.CENTER);

 
 
 frame.setLayout(new BorderLayout());

 frame.add(panel);
 //frame.add(new JLabel("Avanti: W, Indietro: S, Sinistra: A, Destra: D"), BorderLayout.CENTER);
 frame.pack();
 frame.setVisible(true);
 //frame.setLocationRelativeTo(null);p

 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 

 frame.addKeyListener (new KeyListener() {

 public void keyTyped(KeyEvent e) {
 label.setText("Velocità: " + veloc);
 }
 
 public void keyPressed(KeyEvent e) {
 //System.out.println("Tasto premuto = " + e.getKeyCode() + ", char=" + e.getKeyChar());
 if(e.getKeyCode()== KeyEvent.VK_ESCAPE);
	{
		value=27;
	}
 value = e.getKeyChar();
 }
 public void keyReleased(KeyEvent e) {
 //System.out.println("Tasto rilasciato = " + e.getKeyCode() + ", char=" + e.getKeyChar());
 value = '0';
 }
 });

 frame.add(panel);

 }

 public char getValue() {
 return this.value;
 }
 
 public void setVeloc(String vel) {
 
 veloc = Integer.parseInt(vel);
 }
}
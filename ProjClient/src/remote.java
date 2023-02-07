import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*; 

public class remote {

 private char valore;
 private int speed = 0;

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
 frame.pack();
 frame.setVisible(true);
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 frame.addKeyListener (new KeyListener() {

 public void keyTyped(KeyEvent e) {
 label.setText("Velocità: " + speed);
 }
 
 public void keyPressed(KeyEvent e) {
 if(e.getKeyCode()== KeyEvent.VK_ESCAPE);
	{
		valore=27;
	}
 valore = e.getKeyChar();
 }
 public void keyReleased(KeyEvent e) {
 valore = '0';
 }
 });
 frame.add(panel);
 }
 public char getvalore() {
 return this.valore;
 }
 public void setSpeed(String new_speed) {
 speed = Integer.parseInt(new_speed);
 }
}

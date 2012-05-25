//: Ship.java
// Player ship class definition.
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ship extends JPanel {
  private String modelFile = "data/pic/spitfire.jpg";
  private Image image;
  public int x, y;

  public Ship() {
    ImageIcon ii = new ImageIcon(this.getClass().getResource(modelFile));
    image = ii.getImage();
  }
  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(image, x, y, null);
    talk("Ship paint");
    //try{Thread.sleep(5000);}catch(Exception ign){}
  }
  public void justWait() {
    try{Thread.sleep(5000);}catch(Exception ign){}
  }
  private void talk(String s) {
    String name = Thread.currentThread().getName();
    System.out.format("%s: %s%n", name, s);
  }
  synchronized public void setXY(int x, int y) {
    x = x;
    y = y;
  }
}

package pl.piekarczyk.Asteroids2D.View.ViewableObject;

import javax.swing.*;
import java.awt.*;

public class ViewPhysicalPlayerShip extends ViewPhysicalObject {
  //@OPT better path detection
  private final static String modelFile = "data/pic/spitfire.jpg";
  private static Image image;
  public ViewPhysicalPlayerShip(int nx, int ny, int nRot) {
    super(nx, ny, nRot);
    ImageIcon ii = new ImageIcon(this.getClass().getResource(modelFile));
    image = ii.getImage();
  }
  public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    //@OPT rotate
    g2d.drawImage(image, x, y, null);
  }
}

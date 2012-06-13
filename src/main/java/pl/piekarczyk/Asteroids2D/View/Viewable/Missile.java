package pl.piekarczyk.Asteroids2D.View.Viewable;

import javax.swing.*;
import java.awt.*;
import java.lang.Math;

/**
 * Loads the missile image for the view.
 */
public class Missile extends ViewObject {
  public Missile(double nx, double ny, double nRot) {
    super(nx, ny, nRot);
    height = 5;
    width = 5;
    ImageIcon ii = new ImageIcon(this.getClass().getResource(modelFile));
    image = ii.getImage();
  }
  public Image getImage() {
    return image; 
  }
  private final static String modelFile = "data/pic/missile.jpg";
  private static Image image;
}

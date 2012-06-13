package pl.piekarczyk.Asteroids2D.View.Viewable;

import javax.swing.*;
import java.awt.*;
import java.lang.Math;

/**
 * Loads the player ship image for the view.
 */
public class PlayerShip extends ViewObject {
  public PlayerShip(double nx, double ny, double nRot) {
    super(nx, ny, nRot);
    height = 20;
    width = 20;
    ImageIcon ii = new ImageIcon(this.getClass().getResource(modelFile));
    image = ii.getImage();
  }
  public Image getImage() {
    return image; 
  }
  private final static String modelFile = "data/pic/spitfire.jpg";
  private static Image image;
}

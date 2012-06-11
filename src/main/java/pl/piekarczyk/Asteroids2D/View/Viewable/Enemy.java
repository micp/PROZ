package pl.piekarczyk.Asteroids2D.View.Viewable;

import javax.swing.*;
import java.awt.*;
import java.lang.Math;

public class Enemy extends ViewObject {
  //@OPT better path detection
  private final static String modelFile = "data/pic/enemy.jpg";
  private static Image image;
  public Enemy(double nx, double ny, double nRot) {
    super(nx, ny, nRot);
    ImageIcon ii = new ImageIcon(this.getClass().getResource(modelFile));
    image = ii.getImage();
  }
  public Image getImage() { return image; }
}

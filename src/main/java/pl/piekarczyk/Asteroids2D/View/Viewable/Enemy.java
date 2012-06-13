package pl.piekarczyk.Asteroids2D.View.Viewable;

import javax.swing.*;
import java.awt.*;
import java.lang.Math;

public class Enemy extends ViewObject {
  public Enemy(double nx, double ny, double nRot) {
    super(nx, ny, nRot);
    height = 20;
    width = 20;
    ImageIcon ii = new ImageIcon(this.getClass().getResource(modelFile));
    image = ii.getImage();
  }
  public Image getImage() {
    return image; 
  }
  private final static String modelFile = "data/pic/enemy.jpg";
  private static Image image;
}

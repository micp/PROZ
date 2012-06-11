package pl.piekarczyk.Asteroids2D.View.Viewable;

import javax.swing.*;
import java.awt.*;

public abstract class ViewObject {
  public ViewObject(double nx, double ny, double nRot) {
    x = nx;
    y = ny;
    rot = nRot;
  }
  public int getX() { return (int)Math.round(x); }
  public int getY() { return (int)Math.round(y); }
  public int getRot() { return (int)Math.round(rot); }
  public abstract Image getImage();
  protected double x, y;
  protected double rot;
}

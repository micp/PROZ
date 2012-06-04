package pl.piekarczyk.Asteroids2D.View.ViewableObject;

import javax.swing.*;

public abstract class ViewPhysicalObject extends ViewObject {
  public ViewPhysicalObject(int nx, int ny, int nRot) {
    x = nx;
    y = ny;
    rot = nRot;
  }
  protected int x, y;
  protected int rot;
}

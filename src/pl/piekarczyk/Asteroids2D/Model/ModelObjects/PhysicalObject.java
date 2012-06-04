package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import java.lang.Math;

import pl.piekarczyk.Asteroids2D.Model.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.Vectors.MomentumVector;

public abstract class PhysicalObject extends ModelObject {
  protected int x, y, height, width, rot;
  protected Types.PhysicalObjectTypes type;
  protected MomentumVector moment;
  protected void move() {
    int dx = (int) Math.sin(moment.getDirection()) * moment.getMagnitude();
    int dy = (int) Math.sin(moment.getDirection()) * moment.getMagnitude();
    x += dx;
    y += dy;
    rot = moment.getDirection();
  }
  //@OPT less constricting constructor?
  public PhysicalObject(int nx, int ny) { 
    x = nx;
    y = ny;
    removable = false;
    //@OPT chhange params
    moment = new MomentumVector(0, 0);
  }
  public abstract void collide(Types.PhysicalObjectTypes collideWith);
  public int getX() { return x; }
  public int getY() { return y; }
  public int getHeight() { return height; }
  public int getWidth() { return width; }
  public int getRot() { return rot; }
  public Types.PhysicalObjectTypes getType() { return type; }
  public void setXY(int nx, int ny) { x = nx; y = ny; }
}

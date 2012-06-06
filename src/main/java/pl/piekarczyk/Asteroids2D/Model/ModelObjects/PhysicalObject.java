package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import java.lang.Math;

import pl.piekarczyk.Asteroids2D.Model.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.Vectors.MomentumVector;

public abstract class PhysicalObject extends ModelObject {
  protected int x, y, height, width, rot;
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
  public PhysicalObject(PhysicalObject a) {
    super(/*(ModelObject)*/ a);
    this.x = a.getX();
    this.y = a.getY();
    this.height = a.getHeight();
    this.width = a.getWidth();
    this.moment = new MomentumVector(a.getMomentumVector());
  }
  public abstract void collide(Types.ObjectTypes collideWith);
  public int getX() { return x; }
  public int getY() { return y; }
  public int getHeight() { return height; }
  public int getWidth() { return width; }
  public int getRot() { return rot; }
  //@OPT not modifiable
  public MomentumVector getMomentumVector() { return moment; }
  public void setXY(int nx, int ny) { x = nx; y = ny; }
  public void setRot(int nRot) { rot = nRot; }
}

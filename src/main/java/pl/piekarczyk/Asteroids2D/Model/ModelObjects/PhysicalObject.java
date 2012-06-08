package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import java.lang.Math;

import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

public abstract class PhysicalObject extends ModelObject {
  protected int x, y, height, width, rot;
  protected StaticGameVector moment;
  protected void move() {
    int dx = (int) Math.sin(moment.getDirection()) * moment.getMagnitude();
    int dy = (int) Math.sin(moment.getDirection()) * moment.getMagnitude();
    x += dx;
    y += dy;
    rot = moment.getDirection();
  }
  //@OPT less constricting constructor?
  public PhysicalObject(int nx, int ny, AsteroidsModel thisGame) { 
    super(thisGame);
    x = nx;
    y = ny;
    removable = false;
    //@OPT chhange params
    moment = new StaticGameVector(0, 0);
  }
  public PhysicalObject(PhysicalObject a) {
    super(/*(ModelObject)*/ a);
    this.x = a.getX();
    this.y = a.getY();
    this.height = a.getHeight();
    this.width = a.getWidth();
    this.moment = new StaticGameVector(a.getMomentum());
  }
  public abstract void collide(Types.ObjectTypes collideWith);
  public int getX() { return x; }
  public int getY() { return y; }
  public int getHeight() { return height; }
  public int getWidth() { return width; }
  public int getRot() { return rot; }
  //@OPT not modifiable
  public StaticGameVector getMomentum() { return moment; }
  public void setXY(int nx, int ny) { x = nx; y = ny; }
  public void setRot(int nRot) { rot = nRot; }
}

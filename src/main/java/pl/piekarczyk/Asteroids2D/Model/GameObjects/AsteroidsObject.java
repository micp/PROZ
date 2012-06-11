package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import java.lang.Math;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

public abstract class AsteroidsObject implements GameObject {
  //@OPT less constricting constructor?
  public AsteroidsObject(double nx, double ny, GameModel thisGame) { 
    game = thisGame;
    coordLimit = game.getFieldSize();
    removable = false;
    this.setX(nx);
    this.setY(ny);
    removable = false;
    //@OPT chhange params
    moment = new StaticAsteroidsVector(10, 0);
  }
  public AsteroidsObject(GameObject a) {
    this.removable = a.isRemovable();
    this.type = a.getType();
    this.x = a.getX();
    this.y = a.getY();
    this.rot = a.getRot();
    this.height = a.getHeight();
    this.width = a.getWidth();
    this.moment = 
      new StaticAsteroidsVector(a.getMagnitude(), a.getDirection());
  }
  public double getX() { return x; }
  public double getY() { return y; }
  public double getRot() { return rot; }
  public double getMagnitude() { return moment.getMagnitude(); }
  public double getDirection() { return moment.getDirection(); }
  public int getHeight() { return height; }
  public int getWidth() { return width; }
  public boolean isRemovable() { return removable; }
  public Types.ObjectTypes getType() { return type; }
  public void setXY(double nx, double ny) { this.setX(nx); this.setY(ny); }
  public void setX(double nx) { 
    if(nx < -coordLimit)
      x = nx + 2*coordLimit;
    else if(nx > coordLimit)
      x = nx - 2*coordLimit;
    else
      x = nx;
  }
  public void setY(double ny) { 
    if(ny < -coordLimit)
      y = ny + 2*coordLimit;
    else if(ny > coordLimit)
      y = ny - 2*coordLimit;
    else
      y = ny;
  }
  public void setRot(int nRot) { rot = nRot; }
  public void setMagnitude(double x) { moment.setMagnitude(x); }
  public void setDirection(double x) { moment.setDirection(x); }
  public void setHeight(int nh) { height = nh; }
  public void setWidth(int nw) { width = nw; }
  public void setRemovable(boolean nr) { removable = nr; }
  protected void move() {
    double dx = Math.sin(
	Math.toRadians(moment.getDirection()) ) * moment.getMagnitude();
    double dy = Math.cos(
	Math.toRadians(moment.getDirection()) ) * moment.getMagnitude();
    this.setX(x + dx);
    this.setY(y + dy);
    rot = moment.getDirection();
  }

  protected double x, y, rot;
  protected int height, width;
  protected int coordLimit;
  protected StaticAsteroidsVector moment;
  protected Types.ObjectTypes type;
  protected boolean removable;
  protected GameModel game;
  //@OPT
  //public abstract void addObservator();
  //@OPT
  //private int id;
}

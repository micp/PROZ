package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import java.lang.Math;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameControllers.*;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

public abstract class AsteroidsObject implements GameObject {
  public AsteroidsObject(double nx, double ny, GameModel thisGame) { 
    game = thisGame;
    coordLimit = game.getFieldSize();
    removable = false;
    this.setX(nx);
    this.setY(ny);
    removable = false;
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
  public double getX() {
    return x; 
  }
  public double getY() {
    return y; 
  }
  public double getMiddleX() {
    return getX() + getWidth() / 2; 
  }
  public double getMiddleY() {
    return getY() + getHeight() / 2; 
  }
  public double getRot() {
    return rot; 
  }
  public double getMagnitude() {
    return moment.getMagnitude(); 
  }
  public double getDirection() {
    return moment.getDirection(); 
  }
  public int getHeight() {
    return height; 
  }
  public int getWidth() {
    return width; 
  }
  public boolean isRemovable() {
    return removable; 
  }
  public Types.ObjectTypes getType() {
    return type; 
  }
  public void setXY(double nx, double ny) { 
    this.setX(nx); 
    this.setY(ny);
  }
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
  public void setRot(double nRot) {
    rot = nRot%360; 
    if(rot < 0) rot += 360;
  }
  public void setMagnitude(double x) {
    moment.setMagnitude(x); 
  }
  public void setDirection(double x) { 
    moment.setDirection(x);
  }
  public void setHeight(int nh) {
    height = nh; 
  }
  public void setWidth(int nw) {
    width = nw; 
  }
  public void setRemovable(boolean nr) {
    removable = nr; 
  }
  public void setController(GameProducerController gc) {
    myController = gc; 
  }
  protected void notifyCreated() {
    if(myController == null) return;
    myController.notifyCreated();
  }
  protected void notifyDestroyed() {
    if(myController == null) return;
    myController.notifyDestroyed();
  }
  protected void move() {
    double dx = Math.sin(
	Math.toRadians(moment.getDirection()) ) * moment.getMagnitude();
    double dy = Math.cos(
	Math.toRadians(moment.getDirection()) ) * moment.getMagnitude();
    setX(x + dx);
    setY(y + dy);
  }

  protected Types.ObjectTypes type;
  protected double x, y, rot;
  protected int height, width;
  protected StaticAsteroidsVector moment;
  protected boolean removable;
  protected GameModel game;
  protected int coordLimit;
  protected GameProducerController myController;
}

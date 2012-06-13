package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import java.lang.Math;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameControllers.*;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

/**
 * Implements much of the GameObject interface. Most objects should extend
 * this, the methods here are pretty generic.
 */
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
  /**
   * Sets the controller that considers itself the creator of this object.
   */
  public void setController(GameProducerController gc) {
    myController = gc; 
  }
  /**
   * Notifies the related controller that this object has been created. Used
   * in case that the object has been created outside of the controller.
   */
  protected void notifyCreated() {
    if(myController == null) return;
    myController.notifyCreated();
  }
  /** 
   * Notifies the related controller that this object has been destroyed.
   */
  protected void notifyDestroyed() {
    if(myController == null) return;
    myController.notifyDestroyed();
  }
  /**
   * Moves the object. The direction and distance are calculated based on the
   * current moments vector parameters.
   */
  protected void move() {
    double dx = Math.sin(
	Math.toRadians(moment.getDirection()) ) * moment.getMagnitude();
    double dy = Math.cos(
	Math.toRadians(moment.getDirection()) ) * moment.getMagnitude();
    setX(x + dx);
    setY(y + dy);
  }

  /**
   * The type of the object that extends this.
   */
  protected Types.ObjectTypes type;
  /**
   * The horizontal coordinate of the object.
   */
  protected double x;
  /**
   * The vertical coordinate of the object.
   */
  protected double y;
  /**
   * The rotation of this object, in degrees.
   */
  protected double rot;
  /**
   * The vertical coordinate of the object.
   */
  protected int height;
  /**
   * The horizontal coordinate of the object.
   */
  protected int width;
  /**
   * The moments vector of the object.
   */
  protected StaticAsteroidsVector moment;
  /**
   * Removable state of the object. True if object considers itself destroyed.
   */
  protected boolean removable;
  /**
   * The game model this object is connected to.
   */
  protected GameModel game;
  /**
   * Coordinate limit of this object. Used to limit the coordinates this object
   * can have. Derived from the connected game models field size.
   */
  protected int coordLimit;
  /**
   * The game controller that considers itself the creator of this object.
   */
  protected GameProducerController myController;
}

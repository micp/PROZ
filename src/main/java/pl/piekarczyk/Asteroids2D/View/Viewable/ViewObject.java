package pl.piekarczyk.Asteroids2D.View.Viewable;

import javax.swing.*;
import java.awt.*;

/**
 * A base class of all the viewable objects. Provides basic methods of 
 * retrieving information from view objects.
 */
public abstract class ViewObject {
  /**
   * Constructs a view object with specified parameters.
   */
  public ViewObject(double nx, double ny, double nRot) {
    x = nx;
    y = ny;
    rot = nRot;
  }
  /**
   * Returns horizontal coordinate of the object.
   */
  public int getX() {
    return (int)Math.round(x); 
  }
  /**
   * Returns vertical coordinate of the object.
   */
  public int getY() {
    return (int)Math.round(y); 
  }
  /**
   * Returns rotation of the object, in degrees.
   */
  public int getRot() {
    return (int)Math.round(rot); 
  }
  /**
   * Returns vertical size of the object.
   */
  public int getHeight() {
    return height; 
  }
  /**
   * Returns horizontal size of the object.
   */
  public int getWidth() {
    return width; 
  }
  /**
   * Returns the image loaded by the object.
   */
  public abstract Image getImage();

  protected double x, y;
  protected double rot;
  protected int height, width;
}

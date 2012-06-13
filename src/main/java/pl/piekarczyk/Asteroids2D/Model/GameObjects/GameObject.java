package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameControllers.*;

/**
 * Guarantees a bare minimum functionality of game objects. Provides various
 * methods to execute basic management of game objects.
 */
public interface GameObject {
  /**
   * Returns the horizontal coordinate of the object
   */
  double getX();
  /**
   * Returns the vertical coordinate of the object.
   */
  double getY();
  /**
   * Returns the horizontal coordinate of the middle of the object.
   */
  double getMiddleX();
  /**
   * Returns the vertical coordinate of the middle of the object.
   */
  double getMiddleY();
  /**
   * Returns the current rotation of the object. Rotation is between 0 and 
   * 360. May differ from direction, this only specifies the direction
   * this object is facing.
   * @return The rotation of the object, in degrees.
   */
  double getRot();
  /**
   * Returns the magnitude of the momentum vector associated with the model.
   */
  double getMagnitude();
  /**
   * Returns the direction of the momentum vector associated with the model.
   * Direction is between 0 and 360. May differ from rotation, this only 
   * determines the movement direction.
   * @return The direction of the momentum vector, in degrees.
   */
  double getDirection();
  /**
   * Returns the vertical size of the object.
   */
  int getHeight();
  /**
   * Returns the horizontal size of the object.
   */
  int getWidth();
  /**
   * Returns the type of the object.
   * @return The {@link pl.piekarczyk.Asteroids2D.Common.Types.ObjectTypes}
   * type of the object.
   */
  Types.ObjectTypes getType();
  /**
   * Returns the removable state of the object. If true then this object
   * considers self destroyed.
   */
  boolean isRemovable();
  /**
   * Sets the horizontal coordinate of the object.
   */
  void setX(double nx);
  /**
   * Sets the vertical coordinate of the object.
   */
  void setY(double ny);
  /**
   * Sets both the horizontal and the vertical coordinates of the object.
   * @param nx The requested horizontal coordinate.
   * @param ny The requested vertical coordinate.
   */
  void setXY(double nx, double ny);
  /**
   * Sets the rotation of the object.
   * @param nRot The requested rotation of the object, in degrees.
   */
  void setRot(double nRot);
  /**
   * Sets the magnitude of the momentum vector associated with the model.
   */
  void setMagnitude(double x);
  /**
   * Sets the direction of the momentum vector associated with the model.
   */
  void setDirection(double x);
  /**
   * Sets the vertical size of the object.
   */
  void setHeight(int nh);
  /**
   * Sets the horizontal size of the object.
   */
  void setWidth(int nw);
  /**
   * Change the removable state of the object.
   */
  void setRemovable(boolean nr);
  /**
   * Notifies the object that it has collided.
   * @param collideWith The type of object that has collided with the object
   * this method is called for.
   */
  void collide(Types.ObjectTypes collideWith);
  /**
   * Invoked every loop of the game. All object related logic should be kept
   * here.
   */
  void step();
  /**
   * Provides a deep copy of the object
   * @return A reference to a deep copy of the object.
   */
  GameObject copy();
}

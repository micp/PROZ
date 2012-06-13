package pl.piekarczyk.Asteroids2D.Model.Vectors;

import pl.piekarczyk.Asteroids2D.Common.Types;

/**
 * Provides basic functionality of DynamicGameVectors. DynamicGameVectors 
 * should usually extend this class.
 */
public abstract class DynamicAsteroidsVector implements DynamicGameVector {
  /**
   * Constructs a vector with the specified parameters. Makes sure that
   * the values are valid by reversing the direction if the speedBase
   * provided is negative, limiting the newDirection to 360 and handling
   * negative values.
   * @param newSpeedBase Requested speed base of the new vector. If negative
   * the vector will be reversed.
   * @param newDirection Requested direction of the new vector, in degrees.
   * Negative and out of bound values are handled properly.
   */
  public DynamicAsteroidsVector(double newSpeedBase, double newDirection) {
    speedBase = newSpeedBase;
    direction = newDirection%360;
    if(direction < 0) direction += 360;
    if(speedBase < 0) {
      direction = (direction+180)%360;
      speedBase*=-1;
    }
  }
  /**
   * Copies the provided vectors speedBase and direction.
   * @param a The vector to be copied.
   */
  public DynamicAsteroidsVector(DynamicGameVector a) {
    speedBase = a.getSpeedBase();
    direction = a.getDirection();
  }
  /**
   * Sets the vector speedBase. If negative value requested the vector is
   * reversed.
   */
  public void setSpeedBase(double x) {
    if(x < 0) {
      direction = (direction+180)%360;
      speedBase = -1 * x;
    }
    else speedBase = x;
  }
  /**
   * Sets the vector direction, in degrees. Out of bounds values are handled
   * properly.
   */
  public void setDirection(double x) { 
    direction = x%360;
    if(x < 0) direction += 360;
  }
  public double getMagnitude() {
    return magnitude; 
  }
  public double getDirection() {
    return direction; 
  }
  public double getSpeedBase() {
    return speedBase; 
  }

  /**
   * Holds the latest vector magnitude, may not yet reflect speedBase change.
   */
  protected double magnitude;
  /**
   * Holds the vector direction, in degrees.
   */
  protected double direction;
  /**
   * Used to calculate the vector magnitude.
   */
  protected double speedBase;
}

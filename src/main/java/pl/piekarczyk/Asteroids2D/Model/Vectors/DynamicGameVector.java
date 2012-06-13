package pl.piekarczyk.Asteroids2D.Model.Vectors;

import pl.piekarczyk.Asteroids2D.Common.Types;

/**
 * Vector that calculates it's magnitude in elaborate manner. Provides methods
 * to change it's direction and indirectly influence the magnitude.
 */
public interface DynamicGameVector extends GameVector {
  /**
   * Returns a value that is a base variable of magnitude calculation.
   */
  double getSpeedBase();
  /**
   * Sets the vector direction, in degrees.
   * @param newDirection The requested vector direction, in degrees.
   */
  void setDirection(double newDirection);
  /**
   * Sets the value that is a base variable of magnitude calculation.
   */
  void setSpeedBase(double newSpeedBase);
  /**
   * Attempts to accelerate (increase the magnitude) of the vector. Usually
   * increases the speedBase.
   */
  void accelerate();
  /**
   * Attempts to decelerate (decrease the magnitude) of the vector. Usually
   * decreases the speedBase.
   */
  void decelerate();
}

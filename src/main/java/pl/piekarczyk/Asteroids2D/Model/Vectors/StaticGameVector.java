package pl.piekarczyk.Asteroids2D.Model.Vectors;

/**
 * A more modifiable version of GameVector.
 */
public interface StaticGameVector extends GameVector {
  /**
   * Sets the magnitude of the vector.
   */
  void setMagnitude(double newMagnitude);
  /**
   * Sets the direction of the vector, in degrees.
   * @param newDirection Requested direction of the vector, in degrees.
   */
  void setDirection(double newDirection);
}

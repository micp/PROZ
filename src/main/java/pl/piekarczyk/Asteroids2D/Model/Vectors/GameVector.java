package pl.piekarczyk.Asteroids2D.Model.Vectors;

/**
 * Guarantees basic vector functionality.
 */
public interface GameVector {
  /**
   * Returns the magnitude of the vector.
   */
  double getMagnitude();
  /**
   * Returns the direction of the vector, in degrees.
   * @return The direction of the vector, in degrees.
   */
  double getDirection();
}

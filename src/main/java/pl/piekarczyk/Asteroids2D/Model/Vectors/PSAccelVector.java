package pl.piekarczyk.Asteroids2D.Model.Vectors;

import pl.piekarczyk.Asteroids2D.Common.Types;

/**
 * Handles player ship acceleration.
 */
public class PSAccelVector extends DynamicAsteroidsVector {
  public PSAccelVector(double newSpeedBase, double newDirection) {
    super(newSpeedBase, newDirection);
  }
  public PSAccelVector(DynamicGameVector a) {
    super(a);
  }
  /**
   * Sets the magnitude to speedBase.
   */
  public void accelerate() { 
    magnitude = speedBase;
  }
  /**
   * Sets the magnitude to zero.
   */
  public void decelerate() {
    magnitude = 0;
  }
}

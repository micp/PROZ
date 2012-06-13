package pl.piekarczyk.Asteroids2D.Model.Vectors;

import pl.piekarczyk.Asteroids2D.Common.Types;

public class PSAccelVector extends DynamicAsteroidsVector {
  public PSAccelVector(double newSpeedBase, double newDirection) {
    super(newSpeedBase, newDirection);
  }
  public PSAccelVector(DynamicGameVector a) {
    super(a);
  }
  public void accelerate() { 
    magnitude = 5;
  }
  public void decelerate() {
    magnitude = 0;
  }
}

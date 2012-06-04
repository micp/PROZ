package pl.piekarczyk.Asteroids2D.Model.Vectors;

import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public abstract class DynamicGameVector extends StaticGameVector {
  public DynamicGameVector(int newMagnitude, int newDirection) {
    super(newMagnitude, newDirection);
  }
  public abstract void accelerate();
  public abstract void decelerate();
  public abstract void rotate(StaticGameVector v, Types.Keys key);
}

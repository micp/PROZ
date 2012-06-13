package pl.piekarczyk.Asteroids2D.Model.Vectors;

import pl.piekarczyk.Asteroids2D.Common.Types;

public interface DynamicGameVector extends GameVector {
  double getSpeedBase();
  void setDirection(double newDirection);
  void setSpeedBase(double newSpeedBase);
  void accelerate();
  void decelerate();
}

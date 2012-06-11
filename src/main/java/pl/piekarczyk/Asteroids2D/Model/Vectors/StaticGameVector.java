package pl.piekarczyk.Asteroids2D.Model.Vectors;

public interface StaticGameVector extends GameVector {
  void setMagnitude(double newMagnitude);
  void setDirection(double newDirection);
}

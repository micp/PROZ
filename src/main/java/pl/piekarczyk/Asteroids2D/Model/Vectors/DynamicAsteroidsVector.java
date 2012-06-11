package pl.piekarczyk.Asteroids2D.Model.Vectors;

import pl.piekarczyk.Asteroids2D.Common.Types;

public abstract class DynamicAsteroidsVector implements DynamicGameVector {
  public DynamicAsteroidsVector(double speedBase, double newDirection) {
    speedBase = speedBase;
    direction = newDirection%360;
    if(direction < 0) direction += 360;
    if(speedBase < 0) {
      direction = (direction+180)%360;
      speedBase*=-1;
    }
  }
  public DynamicAsteroidsVector(DynamicGameVector a) {
    speedBase = a.getSpeedBase();
    direction = a.getDirection();
  }
  public void setSpeedBase(double x) {
    if(x < 0) {
      direction = (direction+180)%360;
      speedBase = -1 * x;
    }
    else speedBase = x;
  }
  public void setDirection(double x) { 
    direction = x%360;
    if(x < 0) direction += 360;
  }
  public double getMagnitude() { return magnitude; }
  public double getDirection() { return direction; }
  public double getSpeedBase() { return speedBase; }
  public abstract void accelerate();
  public abstract void decelerate();
  public abstract void rotate(StaticGameVector v, Types.Keys key);
  protected double magnitude, direction, speedBase;
}

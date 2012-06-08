package pl.piekarczyk.Asteroids2D.Model.Vectors;

import pl.piekarczyk.Asteroids2D.Model.Common.*;
import java.lang.Math;

public class PSAccelVector extends DynamicGameVector {
  private int speedBase;
  public PSAccelVector(int newMagnitude, int newDirection) {
    super(newMagnitude, newDirection);
    speedBase = 0; 
  }
  public PSAccelVector(PSAccelVector a) {
    super(a);
    speedBase = a.getSpeedBase();
  }
  public void accelerate() { 
    magnitude = (int) Math.log1p( (double)++speedBase );
  }
  public void decelerate() {
    magnitude = (int) Math.log1p( (double)--speedBase );
  }
  public void rotate(StaticGameVector v, Types.Keys key) {
    if(key == Types.Keys.LEFT) {
      direction = v.getDirection() - 90;
    }
    if(key == Types.Keys.RIGHT) {
      direction = v.getDirection() + 90;
    }
  }
  public void rotate(StaticGameVector v) {
    direction = v.getDirection();
  }
  public int getSpeedBase() { return speedBase; }
}

package pl.piekarczyk.Asteroids2D.Model.Vectors;

import java.lang.Math;

public abstract class StaticGameVector {
  protected int magnitude, direction;
  public StaticGameVector(int newMagnitude, int newDirection) {
    magnitude = newMagnitude;
    direction = newDirection;
  }
  public StaticGameVector(StaticGameVector a) {
    magnitude = a.getMagnitude();
    direction = a.getDirection();
  }
  public int getMagnitude() { return magnitude; }
  public int getDirection() { return direction; }
  public void setMagnitude(int x) { /*@OPT check for < 0*/magnitude = x; }
  public void setDirection(int x) { direction = x%360; }
  public void sumWith(StaticGameVector b) {
    int x1 = (int) Math.sin(direction) * magnitude;
    int y1 = (int) Math.cos(direction) * magnitude;
    int x2 = (int) Math.sin(b.getDirection()) * b.getMagnitude();
    int y2 = (int) Math.cos(b.getDirection()) * b.getMagnitude();
    int newx = x1 + x2;
    int newy = y1 + y2;
    magnitude = (int) Math.atan(newx/newy);
    direction = (int) Math.asin(newy/magnitude);
  }
}

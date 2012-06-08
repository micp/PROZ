package pl.piekarczyk.Asteroids2D.Model.Vectors;

import java.lang.Math;

public /*abstract*/ class StaticGameVector {
  protected int magnitude, direction;
  public StaticGameVector(int newMagnitude, int newDirection) {
    magnitude = newMagnitude;
    direction = Math.abs(newDirection%360);
    if(magnitude < 0) {
      direction = (direction+180)%360;
      magnitude*=-1;
    }
  }
  public StaticGameVector(StaticGameVector a) {
    magnitude = a.getMagnitude();
    direction = a.getDirection();
  }
  public int getMagnitude() { return magnitude; }
  public int getDirection() { return direction; }
  public void setMagnitude(int x) {
    if(x < 0) {
      direction = (direction+180)%360;
      magnitude = -1 * x;
    }
  }
  public void setDirection(int x) { 
    direction = Math.abs(x%360); 
  }
  public void sumWith(StaticGameVector b) {
    int x1 = (int) Math.sin(direction) * magnitude;
    int y1 = (int) Math.cos(direction) * magnitude;
    int x2 = (int) Math.sin(b.getDirection()) * b.getMagnitude();
    int y2 = (int) Math.cos(b.getDirection()) * b.getMagnitude();

    int newx = x1 + x2;
    int newy = y1 + y2;

    int angle;
    if(newx == 0) angle = 0;
    else if(newy == 0) angle = 90;
    else if(newx * newy > 0) angle = (int) Math.atan(newx/newy);
    else angle = (int) Math.atan(newy/newx);

    magnitude = (int) Math.sin(angle) * newx;
    if(newx > 0 && newy > 0) direction = angle;
    else if(newx > 0 && newy < 0) direction = 90 + angle;
    else if(newx < 0 && newy < 0) direction = 180 + angle;
    else if(newx < 0 && newy > 0) direction = 270 + angle;
  }
}

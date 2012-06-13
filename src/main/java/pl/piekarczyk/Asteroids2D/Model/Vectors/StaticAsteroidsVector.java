package pl.piekarczyk.Asteroids2D.Model.Vectors;

import java.lang.Math;

public class StaticAsteroidsVector implements StaticGameVector {
  public StaticAsteroidsVector(double newMagnitude, double newDirection) {
    magnitude = newMagnitude;
    direction = newDirection%360;
    if(direction < 0) direction += 360;
    if(magnitude < 0) {
      direction = (direction+180)%360;
      magnitude*=-1;
    }
  }
  public StaticAsteroidsVector(GameVector a) {
    magnitude = a.getMagnitude();
    direction = a.getDirection();
  }
  public double getMagnitude() {
    return magnitude; 
  }
  public double getDirection() {
    return direction; 
  }
  public void setMagnitude(double x) {
    if(x < 0) {
      direction = (direction+180)%360;
      magnitude = -1 * x;
    }
    else magnitude = x;
  }
  public void setDirection(double x) { 
    direction = x%360;
    if(x < 0) direction += 360;
  }
  public void sumWith(GameVector b) {
    double x1 = Math.sin(
	Math.toRadians(direction) ) * magnitude;
    double y1 = Math.cos(
	Math.toRadians(direction) ) * magnitude;
    double x2 = Math.sin(
	Math.toRadians(b.getDirection()) ) * b.getMagnitude();
    double y2 = Math.cos(
	Math.toRadians(b.getDirection()) ) * b.getMagnitude();

    double newx = x1 + x2;
    double newy = y1 + y2;

    if(newx >= 0) {
      if(newy > 0)
	direction = Math.atan(newx / newy);
      else if(newy < 0)
	direction = Math.atan(newx / newy) + Math.toRadians(180);
    }
    else if(newx <= 0) {
      if(newy < 0) 
	direction = Math.atan(newx / newy) + Math.toRadians(180);
      else if(newy > 0)
	direction = Math.atan(newx / newy);
    }
    else if(Math.rint(newy) == 0) {
      if(newx > 0)
	direction = Math.toRadians(90);
      else if(newx < 0)
	direction = Math.toRadians(270);
    }

    if(Math.abs(Math.sin(direction)) < 0.05F || 
       Math.abs(Math.sin(direction - Math.toRadians(180))) < 0.05F)
      magnitude = Math.abs(newy);
    else
      magnitude = newx / Math.sin(direction);
    direction = Math.toDegrees(direction);

  }
  protected double magnitude, direction;
}

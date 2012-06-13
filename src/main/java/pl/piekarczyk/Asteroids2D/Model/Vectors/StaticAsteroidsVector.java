package pl.piekarczyk.Asteroids2D.Model.Vectors;

import java.lang.Math;

/**
 * A generic static game vector with all methods defined.
 */
public class StaticAsteroidsVector implements StaticGameVector {
  /**
   * Constructs a vector with the specified parameters. Makes sure that
   * the values are valid by reversing the direction if the newMagnitude
   * provided is negative, limiting the newDirection to 360 and handling
   * negative values.
   * @param newMagnitude Requested magnitude of the new vector. If negative
   * the vector will be reversed.
   * @param newDirection Requested direction of the new vector, in degrees.
   * Negative and out of bound values are handled properly.
   */
  public StaticAsteroidsVector(double newMagnitude, double newDirection) {
    magnitude = newMagnitude;
    direction = newDirection%360;
    if(direction < 0) direction += 360;
    if(magnitude < 0) {
      direction = (direction+180)%360;
      magnitude*=-1;
    }
  }
  /**
   * Copies the provided vectors magnitude and direction.
   * @param a The vector to be copied.
   */
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
  /**
   * Sums this vector with vector b and stores the result within this vector.
   * @param b Vector to sum with.
   */
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

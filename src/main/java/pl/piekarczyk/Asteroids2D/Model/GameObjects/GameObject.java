package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Common.Types;

public interface GameObject {
  double getX();
  double getY();
  double getRot();
  double getMagnitude();
  double getDirection();
  int getHeight();
  int getWidth();
  boolean isRemovable();
  Types.ObjectTypes getType();
  void setX(double nx);
  void setY(double ny);
  void setXY(double nx, double ny);
  void setRot(int nRot);
  void setMagnitude(double x);
  void setDirection(double x);
  void setHeight(int nh);
  void setWidth(int nw);
  void setRemovable(boolean nr);
  void collide(Types.ObjectTypes collideWith);
  void step();
  GameObject copy();
}

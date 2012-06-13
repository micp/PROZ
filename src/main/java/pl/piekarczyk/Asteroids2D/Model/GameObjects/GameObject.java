package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameControllers.*;

public interface GameObject {
  double getX();
  double getY();
  double getMiddleX();
  double getMiddleY();
  double getRot();
  double getMagnitude();
  double getDirection();
  int getHeight();
  int getWidth();
  Types.ObjectTypes getType();
  boolean isRemovable();
  void setX(double nx);
  void setY(double ny);
  void setXY(double nx, double ny);
  void setRot(double nRot);
  void setMagnitude(double x);
  void setDirection(double x);
  void setHeight(int nh);
  void setWidth(int nw);
  void setRemovable(boolean nr);
  void collide(Types.ObjectTypes collideWith);
  void step();
  GameObject copy();
}

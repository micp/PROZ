package pl.piekarczyk.Asteroids2D.Model.Common;

import java.util.EnumMap;
import java.lang.Integer;

public class Types {
  public static enum Keys { UP, LEFT, RIGHT, SPACE, Q, P, _SIZE}
  //public static EnumMap<Keys, Integer> KeyMap =
  //  new EnumMap<Keys, Integer>(new Class(new Integer()));
  public static enum PhysicalObjectTypes { SHIP, ASTEROID, ENEMY }
}
package pl.piekarczyk.Asteroids2D.Model.Common;

import java.util.EnumMap;
import java.lang.Integer;

public class Types {
  public static enum Keys { UP, LEFT, RIGHT, SPACE, Q, P, _SIZE}
//  public static EnumMap<Keys, Integer> KeyMap =
//    new EnumMap<Keys, Integer>(Keys.class);
  public static enum ObjectTypes { SHIP, ASTEROID, ENEMY, PAUSE, QUIT }
}

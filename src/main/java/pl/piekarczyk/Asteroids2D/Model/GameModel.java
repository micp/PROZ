package pl.piekarczyk.Asteroids2D.Model;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

public interface GameModel {
  void setKbdState(boolean[] newKbdState);
  boolean getKeyState(Types.Keys k);
  void addObserver(GameObserver obs);
  void runGame();
  void stopGame();
  void decLives();
  void addScore(int sc);
  int getLives();
  int getScore();
  int getFieldSize();
  LinkedList<GameObject> getList();
  LinkedList<GameObject> getFutureList();
  boolean isOver();
  boolean isColliding(GameObject obj);
}

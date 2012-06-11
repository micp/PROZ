package pl.piekarczyk.Asteroids2D.Model;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

public interface GameState {
  ListIterator<GameObject> getObjectIterator();
  int getScore();
  int getLives();
  int getFieldSize();
  boolean isPaused();
  boolean isOver();
  boolean isWaiting();
}

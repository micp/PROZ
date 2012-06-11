package pl.piekarczyk.Asteroids2D.Model;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;
//import pl.piekarczyk.Asteroids2D.Model.GameModel;

public class AsteroidsState implements GameState {
  public AsteroidsState(GameModel model) {
    lives = model.getLives();
    score = model.getScore();
    fieldSize = model.getFieldSize();
    paused = model.isPaused();
    over = model.isOver();
    waiting = model.isWaiting();

    objectList = new LinkedList<GameObject>();
    ListIterator<GameObject> it = model.getList().listIterator();
    while(it.hasNext())
      objectList.add(it.next().copy());
  }
  public int getScore() { return score; }
  public int getLives() { return lives; }
  public int getFieldSize() { return fieldSize; }
  public ListIterator<GameObject> getObjectIterator() {
    return objectList.listIterator();
  }
  public boolean isPaused() { return paused; }
  public boolean isOver() { return over; }
  public boolean isWaiting() { return waiting; }
  private int lives, score;
  private int fieldSize;
  private boolean paused, over, waiting;
  private LinkedList<GameObject> objectList;
}

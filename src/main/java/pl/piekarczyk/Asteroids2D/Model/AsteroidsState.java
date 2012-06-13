package pl.piekarczyk.Asteroids2D.Model;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

public class AsteroidsState implements GameState {
  public AsteroidsState(GameModel model) {
    lives = model.getLives();
    score = model.getScore();
    fieldSize = model.getFieldSize();
    over = model.isOver();

    objectList = new LinkedList<GameObject>();
    ListIterator<GameObject> it = model.getList().listIterator();
    while(it.hasNext())
      objectList.add(it.next().copy());
  }
  public int getScore() {
    return score; 
  }
  public int getLives() {
    return lives; 
  }
  public int getFieldSize() {
    return fieldSize; 
  }
  public ListIterator<GameObject> getObjectIterator() {
    return objectList.listIterator();
  }
  public boolean isOver() {
    return over; 
  }

  private int lives, score;
  private int fieldSize;
  private boolean over;
  private LinkedList<GameObject> objectList;
}

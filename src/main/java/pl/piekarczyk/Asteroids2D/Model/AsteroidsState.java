package pl.piekarczyk.Asteroids2D.Model;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

/**
 * Used to hold a state of the game. Constructed by the model to provide
 * information to the observers.
 */
public class AsteroidsState implements GameState {
  /**
   * Copies all necessary information from the model. The constructed object
   * will hold complete information set without any need for further 
   * interaction.
   */
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

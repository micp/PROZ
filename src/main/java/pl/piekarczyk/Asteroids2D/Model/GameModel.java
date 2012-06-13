package pl.piekarczyk.Asteroids2D.Model;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

/**
 * An interface used both in MVP pattern and to communicate within the model.
 * Provides methods to inject keyboard state into the model and to register
 * observer with the model. Also guarantees implementations of methods 
 * required by various model objects to communicate with and control the model.
 */
public interface GameModel {
  /**
   * Injects a new keyboard state into the model. Proper implementation should
   * be thread safe.
   * @param newKbdState An array specifying newest keyboard state. Should be of
   * size {@link pl.piekarczyk.Asteroids2D.Common.Types.Keys#_SIZE}.ordinal(). 
   * Also, the order of keys should be as specified in the Keys enum.
   */
  void setKbdState(boolean[] newKbdState);
  /**
   * Returns the state of selected key. True if key is pressed, false 
   * otherwise.
   * @param k The key which state is to be investigated.
   * @return The state of the selected key.
   */
  boolean getKeyState(Types.Keys k);
  /**
   * Registers an observer with the game model. The observer will be notified
   * of changes within the model at the models discretion.
   * @param obs An observer wishing to register with the model.
   */
  void addObserver(GameObserver obs);
  /**
   * Starts the game loop. Usually forks into a new thread which will run the 
   * game loop until the game is over.
   */
  void runGame();
  /**
   * Notifies the game that is should stop. May not have immediate effect.
   */
  void stopGame();
  /**
   * Decrements the life count in the model. Used within the game model.
   */
  void decLives();
  /**
   * Increase game score by specified amount.
   * @param sc Amount of points by which to increase the score.
   */
  void addScore(int sc);
  /**
   * Get the amount of lives left. Usually, when this reaches zero the game is 
   * over.
   * @return Lives left.
   */
  int getLives();
  /**
   * Get the current score.
   * @return Current score.
   */
  int getScore();
  /**
   * Returns half sides length of a square that represents a logical game 
   * field.
   * @return Half sides length of the game field.
   */
  int getFieldSize();
  /**
   * Returns a list of game objects registered with the game. Meant only as 
   * a way to see which objects are present. Not intended to be modified, see:
   * {@link #getFutureList()} if there's a need to add any object to the game.
   * @return A linked list holding game objects.
   */
  LinkedList<GameObject> getList();
  /**
   * Returns a list intended to be modified by model objects. This list is 
   * meant to be merged with the main list when it is safe to do so. Intended
   * to provide a safe way to add objects to the game without introducing
   * iterator related bugs.
   * @return A linked list temporarily holding new objects.
   */
  LinkedList<GameObject> getFutureList();
  /** 
   * True if game is finished.
   * @return True if finished, false otherwise.
   */
  boolean isOver();
  /**
   * Checks if specified objects collides with anything. The specified object
   * doesn't need to be present on the game field. This function is useful
   * to avoid collisions of newly created objects.
   * @return Whether or not the object is/would collide.
   */
  boolean isColliding(GameObject obj);
}

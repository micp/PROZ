package pl.piekarczyk.Asteroids2D.Model;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

/**
 * Represents a game state. Contains all relevant information. Used to 
 * communicate between game model and any observers.
 */
public interface GameState {
  /**
   * Returns an iterator over the objects stored by this game state. Used to
   * read information about objects stored within.
   * @return A ListIterator over stored objects.
   */
  ListIterator<GameObject> getObjectIterator();
  /**
   * Returns the score stored within the game state.
   * @return Stored score.
   */
  int getScore();
  /**
   * Returns the life amount stored withing the game state.
   * @return Life amount.
   */
  int getLives();
  /**
   * Returns {@link pl.piekarczyk.Asteroids2D.Model.GameModel#getFieldSize()}
   * of the game that generated the game state. This is only half sides length,
   * see the getFieldSize() method for more information.
   * @return Half sides length of field size.
   */
  int getFieldSize();
  /**
   * Checks if the game is over
   * @return True if over, false otherwise.
   */
  boolean isOver();
}

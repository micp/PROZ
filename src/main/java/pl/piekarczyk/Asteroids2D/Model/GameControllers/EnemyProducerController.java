package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

/**
 * Places an enemy ship on the field. Supports only one concurrent enemy ship.
 * Uses Random() to decide if a ship should be placed, if one is not active
 * yet.
 */
public class EnemyProducerController extends ProducerControllerHelper {
  public EnemyProducerController(GameModel thisGame) {
    super(thisGame);
  }
  /**
   * Decides whether to spawn a new enemy. If one is already active does 
   * nothing. Otherwise takes a Random() roll to see if an enemy should
   * be spawned. The position and direction are chosen at random with
   * no guarantees made.
   */
  public void manage() {
    if(count > 0) return;

    Random rnd = new Random();
    if(rnd.nextInt(1000) > 990) {
      Enemy e = new Enemy(game);
      e.setController(this);
      e.setDirection(90);
      addRandom(e);
      incCount();
    }
  }
  /**
   * Empty, does nothing.
   */
  public void close() {}
  /**
   * Empty, does nothing.
   */
  public void notifyCreated() {}
  /**
   * Decrements the variable used to track the amount of ships.
   */
  public void notifyDestroyed() {
    decCount();
  }
  private void incCount() {
    count++;
  }
  private void decCount() {
    count--; 
  }
  private void setCount(int i) { 
    count = i; 
  }
  private int count;
}

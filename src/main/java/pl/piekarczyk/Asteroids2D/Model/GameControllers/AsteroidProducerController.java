package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

/**
 * Responsible for producing asteroid belts. Can produce belts of varying
 * quantity. The spawned asteroids position and direction are chosen randomly
 * with no guarantees made.
 */
public class AsteroidProducerController extends ProducerControllerHelper {
  /**
   * Assumes the default belt size of 4. Calls {@link 
   * #AsteroidProducerController(int, GameModel)} with argument 4.
   * @param thisGame A reference to the GameModel instance that this 
   * controller is to be connected to. Game model should provide 'this' here.
   */
  public AsteroidProducerController(GameModel thisGame) {
    this(4, thisGame);
  }
  /**
   * Accepts a non default field size. This constructor doesn't spawn the 
   * belt. This is done in the first invocation of the {@link #manage()}
   * method. The constructor only links the controller to the provided
   * game model and setups the desired belt size.
   * @param beltSize Desired asteroid belt size.
   * @param thisGame A reference to the GameModel instance that this
   * controller is to be connected to. Game model should provide 'this' here.
   */
  public AsteroidProducerController(int beltSize, GameModel thisGame) {
    super(thisGame);
    asteroidBeltSize = beltSize;
  }
  /**
   * Provides logic for the asteroid belt management. Has to states. If there
   * are any asteroids left, this does nothing. In the other case, this 
   * restores the field size to the number previously specified.
   */
  public void manage() {
    if(count > 0) return;
    while( asteroidBeltSize - count > 0 ) {
      Random rnd = new Random();
      Asteroid na = new Asteroid(game);
      na.setController(this);
      na.setDirection(rnd.nextInt());
      na.setRot(na.getDirection());
      addRandom(na);
      incCount();
    }
  }
  /**
   * Empty, theres no need for any cleanup.
   */
  public void close() {}
  /**
   * Increments the variable tracking the amount of asteroids on the field.
   */
  public void notifyCreated() {
    incCount();
  }
  /**
   * Decrements the variable tracking the amount of asteroids on the field. 
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
  private int asteroidBeltSize;
}

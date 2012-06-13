package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

/**
 * Places a player controlled ship on the field. Permits only one concurrent
 * ship to be present. Guarantees a safe starting spot of the ship.
 */
public class PSProducerController 
extends AsteroidsController implements GameProducerController {
  public PSProducerController(GameModel thisGame) {
    super(thisGame);
  }
  /**
   * Decides if a new ship should be placed. Quits of theres one already active.
   * Quits if the middle of the field is occupied.
   */
  public void manage() {
    if(count > 0) return;
    else {
      PlayerShip ps = new PlayerShip(game);
      if(game.isColliding(ps)) return;
      else {
	ps.setController(this);
	game.getFutureList().add(ps);
	incCount();
      }
    }
  }
  /**
   * Sets ship count to zero.
   */
  public void close() {
    setCount(0);
  }
  /**
   * Does nothing.
   */
  public void notifyCreated() {}
  /**
   * Decrements the variable tracking ship amount.
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

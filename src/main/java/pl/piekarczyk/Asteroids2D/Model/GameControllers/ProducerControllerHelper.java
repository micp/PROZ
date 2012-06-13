package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

/** 
 * Provides an utility for placing random objects on the game field. 
 * Retries until a safe position can be found. Can be dangerous if there are
 * too many objects active on the game field.
 */
public abstract class ProducerControllerHelper 
extends AsteroidsController implements GameProducerController {
  public ProducerControllerHelper(GameModel thisGame) {
    super(thisGame);
  }
  /**
   * Picks a random place to put the object. Will keep retrying until an empty
   * spot can be found. Dangerous if there are too many objects on the field.
   * @param newObject The object which is looking for a safe spot on the field.
   */
  protected void addRandom(GameObject newObject) {
    Random rnd = new Random();
    int fieldSize = game.getFieldSize();
    while(true) {
      int x = rnd.nextInt(2 * fieldSize) - fieldSize;
      int y = rnd.nextInt(2 * fieldSize) - fieldSize;
      newObject.setXY(x, y);
      if(!game.isColliding(newObject)) {
	game.getFutureList().add(newObject);
	break;
      }
    }
  }
}

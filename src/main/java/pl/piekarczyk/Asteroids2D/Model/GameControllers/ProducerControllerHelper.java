package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

public abstract class ProducerControllerHelper extends AsteroidsController {
  public ProducerControllerHelper(GameModel thisGame) {
    super(thisGame);
  }
  protected void addRandom(GameObject newObject) {
    Random rnd = new Random();
    int fieldSize = game.getFieldSize();
    while(true) {
      int x = rnd.nextInt(2 * fieldSize) - fieldSize;
      int y = rnd.nextInt(2 * fieldSize) - fieldSize;
      newObject.setXY(x, y);
      if(!game.isColliding(newObject)) {
	game.getList().add(newObject);
	break;
      }
    }
  }
}

package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;
import pl.piekarczyk.Asteroids2D.Common.Types;

public class PSProducerController extends AsteroidsController {
  public PSProducerController(GameModel thisGame) {
    super(thisGame);
  }
  public void manage() {
    if(PlayerShip.count() > 0) return;
    PlayerShip ps = new PlayerShip(game);
    while(game.isColliding(ps)) {
      ListIterator<GameObject> it = game.getList().listIterator();
      while(it.hasNext())
	it.next().step();
    }
    game.getList().add(ps);
    PlayerShip.incCount();
  }
}

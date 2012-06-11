package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;
import pl.piekarczyk.Asteroids2D.Common.Types;

public class MissileProducerController extends ProducerControllerHelper {
  public MissileProducerController(GameModel thisGame) {
    super(thisGame);
    timer = System.currentTimeMillis();
  }
  public void manage() {
    if(game.getKeyState(Types.Keys.SPACE)) {
      if(System.currentTimeMillis() - timer < 500) return;
      ListIterator<GameObject> it = game.getList().listIterator();
      PlayerShip ps = null;
      while(it.hasNext()) {
	GameObject cur = it.next();
	if(cur.getType() == Types.ObjectTypes.SHIP) {
	  ps = (PlayerShip) cur;
	  break;
	}
      }
      Missile ms = new Missile(ps.getX(), ps.getY(), game);
      ms.setDirection(ps.getRot());
      game.getList().add(ms);
      timer = System.currentTimeMillis();
    }
  }
  private long timer;
}

package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

/**
 * Responds to player request to shoot a missile. Forces a small delay 
 * between each missile. Responds to the space key. Does not limit the 
 * amount of concurrent missiles.
 */
public class MissileProducerController extends ProducerControllerHelper {
  public MissileProducerController(GameModel thisGame) {
    super(thisGame);
    timer = System.currentTimeMillis();
  }
  /**
   * Decides whether to emit a new missile. Responds only to space key.
   * If not enough time passed since last missile does nothing. Otherwise
   * finds the player ship in the list of game objects, quitting if one is
   * not active, and calculates the proper coordinates and direction of the
   * new missile.
   */
  public void manage() {
    if(!game.getKeyState(Types.Keys.SPACE)) return;
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
    if(ps == null) return;

    double x = findX(ps);
    double y = findY(ps);
    Missile ms = new Missile(x, y, game);
    ms.setDirection(ps.getRot());
    game.getFutureList().add(ms);
    timer = System.currentTimeMillis();
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
   * Empty, does nothing.
   */
  public void notifyDestroyed() {}
  private double findX(PlayerShip ps) {
    double ang = Math.toRadians(ps.getRot());
    int h = ps.getHeight();
    double x = ps.getMiddleX();
    return x + Math.sin(ang) * h;
  }
  private double findY(PlayerShip ps) {
    double ang = Math.toRadians(ps.getRot());
    int h = ps.getHeight();
    double y = ps.getMiddleY();
    return y + Math.cos(ang) * h;
  }

  private long timer;
}

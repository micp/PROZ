package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;

/**
 * A tiny asteroid the usual 
 * {@link pl.piekarczyk.Asteroids2D.Model.GameObjects#Asteroid} split into. 
 * Behaves almost the same as Asteroid except these don't split anymore.
 */
public class TinyAsteroid extends AsteroidsObject {
  public TinyAsteroid(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 80;
    width = 80;
    Random rnd = new Random();
    setDirection(rnd.nextInt(360));
    setRot(getDirection());
    type = Types.ObjectTypes.TINYASTEROID;
  }
  public TinyAsteroid(TinyAsteroid a) {
    super(a);
  }
  public TinyAsteroid copy() {
    return new TinyAsteroid(this);
  }
  public void step() {
    move();
  }
  /**
   * Notifies the asteroid of collision. If collideWith is of either Asteroid
   * or TinyAsteroid type does nothing. In other case increases the game models
   * score.
   */
  public void collide(Types.ObjectTypes collideWith) {
    if(collideWith == Types.ObjectTypes.TINYASTEROID ||
	collideWith == Types.ObjectTypes.ASTEROID) {
      return;
    }
    game.addScore(1);
    notifyDestroyed();
    removable = true;
  }
}

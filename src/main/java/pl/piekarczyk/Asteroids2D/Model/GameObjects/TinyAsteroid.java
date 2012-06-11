package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Common.Types;

public class TinyAsteroid extends AsteroidsObject {
  public TinyAsteroid(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 60;
    width = 60;
    Random rnd = new Random();
    this.setDirection(rnd.nextInt(360));
    type = Types.ObjectTypes.TINYASTEROID;
  }
  public TinyAsteroid(TinyAsteroid a) {
    super(a);
  }
  public void collide(Types.ObjectTypes collideWith) {
    //@OPT take care with whom collided
    game.addScore(1);
    Asteroid.decCount();
    removable = true;
  }
  public void step() {
    move();
  }
  public TinyAsteroid copy() {
    return new TinyAsteroid(this);
  }
  static public int count() { return Asteroid.count(); }
  static public void decCount() { Asteroid.decCount(); }
  static public void incCount() { Asteroid.incCount(); }
}

package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Common.Types;

public class Asteroid extends AsteroidsObject {
  public Asteroid(GameModel thisGame) {
    this(0, 0, thisGame);
  }
  public Asteroid(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 60;
    width = 60;
    type = Types.ObjectTypes.ASTEROID;
  }
  public Asteroid(Asteroid a) {
    super(a);
  }
  public void collide(Types.ObjectTypes collideWith) {
    //@OPT take care with whom collided
    for(int i = 0; i < 2; ++i) {
      game.getFutureList().add(new TinyAsteroid(x, y, game));
      count++;
    }
    game.addScore(1);
    count--;
    removable = true;
  }
  public void step() {
    move();
  }
  public Asteroid copy() {
    return new Asteroid(this);
  }
  static public int count() { return count; }
  static public void decCount() { count--; }
  static public void incCount() { count++; }
  private static int count = 0;
}

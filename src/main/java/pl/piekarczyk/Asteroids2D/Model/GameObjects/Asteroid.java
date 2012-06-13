package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;

public class Asteroid extends AsteroidsObject {
  public Asteroid(GameModel thisGame) {
    this(0, 0, thisGame);
  }
  public Asteroid(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 80;
    width = 80;
    type = Types.ObjectTypes.ASTEROID;
  }
  public Asteroid(Asteroid a) {
    super(a);
  }
  public Asteroid copy() {
    return new Asteroid(this);
  }
  public void step() {
    move();
  }
  public void collide(Types.ObjectTypes collideWith) {
    if(collideWith == Types.ObjectTypes.ASTEROID ||
	collideWith == Types.ObjectTypes.TINYASTEROID) {
      return;
    }

    for(int i = 0; i < 2; ++i) {
      TinyAsteroid ta = new TinyAsteroid(x, y, game);
      ta.setController(myController);
      game.getFutureList().add(ta);
      notifyCreated();
    }

    game.addScore(1);
    notifyDestroyed();
    removable = true;
  }
}

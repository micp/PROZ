package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;

/**
 * Represents an asteroid. Can split into smaller asteroids.
 */
public class Asteroid extends AsteroidsObject {
  /**
   * Create a new asteroid in the middle of the field. This just calls 
   * Asteroid(double, double, GameModel) with (0, 0) coordinates.
   * @param thisGame The game that this object is connected to.
   */
  public Asteroid(GameModel thisGame) {
    this(0, 0, thisGame);
  }
  /**
   * Create a new asteroid at the specified coordinates. This doesn't link
   * the asteroid on the games list.
   * @param nx The horizontal coordinate of the asteroid.
   * @param ny The vertical coordinate of the asteroid.
   * @param thisGame The game that this object sis connected to.
   */
  public Asteroid(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 80;
    width = 80;
    type = Types.ObjectTypes.ASTEROID;
  }
  /**
   * A simple copy constructor, calls {@link 
   * pl.piekarczyk.Asteroids2D.Model.GameObjects.AsteroidsObject#AsteroidsObject(GameObject)}
   * @param a The object to be copied.
   */
  public Asteroid(Asteroid a) {
    super(a);
  }
  public Asteroid copy() {
    return new Asteroid(this);
  }
  /**
   * Moves the asteroid.
   */
  public void step() {
    move();
  }
  /**
   * Notifies the asteroid that it has collided. If collideWith is of Asteroid
   * or {@link pl.piekarczyk.Asteroids2D.Model.GameObjects.TinyAsteroid}
   * type does nothing. Otherwise collided asteroids split into TinyAsteroids.
   * TinyAsteroid objects are automatically added to the list of game objects.
   */
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

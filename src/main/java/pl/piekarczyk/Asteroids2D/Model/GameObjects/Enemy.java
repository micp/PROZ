package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;

/**
 * Represents an enemy ship. The ship is capable of randomly changing move
 * direction and shooting missiles in random directions.
 */
public class Enemy extends AsteroidsObject {
  public Enemy(GameModel thisGame) {
    this(0, 0, thisGame);
  }
  public Enemy(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 80;
    width = 80;
    type = Types.ObjectTypes.ENEMY;
  }
  public Enemy(Enemy a) {
    super(a);
  }
  public Enemy copy() {
    return new Enemy(this);
  }
  /**
   * Moves the ship. Does a random check if the movement direction should be 
   * changed. Also does a random check if a missile should be emitted.
   */
  public void step() {
    Random rnd = new Random();

    if(rnd.nextInt(1000) > 990)
      this.setDirection(this.getDirection() + 45 * rnd.nextInt(7));

    if(rnd.nextInt(1000) > 995) {
      Missile ms = new Missile(x, y, game);
      ms.setDirection(rnd.nextInt(360));
      game.getFutureList().add(ms);
    }

    move();
  }
  /**
   * Makes the ship removable. Notifies the controller.
   */
  public void collide(Types.ObjectTypes collideWith) {
    notifyDestroyed();
    removable = true;
  }
}

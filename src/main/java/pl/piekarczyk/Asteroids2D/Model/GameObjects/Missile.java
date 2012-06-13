package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;

/**
 * Represents a missile. Missiles don't change directions and have limited
 * life time.
 */
public class Missile extends AsteroidsObject {
  public Missile(GameModel thisGame) {
    this(0, 0, thisGame);
  }
  public Missile(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 20;
    width = 20;
    type = Types.ObjectTypes.MISSILE;
    born = System.currentTimeMillis();
    this.setMagnitude(60);
  }
  public Missile(Missile a) {
    super(a);
  }
  public Missile copy() {
    return new Missile(this);
  }
  /**
   * Moves the missile. Marks it removable if it has been alive for too long.
   */
  public void step() {
    if(System.currentTimeMillis() - born > 3000) removable = true;
    move();
  }
  /**
   * Makes the missile removable.
   */
  public void collide(Types.ObjectTypes collideWith) {
    removable = true;
  }
  private long born;
}

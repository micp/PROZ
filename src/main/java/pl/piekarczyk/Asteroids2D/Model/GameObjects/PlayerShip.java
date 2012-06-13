package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

/**
 * Represents a player controlled ship. The ship can rotate in both 
 * directions and can accelerate gradually.
 */
public class PlayerShip extends AsteroidsObject {
  public PlayerShip(GameModel thisGame) {
    this(0, 0, thisGame);
  }
  public PlayerShip(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 80;
    width = 80;
    type = Types.ObjectTypes.SHIP;
    this.setMagnitude(0);
    accel = new PSAccelVector(5, 0);
  }
  public PlayerShip(PlayerShip a) {
    super(a);
    this.accel = new PSAccelVector(a.getAccelVector());
  }
  public PlayerShip copy() {
    return new PlayerShip(this);
  }
  /**
   * Moves the ship. Investigates player input to check if the ship should
   * be rotated or accelerated. Traction is applied to the ship to limit
   * it's maximum speed and make it gradually slow down in the absence of
   * acceleration. Also manages the rotation of the ship.
   */
  public void step() {
    if(game.getKeyState(Types.Keys.UP))
      accel.accelerate();
    else
      accel.decelerate();
    if(game.getKeyState(Types.Keys.LEFT))
      accel.setDirection(accel.getDirection() + 10);
    else if(game.getKeyState(Types.Keys.RIGHT))
      accel.setDirection(accel.getDirection() - 10);
    moment.sumWith(accel);
    applyTraction();

    move();
    setRot(accel.getDirection());
  }
  /**
   * Notifies the ship of collision. Colliding player ships decrement life 
   * count of the related GameModel.
   */
  public void collide(Types.ObjectTypes collideWith) {
    game.decLives();
    notifyDestroyed();
    removable = true;
  }
  /**
   * Returns DynamicGameVector which represents the force applied to the ship.
   * @return Reference to the acceleration vector applied to the ship.
   */
  public DynamicGameVector getAccelVector() {
    return accel; 
  }
  private void applyTraction() {
    if(getMagnitude() > 25)
      setMagnitude(25);
    if(getMagnitude() < 1.0)
      setMagnitude(0.0);
    else
      setMagnitude(getMagnitude() - 1.0);
  }
  private DynamicGameVector accel;
}

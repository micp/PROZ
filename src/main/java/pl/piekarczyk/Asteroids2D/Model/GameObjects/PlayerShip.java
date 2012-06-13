package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

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
    accel = new PSAccelVector(0, 0);
  }
  public PlayerShip(PlayerShip a) {
    super(a);
    this.accel = new PSAccelVector(a.getAccelVector());
  }
  public PlayerShip copy() {
    return new PlayerShip(this);
  }
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
  public void collide(Types.ObjectTypes collideWith) {
    game.decLives();
    notifyDestroyed();
    removable = true;
  }
  public DynamicGameVector getAccelVector() {
    return accel; 
  }
  private void applyTraction() {
    if(getMagnitude() > 30)
      setMagnitude(30);
    if(getMagnitude() < 1.0)
      setMagnitude(0.0);
    else
      setMagnitude(getMagnitude() - 1.0);
  }
  private DynamicGameVector accel;
}

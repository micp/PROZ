package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

public class PlayerShip extends AsteroidsObject {
  public PlayerShip(GameModel thisGame) {
    this(0, 0, thisGame);
  }
  public PlayerShip(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 60;
    width = 60;
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
  static public int count() { return count; }
  static public void decCount() { count--; }
  static public void incCount() { count++; }
  //@OPT is modifiable, shouldn't be
  public final DynamicGameVector getAccelVector() { return accel; }
  public void collide(Types.ObjectTypes collideWith) {
    game.decLives();
    //@OPT with whom collided?
    count--;
    removable = true;
    //@OPT
    //addShip();
  }
  public void step() {
    //@OPT
    //if(keys[Keys.up]) accel.accelerate();
    if(game.getKeyState(Types.Keys.UP))
      accel.accelerate();
    else
      accel.decelerate();
    if(game.getKeyState(Types.Keys.LEFT))
      accel.setDirection(accel.getDirection() + 10);
    else if(game.getKeyState(Types.Keys.RIGHT))
      accel.setDirection(accel.getDirection() - 10);
    //else
    //  accel.rotate(moment);
    moment.sumWith(accel);
    applyTraction();
    //System.out.format("%g%n", accel.getMagnitude());

    //@OPT
    //if(keys[Keys.space]) tryAddMissile();

    move();
  }
  private void applyTraction() {
    if(this.getMagnitude() > 50)
      this.setMagnitude(50);
    if(this.getMagnitude() < 1.0)
      this.setMagnitude(0.0);
    else
      this.setMagnitude(this.getMagnitude() - 1.0);
  }
  private DynamicGameVector accel;
  private static int count = 0;
}

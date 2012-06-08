package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

public class PlayerShip extends PhysicalObject {
  public PlayerShip(int nx, int ny, AsteroidsModel thisGame) {
    super(nx, ny, thisGame);
    height = 20;
    width = 20;
    type = Types.ObjectTypes.SHIP;
    accel = new PSAccelVector(0, 0);
  }
  public PlayerShip(PlayerShip a) {
    super(a);
    this.accel = new PSAccelVector(a.getAccelVector());
  }
  public PlayerShip copy() {
    return new PlayerShip(this);
  }
  //@OPT is modifiable, shouldn't be
  public final PSAccelVector getAccelVector() { return accel; }
  public void collide(Types.ObjectTypes collideWith) {
    game.decLives();
    //@OPT with whom collided?
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
      accel.rotate(moment, Types.Keys.LEFT);
    else if(game.getKeyState(Types.Keys.RIGHT))
      accel.rotate(moment, Types.Keys.RIGHT);
    moment.sumWith(accel);

    //@OPT
    //if(keys[Keys.space]) tryAddMissile();

    move();
  }
  private PSAccelVector accel;
}

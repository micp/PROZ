package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.Vectors.MomentumVector;
import pl.piekarczyk.Asteroids2D.Model.Vectors.PSAccelVector;

public class PlayerShip extends PhysicalObject {
  public PlayerShip(int nx, int ny) {
    super(nx, ny);
    height = 20;
    width = 20;
    type = Types.PhysicalObjectTypes.SHIP;
    accel = new PSAccelVector(0, 0);
  }
  public void collide(Types.PhysicalObjectTypes collideWith) {
    //@OPT
    //lives--;
    //@OPT with whom collided?
    removable = true;
    //@OPT
    //addShip();
  }
  public void step() {
    //@OPT
    //if(keys[Keys.up]) accel.accelerate();
    //else accel.decelerate();
    //if(keys[Keys.left]) accel.rotate(moment, Keys.left);
    //else if(keys[Keys.right]) accel.rotate(moment, Keys.right);
    //moment = moment.sum(accel);

    //if(keys[Keys.space]) tryAddMissile();

    move();
  }
  private PSAccelVector accel;
}

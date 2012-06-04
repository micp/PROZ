package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.Vectors.MomentumVector;

public class Enemy extends PhysicalObject {
  public Enemy() {
    this(0, 0);
  }
  public Enemy(int nx, int ny) {
    super(nx, ny);
    height = 20;
    width = 20;
    type = Types.ObjectTypes.SHIP;
  }
  public Enemy(Enemy a) {
    super(a);
  }
  public void collide(Types.ObjectTypes collideWith) {
    //@OPT with how collied?
    //@OPT up score
    removable = true;
  }
  public void step() {
    //@OPT random change dir
    //@OPT try emit missile
    move();
  }
  public Enemy copy() {
    return new Enemy(this);
  }
}

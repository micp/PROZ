package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Common.Types;

public class Missile extends AsteroidsObject {
  public Missile(GameModel thisGame) {
    this(0, 0, thisGame);
  }
  public Missile(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height =15 ;
    width = 15;
    type = Types.ObjectTypes.MISSILE;
    born = System.currentTimeMillis();
    this.setMagnitude(40);
  }
  public Missile(Missile a) {
    super(a);
  }
  public void collide(Types.ObjectTypes collideWith) {
    //@OPT up score
    //@OPT take care with whom collided
    count--;
    removable = true;
  }
  public void step() {
    if(System.currentTimeMillis() - born > 10000) removable = true;
    move();
  }
  public Missile copy() {
    return new Missile(this);
  }
  static public int count() { return count; }
  static public void decCount() { count--; }
  static public void incCount() { count++; }
  private long born;
  private static int count = 0;
}

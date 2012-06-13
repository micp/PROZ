package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;

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
    this.setMagnitude(40);
  }
  public Missile(Missile a) {
    super(a);
  }
  public Missile copy() {
    return new Missile(this);
  }
  public void step() {
    if(System.currentTimeMillis() - born > 3000) removable = true;
    move();
  }
  public void collide(Types.ObjectTypes collideWith) {
    removable = true;
  }
  private long born;
}

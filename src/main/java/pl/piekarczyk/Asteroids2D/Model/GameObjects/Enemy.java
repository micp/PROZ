package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Common.Types;

public class Enemy extends AsteroidsObject {
  public Enemy(GameModel thisGame) {
    this(0, 0, thisGame);
  }
  public Enemy(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 60;
    width = 60;
    type = Types.ObjectTypes.ENEMY;
  }
  public Enemy(Enemy a) {
    super(a);
  }
  public void collide(Types.ObjectTypes collideWith) {
    //@OPT with how collied?
    //@OPT up score
    count--;
    removable = true;
  }
  public void step() {
    Random rnd = new Random();
    if(rnd.nextInt(1000) > 990)
      this.setDirection(this.getDirection() + 45 * rnd.nextInt(7));
    if(rnd.nextInt(1000) > 999) {
      Missile ms = new Missile(x, y, game);
      ms.setDirection(rnd.nextInt(360));
      game.getFutureList().add(ms);
    }
    //@OPT try emit missile
    move();
  }
  public Enemy copy() {
    return new Enemy(this);
  }
  static public int count() { return count; }
  static public void decCount() { count--; }
  static public void incCount() { count++; }
  private static int count = 0;
}

package pl.piekarczyk.Asteroids2D.Model.GameObjects;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;

public class Enemy extends AsteroidsObject {
  public Enemy(GameModel thisGame) {
    this(0, 0, thisGame);
  }
  public Enemy(double nx, double ny, GameModel thisGame) {
    super(nx, ny, thisGame);
    height = 80;
    width = 80;
    type = Types.ObjectTypes.ENEMY;
  }
  public Enemy(Enemy a) {
    super(a);
  }
  public Enemy copy() {
    return new Enemy(this);
  }
  public void step() {
    Random rnd = new Random();

    if(rnd.nextInt(1000) > 990)
      this.setDirection(this.getDirection() + 45 * rnd.nextInt(7));

    if(rnd.nextInt(1000) > 995) {
      Missile ms = new Missile(x, y, game);
      ms.setDirection(rnd.nextInt(360));
      game.getFutureList().add(ms);
    }

    move();
  }
  public void collide(Types.ObjectTypes collideWith) {
    notifyDestroyed();
    removable = true;
  }
}

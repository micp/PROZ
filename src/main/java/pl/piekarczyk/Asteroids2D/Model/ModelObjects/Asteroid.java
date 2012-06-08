package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public class Asteroid extends PhysicalObject {
  public Asteroid(AsteroidsModel thisGame) {
    this(0, 0, thisGame);
  }
  public Asteroid(int nx, int ny, AsteroidsModel thisGame) {
    super(nx, ny, thisGame);
    height = 20;
    width = 20;
    type = Types.ObjectTypes.ASTEROID;
  }
  public Asteroid(Asteroid a) {
    super(a);
  }
  public void collide(Types.ObjectTypes collideWith) {
    //@OPT up score
    //@OPT take care with whom collided
    //@OPT generate lesser level asteroids
    removable = true;
  }
  public void step() {
    move();
  }
  public Asteroid copy() {
    return new Asteroid(this);
  }
}

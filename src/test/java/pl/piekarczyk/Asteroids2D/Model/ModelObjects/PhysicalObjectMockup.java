package pl.piekarczyk.Asteroids2D.Test.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.ModelObjects.PhysicalObject;
import pl.piekarczyk.Asteroids2D.Model.Common.*;
import pl.piekarczyk.Asteroids2D.Model.GameModel;

public class PhysicalObjectMockup extends PhysicalObject {
  public PhysicalObjectMockup(int x, int y, GameModel game) {
    super(x, y, game);
  }
  public PhysicalObjectMockup(PhysicalObjectMockup a) {
    super(a);
  }
  public void collide(Types.ObjectTypes cw) {}
  public PhysicalObjectMockup copy() { return this; /*not needed*/}
  public void step() { move(); }
}

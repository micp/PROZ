package pl.piekarczyk.Asteroids2D.Test.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.ModelObjects.ModelObject;

public class ModelObjectMockup extends ModelObject {
  public void step() {}
  public ModelObjectMockup copy() { return this; /*useless for this test*/}
  public ModelObjectMockup(GameModel game) {
    super(game);
  }
  public ModelObjectMockup(ModelObjectMockup a) {
    super(a);
  }
}

package pl.piekarczyk.Asteroids2D.Model.Input.Keys;

import pl.piekarczyk.Asteroids2D.Model.AsteroidsModel;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public class KeyLeft implements Input {
  public void reaction(AsteroidsModel model) {
    model.setKey(Types.Keys.left, true);
  }
}

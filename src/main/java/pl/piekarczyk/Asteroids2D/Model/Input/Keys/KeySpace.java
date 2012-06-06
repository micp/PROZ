package pl.piekarczyk.Asteroids2D.Model.Input.Keys;

import pl.piekarczyk.Asteroids2D.Model.Input.InputEvent;
import pl.piekarczyk.Asteroids2D.Model.AsteroidsModel;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public class KeySpace implements InputEvent {
  public void reaction(AsteroidsModel model) {
    model.setKey(Types.Keys.SPACE, true);
  }
}

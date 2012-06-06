package pl.piekarczyk.Asteroids2D.Model.Input.Keys;

import pl.piekarczyk.Asteroids2D.Model.Input.InputEvent;
import pl.piekarczyk.Asteroids2D.Model.AsteroidsModel;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public class KeyUP implements InputEvent {
  public void reaction(AsteroidsModel model) {
    model.setKey(Types.Keys.UP, true);
  }
}
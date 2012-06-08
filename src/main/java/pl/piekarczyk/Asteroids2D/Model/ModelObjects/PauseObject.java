package pl.piekarczyk.Asteroids2D.Model.ModelObjects;

import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public class PauseObject extends ModelObject {
  //@OPT
  public PauseObject(AsteroidsModel thisGame) {
    super(thisGame);
    type = Types.ObjectTypes.PAUSE;
  }
  public PauseObject(PauseObject a) {
    super(a);
  }
  public void step() {
    if(game.getKeyState(Types.Keys.P)) {
      if(game.isPaused())
	game.unPause();
      else
	game.pause();
    }
  }
  public PauseObject copy() {
    return new PauseObject(this);
  }
}

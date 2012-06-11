package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Common.Types;

public class PauseController extends AsteroidsController {
  public PauseController(GameModel thisGame) {
    super(thisGame);
  }
  public void manage() {
    if(game.getKeyState(Types.Keys.P)) {
      if(game.isPaused())
	game.unPause();
      else
	game.pause();
    }
  }
}

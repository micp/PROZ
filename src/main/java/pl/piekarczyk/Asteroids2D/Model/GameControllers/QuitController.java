package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Common.Types;

public class QuitController extends AsteroidsController {
  public QuitController(GameModel thisGame) {
    super(thisGame);
  }
  public void manage() {
    if(game.getKeyState(Types.Keys.Q)) game.stopGame();
  }
}

package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;

public class QuitController extends AsteroidsController {
  public QuitController(GameModel thisGame) {
    super(thisGame);
  }
  public void manage() {
    if(game.getKeyState(Types.Keys.Q)) game.stopGame();
  }
  public void close() {}
  public void notifyCreated() {}
  public void notifyDestroyed() {}
}

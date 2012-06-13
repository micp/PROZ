package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;

/**
 * Handles user requests to end the game. Responds to the Q key.
 */
public class QuitController extends AsteroidsController {
  public QuitController(GameModel thisGame) {
    super(thisGame);
  }
  /**
   * Stops the game if the Q key has been pressed. Utilizes the {@link 
   * pl.piekarczyk.Asteroids2D.Model.GameModel#stopGame()} method.
   */
  public void manage() {
    if(game.getKeyState(Types.Keys.Q)) game.stopGame();
  }
  /**
   * Empty, does nothing.
   */
  public void close() {}
  /**
   * Empty, does nothing.
   */
  public void notifyCreated() {}
  /**
   * Empty, does nothing.
   */
  public void notifyDestroyed() {}
}

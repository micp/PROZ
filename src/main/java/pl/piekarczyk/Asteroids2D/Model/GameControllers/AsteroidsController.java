package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import pl.piekarczyk.Asteroids2D.Model.GameModel;

/**
 * A controller helper abstract class. Provides some basic functionality 
 * required by virtually every controller. Most controllers should 
 * extend this.
 */
public abstract class AsteroidsController implements GameController {
  /**
   * Connects the controller to the specified GameModel.
   * @param thisGame Game model should provide 'this' here.
   */
  public AsteroidsController(GameModel thisGame) {
    game = thisGame;
  }
  /**
   * A reference to the connected GameModel.
   */
  protected GameModel game;
}

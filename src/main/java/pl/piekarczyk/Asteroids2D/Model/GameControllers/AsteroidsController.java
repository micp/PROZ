package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import pl.piekarczyk.Asteroids2D.Model.GameModel;

public abstract class AsteroidsController implements GameController {
  public AsteroidsController(GameModel thisGame) {
    game = thisGame;
  }
  protected GameModel game;
}

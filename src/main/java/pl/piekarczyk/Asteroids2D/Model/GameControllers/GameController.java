package pl.piekarczyk.Asteroids2D.Model.GameControllers;

/**
 * Game controllers are responsible for the game logic. This is the primary
 * interface of the game controllers guaranteeing the bare minimum 
 * functionality expected of the game controllers.
 */
public interface GameController {
  /**
   * The primary function of each game controller. Is invoked every loop
   * and all logic of a game controller should be handled here.
   */
  void manage();
  /**
   * Game controller destructor. Is invoked once after the game is over.
   */
  void close();
}

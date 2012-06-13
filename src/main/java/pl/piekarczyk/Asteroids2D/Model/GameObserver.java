package pl.piekarczyk.Asteroids2D.Model;

/**
 * Accepted by the game model as an observer. Objects wishing to observe and 
 * receive game state change should implement this.
 */
public interface GameObserver {
  /**
   * Invoked by the game model to notify observers of state changes. Contains
   * all information about current game state.
   * 
   * @param latestGameState Contains game state information.
   */
  void updAll(GameState latestGameState);
}

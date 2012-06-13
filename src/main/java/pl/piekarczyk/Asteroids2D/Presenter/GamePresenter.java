package pl.piekarczyk.Asteroids2D.Presenter;

/**
 * A presenter in the MVP pattern. Provides utilities for the model and the 
 * view to communicate without ever directly interacting with each other.
 */
public interface GamePresenter {
  /**
   * A method to put heavy operations related to initializing the presenter.
   * Usually this means starting the model.
   */
  void startPresenter();
  /**
   * Used by the view to notify the presenter of keyboard state change. Should
   * handle retrieving new state and injecting it to the model.
   */
  void updKbdState();
  /**
   * Used by the view to request newest game state. Usually pauses the view
   * thread if no current game state to retrieve. Should be used by the view
   * if it has already rendered all previous changes and has nothing else to
   * do.
   * @throws InterruptedException Thrown if the thread waiting for new state
   * is interrupted.
   */
  void getGameState() throws InterruptedException;
  /**
   * Used by the view when the user requests the model to close. Assures 
   * proper shutdown of the model.
   */
  void close();
}

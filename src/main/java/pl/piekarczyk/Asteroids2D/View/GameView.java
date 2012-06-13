package pl.piekarczyk.Asteroids2D.View;

/**
 * A view interface in the MVP pattern. Guarantees all functionality required
 * to properly display the game model.
 */
public interface GameView {
  /**
   * Allows the presenter to retrieve the latest keyboard state.
   * @return A boolean array of key states, compliant with the 
   * {@link pl.piekarczyk.Asteroids2D.Common.Types.Keys} enum.
   */
  boolean[] getKbdState();
  /**
   * Displays the requested score in the view.
   */
  void drawScore(int score);
  /**
   * Displays the requested life amount in the view.
   */
  void drawLives(int lives);
  /**
   * Displays the player ship in the requested coordinates.
   */
  void drawPlayerShip(double x, double y, double rot);
  /**
   * Displays an asteroid in the requested coordinates.
   */
  void drawAsteroid(double x, double y, double rot);
  /**
   * Displays a tiny asteroid in the requested coordinates.
   */
  void drawTinyAsteroid(double x, double y, double rot);
  /**
   * Displays an enemy ship in the requested coordinates.
   */
  void drawEnemy(double x, double y, double rot);
  /**
   * Displays a missile in the requested coordinates.
   */
  void drawMissile(double x, double y, double rot);
  /**
   * Opens the scoreboard window.
   */
  void showScoreBoard();
  /**
   * Clears the view.
   */
  void clearScreen();
  /**
   * Requests the view to close. Usually used after the game has ended or
   * when the user requests the game to stop.
   */
  void requestClose();
  /**
   * Forces the view to redraw itself.
   */
  void forceRepaint();
}

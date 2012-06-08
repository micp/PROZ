package pl.piekarczyk.Asteroids2D.View;

//@OPT this shouldn't be a problem with presenter.
import pl.piekarczyk.Asteroids2D.Model.GameState;

public interface View {
  boolean[] getKbdState();
  //void updGameState(GameState nextGameState);
  void drawScore(int score);
  void drawLives(int lives);
  void drawPaused();
  void hidePaused();
  void drawShip(int x, int y);
  void drawAsteroid(int x, int y);
  void drawEnemy(int x, int y);
  void drawTitle();
  void hideTitle();
  void clearPhysicalObject();
}

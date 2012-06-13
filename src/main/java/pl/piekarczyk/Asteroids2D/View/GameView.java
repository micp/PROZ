package pl.piekarczyk.Asteroids2D.View;

public interface GameView {
  boolean[] getKbdState();
  void drawScore(int score);
  void drawLives(int lives);
  void drawPlayerShip(double x, double y, double rot);
  void drawAsteroid(double x, double y, double rot);
  void drawTinyAsteroid(double x, double y, double rot);
  void drawEnemy(double x, double y, double rot);
  void drawMissile(double x, double y, double rot);
  void showScoreBoard();
  void clearScreen();
  void requestClose();
  void forceRepaint();
}

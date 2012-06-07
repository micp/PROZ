package pl.piekarczyk.Asteroids2D.Model;

public interface Observer {
  void updAll(GameState latestGameState);
}

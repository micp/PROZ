package pl.piekarczyk.Asteroids2D.Presenter;

public interface GamePresenter {
  void startPresenter();
  void updKbdState();
  void getGameState() throws InterruptedException;
  void close();
}

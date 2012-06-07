package pl.piekarczyk.Asteroids2D.View;

//@OPT this shouldn't be a problem with presenter.
import pl.piekarczyk.Asteroids2D.Model.GameState;

public interface View {
  boolean[] getKbdState();
  void updGameState(GameState nextGameState);
}

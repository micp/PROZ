package pl.piekarczyk.Asteroids2D.Presenter;

import java.util.concurrent.*;
import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.View.View;

public class AsteroidsPresenter implements Observer {
  /*--- MVP --*/
  public AsteroidsPresenter(View writeTo) {
    gameView = writeTo;
    gameModel = new AsteroidsModel();
    gameStateQueue = new LinkedBlockingDeque<GameState>(1);
  }
  public void updKbdState() {
    boolean[] nextState = gameView.getKbdState();
    gameModel.setKbdState(nextState);
  }
  public void updAll(GameState latestGameState) {
    if(!gameStateQueue.isEmpty())
      gameStateQueue.clear();
    gameStateQueue.push(latestGameState);
  }
  public void getGameState() throws InterruptedException {
    gameView.updGameState(gameStateQueue.take());
  }
  private BlockingDeque<GameState> gameStateQueue;
  private View gameView;
  private AsteroidsModel gameModel;
  /*--- REST --*/
}

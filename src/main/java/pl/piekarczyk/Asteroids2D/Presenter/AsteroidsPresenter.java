package pl.piekarczyk.Asteroids2D.Presenter;

import java.util.*;
import java.util.concurrent.*;
import pl.piekarczyk.Asteroids2D.Common.*;
import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;
import pl.piekarczyk.Asteroids2D.View.GameView;

public class AsteroidsPresenter implements GameObserver, GamePresenter {
  /*--- MVP --*/
  public AsteroidsPresenter(GameView writeTo) {
    gameView = writeTo;
    gameModel = new AsteroidsModel();
    gameStateQueue = new LinkedBlockingDeque<GameState>(1);
    gameModel.addObserver(this);
    gameModel.runGame();
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
    updView(gameStateQueue.take());
  }
  private void updView(GameState nextGameState) {
    gameView.clearScreen();

    if(nextGameState.isOver()) {
      gameView.showScoreBoard();
      return;
    }
    if(nextGameState.isWaiting()) {
      gameView.drawTitle();
      return;
    }

    if(nextGameState.isPaused()) gameView.drawPaused();

    gameView.drawScore(nextGameState.getScore());
    gameView.drawLives(nextGameState.getLives());

    ListIterator<GameObject> it = 
      nextGameState.getObjectIterator();
    while(it.hasNext()) {
      GameObject cur = it.next();
      Types.ObjectTypes type = cur.getType();

      int limit = nextGameState.getFieldSize();
      double x = (cur.getX() + limit) / (2 * limit / 480);
      double y = (cur.getY() + limit) / (2 * limit / 480);
      double rot = cur.getRot();
      if(type == Types.ObjectTypes.SHIP)
	gameView.drawPlayerShip(x, y, rot);
      else if(type == Types.ObjectTypes.MISSILE)
	gameView.drawMissile(x, y, rot);
      else if(type == Types.ObjectTypes.ASTEROID)
	gameView.drawAsteroid(x, y, rot);
      else if(type == Types.ObjectTypes.TINYASTEROID)
	gameView.drawTinyAsteroid(x, y, rot);
      else if(type == Types.ObjectTypes.ENEMY)
	gameView.drawEnemy(x, y, rot);
    }
    gameView.forceRepaint();
  }
  private BlockingDeque<GameState> gameStateQueue;
  private GameView gameView;
  private GameModel gameModel;
  /*--- REST --*/
}

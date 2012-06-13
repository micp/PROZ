package pl.piekarczyk.Asteroids2D.Presenter;

import java.util.*;
import java.util.concurrent.*;
import pl.piekarczyk.Asteroids2D.Common.*;
import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;
import pl.piekarczyk.Asteroids2D.View.GameView;

public class AsteroidsPresenter implements GameObserver, GamePresenter {
  public AsteroidsPresenter(GameView writeTo) {
    gameView = writeTo;
    gameStateQueue = new LinkedBlockingDeque<GameState>(1);
  }
  public void startPresenter() {
    gameModel = new AsteroidsModel();
    gameModel.addObserver(this);
    gameModel.runGame();
  }
  public void updKbdState() {
    boolean[] nextState = gameView.getKbdState();
    gameModel.setKbdState(nextState);
  }
  public void close() {
    gameModel.stopGame();
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
      gameView.requestClose();
      return;
    }

    gameView.drawScore(nextGameState.getScore());
    gameView.drawLives(nextGameState.getLives());

    ListIterator<GameObject> it = 
      nextGameState.getObjectIterator();
    while(it.hasNext()) {
      GameObject cur = it.next();
      Types.ObjectTypes type = cur.getType();

      double limit = nextGameState.getFieldSize();
      double x = (cur.getX() + limit) * (480 / (2 * limit));
      double y = (cur.getY() + limit) * (480 / (2 * limit));
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
}

package pl.piekarczyk.Asteroids2D.Model;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.*;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;
import pl.piekarczyk.Asteroids2D.Model.GameControllers.*;

/** 
 * The Asteroids2D game. Provides a backbone for various model objects to 
 * communicate and interact.
 */
public class AsteroidsModel implements GameModel {
  /**
   * Prepares the data and links necessary objects. Doesn't run game, just 
   * prepares necessary data. See {@link #runGame()}.
   */
  public AsteroidsModel() {
    over = false;
    lives = 999;
    score = 0;
    fieldSize = 1000;

    volatileKbdState = new boolean[Types.Keys._SIZE.ordinal()];
    safeKbdState = new boolean[Types.Keys._SIZE.ordinal()];

    observerList = new LinkedList<GameObserver>();
    objectList = new LinkedList<GameObject>();
    objectFutureList = new LinkedList<GameObject>();
    controllerList = new LinkedList<GameController>();

    controllerList.add(new QuitController(this));

    controllerList.add(new PSProducerController(this));
    controllerList.add(new AsteroidProducerController(this));
    controllerList.add(new EnemyProducerController(this));
    controllerList.add(new MissileProducerController(this));
  }
  public int getLives() {
    return lives;
  }
  public int getScore() {
    return score;
  }
  public int getFieldSize() {
    return fieldSize;
  }
  public boolean isOver() {
    return over;
  }
  public boolean getKeyState(Types.Keys k) {
    return safeKbdState[k.ordinal()];
  }
  public LinkedList<GameObject> getList() { 
    return objectList;
  }
  public LinkedList<GameObject> getFutureList() {
    return objectFutureList;
  }
  public boolean isColliding(GameObject obj) {
    ListIterator<GameObject> it = objectList.listIterator();
    while(it.hasNext()) {
      GameObject cur = it.next();
      if( cur == obj ) continue;
      else if( collisionDetect(obj, cur) )
        return true;
    }
    return false;
  }
  /** 
   * Registers an observer with the game model. Will be notified of changes
   * in the game model at the end of each loop. Does not allow for duplicate 
   * entries.
   * @param obs Observer to be appended to the list.
   */
  public void addObserver(GameObserver obs) {
    if(observerList.contains(obs)) return;
    observerList.push(obs);
  }
  synchronized public void setKbdState(boolean[] newKbdState) {
    volatileKbdState = newKbdState;
  }
  public void addScore(int sc) {
    score += sc;
  }
  public void decLives() {
    lives--;
  }
  private void notifyUpdAll() {
    GameState currentGameState = new AsteroidsState(this);
    ListIterator<GameObserver> it = observerList.listIterator();
    while(it.hasNext())
      it.next().updAll(currentGameState);
  }
  /**
   * Requests the game to close. Does so by setting lives to zero. Will not 
   * stop the game immediately, instead will wait until the current loop
   * is finished.
   */
  public void stopGame() {
    lives = 0;
  }
  /**
   * Starts the main game loop. Forks into separate thread which will run the
   * loop until the game is over.
   */
  public void runGame() {
    Thread t = new Thread(new Runnable() {
      public void run() {
	startGame();
      }
    });
    t.start();
  }
  private void startGame() {
    while(lives != 0) {
      sleepSnap();
      freezeInput();
      tick();
      notifyUpdAll();
      try {
	sleepTill(60);
      } catch(InterruptedException e) {
	stopGame();
      }
    }
    finishGame();
  }
  private void sleepSnap() {
    timer = System.currentTimeMillis();
  }
  private void sleepTill(int len) throws InterruptedException {
    long sleepLeft = System.currentTimeMillis() - timer;
    if(sleepLeft < 0) return;
    Thread.sleep(len - sleepLeft);
  }
  synchronized private void freezeInput() {
    for(int i = 0; i < Types.Keys._SIZE.ordinal(); i++)
      safeKbdState[i] = volatileKbdState[i];
  }
  private void tick() {
    runControllers();
    runObjects();
    findCollisions();
    cleanRemovable();
    copyFutureObjects();
  }
  private void runControllers() {
    ListIterator<GameController> it = controllerList.listIterator();
    while(it.hasNext())
      it.next().manage();
  }
  private void runObjects() {
    ListIterator<GameObject> it = objectList.listIterator();
    while(it.hasNext())
      it.next().step();
  }
  private void findCollisions() {
    ListIterator<GameObject> it = objectList.listIterator();
    while(it.hasNext()) {
      GameObject cur = it.next();
      if(cur.isRemovable()) continue;
      ListIterator<GameObject> it2 = objectList.listIterator(it.nextIndex());
      while(it2.hasNext()) {
	GameObject next = it2.next();
	if(next.isRemovable()) continue;
	if(collisionDetect(cur, next)) {
	  cur.collide(next.getType());
	  next.collide(cur.getType());
	}
      }
    }
  }
  private void cleanRemovable() {
    ListIterator<GameObject> it = objectList.listIterator();
    while(it.hasNext()) {
      GameObject cur = it.next();
      if(cur.isRemovable())
	it.remove();
    }
  }
  private void copyFutureObjects() {
    ListIterator<GameObject> it = objectFutureList.listIterator();
    while(it.hasNext())
      objectList.add(it.next());
    objectFutureList.clear();
  }
  private void finishGame() {
    RecordList rl;
    try {
      rl = RecordList.read("records");
    } catch(Exception e) {
      rl = new RecordList();
    }
    rl.addRecord(score);
    try {
      rl.write("records");
    } catch(Exception i) {}
    over = true;
    notifyUpdAll();

    closeControllers();
  }
  private void closeControllers() {
    ListIterator<GameController> it = controllerList.listIterator();
    while(it.hasNext())
      it.next().close();
  }
  private boolean collisionDetect(GameObject a, GameObject b) {
    double mxa = a.getMiddleX();
    double mxb = b.getMiddleX();
    double mya = a.getMiddleY();
    double myb = b.getMiddleY();
    double r1 = Math.hypot(mxa - a.getX(), mya - a.getY());
    double r2 = Math.hypot(mxb - b.getX(), myb - b.getY());
    double dist = Math.hypot(mxa - mxb, mya - myb);
    return dist < r1 + r2;
  }

  private boolean[] volatileKbdState;
  private boolean[] safeKbdState;
  private LinkedList<GameObserver> observerList;
  private LinkedList<GameObject> objectList;
  private LinkedList<GameObject> objectFutureList;
  private LinkedList<GameController> controllerList;
  private long timer;
  private boolean over;
  private int lives, score;
  private int fieldSize;
}

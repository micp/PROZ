package pl.piekarczyk.Asteroids2D.Model;

import java.util.*;
import java.io.*;
import java.awt.Rectangle;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;
import pl.piekarczyk.Asteroids2D.Model.GameControllers.*;
import pl.piekarczyk.Asteroids2D.Common.*;

public class AsteroidsModel implements GameModel {
  /*--- MVP ---*/
  public AsteroidsModel() {
    waiting = true;
    paused = false;
    over = false;
    lives = 3;
    score = 0;
    //@OPT scallable?
    fieldSize = 1000;

    volatileKbdState = new boolean[Types.Keys._SIZE.ordinal()];
    safeKbdState = new boolean[Types.Keys._SIZE.ordinal()];

    observerList = new LinkedList<GameObserver>();
    objectList = new LinkedList<GameObject>();
    objectFutureList = new LinkedList<GameObject>();
    controllerList = new LinkedList<GameController>();

    //controllerList.add(new PauseController(this));
    controllerList.add(new QuitController(this));

    controllerList.add(new PSProducerController(this));
    controllerList.add(new AsteroidProducerController(this));
    controllerList.add(new EnemyProducerController(this));
    controllerList.add(new MissileProducerController(this));
  }
  synchronized public void setKbdState(boolean[] newKbdState) {
    volatileKbdState = newKbdState;
  }
  public void addObserver(GameObserver obs) {
    if(observerList.contains(obs)) return;
    observerList.push(obs);
  }
  private void notifyUpdAll() {
    GameState currentGameState = new AsteroidsState(this);
    ListIterator<GameObserver> it = observerList.listIterator();
    while(it.hasNext())
      it.next().updAll(currentGameState);
  }
  private boolean[] volatileKbdState;
  private boolean[] safeKbdState;
  private LinkedList<GameObserver> observerList;
  private LinkedList<GameObject> objectList;
  private LinkedList<GameObject> objectFutureList;
  private LinkedList<GameController> controllerList;
  /*--- INTERFACE ---*/
  //@OPT do this in a better way
  public LinkedList<GameObject> getList() { 
    return objectList;
    //LinkedList<GameObject> result = new LinkedList<GameObject>();
    //ListIterator<GameObject> it = objectList.listIterator();
    //while(it.hasNext())
    //  result.add(it.next().copy());
    //return result; 
  }
  public LinkedList<GameObject> getFutureList() {
    return objectFutureList;
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
  public boolean isWaiting() {
    return waiting;
  }
  public void stopGame() {
    lives = 0;
  }
  public void decLives() {
    lives--;
  }
  public void addScore(int sc) {
    score += sc;
  }
  public boolean isPaused() {
    return paused;
  }
  public void unPause() {
    paused = false;
  }
  public void pause() {
    paused = true;
  }
  public void runGame() {
    Thread t = new Thread(new Runnable() {
      public void run() {
	startGame();
      }
    });
    t.start();
  }
  public boolean getKeyState(Types.Keys k) {
    return safeKbdState[k.ordinal()];
  }
  /*--- REST ---*/
  private void startGame() {
    //Game doesn't run until user input
    waitForInput();
    //Add inital objects to game field
    initGame();
    //Game loop
    while(lives != 0) {
      freezeInput();
      tick();
      notifyUpdAll();
      try{Thread.sleep(50);}catch(Exception i){}
    }
    //Check for new record, clean up
    finishGame();
  }

  private void waitForInput() {
    //Await for user input before starting
    //TODO set pause now, make it removable by everything
  }
  private void initGame() {
    //Game initialization
    waiting = false;
  }
  synchronized private void freezeInput() {
    for(int i = 0; i < Types.Keys._SIZE.ordinal(); i++)
      safeKbdState[i] = volatileKbdState[i];
  }
  //@OPT error log framework
  private void tick() {
    runControllers();
    runObjects();
    findCollisions();
    copyFutureObjects();
  }
  private void runControllers() {
    ListIterator<GameController> it = controllerList.listIterator();
    while(it.hasNext()) {
      it.next().manage();
    }
  }
  private void runObjects() {
    ListIterator<GameObject> it = objectList.listIterator();
    while(it.hasNext()) {
      GameObject cur = it.next();
      //@OPT event for removal? (maybe in collisions)
      if(cur.isRemovable()) {
	it.remove();
	continue;
      }
      cur.step();
    }
  }
  private void finishGame() {
    //@OPT handle new record
    //Reset game
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
  }
  private void findCollisions() {
    //Skip pause and quit objects
    ListIterator<GameObject> it = objectList.listIterator();
    while(it.hasNext()) {
      //@OPT this is wrong, search for others like it
      GameObject cur = it.next();
      ListIterator<GameObject> it2 = it;
      while(it2.hasNext()) {
	GameObject next = it2.next();
	if(collisionDetect(cur, next)) {
	  cur.collide(next.getType());
	  next.collide(cur.getType());
	}
      }
    }
  }
  private void copyFutureObjects() {
    ListIterator<GameObject> it = objectFutureList.listIterator();
    while(it.hasNext())
      objectList.add(it.next());
    objectFutureList.clear();
  }
  //@OPT decide how to optimize (asteroids can't collide with asteroids)
  private boolean collisionDetect(GameObject a, GameObject b) {
    Rectangle aRec = new Rectangle((int)a.getX(), (int)a.getY(), 
                                   a.getWidth(), a.getHeight());
    Rectangle bRec = new Rectangle((int)b.getX(), (int)b.getY(),
                                   b.getWidth(), b.getHeight());
    return aRec.intersects(bRec);
  }
  public boolean isColliding(GameObject obj) {
    //@OPT change iterator template param (and others like it)
    ListIterator<GameObject> it = objectList.listIterator(0);
    while(it.hasNext()) {
      GameObject cur = it.next();
      if( cur == obj ) continue;
      else if( collisionDetect(obj, cur) )
        return true;
    }
    return false;
  }
  private boolean paused, waiting, over;
  private int lives, score;
  private int fieldSize;
}

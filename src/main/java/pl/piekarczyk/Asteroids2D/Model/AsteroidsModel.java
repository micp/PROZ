package pl.piekarczyk.Asteroids2D.Model;

import java.util.*;
import java.awt.Rectangle;
import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.ModelObjects.*;
import pl.piekarczyk.Asteroids2D.Model.Input.*;
import pl.piekarczyk.Asteroids2D.Model.Common.*;
import pl.piekarczyk.Asteroids2D.Model.Listener.*;

public class AsteroidsModel {
  /*--- MVP ---*/
  public AsteroidsModel() {
    gameInit = true;
    gamePaused = false;
    enemyActive = false;
    asteroidBeltSize = 5;
    asteroidCount = 0;
    lives = 3;
    score = 0;
    //@OPT scallable?
    fieldSize = 1000;

    volatileKbdState = new boolean[Types.Keys._SIZE.ordinal()];
    safeKbdState = new boolean[Types.Keys._SIZE.ordinal()];
  }
  synchronized public void setKbdState(boolean[] newKbdState) {
    volatileKbdState = newKbdState;
  }
  private void notifyUpdAll() {
    GameState currentGameState = new GameState(this);
    ListIterator<Observer> it = observers.listIterator();
    while(it.hasNext())
      it.next().updAll(currentGameState);
  }
  private boolean[] volatileKbdState;
  private boolean[] safeKbdState;
  private LinkedList<Observer> observers;
  private LinkedList<ModelObject> objectList;
  /*--- REST ---*/
  public void runGame() {
    Thread t = new Thread(new Runnable() {
      public void run() {
	startGame();
      }
    });
    t.start();
  }
  private void startGame() {
    //Game doesn't run until user input
    waitForInput();
    //Add inital objects to game field
    initGame();
    //Game loop
    while(lives != 0) {
      freezeInput();
      step();
    }
    //Check for new record, clean up
    finishGame();
  }

  private void waitForInput() {
    //Await for user input before starting
    //@OPT add small sleep
    //TODO set pause now, make it removable by everything
  }
  private void initGame() {
    //Game initialization
    gameInit = false;

    addPause();
    addQuit();

    //@OPT violates modularity
    addShip();
    addAsteroidBelt();
    tryAddEnemy();
  }
  //@OPT needs to be atomic
  synchronized private void freezeInput() {
    for(int i = 0; i < Types.Keys._SIZE.ordinal(); i++)
      safeKbdState[i] = volatileKbdState[i];
  }
  private void step() {
    ListIterator<ModelObject> it = objectList.listIterator();
    while(it.hasNext()) {
      ModelObject cur = it.next();
      //@OPT event for removal? (maybe in collisions)
      if(cur.isRemovable()) {
	it.remove();
	continue;
      }
      cur.step();
    }
    //@OPT error log framework
    findCollisions();
    //@OPT violates modularity
    tryAddEnemy();
  }
  private void finishGame() {
    //@OPT handle new record
    //Reset game
    //@OPT unsafe?
    curGame = null;
  }

  private void addPause() {
    objectList.add(new PauseObject());
  }
  private void addQuit() {
    objectList.add(new QuitObject());
  }

  private void addShip() {
    PlayerShip ps = new PlayerShip(0, 0);
    while(isColliding(ps))
      step();
    objectList.add(ps);
  }
  private void addAsteroidBelt() {
    addAsteroidBelt(asteroidBeltSize);
  }
  private void addAsteroidBelt(int size) {
    while( size - asteroidCount > 0 )
      addAsteroid();
  }
  private void addAsteroid() {
    addRandom(new Asteroid());
    asteroidCount++;
  }
  private void tryAddEnemy() {
    if(enemyActive) return;
    //@OPT decide whether to add based on current score
    else addRandom(new Enemy());
  }
  private void addRandom(PhysicalObject newObject) {
    Random rnd = new Random();
    while(true) {
      int x = rnd.nextInt(2 * fieldSize) - fieldSize;
      int y = rnd.nextInt(2 * fieldSize) - fieldSize;
      newObject.setXY(x, y);
      if(!isColliding(newObject)) {
	objectList.add(newObject);
	break;
      }
    }
  }
  private void findCollisions() {
    //Skip pause and quit objects
    ListIterator it = objectList.listIterator(2);
    while(it.hasNext()) {
      //@OPT this is wrong, search for others like it
      PhysicalObject cur = (PhysicalObject) it.next();
      ListIterator it2 = it;
      while(it2.hasNext()) {
	PhysicalObject next = (PhysicalObject) it2.next();
	if(collisionDetect(cur, next)) {
	  cur.collide(next.getType());
	  next.collide(cur.getType());
	}
      }
    }
  }
  //@OPT decide how to optimize (asteroids can't collide with asteroids)
  private boolean collisionDetect(ModelObject a, ModelObject b) {
    Rectangle aRec = new Rectangle(a.getX(), a.getY(), 
                                   a.getWidth(), a.getHeight());
    Rectangle bRec = new Rectangle(b.getX(), b.getY(),
                                   b.getWidth(), b.getHeight());
    return aRec.intersects(bRec);
  }
  private boolean isColliding(ModelObject obj) {
    //@OPT change iterator template param (and others like it)
    ListIterator it = objectList.listIterator(2);
    while(it.hasNext()) {
      ModelObject cur = (ModelObject) it.next();
      if( cur == obj ) continue;
      else if( collisionDetect(obj, (ModelObject) it.next()) )
        return true;
    }
    return false;
  }
  public LinkedList<ModelObject> getAll() {
    ListIterator<ModelObject> it = objectList.listIterator();
    LinkedList<ModelObject> ls = new LinkedList<ModelObject>();
    while(it.hasNext())
      ls.add(it.next().copy());
    return ls;
  }
  public void addEvent(InputEvent in) {
    inputQueue.add(in);
  }
  //@OPT friend Keyevent interface?
  public void setKey(Types.Keys k, boolean state) {
    keys[k.ordinal()] = state;
  }
  //@OPT TEST ONLY
  public boolean getKey(Types.Keys k) {
    return keys[k.ordinal()];
  }
  //public void addListener(AsteroidListener l) {
  //  listenerList.add(l);
  //}
  //public void notifyUpdAll() {
  //  ListIterator<AsteroidListener> it =
  //    listenerList.listIterator();
  //  while(it.hasNext())
  //    it.next().updAll();
  //}
  private boolean gameInit, gamePaused;
  private boolean enemyActive;
  private int asteroidBeltSize, asteroidCount;
  private int lives, score;
  private int fieldSize;

  private static AsteroidsModel curGame;

  private Queue<InputEvent> inputQueue;
  private boolean[] keys;

  //public class Viewable {
  //  //@OPT restrict access
  //  public Viewable(PhysicalObject obj) {
  //    x = obj.getX();
  //    y = obj.getY();
  //    rot = obj.getRot();
  //    type = obj.getType();
  //  }
  //  public int x, y, rot;
  //  public Types.PhysicalObjectTypes type;
  //}
  //@OPT implement those
  public void getOjbect(int id) {}

  /*--- OLD ---*/
  //private ModelObject 
  //producePhysicalObject(Types.PhysicalObjectTypes type, int x, int y) {
  //  if(type == Types.PhysicalObjectTypes.SHIP) 
  //    return new PlayerShip(x, y);
  //  else if(type == Types.PhysicalObjectTypes.ASTEROID) 
  //    return new Asteroid(x, y);
  //  else if(type == Types.PhysicalObjectTypes.ENEMY) 
  //    return new Enemy(x, y);
  //}
}

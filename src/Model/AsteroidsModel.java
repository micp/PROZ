public class AsteroidsModel {
  public static AsteroidsModel getAsteroidsModel() {
    if(curGame == null)
      curGame = new AsteroidsModel();
    return curGame;
  }
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
      parseQueue();
      step();
      clearInput();
    }
    //Check for new record, clean up
    finishGame();
  }

  private void waitForInput() {
    //Await for user input before starting
    while(inputQueue.isEmpty());
  }
  private void initGame() {
    //Game initialization
    gameInit = false;
    clearInput();

    //@TODO implement modelObjects
    addPause();
    addQuit();

    //@OPT violates modularity
    addShip();
    addAsteroidBelt();
    tryAddEnemy();

    //@OPT needs to be atomic
    inputQueue.removeAll();
  }
  //@OPT needs to be atomic
  private void parseQueue() {
    //@OPT limit time spent here?
    while(!inputQueue.isEmpty())
      //@TODO implement keys
      inputQueue.remove().reaction();
  }
  private void step() {
    ListIterator it = objectList.listIterator();
    while(it.hasNext())
      it.next().step();
    //@OPT error log framework
    findCollisions();
    //@OPT violates modularity
    tryAddEnemy();
  }
  private void clearInput() {
    for(int i = 0; i < Keys._size; ++i)
      keys[i] = false;
  }
  private void finishGame() {
    //@OPT handle new record
    //Reset game
    //@OPT unsafe?
    curGame = null;
  }

  private void addPause() {
    objectList.add(new pauseObject());
  }
  private void addQuit() {
    objectList.add(new quitObject());
  }

  private void addShip() {
    PlayerShip ps = new PlayerShip(0, 0);
    while(colliding(ps))
      step();
    objectList.add(ps);
  }
  private void addAsteroidBelt(int size = asteroidBeltSize) {
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
    while(true) {
      int x = random(-fieldSize, fieldSize);
      int y = random(-fieldSize, fieldSize);
      newObject.setXY(x, y);
      if(!colliding(newObject)) {
	objectList.add(newObject);
	break;
      }
    }
  }
  private void findCollisions() {
    //Skip pause and quit objects
    ListIterator it = objectList.listIterator(2);
    while(it.hasNext()) {
      PhysicalObject cur = it.next();
      ListIterator it2 = it;
      while(it2.hasNext()) {
	PhysicalObject next = it2.next();
	if(collisionDetect(cur, next) {
	  cur.collide(next.getType());
	  next.collide(cur.getType());
	}
      }
    }
  }
  //@OPT decide how to optimize (asteroids can't collide with asteroids)
  private void collisionDetect(modelObject a, modelObject b) {
    Rectangle aRec = new Rectangle(a.getX(), a.getY(), 
                                   a.getWidth(), a.getHeigth());
    Rectangle bRec = new Rectangle(b.getX(), b.getY(),
                                   b.getWidth(), b.getHeigth());
    return aRec.intersects(bRec)
  }
  private void colliding(modelObject obj) {
    ListIterator it = objectList.ListIterator(2);
    while(it.hasNext())
      if( collisionDetect(obj, it.next()) )
        return true;
    return false;
  }
  public LinkedList<Viewable> getAll() {
    ListIterator it = objectList.ListIterator();
    LinkedList<Viewable> ls = new LinkedList<Viewable>();
    while(it.hasNext())
      ls.add(new Viewable(it.next()));
    return ls;
  }
  public void addToQueue(Input in) {
    inputQueue.add(in);
  }
  private GameModel() {
    gameInit = true;
    gamePaused = false;
    enemyActive = false;
    asteroidBeltSize = 5;
    asteroidCount = 0;
    lives = 3;
    score = 0;
    //@OPT scallable?
    fieldSize = 1000;

    inputQueue = new ArrayDeque<Input>();
  }
  private boolean gameInit, gamePaused;
  private boolean enemyActive;
  private int asteroidBeltSize, asteroidCount;
  private int lives, score;
  private int fieldSize;

  private AsteroidsModel curGame;

  private Queue<Input> inputQueue;
  private LinkedList<ModelObject> objectList;
  private boolean[Keys._size] keys;

  private enum Keys { up, left, right, space, q, p, _size}
  private enum PhysicalObjectTypes { Ship, Asteroid, Enemy }

  private abstract class ModelObject {
    private int id;
    public abstract void step();
    public abstract void addObservator();
  }
  private abstract class PhysicalObject extends ModelObject {
    private int x, y, rot;
    private PhysicalObjectTypes type;
    public abstract physicalObject(int x, int y);
    public abstract void collide();
    public abstract int getX();
    public abstract int getY();
    public abstract int getRot();
  }
  private interface Input {
    void reaction();
  }
  public class KeyUP implements Input {
    public reaction() {
      ZZ
  public abstract class Observator() {
    public void created();
    public void updated();
    public void destroyed();
  }
  public class Viewable {
    //@OPT restrict access
    public Viewable(PhysicalObject obj) {
      x = obj.getX();
      y = obj.getY();
      rot = obj.getRot();
      type = obj.getType();
    }
    public int x, y, rot;
    public PhysicalObjectTypes type;
  }
  //@OPT implement those
  public void getOjbect(int id) {}

  /*--- OLD ---*/
  private modelObject 
  producePhysicalObject(PhysicalObjectTypes type, int x, int y) {
    if(type == Ship) return new Ship(x, y);
    if(type == Asteroid) return new Asteroid(x, y);
    if(type == Enemy) return new Enemy(x, y);
  }}

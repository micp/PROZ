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
    //@TODO gotta create queue somewhere
    while(inputQueue.isEmpty());
  }
  private void initGame() {
    //Game initialization
    gameInit = false;
    clearInput();

    //@TODO implement modelObject interface (+step)(+collide)
    //@TODO implement modelObjects
    addPause();
    addQuit();

    addShip(playerShipPos.Middle);
    for(int i = 0; i < 10; ++i)
      tryAddAsteroid();
    tryAddEnemy();

    inputQueue.removeAll();
  }
  private void parseQueue() {
    //@TODO limit time spent here?
    while(!inputQueue.isEmpty())
      //@TODO implement key interface (+action)
      //@TODO implement keys
      inputQueue.remove().action();
  }
  private void step() {
    Iterator it = objectList.iterator();
    while(it.hasNext())
      it.next().step();
    //@TODO collision detection goes here
  }
  private void clearInput() {
    for(int i = 0; i < Keys._size; ++i)
      keys[i] = false;
  }
  //@TODO implement this
  private void finishGame() {}

  private void addPause() {
    objectList.add(pauseObject);
  }
  private void addQuit() {
    objectList.add(quitObject);
  }

  private void addShip(PlayerShipPos pos) {
    if(pos == playerShipPos.Middle)
      objectList.add(new playerShip(0, 0));
    else if(pos == playerShipPos.Random)
      addRandom(ObjectTypes.Ship);
  }
  private void tryAddAsteroid() {
    if(asteroidCount >= 10) return;
    else addRandom(ObjectTypes.Asteroid);
  }
  private void tryAddEnemy() {
    if(enemyActive) return;
    //@TODO decide whether to add based on current score
    else addRandom(ObjectTypes.Enemy);
  }
  private void addRandom(ObjectTypes type) {
    while(true) {
      //@TODO set field size
      int x = random(-fieldSize, fieldSize);
      int y = random(-fieldSize, fieldSize);
      modelObject tempObject = produceObject(type, x, y);
      if(!colliding(tempObject)) {
	objectList.add(tempObject);
	break;
      }
    }
  }
  private modelObject produceObject(ObjectTypes type, int x, int y) {
    if(type == Ship) return new Ship(x, y);
    if(type == Asteroid) return new Asteroid(x, y);
    if(type == Enemy) return new Enemy(x, y);
  }
  //needs keypresses
  //  other file
  private GameModel() {
    gameInit = true;
    gamePaused = false;
    enemyActive = false;
    asteroidCount = 0;
    lives = 3;
    score = 0;
  }
  private boolean gameInit, gamePaused;
  private boolean enemyActive;
  private int asteroidCount;
  private int lives, score;

  private AsteroidsModel curGame;

  private enum PlayerShipPos { Middle, Random }
  private enum ObjectTypes { Ship, Asteroid, Enemy }
  private enum Keys { up, left, right, space, q, p , _size}

  private Queue<input> inputQueue;
  private LinkedList<modelObject> objectList;
  private boolean[Keys._size] keys;

  //@TODO implement those
  private interface modelObject {}
  private void collisionDetect(modelObject a, modelObject b) {}
  private void colliding(modelObject) {}
  public void getOjbect(int id) {}
  public void getAll() {}
  private void pause() {}
  private void quit() {}
}

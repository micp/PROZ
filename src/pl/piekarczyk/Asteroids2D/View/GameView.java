public class AsteroidsView {
  public static AsteroidsView getAsteroidsView {
    if(curView == null)
      curView = new AsteroidsView();
    return curView;
  }
  private static AsteroidsView curView;
  public void runView() {
    Thread t = new Thread(new Runnable() {
      public void run() {
	startView();
      }
    });
    t.start();
  }
  private void startView() {
    //Create and connect to model object
    startModel();

    //Run untill closed.
    while(!requestedClose)
      parseEvents();

    //Clean up.
    close();
  }
  private void startModel() {
    observedGame = AsteroidsModel.getAsteroidsModel();
    observedGame.addListener(this);
    observedGame.runGame();
  }
  public void requestClose() {
    requestedClose = true;
  }
  private void parseEvents() {
    evenQueue.poll(1L, TimeUnit.SECONDS).execute();
  }
  private void close() {
    gamePanel.removeAll();
    curGame = null;
    curView = null;
  }
  public void setPanel(Jpanel newGamePanel) {
    gamePanel = newGamePanel;
    gamePanel.addKeyListener(new AsteroidKeyListener());
  }

  //private void notifyKeyPress() {}
  private abstract class ViewPhysicalObject extends JPanel {
    public ViewObject(int nx, int ny, int nRot) {
      x = nx;
      y = ny;
      rot = nRot;
    }
    private int x, y;
    private int rot;
  }
  private class ViewPhysicalPlayerShip extends ViewPhysicalObject {
    //@OPT better path detection
    private final static String modelFile = "data/pic/spitfire.jpg";
    private final static Image image;
    public ViewObjectPlayerShip(int nx, int ny, int nRot) {
      super.(nx, ny, nRot);
      ImageIcon ii = new ImageIcon(this.getClass().getResource(modelFile));
      image = ii.getImage();
    }
    public void paint(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      //@OPT rotate
      g2d.drawImage(image, x, y, null):
    }
  }
  private class ViewPhysicalAsteroid extends ViewPhysicalObject {
    //@OPT better path detection
    private final static String modelFile = "data/pic/asteroid.jpg";
    private final static Image image;
    public ViewObjectAsteroid(int nx, int ny, int nRot) {
      super.(nx, ny, nRot);
      ImageIcon ii = new ImageIcon(this.getClass().getResource(modelFile));
      image = ii.getImage();
    }
    public void paint(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      //@OPT rotate
      g2d.drawImage(image, x, y, null):
    }
  }
  private class ViewPhysicalEnemy extends ViewPhysicalObject {
    //@OPT better path detection
    private final static String modelFile = "data/pic/enemy.jpg";
    private final static Image image;
    public ViewObjectEnemy(int nx, int ny, int nRot) {
      super.(nx, ny, nRot);
      ImageIcon ii = new ImageIcon(this.getClass().getResource(modelFile));
      image = ii.getImage();
    }
    public void paint(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      //@OPT rotate
      g2d.drawImage(image, x, y, null):
    }
  }

  private ViewObject createViewObject(ModelObject v) {
    //@OPT fix this shit nyuaka
    if(v.getType() = PhysicalObjectTypes.PlayerShip)
      return new ViewObjectPlayerShip(v.getX(), v.getY(), v.getRot());
    if(v.getType() = PhysicalObjectTypes.Asteroid)
      return new ViewObjectAsteroid(v.getX(), v.getY(), v.getRot());
    if(v.getType() = PhysicalObjectTypes.Enemy)
      return new ViewObjectEnemy(v.getX(), v.getY(), v.getRot());
  }

  private AsteroidsModel observedGame;
  private BlockingQueue<Event> eventQueue;
  private JPanel gamePanel;
  private LinkedList<ViewObject> objectList;

  //Event handling
  public void updAll() {
    eventQueue.offer(new UpdAllEvent());
  }
  //@OPT
  private void addObject() {}
  private void updObject() {}
  private void delObject() {}
  private void addAll() {}
  private void delAll() {}
  private void displayWelcome() {}
  private void displayGameOver() {}
  private void displayPause() {}
  private void displayRecord() {}

  //needs observators
  //  other file
  private interface Event {
    public void execute();
  }
  private class UpdAllEvent implements Event {
    public void execute() {
      LinkedList<ModelObject> newMap = observedGame.getAll();
      gamePanel.removeAll();
      objectList.removeAll();

      ListIterator<ModelObject> it = newMap.listIterator():
      while(it.hasNext()) {
	objectList.add(createViewOjbect(it.next());
      }
      ListIterator<ViewObject> it2 = objectList.listIterator();
      while(it2.hasNext()) {
	gamePanel.add(it2.next());
      }
    }
  }
  private class AsteroidKeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent ke) {
      switch (ke.getKeyCode()) {
	case KeyEvent.VK_UP: observedGame.addEvent(new KeyUP());
	case KeyEvent.VK_LEFT: observedGame.addEvent(new KeyLeft());
	case KeyEvent.VK_RIGHT: observedGame.addEvent(new KeyRight());
	case KeyEvent.VK_SPACE: observedGame.addEvent(new KeySpace());
	case KeyEvent.VK_P: observedGame.addEvent(new KeyP());
	case KeyEvent.VK_Q: observedGame.addEvent(new KeyQ());
      }
    }
  }
}

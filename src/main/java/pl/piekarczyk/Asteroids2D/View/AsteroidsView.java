package pl.piekarczyk.Asteroids2D.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.GUI.*;
import pl.piekarczyk.Asteroids2D.View.Viewable.*;
import pl.piekarczyk.Asteroids2D.Presenter.*;

public class AsteroidsView extends JPanel implements GameView {
  /*--- MVP ---*/
  public AsteroidsView() {
    this.setFocusable(true);
    this.addKeyListener(new AsteroidKeyListener(this));
    this.setBackground(Color.black);
    gamePresenter = new AsteroidsPresenter(this);
    requestedClose = false;

    gameScore = 0;
    gameLives = 0;

    kbdState = new boolean[Types.Keys._SIZE.ordinal()];
    viewableList = new LinkedList<ViewObject>();
  }
  synchronized public void press(Types.Keys key) {
    kbdState[key.ordinal()] = true;
    gamePresenter.updKbdState();
  }
  synchronized public void release(Types.Keys key) {
    kbdState[key.ordinal()] = false;
    gamePresenter.updKbdState();
  }
  synchronized public boolean[] getKbdState() {
    boolean[] stateCopy = new boolean[Types.Keys._SIZE.ordinal()];
    //@OPT optimize array cloning (in model also)
    for(int i = 0; i < Types.Keys._SIZE.ordinal(); ++i)
      stateCopy[i] = kbdState[i];
    return stateCopy;
  }
  public void forceRepaint() {
    this.repaint();
  }
  public void showScoreBoard() {
    requestedClose = true;
    Top10.getTop10().show();
  }
  public void clearScreen() {
    viewableList.clear();
  }
  public void drawScore(int score) {
    gameScore = score;
  }
  public void drawLives(int lives) {
    gameLives = lives;
  }
  public void drawPaused() {
  }
  public void drawMissile(double x, double y, double rot) {
    viewableList.add(new Missile(x, y, rot));
  }
  public void drawPlayerShip(double x, double y, double rot) {
    viewableList.add(new PlayerShip(x, y, rot));
  }
  public void drawAsteroid(double x, double y, double rot) {
    viewableList.add(new Asteroid(x, y, rot));
  }
  public void drawTinyAsteroid(double x, double y, double rot) {
    viewableList.add(new TinyAsteroid(x, y, rot));
  }
  public void drawEnemy(double x, double y, double rot) {
    viewableList.add(new Enemy(x, y, rot));
  }
  public void drawTitle() {
  }
  public void clearPhysicalObject() {
  }
  private class AsteroidKeyListener extends KeyAdapter {
    public AsteroidKeyListener(AsteroidsView relate) {
      relatedView = relate;
    }
    public void keyPressed(KeyEvent ke) {
      int k = ke.getKeyCode();
      if(k == KeyEvent.VK_UP) relatedView.press(Types.Keys.UP);
      else if(k == KeyEvent.VK_LEFT) relatedView.press(Types.Keys.LEFT);
      else if(k == KeyEvent.VK_RIGHT) relatedView.press(Types.Keys.RIGHT);
      else if(k == KeyEvent.VK_SPACE) relatedView.press(Types.Keys.SPACE);
      else if(k == KeyEvent.VK_P) relatedView.press(Types.Keys.P);
      else if(k == KeyEvent.VK_Q) relatedView.press(Types.Keys.Q);
    }
    public void keyReleased(KeyEvent ke) {
      int k = ke.getKeyCode();
      if(k == KeyEvent.VK_UP) relatedView.release(Types.Keys.UP);
      else if(k == KeyEvent.VK_LEFT) relatedView.release(Types.Keys.LEFT);
      else if(k == KeyEvent.VK_RIGHT) relatedView.release(Types.Keys.RIGHT);
      else if(k == KeyEvent.VK_SPACE) relatedView.release(Types.Keys.SPACE);
      else if(k == KeyEvent.VK_P) relatedView.release(Types.Keys.P);
      else if(k == KeyEvent.VK_Q) relatedView.release(Types.Keys.Q);
    }
    private AsteroidsView relatedView;
  }
  private AsteroidsPresenter gamePresenter;
  private int gameScore, gameLives;
  private boolean[] kbdState;
  /*--- REST ---*/
  synchronized public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Color.white);
    g.drawString("Score: " + Integer.toString(gameScore), 10, 20);
    g.drawString("Lives: " + Integer.toString(gameLives), 75, 20);
    ListIterator<ViewObject> it = viewableList.listIterator();
    while(it.hasNext()) {
      ViewObject cur = it.next();
      g2d.drawImage(cur.getImage(), cur.getX(), cur.getY(), null);
    }

    g.dispose();
  }
  //public static AsteroidsView getAsteroidsView() {
  //  if(curView == null)
  //    curView = new AsteroidsView();
  //  return curView;
  //}
  //private static AsteroidsView curView;
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
    //startModel();

    //Run until closed.
    while(!requestedClose)
      try{gamePresenter.getGameState();}catch(Exception ign){}
      //parseEvents();

    //Clean up.
    //close();
  }
  //private void startModel() {
  //  observedGame = AsteroidsModel.getAsteroidsModel();
  //  observedGame.addListener(this);
  //  observedGame.runGame();
  //}
  public void requestClose() {
    requestedClose = true;
  }
  //private void parseEvents() {
  //  //@TODO FIXME CHANGE THIS!!
  //  try{eventQueue.poll(1L, TimeUnit.SECONDS).execute();}
  //  catch(Exception ignore) {}
  //}
  //private void close() {
  //  gamePanel.removeAll();
  //  observedGame = null;
  //  curView = null;
  //}
  //public void setPanel(JPanel newGamePanel) {
  //  gamePanel = newGamePanel;
  //  gamePanel.addKeyListener(new AsteroidKeyListener());
  //}

  //private ViewObject createViewObject(ModelObject v) {
  //  //@OPT fix this shit nyuaka
  //  if(v.getType() == Types.ObjectTypes.SHIP)
  //    return new ViewPhysicalPlayerShip(v.getX(), v.getY(), v.getRot());
  //  else if(v.getType() == Types.ObjectTypes.ASTEROID)
  //    return new ViewPhysicalAsteroid(v.getX(), v.getY(), v.getRot());
  //  else if(v.getType() == Types.ObjectTypes.ENEMY)
  //    return new ViewPhysicalEnemy(v.getX(), v.getY(), v.getRot());
  //  else return null;
  //}

  //private AsteroidsModel observedGame;
  //private BlockingQueue<Event> eventQueue;
  private LinkedList<ViewObject> viewableList;
  private boolean requestedClose;

  //Event handling
  //public void updAll() {
  //  eventQueue.offer(new UpdAllEvent());
  //}
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
  //private class UpdAllEvent implements Event {
  //  public void execute() {
  //    LinkedList<ModelObject> newMap = observedGame.getAll();
  //    gamePanel.removeAll();
  //    objectList.clear();

  //    ListIterator<ModelObject> it = newMap.listIterator();
  //    while(it.hasNext()) {
  //      objectList.add(createViewObject(it.next()));
  //    }
  //    ListIterator<ViewObject> it2 = objectList.listIterator();
  //    while(it2.hasNext()) {
  //      gamePanel.add(it2.next());
  //    }
  //  }
  //}
}


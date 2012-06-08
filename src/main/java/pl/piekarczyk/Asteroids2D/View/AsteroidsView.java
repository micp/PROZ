package pl.piekarczyk.Asteroids2D.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;
//import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;
//import pl.piekarczyk.Asteroids2D.Model.ModelObjects.*;
//import pl.piekarczyk.Asteroids2D.Model.Input.Keys.*;
//import pl.piekarczyk.Asteroids2D.Model.Listener.*;
import pl.piekarczyk.Asteroids2D.View.View;
import pl.piekarczyk.Asteroids2D.View.ViewableObject.*;
import pl.piekarczyk.Asteroids2D.Presenter.AsteroidsPresenter;

public class AsteroidsView implements View {
  /*--- MVP ---*/
  public AsteroidsView(JPanel gameField) {
    gamePanel = gameField;
    gamePanel.addKeyListener(new AsteroidKeyListener(this));
    gamePresenter = new AsteroidsPresenter(this);
    requestedClose = false;
    kbdState = new boolean[Types.Keys._SIZE.ordinal()];
  }
  public void drawScore(int score){}
  public void drawLives(int lives){}
  public void drawPaused(){}
  public void hidePaused(){}
  public void drawShip(int x, int y){}
  public void drawAsteroid(int x, int y){}
  public void drawEnemy(int x, int y){}
  public void drawTitle(){}
  public void hideTitle(){}
  public void clearPhysicalObject(){}
  synchronized public void press(Types.Keys key) {
    kbdState[key.ordinal()] = true;
  }
  synchronized public void release(Types.Keys key) {
    kbdState[key.ordinal()] = false;
  }
  synchronized public boolean[] getKbdState() {
    boolean[] stateCopy = new boolean[Types.Keys._SIZE.ordinal()];
    //@OPT optimize array cloning (in model also)
    for(int i = 0; i < Types.Keys._SIZE.ordinal(); ++i)
      stateCopy[i] = kbdState[i];
    return stateCopy;
  }
  private class AsteroidKeyListener extends KeyAdapter {
    public AsteroidKeyListener(AsteroidsView relate) {
      relatedView = relate;
    }
    public void keyPressed(KeyEvent ke) {
      switch (ke.getKeyCode()) {
	case KeyEvent.VK_UP: relatedView.press(Types.Keys.UP);
	case KeyEvent.VK_LEFT: relatedView.press(Types.Keys.LEFT);
	case KeyEvent.VK_RIGHT: relatedView.press(Types.Keys.RIGHT);
	case KeyEvent.VK_SPACE: relatedView.press(Types.Keys.SPACE);
	case KeyEvent.VK_P: relatedView.press(Types.Keys.P);
	case KeyEvent.VK_Q: relatedView.press(Types.Keys.Q);
      }
    }
    public void keyReleased(KeyEvent ke) {
      switch (ke.getKeyCode()) {
	case KeyEvent.VK_UP: relatedView.release(Types.Keys.UP);
	case KeyEvent.VK_LEFT: relatedView.release(Types.Keys.LEFT);
	case KeyEvent.VK_RIGHT: relatedView.release(Types.Keys.RIGHT);
	case KeyEvent.VK_SPACE: relatedView.release(Types.Keys.SPACE);
	case KeyEvent.VK_P: relatedView.release(Types.Keys.P);
	case KeyEvent.VK_Q: relatedView.release(Types.Keys.Q);
      }
    }
    private AsteroidsView relatedView;
  }
  private AsteroidsPresenter gamePresenter;
  private boolean[] kbdState;
  /*--- REST ---*/
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
      parseEvents();

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
  private void parseEvents() {
    //@TODO FIXME CHANGE THIS!!
    try{eventQueue.poll(1L, TimeUnit.SECONDS).execute();}
    catch(Exception ignore) {}
  }
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
  private BlockingQueue<Event> eventQueue;
  private JPanel gamePanel;
  private LinkedList<ViewObject> objectList;
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


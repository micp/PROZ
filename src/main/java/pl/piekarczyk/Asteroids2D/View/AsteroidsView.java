package pl.piekarczyk.Asteroids2D.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;
import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.Common.*;
import pl.piekarczyk.Asteroids2D.Model.ModelObjects.*;
import pl.piekarczyk.Asteroids2D.Model.Input.Keys.*;
import pl.piekarczyk.Asteroids2D.Model.Listener.*;
import pl.piekarczyk.Asteroids2D.View.ViewableObject.*;

public class AsteroidsView implements AsteroidListener {
  public static AsteroidsView getAsteroidsView() {
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
  private AsteroidsView() {
    requestedClose = false;
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
    //@TODO FIXME CHANGE THIS!!
    try{eventQueue.poll(1L, TimeUnit.SECONDS).execute();}
    catch(Exception ignore) {}
  }
  private void close() {
    gamePanel.removeAll();
    observedGame = null;
    curView = null;
  }
  public void setPanel(JPanel newGamePanel) {
    gamePanel = newGamePanel;
    gamePanel.addKeyListener(new AsteroidKeyListener());
  }

  private ViewObject createViewObject(ModelObject v) {
    //@OPT fix this shit nyuaka
    if(v.getType() == Types.ObjectTypes.SHIP)
      return new ViewPhysicalPlayerShip(v.getX(), v.getY(), v.getRot());
    else if(v.getType() == Types.ObjectTypes.ASTEROID)
      return new ViewPhysicalAsteroid(v.getX(), v.getY(), v.getRot());
    else if(v.getType() == Types.ObjectTypes.ENEMY)
      return new ViewPhysicalEnemy(v.getX(), v.getY(), v.getRot());
    else return null;
  }

  private AsteroidsModel observedGame;
  private BlockingQueue<Event> eventQueue;
  private JPanel gamePanel;
  private LinkedList<ViewObject> objectList;
  private boolean requestedClose;

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
      objectList.clear();

      ListIterator<ModelObject> it = newMap.listIterator();
      while(it.hasNext()) {
	objectList.add(createViewObject(it.next()));
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

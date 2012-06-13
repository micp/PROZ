package pl.piekarczyk.Asteroids2D.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.concurrent.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.GUI.*;
import pl.piekarczyk.Asteroids2D.View.Viewable.*;
import pl.piekarczyk.Asteroids2D.Presenter.*;

/**
 * An implementation of the game view. Uses swing and awt to display the 
 * game state to the player.
 */
public class AsteroidsView extends JPanel implements GameView {
  /**
   * Sets up basic variables. Doesn't create a presenter or a game, nor does it
   * start the game. See {@link #runView()}
   */
  public AsteroidsView() {
    setFocusable(true);
    setBackground(Color.black);
    requestedClose = false;
    setDoubleBuffered(true);

    gameScore = 0;
    gameLives = 0;

    kbdState = new boolean[Types.Keys._SIZE.ordinal()];
    viewableList = new LinkedList<ViewObject>();
  }
  /**
   * Used by KeyListener to register a pressed key with the view.
   */
  public void press(Types.Keys key) {
    synchronized(kbdState) {
      kbdState[key.ordinal()] = true;
      gamePresenter.updKbdState();
    }
  }
  /**
   * Used by KeyListener to register a released key with the view.
   */
  public void release(Types.Keys key) {
    synchronized(kbdState) {
      kbdState[key.ordinal()] = false;
      gamePresenter.updKbdState();
    }
  }
  /**
   * Allows the presenter to retrieve the latest keyboard state. Creates
   * a copy of the current keyboard state.
   * @return A reference to a copy of the keyboard state.
   */
  public boolean[] getKbdState() {
    synchronized(kbdState) {
      boolean[] stateCopy = new boolean[Types.Keys._SIZE.ordinal()];
      for(int i = 0; i < Types.Keys._SIZE.ordinal(); ++i)
	stateCopy[i] = kbdState[i];
      return stateCopy;
    }
  }
  public void forceRepaint() {
    repaint();
  }
  public void requestClose() {
    gamePresenter.close();
    requestedClose = true;
  }
  public void showScoreBoard() {
    Top10.getTop10().show();
  }
  public void clearScreen() {
    synchronized(viewableList) {
      viewableList.clear();
    }
  }
  public void drawScore(int score) {
    gameScore = score;
  }
  public void drawLives(int lives) {
    gameLives = lives;
  }
  public void drawMissile(double x, double y, double rot) {
    synchronized(viewableList) {
      viewableList.add(new Missile(x, y, rot));
    }
  }
  public void drawPlayerShip(double x, double y, double rot) {
    synchronized(viewableList) {
      viewableList.add(new PlayerShip(x, y, rot));
    }
  }
  public void drawAsteroid(double x, double y, double rot) {
    synchronized(viewableList) {
      viewableList.add(new Asteroid(x, y, rot));
    }
  }
  public void drawTinyAsteroid(double x, double y, double rot) {
    synchronized(viewableList) {
      viewableList.add(new TinyAsteroid(x, y, rot));
    }
  }
  public void drawEnemy(double x, double y, double rot) {
    synchronized(viewableList) {
      viewableList.add(new Enemy(x, y, rot));
    }
  }
  /**
   * Overridden swing paintComponent method. Should only be invoked by the
   * EDT thread.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Color.white);
    g.drawString("Score: " + Integer.toString(gameScore), 10, 20);
    g.drawString("Lives: " + Integer.toString(gameLives), 75, 20);

    synchronized(viewableList) {
      ListIterator<ViewObject> it = viewableList.listIterator();
      while(it.hasNext()) {
	ViewObject cur = it.next();
	g2d.rotate(Math.toRadians(-cur.getRot()), 
	    cur.getX()+cur.getHeight()/2,
	    cur.getY()+cur.getWidth()/2);
	g2d.drawImage(cur.getImage(), cur.getX(), cur.getY(), null);
	g2d.rotate(Math.toRadians(cur.getRot()), 
	    cur.getX()+cur.getHeight()/2,
	    cur.getY()+cur.getWidth()/2);
      }
    }

    g.dispose();
  }
  /**
   * Actually starts the view. Creates a forked thread to run the view in.
   * Also creates a presenter and connects to it. Starts the view loop.
   */
  public void runView() {
    Thread t = new Thread(new Runnable() {
      public void run() {
	startView();
      }
    });
    t.start();
  }
  private class AsteroidKeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent ke) {
      int k = ke.getKeyCode();
      if(k == KeyEvent.VK_UP) press(Types.Keys.UP);
      else if(k == KeyEvent.VK_LEFT) press(Types.Keys.LEFT);
      else if(k == KeyEvent.VK_RIGHT) press(Types.Keys.RIGHT);
      else if(k == KeyEvent.VK_SPACE) press(Types.Keys.SPACE);
      else if(k == KeyEvent.VK_Q) press(Types.Keys.Q);
    }
    public void keyReleased(KeyEvent ke) {
      int k = ke.getKeyCode();
      if(k == KeyEvent.VK_UP) release(Types.Keys.UP);
      else if(k == KeyEvent.VK_LEFT) release(Types.Keys.LEFT);
      else if(k == KeyEvent.VK_RIGHT) release(Types.Keys.RIGHT);
      else if(k == KeyEvent.VK_SPACE) release(Types.Keys.SPACE);
      else if(k == KeyEvent.VK_Q) release(Types.Keys.Q);
    }
  }
  private void startView() {
    addKeyListener(new AsteroidKeyListener());
    gamePresenter = new AsteroidsPresenter(this);
    gamePresenter.startPresenter();

    while(!requestedClose) {
      try {
	gamePresenter.getGameState();
      } catch(InterruptedException e) {
      requestClose();
      }
    }
  }
  private AsteroidsPresenter gamePresenter;
  private int gameScore, gameLives;
  private boolean[] kbdState;
  private LinkedList<ViewObject> viewableList;
  private boolean requestedClose;

}

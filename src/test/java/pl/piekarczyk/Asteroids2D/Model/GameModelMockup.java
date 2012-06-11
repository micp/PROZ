package pl.piekarczyk.Asteroids2D.Test.Model;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.Common.*;
import pl.piekarczyk.Asteroids2D.Model.ModelObjects.*;

public class GameModelMockup implements GameModel {
  public GameModelMockup() {
    list = new LinkedList<PhysicalObject>();
    list.add(new Asteroid(this));
  }
  public void setKbdState(boolean[] newKbdState) {
    /* unnecessary */
  }
  public void stopGame() {
    /* unnecessary */
  }
  public void decLives() {
    /* unnecessary */
  }
  public int getLives() {
    return 3;
  }
  public int getScore() {
    return 100;
  }
  public LinkedList<PhysicalObject> getList() {
    return list;
  }
  public boolean isPaused() {
    return false;
  }
  public boolean isOver() {
    return false;
  }
  public boolean isWaiting() {
    return false;
  }
  public void unPause() {
    /* unnecessary */
  }
  public void pause() {
    /* unnecessary */
  }
  public void runGame() {
    /* unnecessary */
  }
  public boolean getKeyState(Types.Keys k) {
    /* unnecessary */
    return false;
  }
  private LinkedList<PhysicalObject> list;
}

package pl.piekarczyk.Asteroids2D.Test.Model.ModelObjects;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.ModelObjects.*;

public class ModelObjectsGameModelMockup implements GameModel {
  public ModelObjectsGameModelMockup() {
    lives = 5;
    gameStopped = false;
    gamePaused = false;
    keyState = new boolean[Types.Keys._SIZE.ordinal()];
  }
  public void setKbdState(boolean[] newKbdState) {}
  public void stopGame() { gameStopped = true; }
  public void decLives() { lives--; }
  public boolean isPaused() { return gamePaused; }
  public boolean isWaiting() { return false; }
  public boolean isOver() { return false; }
  public void unPause() { gamePaused = false; }
  public void pause() { gamePaused = true; }
  public void runGame() {}
  public boolean getKeyState(Types.Keys k) { return keyState[k.ordinal()]; }
  public int lives;
  public boolean gameStopped, gamePaused;
  public boolean[] keyState;
  public LinkedList<PhysicalObject> getList() { return null; }
  public int getScore() { return 0; }
  public int getLives() { return 0; }
}

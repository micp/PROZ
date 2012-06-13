package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

public class AsteroidProducerController extends ProducerControllerHelper {
  public AsteroidProducerController(GameModel thisGame) {
    this(4*4*4, thisGame);
  }
  public AsteroidProducerController(int beltSize, GameModel thisGame) {
    super(thisGame);
    asteroidBeltSize = beltSize;
  }
  public void manage() {
    if(count > 0) return;
    while( asteroidBeltSize - count > 0 ) {
      Random rnd = new Random();
      Asteroid na = new Asteroid(game);
      na.setController(this);
      na.setDirection(rnd.nextInt());
      na.setRot(na.getDirection());
      addRandom(na);
      incCount();
    }
  }
  public void close() {}
  public void notifyCreated() {
    incCount();
  }
  public void notifyDestroyed() {
    decCount();
  }
  private void incCount() { 
    count++;
  }
  private void decCount() { 
    count--;
  }
  private void setCount(int i) { 
    count = i; 
  }
  private int count;
  private int asteroidBeltSize;
}

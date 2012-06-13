package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

public class EnemyProducerController extends ProducerControllerHelper {
  public EnemyProducerController(GameModel thisGame) {
    super(thisGame);
  }
  public void manage() {
    if(count > 0) return;

    Random rnd = new Random();
    if(rnd.nextInt(1000) > 990) {
      Enemy e = new Enemy(game);
      e.setController(this);
      e.setDirection(90);
      addRandom(e);
      incCount();
    }
  }
  public void close() {}
  public void notifyCreated() {}
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
}

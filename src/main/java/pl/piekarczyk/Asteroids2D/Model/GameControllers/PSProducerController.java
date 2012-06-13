package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;

public class PSProducerController 
extends AsteroidsController implements GameProducerController {
  public PSProducerController(GameModel thisGame) {
    super(thisGame);
  }
  public void manage() {
    if(count > 0) return;
    else {
      PlayerShip ps = new PlayerShip(game);
      if(game.isColliding(ps)) return;
      else {
	ps.setController(this);
	game.getFutureList().add(ps);
	incCount();
      }
    }
  }
  public void close() {
    setCount(0);
  }
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

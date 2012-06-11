package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;
import pl.piekarczyk.Asteroids2D.Common.Types;

public class EnemyProducerController extends ProducerControllerHelper {
  public EnemyProducerController(GameModel thisGame) {
    super(thisGame);
  }
  public void manage() {
    if(Enemy.count() > 0) return;
    Random rnd = new Random();
    if(rnd.nextInt(1000) > 1) {
      addRandom(new Enemy(game));
      Enemy.incCount();
    }
  }
}

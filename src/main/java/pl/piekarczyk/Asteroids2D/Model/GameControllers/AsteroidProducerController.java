package pl.piekarczyk.Asteroids2D.Model.GameControllers;

import java.util.*;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.GameObjects.*;
import pl.piekarczyk.Asteroids2D.Common.Types;

public class AsteroidProducerController extends ProducerControllerHelper {
  public AsteroidProducerController(GameModel thisGame) {
    this(4, thisGame);
  }
  public AsteroidProducerController(int beltSize, GameModel thisGame) {
    super(thisGame);
    asteroidBeltSize = beltSize;
  }
  public void manage() {
    if(Asteroid.count() > 0) return;
    while( asteroidBeltSize - Asteroid.count() > 0 ) {
      Random rnd = new Random();
      Asteroid na = new Asteroid(game);
      na.setDirection(rnd.nextInt());
      addRandom(na);
      Asteroid.incCount();
    }
  }
  private int asteroidBeltSize;
}

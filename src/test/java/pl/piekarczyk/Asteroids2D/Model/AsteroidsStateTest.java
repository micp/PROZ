import org.junit.*;
import static org.junit.Assert.*;

import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Test.Model.GameModelMockup;

public class AsteroidsStateTest {
  GameModel game;
  AsteroidsState state;
  @Before public void setUp() {
    game = new GameModelMockup();
    state = new AsteroidsState(game);
  }
  @Test public void trivialTest() {
    assertEquals("Wrong score", 100, state.getScore());
    assertEquals("Wrong lives", 3, state.getLives());
    assertFalse("Wrong pause", state.isPaused());
    assertFalse("Wrong over", state.isOver());
    assertFalse("Wrong waiting", state.isWaiting());
  }
  @Test public void listTest() {
    game.getList().get(0).setX(20);
    assertFalse("Shallow copy", state.getObjectIterator().next().getX() == 20);
  }
}

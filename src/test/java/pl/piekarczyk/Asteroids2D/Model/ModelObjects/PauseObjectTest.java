import org.junit.*;
import static org.junit.Assert.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.ModelObjects.PauseObject;
import pl.piekarczyk.Asteroids2D.Test.Model.ModelObjects.*;

public class PauseObjectTest {
  PauseObject testee;
  ModelObjectsGameModelMockup game;
  @Before public void setUp() {
    game = new ModelObjectsGameModelMockup();
    testee = new PauseObject(game);
    game.keyState[Types.Keys.P.ordinal()] = true;
  }
  @Test public void constructorTest() {
    assertFalse("isRemovable", testee.isRemovable());
    assertEquals("getType", Types.ObjectTypes.PAUSE, testee.getType());
  }
  @Test public void copyConstructorTest() {
    testee.setRemovable(true);
    PauseObject testee2 = new PauseObject(testee);
    assertTrue("isRemovable", testee2.isRemovable());
    assertEquals("getType", Types.ObjectTypes.PAUSE, testee.getType());
  }
  @Test public void copyTest() {
    testee.setRemovable(true);
    PauseObject testee2 = testee.copy();
    assertTrue("isRemovable", testee2.isRemovable());
    assertEquals("getType", Types.ObjectTypes.PAUSE, testee.getType());
    assertNotSame("testee NotSame testee2", testee, testee2);
  }
  @Test public void stepTest() {
    testee.step();
    assertTrue("Game Paused", game.isPaused());
    testee.step();
    assertFalse("Game not Paused", game.isPaused());
    game.keyState[Types.Keys.P.ordinal()] = false;
    testee.step();
    assertFalse("Game not Paused", game.isPaused());
  }
}

import org.junit.*;
import static org.junit.Assert.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Model.ModelObjects.QuitObject;
import pl.piekarczyk.Asteroids2D.Test.Model.ModelObjects.*;

public class QuitObjectTest {
  QuitObject testee;
  ModelObjectsGameModelMockup game;
  @Before public void setUp() {
    game = new ModelObjectsGameModelMockup();
    testee = new QuitObject(game);
  }
  @Test public void constructorTest() {
    assertFalse("isRemovable", testee.isRemovable());
    assertEquals("getType", Types.ObjectTypes.QUIT, testee.getType());
  }
  @Test public void copyConstructorTest() {
    testee.setRemovable(true);
    QuitObject testee2 = new QuitObject(testee);
    assertTrue("isRemovable", testee2.isRemovable());
    assertEquals("getType", Types.ObjectTypes.QUIT, testee.getType());
  }
  @Test public void copyTest() {
    testee.setRemovable(true);
    QuitObject testee2 = testee.copy();
    assertTrue("isRemovable", testee2.isRemovable());
    assertEquals("getType", Types.ObjectTypes.QUIT, testee.getType());
    assertNotSame("testee NotSame testee2", testee, testee2);
  }
  @Test public void stepTest() {
    testee.step();
    //@OPT change error messages to asy what is wrong
    assertFalse("Game not stopped", game.gameStopped);
    game.keyState[Types.Keys.Q.ordinal()] = true;
    testee.step();
    assertTrue("Game stopped", game.gameStopped);
  }
}

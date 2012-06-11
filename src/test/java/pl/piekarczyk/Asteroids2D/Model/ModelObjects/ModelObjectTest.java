import org.junit.*;
import static org.junit.Assert.*;
import pl.piekarczyk.Asteroids2D.Model.GameModel;
import pl.piekarczyk.Asteroids2D.Test.Model.ModelObjects.*;

public class ModelObjectTest {
  GameModel game;
  ModelObjectMockup testee;
  @Before public void setUp() {
    game = new ModelObjectsGameModelMockup();
    testee = new ModelObjectMockup(game);
  }
  @Test public void constructorTest() {
    assertFalse("isRemovable", testee.isRemovable());
    assertEquals("getX", 0, testee.getX(), 0);
    assertEquals("getY", 0, testee.getY(), 0);
    assertEquals("getRot", 0, testee.getRot(), 0);
    assertEquals("getHeight", 0, testee.getHeight());
    assertEquals("getWidth", 0, testee.getWidth());
  }
  @Test public void copyConstructorTest() {
    testee.setRemovable(true);
    ModelObjectMockup testee2 = new ModelObjectMockup(testee);
    assertTrue("isRemovable", testee2.isRemovable());
  }
  @Test public void methodTest() {
    testee.setRemovable(true);
    assertTrue("isRemovable", testee.isRemovable());
  }
}

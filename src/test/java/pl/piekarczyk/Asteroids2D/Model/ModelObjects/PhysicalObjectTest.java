import org.junit.*;
import static org.junit.Assert.*;
import pl.piekarczyk.Asteroids2D.Test.Model.ModelObjects.*;
import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

public class PhysicalObjectTest {
  PhysicalObjectMockup testee;
  GameModel game;
  @Before public void setUp() {
    game = new ModelObjectsGameModelMockup();
    testee = new PhysicalObjectMockup(0, 0, game);
  }
  @Test public void constructorTest() {
    PhysicalObjectTestHelper.constructorTest(testee, 0, 0);
  }
  @Test public void copyConstructorTest() {
    PhysicalObjectTestHelper.copyConstructorPreTest(testee);
    PhysicalObjectMockup testee2 = new PhysicalObjectMockup(testee);
    PhysicalObjectTestHelper.copyConstructorTest(testee2);
  }
  @Test public void moveNorthTest() {
    PhysicalObjectTestHelper.moveNorthTest(testee);
  }
  @Test public void moveSouthTest() {
    PhysicalObjectTestHelper.moveSouthTest(testee);
  }
  @Test public void moveEastTest() {
    PhysicalObjectTestHelper.moveEastTest(testee);
  }
  @Test public void moveWestTest() {
    PhysicalObjectTestHelper.moveWestTest(testee);
  }
  @Test public void moveNorthEastTest() {
    PhysicalObjectTestHelper.moveNorthEastTest(testee);
  }
  @Test public void  moveSouthEastTest() {
     PhysicalObjectTestHelper.moveSouthEastTest(testee);
  }
  @Test public void moveSouthWestTest() {
    PhysicalObjectTestHelper.moveSouthWestTest(testee);
  }
  @Test public void moveNorthWestTest() {
    PhysicalObjectTestHelper.moveNorthWestTest(testee);
  }
}

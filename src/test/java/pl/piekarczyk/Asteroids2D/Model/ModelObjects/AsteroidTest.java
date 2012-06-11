import org.junit.*;
import static org.junit.Assert.*;
import pl.piekarczyk.Asteroids2D.Test.Model.ModelObjects.*;
import pl.piekarczyk.Asteroids2D.Model.*;
import pl.piekarczyk.Asteroids2D.Model.ModelObjects.Asteroid;

public class AsteroidTest {
  Asteroid testee;
  GameModel game;
  @Before public void setUp() {
    game = new ModelObjectsGameModelMockup();
    testee = new Asteroid(game);
  }
  @Test public void constructorTest() {
    PhysicalObjectTestHelper.constructorTest(testee, 20, 20);
  }
  @Test public void copyConstructorTest() {
    PhysicalObjectTestHelper.copyConstructorPreTest(testee);
    Asteroid testee2 = new Asteroid(testee);
    PhysicalObjectTestHelper.copyConstructorTest(testee2);
  }
  @Test public void copyTest() {
    Asteroid copy = testee.copy();
    assertNotSame(copy, testee);
    PhysicalObjectTestHelper.compareTest(testee, copy);
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

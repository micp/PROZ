import org.junit.*;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

public class StaticGameVectorTest {

  @Before public void setUp() {
  }
  @Test public void vectorBasicConstructorTest() {
    StaticGameVector a = new StaticGameVector(-1, 90);
    Assert.assertEquals(a.getMagnitude(), 1);
    Assert.assertEquals(a.getDirection(), 270);

    a = new StaticGameVector(1, 450);
    Assert.assertEquals(a.getMagnitude(), 1);
    Assert.assertEquals(a.getDirection(), 90);
  }
  @Test public void vectorSetTest() {
    StaticGameVector a = new StaticGameVector(1, 270);
    a.setMagnitude(-2);
    Assert.assertEquals(a.getMagnitude(), 2);
    Assert.assertEquals(a.getDirection(), 90);
    a.setDirection(720);
    Assert.assertEquals(a.getMagnitude(), 2);
    Assert.assertEquals(a.getDirection(), 0);
  }
  @Test public void vectorCopyConstructorTest() {
    StaticGameVector a = new StaticGameVector(
	new StaticGameVector(20, 20));
    Assert.assertEquals(a.getMagnitude(), 20);
    Assert.assertEquals(a.getDirection(), 20);
  }
  @Test public void vectorSumTest() {
    StaticGameVector a = new StaticGameVector(10, 0);
    StaticGameVector b = new StaticGameVector(10, 180);
    a.sumWith(b);
    Assert.assertEquals(a.getMagnitude(), 0);
  }
}

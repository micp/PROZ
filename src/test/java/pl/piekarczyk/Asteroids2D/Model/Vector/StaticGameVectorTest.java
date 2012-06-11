import org.junit.*;
import static org.junit.Assert.*;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;

public class StaticGameVectorTest {

  @Before public void setUp() {
  }
  @Test public void vectorBasicConstructorTest() {
    StaticGameVector a = new StaticGameVector(10, 90);
    assertEquals(10, a.getMagnitude(), 0);
    assertEquals(90, a.getDirection(), 0);

    a = new StaticGameVector(-1, 90);
    assertEquals(1, a.getMagnitude(), 0);
    assertEquals(270, a.getDirection(), 0);

    a = new StaticGameVector(1, 450);
    assertEquals(1, a.getMagnitude(), 0);
    assertEquals(90, a.getDirection(), 0);

    a = new StaticGameVector(1, -90);
    assertEquals(1, a.getMagnitude(), 0);
    assertEquals(270, a.getDirection(), 0);
  }
  @Test public void vectorSetTest() {
    StaticGameVector a = new StaticGameVector(1, 270);
    a.setMagnitude(3);
    assertEquals(3, a.getMagnitude(), 0);
    assertEquals(270, a.getDirection(), 0);
    a.setMagnitude(-2);
    assertEquals(2, a.getMagnitude(), 0);
    assertEquals(90, a.getDirection(), 0);
    a.setDirection(45);
    assertEquals(2, a.getMagnitude(), 0);
    assertEquals(45, a.getDirection(), 0);
    a.setDirection(720);
    assertEquals(2, a.getMagnitude(), 0);
    assertEquals(0, a.getDirection(), 0);
    a.setDirection(-45);
    assertEquals(2, a.getMagnitude(), 0);
    assertEquals(315, a.getDirection(), 0);
  }
  @Test public void vectorCopyConstructorTest() {
    StaticGameVector a = new StaticGameVector(
	new StaticGameVector(20, 20));
    assertEquals(20, a.getMagnitude(), 0);
    assertEquals(20, a.getDirection(), 0);
  }
  @Test public void vectorSumTest() {
    StaticGameVector a = new StaticGameVector(100, 0);
    StaticGameVector b = new StaticGameVector(100, 180);
    a.sumWith(b);
    assertEquals(0, a.getMagnitude(), 0);
    assertEquals(0, a.getDirection(), 0);

    a = new StaticGameVector(10, 0);
    b = new StaticGameVector(10, 90);
    a.sumWith(b);
    assertEquals(45, a.getDirection(), 0);

    a = new StaticGameVector(10, -45);
    b = new StaticGameVector(10, -135);
    a.sumWith(b);
    assertEquals(270, a.getDirection(), 0);

    a = new StaticGameVector(20, 0);
    b = new StaticGameVector(10, 0);
    a.sumWith(b);
    assertEquals(0, a.getDirection(), 0);
    assertEquals(30, a.getMagnitude(), 0);

    a = new StaticGameVector(24, 45);
    b = new StaticGameVector(10, 135);
    a.sumWith(b);
    assertEquals(25, a.getMagnitude(), 1);
    assertEquals(67, a.getDirection(), 1);

    a = new StaticGameVector(24, 135);
    b = new StaticGameVector(10, 225);
    a.sumWith(b);
    assertEquals(25, a.getMagnitude(), 1);
    assertEquals(157, a.getDirection(), 1);

    a = new StaticGameVector(24, 225);
    b = new StaticGameVector(10, 315);
    a.sumWith(b);
    assertEquals(25, a.getMagnitude(), 1);
    assertEquals(247, a.getDirection(), 1);

    a = new StaticGameVector(24, -45);
    b = new StaticGameVector(10, -135);
    a.sumWith(b);
    assertEquals(292.5, a.getDirection(), 1);
    assertEquals(25, a.getMagnitude(), 1);
  }
}

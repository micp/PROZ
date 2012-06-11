import java.lang.Math;
import org.junit.*;
import static org.junit.Assert.*;
import pl.piekarczyk.Asteroids2D.Model.Vectors.*;
import pl.piekarczyk.Asteroids2D.Model.Common.Types;

public class PSAccelVectorTest {
  private StaticGameVector n, e, s, w;
  private PSAccelVector p;
  @Before public void setUp() {
    n = new StaticGameVector(10, 0);
    e = new StaticGameVector(10, 90);
    s = new StaticGameVector(10, 180);
    w = new StaticGameVector(10, 270);
    p = new PSAccelVector(10, 0);
  }
  @Test public void constructorTest() {
    PSAccelVector pc = new PSAccelVector(-10, 0);
    assertEquals("getMagnitude", Math.log1p(10), pc.getMagnitude(), 0);
    assertEquals("getDirection", 180, pc.getDirection(), 0);
    assertEquals("getSpeedBase", 10, pc.getSpeedBase(), 0);
  }
  @Test public void copyConstructorTest() {
    PSAccelVector pc = new PSAccelVector(p);
    assertEquals("getMagnitude", Math.log1p(10), pc.getMagnitude(), 0);
    assertEquals("getDirection", 0, pc.getDirection(), 0);
    assertEquals("getSpeedBase", 10, pc.getSpeedBase());
  }
  @Test public void setSpeedBaseTest() {
    p.setSpeedBase(-10);
    assertEquals("getSpeedBase", 10, p.getSpeedBase());
    assertEquals("getDirection", 180, p.getDirection(), 0);
  }
  @Test public void accelTest() {
    p.accelerate();
    assertTrue(p.getMagnitude() > Math.log1p(10));
  }
  @Test public void decelTest() {
    p.decelerate();
    assertTrue(p.getMagnitude() < Math.log1p(10));
  }
  @Test public void magnitudeSymmetryTest() {
    p.accelerate();
    p.decelerate();
    assertTrue(p.getMagnitude() == Math.log1p(10));
  }
  @Test public void rotateLeft() {
    p.rotate(n, Types.Keys.LEFT);
    assertEquals("Rotating north", 270, p.getDirection(), 0);
    p.rotate(e, Types.Keys.LEFT);
    assertEquals("Rotating east", 0, p.getDirection(), 0);
    p.rotate(s, Types.Keys.LEFT);
    assertEquals("Rotating south", 90, p.getDirection(), 0);
    p.rotate(w, Types.Keys.LEFT);
    assertEquals("Rotating west", 180, p.getDirection(), 0);
  }
  @Test public void rotateRight() {
    p.rotate(n, Types.Keys.RIGHT);
    assertEquals("Rotating north", 90, p.getDirection(), 0);
    p.rotate(e, Types.Keys.RIGHT);
    assertEquals("Rotating east", 180, p.getDirection(), 0);
    p.rotate(s, Types.Keys.RIGHT);
    assertEquals("Rotating south", 270, p.getDirection(), 0);
    p.rotate(w, Types.Keys.RIGHT);
    assertEquals("Rotating west", 0, p.getDirection(), 0);
  }
  @Test public void rotate() {
    p.rotate(n);
    assertEquals("Rotating north", 0, p.getDirection(), 0);
    p.rotate(e);
    assertEquals("Rotating east", 90, p.getDirection(), 0);
    p.rotate(s);
    assertEquals("Rotating south", 180, p.getDirection(), 0);
    p.rotate(w);
    assertEquals("Rotating west", 270, p.getDirection(), 0);
  }
}
